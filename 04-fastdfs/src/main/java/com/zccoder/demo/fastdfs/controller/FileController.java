package com.zccoder.demo.fastdfs.controller;

import com.zccoder.demo.fastdfs.client.FastdfsClient;
import com.zccoder.demo.fastdfs.domain.FastdfsFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 标题：文件上传Controller<br>
 * 描述：文件上传Controller<br>
 * 时间：2018/07/06<br>
 *
 * @author zc
 **/
@Controller
public class FileController {

    private static final String UPLOADED_FOLDER = "D:/";
    private static Logger log = LoggerFactory.getLogger(FileController.class);

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @RequestMapping("uploadStatus")
    public String uploadStatus(){
        return "uploadStatus";
    }

    @PostMapping("upload1")
    public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:uploadStatus";
    }

    @PostMapping("/upload")
    public String singleFileUploadToFastDFS(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
        try {
            // Get the file and save it somewhere
            String path=saveFile(file);
            System.out.println("文件路径："+path);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
            redirectAttributes.addFlashAttribute("path",
                    "file path url '" + path + "'");
        } catch (Exception e) {
            log.error("upload file failed",e);
        }
        return "redirect:/uploadStatus";
    }

    @RequestMapping("down")
    public void download(String group, String file, HttpServletResponse res){
        InputStream inputStream = FastdfsClient.downFile(group,file);
        String fileName = file;
        res.setContentType("application/force-download");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(inputStream);
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
    }

    /**
     * 保存文件到FastDFS
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public String saveFile(MultipartFile multipartFile) throws IOException {
        String[] fileAbsolutePath={};
        String fileName=multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] fileBuff = null;
        InputStream inputStream=multipartFile.getInputStream();
        if(inputStream!=null){
            int len1 = inputStream.available();
            fileBuff = new byte[len1];
            inputStream.read(fileBuff);
        }
        inputStream.close();
        FastdfsFile file = new FastdfsFile(fileName, fileBuff, ext);
        try {
            //upload to fastdfs
            fileAbsolutePath = FastdfsClient.upload(file);
        } catch (Exception e) {
            log.error("upload file Exception!",e);
        }
        if (fileAbsolutePath==null) {
            log.error("upload file failed,please upload again!");
        }
        String path= FastdfsClient.getTrackerUrl()+fileAbsolutePath[0]+ "/"+fileAbsolutePath[1];
        return path;
    }
}

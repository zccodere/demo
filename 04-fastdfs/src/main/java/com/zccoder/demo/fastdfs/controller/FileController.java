package com.zccoder.demo.fastdfs.controller;

import com.zccoder.demo.fastdfs.client.FastdfsClient;
import com.zccoder.demo.fastdfs.domain.FastdfsFile;
import com.zccoder.demo.fastdfs.domain.FastdfsFileKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

/**
 * 文件上传Controller
 *
 * @author zc 2018-07-06
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
    public String uploadStatus() {
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
    public String singleFileUploadToFastDfs(@RequestParam("file") MultipartFile file,
                                            RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
        try {
            // Get the file and save it somewhere
            String path = saveFile(file);
            System.out.println("文件路径：" + path);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
            redirectAttributes.addFlashAttribute("path", "file path url '" + path + "'");
        } catch (Exception e) {
            log.error("upload file failed", e);
            redirectAttributes.addFlashAttribute("message", "Upload file failed：" + e.getMessage());
        }
        return "redirect:/uploadStatus";
    }

    @RequestMapping("down")
    public void download(String group, String file, HttpServletResponse res) {
        InputStream inputStream = FastdfsClient.downFile(group, file);
        if (Objects.isNull(inputStream)) {
            System.out.println("error");
            return;
        }

        res.setContentType("application/force-download");
        res.setHeader("Content-Disposition", "attachment;filename=" + file);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os;
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
     */
    private String saveFile(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        if (Objects.isNull(fileName)) {
            throw new RuntimeException("文件名称不能为空");
        }
        // 文件类型
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);

        InputStream inputStream = multipartFile.getInputStream();
        int length = inputStream.available();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(length);
        byte[] buffer = new byte[4096];
        int n;
        while (-1 != (n = inputStream.read(buffer))) {
            outputStream.write(buffer, 0, n);
        }
        inputStream.close();

        byte[] content = outputStream.toByteArray();
        outputStream.close();

        FastdfsFile file = new FastdfsFile(fileName, content, ext);
        try {
            //upload to fastdfs
            FastdfsFileKey key = FastdfsClient.upload(file);
            return FastdfsClient.getTrackerUrl() + key.getGroupName() + "/" + key.getRemoteFileName();
        } catch (Exception ex) {
            throw new RuntimeException("Upload file Exception：" + ex.getMessage(), ex);
        }
    }
}

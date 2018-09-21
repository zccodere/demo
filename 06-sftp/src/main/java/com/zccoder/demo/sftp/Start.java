package com.zccoder.demo.sftp;

import com.jcraft.jsch.ChannelSftp;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 标题：测试启动类<br>
 * 描述：测试启动类<br>
 * 时间：2018/09/21<br>
 *
 * @author zc
 **/
public class Start {

    public static void main(String[] args) throws Exception {
        upload();
    }

    private static void upload() throws Exception {

        Map<String, String> sftpDetails = new HashMap<>(8);
        // 设置主机ip，端口，用户名，密码
        sftpDetails.put(SftpConstants.SFTP_REQ_HOST, "192.168.130.128");
        sftpDetails.put(SftpConstants.SFTP_REQ_USERNAME, "zc");
        sftpDetails.put(SftpConstants.SFTP_REQ_PASSWORD, "zc");
        sftpDetails.put(SftpConstants.SFTP_REQ_PORT, "22");

        // 本地文件名
        String src = "D:/myfile.REQ";
        // 目标文件名
        String dst = "fsss/myfile.REQ";

        SftpChannel channel = new SftpChannel();
        ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);

        FileInputStream fis = new FileInputStream(src);
        if (fis != null) {
            // chSftp.put(fis, dst, ChannelSftp.OVERWRITE); // 代码段3
            chSftp.put(fis, dst);
        }

        chSftp.quit();
        try {
            channel.closeChannel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


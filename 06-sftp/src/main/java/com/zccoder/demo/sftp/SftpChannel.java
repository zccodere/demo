package com.zccoder.demo.sftp;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Properties;

/**
 * 标题：SFTP通道连接<br>
 * 描述：SFTP通道连接<br>
 * 时间：2018/09/21<br>
 *
 * @author zc
 **/
public class SftpChannel {


    Session session = null;
    Channel channel = null;

    private static final Logger LOG = LoggerFactory.getLogger(SftpChannel.class);

    public ChannelSftp getChannel(Map<String, String> sftpDetails, int timeout) throws JSchException {

        String ftpHost = sftpDetails.get(SftpConstants.SFTP_REQ_HOST);
        String port = sftpDetails.get(SftpConstants.SFTP_REQ_PORT);
        String ftpUserName = sftpDetails.get(SftpConstants.SFTP_REQ_USERNAME);
        String ftpPassword = sftpDetails.get(SftpConstants.SFTP_REQ_PASSWORD);

        int ftpPort = SftpConstants.SFTP_DEFAULT_PORT;
        if (port != null && !"".equals(port)) {
            ftpPort = Integer.valueOf(port);
        }

        // 创建JSch对象
        JSch jsch = new JSch();
        // 根据用户名，主机ip，端口获取一个Session对象
        session = jsch.getSession(ftpUserName, ftpHost, ftpPort);
        LOG.debug("Session created.");
        if (ftpPassword != null) {
            // 设置密码
            session.setPassword(ftpPassword);
        }
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        // 为Session对象设置properties
        session.setConfig(config);
        // 设置timeout时间
        session.setTimeout(timeout);
        // 通过Session建立链接
        session.connect();
        LOG.debug("Session connected.");

        LOG.debug("Opening Channel.");
        // 打开SFTP通道
        channel = session.openChannel("sftp");
        // 建立SFTP通道的连接
        channel.connect();
        LOG.debug("Connected successfully to ftpHost = " + ftpHost + ",as ftpUserName = " + ftpUserName
                + ", returning: " + channel);
        return (ChannelSftp) channel;
    }

    public void closeChannel() throws Exception {
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }


}

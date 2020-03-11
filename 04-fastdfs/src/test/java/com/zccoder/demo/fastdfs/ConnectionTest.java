package com.zccoder.demo.fastdfs;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * 客户端连接测试类
 *
 * @author zc 2018-07-06
 **/
public class ConnectionTest {

    @Test
    public void connection() throws Exception {
        ClientGlobal.initByProperties("fastdfs-client.properties");
        System.out.println(ClientGlobal.configInfo());

        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        Assert.assertNotNull(trackerServer);

        StorageServer storageServer = trackerClient.getStoreStorage(ClientGlobal.getG_tracker_group().getConnection());
        Assert.assertNotNull(storageServer);

        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        Assert.assertNotNull(storageClient);

        String[] fileIds = storageClient.upload_file("D:/myFile.txt", "txt", null);

        System.out.println(fileIds.length);
        System.out.println("组名：" + fileIds[0]);
        System.out.println("路径: " + fileIds[1]);

    }

    @Test
    public void testUpload() {    //上传文件
        TrackerServer trackerServer = null;
        StorageServer storageServer;

        try {
            ClientGlobal.initByProperties("fastdfs-client.properties");
            System.out.println(ClientGlobal.configInfo());
            TrackerClient tracker = new TrackerClient();
            trackerServer = tracker.getConnection();
            storageServer = tracker.getStoreStorage(trackerServer);

            //要上传的文件路径
            String local_filename = "D:/myFile.txt";
            // 这个参数可以指定，也可以不指定，如果指定了，可以根据 testGetFileMate()方法来获取到这里面的值
            NameValuePair[] nvp = new NameValuePair[]{
                    new NameValuePair("age", "18"),
                    new NameValuePair("sex", "male")
            };

            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            String[] fileIds = storageClient.upload_file(local_filename, "txt", nvp);

            System.out.println(fileIds.length);
            System.out.println("组名：" + fileIds[0]);
            System.out.println("路径: " + fileIds[1]);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != trackerServer) trackerServer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

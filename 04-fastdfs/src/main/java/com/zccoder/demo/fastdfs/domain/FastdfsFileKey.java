package com.zccoder.demo.fastdfs.domain;

/**
 * FastDEF文件Key
 *
 * @author zc 2018-07-06
 **/
public class FastdfsFileKey {

    /**
     * 分组名称
     */
    private String groupName;
    /**
     * 文件名称
     */
    private String remoteFileName;

    @Override
    public String toString() {
        return "FastdfsFileKey{" +
                "groupName='" + groupName + '\'' +
                ", remoteFileName='" + remoteFileName + '\'' +
                '}';
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRemoteFileName() {
        return remoteFileName;
    }

    public void setRemoteFileName(String remoteFileName) {
        this.remoteFileName = remoteFileName;
    }
}

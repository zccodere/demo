package com.zccoder.demo.fastdfs.domain;

import java.util.Arrays;

/**
 * FastDEF文件
 *
 * @author zc 2018-07-06
 **/
public class FastdfsFile {

    /**
     * 文件名称
     */
    private String name;
    /**
     * 文件内容
     */
    private byte[] content;
    /**
     * 扩展
     */
    private String ext;
    /**
     * md5
     */
    private String md5;
    /**
     * 作者
     */
    private String author;

    public FastdfsFile() {
    }

    public FastdfsFile(String name, byte[] content, String ext) {
        this.name = name;
        this.content = content;
        this.ext = ext;
    }

    @Override
    public String toString() {
        return "FastdfsFile{" +
                "name='" + name + '\'' +
                ", content=" + Arrays.toString(content) +
                ", ext='" + ext + '\'' +
                ", md5='" + md5 + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    /**
     * 获取 文件名称
     *
     * @return name 文件名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置 文件名称
     *
     * @param name 文件名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 文件内容
     *
     * @return content 文件内容
     */
    public byte[] getContent() {
        return this.content;
    }

    /**
     * 设置 文件内容
     *
     * @param content 文件内容
     */
    public void setContent(byte[] content) {
        this.content = content;
    }

    /**
     * 获取 扩展
     *
     * @return ext 扩展
     */
    public String getExt() {
        return this.ext;
    }

    /**
     * 设置 扩展
     *
     * @param ext 扩展
     */
    public void setExt(String ext) {
        this.ext = ext;
    }

    /**
     * 获取 md5
     *
     * @return md5 md5
     */
    public String getMd5() {
        return this.md5;
    }

    /**
     * 设置 md5
     *
     * @param md5 md5
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    /**
     * 获取 作者
     *
     * @return author 作者
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * 设置 作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }
}

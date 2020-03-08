package com.zccoder.demo.image;

import java.awt.image.BufferedImage;

/**
 * 待合并图片
 *
 * @author zc 2020-03-08
 */
public class ChildImage {

    /**
     * 图片内容
     */
    private BufferedImage image;
    /**
     * 离顶部距离
     */
    private int x;
    /**
     * 离左边距离
     */
    private int y;

    public ChildImage(BufferedImage image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
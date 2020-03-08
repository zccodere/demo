package com.zccoder.demo.image;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * 本地图片合并测试
 *
 * @author zc 2020-03-08
 */
public class ImageLocalDemo {

    public static void main(String[] args) throws Exception {
        test1();
    }

    private static void test1() throws Exception {
        BufferedImage originalImage1 = ImageIO.read(new File("D:\\Work\\Image\\1001-1.jpg"));
        BufferedImage originalImage2 = ImageIO.read(new File("D:\\Work\\Image\\1002-1.jpg"));

        // 两张图片，左右合成
        int w = 800;
        int h = 800;
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = (Graphics2D) bi.getGraphics();
        graphics.setBackground(Color.WHITE);
        graphics.clearRect(0, 0, w, h);

        graphics.drawImage(originalImage1, null, 0, 200);
        graphics.drawImage(originalImage2, null, 400, 200);

        //输出文件
        ImageIO.write(bi, "jpg", new File("D:\\Work\\Image\\HC2-1.jpg"));
    }
}

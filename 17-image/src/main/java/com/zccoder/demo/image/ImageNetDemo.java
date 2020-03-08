package com.zccoder.demo.image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Base64;

import javax.imageio.ImageIO;

/**
 * 合并网络图片
 *
 * @author zc 2020-03-08
 */
public class ImageNetDemo {

    public static void main(String[] args) throws Exception {
        test1();
    }

    private static void test1() throws Exception {
        String path1 = "https://img2.epetbar.com/common/upload/commonfile/2020/02/04/161155_493678.jpg";
        String path2 = "https://img2.epetbar.com/common/upload/commonfile/2020/02/010/112017_454979.jpg";

        BufferedImage originalImage1 = ImageUtils.getBufferedImage(path1);
        BufferedImage originalImage2 = ImageUtils.getBufferedImage(path2);

        // 两张图片，左右合成
        int w = 800;
        int h = 800;
        BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        BufferedImage thumbnail380 = new BufferedImage(380, 380, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.setBackground(Color.WHITE);
        graphics.clearRect(0, 0, w, h);

        graphics.drawImage(originalImage1, null, 0, 200);
        graphics.drawImage(originalImage2, null, 400, 200);

        Graphics2D graphics1 = thumbnail380.createGraphics();
        graphics1.drawImage(originalImage1, 0, 0, 380, 380, null);
        ImageIO.write(thumbnail380, "jpg", new File("D:\\Work\\Image\\HC380.jpg"));

        BufferedImage thumbnail = getThumbnail380(originalImage1);
        ImageIO.write(thumbnail, "jpg", new File("D:\\Work\\Image\\HC380-1.jpg"));

        // 转换为Base64编码
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(originalImage1, "jpg", outputStream);
        String imageBaseCode = Base64.getEncoder().encodeToString(outputStream.toByteArray());
        System.out.println(imageBaseCode);
    }

    /**
     * 将原图缩略为380*380的图片
     */
    private static BufferedImage getThumbnail380(BufferedImage image) {
        BufferedImage newImage = new BufferedImage(380, 380, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(image, 0, 0, 380, 380, null);
        return newImage;
    }
}

package com.zccoder.demo.image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

import javax.imageio.ImageIO;

/**
 * 图片工具类
 *
 * @author zc 2020-02-21
 */
public class ImageUtils {

    private static final String IMAGE_BASE64_HEAD = "data:image/jpeg;base64,";

    /**
     * 对图片进行base64编码
     *
     * @param mediaPath 图片路径（绝对路径）
     * @return base64编码
     */
    public static String encoder(String mediaPath) {
        Objects.requireNonNull(mediaPath);
        return bufferedImageEncoder(getBufferedImage(mediaPath));
    }

    /**
     * 合并图片
     *
     * @param mediaPathList 图片路径（绝对路径）
     * @return base64编码
     */
    public static String merge(List<String> mediaPathList) {
        List<ChildImage> childImageList = buildChildImageList(mediaPathList);
        // 合并后的图片
        BufferedImage newImage = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        // 设置白底
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.setBackground(Color.WHITE);
        graphics.clearRect(0, 0, 800, 800);
        // 合并图片
        childImageList.forEach(childImage -> graphics.drawImage(childImage.getImage(), null, childImage.getX(), childImage.getY()));
        // base64编码
        return bufferedImageEncoder(newImage);
    }

    private static List<ChildImage> buildChildImageList(List<String> mediaPathList) {
        List<ChildImage> imageList = new ArrayList<>(16);
        int size = mediaPathList.size();
        switch (size) {
            case 2:
                mergeTwo(imageList, mediaPathList);
                break;
            case 3:
                mergeThree(imageList, mediaPathList);
                break;
            case 4:
                mergeFour(imageList, mediaPathList);
                break;
            default:
                mergeFive(imageList, mediaPathList);
        }
        return imageList;
    }

    private static void mergeFive(List<ChildImage> imageList, List<String> mediaPathList) {
        imageList.add(new ChildImage(getThumbnail380(mediaPathList.get(0)), 10, 15));
        imageList.add(new ChildImage(getThumbnail380(mediaPathList.get(1)), 410, 15));
        imageList.add(new ChildImage(getThumbnail250(mediaPathList.get(2)), 10, 475));
        imageList.add(new ChildImage(getThumbnail250(mediaPathList.get(3)), 275, 475));
        imageList.add(new ChildImage(getThumbnail250(mediaPathList.get(4)), 540, 475));
    }

    private static void mergeFour(List<ChildImage> imageList, List<String> mediaPathList) {
        imageList.add(new ChildImage(getThumbnail380(mediaPathList.get(0)), 10, 10));
        imageList.add(new ChildImage(getThumbnail380(mediaPathList.get(1)), 410, 10));
        imageList.add(new ChildImage(getThumbnail380(mediaPathList.get(2)), 10, 410));
        imageList.add(new ChildImage(getThumbnail380(mediaPathList.get(3)), 410, 410));
    }

    private static void mergeThree(List<ChildImage> imageList, List<String> mediaPathList) {
        imageList.add(new ChildImage(getThumbnail380(mediaPathList.get(0)), 210, 10));
        imageList.add(new ChildImage(getThumbnail380(mediaPathList.get(1)), 10, 410));
        imageList.add(new ChildImage(getThumbnail380(mediaPathList.get(2)), 410, 410));
    }

    private static void mergeTwo(List<ChildImage> imageList, List<String> mediaPathList) {
        imageList.add(new ChildImage(getThumbnail380(mediaPathList.get(0)), 10, 210));
        imageList.add(new ChildImage(getThumbnail380(mediaPathList.get(1)), 410, 210));
    }

    private static String bufferedImageEncoder(BufferedImage image) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ImageIO.write(image, "jpg", outputStream);
            return IMAGE_BASE64_HEAD + Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (Exception ex) {
            throw new RuntimeException("对图片进行base64编码异常：" + ex.getMessage(), ex);
        }
    }

    private static BufferedImage getThumbnail380(String mediaPath) {
        return getThumbnail(getBufferedImage(mediaPath), 380, 380);
    }

    private static BufferedImage getThumbnail250(String mediaPath) {
        return getThumbnail(getBufferedImage(mediaPath), 250, 250);
    }

    private static BufferedImage getThumbnail(BufferedImage image, int width, int height) {
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(image, 0, 0, width, height, null);
        return newImage;
    }

    static BufferedImage getBufferedImage(String mediaPath) {
        URL url;
        InputStream is = null;
        BufferedImage bufferedImage;
        try {
            url = new URL(mediaPath);
            is = url.openStream();
            bufferedImage = ImageIO.read(is);
        } catch (Exception e) {
            throw new RuntimeException("获取图片内容失败：" + mediaPath);
        } finally {
            try {
                if (Objects.nonNull(is)) {
                    is.close();
                }
            } catch (IOException ignored) {
            }
        }
        if (Objects.isNull(bufferedImage)) {
            throw new RuntimeException("获取图片内容失败：" + mediaPath);
        }
        return bufferedImage;
    }
}

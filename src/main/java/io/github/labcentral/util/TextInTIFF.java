package io.github.labcentral.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//todo 根据文件名获得对应的蛋白名称
//todo 建立一个数据库，蛋白名称和分子量一一对应

/**
 * @author Zemise
 */
public class TextInTIFF {
    //像图片文件插入指定字符
    public static void insertText(File imageFile, String insertText) {
        try {
            // 读取图片文件
            BufferedImage image = ImageIO.read(imageFile);
            int width = image.getWidth();
            int height = image.getHeight();
            // 创建一个带有指定的宽度和高度的空图像
            BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = newImage.createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // 绘制图片
            graphics.drawImage(image, 0, 0, null);
            // 设置字体和颜色
            graphics.setFont(new Font("宋体", Font.PLAIN, 20));
            graphics.setColor(Color.red);
            // 绘制文字
            graphics.drawString(insertText, 10, 20);
            // 写入 TIFF 图片文件
            ImageIO.write(newImage, getFileSuffix(imageFile), new File(imageFile.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insetTextWithTop(File imageFile, String insertText, File outFile, Font font, Color color) {
        if (!outFile.exists()) {
            outFile.mkdirs();
        }

        try {
            // 读取原始图片
            BufferedImage originalImage = ImageIO.read(imageFile);

            // 获取图片宽度和高度
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();

            int extraHeight = getTextHeight(insertText, font);

            // 创建一个新的带有白色区域的BufferedImage
            int newImageHeight = height + extraHeight;
            BufferedImage newImage = new BufferedImage(width, newImageHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = newImage.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // 在新图像上绘制白色区域
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, width, newImageHeight);

            // 将原始图像绘制到新图像中
            g.drawImage(originalImage, 0, extraHeight, null);

            // 在白色区域中添加文字
            g.setColor(color);
            g.setFont(font);


            // the font metrics of this graphics context's current font.
            FontMetrics fontMetrics = g.getFontMetrics();


            // x = 0，太贴近左侧，为了好看，多加一点
            int x = fontMetrics.getHeight() / 3;

            String[] lines = insertText.split("\n");
            for (int i = 0; i < lines.length; i++) {
                g.drawString(lines[i], x, (i + 1) * fontMetrics.getHeight());
            }

            g.dispose();

            // 写入图片文件
            ImageIO.write(newImage, getFileSuffix(imageFile), new File(outFile, imageFile.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insertText(File imageFile, String insertText, File outFile, Font font, Color color) {
        if (!outFile.exists()) {
            outFile.mkdirs();
        }
        try {
            // 读取图片文件
            BufferedImage image = ImageIO.read(imageFile);
            int width = image.getWidth();
            int height = image.getHeight();
            // 创建一个带有指定的宽度和高度的空图像
            BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = newImage.createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // 绘制图片
            graphics.drawImage(image, 0, 0, null);
            // 设置字体和颜色
            graphics.setFont(font);
            graphics.setColor(color);

            FontMetrics fontMetrics = graphics.getFontMetrics();

            String[] lines = insertText.split("\n");
            for (int i = 0; i < lines.length; i++) {
                graphics.drawString(lines[i], 0, (i + 1) * fontMetrics.getHeight());
            }

            // 写入图片文件
            ImageIO.write(newImage, getFileSuffix(imageFile), new File(outFile, imageFile.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 计算需要添加的白色区域的高度


    /**
     * 计算一段特定字体的文本的高度
     * @param text 可能包含换行的字段
     * @param font 文字的awt字体
     * @return
     */
    private static int getTextHeight(String text, Font font) {
        BufferedImage tempImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        Graphics g = tempImage.getGraphics();
        g.setFont(font);
        // 一行文字的高度
        FontMetrics fm = g.getFontMetrics();
        int lineHeight = fm.getHeight();
        // 文字行数
        int numLines = text.split("\n").length;

        g.dispose();
        // 多一点，避免太贴近底部
        return lineHeight * numLines + lineHeight / 2;
    }


    //用递归的方式获得文件对象下的所有图片文件

    /**
     * 递归获取文件夹下所有的图片文件
     *
     * @param fileFolder  文件夹对象
     * @param imageFormat 图片文件后缀名字符串
     * @return 该文件夹下所有符合后缀名的文件对象列表
     */
    public static List<File> getImageFile(File fileFolder, String imageFormat) {
        ArrayList<File> tiffFiles = new ArrayList<>();
        File[] files = fileFolder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory() || file.getName().endsWith("." + imageFormat.toLowerCase());
            }
        });

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // todo 暂时不递归
                    //tiffFiles.addAll(getImageFile(file, imageFormat));
                } else {
                    tiffFiles.add(file);
                }
            }
        }
        return tiffFiles;
    }

    //获取文件的后缀名

    /**
     * 获取文件的后缀名
     *
     * @param file File文件对象
     * @return 文件对象的后缀名字符串
     */
    public static String getFileSuffix(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf(".");
        String suffix = "";

        if (dotIndex != -1) {
            suffix = fileName.substring(dotIndex + 1);
        }
        return suffix;
    }


}

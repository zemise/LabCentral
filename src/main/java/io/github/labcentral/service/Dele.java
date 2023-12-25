package io.github.labcentral.service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
    需求：
        根据所获得的文件路径，删除带特定字符的文件
 */
public class Dele {
    static StringBuilder msg = new StringBuilder();   //定义一个消息变量，用于输出提示信息

    public static String deleteFile(File file, String reg) {
        int count = 0;  //定义一个计数变量，用于存储删除文件的数目
        File newFile = new File(file.getPath(), ("已删除_not_" + reg));
        newFile.mkdirs();   //在目标文件夹下，创建“已删除”文件夹

        File[] files = file.listFiles();    //获得该文件夹对象下的子文件集合

        for (File f : files) {
            if (!f.isHidden() & !f.isDirectory()) {  //不处理隐藏文件和子文件夹
                String fileName = f.getName();
                if (!fileName.matches(".*" + reg + ".*")) {  //文件名是否包含匹配字符
                    File s = new File(newFile.getPath(), f.getName());
                    copyFileUsingStream(f, s);
                    f.delete();
                    count++;
                }
            }
        }
        Date date = new Date();
        SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        msg.append(ss.format(date.getTime()))
                .append(" 完成！已删除匹配文件，总计：")
                .append(count)
                .append("个\n");
        return msg.toString();
    }


    public static void copyFileUsingStream(File source, File dest) {    //以流的方式复制文件
        InputStream is = null;
        OutputStream os = null;

        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

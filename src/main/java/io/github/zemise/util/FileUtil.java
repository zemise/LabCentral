package io.github.zemise.util;

import io.github.zemise.Main;

import java.io.*;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/8
 */
public class FileUtil {
    public static void write(String content) {
        File file = new File(Main.class.getClassLoader().getResource("data/data.txt").getFile());
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(content);
            bw.flush();
            System.out.println("内容已成功写入文件。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String read() {
        InputStream resourceAsStream = FileUtil.class.getClassLoader().getResourceAsStream("data/data.txt");
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream))) {
            String line = reader.readLine();
            while (line != null) {
                builder.append(line).append("\n");
                line = reader.readLine();
            }
            reader.close();
            System.out.println("内容已成功读取文件");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}

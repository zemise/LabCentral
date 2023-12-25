package io.github.labcentral.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class FontSizeConverter {

    public static final Map<String, String> fontSizeMapping = new LinkedHashMap<>();

    static {
        // 添加字号映射
        fontSizeMapping.put("初号", "42");
        fontSizeMapping.put("小初", "36");
        fontSizeMapping.put("一号", "26");
        fontSizeMapping.put("小一", "24");
        fontSizeMapping.put("二号", "22");
        fontSizeMapping.put("小二", "18");
        fontSizeMapping.put("三号", "16");
        fontSizeMapping.put("小三", "15");
        fontSizeMapping.put("四号", "14");
        fontSizeMapping.put("小四", "12");
        fontSizeMapping.put("五号", "10.5");
        fontSizeMapping.put("小五", "9");
        fontSizeMapping.put("六号", "7");
        fontSizeMapping.put("小六", "6.5");
        fontSizeMapping.put("七号", "5.5");
        fontSizeMapping.put("八号", "5"); // 注意：有时候八号字与七号字相同

        // 可以添加其他字号映射
    }

    public static String getFontSize(String chineseFontSize) {
        return fontSizeMapping.get(chineseFontSize);
    }

    public static void main(String[] args) {
        String chineseFontSize = "小四";
        String arabicFontSize = getFontSize(chineseFontSize);
        if (arabicFontSize != null) {
            System.out.println(chineseFontSize + " 对应的阿拉伯数字字号是：" + arabicFontSize);
        } else {
            System.out.println("未找到对应的字号映射。");
        }
    }
}

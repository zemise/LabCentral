package io.github.labcentral;

import io.github.labcentral.domain.Constants;
import javafx.scene.text.Font;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;

@Slf4j
@Component
public class FontLoader {
    private static final double fontSize = 13;
    private static final String fontPath = Constants.FONT_PACKAGE;

    @Bean
    private void loadFont(){
        //scanFontsAndLoad(new File(getClass().getClassLoader().getResource("/font/").toExternalForm()));
    }

    public void logLoadedFonts() {
        // 获取所有已加载字体的名称列表
        String[] fontFamilies = Font.getFamilies().toArray(new String[0]);
        log.info("Loaded font families: ");
        for (String fontFamily : fontFamilies) {
            log.info(fontFamily);
        }
    }


    private static void scanFontsAndLoad(File folder) {
        File[] files = folder.listFiles();

        System.out.println(files);

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    scanFontsAndLoad(file);
                } else {
                    if (isFontFile(file)) {
                        Font.loadFont(file.getPath(), fontSize);
                        log.info("successfully loaded font: {}", file.getName());
                        System.out.println(file.getName());
                    }
                }
            }
        }
    }

    private static boolean isFontFile(File file) {
        if (file.getName().toLowerCase().endsWith(".ttf")) {
            return true;
        }
        return false;
    }
}
package io.github.labcentral.service;

import javafx.scene.text.Font;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author Zemise
 */
@Slf4j
@Service
public class FontLoader {
    private static final double FONT_SIZE = 13;

    //@Bean
    private void loadFont() {
        String fontPath = this.getClass().getResource("../font").getPath();
        scanFontsAndLoad(new File(fontPath));
    }

    public void logLoadedFonts() {
        // 获取所有已加载字体的名称列表
        String[] fontFamilies = Font.getFamilies().toArray(new String[0]);
        log.debug("Loaded font families: ");
        for (String fontFamily : fontFamilies) {
            log.debug(fontFamily);
        }
    }

    private static void scanFontsAndLoad(File folder) {
        log.debug("Scanning fonts path: {}", folder);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    scanFontsAndLoad(file);
                } else {
                    if (isFontFile(file)) {
                        Font.loadFont(file.getPath(), FONT_SIZE);
                        log.info("successfully loaded font: {}", file.getName());
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
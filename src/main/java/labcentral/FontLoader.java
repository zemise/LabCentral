package labcentral;

import javafx.scene.text.Font;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Slf4j
@Component
public class FontLoader{
    @Value("${fonts}")
    private String[] fontList;

    public void loadCustomFont() {
        for (String font : fontList) {
            try {
                System.out.println(font);
                InputStream fontStream = this.getClass().getResourceAsStream("/fonts/" + font);
                if (null != fontStream) {
                    Font.loadFont(fontStream, 52);
                    log.info("Loaded font: {}", font);
                } else {
                    log.error("Font not found: {}", font);
                }
            } catch (Exception e) {
                log.error("Error loading font: {}", font, e);
            }
        }
    }

    public void logLoadedFonts() {
        // 获取所有已加载字体的名称列表
        String[] fontFamilies = Font.getFamilies().toArray(new String[0]);
        log.info("Loaded font families: ");
        for (String fontFamily : fontFamilies) {
            log.info(fontFamily);
        }
    }
}

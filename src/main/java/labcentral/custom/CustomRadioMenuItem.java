package labcentral.custom;

import javafx.scene.control.RadioMenuItem;
import javafx.scene.text.Font;

public class CustomRadioMenuItem extends RadioMenuItem {
    public CustomRadioMenuItem(String text, String fontName, double fontSize) {
        super(text);
        setTextStyle(fontName, fontSize);
    }

    public CustomRadioMenuItem(String text, Font font, double fontSize) {
        super(text);
        setTextStyle(font.getName(), fontSize);
    }

    private void setTextStyle(String fontName, double fontSize) {
        // 应用字体样式到文本
        setStyle("-fx-font-family: '" + fontName + "'; -fx-font-size: " + fontSize + "px;");
    }


}

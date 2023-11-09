package labcentral.custom;

import javafx.scene.control.RadioMenuItem;
import javafx.scene.text.Font;

public class CustomRadioMenuItem extends RadioMenuItem {
    public CustomRadioMenuItem(String text, String fontName) {
        super(text);
        setTextStyle(fontName);
    }

    public CustomRadioMenuItem(String text) {
        super(text);
    }

    private void setTextStyle(String fontName) {
        double fontSize = Font.getDefault().getSize();
        // 应用字体样式到文本
        setStyle("-fx-font-family: '" + fontName + "'; -fx-font-size: " + fontSize + "px;");
    }


}

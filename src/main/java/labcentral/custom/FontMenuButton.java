package labcentral.custom;

import javafx.beans.binding.Bindings;
import javafx.scene.control.*;
import javafx.scene.text.Font;

public class FontMenuButton extends MenuButton {
    // 返回选择的font
    public Font font;

    // 当前系统默认的字体
    private final Font defaultFont = Font.getDefault();
    // 当前系统默认的字体大小
    private final double defaultFontSize = defaultFont.getSize();
    private final int recentlyFontMaxSize = 10;
    private final LimitedOrderedSet<RadioMenuItem> recentlyFontItems = new LimitedOrderedSet<>(recentlyFontMaxSize);
    private final LimitedOrderedSet<String> recentlyFontNames = new LimitedOrderedSet<>(recentlyFontMaxSize);

    public FontMenuButton() {
        setText(defaultFont.getName());
        // 添加样式类
        getStyleClass().add("menu-button");

        ToggleGroup recentlyFontsGroup = new ToggleGroup();

        MenuItem recentFontItem = new CustomRadioMenuItem("最近使用的字体", defaultFont, defaultFontSize);
        recentFontItem.setDisable(true);
        getItems().add(recentFontItem);

        MenuItem blankItem = new CustomRadioMenuItem("所有字体", defaultFont, defaultFontSize);

        blankItem.setDisable(true);
        getItems().addAll(new SeparatorMenuItem(), blankItem, new SeparatorMenuItem());

        ToggleGroup allFontsGroup = new ToggleGroup();

        Font.getFamilies().forEach(fontName -> {
            RadioMenuItem fontItem = new CustomRadioMenuItem(fontName, fontName, defaultFontSize);
            fontItem.setToggleGroup(allFontsGroup);
            getItems().add(fontItem);

            fontItem.setOnAction(event -> {
                if (fontItem.isSelected()) {
                    RadioMenuItem removeLastFontItem = recentlyFontItems.addAndReturnRemoveLast(fontItem);
                    String removeLastFontName = recentlyFontNames.addAndReturnRemoveLast(fontItem.getText());

                    // 如果没有最近的使用字体被删除
                    if (null == removeLastFontName) {

                        RadioMenuItem lateFontItem = new CustomRadioMenuItem(fontItem.getText(), fontItem.getText(), defaultFontSize);
                        lateFontItem.setToggleGroup(recentlyFontsGroup);

                        getItems().add(1, lateFontItem);

                        lateFontItem.setSelected(true);
                        Bindings.bindBidirectional(fontItem.selectedProperty(), lateFontItem.selectedProperty());
                    } else {
                        // 如果有最近的使用字体被删除，那么要移除组件，以及绑定
                        getItems().remove(removeLastFontItem);

                    }
                    setText(fontItem.getText());
                    font = new Font(fontItem.getText(), defaultFontSize);
                }

            });
        });
    }
}

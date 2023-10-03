package labcentral.custom;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.*;
import javafx.scene.text.Font;

public class FontMenuButton extends MenuButton {
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
            RadioMenuItem fontItem = new CustomRadioMenuItem(fontName, defaultFont, defaultFontSize);
            fontItem.setToggleGroup(allFontsGroup);
            getItems().add(fontItem);

        });

        allFontsGroup.selectedToggleProperty().addListener((observable) -> {
            if (null != allFontsGroup.getSelectedToggle()) {
                CustomRadioMenuItem selected = (CustomRadioMenuItem) allFontsGroup.getSelectedToggle();

                RadioMenuItem removeLastFontItem = recentlyFontItems.addAndReturnRemoveLast(selected);
                String removeLastFontName = recentlyFontNames.addAndReturnRemoveLast(selected.getText());

                // 如果没有最近的使用字体被删除
                if (null == removeLastFontName) {

                    RadioMenuItem lateFontItem = new CustomRadioMenuItem(selected.getText(), defaultFont, defaultFontSize);
                    lateFontItem.setToggleGroup(recentlyFontsGroup);

                    getItems().add(1, lateFontItem);

                    lateFontItem.setSelected(true);
                    Bindings.bindBidirectional(selected.selectedProperty(), lateFontItem.selectedProperty());
                } else {
                    // 如果有最近的使用字体被删除，那么要移除组件，以及绑定
                    getItems().remove(removeLastFontItem);
                }

                setText(selected.getText());
                fontProperty().set(new Font(selected.getText(), defaultFontSize));
            }
        });
    }
}

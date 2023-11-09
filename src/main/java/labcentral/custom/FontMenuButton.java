package labcentral.custom;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.*;
import javafx.scene.text.Font;

public class FontMenuButton extends MenuButton {
    // 当前系统默认的字体
    private final Font defaultFont = Font.getDefault();

    private final int recentlyFontMaxSize = 10;
    private final LimitedOrderedSet<RadioMenuItem> recentlyFontItems = new LimitedOrderedSet<>(recentlyFontMaxSize);
    private final LimitedOrderedSet<String> recentlyFontNames = new LimitedOrderedSet<>(recentlyFontMaxSize);

    /**
     * 选中的字体
     */
    private final SimpleObjectProperty<String> selectedFontName = new SimpleObjectProperty<>(defaultFont.getName());

    public SimpleObjectProperty<String> fontNameProperty() {
        return selectedFontName;
    }

    public FontMenuButton() {
        // 初始字体
        setText(defaultFont.getName());
        // 添加样式类
        getStyleClass().add("menu-button");

        ToggleGroup recentlyFontsGroup = new ToggleGroup();

        MenuItem recentFontItem = new RadioMenuItem("最近使用的字体");
        recentFontItem.setDisable(true);
        getItems().add(recentFontItem);

        MenuItem blankItem = new RadioMenuItem("所有字体");

        blankItem.setDisable(true);
        getItems().addAll(new SeparatorMenuItem(), blankItem, new SeparatorMenuItem());

        ToggleGroup allFontsGroup = new ToggleGroup();

        Font.getFamilies().forEach(fontName -> {
            RadioMenuItem fontItem = new RadioMenuItem(fontName);
            fontItem.setToggleGroup(allFontsGroup);
            getItems().add(fontItem);

        });

        allFontsGroup.selectedToggleProperty().addListener((observable) -> {
            if (null != allFontsGroup.getSelectedToggle()) {
                RadioMenuItem selected = (RadioMenuItem) allFontsGroup.getSelectedToggle();

                RadioMenuItem removeLastFontItem = recentlyFontItems.addAndReturnRemoveLast(selected);
                String removeLastFontName = recentlyFontNames.addAndReturnRemoveLast(selected.getText());

                // 如果没有最近的使用字体被删除
                if (null == removeLastFontName) {

                    RadioMenuItem lateFontItem = new RadioMenuItem(selected.getText());
                    lateFontItem.setToggleGroup(recentlyFontsGroup);

                    getItems().add(1, lateFontItem);

                    lateFontItem.setSelected(true);
                    Bindings.bindBidirectional(selected.selectedProperty(), lateFontItem.selectedProperty());
                } else {
                    // 如果有最近的使用字体被删除，那么要移除组件，以及绑定
                    getItems().remove(removeLastFontItem);
                }

                // 按钮文字显示为所选字体名字
                setText(selected.getText());

                // 选中字体
                selectedFontName.set(selected.getText());
            }
        });
    }
}

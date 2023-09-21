package labcentral.fx;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import labcentral.custom.CustomRadioMenuItem;
import labcentral.custom.LimitedOrderedSet;


public class ComboxText extends Application {
    private final Font defaultFont = Font.getDefault();
    private final double defaultFontSize = defaultFont.getSize();
    private final LimitedOrderedSet<RadioMenuItem> recentlyFontItems = new LimitedOrderedSet<>(10);
    private final LimitedOrderedSet<String> recentlyFontNames = new LimitedOrderedSet<>(10);

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("字体选择");

        // 创建一个文本对象，用于显示选定的字体
        Text text = new Text("示例文本");
        text.setFont(Font.font(defaultFont.getName(), 20)); // 设置默认字体


        // 创建一个字体按钮
        MenuButton fontMenuButton = new MenuButton();
        fontMenuButton.getStyleClass().add("menu-button"); // 添加样式类

        ToggleGroup recentlyFontsGroup = new ToggleGroup();


        MenuItem recentFontItem = new CustomRadioMenuItem("最近使用的字体", defaultFont, defaultFontSize);
        recentFontItem.setDisable(true);
        fontMenuButton.getItems().add(recentFontItem);


        MenuItem blankItem = new CustomRadioMenuItem("所有字体", defaultFont, defaultFontSize);

        blankItem.setDisable(true);
        fontMenuButton.getItems().addAll(new SeparatorMenuItem(), blankItem, new SeparatorMenuItem());

        ToggleGroup allFontsGroup = new ToggleGroup();

        Font.getFamilies().forEach(fontName -> {
            RadioMenuItem fontItem = new CustomRadioMenuItem(fontName, defaultFont, defaultFontSize);
            fontItem.setToggleGroup(allFontsGroup);
            fontMenuButton.getItems().add(fontItem);

            fontItem.setOnAction(event -> {
                if (fontItem.isSelected()) {
                    RadioMenuItem removeLastFontItem = recentlyFontItems.addAndReturnRemoveLast(fontItem);
                    String removeLastFontName = recentlyFontNames.addAndReturnRemoveLast(fontItem.getText());

                    // 如果没有最近的使用字体被删除
                    if (null == removeLastFontName) {

                        RadioMenuItem lateFontItem = new CustomRadioMenuItem(fontItem.getText(), defaultFont, defaultFontSize);
                        lateFontItem.setToggleGroup(recentlyFontsGroup);

                        fontMenuButton.getItems().add(1, lateFontItem);

                        lateFontItem.setSelected(true);
                        Bindings.bindBidirectional(fontItem.selectedProperty(), lateFontItem.selectedProperty());
                    } else {
                        // 如果有最近的使用字体被删除，那么要移除组件，以及绑定
                        fontMenuButton.getItems().remove(removeLastFontItem);

                    }

                    text.setFont(Font.font(fontItem.getText(), 20));
                    fontMenuButton.setText(fontItem.getText());
                }

            });

        });

        // 设置字体选择框的默认选项
        fontMenuButton.setText(defaultFont.getName());


        // 创建一个布局并将文本和字体选择框添加到其中
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(text, fontMenuButton);

        Scene scene = new Scene(vbox, 300, 200);

        // 将CSS文件加载到场景中
        scene.getStylesheets().add(getClass().getResource("/style/menubar.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

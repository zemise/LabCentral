package io.github.labcentral.custom;

import io.github.labcentral.util.FxUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class FontMenuButtonTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        System.out.println(Font.getDefault().getSize());
        Pane root = new VBox();
        HBox hBox = new HBox();


        FontMenuButton fontMenuButton = new FontMenuButton();
        fontMenuButton.setMinWidth(120.0);
        fontMenuButton.setMaxWidth(120.0);

        TextArea textArea = new TextArea("this is a test");

        ColorPicker colorPicker = new ColorPicker();
        FxUtil.applyColorPicker(textArea, colorPicker);


        FontSizeComBox fontSizeComBox = new FontSizeComBox();


        fontSizeComBox.fontSizeProperty().addListener((observable, oldValue, newValue) -> {
            textArea.setFont(new Font(fontMenuButton.fontNameProperty().getName(), newValue));
            System.out.println(newValue);
        });

        fontMenuButton.fontNameProperty().addListener((observable, oldValue, newValue) -> {
            textArea.setFont(new Font(newValue, fontSizeComBox.fontSizeProperty().getValue()));
        });

        hBox.getChildren().addAll(fontMenuButton, colorPicker, fontSizeComBox);
        root.getChildren().addAll(hBox, textArea);

        primaryStage.setTitle("测试---自定义字体选择按钮");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }
}

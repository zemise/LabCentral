package io.github.zemise.menubar.menu;

import io.github.zemise.menubar.MenubarPlugin;

import io.github.zemise.menubar.CustomMenuItem;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.nio.file.Paths;

import static io.github.zemise.util.FxUtil.fxmlStage;


/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/11
 */
public class LabCentralMenu implements MenubarPlugin {

    @Override
    public void setup(Stage stage, MenuBar menuBar) {

        Menu menu = new Menu("LabCentral");

        MenuItem aboutItem = CustomMenuItem.configure("关于 LabCentral", x -> showAboutStage(), KeyCode.L);
        MenuItem settingItem = CustomMenuItem.configure("设置", null, KeyCode.COMMA);
        SeparatorMenuItem firstSeparator = new SeparatorMenuItem();
        MenuItem checkUpdateItem = new MenuItem("检查更新");
        SeparatorMenuItem secondSeparator = new SeparatorMenuItem();
        MenuItem exitItem = CustomMenuItem.configure("退出 LabCentral", x -> Platform.exit(), KeyCode.Q);


        menu.getItems().addAll(aboutItem, settingItem, firstSeparator, checkUpdateItem, secondSeparator, exitItem);
        menuBar.getMenus().add(menu);

        aboutItem.setOnAction(event -> {
            showAboutStage();
        });
    }

    private void showAboutStage() {
        Stage aboutStage = fxmlStage("/scene/about.fxml", "关于 LabCentral", false);
        aboutStage.show();
    }
}

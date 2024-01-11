package io.github.labcentral.view.menubar.mainview;

import de.felixroske.jfxsupport.GUIState;
import io.github.labcentral.view.CheckUpdateView;
import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import io.github.labcentral.Main;
import io.github.labcentral.view.AboutView;
import io.github.labcentral.view.menubar.CustomMenuItem;
import io.github.labcentral.view.menubar.MenubarPlugin;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/11
 */

@Component
public class LabCentralMenu implements MenubarPlugin {
    @Override
    public void setup(Stage stage, MenuBar menuBar) {

        Menu menu = new Menu("LabCentral");

        MenuItem aboutItem = CustomMenuItem.configure("关于 LabCentral", x -> showAboutStage(), KeyCode.L);
        MenuItem settingItem = CustomMenuItem.configure("设置", null, KeyCode.COMMA);
        SeparatorMenuItem firstSeparator = new SeparatorMenuItem();
        MenuItem checkUpdateItem = CustomMenuItem.configure("检查更新", x -> handleUpdate(), null);
        SeparatorMenuItem secondSeparator = new SeparatorMenuItem();

        MenuItem hideWindowsItem = CustomMenuItem.configure("隐藏Lab Central", null, KeyCode.H);
        MenuItem hideOtherWindowItem = new MenuItem("隐藏其他");

        SeparatorMenuItem thirdSeparator = new SeparatorMenuItem();
        MenuItem exitItem = CustomMenuItem.configure("退出 LabCentral", x -> {
            // for faster close window, so first close visible window
            GUIState.getStage().close();
            // when use spring web, if super close(), may produce  AsynchronousCloseException
            Platform.exit();
            System.exit(0);
        }, KeyCode.Q);


        menu.getItems().addAll(aboutItem, settingItem, firstSeparator, checkUpdateItem,
                secondSeparator, hideWindowsItem, hideOtherWindowItem, thirdSeparator, exitItem);
        menuBar.getMenus().add(menu);

        aboutItem.setOnAction(event -> {
            showAboutStage();
        });
    }

    private void handleUpdate() {
        //todo: complete update handling
        Main.showView(CheckUpdateView.class, Modality.NONE);
    }

    private void showAboutStage() {
        Main.showView(AboutView.class, Modality.APPLICATION_MODAL);
    }
}

package labcentral.view.menubar.mainview;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import labcentral.LabCentralMain;
import labcentral.view.AboutView;
import labcentral.view.menubar.CustomMenuItem;
import labcentral.view.menubar.MenubarPlugin;
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
        LabCentralMain.showView(AboutView.class, Modality.WINDOW_MODAL);
    }
}

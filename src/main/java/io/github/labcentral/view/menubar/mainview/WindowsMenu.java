package io.github.labcentral.view.menubar.mainview;


import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import io.github.labcentral.util.SystemUtil;
import io.github.labcentral.view.menubar.MenubarPlugin;

import static io.github.labcentral.view.menubar.CustomMenuItem.configure;


/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/12
 */
public class WindowsMenu implements MenubarPlugin {
    @Override
    public void setup(Stage stage, MenuBar menuBar) {
        Menu windows = new Menu("窗口");
        MenuItem mini = configure("最小化", ea -> stage.setIconified(true), KeyCode.M);
        MenuItem front = configure("前置所有窗口", ea -> SystemUtil.showAllWindows(), null);

        windows.getItems().addAll(mini, front);

        menuBar.getMenus().add(windows);
    }
}

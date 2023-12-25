package io.github.labcentral.view.menubar.mainview;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
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
public class HelpMenu implements MenubarPlugin {
    @Override
    public void setup(Stage stage, MenuBar menuBar) {
        Menu helps = new Menu("Help");
        MenuItem help = configure("帮助", null, null);

        helps.getItems().addAll(help);

        menuBar.getMenus().add(helps);
    }
}

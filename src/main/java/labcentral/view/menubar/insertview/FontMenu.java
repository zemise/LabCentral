package labcentral.view.menubar.insertview;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import labcentral.view.menubar.MenubarPlugin;

import static labcentral.view.menubar.CustomMenuItem.configure;

public class FontMenu implements MenubarPlugin {
    @Override
    public void setup(Stage stage, MenuBar menuBar) {
        Menu font = new Menu("字体");
        for (int i = 1; i <= 100; i++){
            MenuItem test = configure(i +"", null,null);

            font.getItems().add(test);
        }
        menuBar.getMenus().add(font);

    }
}

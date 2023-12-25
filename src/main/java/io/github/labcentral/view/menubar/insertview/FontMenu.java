package io.github.labcentral.view.menubar.insertview;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import io.github.labcentral.view.menubar.MenubarPlugin;

import static io.github.labcentral.view.menubar.CustomMenuItem.configure;

public class FontMenu implements MenubarPlugin {
    @Override
    public void setup(Stage stage, MenuBar menuBar) {
        Menu font = new Menu("字体");

        Font.getFamilies().forEach(fontFamily -> {
            MenuItem item = configure(fontFamily, null, null);
            font.getItems().add(item);
        });

        menuBar.getMenus().add(font);

    }
}

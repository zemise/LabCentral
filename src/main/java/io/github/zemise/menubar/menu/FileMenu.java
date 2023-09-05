package io.github.zemise.menubar.menu;

import io.github.zemise.menubar.MenubarPlugin;
import io.github.zemise.util.FxUtil;
import io.github.zemise.util.SystemUtil;
import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import static io.github.zemise.menubar.CustomMenuItem.configure;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/11
 */
public class FileMenu implements MenubarPlugin {
    @Override
    public void setup(Stage stage, MenuBar menuBar) {
        Menu file = new Menu("文件");
        MenuItem newFile = configure("新建", null, KeyCode.N);
        MenuItem open = configure("打开...", x -> FxUtil.openFileDialog(stage), KeyCode.O);

        file.getItems().addAll(newFile, open);

        menuBar.getMenus().addAll(file);
    }
}

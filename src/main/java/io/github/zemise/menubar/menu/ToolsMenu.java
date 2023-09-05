package io.github.zemise.menubar.menu;

import io.github.zemise.menubar.MenubarPlugin;
import io.github.zemise.menubar.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import static io.github.zemise.util.FxUtil.fxmlStage;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/12
 */
public class ToolsMenu implements MenubarPlugin {
    @Override
    public void setup(Stage stage, MenuBar menuBar) {
        Menu menu = new Menu("工具");

        MenuItem batchDeletion = CustomMenuItem.configure("批量删除", null, null);
        MenuItem textInsert = CustomMenuItem.configure("图片插字", event -> showTextInsertStage(), null);

        menu.getItems().addAll(batchDeletion, textInsert);
        menuBar.getMenus().add(menu);
    }

    private void showTextInsertStage(){
        Stage TextInsertStage = fxmlStage("/scene/tempTextInsert-view.fxml", "WB图片插字", false);
        TextInsertStage.show();
    }
}

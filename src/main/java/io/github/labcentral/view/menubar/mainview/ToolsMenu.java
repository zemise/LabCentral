package io.github.labcentral.view.menubar.mainview;

import io.github.labcentral.view.WordView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import io.github.labcentral.LabCentralMain;
import io.github.labcentral.view.menubar.CustomMenuItem;
import io.github.labcentral.view.menubar.MenubarPlugin;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/12
 */

@Component
public class ToolsMenu implements MenubarPlugin {
    @Override
    public void setup(Stage stage, MenuBar menuBar) {
        Menu menu = new Menu("工具");

        MenuItem wordInsert = CustomMenuItem.configure("图片插字", x -> showWordInsertStage(), null);
        MenuItem batchDeletion = CustomMenuItem.configure("正在开发", null, null);

        menu.getItems().addAll(wordInsert, batchDeletion);
        menuBar.getMenus().add(menu);
    }

    private void showWordInsertStage() {
        LabCentralMain.showView(WordView.class, Modality.WINDOW_MODAL);
    }
}

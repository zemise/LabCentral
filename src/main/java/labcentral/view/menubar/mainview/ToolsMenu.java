package labcentral.view.menubar.mainview;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import labcentral.LabCentralMain;
import labcentral.view.InsertView;
import labcentral.view.menubar.CustomMenuItem;
import labcentral.view.menubar.MenubarPlugin;
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

        MenuItem batchDeletion = CustomMenuItem.configure("批量删除", null, null);
        MenuItem textInsert = CustomMenuItem.configure("图片插字", x -> showTextInsertStage(), null);

        menu.getItems().addAll(batchDeletion, textInsert);
        menuBar.getMenus().add(menu);
    }

    private void showTextInsertStage(){
        LabCentralMain.showView(InsertView.class, Modality.WINDOW_MODAL);
    }
}

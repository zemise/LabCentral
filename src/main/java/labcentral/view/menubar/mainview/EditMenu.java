package labcentral.view.menubar.mainview;


import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import labcentral.view.menubar.MenubarPlugin;

import static labcentral.view.menubar.CustomMenuItem.configure;


/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/11
 */
public class EditMenu implements MenubarPlugin {
    @Override
    public void setup(Stage stage, MenuBar menuBar) {
        Menu edit = new Menu("编辑");
        MenuItem undo = configure("撤销", null, KeyCode.Z);
        MenuItem redo = configure("重做", null, KeyCode.R);
        SeparatorMenuItem editSeparator = new SeparatorMenuItem();
        MenuItem cut = configure("剪切", null, KeyCode.X);
        MenuItem copy = configure("复制", null, KeyCode.C);
        MenuItem paste = configure("粘贴", null, KeyCode.V);

        edit.getItems().addAll(undo, redo, editSeparator, cut, copy, paste);

        menuBar.getMenus().add(edit);
    }
}

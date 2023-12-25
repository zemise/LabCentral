package io.github.labcentral.view.menubar;

import javafx.scene.control.MenuBar;
import javafx.stage.Stage;


/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/11
 */
public interface MenubarPlugin {
    void setup(Stage stage, MenuBar menuBar);
}

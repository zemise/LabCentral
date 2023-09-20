package labcentral.util;

import de.felixroske.jfxsupport.GUIState;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import labcentral.view.menubar.MenubarPlugin;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/10
 */
@Slf4j
public class FxUtil {

    /**
     * get the page where the node is located
     *
     * @param node javafx node
     */
    public static Stage getStage(Node node) {
        return (Stage) node.getScene().getWindow();
    }


    /**
     * Close the page where the node is located
     *
     * @param node javafx node
     */
    public static void closePage(Node node) {
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }


    public static void openFileDialog(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open FileMenu");
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            // todo some logic
        } else {
            // todo some logic
        }
    }

    public static void loadMenubar(MenuBar menuBar, MenubarPlugin[] menubarPlugins) {
        // optimize, if mac
        if (SystemUtil.getSystemType() == SystemUtil.TypeOS.MAC) {
            menuBar.setUseSystemMenuBar(true);
        }

        // load menubar
        for (MenubarPlugin menubarPlugin : menubarPlugins) {
            try {
                menubarPlugin.setup(GUIState.getStage(), menuBar);
            } catch (Exception e) {
                log.warn("failed to load menuBarPlugin: {}", menubarPlugin.getClass().getName(), e);
            }
        }
    }
}

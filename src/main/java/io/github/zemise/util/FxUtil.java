package io.github.zemise.util;

import io.github.zemise.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/10
 */
public class FxUtil {

    /**
     * Close the page where the node is located
     *
     * @param node javafx node
     */
    public static void closePage(Node node) {
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public static Stage fxmlStage(String fxmlURL, String title, boolean resizable) {
        FXMLLoader view = new FXMLLoader(Main.class.getResource(fxmlURL));
        Stage stage = new Stage();
        Scene scene = null;
        try {
            scene = new Scene(view.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setResizable(resizable);
        stage.setTitle(title);
        stage.setScene(scene);
        return stage;
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
}

package io.github.labcentral.custompane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">zemise</a>
 * @Date 2024/1/5
 * @since 1.0
 */
public class Test extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ZFXGenericPane root = new ZFXGenericPane();
        root.header.setStyle("-fx-background-color: grey; -fx-border-radius: 5 5 0 0; -fx-background-radius: 5 5 0 0");
        root.setStyle("-fx-background-radius: 5; -fx-border-radius: 5");

        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();

    }
}

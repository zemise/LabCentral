package io.github.labcentral.fx;

import io.github.labcentral.ResourceLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @Date 2024/1/6
 * @since 1.0
 */
public class DemoPaneTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(ResourceLoader.loadUrl("view/demo.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.getScene().setFill(Color.TRANSPARENT);

        //DropShadow dropShadow = new DropShadow();
        //dropShadow.setOffsetX(4.0);
        //dropShadow.setOffsetY(4.0);
        //dropShadow.setColor(javafx.scene.paint.Color.BLACK);
        //root.setEffect(dropShadow);

        primaryStage.show();
    }
}

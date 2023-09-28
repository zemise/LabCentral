package labcentral.custom;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FontDemo extends Application {
    @Override
    public void start(Stage stage){
        Scene scene = new Scene(new HBox(50,
                new FontControl(FontControl.SkinType.TEXT)
        ), 400, 250);

        scene.getStylesheets().add(getClass().getResource("/style/menubar.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("demo");


        stage.show();
    }
}

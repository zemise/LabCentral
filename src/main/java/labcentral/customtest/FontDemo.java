package labcentral.customtest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FontDemo extends Application {
    @Override
    public void start(Stage stage) {
        FontControl fontControl = new FontControl(FontControl.SkinType.TEXT);
        Scene scene = new Scene(new HBox(50, fontControl), 400, 250);
        fontControl.fontProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue.getName());
        });
        fontControl.fontProp.set(new Font("Arial", 20));
        fontControl.fontProp.set(new Font("Arial", 80));

        scene.getStylesheets().add(getClass().getResource("/style/menubar.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("demo");

        stage.show();
    }
}

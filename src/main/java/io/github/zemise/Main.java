package io.github.zemise;

import io.github.zemise.event.EventBus;
import io.github.zemise.event.listener.onUserLogin;
import io.github.zemise.menubar.MenubarPlugin;
import io.github.zemise.menubar.menu.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/11
 */
public class Main extends Application {
    public static Stage primaryStage;
    public static EventBus eventBus = new EventBus();

    // menubar
    private final MenubarPlugin[] menubarPlugins = {new LabCentralMenu(), new FileMenu(), new EditMenu(),
            new ToolsMenu(), new WindowsMenu(), new HelpMenu()};

    @Override
    public void init() throws Exception {
        // event register
        eventBus.register(new onUserLogin());
        loadFonds();
    }

    @Override
    public void start(Stage stage) throws IOException {
        //Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        FXMLLoader rootLoader = new FXMLLoader(Main.class.getResource("/scene/newMain.fxml"));
        Parent rootPane = rootLoader.load();

        Scene scene = new Scene(rootPane);

        stage.setScene(scene);

        stage.getIcons().add(new Image(Paths.get("image", "lab-logo.png").toString()));
        stage.setTitle("中心实验室-库存管理");
        primaryStage = stage;

        // put window to front to avoid it to be hided behind by others.
        stage.show();
        stage.setAlwaysOnTop(true);
        stage.requestFocus();
        stage.toFront();
        stage.setAlwaysOnTop(false);
    }

    private void loadFonds() {
        List<String> fonts = List.of("Poppins-Bold.ttf", "Poppins-Medium.ttf", "Poppins-Regular.ttf", "Poppins-SemiBold.ttf");
        for (String font : fonts) {
            Font.loadFont(Main.class.getResourceAsStream("/font/" + font), 52);
        }
    }
}

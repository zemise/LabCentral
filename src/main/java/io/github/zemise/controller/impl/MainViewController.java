package io.github.zemise.controller.impl;

import io.github.zemise.Main;
import io.github.zemise.controller.Controller;
import io.github.zemise.menubar.MenubarPlugin;
import io.github.zemise.menubar.menu.*;
import io.github.zemise.util.FxUtil;
import io.github.zemise.util.SystemUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/11
 */
public class MainViewController extends Controller implements Initializable {
    private final MenubarPlugin[] menubarPlugins = {new LabCentralMenu(), new FileMenu(), new EditMenu(),
            new ToolsMenu(), new WindowsMenu(), new HelpMenu()};
    @FXML
    public MenuBar menuBar;
    @FXML
    public ImageView userImage;
    @FXML
    public Button userBtn;
    @FXML
    private StackPane root;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadMenubar();
        userImage.setImage(new Image(Paths.get("image", "user_not_login.png").toString()));
        userBtn.setText("登陆");
        userBtn.setOnAction(event -> {
            Stage loginStage = FxUtil.fxmlStage("/scene/login-view.fxml", "登陆", false);
            loginStage.show();
        });
    }

    private void loadMenubar() {
        // optimize, if mac
        if (SystemUtil.getSystemType() == SystemUtil.TypeOS.MAC) {
            menuBar.setUseSystemMenuBar(true);
        }
        // load menubar
        for (MenubarPlugin menubarPlugin : menubarPlugins) {
            try {
                menubarPlugin.setup(Main.primaryStage, menuBar);
            } catch (Exception e) {
                System.err.println("failed to load menuBarPlugin");
                System.err.println(menubarPlugin.getClass().getName());
                e.printStackTrace();
            }
        }
    }
}

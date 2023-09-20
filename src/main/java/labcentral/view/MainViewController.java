package labcentral.view;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import labcentral.LabCentralMain;
import labcentral.event.LoginEvent;
import labcentral.util.FxUtil;
import labcentral.view.menubar.MenubarPlugin;
import labcentral.view.menubar.mainview.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

@Slf4j
@FXMLController
public class MainViewController implements Initializable {
    @FXML
    public Button userLoginButton;
    @FXML
    public MenuBar menuBar;
    @FXML
    public ImageView userImage;

    private final MenubarPlugin[] menubarPlugins = {new LabCentralMenu(), new FileMenu(), new EditMenu(),
            new ToolsMenu(), new WindowsMenu(), new HelpMenu()};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FxUtil.loadMenubar(menuBar, menubarPlugins);

        userImage.setImage(new Image(Paths.get("labcentral/image", "user_not_login.png").toString()));

        userLoginButton.setOnAction((ActionEvent event) -> {
            LabCentralMain.showView(LoginView.class, Modality.WINDOW_MODAL);
        });
    }

    @EventListener
    public void handleLoginEvent(LoginEvent event) {
        if (event.isLoggedIn()) {
            userImage.setImage(new Image(Paths.get("labcentral/image", "app-icon.png").toString()));
        }
    }


}

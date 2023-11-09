package labcentral.view;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import labcentral.LabCentralMain;
import labcentral.domain.custom.domain.PrimaryAntibody;
import labcentral.domain.custom.service.MyService;
import labcentral.event.LoginEvent;
import labcentral.util.FxUtil;
import labcentral.view.menubar.MenubarPlugin;
import labcentral.view.menubar.mainview.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;

import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

@Slf4j
@FXMLController
public class MainViewController implements Initializable {
    @FXML
    private WebView webview;
    @FXML
    private Button userLoginButton;
    @FXML
    private MenuBar menuBar;
    @FXML
    private ImageView userImage;
    @FXML
    private TextField mainSearchFiled;
    @Autowired
    private MyService service;

    private final MenubarPlugin[] menubarPlugins = {new LabCentralMenu(), new FileMenu(), new EditMenu(),
            new ToolsMenu(), new WindowsMenu(), new HelpMenu()};


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FxUtil.loadMenubar(menuBar, menubarPlugins);

        userImage.setImage(new Image(getClass().getResource("/images/user_not_login.png").toExternalForm()));

        userLoginButton.setOnAction((ActionEvent event) -> {
            LabCentralMain.showView(LoginView.class, Modality.WINDOW_MODAL);
        });

        // 开启查询
        mainSearchFiled.setOnKeyPressed(event -> {
            String searchWord = mainSearchFiled.getText();
            if(searchWord.isEmpty()) return;

            if (event.getCode() == KeyCode.ENTER){
                log.info("Start search: " + mainSearchFiled.getText());
                PrimaryAntibody result = service.findByAntiBodyName(searchWord);
                if (null == result){
                    log.info(searchWord + " is not found");
                }else {
                    log.info(searchWord + " 分子量为" + result.getMolecularWeight());
                    webview.getEngine().load(result.getWebsite());
                }
            }
        });

    }

    @EventListener
    public void handleLoginEvent(LoginEvent event) {
        if (event.isLoggedIn()) {
            userImage.setImage(new Image(getClass().getResource("/images/app-icon.png").toExternalForm()));
        }
    }


}

package io.github.labcentral.view;

import de.felixroske.jfxsupport.FXMLController;
import io.github.labcentral.view.menubar.MenubarPlugin;
import io.github.labcentral.view.menubar.mainview.*;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import io.github.labcentral.LabCentralMain;
import io.github.labcentral.domain.custom.domain.PrimaryAntibody;
import io.github.labcentral.domain.custom.service.MyService;
import io.github.labcentral.event.LoginEvent;
import io.github.labcentral.util.FxUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Zemise
 */
@Slf4j
@FXMLController
public class MainViewController implements Initializable {
    @FXML
    private WebView webview;
    @FXML
    private MFXButton userLoginButton;
    @FXML
    private MenuBar menuBar;
    @FXML
    private TextField mainSearchFiled;
    @Autowired
    private MyService service;

    private final MenubarPlugin[] menubarPlugins = {new LabCentralMenu(), new FileMenu(), new EditMenu(),
            new ToolsMenu(), new WindowsMenu(), new HelpMenu()};


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FxUtil.loadMenubar(menuBar, menubarPlugins);

        //userImage.setImage(new Image(getClass().getResource("/images/user_not_login.png").toExternalForm()));

        userLoginButton.setOnAction((ActionEvent event) -> {
            // test notification
            if (SystemTray.isSupported()) {
                SystemTray tray = SystemTray.getSystemTray();

                java.awt.Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
                TrayIcon trayIcon = new TrayIcon(image, "My Mac App");
                trayIcon.setImageAutoSize(true);
                try {
                    tray.add(trayIcon);
                    trayIcon.displayMessage("Message", "Hello, Mac!", TrayIcon.MessageType.INFO);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            LabCentralMain.showView(LoginView.class, Modality.WINDOW_MODAL);

            // 开启查询
            mainSearchFiled.setOnKeyPressed(e -> {
                String searchWord = mainSearchFiled.getText();
                if (searchWord.isEmpty()) {
                    return;
                }

                if (e.getCode() == KeyCode.ENTER) {
                    log.info("Start search: " + mainSearchFiled.getText());
                    PrimaryAntibody result = service.findByAntiBodyName(searchWord);
                    if (null == result) {
                        log.info(searchWord + " is not found");
                    } else {
                        log.info(searchWord + " 分子量为" + result.getMolecularWeight());
                        webview.getEngine().load(result.getWebsite());
                    }
                }
            });

        });
    }

    @EventListener
    public void handleLoginEvent(LoginEvent event) {
        if (event.isLoggedIn()) {
            //userImage.setImage(new Image(getClass().getResource("/images/app-icon.png").toExternalForm()));
        }
    }
}

package labcentral.view;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import labcentral.service.ALabService;
import labcentral.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

@FXMLController
public class LoginViewController implements Initializable {
    @FXML
    public ImageView imageView;
    @FXML
    public TextField accountField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button loginButton;

    private final ALabService actionService;

    @Autowired
    public LoginViewController(LoginService actionService) {
        this.actionService = actionService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageView.setImage(new Image(Paths.get("labcentral/image", "app-icon.png").toString()));
        loginButton.setDisable(true);

        // account and password not blank
        accountField.textProperty().addListener((observable, oldValue, newValue) -> updateNodeStatus(accountField, passwordField, loginButton));

        passwordField.textProperty().addListener((observable, oldValue, newValue) -> updateNodeStatus(accountField, passwordField, loginButton));

        loginButton.setOnAction(event -> actionService.processName("logged"));
    }



    // 更新按钮的可用性
    private void updateNodeStatus(TextField textField1, TextField textField2, Node updateNode) {
        // 否则按钮不可用
        updateNode.setDisable(textField1.getText().isEmpty() || textField2.getText().isEmpty());
    }
}

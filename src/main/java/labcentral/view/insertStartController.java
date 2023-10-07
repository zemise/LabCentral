package labcentral.view;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import labcentral.custom.FontMenuButton;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class insertStartController implements Initializable {
    private final InsertController insertController;

    @Autowired
    public insertStartController(InsertController insertController) {
        this.insertController = insertController;
    }

    @FXML
    public HBox hBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FontMenuButton fontMenuButton = new FontMenuButton();
        hBox.getChildren().add(0,fontMenuButton);

        // 监听字体选择，实时改变输入框字体
        fontMenuButton.fontProperty().addListener((observable, oldValue, newValue) ->
                insertController.textArea.setFont(newValue));
        }
}

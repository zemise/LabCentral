package labcentral.view;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import labcentral.custom.FontSizeComBox;
import labcentral.custom.FontMenuButton;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class InsertStartController implements Initializable {
    private final InsertController insertController;
    @Autowired
    public InsertStartController(InsertController insertController) {
        this.insertController = insertController;
    }
    @FXML
    public HBox hBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FontMenuButton fontMenuButton = new FontMenuButton();
        fontMenuButton.setPrefWidth(100);
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setPrefWidth(100);
        FontSizeComBox fontSizeComBox = new FontSizeComBox();
        fontSizeComBox.setPrefWidth(75);

        hBox.getChildren().add(0, fontMenuButton);
        hBox.getChildren().add(1, colorPicker);
        hBox.getChildren().add(2, fontSizeComBox);


        // 监听字体选择，实时改变输入框----字体类型
        fontMenuButton.fontNameProperty().addListener((observable, oldValue, newValue) ->
                insertController.textArea.setFont(new Font(newValue, fontSizeComBox.fontSizeProperty().getValue())));

        // 监听字体选择，实时改变输入框----字体大小
        fontSizeComBox.fontSizeProperty().addListener((observable, oldValue, newValue) ->
                insertController.textArea.setFont(new Font(fontMenuButton.fontNameProperty().getValue(), newValue)));
    }
}

package io.github.labcentral.view;

import de.felixroske.jfxsupport.FXMLController;
import io.github.labcentral.util.FxUtil;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">zemise</a>
 * @Date 2024/1/5
 * @since 1.0
 */
@FXMLController
public class CheckUpdateViewController implements Initializable {
    @FXML
    public ImageView icon;
    @FXML
    public AnchorPane root;
    public MFXButton cancelButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        icon.setImage(new Image(getClass().getResource("/images/app-icon.png").toExternalForm()));

        cancelButton.setOnAction(event -> {
            Platform.runLater(() -> {
                FxUtil.closePage(root);
            });
        });

    }


    @FXML
    public void onCancel(MouseEvent mouseEvent) {
        //Platform.runLater(() -> {
        //    FxUtil.closePage(root);
        //});
        //
        //GUIState.getStage().close();
    }
}

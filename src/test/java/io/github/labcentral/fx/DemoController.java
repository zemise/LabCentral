package io.github.labcentral.fx;

import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @Date 2024/1/6
 * @since 1.0
 */
public class DemoController implements Initializable {
    @FXML
    public HBox windowHeader;
    @FXML
    public AnchorPane rootPane;
    @FXML
    public MFXFontIcon closeIcon;
    @FXML
    public MFXFontIcon minimizeIcon;
    @FXML
    public MFXFontIcon maximizeIcon;

    private double xOffset;
    private double yOffset;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeIcon.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                this.closeStage();
            }
        });

        maximizeIcon.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                this.maximizeStage();
            }
        });

        minimizeIcon.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                this.minimizeStage();
            }
        });
    }


    @FXML
    public void onDraged(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            handleMoveStage(event);
        }

    }

    @FXML
    public void onPressed(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            Stage stage = (Stage) rootPane.getScene().getWindow();
            xOffset = stage.getX() - event.getScreenX();
            yOffset = stage.getY() - event.getScreenY();
        }
    }

    @FXML
    public void onClicked(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {

            minimizeStage();
        }
    }

    private void closeStage() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }


    private void minimizeStage() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setIconified(true);
    }

    private void maximizeStage() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setMaximized(true);

        System.out.println("最大化");
    }


    private void handleMoveStage(MouseEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setX(event.getScreenX() + xOffset);
        stage.setY(event.getScreenY() + yOffset);
    }
}

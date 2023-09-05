package io.github.zemise.controller.impl;

import io.github.zemise.Main;
import io.github.zemise.event.EventBus;
import io.github.zemise.event.entity.UserLoginEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/24
 */
public class LoginController implements Initializable {
    @FXML
    public ImageView imageView;
    @FXML
    public Button loginBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageView.setImage(new Image(Paths.get("image", "lab-logo.png").toString()));

        loginBtn.setOnAction(event -> {
            Main.eventBus.post(new UserLoginEvent("用户点击了登陆按钮，并成功登陆"));
        });
    }
}

package io.github.zemise.view.config;

import cn.hutool.core.date.DateUtil;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.MvvmFX;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import static atlantafx.base.theme.Styles.ACCENT;
import static atlantafx.base.theme.Styles.BUTTON_CIRCLE;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/20
 */
public class UserInfoView implements FxmlView<UserInfoViewModel>, Initializable {
    @InjectViewModel
    private UserInfoViewModel userInfoViewModel;

    @FXML
    public VBox root;
    @FXML
    public Button iconBut;
    @FXML
    public Label userName;
    @FXML
    public Label email;
    @FXML
    public Label dept;
    @FXML
    public Label roles;
    @FXML
    public Label createDate;
    @FXML
    public Button loginOutBut;
    @FXML
    public Label phoneNumber;
    @FXML
    public Label phoneNumberStr;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userName.textProperty().bind(userInfoViewModel.userNameProperty());
        phoneNumber.textProperty().bind(userInfoViewModel.phoneNumberProperty());
        email.textProperty().bind(userInfoViewModel.emailProperty());
        roles.textProperty().bind(userInfoViewModel.rolesProperty());

        dept.textProperty().bind(Bindings.createStringBinding(
                () -> DateUtil.format((Date) userInfoViewModel.createTimeProperty().getValue(),
                        "yyyy-MM-dd HH:mm:ss"), userInfoViewModel.createTimeProperty()
        ));

        iconBut.getStylesheets().addAll(BUTTON_CIRCLE, ACCENT);
        iconBut.setId("userBut");
        iconBut.setShape(new Circle(120));
        loginOutBut.setOnAction(event -> {
            MvvmFX.getNotificationCenter().publish("showLoginRegister", "显示登录界面");
        });

    }
}

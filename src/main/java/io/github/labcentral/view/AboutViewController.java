package io.github.labcentral.view;

import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.GUIState;
import io.github.labcentral.util.FxUtil;
import io.github.labcentral.util.SystemUtil;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class AboutViewController implements Initializable {

    public Hyperlink hyperlinkWebsite;
    public ImageView imageView;
    public Button btnCopyClose;
    public Button btnClose;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageView.setImage(new Image(getClass().getResource("/images/app-icon.png").toExternalForm()));


        hyperlinkWebsite.setOnAction(event -> {
            SystemUtil.browserUrl("https://github.com/zemise");
            // 第二种写法
            //HostServices hostServices = getHostServices();
            //hostServices.showDocument("https://github.com/zemise");

        });

        btnClose.setOnAction(event -> {
            FxUtil.closePage(btnClose);
        });

        btnCopyClose.setOnAction(event -> {
            SystemUtil.copyToClipboard("todo ==复制about文本内容+运行环境等 至剪贴板==");
            FxUtil.closePage(btnCopyClose);
        });

        btnClose.requestFocus();
    }
}

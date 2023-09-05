package io.github.zemise.controller.impl;

import io.github.zemise.controller.Controller;
import io.github.zemise.util.FxUtil;
import io.github.zemise.util.SystemUtil;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/11
 */
public class AboutViewController extends Controller implements Initializable {
    public Hyperlink hyperlinkWebsite;
    public Button btnCopyClose;
    public Button btnClose;
    public ImageView imageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageView.setImage(new Image("/image/lab-logo.png"));

        hyperlinkWebsite.setOnAction(event -> {
            SystemUtil.browserUrl(hyperlinkWebsite.getText());
        });

        btnClose.setOnAction(event -> {
            FxUtil.closePage(btnClose);
        });

        btnCopyClose.setOnAction(event -> {
            // todo 复制about文本内容至剪贴板
            SystemUtil.copyToClipboard("==复制about文本内容至剪贴板==");
            FxUtil.closePage(btnCopyClose);
        });

        btnClose.requestFocus();
    }
}

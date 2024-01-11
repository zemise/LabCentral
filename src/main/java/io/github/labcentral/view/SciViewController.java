package io.github.labcentral.view;

import de.felixroske.jfxsupport.FXMLController;
import io.github.labcentral.ResourceLoader;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @Date 2024/1/11
 * @since 1.0
 */

@FXMLController
public class SciViewController implements Initializable {
    public WebView web;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WebEngine webEngine = web.getEngine();
        webEngine.load(ResourceLoader.load("media/sci.html"));
        web.setDisable(true);

    }
}

package io.github.labcentral.view;

import de.felixroske.jfxsupport.SplashScreen;
import io.github.labcentral.ResourceLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * <p>
 * Custom SplashScreen
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @Date 2023/12/16
 * @since 1.0
 */

public class SplashView extends SplashScreen {
    @Override
    public Parent getParent() {
        try {
            return FXMLLoader.load(ResourceLoader.loadUrl("view/splash.fxml"));
        } catch (Exception e) {
            return new SplashScreen().getParent();
        }
    }
}

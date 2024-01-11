package io.github.labcentral;

import de.felixroske.jfxsupport.GUIState;
import io.github.palexdev.materialfx.theming.*;
import io.github.labcentral.view.NewMainView;
import io.github.labcentral.view.SplashView;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Main extends AbstractJavaFx {
    public static void main(String[] args) {

        UserAgentBuilder.builder()
                .themes(JavaFXThemes.MODENA)
                .themes(MaterialFXStylesheets.forAssemble(true))
                .setDeploy(true)
                .setResolveAssets(true)
                .build()
                .setGlobal();

        SplashView customSplashScreen = new SplashView();
        launch(Main.class, NewMainView.class, customSplashScreen, args);
    }
}

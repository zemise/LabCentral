package io.github.labcentral;

import io.github.labcentral.view.JavaFXThemes;
import io.github.labcentral.view.SplashView;
import io.github.labcentral.view.WordView;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LabCentralMain extends AbstractJavaFx {
    public static void main(String[] args) {

        //UserAgentBuilder.builder()
        //        .themes(JavaFXThemes.MODENA)
        //        .themes(MaterialFXStylesheets.forAssemble(true))
        //        .setDeploy(true)
        //        .setResolveAssets(true)
        //        .build()
        //        .setGlobal();


        SplashView customSplashScreen = new SplashView();
        launch(LabCentralMain.class, WordView.class, customSplashScreen, args);
    }
}

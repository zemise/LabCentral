package labcentral;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import labcentral.view.MainView;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LabCentralMain extends AbstractJavaFxApplicationSupport{
    public static void main(String[] args) {
        launch(LabCentralMain.class, MainView.class, args);
    }
}

package io.github.labcentral.fx;

import de.felixroske.jfxsupport.GUIState;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.enums.ScrimPriority;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">zemise</a>
 * @Date 2024/1/5
 * @since 1.0
 */
public class MFXStageDialogTest extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane root = new AnchorPane();


        //MFXStageDialog dialog = MFXGenericDialogBuilder.build(dialogContent)
        //        .toStageDialogBuilder()
        //        .initOwner(GUIState.getStage())
        //        .initModality(Modality.APPLICATION_MODAL)
        //        .setDraggable(true)
        //        .setTitle("Dialogs Preview")
        //        .setOwnerNode(root)
        //        .setScrimPriority(ScrimPriority.WINDOW)
        //        .setScrimOwner(true)
        //        .get();
    }
}

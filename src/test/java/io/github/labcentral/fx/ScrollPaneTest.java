package io.github.labcentral.fx;

import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import io.github.palexdev.materialfx.utils.ColorUtils;
import io.github.palexdev.materialfx.utils.ScrollUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import io.github.palexdev.materialfx.theming.*;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @Date 2024/1/5
 * @since 1.0
 */
public class ScrollPaneTest extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        UserAgentBuilder.builder()
                .themes(JavaFXThemes.MODENA)
                .themes(MaterialFXStylesheets.forAssemble(true))
                .setDeploy(true)
                .setResolveAssets(true)
                .build()
                .setGlobal();

        AnchorPane root = new AnchorPane();

        String demoTitle = """
					Lorem Ipsum is simply dummy text of the printing and typesetting industry.
					Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.
					It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.
					It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
					""";

        Label l1 = new Label(demoTitle);
        l1.setWrapText(true);
        l1.setMaxSize(280, Double.MAX_VALUE);

        TextArea textArea = new TextArea();
        //textArea.setWrapText(true);

        MFXScrollPane mfxScrollPane = new MFXScrollPane();
        mfxScrollPane.setMaxWidth(300);
        mfxScrollPane.setContent(l1);
        Color randomColor = ColorUtils.getRandomColor();
        mfxScrollPane.setThumbColor(randomColor);
        ScrollUtils.addSmoothScrolling(mfxScrollPane);






        root.getChildren().addAll(mfxScrollPane);

        stage.setScene(new Scene(root, 300, 150));
        stage.show();
    }
}

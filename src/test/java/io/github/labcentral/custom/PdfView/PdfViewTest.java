package io.github.labcentral.custom.PdfView;

import io.github.labcentral.ResourceLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @Date 2024/1/7
 * @since 1.0
 */
public class PdfViewTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        PdfView pdfView = new PdfView(new File(ResourceLoader.loadUrl("media/testPDF.pdf").getPath()));


        AnchorPane root = new AnchorPane(pdfView);

        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show();


    }
}

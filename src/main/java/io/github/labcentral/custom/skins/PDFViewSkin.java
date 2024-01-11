package io.github.labcentral.custom.skins;

import io.github.labcentral.custom.PDFView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SkinBase;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @Date 2024/1/7
 * @since 1.0
 */
public class PDFViewSkin extends SkinBase<PDFView> {
    // Access to PDF document must be single threaded (see Apache PdfBox website FAQs)
    private final Executor EXECUTOR = Executors.newSingleThreadExecutor(r -> {
        Thread thread = new Thread(r, PDFView.class.getSimpleName() + " Thread");
        thread.setDaemon(true);
        return thread;
    });

    private final ObservableList<Integer> pdfFilePages = FXCollections.observableArrayList();

    private final Map<Integer, Image> imageCache = new HashMap<>();


    /**
     * Constructor for all SkinBase instances.
     *
     * @param view The control for which this Skin should attach to.
     */
    public PDFViewSkin(PDFView view) {
        super(view);


        ScrollPane mainArea = new ScrollPane();
        VBox.setVgrow(mainArea, Priority.ALWAYS);

        VBox centerArea = new VBox(mainArea);
        centerArea.getStyleClass().add("main-area");
        centerArea.setFillWidth(true);

        // just raw use BorderPane to set this main view.
        // TODO need to advance changes
        BorderPane borderPane = new BorderPane();

        borderPane.setLeft(centerArea);
        borderPane.setTop(new Button("this is a testTop"));
        borderPane.setFocusTraversable(false);

        getChildren().add(borderPane);


        class MainAreaScrollPane extends ScrollPane {


            PDFView pdfView = getSkinnable();
        }
    }
}

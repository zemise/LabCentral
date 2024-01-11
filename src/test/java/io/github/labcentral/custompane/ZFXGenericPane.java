package io.github.labcentral.custompane;

import io.github.palexdev.materialfx.beans.properties.EventHandlerProperty;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.theming.base.Theme;
import io.github.palexdev.materialfx.utils.NodeUtils;
import javafx.beans.property.*;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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

public class ZFXGenericPane extends AbstractZFXPane {
    protected HBox header;

    protected MFXIconWrapper closeIcon;
    protected MFXIconWrapper minimizeIcon;
    protected MFXIconWrapper maximizeIcon;
    protected Label headerLabel;

    //protected double radius;
    //
    //protected Color headerColor;
    //
    //private  final  DoubleProperty radiusSize;
    //
    //private  final  SimpleObjectProperty<Color> headColor;


    private final ObjectProperty<Node> headerIcon;

    private final StringProperty headerText;

    private final BooleanProperty showClose;

    private final BooleanProperty showMinimize;

    private final BooleanProperty showMaximize;

    private final EventHandlerProperty<MouseEvent> onClose;

    private final EventHandlerProperty<MouseEvent> onMinimize;

    private final EventHandlerProperty<MouseEvent> onMaximize;



    public ZFXGenericPane() {
        this("");
    }

    public ZFXGenericPane(String headText) {
        headerIcon = new SimpleObjectProperty<>();
        headerText = new SimpleStringProperty();

        showClose = new SimpleBooleanProperty(true);
        showMinimize = new SimpleBooleanProperty(true);
        showMaximize = new SimpleBooleanProperty(true);

        //radiusSize = new SimpleDoubleProperty(0.0);
        //headColor = new SimpleObjectProperty<>(Color.TRANSPARENT);

        onClose = new EventHandlerProperty();
        onMinimize = new EventHandlerProperty();
        onMaximize = new EventHandlerProperty();

        setHeaderText(headText);

        buildHeader();
        setTop(header);

    }

    private void setHeaderText(String headText) {
    }

    @Override
    public Theme getTheme() {
        return ZemiseFXStylesheets.PANE;
    }

    protected void buildHeader() {
        headerLabel = new Label();
        headerLabel.graphicProperty().bind(this.headerIcon);
        headerLabel.textProperty().bind(this.headerTextProperty());
        headerLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        HBox.setHgrow(this.headerLabel, Priority.ALWAYS);
        closeIcon = new MFXIconWrapper("fas-xmark", 12.0, 24.0);
        minimizeIcon = new MFXIconWrapper("fas-minus", 12.0, 24.0);
        maximizeIcon = new MFXIconWrapper("fas-caret-up", 12.0, 24.0);

        closeIcon.setId("close");
        minimizeIcon.setId("minimize");
        maximizeIcon.setId("maximize");

        closeIcon.visibleProperty().bind(this.showCloseProperty());
        minimizeIcon.visibleProperty().bind(this.showMinimizeProperty());
        maximizeIcon.visibleProperty().bind(this.showMaximizeProperty());

        NodeUtils.makeRegionCircular(this.closeIcon);
        NodeUtils.makeRegionCircular(this.minimizeIcon);
        NodeUtils.makeRegionCircular(this.maximizeIcon);
        header = new HBox(10.0, new Node[]{this.headerLabel});
        header.setAlignment(Pos.CENTER_LEFT);
        initHeader();

    }

    private void initHeader() {
        header.getChildren().removeAll(new Node[]{closeIcon, minimizeIcon, maximizeIcon});


        if (this.isShowClose()) {
            this.header.getChildren().add(this.closeIcon);
        }

        if (this.isShowMinimize()) {
            this.header.getChildren().add(this.minimizeIcon);
        }

        if (this.isShowMaximize()) {
            this.header.getChildren().add(this.maximizeIcon);
        }

        closeIcon.setOnMouseClicked(event -> {
            ((Stage) this.getScene().getWindow()).close();
        });
    }


    public StringProperty headerTextProperty() {
        return this.headerText;
    }

    public void setHeaderTextProperty(String headerText) {
        this.headerText.set(headerText);
    }

    public boolean isShowClose() {
        return this.showClose.get();
    }

    public BooleanProperty showCloseProperty() {
        return this.showClose;
    }

    public void setShowCloseProperty(boolean showClose) {
        this.showClose.set(showClose);
    }

    public boolean isShowMinimize() {
        return this.showMinimize.get();
    }

    public BooleanProperty showMinimizeProperty() {
        return this.showMinimize;
    }

    public void setShowMinimizeProperty(boolean showMinimize) {
        this.showMinimize.set(showMinimize);
    }

    public boolean isShowMaximize() {
        return this.showMaximize.get();
    }

    public BooleanProperty showMaximizeProperty() {
        return this.showMaximize;
    }

    public void setShowMaximizeProperty(boolean showMaximize) {
        this.showMaximize.set(showMaximize);
    }
}

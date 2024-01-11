package io.github.labcentral.custompane;

import io.github.palexdev.materialfx.controls.base.Themable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">zemise</a>
 * @Date 2024/1/5
 * @since 1.0
 */
public abstract class AbstractZFXPane extends BorderPane implements Themable {
    public AbstractZFXPane() {
        this.initialize();
    }

    private void initialize() {
        String STYLE_CLASS = "zfx-pane";
        getStyleClass().add(STYLE_CLASS);
        setMinSize(600.0, 400.0);
    }

    public Parent toParent() {
        return this;
    }

    protected double computeMaxWidth(double height) {
        return this.computePrefWidth(height);
    }

    protected double computeMaxHeight(double width) {
        return this.computePrefHeight(width);
    }
}

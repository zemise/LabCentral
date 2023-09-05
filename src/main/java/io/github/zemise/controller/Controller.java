package io.github.zemise.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/11
 */
public class Controller {
    @FXML
    private StackPane root;
    @FXML
    private BorderPane layout;
    @FXML
    private Pane content;

    protected void configure() { /* Child classes can override it to do their own configuration */}

}

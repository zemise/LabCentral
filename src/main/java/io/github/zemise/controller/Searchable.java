package io.github.zemise.controller;

import javafx.scene.layout.Pane;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/11
 */
public interface Searchable {
    Integer NON_REMOVABLE_INDEX = 0;

    default void clear(Pane content) {
        content.getChildren().removeIf(
                child -> content.getChildren().indexOf(child) != NON_REMOVABLE_INDEX
        );
    }

    void find(String text);
}

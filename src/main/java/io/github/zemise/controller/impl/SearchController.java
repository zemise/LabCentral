package io.github.zemise.controller.impl;

import io.github.zemise.controller.Searchable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/11
 */

public class SearchController implements Searchable {
    public TextField searchField;

    @Override
    public void find(String text) {
        // TODO 数据库查找等等
    }

    @FXML
    public void onSearch(ActionEvent event) {
    }
}

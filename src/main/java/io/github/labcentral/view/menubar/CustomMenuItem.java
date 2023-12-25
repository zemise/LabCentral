package io.github.labcentral.view.menubar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;


/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/11
 */
public class CustomMenuItem {
    public static MenuItem configure(String name, EventHandler<ActionEvent> action, KeyCode keyCode) {
        MenuItem menuItem = new MenuItem(name);
        menuItem.setOnAction(action);

        if (keyCode != null) {
            menuItem.setAccelerator(new KeyCodeCombination(keyCode, KeyCombination.SHORTCUT_DOWN));
        }
        return menuItem;
    }
}

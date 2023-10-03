package labcentral.customtest;

import javafx.scene.Node;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Skin;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import labcentral.customtest.FontControl;

public class FontSkinText implements Skin<FontControl> {
    private final FontControl control;
    private final Pane root;

    public FontSkinText(FontControl control) {
        this.control = control;
        MenuButton menuButton = new MenuButton();
        menuButton.setText(control.fontProperty().get().getName());
        menuButton.getStyleClass().add("menu-button");

        ToggleGroup recentlyFontsGroup = new ToggleGroup();

        root = new StackPane(menuButton);

    }

    @Override
    public FontControl getSkinnable() {
        return control;
    }

    @Override
    public Node getNode() {
        return root;
    }

    @Override
    public void dispose() {

    }
}

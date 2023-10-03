package labcentral.customtest;

import javafx.scene.Node;
import javafx.scene.control.Skin;
import javafx.scene.layout.StackPane;
import labcentral.customtest.FontControl;

public class FontSkinHands implements Skin<FontControl> {
    private final FontControl control;

    private final StackPane root;

    public FontSkinHands(FontControl control) {
        this.control = control;

        root = new StackPane();
    }

    @Override
    public FontControl getSkinnable() {
        return null;
    }

    @Override
    public Node getNode() {
        return null;
    }

    @Override
    public void dispose() {

    }
}

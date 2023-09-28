package labcentral.custom;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.text.Font;

/**
 * @author zemise
 */
// making a proper font control we can base it on JavaFX control class
public class FontControl extends Control {

    // enum to select between define skin types
    public enum SkinType {HANDS, TEXT}

    private final SkinType skinType;

    // implement a method from Skinnable interface
    @Override
    protected Skin<?> createDefaultSkin() {
        if (skinType == SkinType.HANDS)
            return new FontSkinHands(this);
        else
            return new FontSkinText(this);
    }

    // this is our model data - font
    final ObjectProperty<Font> fontProp = new SimpleObjectProperty<>(Font.getDefault());

    public ObjectProperty<Font> fontProperty() {
        return fontProp;
    }

    public FontControl(SkinType skinType) {
        this.skinType = skinType;
    }
}

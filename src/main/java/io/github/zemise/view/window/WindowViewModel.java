package io.github.zemise.view.window;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/20
 */
public class WindowViewModel implements ViewModel {
    private BooleanProperty mainViewVisble = new SimpleBooleanProperty();

    public boolean isMainViewVisble() {
        return mainViewVisble.get();
    }

    public BooleanProperty mainViewVisbleProperty() {
        return mainViewVisble;
    }
}

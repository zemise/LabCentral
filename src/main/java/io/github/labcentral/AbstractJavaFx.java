package io.github.labcentral;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import de.felixroske.jfxsupport.GUIState;
import javafx.application.Platform;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">zemise</a>
 * @Date 2023/12/19
 * @since 1.0
 */
public abstract class AbstractJavaFx extends AbstractJavaFxApplicationSupport {
    @Override
    public void stop() throws Exception {
        // for faster close window, so first close visible window
        GUIState.getStage().close();
        // when use spring web, if super close(), may produce  AsynchronousCloseException
        Platform.exit();
        System.exit(0);
    }

    @Override
    public Collection<Image> loadDefaultIcons() {
        List<Image> icons = new ArrayList<>();
        int[] sizes = {16, 24, 36, 42, 64};

        for (int size : sizes) {
            String resourcePath = String.format("/icons/lc_%dx%d.png", size, size);
            icons.add(new Image(getClass().getResource(resourcePath).toExternalForm()));
        }

        return icons;
    }
}

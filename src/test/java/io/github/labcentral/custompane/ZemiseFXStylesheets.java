package io.github.labcentral.custompane;

import io.github.palexdev.materialfx.theming.base.Theme;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">zemise</a>
 * @Date 2024/1/5
 * @since 1.0
 */
public enum ZemiseFXStylesheets implements Theme {
    PANE("css/ZFXPane.css"),
    DEFAULT("css/DefaultTheme.css");

    private final String path;

    private ZemiseFXStylesheets(String path) {
        this.path = path;
    }

    @Override
    public String path() {
        return this.path;
    }
}

package io.github.labcentral;

import java.io.InputStream;
import java.net.URL;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @Date 2024/1/5
 * @since 1.0
 */
public class ResourceLoader {
    private ResourceLoader() {

    }

    public static URL loadUrl(String path) {
        return ResourceLoader.class.getResource(path);
    }

    public static String load(String path) {
        return loadUrl(path).toString();
    }


    public static InputStream loadStream(String name) {
        return ResourceLoader.class.getResourceAsStream(name);
    }

}

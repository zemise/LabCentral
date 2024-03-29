package io.github.labcentral.util;

import javafx.collections.ObservableList;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.*;

import static java.awt.Desktop.getDesktop;

/**
 * <p>
 * A collection of various static system utility methods
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/9
 */
public class SystemUtil {
    private boolean isDark;

    public enum TypeOS {
        WINDOWS, MAC, LINUX, OTHER
    }

    /**
     * Get current system os type
     *
     * @return TypeOS
     */
    public static TypeOS getSystemType() {
        String platform = System.getProperty("os.name").toLowerCase();

        if (platform.contains("win")) {
            return TypeOS.WINDOWS;
        }
        if (platform.contains("mac")) {
            return TypeOS.MAC;
        }
        if (platform.contains("nux")) {
            return TypeOS.LINUX;
        }
        return TypeOS.OTHER;
    }


    /**
     * Opens an url in the current system's default browser
     *
     * @param url target string url
     */
    public static void browserUrl(String url) {
        switch (getSystemType()) {
            case WINDOWS -> {
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
            case MAC -> {
                if (Desktop.isDesktopSupported()) {
                    if (Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                        try {
                            Desktop.getDesktop().browse(new URL(url).toURI());
                        } catch (IOException | URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            case LINUX -> {
                try {
                    Runtime.getRuntime().exec(new String[]{"xdg-open", url});
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            default -> {
            }
        }
    }

    /**
     * Copy text to current system Clipboard
     *
     * @param text String Content
     */
    public static void copyToClipboard(String text) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();

        content.putString(text);
        clipboard.setContent(content);
    }


    private void toggleDark() {
        if (isDark) {
            // This is how to set a light style w/the default JavaFX CSS
            // scene.getRoot().setStyle("");
//            Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        } else {
            // This is how to set a dark style w/the default JavaFX CSS.
            // scene.getRoot().setStyle("-fx-base:#25292D;");
//            Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
        }
        isDark = !isDark;
    }

    public static void openFileLocation(File file) {
        getDesktop().browseFileDirectory(file);
    }

    public static void showAllWindows() {
        ObservableList<Window> windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof Stage stage) {
                stage.setIconified(false);  // 恢复窗口到正常大小
                stage.toFront();
            }
        }
    }

    /**
     * check if there have internet connection
     * @return boolean indicating
     */
    public static boolean isInternetAvailable() {
        try {
            InetAddress address = InetAddress.getByName("8.8.8.8");

            return address.isReachable(3000); // 3 seconds timeout
        } catch (java.io.IOException e) {
            // An exception occurred, meaning no internet connection
            return false;
        }
    }
}

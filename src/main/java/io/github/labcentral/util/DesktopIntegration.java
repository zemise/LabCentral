package io.github.labcentral.util;

import javafx.concurrent.Task;

import java.awt.*;

import static java.awt.Taskbar.getTaskbar;

public class DesktopIntegration {
    int currentIconProgress = 1;
    Image defaultIcon;
    Image redCircleIcon;

//    public Menu extraDesktopIntegration(Log log) {
//        if (!isTaskbarSupported()) {
//            return null;
//        }
//
//        log.log("");
//        log.log("Desktop integration flags for this platform include:");
//
//        for (Feature feature : Feature.values()) {
//            log.log(" " + feature.name() + " " + getTaskbar().isSupported(feature));
//        }
//
//        if (getTaskbar().isSupported(Feature.ICON_IMAGE)) {
//            defaultIcon = getTaskbar().getIconImage();
//
//            BufferedImage bufferedImage = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
//            Graphics2D graphics2D = bufferedImage.createGraphics();
//            graphics2D.setColor(Color.red);
//            graphics2D.fillOval(0, 0, 256, 256);
//            graphics2D.dispose();
//
//
//            redCircleIcon = bufferedImage;
//            // test 默认图片
//            Image image = Toolkit.getDefaultToolkit().getImage("app-icon.png");
//            redCircleIcon = image;
//
//        }
//        MenuItem useCustomIcon = CustomMenuItem.configure("Use Custom App Icon", x -> getTaskbar().setIconImage(redCircleIcon), null);
//        MenuItem useDefaultAppIcon = CustomMenuItem.configure("Use Default App Icon", x -> getTaskbar().setIconImage(defaultIcon), null);
//        useCustomIcon.setDisable(!getTaskbar().isSupported(Feature.ICON_IMAGE));
//        useDefaultAppIcon.setDisable(!getTaskbar().isSupported(Feature.ICON_IMAGE));
//
//        Menu desktopIntegration = new Menu("Desktop");
//
//        MenuItem setIconBadge = CustomMenuItem.configure("Set Badge", x -> getTaskbar().setIconBadge("1"), null);
//        MenuItem removeIconBadge = CustomMenuItem.configure("Remove Badge", x -> getTaskbar().setIconBadge("1"), null);
//
//        setIconBadge.setDisable(!getTaskbar().isSupported(Feature.ICON_BADGE_TEXT));
//        removeIconBadge.setDisable(!getTaskbar().isSupported(Feature.ICON_BADGE_TEXT));
//
//
//        MenuItem addProgress = CustomMenuItem.configure("Add Icon Progress", x -> getTaskbar().setProgressValue(currentIconProgress++), KeyCode.R);
//        MenuItem clearProgress = CustomMenuItem.configure("Clear Icon Progress", x -> {
//            currentIconProgress = -1;
//            getTaskbar().setProgressValue(currentIconProgress++);
//        }, null);
//        addProgress.setDisable(!getTaskbar().isSupported(Feature.PROGRESS_VALUE));
//        clearProgress.setDisable(!getTaskbar().isSupported(Feature.PROGRESS_VALUE));
//
//        MenuItem requestUserAttention = CustomMenuItem.configure("Request User Attention (5s)", x -> requestUserAttention(), null);
//
//        requestUserAttention.setDisable(!getTaskbar().isSupported(Feature.USER_ATTENTION));
//
//        desktopIntegration.getItems().addAll(setIconBadge, removeIconBadge, addProgress, clearProgress, useCustomIcon, useDefaultAppIcon, requestUserAttention);
//
//        return desktopIntegration;
//    }

    private void requestUserAttention() {

        Task task = new Task<Void>() {

            @Override
            public Void call() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getTaskbar().requestUserAttention(true, true);
                return null;
            }
        };

        new Thread(task).start();
    }

}

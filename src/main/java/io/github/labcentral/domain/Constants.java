package io.github.labcentral.domain;


import de.felixroske.jfxsupport.PropertyReaderHelper;
import io.github.labcentral.LabCentralMain;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * <p>
 * All Constants
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @Date 2023/12/16
 * @since 1.0
 */
@Slf4j
public class Constants {
    public static final String TITLE = "LabCentral";

    public static final String RESOURCE_PATH = PropertyReaderHelper.determineFilePathFromPackageName(LabCentralMain.class);
    public static final String FONT_PACKAGE = RESOURCE_PATH + "font/";
    public static final String MEDIA_PACKAGE = RESOURCE_PATH + "media/";
    public static final String VIEW_PACKAGE = RESOURCE_PATH + "view/";
    public static final String SPlASH_VIEW = VIEW_PACKAGE + "splash.fxml";


    public static final String STAGE_ICON = MEDIA_PACKAGE + "icon.png";
    public static final String NO_IMAGE_AVAILABLE = MEDIA_PACKAGE + "empty-image.jpg";
    public static final String INFORMATION_IMAGE = MEDIA_PACKAGE + "information.png";
    public static final String ERROR_IMAGE = MEDIA_PACKAGE + "error.png";
    public static final String SUCCESS_IMAGE = MEDIA_PACKAGE + "success.png";
}

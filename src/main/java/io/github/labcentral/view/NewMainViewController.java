package io.github.labcentral.view;

import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.GUIState;
import io.github.labcentral.ResourceLoader;
import io.github.labcentral.util.SystemUtil;
import io.github.labcentral.view.menubar.MenubarPlugin;
import io.github.labcentral.view.menubar.mainview.*;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXRectangleToggleNode;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.utils.ScrollUtils;
import io.github.palexdev.materialfx.utils.ToggleButtonsUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @Date 2024/1/6
 * @since 1.0
 */

@Slf4j
@FXMLController
public class NewMainViewController implements Initializable {

    private final TextIntoImageView textIntoImageView;

    private final CalculatorView calculatorView;

    private final SciView sciView;

    private final MwSearchView mwSearchView;

    @Autowired
    public NewMainViewController(TextIntoImageView textIntoImageView,
                                 CalculatorView calculatorView,
                                 SciView sciView,
                                 MwSearchView mwSearchView) {
        this.textIntoImageView = textIntoImageView;
        this.calculatorView = calculatorView;
        this.sciView = sciView;
        this.mwSearchView = mwSearchView;
    }

    private final MenubarPlugin[] menubarPlugins = {new LabCentralMenu(), new FileMenu(), new EditMenu(),
            new ToolsMenu(), new WindowsMenu(), new HelpMenu()};
    private ToggleGroup toggleGroup;

    @FXML
    public MenuBar menuBar;
    @FXML
    public AnchorPane rootPane;
    @FXML
    public StackPane logoContainer;
    @FXML
    public MFXScrollPane scrollPane;
    @FXML
    public VBox navBar;

    @FXML
    public StackPane contentPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // menuBar display
        loadMenubar(menuBar, menubarPlugins);

        // 固定大小
        GUIState.getStage().setMinWidth(1280);
        GUIState.getStage().setMinHeight(760);

        // logo display
        Image image = new Image(ResourceLoader.load("media/app-icon.png"), 64, 64, true, true);
        ImageView logo = new ImageView(image);
        Circle clip = new Circle(30);

        // 计算中心点
        double centerX = logo.getBoundsInLocal().getWidth() / 2;
        double centerY = logo.getBoundsInLocal().getHeight() / 2;

        clip.centerXProperty().bind(logo.translateXProperty().add(centerX));
        clip.centerYProperty().bind(logo.translateYProperty().add(centerY));
        logo.setClip(clip);

        logoContainer.getChildren().add(logo);

        // todo: it so nice, try to figure out how to work
        ScrollUtils.addSmoothScrolling(scrollPane);

        this.toggleGroup = new ToggleGroup();
        ToggleButtonsUtil.addAlwaysOneSelectedSupport(toggleGroup);


        ToggleButton t1 = createToggle("fas-computer", "计算浓度");

        createToggle("fas-cloud-arrow-down", "SCI论文下载");
        createToggle("fas-photo-film", "WB图片处理");
        createToggle("fas-book-bookmark", "Protocol");
        createToggle("fas-list-check", "清单计划");
        createToggle("fas-magnifying-glass-chart", "分子量查询");
        createToggle("fas-magnifying-glass-location", "库存查询");
        createToggle("fas-star", "探索", 45);
        createToggle("fas-business-time", "开发ing");
        createToggle("fas-business-time", "开发ing");
        createToggle("fas-business-time", "开发ing");
        createToggle("fas-business-time", "开发ing");
        createToggle("fas-business-time", "开发ing");
        createToggle("fas-business-time", "开发ing");
        createToggle("fas-business-time", "开发ing");
        toggleGroup.getToggles().forEach(e -> {
            navBar.getChildren().add((ToggleButton) e);
        });


        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            Toggle toggle = observable.getValue();
            String selectName = ((ToggleButton) toggle).getText();

            contentPane.getChildren().clear();
            switch (selectName) {
                case "计算浓度" -> {
                    MFXScrollPane mfxScrollPane = new MFXScrollPane(calculatorView.getView());
                    ScrollUtils.addSmoothScrolling(mfxScrollPane);

                    contentPane.getChildren().add(mfxScrollPane);
                }
                case "WB图片处理" -> {
                    contentPane.getChildren().add(textIntoImageView.getView());
                }
                case "SCI论文下载" -> {
                    contentPane.getChildren().add(sciView.getView());
                }
                case "分子量查询" -> {
                    contentPane.getChildren().add(mwSearchView.getView());
                }
                default -> {
                    contentPane.getChildren().clear();
                }
            }

        });

        t1.setSelected(true);

    }


    private ToggleButton createToggle(String icon, String text) {
        return createToggle(icon, text, 0);
    }

    private ToggleButton createToggle(String icon, String text, double rotate) {
        MFXIconWrapper wrapper = new MFXIconWrapper(icon, 24, 32);
        MFXRectangleToggleNode toggleNode = new MFXRectangleToggleNode(text, wrapper);
        toggleNode.setAlignment(Pos.CENTER_LEFT);
        toggleNode.setMaxWidth(Double.MAX_VALUE);
//        toggleNode.setPrefWidth(40);
        toggleNode.setToggleGroup(toggleGroup);
        if (rotate != 0) {
            wrapper.getIcon().setRotate(rotate);
        }
        return toggleNode;
    }

    private void loadMenubar(MenuBar menuBar, MenubarPlugin[] menubarPlugins) {
        // optimize, if mac
        if (SystemUtil.getSystemType() == SystemUtil.TypeOS.MAC) {
            menuBar.setUseSystemMenuBar(true);
        }

        for (MenubarPlugin menubarPlugin : menubarPlugins) {
            try {
                menubarPlugin.setup(GUIState.getStage(), menuBar);
                log.debug("Success to load menuBarPlugin: {}", menubarPlugin.getClass().getName());
            } catch (Exception e) {
                log.error("Failed to load menuBarPlugin: {}", menubarPlugin.getClass().getName(), e);
            }
        }
    }

}

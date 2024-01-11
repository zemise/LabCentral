package io.github.labcentral.view;

import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.GUIState;
import io.github.labcentral.service.Dele;
import io.github.labcentral.util.SystemUtil;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.enums.ScrimPriority;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static io.github.labcentral.util.FxUtil.colorToHex;
import static io.github.labcentral.util.FxUtil.fxColorToAwtFont;
import static io.github.labcentral.util.TextInTIFF.getImageFile;
import static io.github.labcentral.util.TextInTIFF.insetTextWithTop;

/**
 * <p>
 * Controller class for handling text-to-image conversion view.
 * </p>
 *
 * @author <a href= "https://github.com/zemise">zemise</a>
 * @Date 2023/12/18
 * @since 1.0
 */

@Slf4j
@FXMLController
public class TextIntoImageViewController implements Initializable {
    private FileChooser fileChooser;
    private DirectoryChooser dirChooser;
    private File selectedFile;
    private List<File> selectedImages;

    @FXML
    public AnchorPane root;
    @FXML
    public MFXButton btnConfirm;
    public TextArea textArea;
    public ColorPicker colorPicker;
    public MFXTextField directoryFiled;
    public MFXTextField targetNameReg;
    public MFXComboBox<String> cbFont;
    public MFXComboBox<Integer> cbFontSize;
    public MFXDatePicker datePicker;
    public MFXRadioButton imageModel;
    public MFXRadioButton fileModel;
    public MFXToggleButton insertDate;
    public MFXProgressBar progressBar;


    private MFXGenericDialog dialogContent;
    private MFXStageDialog dialog;
    private java.awt.Color color = java.awt.Color.RED;
    private String fontSize = "32";

    private String fontName = javafx.scene.text.Font.getDefault().getName();


    @Value("${insertExample}")
    private String insertExample;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initController();

        // 监听颜色选择，改变输入框----颜色类型，以及插入图片文字颜色
        colorPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
                    color = fxColorToAwtFont(newValue);
                    String colorString = String.format("-fx-text-fill: %s", colorToHex(newValue));
                    textArea.setStyle(colorString);
                }
        );

        cbFontSize.textProperty().addListener((observable, oldValue, newValue) -> {
            fontSize = newValue;
            textArea.setFont(new javafx.scene.text.Font(fontName, Double.parseDouble(newValue)));
        });

        cbFont.textProperty().addListener((observable, oldValue, newValue) -> {
            fontName = newValue;
            textArea.setFont(new javafx.scene.text.Font(fontName, Double.parseDouble(fontSize)));
        });
    }

    private void initController() {
        // 初始化各类Dialogs
        dialogContent = MFXGenericDialogBuilder.build()
                //.setContentText("this is a test message")
                .makeScrollable(true)
                .get();
        dialog = MFXGenericDialogBuilder.build(dialogContent)
                .toStageDialogBuilder()
                .initOwner(GUIState.getStage())
                .initModality(Modality.APPLICATION_MODAL)
                .setDraggable(true)
                .setTitle("Dialogs Preview")
                .setOwnerNode(root)
                .setScrimPriority(ScrimPriority.WINDOW)
                .setScrimOwner(true)
                .get();

        dialogContent.addActions(
                Map.entry(new MFXButton("Confirm"), event -> dialog.close()),
                Map.entry(new MFXButton("Cancel"), event -> dialog.close())
        );
        dialogContent.setMaxSize(400, 200);


        // 初始化colorPicker、textArea等
        colorPicker.valueProperty().set(Color.RED);

        String colorString = String.format("-fx-text-fill: %s", colorToHex(Color.RED));
        textArea.setStyle(colorString);
        textArea.setFont(new javafx.scene.text.Font(fontName, Double.parseDouble(fontSize)));

        // 初始化fontSize comboBox
        for (int i = Integer.parseInt(fontSize); i < 72; i++) {
            cbFontSize.getItems().add(i);
        }

        // 初始化font comboBox
        javafx.scene.text.Font.getFamilies().forEach(fontName -> cbFont.getItems().add(fontName));

        // 初始化date picker
        datePicker.setValue(LocalDate.now());

        // 初始化insertDate
        insertDate.setSelected(true);

        // 初始化模式选择
        ToggleGroup modelGroup = new ToggleGroup();
        imageModel.setToggleGroup(modelGroup);
        fileModel.setToggleGroup(modelGroup);
        fileModel.setSelected(true);

        // 初始化directoryFiled
        directoryFiled.setFloatingText("文件下的所有图片，插入文字");

        // 监听模式选择
        imageModel.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                directoryFiled.setFloatingText("特定图片，插入文字");
                //清空fileModel下选择的文件
                selectedFile = null;
                directoryFiled.setText("");
            }
        });
        fileModel.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                directoryFiled.setFloatingText("文件下的所有图片，插入文字");
                //清空imageModel下选择的图片
                selectedImages = null;
                directoryFiled.setText("");
            }
        });

        // 初始化textArea
        textArea.setPromptText(insertExample);

        // 初始化文件或文件夹选择器
        fileChooser = new FileChooser();
        fileChooser.setTitle("选择图片");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files",
                "*.tif", "*.png", "*.jpg", "*.jpeg", "*.gif");
        fileChooser.getExtensionFilters().add(filter);

        dirChooser = new DirectoryChooser();
        dirChooser.setTitle("选择所有图片所在的文件夹");

    }

    @FXML
    public void onExplore() {
        if (fileModel.selectedProperty().get()) {
            handleDirectorySelection();
        } else {
            handleImageSelection();
        }
    }


    @FXML
    public void onConfirm() {
        progressBar.setVisible(true);
        btnConfirm.setDisable(true);
        Task<Void> insertTextToImage = new Task<>() {
            @Override
            protected Void call() {
                try {
                    if (imageModel.selectedProperty().get()) {
                        handleImageModel();
                    } else {
                        handleFolderModel();
                    }
                } catch (Exception e) {
                    log.error("Error in insertTextToImage: ", e);
                } finally {
                    Platform.runLater(() -> {
                        progressBar.setVisible(false);
                        btnConfirm.setDisable(false);
                    });
                }
                return null;
            }
        };

        new Thread(insertTextToImage).start();
    }

    private void handleImageSelection() {
        // imageModel
        List<File> images = fileChooser.showOpenMultipleDialog(root.getScene().getWindow());

        if (images != null) {
            StringBuilder showSelectedImages = new StringBuilder();
            for (File file : images) {
                showSelectedImages.append(file.getName()).append(" ");
            }
            directoryFiled.setText(showSelectedImages.toString());
            selectedImages = images;
        }
    }

    private void handleDirectorySelection() {
        File directory = dirChooser.showDialog(root.getScene().getWindow());
        if (directory != null) {   //排除用户在文件选择对话框里没有选择任何文件
            directoryFiled.setText(directory.getAbsolutePath());
            selectedFile = directory;
        }
    }

    /**
     * 给指定的图片文件列表插入文字
     *
     * @param images selected images files list
     */
    private void processImages(List<File> images) {
        //插入文字后的文件夹
        File outDir = new File(images.get(0).getParentFile(), "图片输出");
        Font font = new Font(fontName, Font.PLAIN, Integer.parseInt(fontSize));

        String insertText = textArea.getText();
        if (insertDate.isSelected()) {
            insertText = datePicker.getText() + "\n" + insertText;
        }

        for (File file : images) {
            insetTextWithTop(file, insertText, outDir, font, color);
        }

        // 执行完，打开所在文件夹
        SystemUtil.openFileLocation(outDir);
    }


    /**
     * 给母文件夹下的所有图片插入文字
     *
     * @param file image file
     */
    private void processImages(File file) {
        //插入文字后的文件夹
        File outDir = new File(file.getPath(), file.getName());
        // 获得文件夹下大部分图片格式的图片
        List<File> imageFiles = getImageFile(file, "tif");
        imageFiles.addAll(getImageFile(file, "png"));
        imageFiles.addAll(getImageFile(file, "jpg"));
        Font font = new Font(fontName, Font.PLAIN, Integer.parseInt(fontSize));

        String insertText = textArea.getText();
        if (insertDate.isSelected()) {
            insertText = datePicker.getText() + "\n" + insertText;
            System.out.println(insertText);
        }

        for (File imageFile : imageFiles) {
            insetTextWithTop(imageFile, insertText, outDir, font, color);
        }

        // 执行完，打开所在文件夹
        SystemUtil.openFileLocation(outDir);
    }


    private void handleFolderModel() {
        if (selectedFile == null || !selectedFile.exists()) {
            showWarnDialog("文件夹模式下，请选择一个图片所在的文件夹");
            return;
        }
        String reg = targetNameReg.getText().isEmpty() ? targetNameReg.getPromptText() : targetNameReg.getText();
        Dele.deleteFile(selectedFile, reg);
        processImages(selectedFile);
    }

    private void handleImageModel() {
        if (selectedImages == null || selectedImages.isEmpty()) {
            showWarnDialog("图片模式下，请至少选择一张图片");
            return;
        }
        processImages(selectedImages);
    }

    /**
     * MaterialFX style dialog
     *
     * @param content warn message
     */
    private void showWarnDialog(String content) {
        Platform.runLater(() -> {
            MFXFontIcon warnIcon = new MFXFontIcon("fas-circle-exclamation", 18);
            dialogContent.setHeaderIcon(warnIcon);
            dialogContent.setHeaderText("Warning Info");
            dialogContent.setContentText(content);
            convertDialogTo("mfx-warn-dialog");
            dialog.showDialog();
        });
    }

    private void convertDialogTo(String styleClass) {
        dialogContent.getStyleClass().removeIf(
                s -> "mfx-info-dialog".equals(s) || "mfx-warn-dialog".equals(s) || "mfx-error-dialog".equals(s)
        );

        if (styleClass != null) {
            dialogContent.getStyleClass().add(styleClass);
        }
    }
}

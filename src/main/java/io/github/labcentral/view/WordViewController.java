package io.github.labcentral.view;

import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.GUIState;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import io.github.labcentral.MaterialFXStylesheets;
import io.github.labcentral.service.Dele;
import io.github.labcentral.util.FxUtil;
import io.github.labcentral.util.SystemUtil;
import org.springframework.beans.factory.annotation.Value;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import static io.github.labcentral.util.TextInTIFF.*;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">zemise</a>
 * @Date 2023/12/18
 * @since 1.0
 */

@FXMLController
public class WordViewController implements Initializable {
    public MFXButton btnConfirm;
    public TextArea textArea;
    public ColorPicker colorPicker;
    public MFXButton directoryChooser;
    public MFXTextField directoryFiled;
    public MFXTextField targetNameReg;
    public MFXComboBox cbFont;
    public MFXComboBox cbFontSize;
    public MFXDatePicker datePicker;
    public MFXRadioButton imageModel;
    public MFXRadioButton fileModel;
    public MFXToggleButton insertDate;
    public MFXProgressBar progressBar;

    private java.awt.Color color = java.awt.Color.RED;
    private String fontSize = "32";

    private String fontName = javafx.scene.text.Font.getDefault().getName();

    private File selectedFile;
    private List<File> selectedImages;


    @Value("${insertExample}")
    private String insertExample;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initConfig();

        directoryChooser.setOnAction(event -> {
            if (fileModel.selectedProperty().get()) {
                // fileModel
                DirectoryChooser dirChooser = new DirectoryChooser(); //创建文件选择对话框
                dirChooser.setTitle("选择所有图片所在的文件夹");
                File file = dirChooser.showDialog(null);
                if (file != null) {   //排除用户在文件选择对话框里没有选择任何文件
                    directoryFiled.setText(file.getAbsolutePath());
                    selectedFile = file;
                }
            } else {
                // imageModel
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("选择图片");
                // 设置文件过滤器，限定只能选择图片文件
                FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.tif", "*.png", "*.jpg", "*.jpeg", "*.gif");
                fileChooser.getExtensionFilters().add(imageFilter);
                // 显示文件选择对话框
                List<File> selected = fileChooser.showOpenMultipleDialog(GUIState.getStage());

                // 处理选择的文件
                if (selected != null) {
                    String showSelectedImages = "";
                    for (File file : selected) {
                        showSelectedImages += file.getName() + " ";
                    }
                    directoryFiled.setText(showSelectedImages);
                    selectedImages = selected;
                }
            }
        });


        // 监听颜色选择，改变输入框----颜色类型，以及插入图片文字颜色
        colorPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
                    color = fxColorToAwtFont(newValue);
                    String colorString = String.format("-fx-text-fill: %s", colorToHex(newValue));
                    textArea.setStyle(colorString);
                }

        );


        // 执行插入文字
        btnConfirm.setOnAction(event -> {
            progressBar.setVisible(true);
            btnConfirm.setDisable(true);
            new Thread(() -> {
                // 判定模式
                if (imageModel.selectedProperty().get()) {
                    // 图片模式
                    if (selectedImages == null || (selectedImages.size() == 0)) {
                        Platform.runLater(() -> {
                            FxUtil.showWarningDialog("还没有选择图片！");
                            progressBar.setVisible(false);
                            btnConfirm.setDisable(false);
                        });

                        return;
                    }
                    // 直接处理图片
                    processImages(selectedImages);
                } else {
                    if (selectedFile == null || !selectedFile.exists()) {
                        Platform.runLater(
                                () -> {
                                    FxUtil.showWarningDialog("还没有选择图片所在文件夹！");
                                    progressBar.setVisible(false);
                                    btnConfirm.setDisable(false);
                                }
                        );

                        return;
                    }
                    // 先删除，匹配文字reg
                    String reg = targetNameReg.getPromptText();
                    if (targetNameReg.getText() != "") {
                        reg = targetNameReg.getText();
                    }
                    Dele.deleteFile(selectedFile, reg);
                    processImages(selectedFile);
                }

                // 在UI线程上更新ProgressBar的可见性
                Platform.runLater(() -> {
                    progressBar.setVisible(false);
                    btnConfirm.setDisable(false);
                });
            }).start();
        });


        cbFontSize.textProperty().addListener((observable, oldValue, newValue) -> {
            fontSize = newValue;
            textArea.setFont(new javafx.scene.text.Font(fontName, Double.parseDouble(newValue)));
        });

        cbFont.textProperty().addListener((observable, oldValue, newValue) -> {
            fontName = newValue;
            textArea.setFont(new javafx.scene.text.Font(fontName, Double.parseDouble(fontSize)));
        });


    }


    private void initConfig() {
        UserAgentBuilder.builder()
                .themes(JavaFXThemes.MODENA)
                .themes(MaterialFXStylesheets.forAssemble(true))
                .setDeploy(true)
                .setResolveAssets(true)
                .build()
                .setGlobal();

        colorPicker.valueProperty().set(Color.RED);

        String colorString = String.format("-fx-text-fill: %s", colorToHex(Color.RED));
        textArea.setStyle(colorString);
        textArea.setFont(new javafx.scene.text.Font(fontName, Double.parseDouble(fontSize)));

        // 初始化fontSize comboBox
        for (int i = Integer.parseInt(fontSize); i < 72; i++) {
            cbFontSize.getItems().add(i);
        }

        // 初始化font comboBox
        javafx.scene.text.Font.getFamilies().forEach(fontName -> {
            cbFont.getItems().add(fontName);
        });

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

    }


    /**
     * 给指定的图片文件列表插入文字
     *
     * @param images
     */
    private void processImages(List<File> images) {
        //插入文字后的文件夹
        File outDir = new File(images.get(0).getParentFile(), "图片输出");
        Font font = new Font(fontName, 0, Integer.parseInt(fontSize));

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
     * @param file
     */
    private void processImages(File file) {
        //插入文字后的文件夹
        File outDir = new File(file.getPath(), file.getName());
        // 获得文件夹下大部分图片格式的图片
        List<File> imageFiles = getImageFile(file, "tif");
        imageFiles.addAll(getImageFile(file, "png"));
        imageFiles.addAll(getImageFile(file, "jpg"));
        Font font = new Font(fontName, 0, Integer.parseInt(fontSize));

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


    /**
     * 将javaFX Color转换为awt Color
     *
     * @param fxColor javaFX Color
     * @return awt Color
     */
    public java.awt.Color fxColorToAwtFont(Color fxColor) {
        return new java.awt.Color(
                (float) fxColor.getRed(),
                (float) fxColor.getGreen(),
                (float) fxColor.getBlue(),
                (float) fxColor.getOpacity()
        );
    }

    /**
     * 将javaFX Color转换为16进制颜色
     *
     * @param color JavaFX Color
     * @return 16进制颜色
     */
    public String colorToHex(Color color) {
        int red = (int) (color.getRed() * 255);
        int green = (int) (color.getGreen() * 255);
        int blue = (int) (color.getBlue() * 255);
        int opacity = (int) (color.getOpacity() * 255);

        return String.format("#%02X%02X%02X%02X", red, green, blue, opacity);
    }
}

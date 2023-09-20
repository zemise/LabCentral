package labcentral.view;

import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import labcentral.service.Dele;
import labcentral.util.FileUtil;
import labcentral.util.FxUtil;
import labcentral.view.menubar.MenubarPlugin;
import labcentral.view.menubar.insertview.FontMenu;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static labcentral.util.TextInTIFF.getImageFile;
import static labcentral.util.TextInTIFF.insertText;

@FXMLController
public class InsertTextController implements Initializable {
    @FXML
    public TextArea textArea;
    @FXML
    public Button btnConfirm;
    @FXML
    public TextField textTarget;
    @FXML
    public Button btnTarget;
    @FXML
    public Label statusLab;
    @FXML
    public Label progressLab;

    @FXML
    public MenuBar menuBar;

    @Value("test")
    private String text;

    private final MenubarPlugin[] menubarPlugins = {new FontMenu()};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FxUtil.loadMenubar(menuBar, menubarPlugins);

        textArea.setText(text);

        btnTarget.setOnAction(event -> {
            DirectoryChooser dirChooser = new DirectoryChooser(); //创建文件选择对话框
            File file = dirChooser.showDialog(null);
            if (file != null) {   //排除用户在文件选择对话框里没有选择任何文件
                textTarget.setText(file.getAbsolutePath());
            }
        });

        btnConfirm.setOnAction(event -> {
            new Thread(this::textIn).start();
        });

    }

    private void textIn() {
        String content = textArea.getText();
        FileUtil.write(content);

        String targetFolderPath = textTarget.getText();
        if (targetFolderPath.isEmpty()) {
            showWarningDialog("目标文件夹不能为空");
            return;
        }

        Task<String> deleteTask = new Task<String>() {
            @Override
            protected String call() {
                updateStatus("正在将低质量图片移至删除文件夹...");
                return Dele.deleteFile(new File(textTarget.getText()), "300");
            }
        };

        deleteTask.setOnSucceeded(event -> {
            String deleOutput = deleteTask.getValue();
            updateStatus("完成！已将低质量图片移至删除文件夹");
            processImages(targetFolderPath);
        });

        deleteTask.setOnFailed(event -> showWarningDialog("删除文件时出错"));

        Thread deleteThread = new Thread(deleteTask);
        deleteThread.start();
    }

    private void processImages(String targetFolderPath) {
        //目标源文件夹
        File tarDir = new File(textTarget.getText());
        //插入文字后的文件夹
        File outDir = new File(tarDir.getPath(), tarDir.getName());

        List<File> tiffs = getImageFile(tarDir, "tif");

        for (File file : tiffs) {
            updateStatus(file.getName());
            insertText(file, textArea.getText(), outDir);
        }

        updateStatus("本次操作完毕");
        updateProgressLab("Done");
    }

    private void showWarningDialog(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("警告");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.show();
        });

    }

    private void updateStatus(String status) {
        Platform.runLater(() -> statusLab.setText(status));
    }

    private void updateProgressLab(String status) {
        Platform.runLater(() -> {
            progressLab.setText(status);
        });
    }
}

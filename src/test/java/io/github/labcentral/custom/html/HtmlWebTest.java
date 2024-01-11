package io.github.labcentral.custom.html;

import io.github.labcentral.ResourceLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @Date 2024/1/8
 * @since 1.0
 */
public class HtmlWebTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // 加载一个简单的 HTML 内容，也可以加载其他内容
//        String htmlContent = "<html><body><h1>Hello, WebView!</h1></body></html>";
//        webEngine.loadContent(htmlContent);
        webEngine.load(ResourceLoader.load("media/test001.html"));
//        webEngine.load("https://sci-hub.ru");

        // 创建 VBox 布局管理器
        VBox root = new VBox(webView);

        // 设置 VBox 的 VBox.VGrow 属性，使 WebView 在纵向方向上占满全部空间
        VBox.setVgrow(webView, Priority.ALWAYS);

        Scene scene = new Scene(root, 800, 600);




        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();


    }
}

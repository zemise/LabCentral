package io.github.labcentral.custom.PdfView;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @Date 2024/1/7
 * @since 1.0
 */
public class PdfView extends ImageView {

    // PDFBox api---version 2
//    public PdfView(File pdfFile) {
//        try {
//            // 使用 PDFBox 加载 PDF 文档
//            PDDocument document = PDDocument.load(pdfFile);
//
//            // 获取 PDF 文档的第一页
//            BufferedImage bufferedImage = new PDFRenderer(document).renderImage(0);
//
//            // 将 BufferedImage 转换为 JavaFX 的 Image 对象
//            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
//
//            // 设置 Image 到 ImageView 中
//            setImage(image);
//
//            // 关闭 PDF 文档
//            document.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    // PDFBox api---version 3
    public PdfView(File pdfFile) {
        try {
            // 使用 PDFBox 加载 PDF 文档
            PDDocument document = Loader.loadPDF(pdfFile);

            // 获取 PDF 文档的第一页
//            BufferedImage bufferedImage = new PDFRenderer(document).renderImage(0);

            // 提高渲染质量的参数调整
            float scale = 0.5f; // 增加缩放比例
            int dpi = 300; // 增加 DPI（每英寸点数）
            BufferedImage bufferedImage = new PDFRenderer(document).renderImageWithDPI(0, dpi, ImageType.RGB);

            Image image = SwingFXUtils.toFXImage(bufferedImage, null);

            setImage(image);

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

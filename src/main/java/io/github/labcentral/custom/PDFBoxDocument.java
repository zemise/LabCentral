package io.github.labcentral.custom;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessReadBufferedFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.rendering.RenderDestination;

import java.awt.image.BufferedImage;
import java.awt.print.Pageable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * An implementation of {@link Document} for the Apache PDFBox library.
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @Date 2024/1/7
 * @see PDFView#setDocument(Document)
 * </p>
 * @since 1.0
 */
public class PDFBoxDocument implements  SearchableDocument{
    private final PDDocument document;

    public PDFBoxDocument(InputStream pdfInputStream) {
        try {
            document = Loader.loadPDF(pdfInputStream.readAllBytes());
        } catch (IOException e) {
            throw new DocumentProcessingException(e);
        }
    }

    public PDFBoxDocument(File file) throws IOException {
        document = Loader.loadPDF(new RandomAccessReadBufferedFile(file));
    }



    @Override
    public BufferedImage renderPage(int pageNumber, float scale) {
        PDFRenderer renderer = new PDFRenderer(document);
        BufferedImage bufferedImage;

        try {
            bufferedImage = renderer.renderImage(pageNumber, scale, ImageType.ARGB, RenderDestination.VIEW);
        } catch (IOException e) {
            throw new DocumentProcessingException(e);
        }

        return bufferedImage;
    }

    @Override
    public int getNumberOfPages() {
        return document.getNumberOfPages();
    }

    @Override
    public boolean isLandscape(int pageNumber) {
        PDPage page = document.getPage(pageNumber);
        PDRectangle cropBox = page.getCropBox();
        return cropBox.getHeight() < cropBox.getWidth();
    }

    @Override
    public Pageable getPageable() {
        return new PDFPageable(document);
    }

    @Override
    public void close() {
        try {
            document.close();
        } catch (IOException e) {
            throw new DocumentProcessingException(e);
        }
    }

    @Override
    public List<PDFView.SearchResult> getSearchResults(String searchText) {
        // do something
        return null;
    }



}

package io.github.labcentral.custom;

import java.awt.image.BufferedImage;
import java.awt.print.Pageable;

/**
 * <p>
 *  The interface that needs to be implemented by any model object that
 *  represents a PDF document and that wants to be displayed by the view.
 *  @see #setDocument(Document)
 *  @see PDFBoxDocument
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @Date 2024/1/7
 * @since 1.0
 */

public interface Document {
    /**
     * Renders the page specified by the given number at the given scale.
     *
     * @param pageNumber the page number
     * @param scale      the scale
     * @return the generated buffered image
     */
    BufferedImage renderPage(int pageNumber, float scale);

    /**
     * Returns the total number of pages inside the document.
     *
     * @return the total number of pages
     */
    int getNumberOfPages();

    /**
     * Determines if the given page has a landscape orientation.
     *
     * @param pageNumber the page
     * @return true if the page has to be shown in landscape mode
     */
    boolean isLandscape(int pageNumber);

    /**
     * Returns a set of pages to be printed.
     *
     * @return a set of pages to be printed
     */
    Pageable getPageable();

    /**
     * Closes the document.
     */
    void close();

    /**
     * A specialized exception for signalling processing errors while
     * reading / parsing a PDF file.
     */
    class DocumentProcessingException extends RuntimeException {

        /**
         * Constructs a new processing exception wrapping the given
         * cause.
         *
         * @param cause the reason for the exception
         */
        public DocumentProcessingException(Throwable cause) {
            super(cause);
        }
    }

}

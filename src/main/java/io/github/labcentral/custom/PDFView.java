package io.github.labcentral.custom;

import io.github.labcentral.ResourceLoader;
import io.github.labcentral.custom.skins.PDFViewSkin;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <p>
 * A PDF Viewer javaFX Control on base on Apache PDFBox
 * this is base on Dirk Lemmermann <a href="https://github.com/dlsc-software-consulting-gmbh/PDFViewFX/">...</a>
 *  todo need to complete
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @Date 2024/1/7
 * @since 1.0
 */
public class PDFView extends Control {
    /**
     * Construct a new PDFView
     */
    public PDFView() {
        super();

        // temporary address set, need to change in feature
        getStyleClass().add(ResourceLoader.load("style/pdf-view.css"));
        setFocusTraversable(false);

    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new PDFViewSkin(this);
    }

    @Override
    public String getUserAgentStylesheet() {
        // temporary address set, need to change in feature
        return ResourceLoader.load("style/pdf-view.css");
    }


    /**
     * A flag that controls whether we always want to show the entire page. If "true" then the page
     * will be constantly resized to fit the viewport of the scroll pane in which it is showing. In
     * this mode zooming is not possible.
     */
    private final BooleanProperty showAll = new SimpleBooleanProperty(this, "showAll", false);

    public final boolean isShowAll() {
        return showAll.get();
    }

    public final BooleanProperty showAllProperty() {
        return showAll;
    }

    public final void setShowAll(boolean showAll) {
        this.showAll.set(showAll);
    }

    /**
     * The currently loaded and displayed PDF document.
     */
    private final ObjectProperty<Document> document = new SimpleObjectProperty<>(this, "document");

    public final ObjectProperty<Document> documentProperty() {
        return document;
    }

    public final Document getDocument() {
        return document.get();
    }

    public final void setDocument(Document document) {
        this.document.set(document);
    }


    /**
     * Loads the given PDF file.
     *
     * @param file a file containing a PDF document
     * @throws Document.DocumentProcessingException if there is an error while reading/parsing a document.
     */
    public final void load(File file) {
        Objects.requireNonNull(file, "file can not be null");
        load(() -> {
            try {
                return new PDFBoxDocument(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Loads the given PDF file.
     *
     * @param stream a stream returning a PDF document
     * @throws Document.DocumentProcessingException if there is an error while reading/parsing a document.
     */
    public final void load(InputStream stream) {
        Objects.requireNonNull(stream, "stream can not be null");
        load(() -> new PDFBoxDocument(stream));
    }

    /**
     * Sets the document retrieved from the given supplier.
     *
     * @param supplier Document supplier.
     * @throws Document.DocumentProcessingException if there is an error while reading/parsing of a document.
     */
    public final void load(Supplier<Document> supplier) {
        Objects.requireNonNull(supplier, "supplier can not be null");
        setDocument(supplier.get());
    }


    /**
     * Represent a single match in the document.
     */
    public static class SearchResult implements Comparable<SearchResult> {
        private final String searchText;
        private final String textSnippet;
        private final int pageNumber;
        private final Rectangle2D marker;

        /**
         * Constructs a new search result.
         *
         * @param searchText  the text for which was searched
         * @param textSnippet a snippet of the text found at the search hit location
         * @param pageNumber  the page where the result can be found
         * @param marker      the visual bounds of the search hit
         */
        public SearchResult(String searchText, String textSnippet, int pageNumber, Rectangle2D marker) {
            this.searchText = searchText;
            this.textSnippet = textSnippet;
            this.pageNumber = pageNumber;
            this.marker = marker;
        }

        public Rectangle2D getMarker() {
            return marker;
        }

        public Rectangle2D getScaledMarker(double scale) {
            return new Rectangle2D(marker.getMinX() * scale,
                    marker.getMinY() * scale,
                    marker.getWidth() * scale,
                    marker.getHeight() * scale);
        }

        public String getSearchText() {
            return searchText;
        }

        public String getTextSnippet() {
            return textSnippet;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        @Override
        public int compareTo(SearchResult other) {
            int result = Integer.compare(pageNumber, other.pageNumber);

            if (result == 0) {
                result = Double.compare(getMarker().getMinY(), other.getMarker().getMinY());
            }

            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            SearchResult that = (SearchResult) obj;
            return pageNumber == that.pageNumber &&
                    Objects.equals(searchText, that.searchText) &&
                    Objects.equals(textSnippet, that.textSnippet) &&
                    Objects.equals(marker, that.marker);
        }

        @Override
        public int hashCode() {
            return Objects.hash(searchText, textSnippet, pageNumber, marker);
        }
    }



}

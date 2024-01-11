package io.github.labcentral.custom;

import java.util.List;

/**
 * <p>
 * Documents that can be searched for a given text n
 * interface and return a list of search results.
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @Date 2024/1/7
 * @since 1.0
 */
public interface SearchableDocument extends Document {
    /**
     * Returns the list of search results for the given
     * search text.
     *
     * @param searchText the text for which to search
     * @return the list of search results
     */
    List<PDFView.SearchResult> getSearchResults(String searchText);
}

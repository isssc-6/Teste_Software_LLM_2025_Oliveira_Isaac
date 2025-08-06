package exemplo.wikipedia;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class WikipediaInfoTest {
    private WikipediaInfo info;
    private List<Integer> pageIds;
    private Map<Integer, Set<Integer>> outLinksMap;
    private Map<Integer, Set<Integer>> categoryArticleMap;

    @Before
    public void setUp() {
        pageIds = Arrays.asList(1, 2, 3, 4);
        info = new WikipediaInfo(pageIds);
        
        outLinksMap = new HashMap<>();
        outLinksMap.put(1, new HashSet<>(Arrays.asList(2, 3)));
        outLinksMap.put(2, new HashSet<>(Arrays.asList(3)));
        outLinksMap.put(3, new HashSet<>(Arrays.asList(4)));
        outLinksMap.put(4, new HashSet<>(Arrays.asList(1)));
        
        categoryArticleMap = new HashMap<>();
        categoryArticleMap.put(100, new HashSet<>(Arrays.asList(1, 2, 3)));
        categoryArticleMap.put(200, new HashSet<>(Arrays.asList(3, 4)));
        categoryArticleMap.put(300, new HashSet<>(Arrays.asList(5))); // article not in our pages
        info.setCategoryArticleMap(categoryArticleMap);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNullArgument() {
        new WikipediaInfo(null);
    }

    @Test
    public void testConstructor() {
        assertEquals(4, info.getNumberOfPages());
    }

    @Test
    public void testGetAverageFanOut() {
        double expected = (2 + 1 + 1 + 1) / 4.0;
        assertEquals(expected, info.getAverageFanOut(outLinksMap), 0.001);
        
        // Test caching
        assertEquals(expected, info.getAverageFanOut(outLinksMap), 0.001);
    }

    @Test
    public void testGetAverageFanOutWithEmptyPages() {
        WikipediaInfo emptyInfo = new WikipediaInfo(Collections.emptyList());
        assertEquals(0.0, emptyInfo.getAverageFanOut(outLinksMap), 0.001);
    }

    @Test
    public void testSetCategoryArticleMap() {
        Map<Integer, Set<Integer>> newMap = new HashMap<>();
        newMap.put(500, new HashSet<>(Arrays.asList(1, 4)));
        info.setCategoryArticleMap(newMap);
        assertEquals(2, info.getTotalCategorizedArticles());
    }

    @Test
    public void testGetArticlesWithOverlappingCategories() {
        // Articles 3 and 4 are in multiple categories
        // But only article 3 is in our pages list (article 5 is not in our pages)
        assertEquals(1, info.getArticlesWithOverlappingCategories());
    }

    @Test
    public void testGetDistributionOfArticlesByCategory() {
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(3, 1); // category 100 has 3 articles
        expected.put(2, 1); // category 200 has 2 articles
        expected.put(1, 1); // category 300 has 1 article
        
        assertEquals(expected, info.getDistributionOfArticlesByCategory());
    }

    @Test
    public void testGetTotalCategorizedArticles() {
        // Articles in our pages that are categorized: 1, 2, 3, 4
        // Article 5 is not in our pages
        assertEquals(4, info.getTotalCategorizedArticles());
    }

    @Test
    public void testGetNumberOfPages() {
        assertEquals(4, info.getNumberOfPages());
    }
}
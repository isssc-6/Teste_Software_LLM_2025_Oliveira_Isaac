package exemplo.wikipedia;

import java.util.*;

public class WikipediaInfo {
    private Iterable<Integer> pages;
    private int numberOfPages;
    private double averageFanOut;
    private Map<Integer, Set<Integer>> categoryArticleMap;

    public WikipediaInfo(Iterable<Integer> pageIds) {
        if (pageIds == null) throw new IllegalArgumentException("O conjunto de páginas não pode ser nulo.");

        this.pages = pageIds;
        this.numberOfPages = 0;
        for (Integer ignored : pages) this.numberOfPages++;
        this.averageFanOut = -1.0;
        this.categoryArticleMap = new HashMap<>();
    }

    public double getAverageFanOut(Map<Integer, Set<Integer>> outLinksMap) {
        if (averageFanOut >= 0.0) return averageFanOut;

        Set<Integer> pageIDs = new HashSet<>();
        for (Integer id : pages) pageIDs.add(id);

        int fanOutCounter = 0;
        for (Map.Entry<Integer, Set<Integer>> entry : outLinksMap.entrySet()) {
            if (pageIDs.contains(entry.getKey())) {
                fanOutCounter += entry.getValue().size();
            }
        }

        averageFanOut = numberOfPages == 0 ? 0.0 : (double) fanOutCounter / numberOfPages;
        return averageFanOut;
    }

    public void setCategoryArticleMap(Map<Integer, Set<Integer>> map) {
        this.categoryArticleMap = map;
    }

    public int getArticlesWithOverlappingCategories() {
        Set<Integer> overlappingArticles = new HashSet<>();
        List<Integer> keys = new ArrayList<>(categoryArticleMap.keySet());
        Collections.sort(keys);

        for (int i = 0; i < keys.size(); i++) {
            int outer = keys.get(i);
            Set<Integer> outerPages = categoryArticleMap.get(outer);
            for (int j = i + 1; j < keys.size(); j++) {
                int inner = keys.get(j);
                Set<Integer> innerPages = categoryArticleMap.get(inner);
                for (Integer page : outerPages) {
                    if (innerPages.contains(page)) overlappingArticles.add(page);
                }
            }
        }

        return overlappingArticles.size();
    }

    public Map<Integer, Integer> getDistributionOfArticlesByCategory() {
        Map<Integer, Integer> distribution = new HashMap<>();
        for (Set<Integer> pages : categoryArticleMap.values()) {
            int size = pages.size();
            distribution.put(size, distribution.getOrDefault(size, 0) + 1);
        }
        return distribution;
    }

    public int getTotalCategorizedArticles() {
        Set<Integer> allArticles = new HashSet<>();
        for (Set<Integer> pages : categoryArticleMap.values()) {
            allArticles.addAll(pages);
        }
        return allArticles.size();
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public static void main(String[] args) {
        // Simulação de páginas
        List<Integer> paginas = Arrays.asList(1, 2, 3, 4);
        WikipediaInfo info = new WikipediaInfo(paginas);

        // Simulação de links de saída por página
        Map<Integer, Set<Integer>> outLinks = new HashMap<>();
        outLinks.put(1, new HashSet<>(Arrays.asList(2, 3)));
        outLinks.put(2, new HashSet<>(Arrays.asList(3)));
        outLinks.put(3, new HashSet<>(Arrays.asList(4)));
        outLinks.put(4, new HashSet<>(Arrays.asList(1)));

        double fanOut = info.getAverageFanOut(outLinks);
        System.out.println("Fan-out médio: " + fanOut);

        // Simulação de categorias
        Map<Integer, Set<Integer>> categorias = new HashMap<>();
        categorias.put(100, new HashSet<>(Arrays.asList(1, 2, 3)));
        categorias.put(200, new HashSet<>(Arrays.asList(3, 4)));

        info.setCategoryArticleMap(categorias);

        int sobrepostos = info.getArticlesWithOverlappingCategories();
        System.out.println("Artigos com categorias sobrepostas: " + sobrepostos);

        int totalCategorizados = info.getTotalCategorizedArticles();
        System.out.println("Total de artigos categorizados: " + totalCategorizados);

        Map<Integer, Integer> distribuicao = info.getDistributionOfArticlesByCategory();
        System.out.println("Distribuição de artigos por tamanho de categoria:");
        for (Map.Entry<Integer, Integer> entry : distribuicao.entrySet()) {
            System.out.println("Tamanho " + entry.getKey() + ": " + entry.getValue() + " categorias");
        }
    }
}

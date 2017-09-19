package project.test;

import project.entity.Movies;
import project.query.Indexer;
import project.query.Searcher;

import java.io.File;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        String indexPath = "index/";
        File file = new File(indexPath);
        if (!file.exists()) {
            Indexer indexer = new Indexer();
            indexer.createIndex();
        }

        List<Movies> list;
        Searcher searcher = new Searcher();
        list = searcher.search("another night under the starry sky");
        if (!list.isEmpty()) {
            for (Movies m : list) {
                System.out.println(m.getTitle() + "\t" + m.getYear());
            }
        } else {
            System.out.println("SORRY");
        }

    }

}

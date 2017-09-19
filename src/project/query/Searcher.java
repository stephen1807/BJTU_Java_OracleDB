package project.query;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import project.entity.Movies;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Searcher {

    public List<Movies> search(String searchContent) {
        List<Movies> movieList = new ArrayList<Movies>();
        Directory indexDirectory = null;
        try {
            indexDirectory = FSDirectory.open(new File("index/"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        BooleanQuery booleanQuery = new BooleanQuery();
        Query query = null;


        try {
            query = MultiFieldQueryParser.parse(new String[]{searchContent},
                    new String[]{"title"}, new BooleanClause.Occur[]{
                            BooleanClause.Occur.MUST}, new StandardAnalyzer());
            booleanQuery.add(query, BooleanClause.Occur.MUST);
            DirectoryReader diretoryReader = DirectoryReader
                    .open(indexDirectory);
            IndexSearcher indexSearcher = new IndexSearcher(diretoryReader);
            TopScoreDocCollector collector = TopScoreDocCollector.create(10,
                    true);
            indexSearcher.search(booleanQuery, collector);
            ScoreDoc[] docs = collector.topDocs(0, collector.getTotalHits()).scoreDocs;
            for (ScoreDoc doc : docs) {
                Document document = indexSearcher.doc(doc.doc);
                Movies movie = new Movies(Integer.parseInt(document.get("movieid")),
                        document.get("title"), document.get("year"));
                movieList.add(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return movieList;
    }

}

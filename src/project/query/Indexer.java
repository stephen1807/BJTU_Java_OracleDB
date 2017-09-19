package project.query;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import project.entity.Movies;
import project.service.MovieService;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Indexer {

    private String indexPath = "index/";

    public void createIndex() {
        try {

            Directory indexDirectory = FSDirectory.open(new File(indexPath));

            MovieService movieService = MovieService.getInstance();

            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LATEST, getAnalyzer());
            IndexWriter indexwriter = new IndexWriter(indexDirectory,
                    indexWriterConfig);

            for (int i = 0; ; i += 1000) {

                List<Movies> rs = movieService.getMovies(i, 1000);

                for (Movies m : rs) {
                    Document doc = new Document();
                    doc.add(new TextField("movieid", String.valueOf(m.getMovieid()),
                            Field.Store.YES));
                    doc.add(new TextField("title", m.getTitle(),
                            Field.Store.YES));
                    doc.add(new TextField("year", m.getYear(),
                            Field.Store.YES));
                    indexwriter.addDocument(doc);
                }

                if (rs.size() != 1000) {
                    break;
                }
            }

            indexwriter.forceMerge(1);
            indexwriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Analyzer getAnalyzer() {
        return new StandardAnalyzer();
    }

    public boolean checkIndex() {
        File file = new File(indexPath);
        return file.exists();
    }

    public void recreateIndex() {
        File file = new File(indexPath);
        deleteFolder(file);
        createIndex();
    }

    private void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }
}

package project.util;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Stephen on 2014/12/26.
 */
public class WordProcessor {

    private static WordProcessor instance = null;
    private List<String> stopwords;
    private List<String> mustwords;

    private WordProcessor() {

        stopwords = new ArrayList<String>();
        mustwords = new ArrayList<String>();

        InputStream is;
        BufferedReader br;

        try {
            is = new FileInputStream("resource/stopwords.txt");
            br = new BufferedReader(new InputStreamReader(is));

            String line;

            while ((line = br.readLine()) != null) {
                stopwords.add(line);
            }

            is = new FileInputStream("resource/mustwords.txt");
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                mustwords.add(line);
            }
            br.close();
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WordProcessor getInstance() {
        if (instance == null) {
            instance = new WordProcessor();
        }
        return instance;
    }

    public List<String> process(String content) {

        List<String> result = new ArrayList<String>();

        Analyzer analyzer = new Analyzer() {
            @Override
            protected TokenStreamComponents createComponents(String s, Reader reader) {
                StandardTokenizer source = new StandardTokenizer(reader);
                TokenStream filter = new StopFilter(source, StopFilter.makeStopSet(stopwords, true));
                filter = new LowerCaseFilter(filter);
                return new TokenStreamComponents(source, filter);
            }
        };

        try {
            TokenStream token = analyzer.tokenStream(null, new StringReader(content));
            token.reset();
            while (token.incrementToken()) {
                result.add(token.getAttribute(CharTermAttribute.class).toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int checkKeywords(String content) {

        int result = 0;

        for (String m : mustwords) {

            Pattern p = Pattern.compile(m);

            Matcher ma = p.matcher(content);

            if (ma.find()) {
                result++;
            }
        }

        return result;
    }

    public String list2String(List<String> list) {

        String result = "";

        if (list.isEmpty()) return result;

        for (String i : list) {
            result += ' ' + i;
        }

        return result;
    }
}

/*
 * SearchEngine.java
 *
 * Created on 6 March 2006, 14:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ruking.utils;

import java.io.File;
import java.io.IOException;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class SearchEngine {
    private IndexSearcher searcher = null;
    private QueryParser parser = null;
    
    /** Creates a new instance of SearchEngine */
    public SearchEngine() throws IOException {
        searcher = new IndexSearcher(FSDirectory.open(new File("ruking-index")));
        parser = new QueryParser(Version.LUCENE_34,"content", new StandardAnalyzer(Version.LUCENE_34));
    }
    
    public TopDocs performSearch(String queryString)
    throws IOException, ParseException {
        Query query = parser.parse(queryString);        
        TopDocs hits = searcher.search(query,null,10);
        return hits;
    }

	public IndexSearcher getSearcher() {
		return searcher;
	}

	public void setSearcher(IndexSearcher searcher) {
		this.searcher = searcher;
	}
    
}
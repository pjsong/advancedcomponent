package ruking.search;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.lucene.document.Field;
import org.apache.lucene.document.Document;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import ruking.db.DataSourceFactory;
import ruking.db.DbUtil;
import ruking.db.MDTMySQLRowMapper;
import ruking.db.TransRunner;


public class Indexer {
    public Indexer() {
    }
    private IndexWriter indexWriter = null;
    
    public IndexWriter getIndexWriter(String fileDir,boolean create) throws IOException {
        if (indexWriter == null) {
            StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_34);
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_34, analyzer);
            File indexDir = new File(fileDir);
            if(indexDir.exists())
            {
            	File[] files = indexDir.listFiles();
            	for(File f:files){
            		f.delete();
            	}
            }
            FSDirectory dir = FSDirectory.open(indexDir);
            indexWriter = new IndexWriter(dir,config);
        }
        return indexWriter;
   }    
   
    public void closeIndexWriter() throws IOException {
        if (indexWriter != null) {
            indexWriter.close();
            indexWriter = null;
        }
   }
    
    public void indexProduct(Map<String,String> map) throws IOException {
    	String pid = map.get("id");
    	String title = map.get("Title");
    	String content = map.get("content");
        System.out.println("Indexing Products: " + pid);
        IndexWriter writer = getIndexWriter("ruking-index",false);
        Document doc = new Document();
        doc.add(new Field("id", pid, Field.Store.YES, Field.Index.NO));
        doc.add(new Field("title", title, Field.Store.YES, Field.Index.NO));
        doc.add(new Field("content", content, Field.Store.NO, Field.Index.ANALYZED));
        writer.addDocument(doc);
    }   
    
    public void rebuildIndexes(String lang) throws IOException, SQLException {
          //
          // Erase existing index
    	 String fileName = "ruking-index";
    	 if(!lang.equals("")){
    		fileName = fileName +"_"+lang;
    	 }
          getIndexWriter(fileName, true);
          // Index all Accommodation entries
          List<Map> products = getAllProducts(lang);
          for(Map map : products) {
              indexProduct(map);              
          }
          closeIndexWriter();
     }    
    
  private List<Map> getAllProducts(String lang) throws SQLException{
	  List<Map> ret = new ArrayList<Map>();
	  	if(!lang.equals(""))lang="_"+lang;
		String sql = "select * from product"+lang;
        TransRunner runner = new TransRunner(DataSourceFactory.getDataSource("localhost","zkm0m1_db","root", "ChinacaT"), new MDTMySQLRowMapper());
		List<Map> row = runner.query(sql);
		if(row==null || row.size()==0)return null;
		StringBuffer sb = new StringBuffer();
		for(Map map:row){
		    Map<String,String> element = new HashMap<String,String>();
			sb.append((String)map.get("Title")+" ");
			sb.append((String)map.get("Description")+" ");
			sb.append((String)map.get("Category")+" ");
			sb.append((String)map.get("SubCategory")+" ");
			Integer pid = (Integer)map.get("ID");
			String sql1 = "select * from attributes"+lang+" where ProductID="+DbUtil.escSql(pid);
			Map attrMap = runner.queryForMap(sql1);
			Iterator it = map.entrySet().iterator();
			while (it.hasNext())
			{
			    Map.Entry entry = (Map.Entry) it.next();
			    String name = (String) entry.getKey();
			    Object value = entry.getValue();
			    sb.append(value+" ");
		   }
			element.put("content", sb.toString());
			element.put("id", pid.toString());
			element.put("Title", (String)map.get("Title"));
			ret.add(element);
			sb.delete(0, sb.length()-1);

		}
		return ret;
  }
    public static void main(String[] args) throws IOException, SQLException{
        Indexer  indexer = new Indexer();
        indexer.rebuildIndexes("");
        System.out.println("rebuild Indexes done");
        indexer.rebuildIndexes("big");
        System.out.println("rebuild big done");
        indexer.rebuildIndexes("eng");
        System.out.println("rebuild eng done");
        indexer.closeIndexWriter();
    }
    
}

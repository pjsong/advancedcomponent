import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import ruking.dao.ProductDAO;

public class SiteMapGen {
	
	static PrintWriter pw;
	static {
		try {
			File f = new File("sitemap.xml");
			if(f.exists())
			{
				f.delete();
			}
			pw = new PrintWriter("sitemap.xml");
		} catch (FileNotFoundException e) {
		}
	}

	private static void writeHeader(){
		String xmlns = "xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\"";
		String xmlns_xsi = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
		String xsi_schema = "xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\"";
		pw.println("<urlset " + xmlns+" "+xmlns_xsi+" "+xsi_schema+">");
	}
	private static void writeFooter(){
		pw.println("</urlset>");
	}
	private static void writeNode(String loc,String priority){
		if(loc==null || loc.equals(""))return;
		pw.println("<url>");
		pw.println("<loc>http://www.ruking-cn.com"+loc+"</loc>");
		if(priority!=null && !priority.equals(""))
		{
			pw.println("<priority>"+priority+"</priority>");
		}
		pw.println("</url>");
	}
	
	public static void WriteFile() throws IOException, SQLException{
		writeHeader();
		writeNode("/","0.7");
		ProductDAO pdao = new ProductDAO();
		List<Map> lm = pdao.getAllProducts("");
		for(Map m:lm){
			Integer id = (Integer)m.get("ID");
			writeNode("/item.jhtml?id="+id,"0.7");
		}
		List<Map> lm_big = pdao.getAllProducts("big");
		for(Map m:lm_big){
			Integer id = (Integer)m.get("ID");
			writeNode("/item_big.jhtml?id="+id,"0.7");
		}
		List<Map> lm_eng = pdao.getAllProducts("eng");
		for(Map m:lm){
			Integer id = (Integer)m.get("ID");
			writeNode("/item_eng.jhtml?id="+id,"0.7");
		}
		writeNode("/products.jhtml","0.9");
		writeNode("/about/customerservice.jhtml","0.6");
		for(int i=1;i<5;i++){
			writeNode("/about/aboutus.jhtml?id="+i,"0.6");
			writeNode("/about/aboutus_big.jhtml?id="+i,"0.6");
			writeNode("/about/aboutus_eng.jhtml?id="+i,"0.6");
		}
		for(int i=1;i<3;i++){
			writeNode("/about/customerservice.jhtml?id="+i,"0.5");
			writeNode("/about/customerservice_big.jhtml?id="+i,"0.5");
		}
		for(int i=1;i<3;i++){
			writeNode("/about/recruit.jhtml?id="+i,"0.6");
			writeNode("/about/recruit_big.jhtml?id="+i,"0.6");
		}
		writeNode("/geo.html","0.5");
		writeFooter();
	}
	
	public static void main(String args[]) throws IOException, SQLException{
		WriteFile();
		pw.close();
	}
}

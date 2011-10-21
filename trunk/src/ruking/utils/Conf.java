package ruking.utils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Conf {
	   private Properties prp = null;
	   private synchronized void checkAndLoadPrp()
	   {

			InputStream is;
			try {
				is = getClass().getResourceAsStream("/conf/dbconfig.propertie");
				prp = new Properties();
				prp.load(is);
			}catch(IOException e){}
			catch(Exception e){
	            is = getClass().getResourceAsStream(
                "dbconfig.propertie");
			}
//		   File f = new File(getClass().getResource("dbconfig.properties").getPath());
//	       if (!f.exists())
//	       {
//	    	   throw new IOException("dl.prp not found");
//	       }
//    	   prp = new Properties();
//    	   prp.load(new FileInputStream(f));
	   }
	   public String getDbName() throws IOException{
		   if(prp==null)checkAndLoadPrp();
		   return prp.getProperty("dbuser");
		   }
	   public String getDbPassword() throws IOException{
		   if(prp==null)checkAndLoadPrp();
		   return prp.getProperty("dbpassword");}
}

package ruking.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Conf {
	   private Properties prp = null;
	   private synchronized void checkAndLoadPrp() throws IOException
	   {
		   
	       File f = new File(getClass().getResource("/dbconfig.properties").getPath());
	       if (!f.exists())
	       {
	    	   throw new IOException("dl.prp not found");
	       }
	       prp = new Properties();
    	   prp = new Properties();
    	   prp.load(new FileInputStream(f));
	   }
	   public String getDbName(){return prp.getProperty("dbuser");}
	   public String getDbPassword(){return prp.getProperty("dbpassword");}
}

package test.client;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import com.gwtext.client.widgets.Panel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwt_ext implements EntryPoint {
	   public void onModuleLoad() {
		      Panel mainPanel = new Panel() {
		         {
		            setTitle("Hello World!");
		            setHeight(300);
		            setWidth(500);
		            setFrame(true);
		            setHtml("<p>This is my first GWT-Ext webpage and it was a breeze to design!</p>");
		            setStyle("margin: 10px 0px 0px 10px;");
		         }
		      };
		 
		      RootPanel.get().add(mainPanel);
		   }

}

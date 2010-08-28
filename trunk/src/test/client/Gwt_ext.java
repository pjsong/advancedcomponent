package test.client;
import test.client.imageResource.StartMenu;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import com.gwtext.client.core.Margins;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Viewport;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.menu.Item;
import com.gwtext.client.widgets.menu.Menu;
import com.gwtext.client.widgets.menu.MenuItem;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwt_ext implements EntryPoint {
	   UServiceGet u= new UServiceGet();
	   String a;
	   public void onModuleLoad() {
		    a=u.getGoogleLoginURL();
		    Window.alert(a);
			new Viewport(showBasicBorderLayout());
	   }
	   
		 private void setStartMenuButton(Panel panel){
			 StartMenu INSTANCE = GWT.create(StartMenu.class);
			 final Button button = new Button();  
			 button.setText("Start");  
             button.setIcon(INSTANCE.menu_parent().getURL());

			 //create the menu we want to assign to the button  
			 Menu menu = new Menu();  

			 Item wordItem = new Item("GOOGLE LOGIN");  
			 wordItem.setHref(a);	
//			 Window.alert(wordItem.getHref());
			 UServiceAsync uservice = GWT.create(UService.class);
			 uservice.getGoogleUserLoginURL(new AsyncCallback<String>(){
				public void onFailure(Throwable caught) {
				}
				public void onSuccess(final String result) {
//					GWT.runAsync(new RunAsyncCallback(){
//						public void onFailure(Throwable reason) {
//						}
//						public void onSuccess() {
//							wordItem.setHref("fdsfdsfsdf");	
//							Window.alert(wordItem.getHref());

						}
				});
//			 }
//			 }
//		 );
			 wordItem.setHrefTarget("blank");
			 wordItem.setIcon(INSTANCE.page_find().getURL());  
			 menu.addItem(wordItem);  

			 Item excelItem = new Item("SITE LOGIN");  
			 excelItem.setIcon(INSTANCE.user().getURL());  
			 excelItem.disable();
			 menu.addItem(excelItem);  

			 //create a sub menu  
			 Menu subMenu = new Menu();  
			 Item cItem = new Item("GOOGLE");  		
			 cItem.setHref("http://www.google.com.hk");
			 wordItem.setHrefTarget("blank");
			 cItem.setIconCls(INSTANCE.page_find().getURL()); 

			 Item cppItem = new Item("C++");  
			 cppItem.setIconCls("cpp-icon");  

			 Item csharpItem = new Item("CSharp");  
			 csharpItem.setIconCls("csharp-icon");  

			 subMenu.addItem(cItem);  
			 subMenu.addItem(cppItem);  
			 subMenu.addItem(csharpItem);  

			 //add menu item that has sub menu  
			 MenuItem vsItem = new MenuItem("USEFUL SITE", subMenu);  
			 vsItem.setIcon(INSTANCE.expandall().getURL());  
			 menu.addItem(vsItem);  

			 Item powerPointItem = new Item("PowerPoint");  
			 powerPointItem.setIconCls("powerpoint-icon");  
			 menu.addItem(powerPointItem);  

			 //assign the menu to to the button  
			 button.setMenu(menu);  
			 Panel horizontalPanel = new Panel();  
			 horizontalPanel.setLayout(new HorizontalLayout(15));  

			 horizontalPanel.add(button);  

			 panel.add(horizontalPanel);  
		 }
	   	   private void setNorthPanel(Panel borderPanel){
		     //add north panel  
		     Panel northPanel = new Panel();  
		     northPanel.setHtml("<p center>north panel</p>");  
		     northPanel.setHeight(100);  
		     northPanel.setBodyStyle("background-color:#FFFF88");  
//		     northPanel.setTitle("North");
		     setStartMenuButton(northPanel);
		     BorderLayoutData northData = new BorderLayoutData(RegionPosition.NORTH);  
		     borderPanel.add(northPanel, northData);  
	   }
	   

	   private void setSouthPanel(Panel borderPanel){
		     Panel southPanel = new Panel();  
		     southPanel.setHtml("<p>south panel</p>");  
		     southPanel.setHeight(100);  
		     southPanel.setBodyStyle("background-color:#CDEB8B");  
		     southPanel.setCollapsible(true);  
		     southPanel.setTitle("South");  

		     BorderLayoutData southData = new BorderLayoutData(RegionPosition.SOUTH);  
		     southData.setMinSize(100);  
		     southData.setMaxSize(200);  
		     southData.setMargins(new Margins(0, 0, 0, 0));  
		     southData.setSplit(true);  
		     borderPanel.add(southPanel, southData);  
	   }
	   
	   
	   private void setEastPanel(Panel borderPanel){
		     Panel eastPanel = new Panel();  
		     eastPanel.setHtml("<p>east panel</p>");  
		     eastPanel.setTitle("East Side");  
		     eastPanel.setCollapsible(true);  
		     eastPanel.setWidth(225);  

		     BorderLayoutData eastData = new BorderLayoutData(RegionPosition.EAST);  
		     eastData.setSplit(true);  
		     eastData.setMinSize(175);  
		     eastData.setMaxSize(400);  
		     eastData.setMargins(new Margins(0, 0, 5, 0));  

		     borderPanel.add(eastPanel, eastData);  
	   }

	   private void setWestPanel(Panel borderPanel){
		     Panel westPanel = new Panel();  
		     westPanel.setHtml("<p>west panel</p>");  
		     westPanel.setTitle("West");  
		     westPanel.setBodyStyle("background-color:#EEEEEE");  
		     westPanel.setCollapsible(true);  
		     westPanel.setWidth(200);  

		     BorderLayoutData westData = new BorderLayoutData(RegionPosition.WEST);  
		     westData.setSplit(true);  
		     westData.setMinSize(175);  
		     westData.setMaxSize(400);  
		     westData.setMargins(new Margins(0, 5, 0, 0));  

		     borderPanel.add(westPanel, westData);  
	   }
	   private void setCenterPanel(Panel borderPanel){
		     Panel centerPanel = new Panel();  
		     centerPanel.setHtml("<p>center panel</p>");  
		     centerPanel.setBodyStyle("background-color:#C3D9FF");  

		     borderPanel.add(centerPanel, new BorderLayoutData(RegionPosition.CENTER));  
	   }

	   
	   
	   protected Panel showBasicBorderLayout(){
		     Panel panel = new Panel();  
		     panel.setBorder(false);  
		     panel.setPaddings(15);  
		     panel.setLayout(new FitLayout());  

		     Panel borderPanel = new Panel();  
		     borderPanel.setLayout(new BorderLayout());  
		     
		     setNorthPanel(borderPanel);
		     setSouthPanel(borderPanel);
		     setEastPanel(borderPanel);
		     setWestPanel(borderPanel);
		     setCenterPanel(borderPanel);

		     panel.add(borderPanel);  
             return panel;
	   }
	   
	  
}

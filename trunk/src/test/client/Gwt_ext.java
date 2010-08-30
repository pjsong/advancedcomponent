package test.client;
import test.client.imageResource.StartMenu;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Margins;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Viewport;
import com.gwtext.client.widgets.event.ButtonListener;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.TimeField;
import com.gwtext.client.widgets.form.VType;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.menu.BaseItem;
import com.gwtext.client.widgets.menu.CheckItem;
import com.gwtext.client.widgets.menu.Item;
import com.gwtext.client.widgets.menu.Menu;
import com.gwtext.client.widgets.menu.MenuItem;
import com.gwtext.client.widgets.menu.event.BaseItemListenerAdapter;
import com.gwtext.client.widgets.menu.event.CheckItemListenerAdapter;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwt_ext implements EntryPoint {
	 UServiceAsync uservice = GWT.create(UService.class);

    Panel northPanel = new Panel();  
    Panel southPanel = new Panel();  
    final Panel centerPanel = new Panel(); 
    Panel eastPanel = new Panel();  
    Panel westPanel = new Panel();  
    
    private FormPanel showSiteLogin(){
      final FormPanel formPanel = new FormPanel();  
//	  formPanel.setFrame(true);  
	  formPanel.setTitle("Simple Form");  

	  formPanel.setWidth(350);  
	  formPanel.setLabelWidth(75);  
//	  formPanel.setUrl("save-form.php");  

//	  Label label = new Label();  
//	  label.setHtml("<p>This is an example of a Form Label. This can have any <b>HTML</b> content.</p>");  
//	  label.setCls("simple-form-label");  
//	  label.setWidth(350);  
//	  label.setHeight(20);  

	  TextField firstName = new TextField("First Name", "first", 230);  
	  firstName.setAllowBlank(false);  
	  formPanel.add(firstName);  

	  TextField lastName = new TextField("Last Name", "last", 230);  
	  formPanel.add(lastName);  

	  TextField company = new TextField("Company", "company", 230);  
	  formPanel.add(company);  

	  TextField email = new TextField("Email", "email", 230);  
	  email.setVtype(VType.EMAIL);  
	  formPanel.add(email);  

	  TimeField time = new TimeField("Time", "time", 230);  
	  time.setMinValue("8:00am");  
	  time.setMaxValue("6:00pm");  
	  formPanel.add(time);  

	  Button save = new Button("Save");  
	  formPanel.addButton(save);  

	  Button cancel = new Button("Cancel");  
	  formPanel.addButton(cancel);  
	  cancel.addListener(new ButtonListenerAdapter(){
		  public void onClick(Button button, EventObject e) {
			  formPanel.hide();
		  }
	  });
	  formPanel.setVisible(true);
	  
      return formPanel;

    }
	  final BaseItemListenerAdapter listener = new BaseItemListenerAdapter() {  
	      public void onClick(BaseItem item, EventObject e){
	    	  FormPanel fp=showSiteLogin();
//	    	  centerPanel.removeAll();
	    	  centerPanel.add(fp);
	    	  fp.setVisible(true);
	    	  Window.alert(new Boolean(fp.isVisible()).toString());
//	    	  centerPanel.setHtml("fsfsdfsd");
//	          Window.alert(((Item)item).getText());
	      }  
	  };  
    
	   public void onModuleLoad() {
			new Viewport(showBasicBorderLayout());
//		   RootPanel.get("main").add(showBasicBorderLayout());
	   }
	   
		 private void setStartMenuButton(){
			 final StartMenu INSTANCE = GWT.create(StartMenu.class);
			 final Button button = new Button();  
			 button.setText("Start");  
             button.setIcon(INSTANCE.menu_parent().getURL());

			 final Menu menu = new Menu();  

			 final Item wordItem = new Item();  
			 uservice.getGoogleUserLoginURL(new AsyncCallback<String>(){
				public void onFailure(Throwable caught) {
				}
				public void onSuccess(String result) {
					 wordItem.setText("GOOGLE LOGIN");
					 wordItem.setHref(result.toString());
					 wordItem.setHrefTarget("blank");
					 wordItem.setIcon(INSTANCE.page_find().getURL());  
					 menu.addItem(wordItem);  
				}
				});


			 Item siteLoginItem = new Item("SITE LOGIN");
			 siteLoginItem.setIcon(INSTANCE.user().getURL());  
             siteLoginItem.addListener(listener);
			 menu.addItem(siteLoginItem);  

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

			 northPanel.add(horizontalPanel);  
		 }
	   	   private void setNorthPanel(Panel borderPanel){
		     //add north panel  
		     northPanel.setHtml("<p center>north panel</p>");  
		     northPanel.setHeight(100);  
		     northPanel.setBodyStyle("background-color:#FFFF88");  
		     northPanel.setTitle("You have not Loggedin yet");
		     setStartMenuButton();
		     BorderLayoutData northData = new BorderLayoutData(RegionPosition.NORTH);  
		     borderPanel.add(northPanel, northData);  
	   }
	   

	   private void setSouthPanel(Panel borderPanel){
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
		     eastPanel.setHtml("<p>east panel</p>");  
		     eastPanel.setTitle("East Side");  
		     eastPanel.setCollapsible(true);  
//		     eastPanel.setCollapsed(true);
		     eastPanel.setWidth(225);  

		     BorderLayoutData eastData = new BorderLayoutData(RegionPosition.EAST);  
		     eastData.setSplit(true);  
		     eastData.setMinSize(175);  
		     eastData.setMaxSize(400);  
		     eastData.setMargins(new Margins(0, 0, 5, 0));  
		     borderPanel.add(eastPanel, eastData);  
	   }

	   private void setWestPanel(Panel borderPanel){
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
//		     centerPanel.setEl("centerPanel");
		     centerPanel.setBodyStyle("background-color:#C3D9FF");  
//             centerPanel.setAutoScroll(true);
//		     centerPanel.add(showSiteLogin());
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

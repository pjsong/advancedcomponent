package menu.homepage.topmenu.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
// this file is not fit for uibinder
public class Topmenu extends Composite{
	  interface MyUiBinder extends UiBinder<Panel, Topmenu> {}
	  private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	  MyConstants myConstants = (MyConstants) GWT.create(MyConstants.class);
	  @UiField MenuBar topBar;
	  @UiField MenuItem peopleBusiness;
	  @UiField MenuBar peopleBusinessBar;
	  @UiField MenuItem sell;
	  @UiField MenuBar sellBar;
	  @UiField MenuItem cleanSell;
	  @UiField MenuItem sentHome;
	  public Topmenu() {
		    initWidget(uiBinder.createAndBindUi(this));

		    sentHome.setText(myConstants.sentHome());
		    sentHome.setCommand(new TopMenuCommandBuilder("sentHome").getCmd());
		    sellBar.addItem(sentHome);
		    cleanSell.setText(myConstants.cleanSell());
		    cleanSell.setCommand(new TopMenuCommandBuilder("cleanSell").getCmd());
		    sellBar.addItem(cleanSell);
		    
		    sell.setText(myConstants.sellProduct());
		    sell.setCommand(new TopMenuCommandBuilder("sellProduct").getCmd());
		    sell.setSubMenu(sellBar);
		    
		    peopleBusiness.setText(myConstants.peopleBusiness());
		    peopleBusiness.setCommand(new TopMenuCommandBuilder("peopleBusiness").getCmd());
		    peopleBusinessBar.addItem(sell);
		    peopleBusiness.setSubMenu(peopleBusinessBar);

			topBar.addItem(peopleBusiness);
		    }
	  public Widget asWidget() {
		    return this;
		  }
//	  @UiHandler("peopleBusiness")
//	  void handleClick(ClickEvent e) {
//	    Window.alert("Hello, AJAX");
//	  }
		
	public MenuBar buildMenu(){
		    MyConstants myConstants = (MyConstants) GWT.create(MyConstants.class);
		    MenuBar peopleBusiness = new MenuBar(true);
		    MenuBar sell=new MenuBar(true);
		    sell.addItem(myConstants.cleanSell(),new TopMenuCommandBuilder("cleanSell").getCmd());
		    sell.addItem(myConstants.sentHome(),new TopMenuCommandBuilder("sentHome").getCmd());
		    peopleBusiness.addItem(myConstants.sellProduct(),sell);
		    peopleBusiness.addItem(myConstants.others(),new TopMenuCommandBuilder("others").getCmd());

		    MenuBar professionFederal = new MenuBar(true);
		    professionFederal.addItem(myConstants.inputProfession(), new TopMenuCommandBuilder("inputProfession").getCmd());
		    professionFederal.addItem(myConstants.searchBusiness(), new TopMenuCommandBuilder("searchBusiness").getCmd());
		    professionFederal.addItem(myConstants.carrerInfo(), new TopMenuCommandBuilder("carrerInfo").getCmd());
		    professionFederal.addItem(myConstants.searchPeer(), new TopMenuCommandBuilder("searchPeer").getCmd());

		    MenuBar genealogy  = new MenuBar(true);
		    genealogy.addItem(myConstants.addRelative(), new TopMenuCommandBuilder("addRelative").getCmd());
		    genealogy.addItem(myConstants.searchRelative(), new TopMenuCommandBuilder("searchRelative").getCmd());
		    
		    MenuBar sayingProverb  = new MenuBar(true);
		    sayingProverb.addItem(myConstants.addSaying(), new TopMenuCommandBuilder("addSaying").getCmd());
		    sayingProverb.addItem(myConstants.searchSaying(), new TopMenuCommandBuilder("searchSaying").getCmd());
		    
		    MenuBar menu = new MenuBar();
		    menu.addItem(myConstants.peopleBusiness(), peopleBusiness);
		    menu.addItem(myConstants.professionFederal(), professionFederal);
		    menu.addItem(myConstants.Genealogy(), genealogy);
		    menu.addItem(myConstants.sayingProverb(), sayingProverb);  
		    menu.addItem(myConstants.passwordTool(), new TopMenuCommandBuilder("passwordTool").getCmd());  
		    menu.addItem(myConstants.authentication(), new TopMenuCommandBuilder("authentication").getCmd());  
		    menu.addItem(myConstants.newsOurselves(), new TopMenuCommandBuilder("newsOurselves").getCmd());  
		    return menu;
		}
	}
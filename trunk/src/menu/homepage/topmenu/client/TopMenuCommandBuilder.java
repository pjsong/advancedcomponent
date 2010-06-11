package menu.homepage.topmenu.client;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class TopMenuCommandBuilder{
	String constant;
	public TopMenuCommandBuilder(String constant) {
		super();
		this.constant = constant;
	}
	public String getConstant() {
		return constant;
	}
	public void setConstant(String constant) {
		this.constant = constant;
	}
	public Command getCmd() {
		if(constant.equals("cleanSell"))return new Command() {
			public void execute() {
				RootPanel.get("bodyArea").clear();
			    RootPanel.get("bodyArea").add(new HTML("Clean Sell"));
		        }
	     };
		if(constant.equals("sentHome"))return new Command() {
			public void execute() {
				RootPanel.get("bodyArea").clear();
			    RootPanel.get("bodyArea").add(new HTML("sent home service"));
		        }
	     };
		if(constant.equals("sellProduct"))return new Command() {
			public void execute() {
				RootPanel.get("bodyArea").clear();
			    RootPanel.get("bodyArea").add(new HTML("sell product"));
		        }
	     };
		if(constant.equals("others"))return new Command() {
			public void execute() {
				RootPanel.get("bodyArea").clear();
			    RootPanel.get("bodyArea").add(new HTML("others"));
		        }
	     };
		if(constant.equals("inputProfession"))return new Command() {
			public void execute() {
				RootPanel.get("bodyArea").clear();
			    RootPanel.get("bodyArea").add(new HTML("inputProfession"));
		        }
	     };
		if(constant.equals("searchBusiness"))return new Command() {
			public void execute() {
				RootPanel.get("bodyArea").clear();
			    RootPanel.get("bodyArea").add(new HTML("searchBusiness"));
		        }
	     };
		if(constant.equals("carrerInfo"))return new Command() {
			public void execute() {
				RootPanel.get("bodyArea").clear();
			    RootPanel.get("bodyArea").add(new HTML("carrerInfo"));
		        }
	     };
		if(constant.equals("searchPeer"))return new Command() {
			public void execute() {
				RootPanel.get("bodyArea").clear();
			    RootPanel.get("bodyArea").add(new HTML("searchPeer"));
		        }
	     };
		if(constant.equals("addRelative"))return new Command() {
			public void execute() {
				RootPanel.get("bodyArea").clear();
			    RootPanel.get("bodyArea").add(new HTML("sell product"));
		        }
	     };
		if(constant.equals("searchRelative"))return new Command() {
			public void execute() {
				RootPanel.get("bodyArea").clear();
			    RootPanel.get("bodyArea").add(new HTML("searchRelative"));
		        }
	     };
		if(constant.equals("addSaying"))return new Command() {
			public void execute() {
				RootPanel.get("bodyArea").clear();
			    RootPanel.get("bodyArea").add(new HTML("sell product"));
		        }
	     };
		if(constant.equals("searchSaying"))return new Command() {
			public void execute() {
				RootPanel.get("bodyArea").clear();
			    RootPanel.get("bodyArea").add(new HTML("searchSaying"));
		        }
	     };
		if(constant.equals("peopleBusiness"))return new Command() {
			public void execute() {
				RootPanel.get("bodyArea").clear();
			    RootPanel.get("bodyArea").add(new HTML("peopleBusiness"));
		        }
	     };
		if(constant.equals("professionFederal"))return new Command() {
			public void execute() {
				RootPanel.get("bodyArea").clear();
			    RootPanel.get("bodyArea").add(new HTML("professionFederal"));
		        }
	     };
		if(constant.equals("Genealogy"))return new Command() {
			public void execute() {
				RootPanel.get("bodyArea").clear();
			    RootPanel.get("bodyArea").add(new HTML("Genealogy"));
		        }
	     };
		if(constant.equals("sayingProverb"))return new Command() {
			public void execute() {
				RootPanel.get("bodyArea").clear();
			    RootPanel.get("bodyArea").add(new HTML("sayingProverb"));
		        }
	     };
		if(constant.equals("passwordTool"))return new Command() {
			public void execute() {
				RootPanel.get("bodyArea").clear();
			    RootPanel.get("bodyArea").add(new HTML("passwordTool"));
		        }
	     };
		if(constant.equals("authentication"))return new Command() {
			public void execute() {
		        Window.alert("authentication");
		        }
	     };
		if(constant.equals("newsOurselves"))return new Command() {
			public void execute() {
				RootPanel.get("bodyArea").clear();
			    RootPanel.get("bodyArea").add(new HTML("news Ourselves"));
		        }
	     };

		else
			return new Command() {
			public void execute() {
			        Window.alert("Menu item have been selected");
			        }
		};
	}
}

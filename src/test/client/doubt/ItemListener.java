package test.client.doubt;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Window;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Component;
import com.gwtext.client.widgets.menu.BaseItem;
import com.gwtext.client.widgets.menu.event.BaseItemListener;

public class ItemListener implements BaseItemListener{

	public void onActivate(BaseItem item) {
		// TODO Auto-generated method stub
		
	}

	public void onClick(BaseItem item, EventObject e) {
//		Window.open("http://www.google.com.hk", "", "");
		Window.alert("fdsfs");
		
	}

	public void onDeactivate(BaseItem item) {
		// TODO Auto-generated method stub
		
	}

	public boolean doBeforeDestroy(Component component) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean doBeforeHide(Component component) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean doBeforeRender(Component component) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean doBeforeShow(Component component) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean doBeforeStateRestore(Component component,
			JavaScriptObject state) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean doBeforeStateSave(Component component, JavaScriptObject state) {
		// TODO Auto-generated method stub
		return false;
	}


	public void onDestroy(Component component) {
		// TODO Auto-generated method stub
		
	}


	public void onDisable(Component component) {
		// TODO Auto-generated method stub
		
	}


	public void onEnable(Component component) {
		// TODO Auto-generated method stub
		
	}


	public void onHide(Component component) {
		// TODO Auto-generated method stub
		
	}


	public void onRender(Component component) {
		// TODO Auto-generated method stub
		
	}


	public void onShow(Component component) {
		// TODO Auto-generated method stub
		
	}


	public void onStateRestore(Component component, JavaScriptObject state) {
		// TODO Auto-generated method stub
		
	}


	public void onStateSave(Component component, JavaScriptObject state) {
		// TODO Auto-generated method stub
		
	}

}

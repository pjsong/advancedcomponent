package org.gwt.advanced.client;


import org.gwt.advanced.client.datamodel.Editable;
import org.gwt.advanced.client.datamodel.EditableGridDataModel;
import org.gwt.advanced.client.ui.widget.EditableGrid;
import org.gwt.advanced.client.ui.widget.GridPanel;
import org.gwt.advanced.client.ui.widget.cell.LabelCell;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class AdvancedComponent implements EntryPoint {

	public void onModuleLoad() {
		Editable model = new EditableGridDataModel(
			    new Object[][] {
			        new String[]{"John", "Doe"},
			        new String[]{"Piter", "Walkman"},
			        new String[]{"Rupert", "Brown"}
			    }
			);
		GridPanel panel = new GridPanel();
	    panel.createEditableGrid(
	        new String[]{"First Name", "Surname"},
	        new Class[]{LabelCell.class, LabelCell.class},
	        null
	    ).setModel(model);


		// display all
		panel.display();

		RootPanel.get().add(panel);

	}

}

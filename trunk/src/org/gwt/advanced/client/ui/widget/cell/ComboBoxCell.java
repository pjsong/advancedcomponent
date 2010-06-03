/*
 * Copyright 2010 Sergey Skladchikov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gwt.advanced.client.ui.widget.cell;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import org.gwt.advanced.client.datamodel.ComboBoxDataModel;
import org.gwt.advanced.client.datamodel.ListDataModel;
import org.gwt.advanced.client.ui.widget.ComboBox;
import org.gwt.advanced.client.util.GWTUtil;

/**
 * This is a cell implementation that must contain a combo box.
 *
 * @author <a href="mailto:sskladchikov@gmail.com">Sergey Skladchikov</a>
 * @since 1.2.0
 */
public class ComboBoxCell extends AbstractCell {
    /** handler registration for combo box click (valid for FF only) */
    private HandlerRegistration ffHandlerRegistration;

    /** {@inheritDoc} */
    public boolean valueEqual(Object value) {
        return false; // because it will be always the same widget
    }

    /** {@inheritDoc} */
    @SuppressWarnings({"unchecked"})
    public void setValue(Object value) {
        if (value == null) {
            value = new ComboBox<ComboBoxDataModel>();
            ComboBoxDataModel model = new ComboBoxDataModel();
            model.add("---", "---");
            ((ComboBox<ComboBoxDataModel>)value).setModel(model);
        } else if (value instanceof ComboBoxDataModel) {
            ComboBox<ComboBoxDataModel> box = new ComboBox<ComboBoxDataModel>();
            box.setModel((ComboBoxDataModel) value);
            value = box;
        }
        super.setValue(value);
    }

    /** {@inheritDoc} */
    protected Widget createActive() {
        removeStyleName("list-cell");
        ComboBox box = (ComboBox) getValue();
        if (box == null)
            removeStyleName("active-cell");
        else
            box.setWidth("100%");
        return box;
    }

    /** {@inheritDoc} */
    protected Widget createInactive() {
        ComboBox comboBox = (ComboBox) getValue();
        if (comboBox != null && comboBox.isListPanelOpened())
            comboBox.setListPopupOpened(false);
        return getComboBoxWidget(comboBox);
    }

    /** {@inheritDoc} */
    public void setFocus(boolean focus) {
        final ComboBox box = (ComboBox) getValue();
        if (focus && box != null && !box.isListPanelOpened()) {
            box.showList(true);
        }
        if (box != null) {
            // workaround for FF since this browser propagates click event every time
            // the cell is activated on enter key press and as result the box closes the drop down list
            // opened before
            if (GWTUtil.isFF()) {
                ffHandlerRegistration = box.addClickHandler(new ClickHandler(){
                    public void onClick(ClickEvent event) {
                        box.showList(true);
                        if (ffHandlerRegistration != null) {
                            ffHandlerRegistration.removeHandler();
                            ffHandlerRegistration = null;
                        }
                    }
                });
            }
            box.setFocus(focus);
        }
    }

    /** {@inheritDoc} */
    public Object getNewValue() {
        return getValue();
    }

    /**
     * This method returns a widget for the inactive combo box.
     *
     * @param comboBox is a combo box.
     *
     * @return a widget to be extracted from the combo box.
     */
    protected Widget getComboBoxWidget (ComboBox comboBox) {
        Widget widget = new Label();
        if (comboBox != null) {
            int index;

            ListDataModel model = comboBox.getModel();
            index = model.getSelectedIndex();

            if (index != -1)
                widget = comboBox.getListItemFactory().createWidget(model.get(index));
            else if (model.getCount() > 0)
                widget = comboBox.getListItemFactory().createWidget(model.get(0));
            addStyleName("list-cell");
        }
        return widget;
    }
}

/*
 * $Id: ListAdaptor.java,v 1.3 2005/10/10 18:01:33 rbair Exp $
 *
 * Copyright 2004 Sun Microsystems, Inc., 4150 Network Circle,
 * Santa Clara, California 95054, U.S.A. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package sipus.util;

import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

/**
 * An implementation of the AbstractComponentAdaptor that is suitable for a
 * JList in conjunction with a JTextComponent.
 * @author Thomas Bierhance
 */
public class ListAdaptor extends AbstractComponentAdaptor implements ListSelectionListener {

    /** the list containing the items */
    JList list;
    JTable table;
    /** the text component that is used for automatic completion*/
    JTextComponent textComponent;
    DefaultTableModel model = new DefaultTableModel();

    /**
     * Creates a new JListAdaptor for the given list and text component.
     * @param table the list that contains the items that are used for automatic
     * completion
     * @param textComponent the text component that will be used automatic
     * completion
     */
    public ListAdaptor(JList list, JTextComponent textComponent) {
        this.list = list;
        this.textComponent = textComponent;
        // when a new item is selected set and mark the text
        list.addListSelectionListener(this);
    }

    public ListAdaptor(JTable table, JTextComponent textComponent) {
        this.table = table;
        this.textComponent = textComponent;
        // when a new item is selected set and mark the text
        table.getSelectionModel().addListSelectionListener(this);
    }

    /**
     * Implementation side effect - do not invoke.
     * @param listSelectionEvent -
     */
    // ListSelectionListener (listening to list)
    public void valueChanged(javax.swing.event.ListSelectionEvent listSelectionEvent) {
        try {
            // set the text to the currently selected item
            getTextComponent().setText(list.getSelectedValue().toString());
            // mark the entire text
            markEntireText();
        } catch (NullPointerException ex) {
        }
    }

    public Object getSelectedItem() {        
        return list.getSelectedValue();
    }

    public int getItemCount() {
        return list.getModel().getSize();
    }

    public Object getItem(int index) {
        return list.getModel().getElementAt(index);
    }

    public void setSelectedItem(Object item) {
        list.setSelectedValue(item, true);
    }

    public JTextComponent getTextComponent() {
        return textComponent;
    }
}

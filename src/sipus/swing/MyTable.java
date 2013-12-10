/*
 * Copyright (C) 2011 Dimas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package sipus.swing;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import sipus.table.render.ButtonRender;

/**
 *
 * @author Dimas
 */
public final class MyTable extends JTable {
    
    public MyTable(AbstractTableModel tableModel, int width[]) {
        setModel(tableModel);
        setColumnWidth(width);
        getTableHeader().setReorderingAllowed(false);
        getColumnModel().getColumn(0).setCellRenderer(new ButtonRender());
      
    }

    public void setColumnWidth(int col[]) {
        int x = getColumnCount() - 1;
        for (int i = 0; i <= x; i++) {
            TableColumn tableColumn = getColumnModel().getColumn(i);
            tableColumn.setPreferredWidth(col[i]);
        }
    }

    public MyTable(AbstractTableModel tableModel) {
        setModel(tableModel);
    }

    public MyTable() {
    }
}

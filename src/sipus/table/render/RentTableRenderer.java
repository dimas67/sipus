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
package sipus.table.render;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Administrator
 */
public class RentTableRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object objValue, boolean isSelected, boolean hasFocus, int iRow, int iColumn) {
        Component comp = super.getTableCellRendererComponent(table, objValue, isSelected, hasFocus, iRow, iColumn);
        Object status = table.getValueAt(iRow, 5);

        if (status.equals(1)) {
            setForeground(Color.red);
            setBackground(Color.white);
            setFont(getFont().deriveFont(Font.BOLD));
        } else {
            setForeground(Color.black);
            setBackground(Color.WHITE);
            setFont(getFont().deriveFont(Font.PLAIN));
        }

        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        }

        return comp;
    }

    @Override
    public void setValue(Object value) {
        if (value.equals(1)) {
            value = "Masih meminjam";
        } else if (value.equals(0)) {
            value = "Tidak meminjam";
        }
        super.setValue(value);
    }
}

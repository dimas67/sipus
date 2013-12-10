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

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Dimas
 */
public class ButtonRender extends JButton implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        if (row == 0) {
            setText("1");            
        } else {
            for (int i = 1; i <= table.getRowCount(); i++) {
                if (row == i) {
                    setText(Integer.toString(i + 1));                    
                }                
            }
        }

        if (isSelected) {
            setIcon(new ImageIcon(getClass().getResource("/sipus/icon/arrow.png")));
            setText("");
        } else {
            setIcon(null);
        }
        return this;
    }

    public ButtonRender() {
        setFocusPainted(false);
    }
}

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
package sipus.table;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import sipus.model.Kategori;

/**
 *
 * @author Dimas
 */
public class TableModelKategori extends AbstractTableModel {

    private List<Kategori> list = new ArrayList<Kategori>();

    public void insert(Kategori kategori) {
        list.add(kategori);
        fireTableRowsInserted(getRowCount(), getRowCount());
    }

    public void update(int row, Kategori kategori) {
        list.set(row, kategori);
        fireTableRowsUpdated(row, row);
    }

    public void delete(int row) {
        list.remove(row);
        fireTableRowsDeleted(row, row);
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Kategori kategori = list.get(rowIndex);
        switch (columnIndex) {
            case 1:
                return kategori.getKode();
            case 2:
                return kategori.getKategori();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "No";
            case 1:
                return "Kode";
            case 2:
                return "Kategori";
            default:
                return null;
        }
    }
}

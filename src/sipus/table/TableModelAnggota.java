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
import sipus.model.Anggota;

/**
 *
 * @author Dimas
 */
public class TableModelAnggota extends AbstractTableModel {

    private List<Anggota> list = new ArrayList<Anggota>();

    public void insert(Anggota anggota) {
        list.add(anggota);
        fireTableRowsInserted(getRowCount(), getRowCount());
    }

    public void update(int row, Anggota anggota) {
        list.set(row, anggota);
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
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Anggota anggota = list.get(rowIndex);
        switch (columnIndex) {
            case 1:
                return anggota.getKode();
            case 2:
                return anggota.getNama();
            case 3:
                return anggota.getAlamat();
            case 4:
                return anggota.getNoTelp();
            case 5:
                return anggota.getStatus();
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
                return "Nama";
            case 3:
                return "Alamat";
            case 4:
                return "No. Telp";
            case 5:
                return "Status";
            default:
                return null;
        }
    }
}

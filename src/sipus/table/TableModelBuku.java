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
import sipus.model.Buku;

/**
 *
 * @author Dimas
 */
public class TableModelBuku extends AbstractTableModel {

    private List<Buku> list = new ArrayList<Buku>();

    public void insert(Buku buku) {
        list.add(buku);
        fireTableRowsInserted(getRowCount(), getRowCount());
    }

    public void update(int row, Buku buku) {
        list.set(row, buku);
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
        return 10;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Buku buku = list.get(rowIndex);
        switch (columnIndex) {
            case 1:
                return buku.getKode();
            case 2:
                return buku.getJudul();
            case 3:
                return buku.getPenulis();
            case 4:
                return buku.getPenerbit();
            case 5:
                return buku.getKategori();
            case 6:
                return buku.getIsbn();
            case 7:
                return buku.getHargaSewa();
            case 8:
                return buku.getStok();
            case 9:
                return buku.getDenda();
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
                return "Kode Buku";
            case 2:
                return "Judul Buku";
            case 3:
                return "Penulis ";
            case 4:
                return "Penerbit";
            case 5:
                return "Kategori";
            case 6:
                return "ISBN";
            case 7:
                return "Harga Sewa";
            case 8:
                return "Stok";
            case 9:
                return "Denda";
            default:
                return null;
        }
    }
}

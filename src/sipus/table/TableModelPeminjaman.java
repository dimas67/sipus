/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipus.table;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import sipus.model.Peminjaman;

/**
 *
 * @author Administrator
 */
public class TableModelPeminjaman extends AbstractTableModel {

    private List<Peminjaman> list = new ArrayList<Peminjaman>();

    public void insert(Peminjaman peminjaman) {
        list.add(peminjaman);
        fireTableRowsInserted(getRowCount(), getRowCount());
    }

    public void update(int index, Peminjaman peminjaman) {
        list.set(index, peminjaman);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Peminjaman peminjaman = list.get(rowIndex);
        switch (columnIndex) {
            case 1:
                return peminjaman.getKodeTransaksi();
            case 2:
                return peminjaman.getKodeAnggota();
            case 3:
                return peminjaman.getNamaAnggota();
            case 4:
                return peminjaman.getTanggalPinjam();
            case 5:
                return peminjaman.getTanggalKembali();
            case 6:
                return peminjaman.getTotal();
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
                return "Kode Transaksi";
            case 2:
                return "Kode Anggota";
            case 3:
                return "Nama Anggota";
            case 4:
                return "Tgl. Pinjam";
            case 5:
                return "Tgl. Kembali";
            case 6:
                return "Total";
            default:
                return null;
        }
    }
}

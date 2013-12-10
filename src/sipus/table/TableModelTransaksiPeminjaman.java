/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipus.table;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import sipus.model.TransaksiPeminjaman;

/**
 *
 * @author Administrator
 */
public class TableModelTransaksiPeminjaman extends AbstractTableModel {

    private List<TransaksiPeminjaman> list = new ArrayList<TransaksiPeminjaman>();

    public void insert(TransaksiPeminjaman transaksiPeminjaman) {
        list.add(transaksiPeminjaman);
        fireTableRowsInserted(getRowCount(), getRowCount());
    }

    public void update(int index, TransaksiPeminjaman transaksiPeminjaman) {
        list.set(index, transaksiPeminjaman);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TransaksiPeminjaman transaksiPeminjaman = list.get(rowIndex);
        switch (columnIndex) {
            case 1:
                return transaksiPeminjaman.getKode();
            case 2:
                return transaksiPeminjaman.getJudul();
            case 3:
                return transaksiPeminjaman.getPenulis();
            case 4:
                return transaksiPeminjaman.getPenerbit();
            case 5:
                return transaksiPeminjaman.getQty();
            case 6:
                return transaksiPeminjaman.getSubtotal();
            case 7:
                return transaksiPeminjaman.getTotal();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "No.";
            case 1:
                return "Kode Buku";
            case 2:
                return "Judul";
            case 3:
                return "Penulis";
            case 4:
                return "Penerbit";
            case 5:
                return "QTY";
            case 6:
                return "Sub Total";
            case 7:
                return "Total";
            default:
                return null;
        }
    }
}

package sipus.table.render;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import sipus.dao.BukuDao;
import sipus.dao.svc.DaoService;
import sipus.model.Buku;
import sipus.ui.UITransaksiPeminjaman;

public class TextFieldTableCellEditor extends DefaultCellEditor implements CellEditorListener, ActionListener {

    private JTable table;
    private int editRow;
    private int editCol;
    private UpdateTableListener listener;
    private JTextField tf = new JTextField();
    private BukuDao bukuDao;
    private UITransaksiPeminjaman transaksiPeminjaman;

    public TextFieldTableCellEditor(UITransaksiPeminjaman transaksiPeminjaman) {
        super(new JTextField());
        this.transaksiPeminjaman = transaksiPeminjaman;
        bukuDao = DaoService.getBukuDao();

        tf.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    selectBuku(tf.getText());
                    tf.requestFocus();
                }
            }
        });
        tf.setBorder(new SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        addCellEditorListener(this);
    }

    private void selectBuku(String text) {
        Buku buku = bukuDao.getBuku(text);
        transaksiPeminjaman.showBuku(buku);
    }

    public void addUpdateTableListener(UpdateTableListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean isCellEditable(EventObject e) {
        if ((e instanceof MouseEvent)) {
            return ((MouseEvent) e).getClickCount() >= 1;
        }
        return true;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col) {
        try {
            this.table = table;
            this.editRow = row;
            this.editCol = col;

            this.tf.setText(value.toString());
        } catch (NullPointerException ex) {
        }
        return this.tf;
    }

    @Override
    public void editingStopped(ChangeEvent e) {
        this.table.setValueAt(this.tf.getText().trim(), this.editRow, this.editCol);
        if (this.listener != null) {
            this.listener.updateTable();
        }
    }

    @Override
    public void editingCanceled(ChangeEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.table.editCellAt(-1, -1);
    }
}
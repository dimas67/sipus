/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UIPeminjaman.java
 *
 * Created on Dec 1, 2011, 2:26:40 PM
 */
package sipus.ui;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import sipus.dao.TransaksiPeminjamanDao;
import sipus.dao.svc.DaoService;
import sipus.model.Buku;
import sipus.model.Peminjaman;
import sipus.model.TransaksiPeminjaman;
import sipus.swing.MyTable;
import sipus.table.TableModelPeminjaman;
import sipus.table.render.CurrencyRenderer;
import sipus.table.render.DateRenderer;
import sipus.util.OptionPane;

/**
 *
 * @author Administrator
 */
public class UIHistoryPeminjaman extends javax.swing.JInternalFrame {

    /** Creates new form UIPeminjaman */
    private MainForm mainForm;
    private TableModelPeminjaman modelPeminjaman = new TableModelPeminjaman();
    private int row[] = {60, 100, 100, 250, 150, 150, 150};
    private MyTable table = new MyTable(modelPeminjaman, row);
    private TransaksiPeminjamanDao transaksiPeminjamanDao;
    private String kolom[] = {"No", "Kode Buku", "Judul", "Penulis", "Penerbit", "Kategori"};
    private int kolomWidth[] = {60, 100, 250, 150, 150, 150};
    private DefaultTableModel modelBuku = new DefaultTableModel(kolom, 0);
    private MyTable tableBuku = new MyTable(modelBuku, kolomWidth);

    public UIHistoryPeminjaman() {
        initComponents();
        initForm();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popUp = new javax.swing.JPopupMenu();
        panelBuku = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        bTutup = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        tDataPeminjaman = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        bReload = new javax.swing.JButton();
        tCari = new javax.swing.JTextField();
        bCari = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cboCari = new javax.swing.JComboBox();

        panelBuku.setLayout(new java.awt.BorderLayout());
        panelBuku.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        bTutup.setText("Tutup");
        bTutup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTutupActionPerformed(evt);
            }
        });
        jPanel2.add(bTutup);

        panelBuku.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        tDataPeminjaman.setFont(new java.awt.Font("Tahoma", 1, 11));
        tDataPeminjaman.setText("Data Peminjaman : ");
        jPanel4.add(tDataPeminjaman);

        panelBuku.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("History Peminjaman Buku");

        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        bReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sipus/icon/reload.png"))); // NOI18N
        bReload.setText("Reload");
        bReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bReloadActionPerformed(evt);
            }
        });

        tCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tCariKeyPressed(evt);
            }
        });

        bCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sipus/icon/find.png"))); // NOI18N
        bCari.setText("Cari");
        bCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCariActionPerformed(evt);
            }
        });

        jLabel1.setText("Cari berdasarkan : ");

        cboCari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kode Anggota", "Nama Anggota", "Kode Buku", "Judul Buku" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboCari, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tCari, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bCari)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bReload)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bReload)
                    .addComponent(bCari)
                    .addComponent(tCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(cboCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bTutupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTutupActionPerformed
        // TODO add your handling code here:
        popUp.setVisible(false);
    }//GEN-LAST:event_bTutupActionPerformed

    private void bReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bReloadActionPerformed
        // TODO add your handling code here:
        reload();
    }//GEN-LAST:event_bReloadActionPerformed

    private void bCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCariActionPerformed
        // TODO add your handling code here:
        if (tCari.getText().length() == 0) {
            OptionPane.showWarningMessage("Silakan masukkan data yang akan dicari");
        } else {
            cari(cboCari.getSelectedItem().toString(), tCari.getText());
        }
    }//GEN-LAST:event_bCariActionPerformed

    private void tCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tCariKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (tCari.getText().length() == 0) {
                OptionPane.showWarningMessage("Silakan masukkan data yang akan dicari");
            } else {
                cari(cboCari.getSelectedItem().toString(), tCari.getText());
            }
        }
    }//GEN-LAST:event_tCariKeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCari;
    private javax.swing.JButton bReload;
    private javax.swing.JButton bTutup;
    private javax.swing.JComboBox cboCari;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelBuku;
    private javax.swing.JPopupMenu popUp;
    private javax.swing.JTextField tCari;
    private javax.swing.JLabel tDataPeminjaman;
    // End of variables declaration//GEN-END:variables

    private void initForm() {
        transaksiPeminjamanDao = DaoService.getTransaksiPeminjamanDao();
        table.getColumnModel().getColumn(4).setCellRenderer(new DateRenderer());
        table.getColumnModel().getColumn(5).setCellRenderer(new DateRenderer());
        table.getColumnModel().getColumn(6).setCellRenderer(new CurrencyRenderer());

        popUp.setPreferredSize(new Dimension(600, 200));
        popUp.add(panelBuku);

        jScrollPane1.setViewportView(table);
        jScrollPane2.setViewportView(tableBuku);

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getModifiers() == 16) {
                    getData(modelPeminjaman.getValueAt(table.getSelectedRow(), 1).toString());
                    popUp.setLocation(e.getXOnScreen(), e.getYOnScreen());
                    popUp.setVisible(true);
                }

            }

            private void getData(String kodeTransaksi) {
                for (int i = tableBuku.getRowCount() - 1; i >= 0; i--) {
                    modelBuku.removeRow(i);
                }
                TransaksiPeminjaman peminjaman = new TransaksiPeminjaman();
                peminjaman.setKodeTransaksi(kodeTransaksi);
                tDataPeminjaman.setText("Data Peminjaman : " + modelPeminjaman.getValueAt(table.getSelectedRow(), 3));
                List<Buku> list = transaksiPeminjamanDao.getBukuDipinjamHistroy(peminjaman);
                for (Buku buku : list) {
                    String data[] = {"", buku.getKode(), buku.getJudul(), buku.getPenulis(), buku.getPenerbit(), buku.getKategori()};
                    modelBuku.addRow(data);
                }
            }
        });
        reload();
    }

    public void setMainForm(MainForm mainForm) {
        this.mainForm = mainForm;
    }

    public void reload() {
        for (int i = table.getRowCount() - 1; i >= 0; i--) {
            modelPeminjaman.delete(i);
        }
        List<Peminjaman> list = transaksiPeminjamanDao.getAllHistory();
        for (Peminjaman peminjaman : list) {
            peminjaman.setKodeTransaksi(peminjaman.getKodeTransaksi());
            peminjaman.setKodeAnggota(peminjaman.getKodeAnggota());
            peminjaman.setNamaAnggota(peminjaman.getNamaAnggota());
            peminjaman.setTanggalPinjam(peminjaman.getTanggalPinjam());
            peminjaman.setTanggalKembali(peminjaman.getTanggalKembali());
            peminjaman.setTotal(peminjaman.getTotal());
            modelPeminjaman.insert(peminjaman);
        }
    }

    private void cari(String field, String keyword) {
        for (int i = table.getRowCount() - 1; i >= 0; i--) {
            modelPeminjaman.delete(i);
        }
        List<Peminjaman> list = transaksiPeminjamanDao.getAllHistoryBy(field, keyword);
        for (Peminjaman peminjaman : list) {
            peminjaman.setKodeTransaksi(peminjaman.getKodeTransaksi());
            peminjaman.setKodeAnggota(peminjaman.getKodeAnggota());
            peminjaman.setNamaAnggota(peminjaman.getNamaAnggota());
            peminjaman.setTanggalPinjam(peminjaman.getTanggalPinjam());
            peminjaman.setTanggalKembali(peminjaman.getTanggalKembali());
            peminjaman.setTotal(peminjaman.getTotal());
            modelPeminjaman.insert(peminjaman);
        }
    }
}

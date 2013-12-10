/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UIPengembalian.java
 *
 * Created on Dec 11, 2011, 11:06:14 PM
 */
package sipus.ui;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sipus.dao.AnggotaDao;
import sipus.dao.BukuDao;
import sipus.dao.TransaksiPeminjamanDao;
import sipus.dao.svc.DaoService;
import sipus.model.Anggota;
import sipus.model.Buku;
import sipus.model.TransaksiPeminjaman;
import sipus.swing.MyTable;
import sipus.util.AutoCompletion;
import sipus.util.OptionPane;

/**
 *
 * @author Administrator
 */
public class UIPengembalian extends javax.swing.JInternalFrame {

    /** Creates new form UIPengembalian */
    private TransaksiPeminjamanDao transaksiPeminjamanDao;
    private AnggotaDao anggotaDao;
    private BukuDao bukuDao;
    private String kolom[] = {"No", "Kode Buku", "Judul", "Penulis", "Penerbit", "Kategori"};
    private int kolomWidth[] = {60, 100, 250, 150, 150, 150};
    private DefaultTableModel modelBuku = new DefaultTableModel(kolom, 0);
    private MyTable table = new MyTable(modelBuku, kolomWidth);
    private String kodeTransaksi;
    private String kodeAnggota;
    private MainForm mainForm;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
    private int denda;

    public UIPengembalian() {
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboField = new javax.swing.JComboBox();
        lnama = new javax.swing.JLabel();
        tNama = new javax.swing.JLabel();
        lalamat = new javax.swing.JLabel();
        tAlamat = new javax.swing.JLabel();
        tTglPinjam = new javax.swing.JLabel();
        ltglpinjam = new javax.swing.JLabel();
        tTglKembali = new javax.swing.JLabel();
        ltglkembali = new javax.swing.JLabel();
        tDenda = new javax.swing.JLabel();
        ldenda = new javax.swing.JLabel();
        cboSearch = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        bKembalikanSemua = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Pengembalian Buku");

        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jLabel1.setText("Cari berdasarkan");

        cboField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kode Transaksi", "Kode Anggota", "Nama Anggota" }));
        cboField.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboFieldItemStateChanged(evt);
            }
        });

        lnama.setText("Nama :");

        tNama.setFont(new java.awt.Font("Tahoma", 1, 11));
        tNama.setText("nama");

        lalamat.setText("Alamat :");

        tAlamat.setFont(new java.awt.Font("Tahoma", 1, 11));
        tAlamat.setText("alamat");

        tTglPinjam.setFont(new java.awt.Font("Tahoma", 1, 11));
        tTglPinjam.setText("tglPinjam");

        ltglpinjam.setText("Tgl. Pinjam :");

        tTglKembali.setFont(new java.awt.Font("Tahoma", 1, 11));
        tTglKembali.setText("tglKembali");

        ltglkembali.setText("Tgl. Kembali :");

        tDenda.setFont(new java.awt.Font("Tahoma", 1, 11));
        tDenda.setText("denda");

        ldenda.setText("Denda :");

        cboSearch.setEditable(true);
        cboSearch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0001", "0002", "0003", "0004" }));
        cboSearch.setSelectedIndex(-1);
        cboSearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboSearchItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(cboField, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lnama)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tNama)
                        .addGap(18, 18, 18)
                        .addComponent(lalamat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tAlamat)
                        .addGap(18, 18, 18)
                        .addComponent(ltglpinjam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tTglPinjam)
                        .addGap(18, 18, 18)
                        .addComponent(ltglkembali)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tTglKembali)
                        .addGap(18, 18, 18)
                        .addComponent(ldenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tDenda)
                        .addGap(79, 79, 79))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cboSearch, 0, 627, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lnama)
                    .addComponent(tNama)
                    .addComponent(lalamat)
                    .addComponent(tAlamat)
                    .addComponent(ltglpinjam)
                    .addComponent(tTglPinjam)
                    .addComponent(ltglkembali)
                    .addComponent(tTglKembali)
                    .addComponent(ldenda)
                    .addComponent(tDenda))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        bKembalikanSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sipus/icon/save.png"))); // NOI18N
        bKembalikanSemua.setText("Simpan");
        bKembalikanSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bKembalikanSemuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bKembalikanSemua)
                .addContainerGap(765, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bKembalikanSemua)
                .addContainerGap())
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboSearchItemStateChanged
        // TODO add your handling code here:
        try {
            cari(cboSearch.getSelectedItem().toString());
        } catch (NullPointerException ex) {
        }
    }//GEN-LAST:event_cboSearchItemStateChanged

    private void cboFieldItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboFieldItemStateChanged
        // TODO add your handling code here:
        if (cboField.getSelectedItem().equals("Kode Transaksi")) {
            reloadKodeTransaksi();
        } else if (cboField.getSelectedItem().equals("Kode Anggota")) {
            reloadKodeAnggota();
        } else if (cboField.getSelectedItem().equals("Nama Anggota")) {
            reloadNamaAnggota();
        }
    }//GEN-LAST:event_cboFieldItemStateChanged

    private void bKembalikanSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bKembalikanSemuaActionPerformed
        // TODO add your handling code here:
        if (OptionPane.showConfirmMessage("Kembalikan semua buku?") == JOptionPane.YES_OPTION) {
            kembalikanSemua();
        }
    }//GEN-LAST:event_bKembalikanSemuaActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bKembalikanSemua;
    private javax.swing.JComboBox cboField;
    private javax.swing.JComboBox cboSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lalamat;
    private javax.swing.JLabel ldenda;
    private javax.swing.JLabel lnama;
    private javax.swing.JLabel ltglkembali;
    private javax.swing.JLabel ltglpinjam;
    private javax.swing.JLabel tAlamat;
    private javax.swing.JLabel tDenda;
    private javax.swing.JLabel tNama;
    private javax.swing.JLabel tTglKembali;
    private javax.swing.JLabel tTglPinjam;
    // End of variables declaration//GEN-END:variables

    private void initForm() {
        transaksiPeminjamanDao = DaoService.getTransaksiPeminjamanDao();
        anggotaDao = DaoService.getAnggotaDao();
        bukuDao = DaoService.getBukuDao();

        jScrollPane1.setViewportView(table);

        AutoCompletion autoCompletion = new AutoCompletion(cboSearch);
        hiddenInfo(true);

        reloadKodeTransaksi();
    }

    private void hiddenInfo(boolean b) {
        lnama.setVisible(!b);
        lalamat.setVisible(!b);
        ltglpinjam.setVisible(!b);
        ltglkembali.setVisible(!b);
        ldenda.setVisible(!b);
        tNama.setVisible(!b);
        tAlamat.setVisible(!b);
        tTglPinjam.setVisible(!b);
        tTglKembali.setVisible(!b);
        tDenda.setVisible(!b);
    }

    private void cari(String keyword) {
        for (int i = table.getRowCount() - 1; i >= 0; i--) {
            modelBuku.removeRow(i);
        }
        List<TransaksiPeminjaman> list = transaksiPeminjamanDao.getBukuDipinjamBy(cboField.getSelectedItem().toString(), keyword);

        if (list.size() <= 0) {
            OptionPane.showWarningMessage("Tidak ada data peminjaman");
        }
        for (TransaksiPeminjaman transaksiPeminjaman : list) {
            String data[] = {"", transaksiPeminjaman.getKodeBuku(), transaksiPeminjaman.getJudul(), transaksiPeminjaman.getPenulis(), transaksiPeminjaman.getPenerbit(), transaksiPeminjaman.getKategori()};
            modelBuku.addRow(data);

            tNama.setText(transaksiPeminjaman.getNamaAnggota());
            tAlamat.setText(transaksiPeminjaman.getAlamat());
            tTglPinjam.setText(dateFormat.format(transaksiPeminjaman.getTglPinjam()));
            tTglKembali.setText(dateFormat.format(transaksiPeminjaman.getTglKembali()));
            kodeAnggota = transaksiPeminjaman.getKodeAnggota();

            Date tglKembali = transaksiPeminjaman.getTglKembali();
            Date tglDiKembalikan = new Date();

            long day1 = tglKembali.getTime();
            long day2 = tglDiKembalikan.getTime();
            long diff = day2 - day1;
            denda = denda + (int) ((diff / (24 * 60 * 60 * 1000)) * transaksiPeminjaman.getDenda());

            if (denda > 0) {
                tDenda.setForeground(Color.red);
                tDenda.setText("Rp. " + String.valueOf(denda));
            } else {
                tDenda.setForeground(Color.black);
                tDenda.setText("Rp. 0");
            }
            kodeTransaksi = transaksiPeminjaman.getKodeTransaksi();
        }
        denda = 0;
        if (table.getRowCount() > 0) {
            hiddenInfo(false);
        }
    }

    private void reloadKodeTransaksi() {
        cboSearch.removeAllItems();
        List<TransaksiPeminjaman> list = transaksiPeminjamanDao.getKodeTransaksi();
        for (TransaksiPeminjaman peminjaman : list) {
            cboSearch.addItem(peminjaman.getKodeTransaksi());
        }
    }

    private void reloadKodeAnggota() {
        cboSearch.removeAllItems();
        List<TransaksiPeminjaman> list = transaksiPeminjamanDao.getKodeTransaksi();
        for (TransaksiPeminjaman peminjaman : list) {
            cboSearch.addItem(peminjaman.getKodeAnggota());
        }
    }

    private void reloadNamaAnggota() {
        cboSearch.removeAllItems();
        List<TransaksiPeminjaman> list = transaksiPeminjamanDao.getKodeTransaksi();
        for (TransaksiPeminjaman peminjaman : list) {
            cboSearch.addItem(peminjaman.getNamaAnggota());
        }
    }

    private void kembalikanSemua() {
        for (int i = 0; i < table.getRowCount(); i++) {
            TransaksiPeminjaman transaksiPeminjaman = new TransaksiPeminjaman();
            transaksiPeminjaman.setKodeTransaksi(kodeTransaksi);
            transaksiPeminjamanDao.deleteAllDetailPeminjaman(transaksiPeminjaman);
            Buku buku = bukuDao.getBuku(modelBuku.getValueAt(i, 1).toString());
            buku.setKode(modelBuku.getValueAt(i, 1).toString());
            buku.setStok(buku.getStok() + 1);
            bukuDao.updateStok(buku);
        }
        if (transaksiPeminjamanDao.isSuccess() && bukuDao.isSuccess()) {
            transaksiPeminjamanDao.deleteMasterPeminjaman(kodeTransaksi);
            if (transaksiPeminjamanDao.isSuccess()) {
                Anggota anggota = new Anggota();
                anggota.setKode(kodeAnggota);
                anggota.setStatus(0);
                anggotaDao.updateStatus(anggota);
                if (anggotaDao.isSuccess()) {
                    OptionPane.showMessage("Data berhasil disimpan");
                    dispose();
                    mainForm.reloadUIPeminjaman();
                    reload();
                }
            }
        }
    }

    public void setMainForm(MainForm mainForm) {
        this.mainForm = mainForm;
    }

    public void reload() {
        if (cboField.getSelectedIndex() == 0) {
            reloadKodeTransaksi();
        } else if (cboField.getSelectedIndex() == 1) {
            reloadKodeAnggota();
        } else if (cboField.getSelectedIndex() == 2) {
            reloadNamaAnggota();
        }
    }
}

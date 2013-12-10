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

/*
 * UIBuku.java
 *
 * Created on Nov 24, 2011, 10:23:25 PM
 */
package sipus.ui;

import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;
import sipus.dao.KategoriDao;
import sipus.dao.svc.DaoService;
import sipus.model.Kategori;
import sipus.swing.MyTable;
import sipus.table.TableModelKategori;
import sipus.util.OptionPane;

/**
 *
 * @author Dimas
 */
public class UIKategori extends javax.swing.JInternalFrame {

    /** Creates new form UIBuku */
    private TableModelKategori modelKategori = new TableModelKategori();
    private int col[] = {60, 150, 250}, action = 0;
    private static final int TAMBAH = 1, UBAH = 2;
    private MyTable myTable = new MyTable(modelKategori, col);
    private KategoriDao kategoriDao;

    public UIKategori() {
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

        dialog = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tKode = new javax.swing.JTextField();
        tKategori = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        bBatal = new javax.swing.JButton();
        bSimpan = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        bTambah = new javax.swing.JButton();
        bUbah = new javax.swing.JButton();
        bHapus = new javax.swing.JButton();
        bReload = new javax.swing.JButton();

        dialog.setTitle("Data Kategori");
        dialog.setModal(true);

        jLabel1.setText("Kode Kategori");

        jLabel2.setText("Kategori");

        tKategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tKategoriKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tKategori, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                    .addComponent(tKode, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        dialog.getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        bBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sipus/icon/cancel.png"))); // NOI18N
        bBatal.setText("Batal");
        bBatal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bBatal.setPreferredSize(new java.awt.Dimension(67, 45));
        bBatal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBatalActionPerformed(evt);
            }
        });

        bSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sipus/icon/save.png"))); // NOI18N
        bSimpan.setText("Simpan");
        bSimpan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bSimpan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSimpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(250, Short.MAX_VALUE)
                .addComponent(bSimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bBatal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bBatal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bSimpan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dialog.getContentPane().add(jPanel4, java.awt.BorderLayout.PAGE_END);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Data Kategori");

        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        bTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sipus/icon/new.png"))); // NOI18N
        bTambah.setText("Tambah");
        bTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTambahActionPerformed(evt);
            }
        });

        bUbah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sipus/icon/edit.png"))); // NOI18N
        bUbah.setText("Ubah");
        bUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUbahActionPerformed(evt);
            }
        });

        bHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sipus/icon/delete.png"))); // NOI18N
        bHapus.setText("Hapus");
        bHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHapusActionPerformed(evt);
            }
        });

        bReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sipus/icon/reload.png"))); // NOI18N
        bReload.setText("Reload");
        bReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bReloadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bTambah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bUbah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bHapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 238, Short.MAX_VALUE)
                .addComponent(bReload)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bTambah)
                    .addComponent(bUbah)
                    .addComponent(bHapus)
                    .addComponent(bReload))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTambahActionPerformed
        // TODO add your handling code here:
        setAction(TAMBAH);
        clearForm();
        dialog.setVisible(true);
    }//GEN-LAST:event_bTambahActionPerformed

    private void bSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanActionPerformed
        // TODO add your handling code here:
        if (getAction() == TAMBAH) {
            simpan();
        } else if (getAction() == UBAH) {
            ubah(myTable.getSelectedRow());
        }
    }//GEN-LAST:event_bSimpanActionPerformed

    private void bUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahActionPerformed
        // TODO add your handling code here:
        if (myTable.getSelectedRow() < 0) {
            OptionPane.showWarningMessage("Silakan pilih data yang akan diubah");
        } else {
            setAction(UBAH);
            loadTableToForm(myTable.getSelectedRow());
            dialog.setVisible(true);
        }
    }//GEN-LAST:event_bUbahActionPerformed

    private void bHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusActionPerformed
        // TODO add your handling code here:
        if (myTable.getSelectedRow() < 0) {
            OptionPane.showWarningMessage("Silakan pilih data yang akan dihapus");
        } else {
            if (OptionPane.showQuestionBox("Yakin menghapus data?", new String[]{"Hapus", "Batal"}) == JOptionPane.YES_OPTION) {
                hapus(myTable.getSelectedRow());
            }
        }
    }//GEN-LAST:event_bHapusActionPerformed

    private void bBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBatalActionPerformed
        // TODO add your handling code here:
        dialog.dispose();
    }//GEN-LAST:event_bBatalActionPerformed

    private void tKategoriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tKategoriKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (getAction() == TAMBAH) {
                simpan();
            } else if (getAction() == UBAH) {
                ubah(myTable.getSelectedRow());
            }
        }
    }//GEN-LAST:event_tKategoriKeyPressed

    private void bReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bReloadActionPerformed
        // TODO add your handling code here:
        reloadTable();
    }//GEN-LAST:event_bReloadActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bBatal;
    private javax.swing.JButton bHapus;
    private javax.swing.JButton bReload;
    private javax.swing.JButton bSimpan;
    private javax.swing.JButton bTambah;
    private javax.swing.JButton bUbah;
    private javax.swing.JDialog dialog;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tKategori;
    private javax.swing.JTextField tKode;
    // End of variables declaration//GEN-END:variables

    private void initForm() {
        kategoriDao = DaoService.getKategoriDao();
        jScrollPane1.setViewportView(myTable);

        dialog.pack();
        dialog.setLocationRelativeTo(null);

        reloadTable();

    }

    private void clearForm() {
        tKode.setText(kategoriDao.getGeneratedNumber());
        tKategori.setText("");
        tKategori.requestFocus();
    }

    private void simpan() {
        Kategori kategori = getKategori();
        kategoriDao.insert(kategori);

        if (kategoriDao.isSuccess()) {
            modelKategori.insert(kategori);
            clearForm();
        }
    }

    private void ubah(int selectedROw) {
        Kategori kategori = getKategori();
        kategoriDao.update(kategori);

        if (kategoriDao.isSuccess()) {
            modelKategori.update(selectedROw, kategori);
            clearForm();
            dialog.dispose();
        }
    }

    private void hapus(int selectedRow) {
        Kategori kategori = new Kategori();
        kategori.setKode(myTable.getValueAt(selectedRow, 1).toString());
        kategoriDao.delete(kategori);
        if (kategoriDao.isSuccess()) {
            modelKategori.delete(selectedRow);
        }
    }

    private void loadTableToForm(int selectedRow) {
        tKode.setText(myTable.getValueAt(selectedRow, 1).toString());
        tKategori.setText(myTable.getValueAt(selectedRow, 2).toString());
        tKategori.requestFocus();
    }

    private Kategori getKategori() {
        Kategori kategori = new Kategori();
        kategori.setKode(tKode.getText());
        kategori.setKategori(tKategori.getText());
        if (getAction() == UBAH) {
            kategori.setOldKode(myTable.getValueAt(myTable.getSelectedRow(), 1).toString());
        }
        return kategori;
    }

    private void reloadTable() {
        for (int i = myTable.getRowCount() - 1; i >= 0; i--) {
            modelKategori.delete(i);
        }
        List<Kategori> list = kategoriDao.getAll();
        for (Kategori kategori : list) {
            kategori.setKode(kategori.getKode());
            kategori.setKategori(kategori.getKategori());
            modelKategori.insert(kategori);
        }
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }
}

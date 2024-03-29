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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sipus.dao.AnggotaDao;
import sipus.dao.svc.DaoService;
import sipus.model.Anggota;
import sipus.swing.MyTable;
import sipus.table.TableModelAnggota;
import sipus.table.render.RentTableRenderer;
import sipus.util.OptionPane;

/**
 *
 * @author Dimas
 */
public class UIAnggota extends javax.swing.JInternalFrame {

    /** Creates new form UIBuku */
    private TableModelAnggota modelAnggota = new TableModelAnggota();
    private int col[] = {60, 150, 250, 350, 250, 150}, action = 0;
    private static final int TAMBAH = 1, UBAH = 2;
    private MyTable table = new MyTable(modelAnggota, col);
    private AnggotaDao anggotaDao;
    private int rows[];

    public UIAnggota() {
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
        tNama = new javax.swing.JTextField();
        tAlamat = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tNoTelp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
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

        dialog.setTitle("Data Anggota");
        dialog.setModal(true);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                dialogWindowActivated(evt);
            }
        });

        jLabel1.setText("Kode Anggota");

        jLabel2.setText("Nama");

        tNama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tNamaKeyPressed(evt);
            }
        });

        jLabel3.setText("Alamat");

        tNoTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tNoTelpKeyPressed(evt);
            }
        });

        jLabel4.setText("No. Telp");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tNoTelp, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                    .addComponent(tAlamat, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                    .addComponent(tNama, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                    .addComponent(tKode, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
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
                    .addComponent(tNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tNoTelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        setTitle("Data Anggota");

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
            ubah(table.getSelectedRow());
        }
    }//GEN-LAST:event_bSimpanActionPerformed

    private void bUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahActionPerformed
        // TODO add your handling code here:
        if (table.getSelectedRow() < 0) {
            OptionPane.showWarningMessage("Silakan pilih data yang akan diubah");
        } else {
            setAction(UBAH);
            loadTableToForm(table.getSelectedRow());
            dialog.setVisible(true);
        }
    }//GEN-LAST:event_bUbahActionPerformed

    private void bHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusActionPerformed
        // TODO add your handling code here:        
        if (table.getSelectedRow() < 0) {
            OptionPane.showWarningMessage("Silakan pilih data yang akan dihapus");
        } else {
            if (rows.length <= 1) {
                if (OptionPane.showQuestionBox("Yakin menghapus data?", new String[]{"Hapus", "Batal"}) == JOptionPane.YES_OPTION) {
                    hapus(table.getSelectedRow());
                }
            } else {
                if (OptionPane.showQuestionBox("Yakin menghapus " + rows.length + " data?", new String[]{"Hapus", "Batal"}) == JOptionPane.YES_OPTION) {
                    for (int i = 0; i < rows.length; i++) {
                        hapus(rows[i]);
                    }
                }
            }
        }
    }//GEN-LAST:event_bHapusActionPerformed

    private void bBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBatalActionPerformed
        // TODO add your handling code here:
        dialog.dispose();
    }//GEN-LAST:event_bBatalActionPerformed

    private void tNamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNamaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (getAction() == TAMBAH) {
                simpan();
            } else if (getAction() == UBAH) {
                ubah(table.getSelectedRow());
            }
        }
    }//GEN-LAST:event_tNamaKeyPressed

    private void bReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bReloadActionPerformed
        // TODO add your handling code here:
        reloadTable();
    }//GEN-LAST:event_bReloadActionPerformed

    private void tNoTelpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNoTelpKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (getAction() == TAMBAH) {
                simpan();
            } else if (getAction() == UBAH) {
                ubah(table.getSelectedRow());
            }
        }
    }//GEN-LAST:event_tNoTelpKeyPressed

    private void dialogWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_dialogWindowActivated
        // TODO add your handling code here:
        tNama.requestFocus();
    }//GEN-LAST:event_dialogWindowActivated
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tAlamat;
    private javax.swing.JTextField tKode;
    private javax.swing.JTextField tNama;
    private javax.swing.JTextField tNoTelp;
    // End of variables declaration//GEN-END:variables

    private void initForm() {
        anggotaDao = DaoService.getAnggotaDao();
        jScrollPane1.setViewportView(table);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                rows = table.getSelectedRows();
            }
        });


        dialog.pack();
        dialog.setLocationRelativeTo(null);

        reloadTable();
        initTableCellRenderer();
    }

    private void clearForm() {
        tKode.setText(anggotaDao.getGeneratedNumber());
        tNama.setText("");
        tAlamat.setText("");
        tNoTelp.setText("");
        tNama.requestFocus();
    }

    private void simpan() {
        Anggota anggota = getAnggota();
        anggotaDao.insert(anggota);

        if (anggotaDao.isSuccess()) {
            modelAnggota.insert(anggota);
            clearForm();
        }
    }

    private void ubah(int selectedROw) {
        Anggota kategori = getAnggota();
        anggotaDao.update(kategori);

        if (anggotaDao.isSuccess()) {
            modelAnggota.update(selectedROw, kategori);
            clearForm();
            dialog.dispose();
        }
    }

    private void hapus(int selectedRow) {
        Anggota kategori = new Anggota();
        kategori.setKode(table.getValueAt(selectedRow, 1).toString());
        anggotaDao.delete(kategori);
        if (anggotaDao.isSuccess()) {
            modelAnggota.delete(selectedRow);
        }
    }

    private void loadTableToForm(int selectedRow) {
        tKode.setText(table.getValueAt(selectedRow, 1).toString());
        tNama.setText(table.getValueAt(selectedRow, 2).toString());
        tAlamat.setText(table.getValueAt(selectedRow, 3).toString());
        tNoTelp.setText(table.getValueAt(selectedRow, 4).toString());
        tNama.requestFocus();
    }

    private Anggota getAnggota() {
        Anggota anggota = new Anggota();
        anggota.setKode(tKode.getText());
        anggota.setNama(tNama.getText());
        anggota.setAlamat(tAlamat.getText());
        anggota.setNoTelp(tNoTelp.getText());
        anggota.setStatus(0);
        if (getAction() == UBAH) {
            anggota.setOldKode(table.getValueAt(table.getSelectedRow(), 1).toString());
        }
        return anggota;
    }

    private void reloadTable() {
        for (int i = table.getRowCount() - 1; i >= 0; i--) {
            modelAnggota.delete(i);
        }
        List<Anggota> list = anggotaDao.getAll();
        for (Anggota kategori : list) {
            kategori.setKode(kategori.getKode());
            kategori.setNama(kategori.getNama());
            kategori.setAlamat(kategori.getAlamat());
            kategori.setNoTelp(kategori.getNoTelp());
            modelAnggota.insert(kategori);
        }
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    private void initTableCellRenderer() {
        for (int i = 1; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new RentTableRenderer());
        }
    }
}

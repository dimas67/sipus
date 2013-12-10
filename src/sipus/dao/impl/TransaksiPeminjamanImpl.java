/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sipus.dao.TransaksiPeminjamanDao;
import sipus.model.Buku;
import sipus.model.Peminjaman;
import sipus.model.TransaksiPeminjaman;
import sipus.util.OptionPane;

/**
 *
 * @author Administrator
 */
public class TransaksiPeminjamanImpl implements TransaksiPeminjamanDao {

    private Connection connection;
    private boolean success;
    private final String QUERY_INSERT_MASTER = "insert into peminjaman values(?,?,?,?,?)";
    private final String QUERY_INSERT_MASTER_TEMP = "insert into temp_peminjaman values(?,?,?,?,?)";
    private final String QUERY_INSERT_DETAIL = "insert into detail_peminjaman values(?,?)";
    private final String QUERY_INSERT_DETAIL_TEMP = "insert into temp_detail_peminjaman values(?,?)";
    private final String QUERY_GET_GENERATED_KODE = "select kode_transaksi from temp_peminjaman order by kode_transaksi";
    private final String QUERY_GET_ALL = "select p.*, a.* from peminjaman p, anggota a where p.kode_anggota = a.kode";
    private final String QUERY_GET_ALL_HISTORY = "select p.*, a.* from temp_peminjaman p, anggota a where p.kode_anggota = a.kode order by p.kode_transaksi";
    private final String QUERY_GET_BUKU_DIPINJAM = "select b.*, p.*, d.*, k.kategori as kat "
            + "from buku b, peminjaman p, detail_peminjaman d, kategori k "
            + "where b.kategori = k.kode and b.kode = d.kode_buku and d.kode_transaksi = p.kode_transaksi and p.kode_transaksi = ?";
    private final String QUERY_GET_BUKU_DIPINJAM_HISTORY = "select b.*, p.*, d.*, k.kategori as kat "
            + "from buku b, temp_peminjaman p, temp_detail_peminjaman d, kategori k "
            + "where b.kategori = k.kode and b.kode = d.kode_buku and d.kode_transaksi = p.kode_transaksi and p.kode_transaksi = ?";
    private final String QUERY_ROLLBACK = "delete from peminjaman where kode_transaksi = ?";
    private final String QUERY_GET_ALL_KODE_TRANSAKSI = "select p.*, a.* from peminjaman p, anggota a where p.kode_anggota = a.kode ";
    private final String QUERY_UPDATE_PEMINJAMAN = "select p.*, a.* from peminjaman p, anggota a where p.kode_anggota = a.kode ";
    private final String QUERY_DELETE_MASTER_PEMINJAMAN = "delete from peminjaman where kode_transaksi = ? ";
    private final String QUERY_DELETE_DETAIL_PEMINJAMAN = "delete from detail_peminjaman where kode_transaksi = ? and kode_buku = ?";
    private final String QUERY_DELETE_ALL_DETAIL_PEMINJAMAN = "delete from detail_peminjaman where kode_transaksi = ?";
    private final String QUERY_GET_ALL_PRINT = "select a.*, b.*, p.*, d.*, k.kategori as kat "
            + "from anggota a, buku b, peminjaman p, detail_peminjaman d, kategori k "
            + "where b.kategori = k.kode and b.kode = d.kode_buku and d.kode_transaksi = p.kode_transaksi and a.kode = p.kode_anggota";
    private final String QUERY_GET_ALL_PRINT_PERIODE = "select a.*, b.*, p.*, d.*, k.kategori as kat "
            + "from anggota a, buku b, peminjaman p, detail_peminjaman d, kategori k "
            + "where b.kategori = k.kode and b.kode = d.kode_buku and d.kode_transaksi = p.kode_transaksi and a.kode = p.kode_anggota "
            + "and p.tgl_pinjam >= ? and p.tgl_kembali <=?";
    private final String QUERY_GET_ALL_HISTORY_PRINT = "select a.*, b.*, p.*, d.*, k.kategori as kat "
            + "from anggota a, buku b, temp_peminjaman p, temp_detail_peminjaman d, kategori k "
            + "where b.kategori = k.kode and b.kode = d.kode_buku and d.kode_transaksi = p.kode_transaksi and a.kode = p.kode_anggota "
            + "order by p.kode_transaksi";
    private final String QUERY_GET_PERUSAHAAN = "select * from perusahaan";
    private String lastKode;

    public TransaksiPeminjamanImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertMasterPeminjaman(TransaksiPeminjaman transaksiPeminjaman) {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(QUERY_INSERT_MASTER);
            statement.setString(1, transaksiPeminjaman.getKodeTransaksi());
            statement.setString(2, transaksiPeminjaman.getKodeAnggota());
            statement.setDate(3, transaksiPeminjaman.getTglKembali());
            statement.setDate(4, transaksiPeminjaman.getTglPinjam());
            statement.setInt(5, transaksiPeminjaman.getTotal());
            statement.executeUpdate();

            statement = connection.prepareStatement(QUERY_INSERT_MASTER_TEMP);
            statement.setString(1, transaksiPeminjaman.getKodeTransaksi());
            statement.setString(2, transaksiPeminjaman.getKodeAnggota());
            statement.setDate(3, transaksiPeminjaman.getTglKembali());
            statement.setDate(4, transaksiPeminjaman.getTglPinjam());
            statement.setInt(5, transaksiPeminjaman.getTotal());
            statement.executeUpdate();

            connection.commit();
            success = true;
            lastKode = transaksiPeminjaman.getKodeTransaksi();
        } catch (SQLException ex) {
            try {
                success = false;
                connection.rollback();
                OptionPane.showErrorMessage(ex);
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                statement.close();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void insertDetailPeminjaman(TransaksiPeminjaman transaksiPeminjaman) {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(QUERY_INSERT_DETAIL);
            statement.setString(1, transaksiPeminjaman.getKodeTransaksi());
            statement.setString(2, transaksiPeminjaman.getKodeBuku());
            statement.executeUpdate();

            statement = connection.prepareStatement(QUERY_INSERT_DETAIL_TEMP);
            statement.setString(1, transaksiPeminjaman.getKodeTransaksi());
            statement.setString(2, transaksiPeminjaman.getKodeBuku());
            statement.executeUpdate();

            connection.commit();
            success = true;
        } catch (SQLException ex) {
            try {
                success = false;
                connection.rollback();
                OptionPane.showErrorMessage(ex);
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                statement.close();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public String getGeneratedKodeTransaksi() {
        String generatedKode = "";
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(QUERY_GET_GENERATED_KODE);
            ResultSet rs = statement.executeQuery();
            connection.commit();
            String num;
            int x = 0;
            while (rs.next()) {
                num = rs.getString("kode_transaksi");
                int length = num.length();
                x = Integer.parseInt(num.substring(length - 4, length));
            }
            int number = x + 1;
            if (number >= 1 && number <= 9) {
                generatedKode = "000" + number;
            } else if (number >= 10 && number <= 99) {
                generatedKode = "00" + number;
            } else if (number >= 100 && number <= 999) {
                generatedKode = "0" + number;
            }
        } catch (SQLException ex) {
            try {
                Logger.getLogger(AnggotaImpl.class.getName()).log(Level.SEVERE, null, ex);
                OptionPane.showErrorMessage(ex);
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AnggotaImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                statement.close();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AnggotaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return generatedKode;
    }

    @Override
    public List<Peminjaman> getAll() {
        List<Peminjaman> list = null;
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(QUERY_GET_ALL);
            list = new ArrayList<Peminjaman>();
            ResultSet rs = statement.executeQuery();
            connection.commit();
            while (rs.next()) {
                Peminjaman peminjaman = new Peminjaman();
                peminjaman.setKodeTransaksi(rs.getString("kode_transaksi"));
                peminjaman.setKodeAnggota(rs.getString("kode_anggota"));
                peminjaman.setNamaAnggota(rs.getString("nama"));
                peminjaman.setTanggalKembali(rs.getDate("tgl_kembali"));
                peminjaman.setTanggalPinjam(rs.getDate("tgl_pinjam"));
                peminjaman.setTotal(rs.getInt("total"));
                list.add(peminjaman);
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                statement.close();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public void rollBack() {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(QUERY_ROLLBACK);
            statement.setString(1, lastKode);
            statement.executeUpdate();
            connection.commit();
            success = true;
        } catch (SQLException ex) {
            try {
                success = false;
                connection.rollback();
                OptionPane.showErrorMessage(ex);
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                statement.close();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<Buku> getBukuDipinjam(TransaksiPeminjaman transaksiPeminjaman) {
        List<Buku> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<Buku>();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(QUERY_GET_BUKU_DIPINJAM);
            statement.setString(1, transaksiPeminjaman.getKodeTransaksi());
            ResultSet rs = statement.executeQuery();
            connection.commit();
            while (rs.next()) {
                Buku buku = new Buku();
                buku.setKode(rs.getString("kode"));
                buku.setJudul(rs.getString("judul"));
                buku.setPenulis(rs.getString("penulis"));
                buku.setPenerbit(rs.getString("penerbit"));
                buku.setKategori(rs.getString("kat"));
                list.add(buku);
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                statement.close();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public List<TransaksiPeminjaman> getBukuDipinjamBy(String field, String keyword) {
        List<TransaksiPeminjaman> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<TransaksiPeminjaman>();
            connection.setAutoCommit(false);
            if (field.equals("Kode Transaksi")) {
                field = "p.kode_transaksi";
            } else if (field.equals("Kode Anggota")) {
                field = "p.kode_anggota";
            } else if (field.equals("Nama Anggota")) {
                field = "a.nama";
            }
            String QUERY_GET_BUKU_DIPINJAM_BY = "select b.*, p.*, d.*, k.kategori as kat, a.* "
                    + "from buku b, peminjaman p, detail_peminjaman d, kategori k, anggota a "
                    + "where b.kategori = k.kode and b.kode = d.kode_buku and d.kode_transaksi = p.kode_transaksi "
                    + "and p.kode_anggota = a.kode and " + field + " = ? ";
            statement = connection.prepareStatement(QUERY_GET_BUKU_DIPINJAM_BY);
            statement.setString(1, keyword);

            ResultSet rs = statement.executeQuery();
            connection.commit();
            while (rs.next()) {
                TransaksiPeminjaman transaksiPeminjaman = new TransaksiPeminjaman();
                transaksiPeminjaman.setKodeTransaksi(rs.getString("kode_transaksi"));
                transaksiPeminjaman.setKodeAnggota(rs.getString("kode_anggota"));
                transaksiPeminjaman.setKodeBuku(rs.getString("kode"));
                transaksiPeminjaman.setJudul(rs.getString("judul"));
                transaksiPeminjaman.setPenulis(rs.getString("penulis"));
                transaksiPeminjaman.setPenerbit(rs.getString("penerbit"));
                transaksiPeminjaman.setKategori(rs.getString("kat"));
                transaksiPeminjaman.setTglPinjam(rs.getDate("tgl_pinjam"));
                transaksiPeminjaman.setTglKembali(rs.getDate("tgl_kembali"));
                transaksiPeminjaman.setNamaAnggota(rs.getString("nama"));
                transaksiPeminjaman.setAlamat(rs.getString("alamat"));
                transaksiPeminjaman.setDenda(rs.getInt("denda"));
                list.add(transaksiPeminjaman);
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                statement.close();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public List<TransaksiPeminjaman> getKodeTransaksi() {
        List<TransaksiPeminjaman> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<TransaksiPeminjaman>();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(QUERY_GET_ALL_KODE_TRANSAKSI);
            ResultSet rs = statement.executeQuery();
            connection.commit();
            while (rs.next()) {
                TransaksiPeminjaman peminjaman = new TransaksiPeminjaman();
                peminjaman.setKodeTransaksi(rs.getString("kode_transaksi"));
                peminjaman.setKodeAnggota(rs.getString("kode_anggota"));
                peminjaman.setNamaAnggota(rs.getString("nama"));
                list.add(peminjaman);
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                statement.close();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public void deleteDetailPeminjaman(TransaksiPeminjaman transaksiPeminjaman) {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(QUERY_DELETE_DETAIL_PEMINJAMAN);
            statement.setString(1, transaksiPeminjaman.getKodeTransaksi());
            statement.setString(2, transaksiPeminjaman.getKodeBuku());
            statement.executeUpdate();
            connection.commit();
            success = true;
            lastKode = transaksiPeminjaman.getKodeTransaksi();
        } catch (SQLException ex) {
            try {
                success = false;
                connection.rollback();
                OptionPane.showErrorMessage(ex);
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                statement.close();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void deleteAllDetailPeminjaman(TransaksiPeminjaman transaksiPeminjaman) {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(QUERY_DELETE_ALL_DETAIL_PEMINJAMAN);
            statement.setString(1, transaksiPeminjaman.getKodeTransaksi());
            statement.executeUpdate();
            connection.commit();
            success = true;
            lastKode = transaksiPeminjaman.getKodeTransaksi();
        } catch (SQLException ex) {
            try {
                success = false;
                connection.rollback();
                OptionPane.showErrorMessage(ex);
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                statement.close();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void deleteMasterPeminjaman(String kodeTransaksi) {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(QUERY_DELETE_MASTER_PEMINJAMAN);
            statement.setString(1, kodeTransaksi);
            statement.executeUpdate();
            connection.commit();
            success = true;
            lastKode = kodeTransaksi;
        } catch (SQLException ex) {
            try {
                success = false;
                connection.rollback();
                OptionPane.showErrorMessage(ex);
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                statement.close();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<Peminjaman> getAllHistory() {
        List<Peminjaman> list = null;
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(QUERY_GET_ALL_HISTORY);
            list = new ArrayList<Peminjaman>();
            ResultSet rs = statement.executeQuery();
            connection.commit();
            while (rs.next()) {
                Peminjaman peminjaman = new Peminjaman();
                peminjaman.setKodeTransaksi(rs.getString("kode_transaksi"));
                peminjaman.setKodeAnggota(rs.getString("kode_anggota"));
                peminjaman.setNamaAnggota(rs.getString("nama"));
                peminjaman.setTanggalKembali(rs.getDate("tgl_kembali"));
                peminjaman.setTanggalPinjam(rs.getDate("tgl_pinjam"));
                peminjaman.setTotal(rs.getInt("total"));
                list.add(peminjaman);
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                statement.close();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public List<Buku> getBukuDipinjamHistroy(TransaksiPeminjaman transaksiPeminjaman) {
        List<Buku> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<Buku>();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(QUERY_GET_BUKU_DIPINJAM_HISTORY);
            statement.setString(1, transaksiPeminjaman.getKodeTransaksi());
            ResultSet rs = statement.executeQuery();
            connection.commit();
            while (rs.next()) {
                Buku buku = new Buku();
                buku.setKode(rs.getString("kode"));
                buku.setJudul(rs.getString("judul"));
                buku.setPenulis(rs.getString("penulis"));
                buku.setPenerbit(rs.getString("penerbit"));
                buku.setKategori(rs.getString("kat"));
                list.add(buku);
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                statement.close();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public List<Peminjaman> getAllHistoryBy(String field, String keyword) {
        List<Peminjaman> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<Peminjaman>();
            connection.setAutoCommit(false);
            if (field.equals("Kode Anggota")) {
                field = "p.kode_anggota";
            } else if (field.equals("Nama Anggota")) {
                field = "a.nama";
            } else if (field.equals("Kode Buku")) {
                field = "a.nama";
            } else if (field.equals("Judul Buku")) {
                field = "a.nama";
            }
            String QUERY_GET_ALL_HISTORY_BY = "select p.*, a.* from temp_peminjaman p, anggota a "
                    + "where p.kode_anggota = a.kode and " + field + " = ?";
            statement = connection.prepareStatement(QUERY_GET_ALL_HISTORY_BY);
            statement.setString(1, keyword);

            ResultSet rs = statement.executeQuery();
            connection.commit();
            while (rs.next()) {
                Peminjaman peminjaman = new Peminjaman();
                peminjaman.setKodeTransaksi(rs.getString("kode_transaksi"));
                peminjaman.setKodeAnggota(rs.getString("kode_anggota"));
                peminjaman.setNamaAnggota(rs.getString("nama"));
                peminjaman.setTanggalKembali(rs.getDate("tgl_kembali"));
                peminjaman.setTanggalPinjam(rs.getDate("tgl_pinjam"));
                peminjaman.setTotal(rs.getInt("total"));
                list.add(peminjaman);
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                statement.close();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public List<TransaksiPeminjaman> getAllPrint() {
        List<TransaksiPeminjaman> list = null;
        PreparedStatement statement = null;
        try {
            String np = null, alp = null, notlp = null;

            list = new ArrayList<TransaksiPeminjaman>();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(QUERY_GET_ALL_PRINT);
            ResultSet rs = statement.executeQuery();

            statement = connection.prepareStatement(QUERY_GET_PERUSAHAAN);
            ResultSet rsPerusahaan = statement.executeQuery();

            if (rsPerusahaan.next()) {
                np = rsPerusahaan.getString("nama");
                alp = rsPerusahaan.getString("alamat");
                notlp = rsPerusahaan.getString("no_telp");
            }

            connection.commit();

            while (rs.next()) {
                TransaksiPeminjaman peminjaman = new TransaksiPeminjaman();
                peminjaman.setKodeTransaksi(rs.getString("kode_transaksi"));
                peminjaman.setKodeAnggota(rs.getString("kode_anggota"));
                peminjaman.setNamaAnggota(rs.getString("nama"));
                peminjaman.setAlamat(rs.getString("alamat"));
                peminjaman.setNoTelpAnggota(rs.getString("telp"));
                peminjaman.setTglPinjam(rs.getDate("tgl_pinjam"));
                peminjaman.setTglKembali(rs.getDate("tgl_kembali"));
                peminjaman.setKodeBuku(" " + rs.getString("kode_buku"));
                peminjaman.setJudul(" " + rs.getString("judul"));
                peminjaman.setPenulis(" " + rs.getString("penulis"));
                peminjaman.setPenerbit(" " + rs.getString("penerbit"));
                peminjaman.setKategori(" " + rs.getString("kat"));
                peminjaman.setTotal(rs.getInt("total"));
                peminjaman.setNamaPerusahaan(np);
                peminjaman.setAlamatPerusahaan(alp);
                peminjaman.setNoTelpPerusahaan(notlp);
                list.add(peminjaman);
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                statement.close();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public List<TransaksiPeminjaman> getAllPrint(java.sql.Date tgl1, java.sql.Date tgl2) {
        List<TransaksiPeminjaman> list = null;
        PreparedStatement statement = null;
        try {
            String np = null, alp = null, notlp = null;

            list = new ArrayList<TransaksiPeminjaman>();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(QUERY_GET_ALL_PRINT_PERIODE);
            statement.setDate(1, tgl1);
            statement.setDate(2, tgl2);
            ResultSet rs = statement.executeQuery();

            statement = connection.prepareStatement(QUERY_GET_PERUSAHAAN);
            ResultSet rsPerusahaan = statement.executeQuery();

            if (rsPerusahaan.next()) {
                np = rsPerusahaan.getString("nama");
                alp = rsPerusahaan.getString("alamat");
                notlp = rsPerusahaan.getString("no_telp");
            }

            connection.commit();

            while (rs.next()) {
                TransaksiPeminjaman peminjaman = new TransaksiPeminjaman();
                peminjaman.setKodeTransaksi(rs.getString("kode_transaksi"));
                peminjaman.setKodeAnggota(rs.getString("kode_anggota"));
                peminjaman.setNamaAnggota(rs.getString("nama"));
                peminjaman.setAlamat(rs.getString("alamat"));
                peminjaman.setNoTelpAnggota(rs.getString("telp"));
                peminjaman.setTglPinjam(rs.getDate("tgl_pinjam"));
                peminjaman.setTglKembali(rs.getDate("tgl_kembali"));
                peminjaman.setKodeBuku(" " + rs.getString("kode_buku"));
                peminjaman.setJudul(" " + rs.getString("judul"));
                peminjaman.setPenulis(" " + rs.getString("penulis"));
                peminjaman.setPenerbit(" " + rs.getString("penerbit"));
                peminjaman.setKategori(" " + rs.getString("kat"));
                peminjaman.setTotal(rs.getInt("total"));
                peminjaman.setNamaPerusahaan(np);
                peminjaman.setAlamatPerusahaan(alp);
                peminjaman.setNoTelpPerusahaan(notlp);
                peminjaman.setTgl1(tgl1);
                peminjaman.setTgl2(tgl2);
                list.add(peminjaman);
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                statement.close();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public List<TransaksiPeminjaman> getAllHistoryPrint() {
        List<TransaksiPeminjaman> list = null;
        PreparedStatement statement = null;
        try {
            String np = null, alp = null, notlp = null;

            list = new ArrayList<TransaksiPeminjaman>();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(QUERY_GET_ALL_HISTORY_PRINT);
            ResultSet rs = statement.executeQuery();

            statement = connection.prepareStatement(QUERY_GET_PERUSAHAAN);
            ResultSet rsPerusahaan = statement.executeQuery();

            if (rsPerusahaan.next()) {
                np = rsPerusahaan.getString("nama");
                alp = rsPerusahaan.getString("alamat");
                notlp = rsPerusahaan.getString("no_telp");
            }

            connection.commit();

            while (rs.next()) {
                TransaksiPeminjaman peminjaman = new TransaksiPeminjaman();
                peminjaman.setKodeTransaksi(rs.getString("kode_transaksi"));
                peminjaman.setKodeAnggota(rs.getString("kode_anggota"));
                peminjaman.setNamaAnggota(rs.getString("nama"));
                peminjaman.setAlamat(rs.getString("alamat"));
                peminjaman.setNoTelpAnggota(rs.getString("telp"));
                peminjaman.setTglPinjam(rs.getDate("tgl_pinjam"));
                peminjaman.setTglKembali(rs.getDate("tgl_kembali"));
                peminjaman.setKodeBuku(" " + rs.getString("kode_buku"));
                peminjaman.setJudul(" " + rs.getString("judul"));
                peminjaman.setPenulis(" " + rs.getString("penulis"));
                peminjaman.setPenerbit(" " + rs.getString("penerbit"));
                peminjaman.setKategori(" " + rs.getString("kat"));
                peminjaman.setTotal(rs.getInt("total"));
                peminjaman.setNamaPerusahaan(np);
                peminjaman.setAlamatPerusahaan(alp);
                peminjaman.setNoTelpPerusahaan(notlp);
                list.add(peminjaman);
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                statement.close();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPeminjamanImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
}

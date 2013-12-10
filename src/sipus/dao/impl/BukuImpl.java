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
package sipus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sipus.dao.BukuDao;
import sipus.model.Buku;
import sipus.util.OptionPane;

/**
 *
 * @author Dimas
 */
public class BukuImpl implements BukuDao {

    private Connection connection;
    private boolean success;
    private final String QUERY_INSERT = "insert into buku values(?,?,?,?,?,?,?,?,?)";
    private final String QUERY_UPDATE = "update buku set kode = ?, judul = ?, penulis = ?,"
            + "penerbit = ?, kategori = ?, isbn = ?, harga_sewa = ?, denda = ?, stok = ? where kode = ?";
    private final String QUERY_DELETE = "delete from buku where kode = ?";
    private final String QUERY_GET_BUKU = "select * from buku where kode = ?";
    private final String QUERY_GET_ALL = "select b.*, k.kategori as kat from buku b, kategori k where k.kode = b.kategori order by b.kode";
    private final String QUERY_GET_ALL_KEYWORD = "select * from buku where judul like ? or penulis like ? or penerbit like ?";
    private final String QUERY_GET_GENERATED_NUMBER = "select kode from buku order by kode";
    private final String QUERY_UPDATE_STOK = "update buku set stok = ? where kode = ?";

    public BukuImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Buku buku) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(QUERY_INSERT);
            statement.setString(1, buku.getKode());
            statement.setString(2, buku.getJudul());
            statement.setString(3, buku.getPenulis());
            statement.setString(4, buku.getPenerbit());
            statement.setString(5, buku.getKategori());
            statement.setString(6, buku.getIsbn());
            statement.setInt(7, buku.getHargaSewa());
            statement.setInt(8, buku.getDenda());
            statement.setInt(9, buku.getStok());
            statement.executeUpdate();
            connection.commit();
            success = true;
        } catch (SQLException ex) {
            try {
                success=false;
                Logger.getLogger(BukuImpl.class.getName()).log(Level.SEVERE, null, ex);
                OptionPane.showErrorMessage(ex);
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BukuImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(BukuImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(Buku buku) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE);
            statement.setString(1, buku.getKode());
            statement.setString(2, buku.getJudul());
            statement.setString(3, buku.getPenulis());
            statement.setString(4, buku.getPenerbit());
            statement.setString(5, buku.getKategori());
            statement.setString(6, buku.getIsbn());
            statement.setInt(7, buku.getHargaSewa());
            statement.setInt(8, buku.getDenda());
            statement.setInt(9, buku.getStok());
            statement.setString(10, buku.getOldKode());
            statement.executeUpdate();
            connection.commit();
            success = true;
        } catch (SQLException ex) {
            try {
                success=false;
                Logger.getLogger(BukuImpl.class.getName()).log(Level.SEVERE, null, ex);
                OptionPane.showErrorMessage(ex);
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BukuImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(BukuImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(Buku buku) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(QUERY_DELETE);
            statement.setString(1, buku.getKode());
            statement.executeUpdate();
            connection.commit();
            success = true;
        } catch (SQLException ex) {
            try {
                success=false;
                Logger.getLogger(BukuImpl.class.getName()).log(Level.SEVERE, null, ex);
                OptionPane.showErrorMessage(ex);
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BukuImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(BukuImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Buku getBuku(String kode) {
        Buku buku = new Buku();
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(QUERY_GET_BUKU);
            statement.setString(1, kode);
            ResultSet rs = statement.executeQuery();
            connection.commit();
            if (rs.next()) {
                buku.setKode(rs.getString("kode"));
                buku.setJudul(rs.getString("judul"));
                buku.setPenulis(rs.getString("penulis"));
                buku.setPenerbit(rs.getString("penerbit"));
                buku.setKategori(rs.getString("kategori"));
                buku.setIsbn(rs.getString("isbn"));
                buku.setHargaSewa(rs.getInt("harga_sewa"));
                buku.setDenda(rs.getInt("denda"));
                buku.setStok(rs.getInt("stok"));
            }
        } catch (SQLException ex) {
            try {
                success=false;
                Logger.getLogger(BukuImpl.class.getName()).log(Level.SEVERE, null, ex);
                OptionPane.showErrorMessage(ex);
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BukuImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(BukuImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return buku;
    }

    @Override
    public List<Buku> getAll() {
        List<Buku> list = new ArrayList<Buku>();
        try {
            NumberFormat numberFormater = new DecimalFormat("#,##0");

            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(QUERY_GET_ALL);
            ResultSet rs = statement.executeQuery();
            connection.commit();
            while (rs.next()) {
                Buku buku = new Buku();
                buku.setKode(rs.getString("kode"));
                buku.setJudul(rs.getString("judul"));
                buku.setPenulis(rs.getString("penulis"));
                buku.setPenerbit(rs.getString("penerbit"));
                buku.setKategori(rs.getString("kat"));
                buku.setIsbn(rs.getString("isbn"));
                buku.setHargaSewa(rs.getInt("harga_sewa"));
                buku.setDenda(rs.getInt("denda"));
                buku.setStok(rs.getInt("stok"));
                list.add(buku);
            }
        } catch (SQLException ex) {
            try {
                success=false;
                Logger.getLogger(BukuImpl.class.getName()).log(Level.SEVERE, null, ex);
                OptionPane.showErrorMessage(ex);
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BukuImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(BukuImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return list;
    }

    @Override
    public List<Buku> getAll(String keyword) {
        List<Buku> list = new ArrayList<Buku>();
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(QUERY_GET_ALL_KEYWORD);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setString(3, "%" + keyword + "%");
            ResultSet rs = statement.executeQuery();
            connection.commit();
            while (rs.next()) {
                Buku buku = new Buku();
                buku.setKode(rs.getString("kode"));
                buku.setJudul(rs.getString("judul"));
                buku.setPenulis(rs.getString("penulis"));
                buku.setPenerbit(rs.getString("penerbit"));
                buku.setKategori(rs.getString("kategori"));
                buku.setIsbn(rs.getString("isbn"));
                buku.setHargaSewa(rs.getInt("harga_sewa"));
                buku.setDenda(rs.getInt("denda"));
                buku.setStok(rs.getInt("stok"));
                list.add(buku);
            }
        } catch (SQLException ex) {
            try {
                success=false;
                Logger.getLogger(BukuImpl.class.getName()).log(Level.SEVERE, null, ex);
                OptionPane.showErrorMessage(ex);
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BukuImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(BukuImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return list;
    }

    @Override
    public String getGeneratedNumber() {
        String generatedNumber = "";
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(QUERY_GET_GENERATED_NUMBER);
            ResultSet rs = statement.executeQuery();
            connection.commit();
            String num;
            int x = 0;
            while (rs.next()) {
                num = rs.getString("kode");
                int length = num.length();
                x = Integer.parseInt(num.substring(length - 4, length));
            }
            int number = x + 1;
            if (number >= 1 && number <= 9) {
                generatedNumber = "000" + number;
            } else if (number >= 10 && number <= 99) {
                generatedNumber = "00" + number;
            } else if (number >= 100 && number <= 999) {
                generatedNumber = "0" + number;
            }
        } catch (SQLException ex) {
            try {
                success=false;
                Logger.getLogger(BukuImpl.class.getName()).log(Level.SEVERE, null, ex);
                OptionPane.showErrorMessage(ex);
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BukuImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(BukuImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return generatedNumber;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public void updateStok(Buku buku) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE_STOK);
            statement.setInt(1, buku.getStok());
            statement.setString(2, buku.getKode());
            statement.executeUpdate();
            connection.commit();
            success = true;
        } catch (SQLException ex) {
            try {
                success=false;
                Logger.getLogger(BukuImpl.class.getName()).log(Level.SEVERE, null, ex);
                OptionPane.showErrorMessage(ex);
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BukuImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(BukuImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

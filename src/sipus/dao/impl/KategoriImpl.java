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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sipus.dao.KategoriDao;
import sipus.model.Kategori;
import sipus.util.OptionPane;

/**
 *
 * @author Dimas
 */
public class KategoriImpl implements KategoriDao {

    private Connection connection;
    private boolean success;
    private final String QUERY_INSERT = "insert into kategori values(?,?)";
    private final String QUERY_UPDATE = "update kategori set kode = ?, kategori = ? where kode = ?";
    private final String QUERY_DELETE = "delete from kategori where kode = ?";
    private final String QUERY_GET_BUKU = "select * from kategori where kategori = ?";
    private final String QUERY_GET_ALL = "select * from kategori order by kategori";
    private final String QUERY_GET_ALL_KEYWORD = "select * from kategori where kategori like ? order by kategori";
    private final String QUERY_GET_GENERATED_NUMBER = "select kode from kategori order by kode";

    public KategoriImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Kategori kategori) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(QUERY_INSERT);
            statement.setString(1, kategori.getKode());
            statement.setString(2, kategori.getKategori());
            statement.executeUpdate();
            connection.commit();
            success = true;
        } catch (SQLException ex) {
            try {
                Logger.getLogger(KategoriImpl.class.getName()).log(Level.SEVERE, null, ex);
                OptionPane.showErrorMessage(ex);
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(KategoriImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(KategoriImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(Kategori kategori) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE);
            statement.setString(1, kategori.getKode());
            statement.setString(2, kategori.getKategori());
            statement.setString(3, kategori.getOldKode());
            statement.executeUpdate();
            connection.commit();
            success = true;
        } catch (SQLException ex) {
            try {
                Logger.getLogger(KategoriImpl.class.getName()).log(Level.SEVERE, null, ex);
                OptionPane.showErrorMessage(ex);
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(KategoriImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(KategoriImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(Kategori kategori) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(QUERY_DELETE);
            statement.setString(1, kategori.getKode());
            statement.executeUpdate();
            connection.commit();
            success = true;
        } catch (SQLException ex) {
            try {
                Logger.getLogger(KategoriImpl.class.getName()).log(Level.SEVERE, null, ex);
                OptionPane.showErrorMessage(ex);
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(KategoriImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(KategoriImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Kategori getKategori(String kat) {
        Kategori kategori = new Kategori();
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(QUERY_GET_BUKU);
            statement.setString(1, kat);
            ResultSet rs = statement.executeQuery();
            connection.commit();
            if (rs.next()) {
                kategori.setKode(rs.getString("kode"));
                kategori.setKategori(rs.getString("kategori"));
            }
        } catch (SQLException ex) {
            try {
                Logger.getLogger(KategoriImpl.class.getName()).log(Level.SEVERE, null, ex);
                OptionPane.showErrorMessage(ex);
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(KategoriImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(KategoriImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return kategori;
    }

    @Override
    public List<Kategori> getAll() {
        List<Kategori> list = new ArrayList<Kategori>();
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(QUERY_GET_ALL);
            ResultSet rs = statement.executeQuery();
            connection.commit();
            while (rs.next()) {
                Kategori kategori = new Kategori();
                kategori.setKode(rs.getString("kode"));
                kategori.setKategori(rs.getString("kategori"));
                list.add(kategori);
            }
        } catch (SQLException ex) {
            try {
                Logger.getLogger(KategoriImpl.class.getName()).log(Level.SEVERE, null, ex);
                OptionPane.showErrorMessage(ex);
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(KategoriImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(KategoriImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return list;
    }

    @Override
    public List<Kategori> getAll(String keyword) {
        List<Kategori> list = new ArrayList<Kategori>();
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(QUERY_GET_ALL_KEYWORD);
            statement.setString(1, keyword);
            ResultSet rs = statement.executeQuery();
            connection.commit();
            if (rs.next()) {
                Kategori kategori = new Kategori();
                kategori.setKode(rs.getString("kode"));
                kategori.setKategori(rs.getString("kategori"));
                list.add(kategori);
            }
        } catch (SQLException ex) {
            try {
                Logger.getLogger(KategoriImpl.class.getName()).log(Level.SEVERE, null, ex);
                OptionPane.showErrorMessage(ex);
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(KategoriImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(KategoriImpl.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(KategoriImpl.class.getName()).log(Level.SEVERE, null, ex);
                OptionPane.showErrorMessage(ex);
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(KategoriImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(KategoriImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return generatedNumber;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }
}

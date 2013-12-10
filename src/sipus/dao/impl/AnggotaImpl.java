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
import sipus.dao.AnggotaDao;
import sipus.model.Anggota;
import sipus.util.OptionPane;

/**
 *
 * @author Dimas
 */
public class AnggotaImpl implements AnggotaDao {

    private Connection connection;
    private boolean success;
    private final String QUERY_INSERT = "insert into anggota values(?,?,?,?,?)";
    private final String QUERY_UPDATE = "update anggota set kode = ?, nama = ?, alamat = ?, telp = ?, status = ? where kode = ?";
    private final String QUERY_DELETE = "delete from anggota where kode = ?";
    private final String QUERY_GET_ANGGOTA = "select * from anggota where kode = ? ";
    private final String QUERY_GET_ALL = "select * from anggota order by kode";
    private final String QUERY_GET_ALL_KEYWORD = "select * from anggota where nama like ? or alamat like ? or telp like ? order by kode";
    private final String QUERY_GET_GENERATED_NUMBER = "select kode from anggota order by kode";
    private final String QUERY_UPDATE_STATUS = "update anggota set status = ? where kode = ?";

    public AnggotaImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Anggota anggota) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(QUERY_INSERT);
            statement.setString(1, anggota.getKode());
            statement.setString(2, anggota.getNama());
            statement.setString(3, anggota.getAlamat());
            statement.setString(4, anggota.getNoTelp());
            statement.setInt(5, anggota.getStatus());
            statement.executeUpdate();
            connection.commit();
            success = true;
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
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AnggotaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(Anggota anggota) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE);
            statement.setString(1, anggota.getKode());
            statement.setString(2, anggota.getNama());
            statement.setString(3, anggota.getAlamat());
            statement.setString(4, anggota.getNoTelp());
            statement.setInt(5, anggota.getStatus());
            statement.setString(6, anggota.getOldKode());
            statement.executeUpdate();
            connection.commit();
            success = true;
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
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AnggotaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(Anggota anggota) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(QUERY_DELETE);
            statement.setString(1, anggota.getKode());
            statement.executeUpdate();
            connection.commit();
            success = true;
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
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AnggotaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Anggota getAnggota(String kat) {
        Anggota anggota = new Anggota();
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(QUERY_GET_ANGGOTA);
            statement.setString(1, kat);
            ResultSet rs = statement.executeQuery();
            connection.commit();
            if (rs.next()) {
                anggota.setKode(rs.getString("kode"));
                anggota.setNama(rs.getString("nama"));
                anggota.setAlamat(rs.getString("alamat"));
                anggota.setNoTelp(rs.getString("telp"));
                anggota.setStatus(rs.getInt("status"));
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
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AnggotaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return anggota;
    }

    @Override
    public List<Anggota> getAll() {
        List<Anggota> list = new ArrayList<Anggota>();
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(QUERY_GET_ALL);
            ResultSet rs = statement.executeQuery();
            connection.commit();
            while (rs.next()) {
                Anggota anggota = new Anggota();
                anggota.setKode(rs.getString("kode"));
                anggota.setNama(rs.getString("nama"));
                anggota.setAlamat(rs.getString("alamat"));
                anggota.setNoTelp(rs.getString("telp"));
                anggota.setStatus(rs.getInt("status"));
                list.add(anggota);
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
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AnggotaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return list;
    }

    @Override
    public List<Anggota> getAll(String keyword) {
        List<Anggota> list = new ArrayList<Anggota>();
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(QUERY_GET_ALL_KEYWORD);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setString(3, "%" + keyword + "%");
            ResultSet rs = statement.executeQuery();
            connection.commit();
            while (rs.next()) {
                Anggota anggota = new Anggota();
                anggota.setKode(rs.getString("kode"));
                anggota.setNama(rs.getString("nama"));
                anggota.setAlamat(rs.getString("alamat"));
                anggota.setNoTelp(rs.getString("telp"));
                anggota.setStatus(rs.getInt("status"));
                list.add(anggota);
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
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AnggotaImpl.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(AnggotaImpl.class.getName()).log(Level.SEVERE, null, ex);
                OptionPane.showErrorMessage(ex);
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AnggotaImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AnggotaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return generatedNumber;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public void updateStatus(Anggota anggota) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE_STATUS);
            statement.setInt(1, anggota.getStatus());
            statement.setString(2, anggota.getKode());
            statement.executeUpdate();
            connection.commit();
            success = true;
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
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AnggotaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sipus.dao.DataPerusahaanDao;
import sipus.model.DataPerusahaan;
import sipus.util.OptionPane;

/**
 *
 * @author Administrator
 */
public class DataPerusahaanImpl implements DataPerusahaanDao {

    private Connection connection;
    private boolean success;
    private final String QUERY_INSERT = "insert into perusahaan values(?,?,?)";
    private final String QUERY_UPDATE = "update perusahaan set nama=?, alamat=?, no_telp=?";
    private final String QUERY_IS_EXISTS = "select * from perusahaan";

    public DataPerusahaanImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(DataPerusahaan dataPerusahaan) {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(QUERY_INSERT);
            statement.setString(1, dataPerusahaan.getNama());
            statement.setString(2, dataPerusahaan.getAlamat());
            statement.setString(3, dataPerusahaan.getNoTelp());
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
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(AnggotaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(DataPerusahaan dataPerusahaan) {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(QUERY_UPDATE);
            statement.setString(1, dataPerusahaan.getNama());
            statement.setString(2, dataPerusahaan.getAlamat());
            statement.setString(3, dataPerusahaan.getNoTelp());
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
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(AnggotaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean isExists() {
        boolean exists = false;
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(QUERY_IS_EXISTS);
            ResultSet rs = statement.executeQuery();
            connection.commit();
            if (rs.next()) {
                exists = true;
            } else {
                exists = false;
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
        return exists;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public DataPerusahaan getDataPerusahaan() {
        DataPerusahaan dataPerusahaan = null;
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(QUERY_IS_EXISTS);
            ResultSet rs = statement.executeQuery();
            connection.commit();
            if (rs.next()) {
                dataPerusahaan = new DataPerusahaan();
                dataPerusahaan.setNama(rs.getString("nama"));
                dataPerusahaan.setAlamat(rs.getString("alamat"));
                dataPerusahaan.setNoTelp(rs.getString("no_telp"));
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
        return dataPerusahaan;
    }
}

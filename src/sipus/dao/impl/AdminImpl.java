/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sipus.crypt.MD5;
import sipus.dao.AdminDao;
import sipus.model.Admin;

/**
 *
 * @author Administrator
 */
public class AdminImpl implements AdminDao {

    private Connection connection;
    private final String QUERY_GET_ADMIN = "select * from login where username = ? and password = ?";
    private final String QUERY_UPDATE_STATUS = "update login set status = ? where id_login = ?";
    private boolean success;

    public AdminImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Admin admin) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Admin admin) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Admin admin) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Admin> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Admin getAdmin(String username, String password) {
        Admin admin = null;
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(QUERY_GET_ADMIN);
            statement.setString(1, username);
            statement.setString(2, MD5.getMD5(password));
            ResultSet rs = statement.executeQuery();
            connection.commit();
            if (rs.next()) {
                admin = new Admin();
                admin.setId(rs.getInt("id_login"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                admin.setStatus(rs.getInt("status"));
            }
            success = true;
        } catch (SQLException ex) {
            try {
                success = false;
                connection.rollback();
                Logger.getLogger(AdminImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(AdminImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                statement.close();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AdminImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return admin;
    }

    @Override
    public boolean isSuccess() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateStatus(Admin admin) {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(QUERY_UPDATE_STATUS);
            statement.setInt(1, admin.getStatus());
            statement.setInt(2, admin.getId());
            statement.executeUpdate();
            connection.commit();
            success = true;
        } catch (SQLException ex) {
            try {
                success = false;
                connection.rollback();
                Logger.getLogger(AdminImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(AdminImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                statement.close();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AdminImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

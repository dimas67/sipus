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
package sipus.dao.svc;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.pool.OracleDataSource;
import sipus.dao.AdminDao;
import sipus.dao.AnggotaDao;
import sipus.dao.BukuDao;
import sipus.dao.DataPerusahaanDao;
import sipus.dao.KategoriDao;
import sipus.dao.TransaksiPeminjamanDao;
import sipus.dao.impl.AdminImpl;
import sipus.dao.impl.AnggotaImpl;
import sipus.dao.impl.BukuImpl;
import sipus.dao.impl.DataPerusahaanImpl;
import sipus.dao.impl.KategoriImpl;
import sipus.dao.impl.TransaksiPeminjamanImpl;
import sipus.util.OptionPane;

/**
 *
 * @author Dimas
 */
public class DaoService {

    private static BukuDao bukuDao;
    private static KategoriDao kategoriDao;
    private static AnggotaDao anggotaDao;
    private static TransaksiPeminjamanDao transaksiPeminjamanDao;
    private static AdminDao adminDao;
    private static DataPerusahaanDao dataPerusahaanDao;
    private static String server, port, database, username, password;

    public static Connection getConnection() {
        loadConfig();
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setURL("jdbc:mysql://" + server + ":" + port + "/" + database);
            mysqlDataSource.setUser(username);
            mysqlDataSource.setPassword(password);
            connection = mysqlDataSource.getConnection();
        } catch (SQLException ex) {
            OptionPane.showErrorMessage("Koneksi Gagal : \n" + ex);
            Logger.getLogger(DaoService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public static BukuDao getBukuDao() {
        if (bukuDao == null) {
            bukuDao = new BukuImpl(getConnection());
        }
        return bukuDao;
    }

    public static KategoriDao getKategoriDao() {
        if (kategoriDao == null) {
            kategoriDao = new KategoriImpl(getConnection());
        }
        return kategoriDao;
    }

    public static AnggotaDao getAnggotaDao() {
        if (anggotaDao == null) {
            anggotaDao = new AnggotaImpl(getConnection());
        }
        return anggotaDao;
    }

    public static TransaksiPeminjamanDao getTransaksiPeminjamanDao() {
        if (transaksiPeminjamanDao == null) {
            transaksiPeminjamanDao = new TransaksiPeminjamanImpl(getConnection());
        }
        return transaksiPeminjamanDao;
    }

    public static AdminDao getAdminDao() {
        if (adminDao == null) {
            adminDao = new AdminImpl(getConnection());
        }
        return adminDao;
    }

    public static DataPerusahaanDao getDataPerusahaanDao() {
        if (dataPerusahaanDao == null) {
            dataPerusahaanDao = new DataPerusahaanImpl(getConnection());
        }
        return dataPerusahaanDao;
    }

    private static void loadConfig() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config/database.ini"));
            server = properties.getProperty("Server");
            port = properties.getProperty("Port");
            username = properties.getProperty("Username");
            password = properties.getProperty("Password");
        } catch (IOException ex) {
            Logger.getLogger(DaoService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipus.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import sipus.dao.svc.DaoService;

/**
 *
 * @author Administrator
 */
public class Backup {

    private String username;
    private String password;
    private String file;

    public Backup(String file) {
        this.file = file;
        loadConfig();
    }

    private void loadConfig() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config/database.ini"));
            username = properties.getProperty("Username");
            password = properties.getProperty("Password");
        } catch (IOException ex) {
            Logger.getLogger(DaoService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startBackup() {
        try {            
            Process process = Runtime.getRuntime().exec("exp " + username + "/" + password + " file=" + file);
            OptionPane.showMessage("Database berhasil dibackup");
        } catch (IOException ex) {
            OptionPane.showErrorMessage("Database gagal dibackup :\n" + ex);
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

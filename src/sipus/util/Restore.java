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

/**
 *
 * @author Administrator
 */
public class Restore {

    private String touser;
    private String fromuser;
    private String password;
    private String file;

    public Restore(String file) {
        this.file = file;
        loadUser();
    }

    private void loadUser() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config/database.ini"));
            setFromuser(properties.getProperty("Username"));
        } catch (IOException ex) {
            Logger.getLogger(Restore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startRestore() {
        try {
            Process process = Runtime.getRuntime().exec("imp " + touser + "/" + password + " file=" + file + " fromuser=" + fromuser + " touser=" + touser);
            OptionPane.showMessage("Database berhasil direstore");
        } catch (Exception ex) {
            OptionPane.showErrorMessage("Database gagal dibackup :\n" + ex);
            Logger.getLogger(Restore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return touser;
    }

    public void setUsername(String username) {
        this.touser = username;
    }

    public String getFromuser() {
        return fromuser;
    }

    public void setFromuser(String fromuser) {
        this.fromuser = fromuser;
    }
}

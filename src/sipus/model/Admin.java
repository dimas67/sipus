/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipus.model;

/**
 *
 * @author Administrator
 */
public class Admin {

    private int id;
    private String username;
    private String password;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

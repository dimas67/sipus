/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipus.dao;

import java.util.List;
import sipus.model.Admin;

/**
 *
 * @author Administrator
 */
public interface AdminDao {

    public void insert(Admin admin);

    public void update(Admin admin);

    public void delete(Admin admin);

    public List<Admin> getAll();

    public Admin getAdmin(String username, String password);
    
    public void updateStatus(Admin admin);

    public boolean isSuccess();
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipus.dao;

import sipus.model.DataPerusahaan;

/**
 *
 * @author Administrator
 */
public interface DataPerusahaanDao {

    public void insert(DataPerusahaan dataPerusahaan);

    public void update(DataPerusahaan dataPerusahaan);

    public DataPerusahaan getDataPerusahaan();

    public boolean isExists();

    public boolean isSuccess();
}

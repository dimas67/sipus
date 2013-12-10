/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipus.dao;

import java.sql.Date;
import java.util.List;
import sipus.model.Buku;
import sipus.model.Peminjaman;
import sipus.model.TransaksiPeminjaman;

/**
 *
 * @author Administrator
 */
public interface TransaksiPeminjamanDao {

    public void insertMasterPeminjaman(TransaksiPeminjaman transaksiPeminjaman);

    public void insertDetailPeminjaman(TransaksiPeminjaman transaksiPeminjaman);

    public void deleteMasterPeminjaman(String kodeTransaksi);

    public void deleteDetailPeminjaman(TransaksiPeminjaman transaksiPeminjaman);

    public void deleteAllDetailPeminjaman(TransaksiPeminjaman transaksiPeminjaman);

    public List<Peminjaman> getAll();

    public List<Peminjaman> getAllHistory();

    public List<Peminjaman> getAllHistoryBy(String field, String keyword);

    public List<Buku> getBukuDipinjam(TransaksiPeminjaman transaksiPeminjaman);

    public List<Buku> getBukuDipinjamHistroy(TransaksiPeminjaman transaksiPeminjaman);

    public List<TransaksiPeminjaman> getBukuDipinjamBy(String field, String keyword);

    public List<TransaksiPeminjaman> getAllPrint();

    public List<TransaksiPeminjaman> getAllHistoryPrint();

    public List<TransaksiPeminjaman> getAllPrint(Date tgl1, Date tgl2);

    public String getGeneratedKodeTransaksi();

    public List<TransaksiPeminjaman> getKodeTransaksi();

    public boolean isSuccess();

    public void rollBack();
}

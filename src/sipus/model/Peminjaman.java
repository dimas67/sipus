/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipus.model;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class Peminjaman {

    private String kodeTransaksi;
    private String oldKodeTransaksi;
    private String kodeAnggota;
    private String namaAnggota;
    private Date tanggalPinjam;
    private Date tanggalKembali;
    private int total;

    public String getNamaAnggota() {
        return namaAnggota;
    }

    public void setNamaAnggota(String namaAnggota) {
        this.namaAnggota = namaAnggota;
    }

    public String getOldKodeTransaksi() {
        return oldKodeTransaksi;
    }

    public void setOldKodeTransaksi(String oldKodeTransaksi) {
        this.oldKodeTransaksi = oldKodeTransaksi;
    }

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    public String getKodeAnggota() {
        return kodeAnggota;
    }

    public void setKodeAnggota(String kodeAnggota) {
        this.kodeAnggota = kodeAnggota;
    }

    public Date getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(Date tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public Date getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(Date tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

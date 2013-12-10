/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipus.model;

import java.sql.Date;

/**
 *
 * @author Administrator
 */
public class TransaksiPeminjaman {

    private String kodeBuku;
    private String kodeTransaksi;
    private String kodeAnggota;
    private String namaAnggota;
    private String noTelpAnggota;
    private String alamatAnggota;
    private Date tglKembali;
    private Date tglPinjam;
    private String judul;
    private String penulis;
    private String penerbit;
    private String kategori;
    private int qty;
    private int subtotal;
    private int total;
    private int denda;
    private String namaPerusahaan;
    private String alamatPerusahaan;
    private String noTelpPerusahaan;
    private Date tgl1;
    private Date tgl2;

    public Date getTgl1() {
        return tgl1;
    }

    public void setTgl1(Date tgl1) {
        this.tgl1 = tgl1;
    }

    public Date getTgl2() {
        return tgl2;
    }

    public void setTgl2(Date tgl2) {
        this.tgl2 = tgl2;
    }

    public String getAlamatAnggota() {
        return alamatAnggota;
    }

    public void setAlamatAnggota(String alamatAnggota) {
        this.alamatAnggota = alamatAnggota;
    }

    public String getAlamatPerusahaan() {
        return alamatPerusahaan;
    }

    public void setAlamatPerusahaan(String alamatPerusahaan) {
        this.alamatPerusahaan = alamatPerusahaan;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public void setNamaPerusahaan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
    }

    public String getNoTelpPerusahaan() {
        return noTelpPerusahaan;
    }

    public void setNoTelpPerusahaan(String noTelpPerusahaan) {
        this.noTelpPerusahaan = noTelpPerusahaan;
    }

    public int getDenda() {
        return denda;
    }

    public void setDenda(int denda) {
        this.denda = denda;
    }

    public String getAlamat() {
        return alamatAnggota;
    }

    public void setAlamat(String alamat) {
        this.alamatAnggota = alamat;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getNamaAnggota() {
        return namaAnggota;
    }

    public void setNamaAnggota(String namaAnggota) {
        this.namaAnggota = namaAnggota;
    }

    public Date getTglPinjam() {
        return tglPinjam;
    }

    public void setTglPinjam(Date tglPinjam) {
        this.tglPinjam = tglPinjam;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKode() {
        return kodeBuku;
    }

    public void setKode(String kode) {
        this.kodeBuku = kode;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getKodeAnggota() {
        return kodeAnggota;
    }

    public void setKodeAnggota(String kodeAnggota) {
        this.kodeAnggota = kodeAnggota;
    }

    public String getKodeBuku() {
        return kodeBuku;
    }

    public void setKodeBuku(String kodeBuku) {
        this.kodeBuku = kodeBuku;
    }

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    public Date getTglKembali() {
        return tglKembali;
    }

    public void setTglKembali(Date tglKembali) {
        this.tglKembali = tglKembali;
    }

    public String getNoTelpAnggota() {
        return noTelpAnggota;
    }

    public void setNoTelpAnggota(String noTelpAnggota) {
        this.noTelpAnggota = noTelpAnggota;
    }
}

package models;

public class ThanhVien {
    private String maTV;
    private String tenTV;
    private String soDienThoai;
    private String nhomMau;

    public ThanhVien() {
    }

    public ThanhVien(String maTV, String tenTV, String soDienThoai, String nhomMau) {
        this.maTV = maTV;
        this.tenTV = tenTV;
        this.soDienThoai = soDienThoai;
        this.nhomMau = nhomMau;
    }

    public String getMaTV() { return maTV; }
    public void setMaTV(String maTV) { this.maTV = maTV; }

    public String getTenTV() { return tenTV; }
    public void setTenTV(String tenTV) { this.tenTV = tenTV; }

    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }

    public String getNhomMau() { return nhomMau; }
    public void setNhomMau(String nhomMau) { this.nhomMau = nhomMau; }
}
package models;

public class SuKien {
    private String maSK;
    private String tenSK;
    private String ngayToChuc;

    public SuKien() {
    }

    public SuKien(String maSK, String tenSK, String ngayToChuc) {
        this.maSK = maSK;
        this.tenSK = tenSK;
        this.ngayToChuc = ngayToChuc;
    }

    public String getMaSK() { return maSK; }
    public void setMaSK(String maSK) { this.maSK = maSK; }

    public String getTenSK() { return tenSK; }
    public void setTenSK(String tenSK) { this.tenSK = tenSK; }

    public String getNgayToChuc() { return ngayToChuc; }
    public void setNgayToChuc(String ngayToChuc) { this.ngayToChuc = ngayToChuc; }
}
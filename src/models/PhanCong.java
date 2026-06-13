package models;

public class PhanCong {
    private String maSK;
    private String maTV;
    private String vaiTro;

    public PhanCong() {
    }

    public PhanCong(String maSK, String maTV, String vaiTro) {
        this.maSK = maSK;
        this.maTV = maTV;
        this.vaiTro = vaiTro;
    }

    public String getMaSK() { return maSK; }
    public void setMaSK(String maSK) { this.maSK = maSK; }

    public String getMaTV() { return maTV; }
    public void setMaTV(String maTV) { this.maTV = maTV; }

    public String getVaiTro() { return vaiTro; }
    public void setVaiTro(String vaiTro) { this.vaiTro = vaiTro; }
}
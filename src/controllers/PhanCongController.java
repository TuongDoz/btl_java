package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import models.PhanCong;
import utils.DatabaseConnection;

public class PhanCongController {


    public List<PhanCong> getAllDiemDanh() {
        List<PhanCong> list = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection()) {

            String sql = "SELECT MaSK, MaTV, VaiTro FROM DiemDanh ORDER BY MaSK ASC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new PhanCong(
                    rs.getString("MaSK"),
                    rs.getString("MaTV"),
                    rs.getString("VaiTro")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<String> getDanhSachSuKien() {
        List<String> list = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection()) {
            String sql = "SELECT MaSK, TenSK FROM SuKien";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                list.add(rs.getString("MaSK") + " - " + rs.getString("TenSK"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<String> getDanhSachThanhVien() {
        List<String> list = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection()) {
            String sql = "SELECT MaTV, TenTV FROM ThanhVien WHERE MaTV NOT IN (SELECT MaTV FROM DiemDanh)";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("MaTV") + " - " + rs.getString("TenTV"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean themDiemDanh(PhanCong dd) {
        try (Connection con = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO DiemDanh(MaSK, MaTV, VaiTro) VALUES(?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, dd.getMaSK());
            pst.setString(2, dd.getMaTV());
            pst.setString(3, dd.getVaiTro());
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<models.ThanhVien> getThanhVienTheoSuKien(String maSK) {
        List<models.ThanhVien> list = new ArrayList<>();

        String sql = "SELECT tv.MaTV, tv.TenTV, tv.SoDienThoai, dd.VaiTro AS NhomMau " +
                     "FROM DiemDanh dd " +
                     "INNER JOIN ThanhVien tv ON dd.MaTV = tv.MaTV " +
                     "WHERE dd.MaSK = ?";
        try (Connection con = DatabaseConnection.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, maSK);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                list.add(new models.ThanhVien(
                    rs.getString("MaTV"),
                    rs.getString("TenTV"),
                    rs.getString("SoDienThoai"),
                    rs.getString("NhomMau") 
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
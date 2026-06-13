package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.ThanhVien;
import utils.DatabaseConnection;

public class ThanhVienController {
    
    // 1. Lấy toàn bộ danh sách thành viên
    public List<ThanhVien> getAllThanhVien() {
        List<ThanhVien> list = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM ThanhVien";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new ThanhVien(
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

    // 2. Kiểm tra trùng Mã TV
    public boolean checkTonTai(String maTV) {
        try (Connection con = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM ThanhVien WHERE MaTV=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, maTV);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3. Thêm mới
    public boolean themThanhVien(ThanhVien tv) {
        try (Connection con = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO ThanhVien(MaTV, TenTV, SoDienThoai, NhomMau) VALUES(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, tv.getMaTV());
            pst.setString(2, tv.getTenTV());
            pst.setString(3, tv.getSoDienThoai());
            pst.setString(4, tv.getNhomMau());
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4. Sửa
    public boolean suaThanhVien(ThanhVien tv) {
        try (Connection con = DatabaseConnection.getConnection()) {
            String sql = "UPDATE ThanhVien SET TenTV=?, SoDienThoai=?, NhomMau=? WHERE MaTV=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, tv.getTenTV());
            pst.setString(2, tv.getSoDienThoai());
            pst.setString(3, tv.getNhomMau());
            pst.setString(4, tv.getMaTV());
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 5. Xóa
    public boolean xoaThanhVien(String maTV) {
        try (Connection con = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM ThanhVien WHERE MaTV=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, maTV);
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
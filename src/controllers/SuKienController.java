package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import models.SuKien;
import utils.DatabaseConnection;

public class SuKienController {

    // 1. Lấy danh sách sự kiện (có hỗ trợ tìm kiếm)
    public List<SuKien> getDanhSachSuKien(String keyword) {
        List<SuKien> list = new ArrayList<>();
        String sql;
        if (keyword == null || keyword.trim().isEmpty()) {
            sql = "SELECT MaSK, TenSK, NgayToChuc FROM SuKien ORDER BY NgayToChuc DESC";
        } else {
            sql = "SELECT MaSK, TenSK, NgayToChuc FROM SuKien WHERE MaSK LIKE ? OR TenSK LIKE ? ORDER BY NgayToChuc DESC";
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (keyword != null && !keyword.trim().isEmpty()) {
                String param = "%" + keyword.trim() + "%";
                pstmt.setString(1, param);
                pstmt.setString(2, param);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new SuKien(
                        rs.getString("MaSK"), 
                        rs.getString("TenSK"), 
                        rs.getString("NgayToChuc")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. Kiểm tra trùng Mã Sự kiện
    public boolean checkTonTai(String maSK) {
        try (Connection con = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM SuKien WHERE MaSK=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, maSK);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3. Thêm sự kiện
    public boolean themSuKien(SuKien sk) {
        String sql = "INSERT INTO SuKien VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, sk.getMaSK());
            pstmt.setString(2, sk.getTenSK());
            
            // Xử lý nếu ngày để trống
            if (sk.getNgayToChuc() == null || sk.getNgayToChuc().isEmpty()) {
                pstmt.setNull(3, Types.DATE);
            } else {
                pstmt.setString(3, sk.getNgayToChuc());
            }
            
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4. Sửa sự kiện
    public boolean suaSuKien(SuKien sk) {
        String sql = "UPDATE SuKien SET TenSK = ?, NgayToChuc = ? WHERE MaSK = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, sk.getTenSK());
            
            if (sk.getNgayToChuc() == null || sk.getNgayToChuc().isEmpty()) {
                pstmt.setNull(2, Types.DATE);
            } else {
                pstmt.setString(2, sk.getNgayToChuc());
            }
            
            pstmt.setString(3, sk.getMaSK());
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 5. Xóa sự kiện
    public boolean xoaSuKien(String maSK) {
        String sql = "DELETE FROM SuKien WHERE MaSK = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maSK);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
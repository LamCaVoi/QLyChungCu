package com.temp.DAO;

import com.temp.models.Phong.Phong;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PhongJdbcDao implements DAO<Phong> {

    private final JdbcTemplate jdbcTemplate;

    public PhongJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<Phong> rowMapper = (rs, rowNum) -> {
        Phong phg = new Phong();
        phg.setID(rs.getInt("ID"));
        phg.setDienTich(rs.getDouble("Dien_Tich"));
        phg.setGiaTien(rs.getLong("Gia_Tien"));
        phg.setTrangThai(rs.getString("Trang_Thai"));
        return phg;
    };

    @Override
    public RowMapper<Phong> getRowMapper() {
        return (rs, rowNum) -> {
            Phong phg = new Phong();
            phg.setID(rs.getInt("ID"));
            phg.setDienTich(rs.getDouble("Dien_Tich"));
            phg.setGiaTien(rs.getLong("Gia_Tien"));
            phg.setTrangThai(rs.getString("Trang_Thai"));
            return phg;
        };
    }

    @Override
    public void getValue(JTable table, String searchValue) {
        String sql = "SELECT * FROM Phong WHERE CONCAT(id, dien_tich, gia_tien, trang_thai) LIKE ? ORDER BY id DESC";
        List<Phong> rows = jdbcTemplate.query(sql, new Object[]{"%" + searchValue + "%"}, rowMapper);

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows
        for (Phong phong : rows) {
            model.addRow(new Object[]{phong.getID(), phong.getDienTich(), phong.getGiaTien(), phong.getTrangThai()});
        }
    }

   

    @Override
    public void delete(int id) {
        int yesOrNo = JOptionPane.showConfirmDialog(null, "Tất cả người thuê của Phòng này sẽ bị xóa", "Phòng sẽ bị xóa", JOptionPane.OK_CANCEL_OPTION, 0);
        if (yesOrNo == JOptionPane.OK_OPTION) {
            jdbcTemplate.update("Delete from Phong where ID = ?", id);
            jdbcTemplate.update("Delete from NguoiThue where ID_Phong = ?", id);
        }
    }

    @Override
    public void create(Phong t) {
        String sql = "INSERT INTO Phong (Dien_Tich, Gia_Tien, Trang_Thai) VALUES(?, ?, ?)";
        jdbcTemplate.update(sql, t.getDienTich(), t.getGiaTien(), t.getTrangThai());
        JOptionPane.showMessageDialog(null, "Thêm phòng thành công. Phòng mới thêm vào sẽ có trạng thái còn trống");
    }

    @Override
    public void update(Phong t, int id) {
        try {
            String sql = "update Phong set dien_tich=?, gia_tien=?, trang_thai=? where id=?";
            jdbcTemplate.update(sql, t.getDienTich(), t.getGiaTien(), t.getTrangThai(), id);
            JOptionPane.showMessageDialog(null, "Cập nhật phòng thành công. Lưu ý: Bạn sẽ không thể chỉnh sửa trạng thái.");
        } catch (Exception e) {

        }
    }

    public String getRoomStatus(int roomId) {
        String sql = "Select Trang_Thai from Phong where ID = " + Integer.toString(roomId);
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    public void updateRoomStatus(int roomId) {
        String trangThai = getRoomStatus(roomId);
        String trangThai1 = trangThai.equals("Còn trống") ? "Đang được sử dụng" : "Còn trống";
        String sql = "UPDATE Phong SET Trang_Thai = '" + trangThai1 + "' WHERE ID = ?";
        jdbcTemplate.update(sql, roomId);
    }

    public long getRoomPrize(int idPhg) {
        String sql = "SELECT Gia_Tien FROM Phong WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, idPhg);
    }

}

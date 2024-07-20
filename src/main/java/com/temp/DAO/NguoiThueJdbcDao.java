
package com.temp.DAO;

import com.temp.Services.Services;
import com.temp.models.NguoiThue.NguoiThue;
import java.sql.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

@Repository
public class NguoiThueJdbcDao implements DAO<NguoiThue> {

    private static final Logger log = LoggerFactory.getLogger(NguoiThueJdbcDao.class);
    private final JdbcTemplate jdbcTemplate;

    public NguoiThueJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<NguoiThue> rowMapper = (rs, rowNum) -> {
        NguoiThue nt = new NguoiThue();
        nt.setId(rs.getInt("ID"));
        nt.setTen(rs.getString("Ten"));
        nt.setSdt(rs.getString("So_Dien_Thoai"));
        nt.setNgaySinh(rs.getString("Ngay_Thang_Nam_Sinh"));
        nt.setQueQuan(rs.getString("Que_Quan"));
        nt.setCccd(rs.getString("So_CCCD"));
        nt.setIdPhg(rs.getInt("ID_Phong"));
        return nt;
    };
    
    @Override
    public void create(NguoiThue nguoiThue) {
        String sql = "INSERT INTO NguoiThue (Ten, So_Dien_Thoai, Ngay_Thang_Nam_Sinh, Que_Quan, So_CCCD, ID_Phong) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, nguoiThue.getTen(), nguoiThue.getSdt(), nguoiThue.getNgaySinh(), nguoiThue.getQueQuan(), nguoiThue.getCccd(), nguoiThue.getIdPhg());
            JOptionPane.showMessageDialog(null, "Thêm người thuê thành công");
        } catch (Exception e) {
            
        }
    }


    @Override
    public void update(NguoiThue nguoiThue, int id) {
        String sql = "UPDATE NguoiThue SET Ten = ?, So_Dien_Thoai = ?, Ngay_Thang_Nam_Sinh = ?, Que_Quan = ?, So_CCCD = ?, ID_Phong = ? WHERE id = ?";
        try {
            jdbcTemplate.update(sql, nguoiThue.getTen(), nguoiThue.getSdt(), nguoiThue.getNgaySinh(), nguoiThue.getQueQuan(), nguoiThue.getCccd(), nguoiThue.getIdPhg(), id);
            JOptionPane.showMessageDialog(null, "Cập nhật người thuê thành công");
        } catch (Exception e) {
            
        }
    }

    @Override
    public void delete(int id) {
        int yesOrNo = JOptionPane.showConfirmDialog(null, "Người thuê này sẽ bị xóa", "Đã xóa người thuê", JOptionPane.OK_CANCEL_OPTION, 0);
        if (yesOrNo == JOptionPane.OK_OPTION) {
            try {
                jdbcTemplate.update("DELETE FROM NguoiThue WHERE ID = ?", id);
                JOptionPane.showMessageDialog(null, "Đã xóa người thuê thành công");
            } catch (Exception e) {
                
            }
        }
    }

    @Override
    public void getValue(JTable table, String searchValue) {
        String sql = "SELECT * FROM NguoiThue WHERE CONCAT(ID, Ten, So_Dien_Thoai, Ngay_Thang_Nam_Sinh, Que_Quan, So_CCCD, ID_Phong) LIKE ? ORDER BY id DESC";
        List<NguoiThue> nguoiThueList = jdbcTemplate.query(sql, new Object[]{"%" + searchValue + "%"}, rowMapper);

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows
        for (NguoiThue nguoiThue : nguoiThueList) {
            model.addRow(new Object[]{nguoiThue.getId(), nguoiThue.getIdPhg(), nguoiThue.getTen(), nguoiThue.getSdt(), nguoiThue.getNgaySinh(), nguoiThue.getQueQuan(), nguoiThue.getCccd()});
        }
    }

    @Override
    public RowMapper<NguoiThue> getRowMapper() {
        return (rs, rowNum) -> {
        NguoiThue nt = new NguoiThue();
        nt.setId(rs.getInt("ID"));
        nt.setTen(rs.getString("Ten"));
        nt.setSdt(rs.getString("So_Dien_Thoai"));
        nt.setNgaySinh(rs.getString("Ngay_Thang_Nam_Sinh"));
        nt.setQueQuan(rs.getString("Que_Quan"));
        nt.setCccd(rs.getString("So_CCCD"));
        nt.setIdPhg(rs.getInt("ID_Phong"));
        return nt;
    };
        
    }

    public boolean isCCCDExist(String text) {
        String sql = "select COUNT(*) FROM NguoiThue where So_CCCD = ?";
        int rows = jdbcTemplate.queryForObject(sql, Integer.class, text);
        return 0 < rows;
    }

    public boolean isPhoneExist(String sdt) {
        String sql = "select COUNT(*) FROM NguoiThue where So_Dien_Thoai = ?";
        int rows = jdbcTemplate.queryForObject(sql, Integer.class, sdt);
        return 0 < rows;
    }

    public boolean isRoomEmpty(int roomId) {
        String sql = "SELECT COUNT(*) FROM NguoiThue WHERE ID_Phong = ? ";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, roomId);
        return count == 0;
    }

    public int searchTenantRoom(int idNguoiThue) {
        // SQL query to find the room ID for the given tenant ID
        String sql = "SELECT ID_Phong FROM NguoiThue WHERE ID = ?";
        int idPhg = jdbcTemplate.queryForObject(sql, Integer.class, idNguoiThue);
        return idPhg;
    }

    public List<Integer> getAllByIdPhg(int idPhg) {
        String sql = "SELECT ID FROM NguoiThue WHERE ID_Phong = ?";
        return jdbcTemplate.query(sql, new Object[]{idPhg}, (rs, rowNum) -> rs.getInt("ID"));
    }
    
}

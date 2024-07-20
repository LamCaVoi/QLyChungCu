package com.temp.DAO;

import com.temp.Services.Services;
import com.temp.models.DichVu.TienTro;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TienTroJdbcDao implements DAO<TienTro>{
    private static final Logger log = LoggerFactory.getLogger(TienTroJdbcDao.class);
    private final JdbcTemplate jdbcTemplate;

    RowMapper<TienTro> rowMapper = (rs, rowNum) -> {
            TienTro tienTro = new TienTro();
            tienTro.setID(rs.getInt("ID"));
            tienTro.setIDPhg(rs.getInt("IDPhong"));
            tienTro.setDiscount(rs.getInt("Discount"));
            tienTro.setTime(rs.getString("Date_Time"));
            tienTro.setSoNuoc(rs.getInt("So_Nuoc"));
            tienTro.setSoDien(rs.getInt("So_Dien"));
            tienTro.setServiceFee(rs.getLong("Service_Fee"));
            tienTro.setTotal(rs.getLong("Total"));
            return tienTro;
        };
    
    public TienTroJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    

    @Override
    public void create(TienTro t) {
        String sql = "Insert into TienTro(Discount, IDPhong, Date_Time, So_Nuoc, So_Dien, Service_Fee, Total) values (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, t.getDiscount(),t.getIDPhg(), t.getTime(), t.getSoNuoc(), t.getSoDien(), t.getServiceFee(), t.getTotal());
//        System.out.println("New Course Added!!!"*(row==1) + t.getTime());
        JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công");
        
    }

    @Override
    public void update(TienTro t, int id) {
        String sql = "Update TienTro set Discount = ?,IDPhong=?, Date_Time = ?, So_Nuoc=?, So_Dien=?, Service_Fee=?, Total=? where ID=?";
        jdbcTemplate.update(sql, t.getDiscount(), t.getIDPhg(), t.getTime(), t.getSoNuoc(), t.getSoDien(), t.getServiceFee(), t.getTotal(), id);
        JOptionPane.showMessageDialog(null, "Cập nhật hóa đơn thành công");
    }

    @Override
    public void delete(int id) {
        int yesOrNo = JOptionPane.showConfirmDialog(null, "Hóa đơn này sẽ bị xóa", "Đã xóa hóa đơn", JOptionPane.OK_CANCEL_OPTION, 0);
        if (yesOrNo == JOptionPane.OK_OPTION) {
            String sql = "Delete from TienTro where ID=?";
            jdbcTemplate.update(sql, id);
            JOptionPane.showMessageDialog(null, "Xóa thành công");
        }

    }

    @Override
    public void getValue(JTable table, String searchValue) {
        String sql = "SELECT * FROM TienTro WHERE CONCAT(ID, IDPhong, Date_Time, So_Nuoc,So_Dien, Discount, Service_Fee,Total) LIKE ? ORDER BY id DESC";
        List<TienTro> rows = jdbcTemplate.query(sql, new Object[]{"%" + searchValue + "%"}, rowMapper);

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows
        for (TienTro t : rows) {
            model.addRow(new Object[]{t.getID(), t.getIDPhg(), t.getTime(), t.getSoNuoc(), t.getSoDien(), t.getDiscount(), t.getServiceFee(), t.getTotal()});
        }
    }

    @Override
    public RowMapper<TienTro> getRowMapper() {
        return (rs, rowNum) -> {
            TienTro tienTro = new TienTro();
            tienTro.setID(rs.getInt("ID"));
            tienTro.setIDPhg(rs.getInt("IDPhong"));
            tienTro.setDiscount(rs.getInt("Discount"));
            tienTro.setTime(rs.getString("Date_Time"));
            tienTro.setSoNuoc(rs.getInt("So_Nuoc"));
            tienTro.setSoDien(rs.getInt("So_Dien"));
            tienTro.setServiceFee(rs.getLong("Service_Fee"));
            tienTro.setTotal(rs.getLong("Total"));
            return tienTro;
        };
    }

    public String getMostRecentHoaDon(int roomId) {
        String sql = "SELECT MAX(Date_Time) FROM TienTro WHERE IDPhong = ?";
        String dateStr = jdbcTemplate.queryForObject(sql, new Object[]{roomId}, String.class);
        if (dateStr != null) {
            return dateStr;
        }
        return null;
    }
}

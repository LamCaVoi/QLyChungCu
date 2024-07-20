package com.temp.Services;

import com.temp.DAO.NguoiThueJdbcDao;
import com.temp.DAO.PhongJdbcDao;
import com.temp.DAO.TienTroJdbcDao;
import com.temp.models.DichVu.TienTro;
import com.temp.models.NguoiThue.NguoiThue;
import com.temp.models.Phong.Phong;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class Services {

    private static PhongJdbcDao daoPhong;
    private static NguoiThueJdbcDao daoNguoiThue;
    private static TienTroJdbcDao daoTienTro;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Services(PhongJdbcDao daoPhong, NguoiThueJdbcDao daoNguoiThue, TienTroJdbcDao daoTienTro, JdbcTemplate jdbcTemplate) {
        Services.daoNguoiThue = daoNguoiThue;
        Services.daoPhong = daoPhong;
        Services.daoTienTro = daoTienTro;
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMaxID(String tableName) {
        String sql = "SELECT MAX(id) FROM " + tableName;
        Integer maxID = jdbcTemplate.queryForObject(sql, Integer.class);
        return (maxID != null) ? maxID + 1 : 1;
    }

    public boolean isIdExist(int id, String tableName) {
        String sql = "select COUNT(*) FROM " + tableName + " where id = ?";
        int rows = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return 0 < rows;
    }

    public void createPhong(double dienTich, long giaTien, String trangThai) {
        Phong phg = new Phong(dienTich, giaTien, trangThai);
        daoPhong.create(phg);
    }

    public boolean createNguoiThue(String hoVaTen, String soDienThoai, String date, String queQuan, String soCCCD, int idPhong) {

        if (!isIdExist(idPhong, "Phong")) {

            JOptionPane.showMessageDialog(null, "ID phòng này không tồn tại", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (daoNguoiThue.isCCCDExist(soCCCD)) {
            JOptionPane.showMessageDialog(null, "Số CCCD/CMND này đã tồn tại", "Lưu ý", JOptionPane.WARNING_MESSAGE);
        }
        if (daoNguoiThue.isPhoneExist(soDienThoai)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại này đã tồn tại", "Lưu ý", JOptionPane.WARNING_MESSAGE);
        }

        if (daoNguoiThue.isRoomEmpty(idPhong)) {
            daoPhong.updateRoomStatus(idPhong);
        }

        NguoiThue nt = new NguoiThue(hoVaTen, soDienThoai, date, queQuan, soCCCD, idPhong);
        daoNguoiThue.create(nt);
        return true;
    }

    public void updatePhong(int id, double dienTich, long giaTien) {
        Phong phg = new Phong(dienTich, giaTien);
        daoPhong.update(phg, id);
    }
    
    public void deletePhong(int id) {
        daoPhong.delete(id);
        List<Integer> lst = daoNguoiThue.getAllByIdPhg(id);
        for (Integer idNT : lst) {
            daoNguoiThue.delete(idNT);
        }
    }

    public void getValuePhong(JTable table, String searchValue) {
        daoPhong.getValue(table, searchValue);
    }

    public void updateNguoiThue(String hoVaTen, String soDienThoai, String date, String queQuan, String soCCCD, int idPhong, int idNguoiThue) {
        int exRoom = daoNguoiThue.searchTenantRoom(idNguoiThue);

        if (daoNguoiThue.isRoomEmpty(idPhong)) {
            daoPhong.updateRoomStatus(idPhong);
        }
        NguoiThue nt = new NguoiThue(hoVaTen, soDienThoai, date, queQuan, soCCCD, idPhong);
        daoNguoiThue.update(nt, idNguoiThue);

        if (exRoom != idPhong && daoNguoiThue.isRoomEmpty(exRoom)) {
            daoPhong.updateRoomStatus(exRoom);
        }
    }

    public void deleteNguoiThue(int id, int idPhg) {
        daoNguoiThue.delete(id);
        if (daoNguoiThue.isRoomEmpty(idPhg)) {
            daoPhong.updateRoomStatus(idPhg);
        }
    }

    public void getValueNguoiThue(JTable table, String searchValue) {
        daoNguoiThue.getValue(table, searchValue);
    }

    public void searchIDPhongForNguoiThue(JTable jTable, String id) {
        String sql = "Select * from NguoiThue WHERE ID_Phong = ?";
        List<NguoiThue> rows = jdbcTemplate.query(sql, new Object[]{id}, getRowMapperNguoiThue());

        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        model.setRowCount(0);

        for (NguoiThue nguoiThue : rows) {
            model.addRow(new Object[]{nguoiThue.getId(), nguoiThue.getIdPhg(), nguoiThue.getTen(), nguoiThue.getSdt(), nguoiThue.getNgaySinh(), nguoiThue.getQueQuan(), nguoiThue.getCccd()});
        }
    }

    public void searchIDPhongForHoaDon(JTable jTable, String id) {
        String sql = "Select * from TienTro WHERE IDPhong = ?";
        List<TienTro> rows = jdbcTemplate.query(sql, new Object[]{id}, getRowMapperTienTro());

        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        model.setRowCount(0);

        for (TienTro t : rows) {
            model.addRow(new Object[]{t.getID(), t.getDiscount(), t.getIDPhg(), t.getTime(), t.getSoNuoc(), t.getSoDien(), t.getServiceFee(), t.getTotal()});
        }
    }

    public RowMapper<Phong> getRowMapperPhong() {
        return daoPhong.getRowMapper();
    }

    public RowMapper<NguoiThue> getRowMapperNguoiThue() {
        return daoNguoiThue.getRowMapper();
    }

    public RowMapper<TienTro> getRowMapperTienTro() {
        return daoTienTro.getRowMapper();
    }

    public void getValueTienTro(JTable table, String searchValue) {
        daoTienTro.getValue(table, searchValue);
    }


    public boolean createTienTro(int idPhong, String time, int tienNuoc, int tienDien, int discount) {
        if (!isIdExist(idPhong, "Phong")) {

            JOptionPane.showMessageDialog(null, "ID phòng này không tồn tại", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (daoNguoiThue.isRoomEmpty(idPhong)) {
            JOptionPane.showMessageDialog(null, "ID phòng này không có người thuê", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        LocalDate timeFormatted = LocalDate.parse(time, DATE_FORMATTER);
        String mostRecentDate = daoTienTro.getMostRecentHoaDon(idPhong);
        if (mostRecentDate != null) {
            LocalDate date = LocalDate.parse(mostRecentDate, DATE_FORMATTER);
            if (!timeFormatted.isAfter(date.plusDays(30))) {
                JOptionPane.showMessageDialog(null, "Ngày nhập hóa đơn trước của phòng chưa qua 30 ngày", "Error", JOptionPane.ERROR_MESSAGE);
                return false; // New date is not at least 30 days apart
            }
        }

        TienTro t = new TienTro(idPhong, discount, time, tienDien, tienNuoc, daoPhong.getRoomPrize(idPhong));
        daoTienTro.create(t);
        return true;
    }

    public void deleteTienTro(int id) {
        daoTienTro.delete(id);
    }

    public void updateTienTro(int id, int idPhong, String time, int tienNuoc, int tienDien, int giamGia) {
        if (!isIdExist(idPhong, "Phong")) {

            JOptionPane.showMessageDialog(null, "ID phòng này không tồn tại", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (daoNguoiThue.isRoomEmpty(idPhong)) {
            JOptionPane.showMessageDialog(null, "ID phòng này không có người thuê", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        TienTro t = new TienTro(idPhong, giamGia, time, tienDien, tienNuoc, daoPhong.getRoomPrize(idPhong));
        daoTienTro.update(t, id);
    }

    public void uploadPic(int idPhg, String path) throws FileNotFoundException {
        InputStream iS = new FileInputStream(new File(path));
        String sql = "Insert into ROOMPIC (ID_Phong, Pic) Values (?, ?)";
        jdbcTemplate.update(sql, idPhg, iS);
    }

}

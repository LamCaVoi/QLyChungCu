package com.temp.DAO;

import com.temp.models.Phong.Phong;
import java.util.List;
import java.util.Optional;
import javax.swing.JTable;
import org.springframework.jdbc.core.RowMapper;


public interface DAO<T> {
    
    void create(T t);
    
    void update(T t, int id);
    
    void delete(int id);
    
    public void getValue(JTable table, String searchValue);
    
    public RowMapper<T> getRowMapper();
}

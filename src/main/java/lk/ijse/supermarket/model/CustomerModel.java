package lk.ijse.supermarket.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import lk.ijse.supermarket.db.DBConnection;
import lk.ijse.supermarket.dto.CustomerDTO;

public class CustomerModel {

    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        
        String sql = "INSERT INTO customer (name, address, salary) VALUES (?,?,?)";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setString(1, customerDTO.getName());
        pstm.setString(2, customerDTO.getAddress());
        pstm.setDouble(3, customerDTO.getSalary());
        
        int result = pstm.executeUpdate();
        
        return result>0;
    }
    
    public void searchCustomer() {}
    
    public void updateCustomer() {}
    
    public void deleteCustomer() {}
    
}

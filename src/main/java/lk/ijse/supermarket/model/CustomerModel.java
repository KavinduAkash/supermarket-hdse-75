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
    
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException  {
    
            Connection conn = DBConnection.getInstance().getConnection();
       
            String sql = "UPDATE customer SET name=?, address=?, salary=? WHERE id=?";
                
            PreparedStatement pstm = conn.prepareStatement(sql);
                
            pstm.setString(1, customerDTO.getName());
            pstm.setString(2, customerDTO.getAddress());
            pstm.setDouble(3, customerDTO.getSalary());
            pstm.setInt(4, customerDTO.getId());
                
            int result = pstm.executeUpdate();
            
            return result>0;
    }
    
    public boolean deleteCustomer(String id) throws SQLException {
    
            Connection conn = DBConnection.getInstance().getConnection();
       
            String sql = "DELETE FROM customer WHERE id=?";
                
            PreparedStatement pstm = conn.prepareStatement(sql);
          
            pstm.setInt(1, Integer.parseInt(id));
                
            int result = pstm.executeUpdate();
        
            return result>0;
    }
    
}

package lk.ijse.supermarket.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import lk.ijse.supermarket.db.DBConnection;

public class CustomerModel {

    public boolean saveCustomer(String name, String address, double salary) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        
        String sql = "INSERT INTO customer (name, address, salary) VALUES (?,?,?)";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setString(1, name);
        pstm.setString(2, address);
        pstm.setDouble(3, salary);
        
        int result = pstm.executeUpdate();
        
        return result>0;
    }
    
    public void searchCustomer() {}
    
    public void updateCustomer() {}
    
    public void deleteCustomer() {}
    
}

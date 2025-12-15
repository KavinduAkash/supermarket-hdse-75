package lk.ijse.supermarket.model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lk.ijse.supermarket.db.DBConnection;
import lk.ijse.supermarket.dto.CustomerDTO;
import lk.ijse.supermarket.util.CrudUtil;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

public class CustomerModel {

    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException {
        /*
        Connection conn = DBConnection.getInstance().getConnection();
        
        String sql = "INSERT INTO customer (name, address, salary) VALUES (?,?,?)";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setString(1, customerDTO.getName());
        pstm.setString(2, customerDTO.getAddress());
        pstm.setDouble(3, customerDTO.getSalary());
        
        int result = pstm.executeUpdate();
        
        return result>0;
        */
        
        boolean result = CrudUtil.execute("INSERT INTO customer (name, address, salary) VALUES (?,?,?)", customerDTO.getName(), customerDTO.getAddress(), customerDTO.getSalary());
        return result;
    }
    
    public CustomerDTO searchCustomer(String id) throws SQLException {
    
        /*
        Connection conn = DBConnection.getInstance().getConnection();
                
        String sql = "SELECT * FROM customer WHERE id=?";
                
        PreparedStatement ptsm = conn.prepareStatement(sql);
        ptsm.setInt(1, Integer.parseInt(id));
                
        ResultSet rs = ptsm.executeQuery();
        */
        
        ResultSet rs = CrudUtil.execute("SELECT * FROM customer WHERE id=?", Integer.parseInt(id));
        
        CustomerDTO customerDTO = null;
        
        if(rs.next()) {
            int cusId = rs.getInt("id");
            String cusName = rs.getString("name");
            String cusAddress = rs.getString("address");
            double cusSalary = rs.getDouble("salary");
            
            customerDTO = new CustomerDTO(cusId, cusName, cusAddress, cusSalary);
        }
        
        return customerDTO;
        
    }
    
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException  {
    
            /*
            Connection conn = DBConnection.getInstance().getConnection();
       
            String sql = "UPDATE customer SET name=?, address=?, salary=? WHERE id=?";
                
            PreparedStatement pstm = conn.prepareStatement(sql);
                
            pstm.setString(1, customerDTO.getName());
            pstm.setString(2, customerDTO.getAddress());
            pstm.setDouble(3, customerDTO.getSalary());
            pstm.setInt(4, customerDTO.getId());
                
            int result = pstm.executeUpdate();
            
            return result>0;
            */
            
            boolean result = CrudUtil.execute("UPDATE customer SET name=?, address=?, salary=? WHERE id=?",customerDTO.getName(),customerDTO.getAddress(), customerDTO.getSalary(), customerDTO.getId());
            return result;
    }
    
    public boolean deleteCustomer(String id) throws SQLException {
    
            /*
            Connection conn = DBConnection.getInstance().getConnection();
       
            String sql = "DELETE FROM customer WHERE id=?";
                
            PreparedStatement pstm = conn.prepareStatement(sql);
          
            pstm.setInt(1, Integer.parseInt(id));
                
            int result = pstm.executeUpdate();
        
            return result>0;
            */
            
            
            boolean result = CrudUtil.execute("DELETE FROM customer WHERE id=?", id);
            return result;
            
    }
    
    public List<CustomerDTO> getAllCustomers() throws SQLException {
    
        /*
        Connection conn = DBConnection.getInstance().getConnection();
        
        String sql = "SELECT * FROM customer";
        
        PreparedStatement ptsm = conn.prepareStatement(sql);
        
        ResultSet rs = ptsm.executeQuery();
        */
        
        ResultSet rs = CrudUtil.execute("SELECT * FROM customer");
        
        List<CustomerDTO> customerList = new ArrayList<>();
        
        while(rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String address = rs.getString("address");
            double salary = rs.getDouble("salary");
            
            CustomerDTO customerDTO = new CustomerDTO(id, name, address, salary);
            customerList.add(customerDTO);
        }
        
        return customerList;
    }
    
    public void printCustomerReport() throws SQLException, JRException {
        
        Connection conn = DBConnection.getInstance().getConnection();
        
        InputStream inputStream = getClass().getResourceAsStream("/lk/ijse/supermarket/reports/customer_report.jrxml");
        
        JasperReport jr = JasperCompileManager.compileReport(inputStream);
        
        JasperPrint jp = JasperFillManager.fillReport(jr, null, conn); // (jr, params, connection_obj)
    
        JasperViewer.viewReport(jp, false);

    }
}

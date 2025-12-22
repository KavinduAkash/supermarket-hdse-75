package lk.ijse.supermarket.model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lk.ijse.supermarket.db.DBConnection;
import lk.ijse.supermarket.dto.OrderDTO;
import lk.ijse.supermarket.dto.OrderItemDTO;
import lk.ijse.supermarket.util.CrudUtil;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

public class OrderModel {

    private final OrderItemModel orderItemModel = new OrderItemModel();
    
    public int placeOrder(OrderDTO orderDTO) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        
        try {
           
            int orderId = 0;
            
            conn.setAutoCommit(false);

            // orders table
            boolean result = CrudUtil.execute(
                    "INSERT INTO orders (date, customer_id) VALUES (?,?)", 
                    orderDTO.getDate(),
                    orderDTO.getCustomerId()
                    );

            // order_items table
            if(result) {
                ResultSet rs = CrudUtil.execute("SELECT id FROM orders ORDER BY id DESC LIMIT 1");
                if(rs.next()) {
                    orderId = rs.getInt("id");
                    boolean result2 = orderItemModel.saveOrderItems(orderDTO.getOrderItems(), orderId);
                }
            } else {
                throw new SQLException();
            }
            conn.commit();
            
            return orderId;
        } catch(Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
        
    }
    
    public void printInvoice(int orderId) throws SQLException, JRException {
        
        Connection conn = DBConnection.getInstance().getConnection();
        
        InputStream inputStream = getClass().getResourceAsStream("/lk/ijse/supermarket/reports/invoice.jrxml");
        
        JasperReport jr = JasperCompileManager.compileReport(inputStream);
        
        Map<String, Object> params = new HashMap<>();
        params.put("ORDER_ID", orderId);
        
        JasperPrint jp = JasperFillManager.fillReport(jr, params, conn); // (jr, params, connection_obj)
        
        JasperViewer.viewReport(jp, false);
    }
    
}

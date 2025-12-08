package lk.ijse.supermarket.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import lk.ijse.supermarket.dto.OrderDTO;
import lk.ijse.supermarket.dto.OrderItemDTO;
import lk.ijse.supermarket.util.CrudUtil;

public class OrderModel {

    private final OrderItemModel orderItemModel = new OrderItemModel(); 
    
    public boolean placeOrder(OrderDTO orderDTO) throws SQLException {
    
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
                int orderId = rs.getInt("id");
                boolean result2 = orderItemModel.saveOrderItems(orderDTO.getOrderItems(), orderId);
            }
        } else {
            throw new SQLException();
        }
        return true;
    }
    
}

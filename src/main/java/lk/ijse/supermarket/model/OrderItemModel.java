package lk.ijse.supermarket.model;

import java.sql.SQLException;
import java.util.List;
import lk.ijse.supermarket.dto.OrderItemDTO;
import lk.ijse.supermarket.util.CrudUtil;

public class OrderItemModel {

    private final ItemModel itemModel = new ItemModel();
    
    public void saveOrderItems(List<OrderItemDTO> orderItemList, int orderId) throws SQLException {
    
        for (OrderItemDTO orderItemDTO : orderItemList) {
            
            boolean result = CrudUtil.execute("INSERT INTO order_items VALUES (?, ?, ?, ?)", 
                    orderId,
                    orderItemDTO.getItemId(),
                    orderItemDTO.getQty(),
                    orderItemDTO.getUnitPrice()
            );
            
            if(result) {
                itemModel.decreseItemQty(orderItemDTO.getItemId(), orderItemDTO.getQty());
            } else {
            
            }
            
        }
        
    }
    
    
    
}

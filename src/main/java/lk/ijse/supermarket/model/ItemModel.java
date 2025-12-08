package lk.ijse.supermarket.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lk.ijse.supermarket.dto.ItemDTO;
import lk.ijse.supermarket.util.CrudUtil;

public class ItemModel {

   public List<ItemDTO> getAllItems() throws SQLException {
    
        ResultSet rs = CrudUtil.execute("SELECT * FROM item");
        
        List<ItemDTO> itemList = new ArrayList<>();
        
        while(rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int qty = rs.getInt("qty");
            double unitPrice = rs.getDouble("unit_price");
            
            itemList.add(new ItemDTO(id, name, qty, unitPrice));
        }
        
        return itemList;
    }
   
   public ItemDTO searchItem(int itemId) throws SQLException {
    
        ResultSet rs = CrudUtil.execute("SELECT * FROM item WHERE id=" + itemId);
        
        ItemDTO itemDTO = null;
        
        while(rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int qty = rs.getInt("qty");
            double unitPrice = rs.getDouble("unit_price");
            
            itemDTO = new ItemDTO(id, name, qty, unitPrice);
        }
        
        return itemDTO;
    }
   
   public boolean decreseItemQty(int itemId, int qty) throws SQLException {
       boolean result = CrudUtil.execute("UPDATE item SET qty=qty - ? WHERE id = ? AND qty >= ?", qty, itemId, qty);
       return result;
   }
    
}

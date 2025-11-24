package lk.ijse.supermarket.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import lk.ijse.supermarket.db.DBConnection;

public class CrudUtil {

    public static boolean execute(String sql, String id) throws SQLException {
    
        Connection conn = DBConnection.getInstance().getConnection();
        
        PreparedStatement ptsm = conn.prepareStatement(sql);
        
        ptsm.setInt(1, Integer.parseInt(id));
        
        int result = ptsm.executeUpdate();
        
        return result>0;
        
    }
    
}

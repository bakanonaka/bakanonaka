package model1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BrandDAO extends DAO {

	public ArrayList<BrandTO> getBrandList() {
		Connection conn = null;
   		PreparedStatement pstmt = null;
   		ResultSet rs = null;
   		
		ArrayList<BrandTO> brandList = new ArrayList<>();
		
		try {
   			
    		 conn = super.getDataSource().getConnection();
 			
 			String sql = "select * from brand";
 			pstmt = conn.prepareStatement(sql);
 			rs = pstmt.executeQuery();

 			while(rs.next()) {
 				BrandTO to = new BrandTO();

 				to.setNo(rs.getInt("no"));
 				to.setName(rs.getString("name"));
 				to.setEventBoardUrl(rs.getString("eventBoardUrl"));
 				to.setStoreUrl(rs.getString("storeUrl"));
 				
 				brandList.add(to);
 			}
    			
    		} catch (SQLException e) {
    			System.out.println("SQL 에러 : " + e.getMessage());
    		} finally {
    			if(conn != null)try {conn.close();}catch(SQLException e) {}
    			if(pstmt != null)try {pstmt.close();}catch(SQLException e) {}
    			if(rs != null)try {rs.close();}catch(SQLException e) {}
    		}
		
		
		return brandList;
	}

}

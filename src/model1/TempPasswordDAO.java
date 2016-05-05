package model1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TempPasswordDAO extends DAO {
	
	public int setTempPassword(MemberTO to) {
		int flag = 1;//0이면, 정상. 1이상이면 Error
		
		   Connection conn = null;
		   PreparedStatement pstmt = null;
		   
		   try {
			   conn = super.getDataSource().getConnection();
			   
			   String sql = "";
			   
				   sql = "update memberlist set password = ?"
				   		+ "where email = ?";
			   
				   pstmt = conn.prepareStatement(sql);
				   				   
				   pstmt.setString(1, to.getPassword());				   
				   pstmt.setString(2, to.getEmail());
		   

			   int result = pstmt.executeUpdate();
			   
			   if(result == 1) {	//결과 행수가 1행이기 때문에 정상
				   flag = 0;
			   
			   } else {
				   //비정상
			   }
			   
		   } catch (SQLException e) {
			   System.out.println("[SQL 에러] : " + e.getMessage());
		   } finally {
			   if(conn != null) try {conn.close();}catch(SQLException e) {}
			   if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
		   }
		
		return flag;
	} 
	
}

package model1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFBDAO extends DAO {

	public MemberTO loginFBOk(MemberTO to) {
		
		   Connection conn = null;
		   PreparedStatement pstmt = null;
		   ResultSet rs = null;
		   
		   try {
			   conn = super.getDataSource().getConnection();
			   
			   String sql = "select memNo, password, name from memberlist where id = ?";
			   pstmt = conn.prepareStatement(sql);
			   pstmt.setString(1, to.getId());
			   
			   rs = pstmt.executeQuery();
			   
			   while(rs.next()) {
				   to.setPassword(rs.getString("password"));
				   to.setName(rs.getString("name"));
				   to.setMemNo(rs.getInt("memNo"));
	    		}
			   
		   } catch (SQLException e) {
			   System.out.println("[SQL 에러] : " + e.getMessage());
		   } finally {
			   if(conn != null) try {conn.close();}catch(SQLException e) {}
			   if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			   if(rs != null)try {rs.close();}catch(SQLException e) {}
		   }
		
		return to;
	} 
	
}

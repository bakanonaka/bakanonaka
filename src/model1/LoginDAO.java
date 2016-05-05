package model1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO extends DAO {

	public MemberTO loginOk(MemberTO to) {
		
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
	
	public int logoutOk(MemberTO to) {
		int flag = 1;//0이면, 정상. 1이상이면 Error
		
		   Connection conn = null;
		   PreparedStatement pstmt = null;
		   
		   try {
			   conn = super.getDataSource().getConnection();
			   
//			   String sql = "update memberlist set loginDate = sysdate() where name = ?";
			   String sql = "update memberlist set loginDate = sysdate() where memNo = ?";
			   pstmt = conn.prepareStatement(sql);
//			   pstmt.setString(1, to.getName());
			   pstmt.setInt(1, to.getMemNo());
			   
			   
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

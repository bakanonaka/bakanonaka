package model1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckDAO extends DAO {

		public int checkIdOk(MemberTO to) {
			
			int flag = 1;//0이면, 정상. 1이상이면 Error
			
			   Connection conn = null;
			   PreparedStatement pstmt = null;
			   ResultSet rs = null;
			   
			   //확인
			   //System.out.println("123");
			   
			   try {
				   conn = super.getDataSource().getConnection();
				   
				   String sql = "select count(*) as count from memberlist where id=?";
				   pstmt = conn.prepareStatement(sql);
				   pstmt.setString(1, to.getId());
	
				   rs = pstmt.executeQuery();
				   
				   int rsint = 0;
				   while(rs.next()) {
					   rsint = rs.getInt("count");
		    		}
				   
				   if (rsint != 0) {
					   flag = 1;
				   } else {
					   flag = 0;
				   }

			   } catch (SQLException e) {
				   System.out.println("[SQL 에러] : " + e.getMessage());
			   } finally {
				   if(conn != null) try {conn.close();}catch(SQLException e) {}
				   if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
				   if(rs != null)try {rs.close();}catch(SQLException e) {}
			   }
			   
			return flag;
		} 
		
		public int checkNameOk(MemberTO to) {
			
			int flag = 1;//0이면, 정상. 1이상이면 Error
			
			   Connection conn = null;
			   PreparedStatement pstmt = null;
			   ResultSet rs = null;
			   
			   //확인
			   //System.out.println("123");
			   
			   try {
				   conn = super.getDataSource().getConnection();
				   
				   String sql = "select count(*) as count from memberlist where name=?";
				   pstmt = conn.prepareStatement(sql);
				   pstmt.setString(1, to.getName());
	
				   rs = pstmt.executeQuery();
				   
				   int rsint = 0;
				   while(rs.next()) {
					   rsint = rs.getInt("count");
		    		}
				   
				   if (rsint != 0) {
					   flag = 1;
				   } else {
					   flag = 0;
				   }
				  // System.out.println(flag);
			   } catch (SQLException e) {
				   System.out.println("[SQL 에러] : " + e.getMessage());
			   } finally {
				   if(conn != null) try {conn.close();}catch(SQLException e) {}
				   if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
				   if(rs != null)try {rs.close();}catch(SQLException e) {}
			   }
			   
			return flag;
		} 
		
		public int checkEmailOk(MemberTO to) {
			
			int flag = 1;//0이면, 정상. 1이상이면 Error
			
			   Connection conn = null;
			   PreparedStatement pstmt = null;
			   ResultSet rs = null;
			   
			   //확인
			   //System.out.println("123");
			   
			   try {
				   conn = super.getDataSource().getConnection();
				   
				   String sql = "select count(*) as count from memberlist where email=?";
				   pstmt = conn.prepareStatement(sql);
				   pstmt.setString(1, to.getEmail());
	
				   rs = pstmt.executeQuery();
				   
				   int rsint = 0;
				   while(rs.next()) {
					   rsint = rs.getInt("count");
		    		}
				   
				   if (rsint != 0) {
					   flag = 1;
				   } else {
					   flag = 0;
				   }
				  // System.out.println(flag);
			   } catch (SQLException e) {
				   System.out.println("[SQL 에러] : " + e.getMessage());
			   } finally {
				   if(conn != null) try {conn.close();}catch(SQLException e) {}
				   if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
				   if(rs != null)try {rs.close();}catch(SQLException e) {}
			   }
			   
			return flag;
		} 
}
	


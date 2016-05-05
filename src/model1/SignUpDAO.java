package model1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpDAO extends DAO {

		public int signUpOk(MemberTO to) {
			
			int flag = 1;//0이면, 정상. 1이상이면 Error
			
			   Connection conn = null;
			   PreparedStatement pstmt = null;
			   ResultSet rs = null;
			   
			   //확인
			   //System.out.println("123");
			   
			   try {
				   conn = super.getDataSource().getConnection();
				   
				   String sql = "insert into memberlist(id, password, name, email, receiveMailFlag, signUpDate, loginDate, likeColor, seasonOfLikeColor, numOfText, numOfComment)"
				   						+ "values (?, ?, ?, ?, ?, sysdate(), sysdate(), ?, ?, 0, 0)";
				   pstmt = conn.prepareStatement(sql);
				   pstmt.setString(1, to.getId());
				   pstmt.setString(2, to.getPassword());
				   pstmt.setString(3, to.getName());
				   pstmt.setString(4, to.getEmail());
				   pstmt.setInt(5, to.getReceiveMailFlag());
				   //pstmt.setString(6, to.getSignUpDate());
				   //pstmt.setString(7, to.getLoginDate());
				   pstmt.setInt(6, to.getLikeColor());
				   pstmt.setString(7, to.getSeasonOfLikeColor());
				   //pstmt.setInt(10, to.getNumOfText());
				   //pstmt.setInt(11, to.getNumOfComment());
	
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
				   if(rs != null)try {rs.close();}catch(SQLException e) {}
			   }
			
			return flag;
		}
		
		public int signUpFB(MemberTO to) {
			
			int flag = 1;//0이면, 정상. 1이상이면 Error
			
			   Connection conn = null;
			   PreparedStatement pstmt = null;
			   ResultSet rs = null;
			   
			   //확인
			   //System.out.println("123");
			   
			   try {
				   conn = super.getDataSource().getConnection();
				   
				   String sql = "insert into memberlist(id, password, name, email, receiveMailFlag, signUpDate, loginDate, likeColor, seasonOfLikeColor, numOfText, numOfComment)"
				   						+ "values (?, ?, ?, ?, 0, sysdate(), sysdate(), 0, '', 0, 0)";
				   pstmt = conn.prepareStatement(sql);
				   pstmt.setString(1, to.getId());
				   pstmt.setString(2, to.getPassword());
				   pstmt.setString(3, to.getName());
				   pstmt.setString(4, to.getEmail());
	
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
				   if(rs != null)try {rs.close();}catch(SQLException e) {}
			   }
			
			return flag;
		} 
}
	


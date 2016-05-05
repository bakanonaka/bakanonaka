package model1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModifyDAO extends DAO {

	public MemberTO modifyCheck(MemberTO to) {
		
		   Connection conn = null;
		   PreparedStatement pstmt = null;
		   ResultSet rs = null;
		   
		   try {
			   conn = super.getDataSource().getConnection();
			   
			   String sql = "select id, name, email, likecolor, seasonoflikecolor, receivemailflag from memberlist where id = ?";
			   pstmt = conn.prepareStatement(sql);
			   pstmt.setString(1, to.getId());
			   
			   rs = pstmt.executeQuery();
			   
			   while(rs.next()) {				   
				   to.setId(rs.getString("id"));
				   to.setName(rs.getString("name"));
				   to.setEmail(rs.getString("email"));
				   to.setLikeColor(rs.getInt("likecolor"));
				   to.setSeasonOfLikeColor(rs.getString("seasonoflikecolor"));
				   to.setReceiveMailFlag(rs.getInt("receivemailflag"));
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
	
	public int modifyOk(MemberTO to) {
		int flag = 1;//0이면, 정상. 1이상이면 Error
		
		   Connection conn = null;
		   PreparedStatement pstmt = null;
		   
		   try {
			   conn = super.getDataSource().getConnection();
			   
			   String sql = "";
			   
			   if (to.getPassword() != "") {
				
				   sql = "update memberlist set name = ?, password = ?, receiveMailFlag = ?, likecolor = ?, seasonoflikecolor = ? "
				   		+ "where id = ?";
			   
				   pstmt = conn.prepareStatement(sql);
				   
				   pstmt.setString(1, to.getName());
				   pstmt.setString(2, to.getPassword());
				   pstmt.setInt(3, to.getReceiveMailFlag());
				   pstmt.setInt(4, to.getLikeColor());
				   pstmt.setString(5, to.getSeasonOfLikeColor());
				   pstmt.setString(6, to.getId());
				   
			   } else {
				   sql = "update memberlist set name = ?, receiveMailFlag = ?, likecolor = ?, seasonoflikecolor = ? "
					   		+ "where id = ?";

				   pstmt = conn.prepareStatement(sql);
				   
				   pstmt.setString(1, to.getName());				   
				   pstmt.setInt(2, to.getReceiveMailFlag());
				   pstmt.setInt(3, to.getLikeColor());
				   pstmt.setString(4, to.getSeasonOfLikeColor());
				   pstmt.setString(5, to.getId());
			   }

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
	
	public int byebyeOk(MemberTO to, String why) {	//사용자 본인 탈퇴
		int flag = 1;//0이면, 정상. 1이상이면 Error
		
		   Connection conn = null;
		   PreparedStatement pstmt = null;
		   
		   try {
			   conn = super.getDataSource().getConnection();

			   String sql = "update memberlist set id = 'banned_self', password = '', receiveMailFlag = 0, "
			   		+ "likecolor = 0, seasonoflikecolor = '', email = '', dropOut = ?"
			   		+ "where id = ? and password = ?";
		   
			   pstmt = conn.prepareStatement(sql);
			   pstmt.setString(1, why);
			   pstmt.setString(2, to.getId());
			   pstmt.setString(3, to.getPassword());

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
	
	public int byebyeMembers(String email[]) {	//관리자에 의한 사용자 강퇴
		int flag = 1;//0이면, 정상. 1이상이면 Error
		
		   Connection conn = null;
		   PreparedStatement pstmt = null;
		   
		   try {
			   conn = super.getDataSource().getConnection();
			   			   
			   int result = 0;
			   
			   for (int i=0 ; i < email.length ; i++) {
		   					   			
				   	String sql = "update memberlist set id = 'banned_kick', password = '', receiveMailFlag = 0, "
					   		+ "likecolor = 0, seasonoflikecolor = '', name = ''"
					   		+ "where email = ?";
				   
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, email[i]);		
					result = pstmt.executeUpdate();			
					
					} 
		   
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

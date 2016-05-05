package model1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberListDAO extends DAO {
	
	public ArrayList<MemberTO> boardList() {
		Connection conn = null;
   		PreparedStatement pstmt = null;
   		ResultSet rs = null;
   		
   		ArrayList<MemberTO> boardList = new ArrayList<>();
		
   		try {
   			
      		 conn = super.getDataSource().getConnection();
      		 
			String sql = "select * from memberlist";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberTO to = new MemberTO();
				to.setMemNo(rs.getInt("memNo"));
				to.setId(rs.getString("id"));
				to.setName(rs.getString("name"));
				to.setEmail(rs.getString("email"));
				to.setReceiveMailFlag(rs.getInt("receiveMailFlag"));
				to.setSignUpDate(rs.getString("signUpDate"));
				to.setLoginDate(rs.getString("loginDate"));
				to.setLikeColor(rs.getInt("likeColor"));
				to.setSeasonOfLikeColor(rs.getString("seasonOfLikeColor"));
				

				boardList.add(to);
			}
      			
      		} catch (SQLException e) {
      			System.out.println("SQL 에러 : " + e.getMessage());
      		} finally {
      			if(conn != null)try {conn.close();}catch(SQLException e) {}
      			if(pstmt != null)try {pstmt.close();}catch(SQLException e) {}
      			if(rs != null)try {rs.close();}catch(SQLException e) {}
      		}
      		
      		return boardList;
	}
		
	public String memberSelectedOk(String[] memNos) {
		
		String emails = "";
		
		Connection conn = null;
   		PreparedStatement pstmt = null;
   		ResultSet rs = null;   				     		   		  		
   		
   		try {
   			conn = super.getDataSource().getConnection();
   			
			
	   		for (int i=0 ; i < memNos.length ; i++) {
	   			
//	   			System.out.println(memNos[i]);
	   			String sql = "select email from memberlist where memNo = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memNos[i]);		
				rs = pstmt.executeQuery();				
				
				while(rs.next()){													
//					System.out.println(rs.getString("email"));
					String em = rs.getString("email");	
					emails += em + ",";
//					System.out.println(emails);
				} 
				//emails += ";";
				
	   		}	   			
	   		//emails += "admin@naver.com";
			//System.out.println(emails);
						
      		} catch (SQLException e) {
      			System.out.println("SQL 에러 : " + e.getMessage());
      		} finally {
      			if(conn != null)try {conn.close();}catch(SQLException e) {}
      			if(pstmt != null)try {pstmt.close();}catch(SQLException e) {}
      			if(rs != null)try {rs.close();}catch(SQLException e) {}
      		}
      		
      		return emails;
	}
	
	
}

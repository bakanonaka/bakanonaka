package model1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class commentDAO extends DAO {
	
	public int commentOk(int memNo, int grp, String comment, String boardType) {
		
		int flag = 1;
		
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				
	  		 conn = super.getDataSource().getConnection();
	  		 
	  		String sql = "insert into commentlist(memNo, comment, boardType, grp, wdate) values (?, ?, ?, ?, sysdate())";
	  		
	  		
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			pstmt.setString(2, comment);
			pstmt.setString(3, boardType);
			pstmt.setInt(4, grp);			
			
			int result = pstmt.executeUpdate();
			   
			   if(result == 1) {	//결과 행수가 1행이기 때문에 정상
				   flag = 0;
			   
			   } else {
				   //비정상
			   }
			   
	  			
	  		} catch (SQLException e) {
	  			System.out.println("SQL 에러 : " + e.getMessage());
	  		} finally {
	  			if(conn != null)try {conn.close();}catch(SQLException e) {}
	  			if(pstmt != null)try {pstmt.close();}catch(SQLException e) {}
	  		}
	  		
	  		return flag;
	}
	
	public ArrayList<CommentTO> commentListOk(CommentTO commentList) {
							
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<CommentTO> commentLists = new ArrayList<>();
		
		try {
			
  		conn = super.getDataSource().getConnection();
  		 
//  		String sql = "select memNo, comment, boardType, grp, DATE_FORMAT(wdate, '%Y-%m-%d(%h:%i:%s)') as wdate from commentlist where grp=? and boardType=?";
  		String sql = "select m.memNo, m.name, comment, boardType, grp, DATE_FORMAT(wdate, '%Y-%m-%d(%h:%i:%s)') as wdate from commentlist c, memberlist m where grp=? and boardType=? and c.memNo = m.memNo order by wdate desc";
  		
  		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, commentList.getGrp());
		pstmt.setString(2, commentList.getBoardType());			
		
		rs = pstmt.executeQuery();
		   
		while(rs.next()){
			CommentTO to = new CommentTO();
			to.setName(rs.getString("m.name"));
			to.setComment(rs.getString("comment"));
			to.setWdate(rs.getString("wdate"));		
			
			commentLists.add(to);
			
		}
		//System.out.println(commentLists);
		
  		} catch (SQLException e) {
  			System.out.println("SQL 에러 : " + e.getMessage());
  		} finally {
  			if(conn != null)try {conn.close();}catch(SQLException e) {}
  			if(pstmt != null)try {pstmt.close();}catch(SQLException e) {}
  		}
  		
  		return commentLists;
	}

}

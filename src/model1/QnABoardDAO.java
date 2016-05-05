package model1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QnABoardDAO extends DAO {
	
public BoardListTO getQnaBoardList(BoardListTO listTO, int eventNo) {
		
		Connection conn = null;
   		PreparedStatement pstmt = null;
   		ResultSet rs = null;
   		
   		int cpage = listTO.getCpage();
//   		int recordPerPage = listTO.getRecordPerPage();
   		int recordPerPage = listTO.getRecordPerPageForQnaBoard();	//Qna게시판 전용 페이지 수
		int blockPerPage = listTO.getBlockPerPage();
   		
		try {

			conn = super.getDataSource().getConnection();

			String sql = "select q.*, m.name writer, e.title eventName from qnaboard q, memberlist m, event e where q.memNo = m.memNo and q.eventNo = e.no and q.eventNo = ? order by q.seq desc";
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pstmt.setInt(1, eventNo);
			rs = pstmt.executeQuery();

			// 전체 데이터 갯수 구하기
			rs.last();	//끝으로
			int totalRecord = rs.getRow(); // Retrieves the current row number.
			rs.beforeFirst();//처음으로

			// 전체 페이지 갯수 구하기

			int totalPage = (totalRecord > 0 && totalRecord % recordPerPage == 0) ? (totalRecord / recordPerPage)
					: (totalRecord / recordPerPage) + 1;
			listTO.setTotalPage(totalPage);

			int skip = (cpage - 1) * recordPerPage; // 페이지 번호 * 페이지당 데이터 갯수
			if (skip != 0) {
				rs.absolute(skip); // 3. ResultSet.TYPE_SCROLL_INSENSITIVE 옵션이
									// 있어야만 .absolute()를 쓸 수 있다.
			}
			
			ArrayList<QnABoardTO> qnaBoardList = new ArrayList<>();
			for (int i = 0; i < recordPerPage && rs.next(); i++) {
				QnABoardTO to = new QnABoardTO();

				to.setSeq(rs.getInt("seq"));
				to.setSubject(rs.getString("Subject"));
				to.setWdate(rs.getString("Wdate"));
				to.setContent(rs.getString("content"));
				to.setEventName(rs.getString("eventName"));
				to.setWriter(rs.getString("Writer").trim());
				// 데이터 입력하기.
				
				qnaBoardList.add(to);
			}

//			System.out.println("qnaBoardList 데이터 수 : " + qnaBoardList.size());
			
			listTO.setQnaBoardList(qnaBoardList);
			listTO.setStartBlock(((cpage - 1) / blockPerPage) * blockPerPage + 1);
			listTO.setEndBlock(listTO.getStartBlock() + blockPerPage);

		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
     		if(conn != null)try {conn.close();}catch(SQLException e) {}
     		if(pstmt != null)try {pstmt.close();}catch(SQLException e) {}
     		if(rs != null)try {rs.close();}catch(SQLException e) {}
     	}
     		
     	return listTO;
	}

	public QnABoardTO getBoardViewData(QnABoardTO to) {
		
		Connection conn = null;
   		PreparedStatement pstmt = null;
   		ResultSet rs = null;
   		
   		try {
   			
      		 conn = super.getDataSource().getConnection();
      		 
			String sql = "select q.*, m.name writer, e.title eventName from qnaboard q, memberlist m, event e where q.memNo = m.memNo and q.eventNo = e.no and q.seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, to.getSeq());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				to.setSubject(rs.getString("Subject"));
				to.setWdate(rs.getString("Wdate"));
				to.setContent(rs.getString("content"));
				to.setEventName(rs.getString("eventName"));
				to.setWriter(rs.getString("Writer").trim());
			}
      			
      		} catch (SQLException e) {
      			System.out.println("SQL 에러 : " + e.getMessage());
      		} finally {
      			if(conn != null)try {conn.close();}catch(SQLException e) {}
      			if(pstmt != null)try {pstmt.close();}catch(SQLException e) {}
      			if(rs != null)try {rs.close();}catch(SQLException e) {}
      		}
      		
      		return to;
	}
	
public QnABoardTO getBoardModifyViewData(QnABoardTO to) {
		
		Connection conn = null;
   		PreparedStatement pstmt = null;
   		ResultSet rs = null;
   		
   		try {
   			
      		 conn = super.getDataSource().getConnection();
      		 
			String sql = "select q.*, m.name writer, e.title eventName from qnaboard q, memberlist m, event e where q.memNo = m.memNo and q.eventNo = e.no and q.seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, to.getSeq());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				to.setSubject(rs.getString("Subject"));
				to.setWdate(rs.getString("Wdate"));
				to.setContent(rs.getString("content"));
				to.setEventName(rs.getString("eventName"));
				to.setWriter(rs.getString("Writer").trim());
			}
      			
      		} catch (SQLException e) {
      			System.out.println("SQL 에러 : " + e.getMessage());
      		} finally {
      			if(conn != null)try {conn.close();}catch(SQLException e) {}
      			if(pstmt != null)try {pstmt.close();}catch(SQLException e) {}
      			if(rs != null)try {rs.close();}catch(SQLException e) {}
      		}
      		
      		return to;
	}


	public int writeOk(QnABoardTO to, int memNo, int eventNo) {
		
		int flag = 1;
		
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			ArrayList<QnABoardTO> boardList = new ArrayList<>();
		
			try {
				
	  		 conn = super.getDataSource().getConnection();
	  		 
	  		String sql = "insert into qnaboard(subject, wdate, content, eventNo, memNo) values (?, sysdate(), ?, ?, ?)";
	  		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSubject());
			pstmt.setString(2, to.getContent());
			pstmt.setInt(3, eventNo);
			pstmt.setInt(4, memNo);
			
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

	public int modifyOk(QnABoardTO to, int seq, int eventNo) {
		
		int flag = 1;
		
		Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				
	  		 conn = super.getDataSource().getConnection();
	  		 
	  		String sql = "update qnaboard set subject = ?, content = ?, eventNo = ? where seq = ?";
	  		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSubject());
			pstmt.setString(2, to.getContent());
			pstmt.setInt(3, eventNo);
			pstmt.setInt(4, seq);
			
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
	
	public int deleteOk(int seq, int memNo) {
		
			int flag = 1;
		
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				
	  		 conn = super.getDataSource().getConnection();
	  		 
	  		String sql = "delete from qnaboard where seq = ? and memNo = ?";
	  		
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.setInt(2, memNo);
			
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
}

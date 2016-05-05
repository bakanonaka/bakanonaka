package model1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RequestBoardDAO extends DAO {
	
public BoardListTO getRequestBoardList(BoardListTO listTO) {
		
		Connection conn = null;
   		PreparedStatement pstmt = null;
   		ResultSet rs = null;
   		
   		int cpage = listTO.getCpage();
//		int recordPerPage = listTO.getRecordPerPage();
   		int recordPerPage = listTO.getRecordPerPageForRequestBoard();	//Request게시판 전용 페이지 수
		int blockPerPage = listTO.getBlockPerPage();
   		
		try {

			conn = super.getDataSource().getConnection();
			
			String sql = "select r.seq, r.subject, r.wdate, r.content, r.imgName requestImgName, b.name brandName, m.name writer from requestboard r, memberlist m, brand b where r.memNo = m.memNo and r.brandNo = b.no order by r.seq desc";
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); 
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
			
			ArrayList<RequestBoardTO> requestBoardList = new ArrayList<>();
			for (int i = 0; i < recordPerPage && rs.next(); i++) {
				RequestBoardTO to = new RequestBoardTO();

				to.setSeq(rs.getInt("seq"));
				to.setSubject(rs.getString("Subject"));
				to.setWdate(rs.getString("Wdate"));
				to.setContent(rs.getString("content"));
				to.setRequestImgName(rs.getString("requestImgName") == null? "" : rs.getString("requestImgName"));
				to.setBrandName(rs.getString("brandName"));
				to.setWriter(rs.getString("Writer").trim());
				// 데이터 입력하기.
				
				requestBoardList.add(to);
			}

//			System.out.println("RequestBoardList 데이터 수 : " + RequestBoardList.size());
			
			listTO.setRequestBoardList(requestBoardList);
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

public BoardListTO getRequestBoardListForBrand(BoardListTO listTO, String brandName) {
	
	Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int cpage = listTO.getCpage();
//	int recordPerPage = listTO.getRecordPerPage();
		int recordPerPage = listTO.getRecordPerPageForRequestBoard();	//Request게시판 전용 페이지 수
	int blockPerPage = listTO.getBlockPerPage();
		
	try {

		conn = super.getDataSource().getConnection();
		
		String sql = "select r.seq, r.subject, r.wdate, r.content, r.imgName requestImgName, b.name brandName, m.name writer from requestboard r, memberlist m, brand b where r.memNo = m.memNo and r.brandNo = b.no and b.name = ? order by r.seq desc";
		pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		pstmt.setString(1, brandName);
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
		
		ArrayList<RequestBoardTO> requestBoardList = new ArrayList<>();
		for (int i = 0; i < recordPerPage && rs.next(); i++) {
			RequestBoardTO to = new RequestBoardTO();

			to.setSeq(rs.getInt("seq"));
			to.setSubject(rs.getString("Subject"));
			to.setWdate(rs.getString("Wdate"));
			to.setContent(rs.getString("content"));
			to.setRequestImgName(rs.getString("requestImgName") == null? "" : rs.getString("requestImgName"));
			to.setBrandName(rs.getString("brandName"));
			to.setWriter(rs.getString("Writer").trim());
			// 데이터 입력하기.
			
			requestBoardList.add(to);
		}

//		System.out.println("RequestBoardList 데이터 수 : " + RequestBoardList.size());
		
		listTO.setRequestBoardList(requestBoardList);
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

	public RequestBoardTO getBoardViewData(RequestBoardTO to) {
		
		Connection conn = null;
   		PreparedStatement pstmt = null;
   		ResultSet rs = null;
   		
		
   		try {
   			
      		 conn = super.getDataSource().getConnection();
      		 
      		String sql = "select r.seq, r.subject, r.wdate, r.content, r.imgName requestImgName, b.name brandName, m.name writer from requestboard r, memberlist m, brand b where r.memNo = m.memNo and r.brandNo = b.no and seq = ?";
      		pstmt = conn.prepareStatement(sql);
      		pstmt.setInt(1, to.getSeq());
      		rs = pstmt.executeQuery();

			while (rs.next()) {
				to.setSubject(rs.getString("Subject"));
				to.setWdate(rs.getString("Wdate"));
				to.setContent(rs.getString("content"));
				to.setRequestImgName(rs.getString("requestImgName") == null? "" : rs.getString("requestImgName"));
				to.setBrandName(rs.getString("brandName"));
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
	
public RequestBoardTO getBoardModifyViewData(RequestBoardTO to) {
		
		Connection conn = null;
   		PreparedStatement pstmt = null;
   		ResultSet rs = null;
   		
   		try {
   			
      		 conn = super.getDataSource().getConnection();
      		 
//      		 sql 고쳐야됨
			String sql = "select r.seq, r.subject, r.wdate, r.content, r.imgName requestImgName, b.name brandName, m.name writer from requestboard r, memberlist m, brand b where r.memNo = m.memNo and r.brandNo = b.no and r.seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, to.getSeq());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				to.setSubject(rs.getString("Subject"));
				to.setWdate(rs.getString("Wdate"));
				to.setContent(rs.getString("content"));
				to.setRequestImgName(rs.getString("requestImgName") == null ? "":rs.getString("requestImgName"));
				to.setBrandName(rs.getString("brandName"));
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
	
	public int writeOk(RequestBoardTO to, int memNo, int brandNo) {
		
		int flag = 1;
		
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			ArrayList<RequestBoardTO> boardList = new ArrayList<>();
		
			try {
				
	  		 conn = super.getDataSource().getConnection();
	  		 
	  		String sql = "insert into requestboard(subject, wdate, content, imgName, brandNo, memNo) values (?, sysdate(), ?, ?, ?, ?)";
//	  		String sql = "insert into requestboard(subject, wdate, content, brandNo, memNo) values (?, sysdate(), ?, ?, ?)";
	  		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSubject());
			pstmt.setString(2, to.getContent());
			pstmt.setString(3, to.getRequestImgName());	//이미지 때문에 안되면 지워야됨
			pstmt.setInt(4, brandNo);
			pstmt.setInt(5, memNo);
			
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

	public String getImageNameBeforeModify(int seq) {
		String imageName = "";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
   		
   		try {
   			
      		 conn = super.getDataSource().getConnection();
      		 
      		String sql = "select imgName from requestboard where seq = ?";
      		pstmt = conn.prepareStatement(sql);
      		pstmt.setInt(1, seq);
      		rs = pstmt.executeQuery();

			while (rs.next()) {
				imageName = rs.getString("imgName");
			}
      			
      		} catch (SQLException e) {
      			System.out.println("SQL 에러 : " + e.getMessage());
      		} finally {
      			if(conn != null)try {conn.close();}catch(SQLException e) {}
      			if(pstmt != null)try {pstmt.close();}catch(SQLException e) {}
      			if(rs != null)try {rs.close();}catch(SQLException e) {}
      		}
		
		
		return imageName;
	}
	
	
	public int modifyOk(RequestBoardTO to, int seq, int brandNo, String requestImgName) {
		
		int flag = 1;
		
		Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				
	  		 conn = super.getDataSource().getConnection();
	  		 
	  		String sql = "update requestboard set subject = ?, content = ?, imgName = ?, brandNo = ? where seq = ?";
	  		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSubject());
			pstmt.setString(2, to.getContent());
			pstmt.setString(3, requestImgName);
			pstmt.setInt(4, brandNo);
			pstmt.setInt(5, seq);
			
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
	  		 
	  		String sql = "delete from requestboard where seq = ? and memNo = ?";
	  		
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

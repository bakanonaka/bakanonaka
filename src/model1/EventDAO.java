package model1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.oracle.jrockit.jfr.EventToken;

public class EventDAO extends DAO {

public EventListTO getEventsList(EventListTO listTO) {
		
		Connection conn = null;
   		PreparedStatement pstmt = null;
   		ResultSet rs = null;
   		
   		int cpage = listTO.getCpage();
		int recordPerPage = listTO.getRecordPerPage();
		int blockPerPage = listTO.getBlockPerPage();
   		
		try {

			conn = super.getDataSource().getConnection();

			String sql = "";

			// mysql DB 접속하려면 context.xml 바꿔줘야함
			
			sql = "select e.*, b.name brandName, b.eventBoardUrl from event e, brand b where e.brandNo = b.no and status = 0";
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
			
			ArrayList<EventTO> eventsList = new ArrayList<>();
			for (int i = 0; i < recordPerPage && rs.next(); i++) {
				EventTO to = new EventTO();
				
				to.setNo(rs.getInt("no"));
 				to.setTitle(rs.getString("title"));
 				to.setImgName(rs.getString("imgName") == null ? "" : rs.getString("imgName"));
 				to.setBrandName(rs.getString("brandName"));
 				
 				String term[] = rs.getString("term").split("~");
 				//형식이 맞는지 확인 필요.
 				to.setDateFrom(term[0].trim());
 				to.setDateTo(term[1].trim());
 				
 				if(rs.getString("eventUrl") == null || rs.getString("eventUrl").length() < 3) {
 					to.setUrl(rs.getString("eventBoardUrl"));
 				} else {
 					to.setUrl(rs.getString("eventUrl"));
 				}
 				
// 				private String address;		//가장 가까운 지점주소
// 				private String storeName;	//가장 가까운 지점명
 				
				// 데이터 입력하기.
				
 				eventsList.add(to);
			}

//			System.out.println("eventsList 데이터 수 : " + eventsList.size());
			
			listTO.setEventsList(eventsList);
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

	public ArrayList<EventTO> getAllEventsList() {
		
		Connection conn = null;
   		PreparedStatement pstmt = null;
   		ResultSet rs = null;
   		
   		ArrayList<EventTO> allEventsList = new ArrayList<>();
   		
   		try {
   			
   		 conn = super.getDataSource().getConnection();
			
			String sql = "select e.*, b.name brandName, b.eventBoardUrl from event e, brand b where e.brandNo = b.no and status = 0";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				EventTO to = new EventTO();

			
				to.setNo(rs.getInt("no"));
 				to.setTitle(rs.getString("title"));
 				to.setImgName(rs.getString("imgName") == null? "" : rs.getString("imgName"));
 				to.setBrandName(rs.getString("brandName"));
 				
 				String term[] = rs.getString("term").split("~");
 				//형식이 맞는지 확인 필요.
 				to.setDateFrom(term[0].trim());
 				to.setDateTo(term[1].trim());
 				
 				if(rs.getString("eventUrl") == null || rs.getString("eventUrl").length() < 3) {
 					to.setUrl(rs.getString("eventBoardUrl"));
 				} else {
 					to.setUrl(rs.getString("eventUrl"));
 				}
 				
// 				private String address;		//가장 가까운 지점주소
// 				private String storeName;	//가장 가까운 지점명
 				
				allEventsList.add(to);
			}
   			
//				System.out.println("allEventsList 갯수: " + allEventsList.size());
			
   		} catch (SQLException e) {
   			System.out.println("SQL 에러 : " + e.getMessage());
   		} finally {
   			if(conn != null)try {conn.close();}catch(SQLException e) {}
   			if(pstmt != null)try {pstmt.close();}catch(SQLException e) {}
   			if(rs != null)try {rs.close();}catch(SQLException e) {}
   		}
     		
     	return allEventsList;
     	
	}
	
		public ArrayList<EventTO> getAllEventsListForManage() {
		
		Connection conn = null;
   		PreparedStatement pstmt = null;
   		ResultSet rs = null;
   		
   		ArrayList<EventTO> allEventsList = new ArrayList<>();
   		
   		try {
   			
   		 conn = super.getDataSource().getConnection();
			
			String sql = "select e.*, b.name brandName, b.eventBoardUrl from event e, brand b where e.brandNo = b.no";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				EventTO to = new EventTO();

			
				to.setNo(rs.getInt("no"));
 				to.setTitle(rs.getString("title"));
 				to.setImgName(rs.getString("imgName") == null ? "" : rs.getString("imgName"));
 				to.setBrandName(rs.getString("brandName"));
 				
 				String term[] = rs.getString("term").split("~");
 				//형식이 맞는지 확인 필요.
 				to.setDateFrom(term[0].trim());
 				to.setDateTo(term[1].trim());
 				
 				to.setUrl(rs.getString("eventUrl") == null? "" : rs.getString("eventUrl"));
 
 				to.setStatus(rs.getInt("status"));
				
// 				private String address;		//가장 가까운 지점주소
// 				private String storeName;	//가장 가까운 지점명
 				
				allEventsList.add(to);
			}
   			
//				System.out.println("allEventsList 갯수: " + allEventsList.size());
			
   		} catch (SQLException e) {
   			System.out.println("SQL 에러 : " + e.getMessage());
   		} finally {
   			if(conn != null)try {conn.close();}catch(SQLException e) {}
   			if(pstmt != null)try {pstmt.close();}catch(SQLException e) {}
   			if(rs != null)try {rs.close();}catch(SQLException e) {}
   		}
     		
     	return allEventsList;
     	
	}
		
		
		public int writeOk(EventTO to, String term, int brandNo) {
			
			int flag = 1;
			
				Connection conn = null;
				PreparedStatement pstmt = null;
				
				ArrayList<RequestBoardTO> boardList = new ArrayList<>();
			
				try {
					
		  		 conn = super.getDataSource().getConnection();
		  		 
		  		String sql = "insert into event(title, imgName, term, status, brandNo, eventUrl) values(?, ?, ?, 2, ?, ?)";
		  		
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, to.getTitle());
				pstmt.setString(2, to.getImgName());
				pstmt.setString(3, term);	//이미지 때문에 안되면 지워야됨
				pstmt.setInt(4, brandNo);
				pstmt.setString(5, to.getUrl());
				
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
		
	public EventTO getEventModifyViewData (EventTO to) {
		Connection conn = null;
   		PreparedStatement pstmt = null;
   		ResultSet rs = null;
   		
   		try {
   			
   		 conn = super.getDataSource().getConnection();
			
			String sql = "select e.*, b.name brandName, b.eventBoardUrl from event e, brand b where e.brandNo = b.no and e.no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, to.getNo());

			rs = pstmt.executeQuery();

			while(rs.next()) {
			
				to.setNo(rs.getInt("no"));
 				to.setTitle(rs.getString("title"));
 				to.setImgName(rs.getString("imgName") == null? "" : rs.getString("imgName"));
 				to.setBrandName(rs.getString("brandName"));
 				
 				String term[] = rs.getString("term").split("~");
 				//형식이 맞는지 확인 필요.
 				to.setDateFrom(term[0].trim());
 				to.setDateTo(term[1].trim());
 				
 				to.setUrl(rs.getString("eventUrl") == null ? "" : rs.getString("eventUrl"));
 				
 				to.setStatus(rs.getInt("status"));
				
// 				private String address;		//가장 가까운 지점주소
// 				private String storeName;	//가장 가까운 지점명
 				
			}
   			
//				System.out.println("allEventsList 갯수: " + allEventsList.size());
			
   		} catch (SQLException e) {
   			System.out.println("SQL 에러 : " + e.getMessage());
   		} finally {
   			if(conn != null)try {conn.close();}catch(SQLException e) {}
   			if(pstmt != null)try {pstmt.close();}catch(SQLException e) {}
   			if(rs != null)try {rs.close();}catch(SQLException e) {}
   		}
     		
     	return to;
     	
	}
		
	public String getImageNameBeforeModify(EventTO to) {
		
		String ImageName = "";
		
		Connection conn = null;
   		PreparedStatement pstmt = null;
   		ResultSet rs = null;
   		
   		try {
   			
   		 conn = super.getDataSource().getConnection();
			
			String sql = "select imgName from event where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, to.getNo());

			rs = pstmt.executeQuery();

			while(rs.next()) {
			
 				ImageName = rs.getString("imgName") == null ? "" : rs.getString("imgName");
			}
   			
//				System.out.println("allEventsList 갯수: " + allEventsList.size());
			
   		} catch (SQLException e) {
   			System.out.println("SQL 에러 : " + e.getMessage());
   		} finally {
   			if(conn != null)try {conn.close();}catch(SQLException e) {}
   			if(pstmt != null)try {pstmt.close();}catch(SQLException e) {}
   			if(rs != null)try {rs.close();}catch(SQLException e) {}
   		}
     		
     	return ImageName;
	}
	
	public int modifyOk(EventTO to, int no, int brandNo, String imgName, String term) {
		
		int flag = 1;
		
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			ArrayList<RequestBoardTO> boardList = new ArrayList<>();
		
			try {
				
	  		 conn = super.getDataSource().getConnection();
	  		 
	  		String sql = "update event set title = ?, imgName = ?, term = ?, status = ?, brandNo = ?, eventUrl = ? where no = ?";
	  		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getTitle());
			pstmt.setString(2, imgName);
			pstmt.setString(3, term);	//이미지 때문에 안되면 지워야됨
			pstmt.setInt(4, to.getStatus());
			pstmt.setInt(5, brandNo);
			pstmt.setString(6, to.getUrl());
			pstmt.setInt(7, no);
			
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
	
	public int deleteOk(int no) {
		
		int flag = 1;
		
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			ArrayList<RequestBoardTO> boardList = new ArrayList<>();
		
			try {
				
	  		 conn = super.getDataSource().getConnection();
	  		 
	  		String sql = "delete from event where no = ?";
	  		
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
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

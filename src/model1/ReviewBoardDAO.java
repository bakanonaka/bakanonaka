package model1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReviewBoardDAO extends DAO {

public BoardListTO getReviewBoardList(BoardListTO listTO) {
		
		Connection conn = null;
   		PreparedStatement pstmt = null;
   		ResultSet rs = null;
   		
   		int cpage = listTO.getCpage();
		int recordPerPage = listTO.getRecordPerPage();
		int blockPerPage = listTO.getBlockPerPage();
   		
		try {

			conn = super.getDataSource().getConnection();

			String sql = "select r.*, p.name productName, p.colorName, p.basicProductImgName, p.colorProductImgName, m.name writer, b.name brandName from reviewboard r, product p, memberlist m, brand b where r.memNo = m.memNo and r.productNo = p.no and r.brandNo = b.no order by r.seq desc";
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
			
			ArrayList<ReviewBoardTO> reviewBoardList = new ArrayList<>();
			for (int i = 0; i < recordPerPage && rs.next(); i++) {
				ReviewBoardTO to = new ReviewBoardTO();

				to.setSeq(rs.getInt("seq"));
				to.setSubject(rs.getString("Subject"));
				to.setWdate(rs.getString("Wdate"));
				to.setContent(rs.getString("content"));
				to.setReviewImgName(rs.getString("imgName") == null ? "" : rs.getString("imgName"));
				to.setProductName(rs.getString("productName"));
				
				if(rs.getString("basicProductImgName").equals("") || rs.getString("basicProductImgName").length() < 3){
					to.setProductImgName(rs.getString("colorProductImgName"));
				} else {
					to.setProductImgName(rs.getString("basicProductImgName"));
				}
				
				to.setBrandName(rs.getString("brandName"));
				to.setCountOfStar(rs.getInt("countOfStar"));
				to.setColorNameOfProduct(rs.getString("colorName"));
				to.setWriter(rs.getString("Writer").trim());
				
				// 데이터 입력하기.
				
				reviewBoardList.add(to);
				
//				System.out.println("to.getProductImgName() : " + to.getProductImgName());
			}

//			System.out.println("reviewBoardList 데이터 수 : " + reviewBoardList.size());
			
			listTO.setReviewBoardList(reviewBoardList);
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

	public ReviewBoardTO getBoardViewData(ReviewBoardTO to) {
		
		Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
		
			try {
				
	  		 conn = super.getDataSource().getConnection();
	  		 
	  		String sql = "select r.*, p.name productName, p.colorName, p.basicProductImgName, p.colorProductImgName, m.name writer, b.name brandName from reviewboard r, product p, memberlist m, brand b where r.memNo = m.memNo and r.productNo = p.no and r.brandNo = b.no and seq = ?";
	  		pstmt = conn.prepareStatement(sql);
	  		pstmt.setInt(1, to.getSeq());
	  		rs = pstmt.executeQuery();
	
			while (rs.next()) {
				
				to.setSeq(rs.getInt("seq"));
				to.setSubject(rs.getString("Subject"));
				to.setWdate(rs.getString("Wdate"));
				to.setContent(rs.getString("content"));
				to.setReviewImgName(rs.getString("imgName") == null ? "" : rs.getString("imgName"));
				to.setProductName(rs.getString("productName"));
				
				if(rs.getString("basicProductImgName").equals("") || rs.getString("basicProductImgName").length() < 3){
					to.setProductImgName(rs.getString("colorProductImgName"));
				} else {
					to.setProductImgName(rs.getString("basicProductImgName"));
				}
				
				to.setBrandName(rs.getString("brandName"));
				to.setCountOfStar(rs.getInt("countOfStar"));
				to.setColorNameOfProduct(rs.getString("colorName"));
				to.setWriter(rs.getString("Writer").trim());
			}
	  		
//			System.out.println(to.getSeq());
//			System.out.println(to.getSubject());
//			System.out.println(to.getWdate());
//			System.out.println(to.getContent());
//			System.out.println(to.getReviewImgName());
//			System.out.println(to.getProductName());
//			System.out.println(to.getProductImgName());
//			System.out.println(to.getBrandName());
//			System.out.println(to.getCountOfStar());
//			System.out.println(to.getWriter());
			
	  		} catch (SQLException e) {
	  			System.out.println("SQL 에러 : " + e.getMessage());
	  		} finally {
	  			if(conn != null)try {conn.close();}catch(SQLException e) {}
	  			if(pstmt != null)try {pstmt.close();}catch(SQLException e) {}
	  			if(rs != null)try {rs.close();}catch(SQLException e) {}
	  		}
	  		
	  		return to;
	}
	
	public ReviewBoardTO getBoardModifyViewData(ReviewBoardTO to) {
		
		Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				
	  		 conn = super.getDataSource().getConnection();
	  		 
	//  		 sql 고쳐야됨
			String sql = "select r.*, p.name productName, p.colorName, p.no productNo, p.basicProductImgName, p.colorProductImgName, m.name writer, b.name brandName from reviewboard r, product p, memberlist m, brand b where r.memNo = m.memNo and r.productNo = p.no and r.brandNo = b.no and seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, to.getSeq());
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				to.setSeq(rs.getInt("seq"));
				to.setSubject(rs.getString("Subject"));
				to.setWdate(rs.getString("Wdate"));
				to.setContent(rs.getString("content"));
				to.setReviewImgName(rs.getString("imgName") == null ? "" : rs.getString("imgName"));
				to.setProductName(rs.getString("productName"));
				
				if(rs.getString("basicProductImgName").equals("") || rs.getString("basicProductImgName").length() < 3){
					to.setProductImgName(rs.getString("colorProductImgName"));
				} else {
					to.setProductImgName(rs.getString("basicProductImgName"));
				}
				
				to.setBrandName(rs.getString("brandName"));
				to.setCountOfStar(rs.getInt("countOfStar"));
				to.setWriter(rs.getString("Writer").trim());
				to.setColorNameOfProduct(rs.getString("colorName"));
				to.setProductNo(rs.getInt("productNo"));
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
	
	public int writeOk(ReviewBoardTO to, int memNo, int productNo, int brandNo) {
		
		int flag = 1;
		
			Connection conn = null;
			PreparedStatement pstmt = null;
		
			try {
				
	  		 conn = super.getDataSource().getConnection();
	  		 
	  		String sql = "insert into reviewboard(subject, wdate, content, imgName, countOfStar, productNo, memNo, brandNo) values (?, sysdate(), ?, ?, ?, ?, ?, ?)";
	  		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSubject());
			pstmt.setString(2, to.getContent());
			pstmt.setString(3, to.getReviewImgName());	//이미지 때문에 안되면 지워야됨
			pstmt.setInt(4, to.getCountOfStar());
			pstmt.setInt(5, productNo);
			pstmt.setInt(6, memNo);
			pstmt.setInt(7, brandNo);
			
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
	  		 
	  		String sql = "select imgName from reviewboard where seq = ?";
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
		
		System.out.println(imageName);
		return imageName;
	}
	
	
	public int modifyOk(ReviewBoardTO to, int seq, int productNo, int brandNo, String reviewImgName) {
		
		int flag = 1;
		
		Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				
	  		 conn = super.getDataSource().getConnection();
	  		 
	  		String sql = "update reviewboard set subject = ?, content = ?, imgName = ?, countOfStar = ?, productNo = ?, brandNo = ? where seq = ?";
	  		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSubject());
			pstmt.setString(2, to.getContent());
			pstmt.setString(3, reviewImgName);
			pstmt.setInt(4, to.getCountOfStar());
			pstmt.setInt(5, productNo);
			pstmt.setInt(6, brandNo);
			pstmt.setInt(7, seq);
			
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
	
//	public int deleteOk(int seq, int memNo) {
		public int deleteOk(int seq) {	//맴버 없이 그냥 삭제 됨
			int flag = 1;
		
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				
	  		 conn = super.getDataSource().getConnection();
	  		 
//	  		String sql = "delete from reviewboard where seq = ? and memNo = ?";
	  		String sql = "delete from reviewboard where seq = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
//			pstmt.setInt(2, memNo);
			
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
		
	public ArrayList<ReviewBoardTO> getReviewListForProduct(int productNo) {
		
		ArrayList<ReviewBoardTO> reviewList = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
	
		try {
			
  		 conn = super.getDataSource().getConnection();
  		 
  		String sql = "select r.*, p.name productName, p.colorName, p.basicProductImgName, p.colorProductImgName, m.name writer, b.name brandName "
  				+ "from reviewboard r, product p, memberlist m, brand b "
  				+ "where r.memNo = m.memNo and r.productNo = p.no and r.brandNo = b.no and r.productNo = ? order by r.countOfStar desc limit 3";
  		
  		pstmt = conn.prepareStatement(sql);
  		pstmt.setInt(1, productNo);
  		rs = pstmt.executeQuery();

		while (rs.next()) {
			
			ReviewBoardTO to = new ReviewBoardTO();
			
			to.setSeq(rs.getInt("seq"));
			to.setSubject(rs.getString("Subject"));
			to.setWdate(rs.getString("Wdate"));
			to.setContent(rs.getString("content"));
			to.setReviewImgName(rs.getString("imgName") == null ? "" : rs.getString("imgName"));
			to.setProductName(rs.getString("productName"));
			
			if(rs.getString("basicProductImgName").equals("") || rs.getString("basicProductImgName").length() < 3){
				to.setProductImgName(rs.getString("colorProductImgName"));
			} else {
				to.setProductImgName(rs.getString("basicProductImgName"));
			}
			
			to.setBrandName(rs.getString("brandName"));
			to.setCountOfStar(rs.getInt("countOfStar"));
			to.setWriter(rs.getString("Writer").trim());
			to.setColorNameOfProduct(rs.getString("colorName"));
			
			reviewList.add(to);
		}
  		
		System.out.println("reviewList 갯수 : " + reviewList.size());
		
  		} catch (SQLException e) {
  			System.out.println("SQL 에러 : " + e.getMessage());
  		} finally {
  			if(conn != null)try {conn.close();}catch(SQLException e) {}
  			if(pstmt != null)try {pstmt.close();}catch(SQLException e) {}
  			if(rs != null)try {rs.close();}catch(SQLException e) {}
  		}
  		
  		return reviewList;
  		
	}
		
		
	public Integer[] getSumOfStarOfProduct(int productNo) {
		
		Integer[] dataOfStar = {0,0}; 
				
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
	
		try {
			
  		 conn = super.getDataSource().getConnection();
  		 
  		String sql = " select sum(countOfStar), count(*) from reviewboard where productNo = ?";
  		
  		pstmt = conn.prepareStatement(sql);
  		pstmt.setInt(1, productNo);
  		rs = pstmt.executeQuery();

		while (rs.next()) {
			dataOfStar[0] = rs.getInt(1);
			dataOfStar[1] = rs.getInt(2);
		}
  		
  		} catch (SQLException e) {
  			System.out.println("SQL 에러 : " + e.getMessage());
  		} finally {
  			if(conn != null)try {conn.close();}catch(SQLException e) {}
  			if(pstmt != null)try {pstmt.close();}catch(SQLException e) {}
  			if(rs != null)try {rs.close();}catch(SQLException e) {}
  		}
  		
  		return dataOfStar;
	}
	
	public int getProductNo(int seq) {
		int productNo = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
			try {
				
	  		 conn = super.getDataSource().getConnection();
	  		 
	  		String sql = "select productNo from reviewboard where seq = ?";
	  		pstmt = conn.prepareStatement(sql);
	  		pstmt.setInt(1, seq);
	  		rs = pstmt.executeQuery();
	
			while (rs.next()) {
				productNo = rs.getInt("productNo");
			}
	  			
	  		} catch (SQLException e) {
	  			System.out.println("SQL 에러 : " + e.getMessage());
	  		} finally {
	  			if(conn != null)try {conn.close();}catch(SQLException e) {}
	  			if(pstmt != null)try {pstmt.close();}catch(SQLException e) {}
	  			if(rs != null)try {rs.close();}catch(SQLException e) {}
	  		}
		
		
		return productNo;
	}
}

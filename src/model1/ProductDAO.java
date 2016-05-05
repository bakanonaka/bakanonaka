package model1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO extends DAO {

	public ProductListTO getProductsOfColorList(ProductListTO listTO) {
		
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
			
			sql = "select p.*, b.name brandName, pk.name productKindName "
					+ "from product p, brand b, productkind pk "
					+ "where p.brandNo = b.no and p.productkindno = pk.no order by p.countOfStar desc";
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
			
			ArrayList<ProductTO> productsList = new ArrayList<>();
			for (int i = 0; i < recordPerPage && rs.next(); i++) {
				ProductTO to = new ProductTO();

				to.setNo(rs.getInt("no"));							//제품 구분자
				to.setName(rs.getString("name").trim());					//화장품 명
				to.setPrice(rs.getInt("price"));					//가격
				to.setCountOfStar(rs.getInt("countOfstar"));		//별점 수
				to.setColorName(rs.getString("colorName"));				//화장품 색상명
				to.setSpringNumber(rs.getInt("spring"));				//봄계열 숫자
				to.setSummerNumber(rs.getInt("summer"));				//여름계열 숫자
				to.setFallNumber(rs.getInt("fall"));				//가을계열 숫자
				to.setWinterNumber(rs.getInt("winter"));				//겨울계열 숫자
				to.setBasicProductImgName(rs.getString("basicProductImgName"));	//1. 화장품 기본 이미지
				to.setColorProductImgName(rs.getString("colorProductImgName"));	//2. 화장품 색상별 이미지 (1이 없으면, 보여줌)
				to.setColorImgName(rs.getString("colorImgName"));			//3. 색상 이미지 (2가 없으면, 1과 같이 보여줌)
				to.setTestImgName(rs.getString("testImgName"));			//4. 발색 이미지 (상세 보기에서 보여줌)
				to.setBrandName(rs.getString("brandName"));				//브랜드명
				to.setProductKindName(rs.getString("productKindName"));		//화장품 종류 (립스틱)
				
				// 데이터 입력하기.
				
				productsList.add(to);
			}

//			System.out.println("productsList 데이터 수 : " + productsList.size());
			
			listTO.setProductsList(productsList);
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
	
	public ProductListTO getProductsOfColorListWithColor(ProductListTO listTO, int colorGroup, String seasonName) {
		
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
			
			switch(seasonName) {
				case "spring":
					sql = "select p.*, b.name brandName, pk.name productKindName "
							+ "from product p, brand b, productkind pk "
							+ "where p.brandNo = b.no and p.productkindno = pk.no and spring = ? order by p.countOfStar desc";
					break;
				case "summer":
					sql = "select p.*, b.name brandName, pk.name productKindName "
							+ "from product p, brand b, productkind pk "
							+ "where p.brandNo = b.no and p.productkindno = pk.no and summer = ? order by p.countOfStar desc";
					break;
				case "fall":
					sql = "select p.*, b.name brandName, pk.name productKindName "
							+ "from product p, brand b, productkind pk "
							+ "where p.brandNo = b.no and p.productkindno = pk.no and fall = ? order by p.countOfStar desc";
					break;
				case "winter":
					sql = "select p.*, b.name brandName, pk.name productKindName "
							+ "from product p, brand b, productkind pk "
							+ "where p.brandNo = b.no and p.productkindno = pk.no and winter = ? order by p.countOfStar desc";
					break;
				default:
					break;
			}
			
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pstmt.setInt(1, colorGroup);
			
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
			
			ArrayList<ProductTO> productsList = new ArrayList<>();
			for (int i = 0; i < recordPerPage && rs.next(); i++) {
				ProductTO to = new ProductTO();

				to.setNo(rs.getInt("no"));							//제품 구분자
				to.setName(rs.getString("name").trim());					//화장품 명
				to.setPrice(rs.getInt("price"));					//가격
				to.setCountOfStar(rs.getInt("countOfstar"));		//별점 수
				to.setColorName(rs.getString("colorName"));				//화장품 색상명
				to.setSpringNumber(rs.getInt("spring"));				//봄계열 숫자
				to.setSummerNumber(rs.getInt("summer"));				//여름계열 숫자
				to.setFallNumber(rs.getInt("fall"));				//가을계열 숫자
				to.setWinterNumber(rs.getInt("winter"));				//겨울계열 숫자
				to.setBasicProductImgName(rs.getString("basicProductImgName"));	//1. 화장품 기본 이미지
				to.setColorProductImgName(rs.getString("colorProductImgName"));	//2. 화장품 색상별 이미지 (1이 없으면, 보여줌)
				to.setColorImgName(rs.getString("colorImgName"));			//3. 색상 이미지 (2가 없으면, 1과 같이 보여줌)
				to.setTestImgName(rs.getString("testImgName"));			//4. 발색 이미지 (상세 보기에서 보여줌)
				to.setBrandName(rs.getString("brandName"));				//브랜드명
				to.setProductKindName(rs.getString("productKindName"));		//화장품 종류 (립스틱)
				
				// 데이터 입력하기.
				
				productsList.add(to);
			}

//			System.out.println("productsList 데이터 수 : " + productsList.size());
			
			listTO.setProductsList(productsList);
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
	
	public ArrayList<ProductTO> getProductList(int brandNo) {
		Connection conn = null;
   		PreparedStatement pstmt = null;
   		ResultSet rs = null;
   		
		ArrayList<ProductTO> productList = new ArrayList<>();
		
		try {
   			
    		 conn = super.getDataSource().getConnection();
 			
// 			String sql = "select no, name, colorname from product where brandNo = ?";
    		 //distinct로 브랜드의 화장품이 중복되지 않게 가져옴.
 			String sql = "select distinct name from product where brandNo = ?";
 			pstmt = conn.prepareStatement(sql);
 			pstmt.setInt(1, brandNo);
 			rs = pstmt.executeQuery();

 			while(rs.next()) {
 				ProductTO to = new ProductTO();

// 				to.setNo(rs.getInt("no"));
 				to.setName(rs.getString("name"));
// 				to.setColorName(rs.getString("colorName"));
 				
 				productList.add(to);
 			}
    			
// 				System.out.println("productList 갯수: " + productList.size());
 			
    		} catch (SQLException e) {
    			System.out.println("SQL 에러 : " + e.getMessage());
    		} finally {
    			if(conn != null)try {conn.close();}catch(SQLException e) {}
    			if(pstmt != null)try {pstmt.close();}catch(SQLException e) {}
    			if(rs != null)try {rs.close();}catch(SQLException e) {}
    		}
		
		
		return productList;
	}
	
//	public ArrayList<ProductTO> getProductListForColors(int productNo) {
	public ArrayList<ProductTO> getProductListForColors(String productName) {		
		Connection conn = null;
   		PreparedStatement pstmt = null;
   		ResultSet rs = null;
   		
		ArrayList<ProductTO> productList = new ArrayList<>();
		
		try {
   			
    		 conn = super.getDataSource().getConnection();
 			
// 			String sql = "select no, name, colorname from product where no = ?";
 			String sql = "select no, name, colorname from product where name = ?";
 			pstmt = conn.prepareStatement(sql);
// 			pstmt.setInt(1, productNo);
 			pstmt.setString(1, productName);
 			rs = pstmt.executeQuery();

 			while(rs.next()) {
 				ProductTO to = new ProductTO();

 				to.setNo(rs.getInt("no"));
 				to.setName(rs.getString("name"));
 				to.setColorName(rs.getString("colorName"));
 				
 				productList.add(to);
 			}
    			
// 				System.out.println("productList 갯수: " + productList.size());
 			
    		} catch (SQLException e) {
    			System.out.println("SQL 에러 : " + e.getMessage());
    		} finally {
    			if(conn != null)try {conn.close();}catch(SQLException e) {}
    			if(pstmt != null)try {pstmt.close();}catch(SQLException e) {}
    			if(rs != null)try {rs.close();}catch(SQLException e) {}
    		}
		
		
		return productList;
	}
	
	
	public ProductTO getProductViewData(ProductTO to) {
		
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
		
			try {
				
	  		 conn = super.getDataSource().getConnection();
	  		 
	  		String sql = "select p.*, b.name brandName, pk.name productKindName from product p, brand b, productkind pk where p.brandNo = b.no and p.productkindno = pk.no and p.no = ?";
	  		pstmt = conn.prepareStatement(sql);
	  		pstmt.setInt(1, to.getNo());
	  		rs = pstmt.executeQuery();
	
			while (rs.next()) {
				
				to.setNo(rs.getInt("no"));							//제품 구분자
				to.setName(rs.getString("name").trim());					//화장품 명
				to.setPrice(rs.getInt("price"));					//가격
				to.setCountOfStar(rs.getInt("countOfstar"));		//별점 수
				to.setColorName(rs.getString("colorName"));				//화장품 색상명
				to.setSpringNumber(rs.getInt("spring"));				//봄계열 숫자
				to.setSummerNumber(rs.getInt("summer"));				//여름계열 숫자
				to.setFallNumber(rs.getInt("fall"));				//가을계열 숫자
				to.setWinterNumber(rs.getInt("winter"));				//겨울계열 숫자
				to.setBasicProductImgName(rs.getString("basicProductImgName"));	//1. 화장품 기본 이미지
				to.setColorProductImgName(rs.getString("colorProductImgName"));	//2. 화장품 색상별 이미지 (1이 없으면, 보여줌)
				to.setColorImgName(rs.getString("colorImgName"));			//3. 색상 이미지 (2가 없으면, 1과 같이 보여줌)
				to.setTestImgName(rs.getString("testImgName"));			//4. 발색 이미지 (상세 보기에서 보여줌)
				to.setBrandName(rs.getString("brandName"));				//브랜드명
				to.setProductKindName(rs.getString("productKindName"));		//화장품 종류 (립스틱)
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
	
	public ArrayList<ProductTO> getOtherProductList(ProductTO to) {		
		Connection conn = null;
   		PreparedStatement pstmt = null;
   		ResultSet rs = null;
   		
		ArrayList<ProductTO> productList = new ArrayList<>();
		
		try {
   			
    		 conn = super.getDataSource().getConnection();
 			
 			String sql = "select p.*, b.name brandName, pk.name productKindName "
 					+ "from product p, brand b, productkind pk "
 					+ "where p.brandNo = b.no and p.productkindno = pk.no and b.name <> ? and p.spring <> ? and p.summer <> ? and p.fall <> ? and p.winter <> ? order by p.countOfStar desc limit 3";
 			pstmt = conn.prepareStatement(sql);
 			pstmt.setString(1, to.getBrandName());
 			pstmt.setInt(2, to.getSpringNumber());
 			pstmt.setInt(3, to.getSummerNumber());
 			pstmt.setInt(4, to.getFallNumber());
 			pstmt.setInt(5, to.getWinterNumber());
 			
 			rs = pstmt.executeQuery();

 			while(rs.next()) {
 				ProductTO tto = new ProductTO();

 				tto.setNo(rs.getInt("no"));							//제품 구분자
 				tto.setName(rs.getString("name").trim());					//화장품 명
 				tto.setPrice(rs.getInt("price"));					//가격
 				tto.setCountOfStar(rs.getInt("countOfstar"));		//별점 수
 				tto.setColorName(rs.getString("colorName"));				//화장품 색상명
 				tto.setSpringNumber(rs.getInt("spring"));				//봄계열 숫자
 				tto.setSummerNumber(rs.getInt("summer"));				//여름계열 숫자
 				tto.setFallNumber(rs.getInt("fall"));				//가을계열 숫자
 				tto.setWinterNumber(rs.getInt("winter"));				//겨울계열 숫자
 				tto.setBasicProductImgName(rs.getString("basicProductImgName"));	//1. 화장품 기본 이미지
 				tto.setColorProductImgName(rs.getString("colorProductImgName"));	//2. 화장품 색상별 이미지 (1이 없으면, 보여줌)
 				tto.setColorImgName(rs.getString("colorImgName"));			//3. 색상 이미지 (2가 없으면, 1과 같이 보여줌)
 				tto.setTestImgName(rs.getString("testImgName"));			//4. 발색 이미지 (상세 보기에서 보여줌)
 				tto.setBrandName(rs.getString("brandName"));				//브랜드명
 				tto.setProductKindName(rs.getString("productKindName"));		//화장품 종류 (립스틱)
 				productList.add(tto);
 			}
    			
// 				System.out.println("productList 갯수: " + productList.size());
 			
    		} catch (SQLException e) {
    			System.out.println("SQL 에러 : " + e.getMessage());
    		} finally {
    			if(conn != null)try {conn.close();}catch(SQLException e) {}
    			if(pstmt != null)try {pstmt.close();}catch(SQLException e) {}
    			if(rs != null)try {rs.close();}catch(SQLException e) {}
    		}
		
		
		return productList;
	}

	public int updateCountOfStarOfProduct(int productNo, int countOfStar) {
		int flag = 1;
		
		Connection conn = null;
   		PreparedStatement pstmt = null;
   		ResultSet rs = null;
   		
		try {
   			
    		 conn = super.getDataSource().getConnection();
 			
 			String sql = "update product set countOfStar = ? where no = ?";
 			pstmt = conn.prepareStatement(sql);
 			pstmt.setInt(1, countOfStar);
 			pstmt.setInt(2, productNo);

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
    			if(rs != null)try {rs.close();}catch(SQLException e) {}
    		}
		
   		return flag;
	}
}

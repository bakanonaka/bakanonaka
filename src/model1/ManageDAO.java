package model1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model2.ContextPath;

public class ManageDAO extends DAO {
	
	public int readFileAndInputProductToDB() {
		int flag = 1;// 0이면, 정상. 1이상이면 Error

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		BufferedReader br = null;

		try {
			
			conn = super.getDataSource().getConnection();

			String sql = "";

			sql = "select count(*) tableCount from information_schema.tables where table_schema = 'projectr' and table_name = 'product'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			int tableCount = 0;

			while (rs.next()) {

				if (rs.getInt("tableCount") > 0) {
					tableCount++;
				}

			}

			if (tableCount != 0) {
				// 이미 테이블이 있으면 데이터를 삭제 한다. (테스트시 사용, 리셋)

				sql = "delete from product";
				pstmt.close();
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				
				//컬럼에 auto_increment 설정된 것은 다시 초기화 한다.
				sql = "alter table product auto_increment = 1";
				pstmt.close();
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				
			} else {
				// 테이블이 없으면 생성한다.

				sql = "create table product ("
						+ "no					int      unsigned	not null auto_increment	primary key,"
						+ "name					char(40)			not null,"
						+ "price					int					not null,"
						+ "countOfStar			int					not null,"
						+ "colorName				char(100)			not null,"
						+ "spring				int					not null,"
						+ "summer				int					not null,"
						+ "fall					int					not null,"
						+ "winter				int					not null,"
						+ "basicProductImgName	char(100)			not null,"
						+ "colorProductImgName	char(100)			not null,"
						+ "colorImgName			char(100)			not null,"
						+ "testImgName			char(100)			not null,"
						+ "brandNo				int	unsigned		not null,"
						+ "productKindNo			int	unsigned		not null,"
						+ "constraint product_brandNo_fk foreign key (brandNo) references brand(no),"
						+ "constraint product_productKindNo_fk foreign key (productKindNo) references productkind(no)"
						+ ");";

				pstmt.close();
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
			}

			
			String pathOfWebContent = "";
			pathOfWebContent = ContextPath.getInstance().getPath();
//			System.out.println("pathOfWebContent : " + pathOfWebContent);
			
			br = new BufferedReader(new FileReader(pathOfWebContent + "/dbData/ProjectR_DataForProduct.txt"));
			
			//BOM maker will only appear on the very beginning
			br.mark(4);
			if('\ufeff' != br.read()) {
				br.reset();
			}
			
			String readLine = null;
			while ((readLine = br.readLine()) != null) {

//				System.out.println("readLine : " + readLine);
				String[] strArr = readLine.split(",");

				// insert into edu_hopedepart values(10, '개발팀');
				sql = "insert into product(name, price, countOfStar, colorName, spring, summer, fall, winter, "
						+ "basicProductImgName, colorProductImgName, colorImgName, testImgName, brandNo, productKindNo) "
						+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

				pstmt.close();
				pstmt = conn.prepareStatement(sql);
				// pstmt.setInt(1, Integer.parseInt(strArr[0].trim().replace("\uFEFF", "")));
				//데이터의 맨 앞이 숫자인 경우 replace 꼭 해줘야함 (이번엔 해당 사항 아님)
				
				pstmt.setString(1, strArr[0]); // name
				pstmt.setInt(2, Integer.parseInt(strArr[1])); // price
				pstmt.setInt(3, Integer.parseInt(strArr[2])); // countOfStar
				pstmt.setString(4, strArr[3]); // colorName
				pstmt.setInt(5, Integer.parseInt(strArr[4])); // spring
				pstmt.setInt(6, Integer.parseInt(strArr[5])); // summer
				pstmt.setInt(7, Integer.parseInt(strArr[6])); // fall
				pstmt.setInt(8, Integer.parseInt(strArr[7])); // winter
				pstmt.setString(9, strArr[8]); // basicProductImgName
				pstmt.setString(10, strArr[9]); // colorProductImgName
				pstmt.setString(11, strArr[10]); // colorImgName
				pstmt.setString(12, strArr[11]); // testImgName
				pstmt.setInt(13, Integer.parseInt(strArr[12])); // brandNo
				pstmt.setInt(14, Integer.parseInt(strArr[13])); // productKindNo

				pstmt.executeUpdate();

			}

			sql = "commit";
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeQuery();

			flag = 0;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("[FileNotFoundException 에러] : " + e.getMessage());

		} catch (SQLException e) {
			System.out.println("[SQL 에러] : " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("[IOException 에러] : " + e.getMessage());
		} finally {
			if(rs != null)try{rs.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(br != null)try{br.close();}catch(IOException e) {}
		}
		
		return flag;
	} 
	
}

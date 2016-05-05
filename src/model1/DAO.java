package model1;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DAO {

	private DataSource dataSource = null;
	
	public DAO() {
		// TODO Auto-generated constructor stub
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			this.dataSource = (DataSource)envCtx.lookup("jdbc/oracle");
			
//			System.out.println("this.dataSource 생성");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("DAO Naming 에러 : " + e.getMessage());
		}
	}

	public DataSource getDataSource() {
		return dataSource;
	}
	
}

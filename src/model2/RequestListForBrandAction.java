package model2;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.BoardListTO;
import model1.RequestBoardDAO;
import model1.RequestBoardDAO;
import model1.RequestBoardTO;

public class RequestListForBrandAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String cpage_text = request.getParameter("cpage");
		String brandName = request.getParameter("brandName");
		
		int cpage = Integer.parseInt(cpage_text);
		
		System.out.println("request cpage : " + cpage);
		System.out.println("brandName : " + brandName);
		
		BoardListTO boardListTO = new BoardListTO();
		boardListTO.setCpage(cpage);
		
		RequestBoardDAO dao = new RequestBoardDAO();

		boardListTO = dao.getRequestBoardListForBrand(boardListTO, brandName);
		
		int flag = 1;	//오류
		
		if(boardListTO.getRequestBoardList().size() > 0) {
			flag = 0;	//데이터 있음
		} else if(boardListTO.getRequestBoardList().size() == 0) {
			flag = 2;	//데이터 없음
		}
		
		request.setAttribute("requestBoardList", boardListTO);
		request.setAttribute("flag", flag);
	
	
	
	}

}

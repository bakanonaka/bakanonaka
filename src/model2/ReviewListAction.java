package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.BoardListTO;
import model1.ReviewBoardDAO;

public class ReviewListAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String cpage_text = request.getParameter("cpage");
		
		int cpage = Integer.parseInt(cpage_text);
		
		System.out.println("reviewlist cpage : " + cpage);
		
		BoardListTO boardListTO = new BoardListTO();
		boardListTO.setCpage(cpage);
		
		ReviewBoardDAO dao = new ReviewBoardDAO();

		boardListTO = dao.getReviewBoardList(boardListTO);		
		
		int flag = 1;	//오류
		
		if(boardListTO.getReviewBoardList().size() > 0) {
			flag = 0;	//데이터 있음
		} else if(boardListTO.getReviewBoardList().size() == 0) {
			flag = 2;	//데이터 없음
		}
		
		request.setAttribute("reviewBoardList", boardListTO);
		request.setAttribute("flag", flag);
	
	
	
	}

}

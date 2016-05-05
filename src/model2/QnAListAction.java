package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.BoardListTO;
import model1.QnABoardDAO;

public class QnAListAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String cpage_text = request.getParameter("cpage");
		String eventNoName = request.getParameter("eventNoName");
		
		int cpage = Integer.parseInt(cpage_text);
		System.out.println("qnaboard cpage : " + cpage);
		
		int eventNo = Integer.parseInt(eventNoName.substring(0, eventNoName.indexOf(".")));
		
		BoardListTO boardListTO = new BoardListTO();
		boardListTO.setCpage(cpage);
		
		QnABoardDAO dao = new QnABoardDAO();

		boardListTO = dao.getQnaBoardList(boardListTO, eventNo);
		
		int flag = 1;	//오류
		
		if(boardListTO.getQnaBoardList().size() > 0) {
			flag = 0;	//데이터 있음
		} else if(boardListTO.getQnaBoardList().size() == 0) {
			flag = 2;	//데이터 없음
		}
		
		request.setAttribute("qnaBoardList", boardListTO);
		request.setAttribute("flag", flag);
	}

}

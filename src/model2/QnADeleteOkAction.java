package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.QnABoardDAO;
import model1.QnABoardTO;

public class QnADeleteOkAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		
		QnABoardDAO dao = new QnABoardDAO();
		
		int flag = dao.deleteOk(seq, memNo);
		
		if(flag == 0) {
			System.out.println("QnA 글 수정 성공");
		} else {
			System.out.println("QnA 글 수정 실패");
		}
		
		request.setAttribute("flag", flag);
		
	}

}

package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.QnABoardDAO;
import model1.QnABoardTO;

public class QnAModifyAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		QnABoardDAO dao = new QnABoardDAO();
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		
//		System.out.println("seq : " + seq);
		
		QnABoardTO to = new QnABoardTO();
		to.setSeq(seq);
		
		to = dao.getBoardModifyViewData(to);
		
		request.setAttribute("boardModifyViewData", to);
		
	}

}

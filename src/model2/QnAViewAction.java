package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.QnABoardDAO;
import model1.QnABoardTO;

public class QnAViewAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
			int seq = Integer.parseInt(request.getParameter("seq"));
			
//			System.out.println("seq : " + seq);
			
			QnABoardDAO dao = new QnABoardDAO();
			
			QnABoardTO to = new QnABoardTO();
			to.setSeq(seq);
			
			to = dao.getBoardViewData(to);
			
			request.setAttribute("boardViewData", to);
			
	}

}

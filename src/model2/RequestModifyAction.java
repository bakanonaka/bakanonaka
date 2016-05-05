package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.RequestBoardDAO;
import model1.RequestBoardTO;

public class RequestModifyAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		RequestBoardDAO dao = new RequestBoardDAO();
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		
//		System.out.println("seq : " + seq);
		
		RequestBoardTO to = new RequestBoardTO();
		to.setSeq(seq);
		
		to = dao.getBoardModifyViewData(to);
		
		request.setAttribute("boardModifyViewData", to);
		
	}

}

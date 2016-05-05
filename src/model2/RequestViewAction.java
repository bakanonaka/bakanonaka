package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.RequestBoardDAO;
import model1.RequestBoardTO;

public class RequestViewAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
			int seq = Integer.parseInt(request.getParameter("seq"));
			
//			System.out.println("seq : " + seq);
			
			RequestBoardDAO dao = new RequestBoardDAO();
			
			RequestBoardTO to = new RequestBoardTO();
			to.setSeq(seq);
			
			to = dao.getBoardViewData(to);
			
			request.setAttribute("boardViewData", to);
			
	}

}

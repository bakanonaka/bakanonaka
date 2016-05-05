package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.ReviewBoardDAO;
import model1.ReviewBoardTO;

public class ReviewModifyAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		ReviewBoardDAO dao = new ReviewBoardDAO();
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		
//		System.out.println("seq : " + seq);
		
		ReviewBoardTO to = new ReviewBoardTO();
		to.setSeq(seq);
		
		to = dao.getBoardModifyViewData(to);
		
		request.setAttribute("boardModifyViewData", to);
		
	}

}

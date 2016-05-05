package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.ReviewBoardDAO;
import model1.ReviewBoardTO;

public class ReviewViewAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String seq_text = request.getParameter("seq");
		
		int seq = Integer.parseInt(seq_text);
		
		System.out.println("review seq : " + seq);
		
		ReviewBoardDAO dao = new ReviewBoardDAO();
		
		ReviewBoardTO to = new ReviewBoardTO();
		to.setSeq(seq);
		to = dao.getBoardViewData(to);		
		
		int flag = 1;	//오류
		
		if(!to.getWdate().equals("")) {
			flag = 0;	//데이터 있음
		} else {
			flag = 2;	//데이터 없음
		}
		
		request.setAttribute("reviewBoardView", to);
		request.setAttribute("flag", flag);
	}

}

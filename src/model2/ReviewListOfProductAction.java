package model2;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.ReviewBoardDAO;
import model1.ReviewBoardTO;

public class ReviewListOfProductAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String no_text = request.getParameter("no");
		
		int productNo = Integer.parseInt(no_text);
		
//		System.out.println("product no : " + productNo);
		
		ReviewBoardDAO dao = new ReviewBoardDAO();

		ArrayList<ReviewBoardTO> reviewList = dao.getReviewListForProduct(productNo);
		
		int rflag = 1;	//오류
		
		if(reviewList.size() > 0) {
			rflag = 0;	//데이터 있음
		} else if(reviewList.size() == 0) {
			rflag = 2;	//데이터 없음
		}
		
		request.setAttribute("reviewListOfProduct", reviewList);
		request.setAttribute("rflag", rflag);
	
	
	
	}

}

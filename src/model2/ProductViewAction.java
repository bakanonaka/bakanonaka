package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.ProductDAO;
import model1.ProductTO;


public class ProductViewAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String no_text = request.getParameter("no");
		
		int productNo = Integer.parseInt(no_text);
		
//		System.out.println("product no : " + productNo);
		ProductDAO dao = new ProductDAO();
		
		ProductTO to = new ProductTO();
		to.setNo(productNo);
		
		to = dao.getProductViewData(to);
		
		
		int flag = 1;
		
		if(!to.getName().equals("")) {
			flag = 0;	//데이터 있음
		} else {
			flag = 2;	//데이터 없음
		}
		
		request.setAttribute("productInfo", to);
		request.setAttribute("flag", flag);
		
	}

}

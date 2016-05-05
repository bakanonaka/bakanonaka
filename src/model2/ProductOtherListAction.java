package model2;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.ProductDAO;
import model1.ProductTO;

public class ProductOtherListAction implements BasicAction {

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
		
		ArrayList<ProductTO> otherProductList = dao.getOtherProductList(to);
		
		int pflag = 1;
		
		if(otherProductList.size() > 0) {
			pflag = 0;
		} else if(otherProductList.size() == 0) {
			pflag = 2;
		}
		
		request.setAttribute("otherProductList", otherProductList);
		request.setAttribute("pflag", pflag);
		
	}

}

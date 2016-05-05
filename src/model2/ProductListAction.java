package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.ProductListTO;
import model1.ProductDAO;

public class ProductListAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String cpage_text = request.getParameter("cpage");
		
		int cpage = Integer.parseInt(cpage_text);
		
		System.out.println("productlist cpage : " + cpage);
		
		ProductListTO productListTO = new ProductListTO();
		productListTO.setCpage(cpage);
		
		ProductDAO dao = new ProductDAO();
		productListTO = dao.getProductsOfColorList(productListTO);
		
		int flag = 1;
		
		if(productListTO.getProductsList().size() > 0) {
			flag = 0;
		} else if(productListTO.getProductsList().size() == 0) {
			flag = 2;
		}
		
		request.setAttribute("productsList", productListTO);
		request.setAttribute("flag", flag);
		
	}

}

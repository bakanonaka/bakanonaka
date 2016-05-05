package model2;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.ProductDAO;
import model1.ProductTO;

public class ProductActionByBrand implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
			
		
			String brandNoName = request.getParameter("brandNoName");

			int brandNo = Integer.parseInt(brandNoName.substring(0, brandNoName.indexOf(".")));
			
			ProductDAO dao = new ProductDAO();
			ArrayList<ProductTO> productList = dao.getProductList(brandNo);
			
			request.setAttribute("productsForBrand", productList);
	}

}

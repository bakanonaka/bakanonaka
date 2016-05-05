package model2;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.ProductDAO;
import model1.ProductTO;

public class ProductActionByProduct implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
			
		
//			String productNoName = request.getParameter("productNoName");
//			int productNo = Integer.parseInt(productNoName.substring(0, productNoName.indexOf(".")));
			
			String productName = request.getParameter("productName");
		
			ProductDAO dao = new ProductDAO();
//			ArrayList<ProductTO> productList = dao.getProductListForColors(productNo);
			ArrayList<ProductTO> productList = dao.getProductListForColors(productName);
			
			request.setAttribute("colorsForProduct", productList);
	}

}

package model2;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.ProductDAO;
import model1.ProductTO;

public class ProductAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
			ProductDAO dao = new ProductDAO();
			ArrayList<ProductTO> productList = dao.getProductList(1);
			
			request.setAttribute("productList", productList);
	}

}

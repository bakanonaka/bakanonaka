package model2;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.BrandDAO;
import model1.BrandTO;

public class BrandListAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
			BrandDAO dao = new BrandDAO();
			ArrayList<BrandTO> brandList = dao.getBrandList();
			
			request.setAttribute("brandList", brandList);
	}

}

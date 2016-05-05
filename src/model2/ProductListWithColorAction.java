package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.ProductListTO;
import model1.ProductDAO;

public class ProductListWithColorAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String cpage_text = request.getParameter("cpage");
		String colorNum_text = request.getParameter("colorNum");
		String seasonName = request.getParameter("seasonName");
		
		
		int cpage = Integer.parseInt(cpage_text);
		
		System.out.println("productlistwithColor cpage : " + cpage);
		
		ProductListTO productListTO = new ProductListTO();
		productListTO.setCpage(cpage);
		
		ProductDAO dao = new ProductDAO();
		
		int flag = 1;
		
		if(colorNum_text != null && seasonName != null) {
			
			int colorNum = Integer.parseInt(colorNum_text);
			
			//몫을 계산해줘야함. 70개(1번 ~ 70번)를 10그룹으로, 봄/가을 70개, 여름/겨울 60개임.
			int colorGroup = 0;
			
			colorGroup = colorNum / 7 + 1;	// 0은 선택 안함.
			
			System.out.println("colorGroup : " + colorGroup);
			System.out.println("seasonName : " + seasonName);
			
			productListTO = dao.getProductsOfColorListWithColor(productListTO, colorGroup, seasonName);
		} else {
			//에러는 나지 않게 전체 검색으로 대체. 대신, 화면에는 화장품 보이지 않게 flag를 2로 함.
			productListTO = dao.getProductsOfColorList(productListTO);
		}
		
		if(productListTO.getProductsList().size() > 0) {
			flag = 0;
		} else if(productListTO.getProductsList().size() == 0) {
			flag = 2;
		}
		
		request.setAttribute("productsList", productListTO);
		request.setAttribute("flag", flag);
		
	}

}

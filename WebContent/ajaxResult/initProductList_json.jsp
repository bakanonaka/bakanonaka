<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@page import = "java.util.ArrayList" %>
    <%@page import = "model1.ProductListTO" %>
    <%@page import = "model1.ProductTO" %>
        
    <%
    	int flag = (Integer)request.getAttribute("flag");
    	ProductListTO productsListTO = (ProductListTO)request.getAttribute("productsList");
    	
    	// 1. 현재 페이지
    	int currentPage = 1;
    	
    	currentPage = productsListTO.getCpage();
    	
    	// 2. 페이지 당 데이터 갯수
    	int recordPerPage = productsListTO.getRecordPerPage();

    	//전체 페이지 갯수
    	int totalPage = productsListTO.getTotalPage();
    	
    	//페이지당 블럭 갯수, 블럭 내 시작&끝page
    	int blockPerPage = productsListTO.getBlockPerPage();
    	int numberOfStartBlock = productsListTO.getStartBlock();
    	int numberOfEndBlock = productsListTO.getEndBlock();
    	
    	ArrayList<ProductTO> productsList = productsListTO.getProductsList();
    	
    	
		StringBuffer result = new StringBuffer();		
    	
		result.append("[");
    	
    	result.append("{");
    	result.append("\"flag\":\"" + flag + "\",");
    	result.append("\"currentPage\":\"" + currentPage + "\",");
    	result.append("\"recordPerPage\":\"" + recordPerPage + "\",");
    	result.append("\"totalPage\":\"" + totalPage + "\",");
    	result.append("\"blockPerPage\":\"" + blockPerPage + "\",");
    	result.append("\"numberOfStartBlock\":\"" + numberOfStartBlock + "\",");
    	result.append("\"numberOfEndBlock\":\"" + numberOfEndBlock + "\"");
    	result.append("}");
    	
    	for(ProductTO to : productsList){
    		result.append(",{");
       		result.append("\"no\":\"" + to.getNo() + "\",");
       		result.append("\"name\":\"" + to.getName() + "\",");
       		result.append("\"price\":\"" + to.getPrice() + "\",");
       		result.append("\"countOfstar\":\"" + to.getCountOfStar() + "\",");
       		result.append("\"colorName\":\"" + to.getColorName() + "\",");
       		result.append("\"spring\":\"" + to.getSpringNumber() + "\",");
       		result.append("\"summer\":\"" + to.getSummerNumber() + "\",");
       		result.append("\"fall\":\"" + to.getFallNumber() + "\",");
       		result.append("\"winter\":\"" + to.getWinterNumber() + "\",");
       		result.append("\"basicProductImgName\":\"" + to.getBasicProductImgName() + "\",");
       		result.append("\"colorProductImgName\":\"" + to.getColorProductImgName() + "\",");
       		result.append("\"colorImgName\":\"" + to.getColorImgName() + "\",");
       		result.append("\"testImgName\":\"" + to.getTestImgName() + "\",");
       		result.append("\"brandName\":\"" + to.getBrandName() + "\",");
       		result.append("\"productKindName\":\"" + to.getProductKindName() + "\"");
       		result.append("}");
    	}
    	
   		result.append("]");
   		
	%>

<%=result %>
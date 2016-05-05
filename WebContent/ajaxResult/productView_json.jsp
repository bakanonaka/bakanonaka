<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@page import = "java.util.ArrayList" %>
    <%@page import = "model1.ProductTO" %>
    <%@page import = "model1.ReviewBoardTO"%>
        
    <%
    	int flag = (Integer)request.getAttribute("flag");
    	int pflag = (Integer)request.getAttribute("pflag");
    	int rflag = (Integer)request.getAttribute("rflag");
    	
	    ProductTO to = (ProductTO)request.getAttribute("productInfo");
    
	    ArrayList<ReviewBoardTO> reviewList = (ArrayList<ReviewBoardTO>)request.getAttribute("reviewListOfProduct");
	    
	    ArrayList<ProductTO> productList = (ArrayList<ProductTO>)request.getAttribute("otherProductList");

	    
		StringBuffer result = new StringBuffer();		
    	
		result.append("[");
    	
    	result.append("{");
    	result.append("\"flag\":\"" + flag + "\"");
    	result.append("}");
    	
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
    	
       	
       	if(pflag == 0) {
       		result.append(",{");
       		
	       	for(int i = 0; i < productList.size(); i++) {
	       		
	       		ProductTO pto = productList.get(i);
	       		
	       		result.append("\"otherProduct"+i+"\":{");
	       		
	       		result.append("\"no\":\"" + pto.getNo() + "\",");
	       		result.append("\"name\":\"" + pto.getName() + "\",");
	       		result.append("\"price\":\"" + pto.getPrice() + "\",");
	       		result.append("\"countOfstar\":\"" + pto.getCountOfStar() + "\",");
	       		result.append("\"colorName\":\"" + pto.getColorName() + "\",");
	       		result.append("\"spring\":\"" + pto.getSpringNumber() + "\",");
	       		result.append("\"summer\":\"" + pto.getSummerNumber() + "\",");
	       		result.append("\"fall\":\"" + pto.getFallNumber() + "\",");
	       		result.append("\"winter\":\"" + pto.getWinterNumber() + "\",");
	       		result.append("\"basicProductImgName\":\"" + pto.getBasicProductImgName() + "\",");
	       		result.append("\"colorProductImgName\":\"" + pto.getColorProductImgName() + "\",");
	       		result.append("\"colorImgName\":\"" + pto.getColorImgName() + "\",");
	       		result.append("\"testImgName\":\"" + pto.getTestImgName() + "\",");
	       		result.append("\"brandName\":\"" + pto.getBrandName() + "\",");
	       		result.append("\"productKindName\":\"" + pto.getProductKindName() + "\"");
	       		
	       		if(i == productList.size() - 1) {
	       			result.append("}");
	       		} else {
	       			result.append("},");	
	       		}
	    	}
	       	
	       	result.append("}");
       	}
       	
       	if(rflag == 0) {
       		result.append(",{");
       		
       	
       		for(int j = 0; j < reviewList.size(); j++) {
       			ReviewBoardTO rto = reviewList.get(j);
       			
       			result.append("\"reviewOfProduct"+j+"\":{");
       			
           		result.append("\"seq\":\"" + rto.getSeq() + "\",");
           		result.append("\"subject\":\"" + rto.getSubject() + "\",");
           		result.append("\"wdate\":\"" + rto.getWdate() + "\",");
           		
           		// 태그에 "로 표시 되기 때문에 json 형식과 충돌됨. " -> ' 로 모두 바꿔줌.
           		result.append("\"content\":\"" + rto.getContent().replaceAll("\"", "\'") + "\",");
           		
           		result.append("\"reviewImgName\":\"" + rto.getReviewImgName() + "\",");
           		result.append("\"productImgName\":\"" + rto.getProductImgName() + "\",");
           		result.append("\"productName\":\"" + rto.getProductName() + "\",");
           		result.append("\"brandName\":\"" + rto.getBrandName() + "\",");
           		result.append("\"countOfStar\":\"" + rto.getCountOfStar() + "\",");
           		result.append("\"colorNameOfProduct\":\"" + rto.getColorNameOfProduct() + "\",");
           		result.append("\"writer\":\"" + rto.getWriter() + "\"");
           		
           		if(j == reviewList.size() - 1) {
	       			result.append("}");
	       		} else {
	       			result.append("},");	
	       		}
       		}
       		
       		result.append("}");
       	}
       	
   		result.append("]");
   		
//    		System.out.println("result : " + result);
   		
	%>

<%=result %>
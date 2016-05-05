<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@page import = "java.util.ArrayList" %>
    <%@page import = "model1.ReviewBoardTO" %>
        
    <%
    	int flag = (Integer)request.getAttribute("flag");
    	ReviewBoardTO to = (ReviewBoardTO)request.getAttribute("reviewBoardView");
    	
		StringBuffer result = new StringBuffer();		
    	
		result.append("[");
    	
    	result.append("{");
    	result.append("\"flag\":\"" + flag + "\"");
    	result.append("}");
    	
    	result.append(",{");
       	result.append("\"seq\":\"" + to.getSeq() + "\",");
       	result.append("\"subject\":\"" + to.getSubject() + "\",");
       	result.append("\"wdate\":\"" + to.getWdate() + "\",");
       		
       	// 태그에 "로 표시 되기 때문에 json 형식과 충돌됨. " -> ' 로 모두 바꿔줌.
       	result.append("\"content\":\"" + to.getContent().replaceAll("\"", "\'") + "\",");
       		
       	result.append("\"reviewImgName\":\"" + to.getReviewImgName() + "\",");
       	result.append("\"productImgName\":\"" + to.getProductImgName() + "\",");
       	result.append("\"productName\":\"" + to.getProductName() + "\",");
       	result.append("\"brandName\":\"" + to.getBrandName() + "\",");
       	result.append("\"countOfStar\":\"" + to.getCountOfStar() + "\",");
       	result.append("\"colorNameOfProduct\":\"" + to.getColorNameOfProduct() + "\",");
       	result.append("\"writer\":\"" + to.getWriter() + "\"");
       	result.append("}");
    	
   		result.append("]");
   		
   		System.out.println("result : " + result);
   		
	%>

<%=result %>
<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@page import = "java.util.ArrayList" %>
    <%@page import = "model1.ProductTO"%>
        
    <%
    	ArrayList<ProductTO> productList = (ArrayList<ProductTO>)request.getAttribute("colorsForProduct");
    	
    	
		StringBuffer result = new StringBuffer();		
    	
		result.append("[");
		
		for(int i = 0; i < productList.size(); i++) {
			
			ProductTO to = productList.get(i);
			
			result.append("{");
       		result.append("\"no\":\"" + to.getNo() + "\",");
       		result.append("\"name\":\"" + to.getName() + "\",");
       		result.append("\"colorName\":\"" + to.getColorName() + "\"");
       		
       		if(i == productList.size()-1) {
       			result.append("}");
       		} else {
       			result.append("},");
       		}
		}
    	
   		result.append("]");
   		
//    		System.out.println("result : " + result);
   		
	%>

<%=result %>
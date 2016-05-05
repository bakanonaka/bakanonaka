<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	int flag = (Integer)request.getAttribute("flag");

 		//확인
 		//System.out.println("123");
 		
    	StringBuffer result = new StringBuffer();
    		
    	result.append("<data>");
   		result.append("<flag>"+flag+"</flag>");
   		
   		result.append("</data>");
   		
   		//확인

   		//System.out.println(result);
	%>

<data>
<%=result %>
</data>
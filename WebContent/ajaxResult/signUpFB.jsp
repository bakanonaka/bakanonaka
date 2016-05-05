<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	int flag = (Integer)request.getAttribute("flag");
		//String id = (String)request.getAttribute("id");
		//String password = (String)request.getAttribute("password");
		
 		//확인
 		//System.out.println("123");
 		
    	StringBuffer result = new StringBuffer();
    		
    	result.append("<data>");
   		result.append("<flag>"+flag+"</flag>");
   		
   		result.append("</data>");
   		
   		//확인
		//session.setAttribute("id", id);
		//session.setAttribute("password", password);
   		//System.out.println(result);
	%>

<data>
<%=result %>
</data>
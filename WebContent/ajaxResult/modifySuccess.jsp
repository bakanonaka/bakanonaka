<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	int flag = (Integer)request.getAttribute("flag");
		String name = (String)request.getAttribute("name");
    	
    	StringBuffer result = new StringBuffer();
    		
    	result.append("<data>");
   		result.append("<flag>"+flag+"</flag>");
   		result.append("<name>"+name+"</name>");
   		result.append("</data>");
   		
		session.setAttribute("name", name);
	%>

<data>
<%=result %>
</data>
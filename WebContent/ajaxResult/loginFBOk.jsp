<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	int flag = (Integer)request.getAttribute("flag");
    
    	String id = (String)request.getAttribute("id");
    	String name = (String)request.getAttribute("name");
    	int memNo = (Integer)request.getAttribute("memNo");
    	String password = (String)request.getAttribute("password");
    
    	StringBuffer result = new StringBuffer();
    		
    	result.append("<data>");
   		result.append("<flag>"+flag+"</flag>");
   		result.append("<id>"+id+"</id>");
   		result.append("<name>"+name+"</name>");
   		result.append("<memNo>"+memNo+"</memNo>");
   		if (password.equals("facebook")) {
   			result.append("<password>"+ password +"</password>");
   		} else {
   			result.append("<password>"+ "" +"</password>");
   		}
   		result.append("</data>");
   		
   		session.setAttribute("id", id);
   		session.setAttribute("name", name);
   		session.setAttribute("memNo", String.valueOf(memNo));
   		
	%>

<data>
<%=result %>
</data>
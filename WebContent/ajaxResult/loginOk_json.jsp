<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	int flag = (Integer)request.getAttribute("flag");
    	String id = (String)request.getAttribute("id");
    	String name = (String)request.getAttribute("name");
    
    	StringBuffer result = new StringBuffer();
    		
    	result.append("[");
    	result.append("{");
   		result.append("\"flag\":\"" + flag + "\",");
   		result.append("\"id\":\"" + id + "\",");
   		result.append("\"name\":\"" + name + "\"");
   		result.append("}");
   		result.append("]");
   		
   		session.setAttribute("id", id);
   		session.setAttribute("name", name);
	%>

<%=result %>
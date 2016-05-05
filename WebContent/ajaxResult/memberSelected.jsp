<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
	    	
    	String emails = (String)request.getAttribute("emails");
    
//     System.out.println(emails);
    
    	StringBuffer result = new StringBuffer();
    		
    	result.append("<data>");
   		result.append("<emails>"+emails+"</emails>");

   		result.append("</data>");		
	%>

<data>
<%=result %>
</data>
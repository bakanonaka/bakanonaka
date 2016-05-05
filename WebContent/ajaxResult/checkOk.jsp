<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	int idflag = (Integer)request.getAttribute("idflag");
    	int nameflag = (Integer)request.getAttribute("nameflag");
    	int emailflag = (Integer)request.getAttribute("emailflag");
    	
    	StringBuffer result = new StringBuffer();
    		
    	result.append("<data>");
   		result.append("<idflag>"+idflag+"</idflag>");
   		result.append("<nameflag>"+nameflag+"</nameflag>");
   		result.append("<emailflag>"+emailflag+"</emailflag>");
   		result.append("</data>");
   		   		
	%>

<data>
<%=result %>
</data>
<%@page import="model1.MemberTO"%>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	MemberTO memberTo = (MemberTO)request.getAttribute("MemberTO");
    	String id = memberTo.getId();
    	String name = memberTo.getName();
    	String email = memberTo.getEmail();
    	int likecolor = memberTo.getLikeColor();
    	String seasonoflikecolor = memberTo.getSeasonOfLikeColor();
    	int receivemailflag = memberTo.getReceiveMailFlag();
    	    	
    	StringBuffer result = new StringBuffer();
    		
    	result.append("<data>");   		
   		result.append("<id>"+id+"</id>");
   		result.append("<name>"+name+"</name>");
   		result.append("<email>"+email+"</email>");
   		result.append("<likecolor>"+likecolor+"</likecolor>");
   		result.append("<seasonoflikecolor>"+seasonoflikecolor+"</seasonoflikecolor>");
   		result.append("<receivemailflag>"+receivemailflag+"</receivemailflag>");
   		result.append("</data>");
   		
   		//System.out.println(id+name+email+likecolor+seasonoflikecolor);
	%>

<data>
<%=result %>
</data>
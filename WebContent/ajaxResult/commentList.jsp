<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@page import = "java.util.ArrayList" %>    
	<%@page import="model1.CommentTO"%>
	        
    <%

		//ArrayList<String> commentList = <String>request.getAttribute("commentList");
    	ArrayList<CommentTO> commentLists = (ArrayList<CommentTO>)request.getAttribute("commentLists");
    	
    	
		StringBuffer result = new StringBuffer();		
    	
		result.append("[");
    	
    	
    	for(int i = 0; i< commentLists.size(); i++){
    		CommentTO to = commentLists.get(i);
    		if(i==0) {
    			result.append("{");
    		} else{
    			result.append(",{");	
    		}
    	   	
       		result.append("\"no\":\"" + to.getNo() + "\",");
       		result.append("\"comment\":\"" + to.getComment() + "\",");
       		result.append("\"name\":\"" + to.getName() + "\",");
       		result.append("\"wdate\":\"" + to.getWdate() + "\"");
       		result.append("}");
    	}
    	
   		result.append("]");
   		System.out.println(result);
	%>

<%=result %>

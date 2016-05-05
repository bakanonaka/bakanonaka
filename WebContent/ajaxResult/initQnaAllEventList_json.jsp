<%@page import="model1.EventListTO"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@page import = "java.util.ArrayList" %>
    <%@page import = "model1.EventTO" %>
        
    <%
    	int flag = (Integer)request.getAttribute("flag");
    	ArrayList<EventTO> eventsList = (ArrayList<EventTO>)request.getAttribute("eventAllList");
    	
		StringBuffer result = new StringBuffer();		
    	
		result.append("[");
    	
    	result.append("{");
    	result.append("\"flag\":\"" + flag + "\"");
    	result.append("}");
    
    	for(EventTO to : eventsList){
    		result.append(",{");
       		result.append("\"no\":\"" + to.getNo() + "\",");
       		result.append("\"title\":\"" + to.getTitle() + "\",");
       		result.append("\"imgName\":\"" + to.getImgName() + "\",");
       		result.append("\"brandName\":\"" + to.getBrandName() + "\",");
       		result.append("\"dateFrom\":\"" + to.getDateFrom() + "\",");
       		result.append("\"dateTo\":\"" + to.getDateTo() + "\",");
       		result.append("\"eventBoardUrl\":\"" + to.getUrl() + "\"");

//				private String address;		//가장 가까운 지점주소
//				private String storeName;	//가장 가까운 지점명

       		result.append("}");
    	}
    	
   		result.append("]");
   		
	%>

<%=result %>
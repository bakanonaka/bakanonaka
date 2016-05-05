<%@page import="model1.EventListTO"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@page import = "java.util.ArrayList" %>
    <%@page import = "model1.EventListTO" %>
    <%@page import = "model1.EventTO" %>
        
    <%
    	int flag = (Integer)request.getAttribute("flag");
    	EventListTO eventsListTO = (EventListTO)request.getAttribute("eventsList");
    	
    	// 1. 현재 페이지
    	int currentPage = 1;
    	
    	currentPage = eventsListTO.getCpage();
    	
    	// 2. 페이지 당 데이터 갯수
    	int recordPerPage = eventsListTO.getRecordPerPage();

    	//전체 페이지 갯수
    	int totalPage = eventsListTO.getTotalPage();
    	
    	//페이지당 블럭 갯수, 블럭 내 시작&끝page
    	int blockPerPage = eventsListTO.getBlockPerPage();
    	int numberOfStartBlock = eventsListTO.getStartBlock();
    	int numberOfEndBlock = eventsListTO.getEndBlock();
    	
    	ArrayList<EventTO> eventsList = eventsListTO.getEventsList();
    	
    	
		StringBuffer result = new StringBuffer();		
    	
		result.append("[");
    	
    	result.append("{");
    	result.append("\"flag\":\"" + flag + "\",");
    	result.append("\"currentPage\":\"" + currentPage + "\",");
    	result.append("\"recordPerPage\":\"" + recordPerPage + "\",");
    	result.append("\"totalPage\":\"" + totalPage + "\",");
    	result.append("\"blockPerPage\":\"" + blockPerPage + "\",");
    	result.append("\"numberOfStartBlock\":\"" + numberOfStartBlock + "\",");
    	result.append("\"numberOfEndBlock\":\"" + numberOfEndBlock + "\"");
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
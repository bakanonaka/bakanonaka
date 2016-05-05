<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@page import = "java.util.ArrayList" %>
    <%@page import = "model1.BoardListTO" %>
    <%@page import = "model1.QnABoardTO" %>
        
    <%
    	int flag = (Integer)request.getAttribute("flag");
    	BoardListTO boardListTO = (BoardListTO)request.getAttribute("qnaBoardList");
    	
    	// 1. 현재 페이지
    	int currentPage = 1;
    	
    	currentPage = boardListTO.getCpage();
    	
    	// 2. 페이지 당 데이터 갯수
    	int recordPerPage = boardListTO.getRecordPerPage();

    	//전체 페이지 갯수
    	int totalPage = boardListTO.getTotalPage();
    	
    	//페이지당 블럭 갯수, 블럭 내 시작&끝page
    	int blockPerPage = boardListTO.getBlockPerPage();
    	int numberOfStartBlock = boardListTO.getStartBlock();
    	int numberOfEndBlock = boardListTO.getEndBlock();
    	
    	ArrayList<QnABoardTO> qnaBoardList =  boardListTO.getQnaBoardList();
    	
    	
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
    	
    	for(QnABoardTO to : qnaBoardList){
    		result.append(",{");
       		result.append("\"seq\":\"" + to.getSeq() + "\",");
       		result.append("\"subject\":\"" + to.getSubject() + "\",");
       		result.append("\"wdate\":\"" + to.getWdate() + "\",");

       		// 태그에 "로 표시 되기 때문에 json 형식과 충돌됨. " -> ' 로 모두 바꿔줌.
       		result.append("\"content\":\"" + to.getContent().replaceAll("\"", "\'") + "\",");
       	
       		result.append("\"eventName\":\"" + to.getEventName() + "\",");
       		result.append("\"writer\":\"" + to.getWriter() + "\"");
       		result.append("}");
    	}
    	
   		result.append("]");
   		
//    		System.out.println("result : " + result);
   		
	%>

<%=result %>
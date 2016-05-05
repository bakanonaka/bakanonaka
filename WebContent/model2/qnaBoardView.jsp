<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="model1.QnABoardTO"%>
<%@page import="model1.BoardListTO"%>

<%
	QnABoardTO to = (QnABoardTO)request.getAttribute("boardViewData");

	int seq = to.getSeq();
	String subject = "";
	String wdate = "";
	String content = "";
	String eventName = "";
	String writer = "";
	
	subject = to.getSubject();
	wdate = to.getWdate();
	content = to.getContent();
	eventName = to.getEventName();
	writer = to.getWriter();
	
	
%>

<html>
<head>
<title>RedLipstick</title>
<!-- Styles -->
		<!-- Bootstrap CSS -->
		<link href="css/bootstrap.css" rel="stylesheet">
		
		<!-- Animate CSS -->
		<link href="css/animate.min.css" rel="stylesheet">
		
		<!-- Font awesome CSS -->
		<link href="css/font-awesome.min.css" rel="stylesheet">	
			
		<!-- Custom CSS -->
		<link href="css/style.css" rel="stylesheet">
		
		<!-- Custom CSS by YYJ -->
		<link rel="stylesheet" href="css/cupertino/jquery-ui.css">



<!-- Javascript files -->
		<!-- jQuery -->
		<script src="js/jquery.js"></script>
		
		<!-- Bootstrap JS -->
		<script src="js/bootstrap.min.js"></script>
		
		<!-- jQuery way points -->
		<script src="js/waypoints.min.js"></script>
		
		<!-- jQuery prettyPhoto & Isotope -->
		<script src="js/jquery.prettyPhoto.js"></script>
		<script src="js/isotope.js"></script>

		<!-- Custom JS -->
		<script src="js/custom.js"></script>
		
		<script src="js/custom_by_yyj_for_qnaboard_view.js"></script>
<!-- 		스타일 & 스크립트  -->

<style type="text/css">
body{
	color: white;
	
}

margin: auto;
</style>



<meta http-equiv='Content-Type' content='text/html;charset=utf-8'>
<!-- <link rel='stylesheet' type='text/css' href='../images/common.css'> -->
</head>

<!-- <body bgcolor='#ffffff' topmargin='5' rightmargin='0' leftmargin='5' bottommargin='10'> -->
<body>

<div class="wrapper white"> <!-- max width 1300px; -->
		
			<!-- include header -->
			<jsp:include page="/model2/header.jsp" flush ="false"></jsp:include>
			
			<div class="container" style = "margin-bottom: 15px;">
					<table width='100%' border='0' cellpadding='0' cellspacing='0'>
						<tr>
							<td align='right'>
								게시판 > 이벤트 Q&A 게시판
							</td>
						</tr>
					</table>
			</div>

<div>

<table style="width: 65%;" border='0' cellpadding='0' cellspacing='0' align='center'><!-- 뷰 테이블 크기 -->
<tr>
<td align="center" >
		<table width='100%' cellpadding='0' cellspacing='0' border='0' align='center'> <!-- 글쓰기, 수정, 삭제 버튼 -->
		
<!-- <table width="100%" cellspacing="0" cellpadding="0"> -->
<!--  <table border="1" summary="" bgcolor="ffffff">  테이블 테두리--> 

<tbody>
<tr><td height="2" bgcolor="ffffff"></td></tr> <!-- 뷰 테이블 줄 크기, 색 -->
<tr>

    <td class="mw_basic_view_subject br-red">
                <h3 ><%=subject %></h3>
    </td>
</tr>
<tr><td height="2" bgcolor="ffffff"></td></tr>

<tr>

    <td height="30" class="mw_basic_view_title">
        글쓴이 : 
	<span class="mw_basic_view_name" ><%=writer %></span>
        		    </td>
</tr>
<tr class="etcArea" >

<tr>

    <td height="30" class="mw_basic_view_date">
        날짜 : 
	<span class="mw_basic_view_date" ><%=wdate %></span>
        		    </td>
</tr>
<tr>

    <td height="30" class="mw_basic_view_event">
        이벤트 : 
	<span class="mw_basic_view_event" ><%=eventName %></span>
        		    </td>
</tr>



<tr>
    <td class="mw_basic_view_content">
<br>
        <div id="view_content" style="word-wrap:break-word; overflow-wrap:break-word; width:764px;">

<tr>
			<td height="2" bgcolor="ffffff"></td>
		</tr>
		<tr>
			<td height='100'><%=content %></td>
		</tr>
        </div>


        <!-- 테러 태그 방지용 --><a href=""></a><a href=""></a>


             <div id="mw_good"><br>
    <tbody>
	    <tr>
        <td height="5" colspan="2"></td>
    </tr>
    <table width='100%' border='0' cellspacing='1' cellpadding='1'>
		<tr>
			<td colspan='2' class='gline'></td>
		</tr>
		<tr>
			<td width='500' height='30'>
			<% 
				boolean b_loggedIn = false;
				String str_loggedName = ""; 
				String str_loggedId = "";
				String str_loggedMemNo = "";
												
				if(session.getAttribute("id") != null) {
					b_loggedIn = true;
					str_loggedName = (String)session.getAttribute("name");
					str_loggedId = (String)session.getAttribute("id");
					str_loggedMemNo = (String)session.getAttribute("memNo");													
				}
				
				
				%>

</td></tr>
<tr style="border-top:2px dotted white; border-bottom:2px dotted white">
<td>댓글<br>
<input type="hidden" id="currentUser"  value="<%=str_loggedMemNo %>">
<%=str_loggedName %>님 : <input type="text" id="comment"  style="width:580px; color:black;">
<input type="button" id="commentButton" value="댓글 쓰기" style="color:black;"></td>
</tr>

<tr style="height:10px;"></tr>
<tr id="commentList">
<td style="border:2px solid white;">
<input type="button" id="commentListOk" value="댓글 보기" style="width:100%; color:black;">
<br>
<table id ="commentListView" style="width:100%; color:white;">
</table>
</td>
</tr>

<tr style="height:10px;"></tr>
<tr>
<td>				
				
				<%
// 				out.println("writer : " + writer);
// 				out.println("str_loggedName : " + str_loggedName);
				if(writer.equals(str_loggedName)){
			%>
				<a href='qnaBoardWrite.board' class="btn btn-black"> 글쓰기</a>				  
				<a href='qnaBoardModify.board?seq=<%=seq %>' class="btn btn-black"> 수정</a>
				<a class="btn btn-black" id = 'a_qnaBoardView_Delete'> 삭제</a>
				<input type = 'hidden' value = '<%=seq %>' name = 'seq'>
			<%} else {%>
				<a id = "a_qnaBoardView_Write" class="btn btn-black"> 글쓰기</a>				  
			<%}%>
			
			</td>
			<td align='right'>
				<a href='qnaBoard.board' class="btn btn-black"> 목록보기</a>
			</td>
		</tr>
		<tr>
			<td colspan='2' class='gline'></td>
		</tr>
		</table>
		</tbody>
		</div>
		</td>
		</tr>
		

</tbody>
</table>
</table>


		
<!-- <table width='750px' border='0' cellpadding='0' cellspacing='0' align='center'> -->
<tr>
	<td height='15' bgcolor='#f0f0f0' style='padding:5' align='center'></td>
</tr>
</table>
<!-- 			글 끝	 -->

			<!-- include footer -->
			<jsp:include page="/model2/footer.jsp" flush ="false"></jsp:include>
		
		</div>
		
		
		<!-- Scroll to top -->
		<span class="totop"><a href="#"><i class="fa fa-angle-up"></i></a></span>
	

</body>
</html>

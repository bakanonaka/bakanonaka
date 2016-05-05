<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import = "java.util.ArrayList" %>
<%@page import = "model1.EventTO" %>

<%
	ArrayList<EventTO> eventList = (ArrayList<EventTO>)request.getAttribute("eventAllList");
	
	StringBuffer buffer = new StringBuffer();
	
// 	buffer.append("<select name='eventNo'>");
	for(EventTO to : eventList) {
		int no = to.getNo();
		String title = to.getTitle();
		
		buffer.append("<option value="+no+"."+title+">"+no+" . "+title+"</option>");
// 		buffer.append("<option value="+no+"."+title+">"+no+"</option>");
		
	}
// 	buffer.append("</select>");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

		<!-- SmartEditor 관련 함수 포함 되어 있음 -->
		<script src="js/custom_by_yyj_for_qnaboard_write.js"></script>		
		
		<!-- SmartEditor 2.0 -->
		<script type = "text/javascript" src = "smartEditor20/js/HuskyEZCreator.js" charset="utf-8"></script>
		
</head>
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
			
<!-- 			글 시작 -->
			<table border='0' cellpadding='0' cellspacing='0' align='center' style="color:black">
<tr>
	<td height='23' bgcolor='#ffffff'  align='right' ></td>
</tr>
<tr>
<!-- 	<td bgcolor='#ffffff' style='padding:20'>  없으면 투명 있으면 하얀 박스--> 
		<form action='#' method='post' name='wfrm'>
		<table width='100%' border='0' cellpadding='0' cellspacing='0'>
		<tr>
			<td width='70' style='padding:5' valign='top' align='right'>
				<font class='titdot'>&#149;&nbsp;</font>
				<font class='title'>제목</font> :
			</td>
			<td>
				<input type='text' name='subject' size='70px' class='form' style="color: black">
<!-- 				style="color: black" 추가 해줘야 글자가 검은색으로 나옴...ㄷㄷ -->
			</td>
		</tr>
		<tr>
			<td width='50' style='padding:5' valign='top' align='right'>
				<font class='titdot'>&#149;&nbsp;</font>
				<font class='title'>이벤트 넘버</font> :
			</td>
			
			<td style="color: black" width="50px">
			<select name='eventNo'>
				<%=buffer %>
			</select>
			</td>
		</tr>
		<tr>
			<td colspan='2' class='imgline'></td>
		</tr>
		<!-- <tr>
			<td width='70' style='padding:5' valign='top' align='right'>
				<font class='titdot'>&#149;</font>
				<font class='title'>작성자</font> :
			</td>
			<td>
				이름&nbsp;&nbsp;<input type='text' name='writer' size='10' maxlength='10' class='form'>&nbsp;&nbsp;/&nbsp;
				메일&nbsp;&nbsp;<input type='text' name='mail' size='40' maxlength='70' class='form'>&nbsp;&nbsp;/&nbsp;
				암호&nbsp;&nbsp;<input type='password' name='password' size='10' maxlength='10' class='form'>
			</td>
		</tr> -->
		<tr>
			<td colspan='2' class='imgline'></td>
		</tr>
		<tr>
		
			<td width='70' style='padding:5' valign='top' align='right' >
			
				<font class='titdot'>&#149;&nbsp;</font>
				<font class='title'>내용</font> :
			</td>
			<td>
				<textarea name='content' id = 'content' style='width:655px; height:300px; color: black; display:none;' class='form'></textarea>
<!-- 				<textarea name = 'content' id = 'content' rows = '10' cols = '100' style = 'width:766px; height:412px; display:none;'></textarea> -->
			</td>
			
		</tr>
		<tr>
			<td colspan='2' class='gline'></td>
		</tr>
		</table>

		<table width='100%' cellpadding='0' cellspacing='0' border='0'>
		<tr>
			<td colspan='2' class='gline' height = '10'></td>
		</tr>
		<tr>
			<td align='right' width="450pt">
			</td>
			<td align='right'>
			</td>
			<td width='770' height='30' align='center'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a id = "a_qnaBoard_Write" class="btn btn-black">글쓰기</a>
				<a href='qnaBoard.board' class="btn btn-black"> 목록보기</a>
			</td>
			<td align='right'>			
			</td>
		</tr>
		<tr>
			<td colspan='2' class='gline'></td>
		</tr>
		</table>
		</form>
	</td>
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
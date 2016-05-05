<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import = "java.util.ArrayList" %>
<%@page import = "model1.BrandTO" %>

<%
	ArrayList<BrandTO> brandList = (ArrayList<BrandTO>)request.getAttribute("brandList");
	
	StringBuffer buffer = new StringBuffer();
	
	buffer.append("<select name='brandNo'>");
	for(BrandTO to : brandList) {
		int no = to.getNo();
		String name = to.getName();
		
		buffer.append("<option value="+no+"."+name+">"+no+" . "+name+"</option>");
// 		buffer.append("<option value="+no+"."+title+">"+no+"</option>");
		
	}
	buffer.append("</select>");
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
		
    <script type = "text/javascript">
	
	function ChkForm(form) {
		
		//SmartEditor로 추가된 부분
		oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
// 		alert(document.getElementById("content").value);
		//-------------------------------
		
		if(form.subject.value.trim() == "") {
			alert("제목을 입력하셔야 합니다.");
			return false;
		}
		
		
		if(form.content.value.trim() == "") {
			alert("내용을 입력하셔야 합니다.");
			return false;
		}

// 		if(form.requestImgName.value.trim() == "") {
// 			alert("사진을 추가하셔야 합니다.");
// 			return false;
// 		}
	}
	
    </script>
    
</head>
<body>

<div class="wrapper white"> <!-- max width 1300px; -->
		
			<!-- include header -->
			<jsp:include page="/model2/header.jsp" flush ="false"></jsp:include>


			<div class="container" style = "margin-bottom: 15px;">
			<table width='100%' border='0' cellpadding='0' cellspacing='0'>
						<tr>
							<td align='right'>
								이벤트 관리 페이지 > 이벤트 추가
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
		<form action='eventManageWrite_ok.board' method='post' name='wfrm' onSubmit='return ChkForm(this)' enctype = "multipart/form-data">
		<%
			String str_loggedMemNo = "";
			if(session.getAttribute("id") != null) {
				str_loggedMemNo = (String)session.getAttribute("memNo");
		%>
		<input type = "hidden" name = "memNo" value = "<%=str_loggedMemNo%>">
		<%	} %>
		
		<table width='200%' border='0' cellpadding='0' cellspacing='0'>
		<tr>
			<td width='80' style='padding:5' valign='top' align='right'>
				<font class='titdot'>&#149;&nbsp;</font>
				<font class='title'>제목</font> :
			</td>
			<td>
				<input type='text' name='subject' size='70px' class='form' style="color: black">
<!-- 				style="color: black" 추가 해줘야 글자가 검은색으로 나옴...ㄷㄷ -->
			</td>
		</tr>
		
		<tr>
			<td colspan='2' class='imgline'></td>
		</tr>
		<tr>
			<td width='80' style='padding:5' valign='top' align='right'>
				<font class='titdot'>&#149;&nbsp;</font>
				<font class='title'>기간</font> :
			</td>
			<td>
				<input type='text' name='eventDate' size='100px' class='form' placeholder="XX월 XX일 ~ XX월 XX일" style="color: black">
<!-- 				style="color: black" 추가 해줘야 글자가 검은색으로 나옴...ㄷㄷ -->
			</td>
		</tr>
		
		<tr>
			<td width='50' style='padding:5' valign='top' align='right'>
				<font class='titdot'>&#149;&nbsp;</font>
				<font class='title'>브랜드</font> :
			</td>
			
			<td style="color: black" width="50px">
				<%=buffer %>

			</td>
		</tr>
		<tr>
			<td colspan='2' class='imgline'></td>
		</tr>
		<!-- <tr>
			<td width='80' style='padding:5' valign='top' align='right'>
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
			<td width='80' style='padding:5' valign='top' align='right'>
				<font class='titdot'>&#149;</font>
				<font class='title'>이벤트 이미지</font> :
			</td>
			<td>
				<input type='file' name='eventImgName' size='50' maxlength='45'>
			</td>
		</tr>

		<tr>
			<td colspan='2' class='gline'></td>
		</tr>
				<tr>
			<td width='100' style='padding:5' valign='top' align='right'>
				<font class='titdot'>&#149;&nbsp;</font>
				<font class='title'>이벤트 게시판 URL</font> :
			</td>
			<td>
				<input type='text' name='url' size='200px' class='form' placeholder="http://xxx.com" style="color: black">
<!-- 				style="color: black" 추가 해줘야 글자가 검은색으로 나옴...ㄷㄷ -->
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
			<td align='right' width="550pt">
			</td>
			<td align='right'>
			</td>
			
			<td width='770' height='30' align='center'>
				<a><input type="submit" class="btn btn-black" value = "추가"></a>
				<a href='adminEventManagePage.board' class="btn btn-black"> 목록보기</a>
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
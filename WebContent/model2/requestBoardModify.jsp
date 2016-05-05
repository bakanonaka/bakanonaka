<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import = "java.util.ArrayList" %>
<%@page import = "model1.BrandTO" %>

<%@page import="model1.RequestBoardTO"%>
<%@page import="model1.BoardListTO"%>

<%
	RequestBoardTO requestTo = (RequestBoardTO)request.getAttribute("boardModifyViewData");
	
	int seq = requestTo.getSeq();
	String subject = "";
	String wdate = "";
	String content = "";
	String requestImgName = "";
	String brandName = "";
	String writer = "";
	
	subject = requestTo.getSubject();
	wdate = requestTo.getWdate();
	content = requestTo.getContent();
	requestImgName = requestTo.getRequestImgName();
	brandName = requestTo.getBrandName();
	writer = requestTo.getWriter();

	ArrayList<BrandTO> brandList = (ArrayList<BrandTO>)request.getAttribute("brandList");
	
	StringBuffer buffer = new StringBuffer();
	
	buffer.append("<select name='brandNo'>");
	for(BrandTO to : brandList) {
		int no = to.getNo();
		String name = to.getName();
		
		if(name.equals(brandName)) {
			buffer.append("<option value="+no+"."+name+" selected = 'selected'>"+no+" . "+name+"</option>");
		} else {
			buffer.append("<option value="+no+"."+name+">"+no+" . "+name+"</option>");
		}
		
	}
	buffer.append("</select>");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>RedLipstick</title>
<meta http-equiv='Content-Type' content='text/html;charset=utf-8'>
<!-- <link rel='stylesheet' type='text/css' href='../images/common.css'> -->
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

	<!-- SmartEditor 2.0 -->
		<script type = "text/javascript" src = "smartEditor20/js/HuskyEZCreator.js" charset="utf-8"></script>
		
<script type="text/javascript">
	function ChkForm(form) {
		
		//SmartEditor로 추가된 부분
		oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
// 		alert(document.getElementById("content").value);
		//-------------------------------
		
		
		if (form.subject.value.trim() == "") {
			alert("제목을 입력하셔야 합니다.");
			return false;
		}
		if (form.content.value.trim() == "") {
			alert("내용을 입력하셔야 합니다.");
			return false;
		}
	}
</script>


</head>

<body>

<div class="wrapper white"> <!-- max width 1300px; -->
		
			<!-- include header -->
			<jsp:include page="/model2/header.jsp" flush ="false"></jsp:include>


			<div class="container" style = "margin-bottom: 15px;">
			<table width='200%' border='0' cellpadding='0' cellspacing='0'>
						<tr>
							<td align='right'>
								게시판 > 요청 게시판
							</td>
						</tr>
				</table>
			</div>
			
			<!-- 여기에 내용 작성 -->

<!-- 			글 시작 -->
			<table border='0' cellpadding='0' cellspacing='0' align='center' style="color:black">
<tr>
	<td height='23' bgcolor='#ffffff'  align='right' ></td>
</tr>
<tr>
<!-- 	<td bgcolor='#ffffff' style='padding:20'>  없으면 투명 있으면 하얀 박스--> 
		<form action='requestBoardModify_ok.board' method='post' name='wfrm' onSubmit='return ChkForm(this)' enctype = "multipart/form-data">
		<input type = 'hidden' name = 'seq' value = "<%=seq %>">
		<table width='200%' border='0' cellpadding='0' cellspacing='0'>
		<tr>
			<td width='80' style='padding:5' valign='top' align='right'>
				<font class='titdot'>&#149;&nbsp;</font>
				<font class='title'>제목</font> :
			</td>
			<td>
				<input type='text' name='subject' value="<%=subject %>" size='70px' class='form' style="color: black; width:28%;">
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
		
		<!-- 이미지가 없으면 파일 업로드만 가능하게 함. -->
		<% if(requestImgName == null || requestImgName.equals("")){
		%>
		<tr>
			<td width='80' style='padding:5' valign='top' align='right'>
				<font class='titdot'>&#149;</font>
				<font class='title'>요청 이미지</font> :
			</td>
			<td>
				<input type='file' name='newRequestImgName' size='50' maxlength='45'>
			</td>
		</tr>
		
		<%
		}else {%>
		<tr>
			<td width='80' style='padding:5' valign='top' align='right'>
				<font class='titdot'>&#149;</font>
				<font class='title'>업로드 이미지</font> :
			</td>
			<td  class='imgline'><img src='<%="uploadRequestImage/"+requestImgName%>' name = 'requestImgNamePath' alt = '이미지 없음' style = 'width: 100%; max-width: 250px;'>
			</td>
		</tr>
		
		
		<tr>
			<td width='80' style='padding:5' valign='top' align='right'>
				<font class='titdot'>&#149;</font>
				<font class='title'>수정 이미지</font> :
			</td>
			<td>
				<input type='file' name='newRequestImgName' size='50' maxlength='45'>
			</td>
		</tr>
		<%} %>
		
		<tr>
			<td colspan='2' class='imgline'></td>
		</tr>
		<tr>
		
			<td width='80' style='padding:5' valign='top' align='right' >
			
				<font class='titdot'>&#149;&nbsp;</font>
				<font class='title'>내용</font> :
			</td>
			<td>
			
				<textarea name='content' id = 'content' style='width:655px; height:300px; color: black' class='form'><%=content %></textarea>
				 <!-- 여기에 둬야만 적용된다. -->
				    <script type = "text/javascript">
    
						var oEditors = [];
						nhn.husky.EZCreator.createInIFrame({
							oAppRef: oEditors,
							elPlaceHolder: "content",
							sSkinURI: "smartEditor20/SmartEditor2Skin.html",
							fCreator: "createSEditor2"
						});
						
					</script>
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
			<td width='770' height='30' align='center'>&nbsp;&nbsp;&nbsp;
				<a><input type="submit" class="btn btn-black" value = "수정"></a>
				<a href='requestBoard.board' class="btn btn-black"> 목록보기</a>
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

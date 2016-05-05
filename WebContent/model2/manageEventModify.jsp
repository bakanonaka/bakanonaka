<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import = "java.util.ArrayList" %>
<%@page import = "model1.BrandTO" %>

<%@page import="model1.EventTO"%>

<%
	EventTO eTo = (EventTO)request.getAttribute("eventModifyViewData");

	int eventNo = eTo.getNo();
	String subject = "";
	String eventImgName = "";
	String brandName = "";
	String term = "";
	String url = "";
	int status = 0;
		
	subject = eTo.getTitle();
	eventImgName = eTo.getImgName();
	brandName = eTo.getBrandName();
	term = eTo.getDateFrom() + " ~ " + eTo.getDateTo();
	url = eTo.getUrl();
	status = eTo.getStatus();

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
	
	StringBuffer buffer4 = new StringBuffer();
	
	String status_text = "";
	
	for(int i = 0; i < 3; i++) {//별점 점수 0 ~ 5
		
		switch(i) {
			case 0:
				status_text = "이벤트 진행중";
				break;
			case 1:
				status_text = "이벤트 종료";
				break;
			case 2:
				status_text = "이벤트 예정";
				break;
		}
		
		
		
		if(i == status) {
			buffer4.append("<option value="+i+" selected = 'selected'>"+i+". "+status_text+"</option>");
		} else {
			buffer4.append("<option value="+i+">"+i+". "+status_text+"</option>");	
		}
		
	}
	
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
			<table width='100%' border='0' cellpadding='0' cellspacing='0'>
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
		<form action='eventManageModify_ok.board' method='post' name='wfrm' onSubmit='return ChkForm(this)' enctype = "multipart/form-data">
		<input type = 'hidden' name = 'no' value = "<%=eventNo %>">
		<table width='200%' border='0' cellpadding='0' cellspacing='0'>
		<tr>
			<td width='80' style='padding:5' valign='top' align='right'>
				<font class='titdot'>&#149;&nbsp;</font>
				<font class='title'>제목</font> :
			</td>
			<td>
				<input type='text' name='subject' value="<%=subject %>" size='70px' class='form' style="color: black">
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
				<input type='text' name='eventDate' size='100px' class='form' value = "<%=term %>" placeholder="XX월 XX일 ~ XX월 XX일" style="color: black">
<!-- 				style="color: black" 추가 해줘야 글자가 검은색으로 나옴...ㄷㄷ -->
			</td>
		</tr>
		<tr>
			<td colspan='2' class='imgline'></td>
		</tr>
		
		<tr>
			<td width='50' style='padding:5' valign='top' align='right'>
				<font class='titdot'>&#149;&nbsp;</font>
				<font class='title'>상태</font> :
			</td>
			
			<td style="color: black" width="50px">
				<select name='status'>
				<%=buffer4 %>
				</select>
			</td>
		</tr>
		
		<tr>
			<td colspan='2' class='imgline'></td>
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
		<% if(eventImgName == null || eventImgName.equals("")){
		%>
		<tr>
			<td width='80' style='padding:5' valign='top' align='right'>
				<font class='titdot'>&#149;</font>
				<font class='title'>요청 이미지</font> :
			</td>
			<td>
				<input type='file' name='newEventImgName' size='50' maxlength='45'>
			</td>
		</tr>
		
		<%
		}else {%>
		<tr>
			<td width='80' style='padding:5' valign='top' align='right'>
				<font class='titdot'>&#149;</font>
				<font class='title'>업로드 이미지</font> :
			</td>
			<td  class='imgline'><img src='<%="eventImg/"+eventImgName%>' name = 'requestImgNamePath' alt = '이미지 없음' style = 'width: 100%; max-width: 250px;'>
			</td>
		</tr>
		
		
		<tr>
			<td width='80' style='padding:5' valign='top' align='right'>
				<font class='titdot'>&#149;</font>
				<font class='title'>수정 이미지</font> :
			</td>
			<td>
				<input type='file' name='newEventImgName' size='50' maxlength='45'>
			</td>
		</tr>
		<%} %>
		
		<tr>
			<td colspan='2' class='imgline'></td>
		</tr>
				<tr>
			<td width='100' style='padding:5' valign='top' align='right'>
				<font class='titdot'>&#149;&nbsp;</font>
				<font class='title'>이벤트 게시판 URL</font> :
			</td>
			<td>
				<input type='text' name='url' size='200px' class='form' value = "<%=url %>" placeholder="http://xxx.com" style="color: black">
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
				<a><input type="submit" class="btn btn-black" value = "수정"></a>
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

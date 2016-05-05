<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
		
		<!-- Custom JS by YYJ -->
		<script src="js/custom_by_yyj_for_qnaBoardList.js"></script>
		
		<style type="text/css">
			td span{
		/* 		background: url(images/bar7_1.png) no-repeat right 10px; */
		/* 		background-color: red; */
		  		display:block;
		  		padding:0px 0px 2px 0px;
			}
		</style>
		
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
					
					<br/>
					
					<table width='100%' border='0' cellpadding='0' cellspacing='0'>
						<tr>
							<td style="color: black; text-align:right;">
								<select name='eventNo'>
									<!-- DB의 이벤트 타이틀 가져옴 -->
								</select>
							</td>
						</tr>
					</table>
			</div>		
			
			<!-- Box Wrapper -->
			<div class="box-wrapper">
				<div class="container">

				<div class="row" id = "eventBoxList">

				</div><!-- row -->
				</div><!-- container -->
			</div>	<!-- Box Wrapper-->
	

			<br/><!-- 공간 -->
			<div class="container">
				<table align='center' width='80%' border='0' cellpadding='0' cellspacing='0'>
					<tr valign='bottom'>
						<td width='6%' height='29' align='center' valign='bottom' class="br-red"><span>글번호</span></td>
						<td width='1%' valign='bottom' style="color: black;"></td>
						<td width='26%' align='center' valign='bottom' class="br-red"><span>이벤트</span></td>
						<td width='1%' valign='bottom'></td>
						<td width='40%' align='center' valign='bottom' class="br-red"><span>제목</span></td>
						<td width='1%' valign='bottom'></td>
						<td width='10%' align='center' valign='bottom' class="br-red"><span>작성자</span></td>
						<td width='1%' valign='bottom'></td>
						<td width='14%' align='center' valign='bottom' class="br-red"><span>날짜</span></td>
					</tr>
				</table>
				
				<div id = "qnaBoardList" >
					<!--	custom_by_yyj_for_qnaBoardList.js 의 Ajax로 DB에서 Qna게시글 정보를 가져온다. -->	
				</div>
				
				<br/><!-- 공간 -->
				
					<div style = "width:100%; text-align:center;">
						<div style = "margin: 0 auto;">
							<!-- 좌 블럭 이동 -->
							<span id = "pageNumberList">
							<!-- 페이징 숫자 -->
							</span>
							<!-- 우 블럭 이동-->
						</div>
					</div>
					
				</div>
			
<!-- 글쓰기 버튼 -->
			<div class="container">
				<table width='100%' border='0' cellpadding='0' cellspacing='0'>
						<tr>
							<td width='500' height='30'>&nbsp;</td>
							<td align='right'>
								<a id = "a_qnaBoardList_Write" class="btn btn-black">글쓰기</a>
			<!-- 					<a href='qnaBoardWrite.board'>글쓰기</a> -->
							</td>
						</tr>
				</table>
			</div>
	
<!-- 글쓰기 버튼 끝 -->
			<!-- include footer -->
			<jsp:include page="/model2/footer.jsp" flush ="false"></jsp:include>
		
</div>
		
		
		<!-- Scroll to top -->
		<span class="totop"><a href="#"><i class="fa fa-angle-up"></i></a></span>
	

</body>
</html>
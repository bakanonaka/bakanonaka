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
		
<!-- 		requestBoardList.js 추가 -->
		
		<!-- Custom JS by YYJ -->
		<script src="js/custom_by_yyj_for_requestBoardList.js"></script>		
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
				<div class="container">

			<div class="inner-page" align="center">
				<div class="container">
					<div class="row">
						
						<!-- Main-bar Column -->
						<div class="col-md-12">
						
						
							<div class="page-mainbar portfolio">
								<!-- Heading -->
								
<!-- 								<h1>후기게시판</h1> -->
								<!-- Portfolio Content -->
								<div class="portfolio-content">
									<div class="img-portfolio">
									<br/>
										<p>
											<ul id="filters">
												<li><a href="#" data-filter="*" class="btn btn-default black"><b>All</b></a></li>
												<li><a href="#" data-filter=".토니모리" class="btn br-lightgrey black"><img src='productImg/토니모리/logo/토니모리_small_150.gif'style = 'height: 20px; width: 120px; ' align='middle'/></a></li>
												<li><a href="#" data-filter=".더페이스샵" class="btn br-lightgrey black"><img src='productImg/더페이스샵/logo/더페이스샵_small.gif'style = 'height: 20px; width: 120px; ' align='middle'/></a></li>
												<li><a href="#" data-filter=".미샤" class="btn br-lightgrey black"><img src='productImg/미샤/logo/미샤_small_150.gif'style = 'height: 20px; width: 120px; ' align='middle'/></a></li>
												<li><a href="#" data-filter=".스킨푸드" class="btn br-lightgrey black"><img src='productImg/스킨푸드/logo/스킨푸드_small.gif'style = 'height: 20px; width: 120px; ' align='middle'/></a></li>
												<li><a href="#" data-filter=".에뛰드하우스" class="btn br-lightgrey black" ><img src='productImg/에뛰드하우스/logo/에뛰드하우스_small_150.gif'style = 'height: 20px; width: 120px; ' align='middle'/></a></li>
												<li><a href="#" data-filter=".이니스프리" class="btn br-lightgrey black" ><img src='productImg/이니스프리/logo/이니스프리_small_150.gif'style = 'height: 20px; width: 120px; ' align='middle'/></a></li>
												
											</ul>
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
								
				<table align='center' width='80%' border='0' cellpadding='0' cellspacing='0'>
					<tr valign='bottom'>
						<td width='6%' height='29' align='center' valign='bottom'
							class="br-green"><span>글번호</span></td>
						<td width='1%' valign='bottom' style="color: black;"></td>
						<td width='15%' align='center' valign='bottom' class="br-green"><span>브랜드</span></td>
						<td width='1%' valign='bottom'></td>
						<td width='49%' align='center' valign='bottom' class="br-green"><span>제목</span></td>
						<td width='1%' valign='bottom'></td>
						<td width='10%' align='center' valign='bottom' class="br-green"><span>작성자</span></td>
						<td width='1%' valign='bottom'></td>
						<td width='14%' align='center' valign='bottom' class="br-green"><span>날짜</span></td>
						<br/>
				</table>

				<div id = "requestBoardList">
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
						<a id = "a_requestBoardList_Write" class="btn btn-black">글쓰기</a>
		<!-- 					<a href='requestBoardWrite.board'>글쓰기</a> -->
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
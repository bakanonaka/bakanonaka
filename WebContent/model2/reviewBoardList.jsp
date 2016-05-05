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
		
		<!-- Portfolio CSS -->
		<link href="css/prettyPhoto.css" rel="stylesheet">

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
		
		<script src="js/custom_by_yyj_for_reviewBoardList.js"></script>
		
		<script>
		/*
		var idx = 1;
		var idx_p = 1;
		var page = 1;
		jQuery(document).ready(function () {
		jQuery(window).scroll(function() {
			if (jQuery(window).scrollTop() == jQuery(document).height() - jQuery(window).height()) {
			var newDiv=document.createElement('div'); 
			newDiv.innerHTML= idx+'<div id="load_deobogi"><center><img src="/modules/board/skins/sketchbook5/deobogi_loading.gif"/></center></div>';
			newDiv.setAttribute('id','more'+idx); 
			setTimeout(function(){
		 morediv = '#more'+idx;
		 morediv_p = '#more'+idx_p;
		 
			var reload_href_full ='http://localhost:8080/ProjectR/reviewBoard.board?&page='+page+'#listmore';
				jQuery(newDiv).insertBefore('#listmore_deobogi' );
				jQuery(morediv).load(reload_href_full).fadeIn(1000).delay(5000);
		 page++;
		  idx++;
		 idx_p++;
		}, 1000);
		 }
		});
		});
		*/
		
		

</script>
			 
</head>
	
	<body>
  
  
  
  
  
<!--                            Body Wrapper                      -->
		<div class="wrapper white">
		
		<!-- include header -->
			<jsp:include page="/model2/header.jsp" flush ="false"></jsp:include>
	
			<div class="container" style = "margin-bottom: 15px;">
				<table width='100%' border='0' cellpadding='0' cellspacing='0'>
						<tr>
							<td align='right'>
								게시판 > 리뷰 게시판
							</td>
						</tr>
				</table>
			</div>
	
			<!-- Box Wrapper -->
			<div class="box-wrapper">
				<div class="container">
				

			<div class="row">

			
			<!-- Inner Page Content // Start -->
			
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
										<p>
											<ul id="filters">
												<br>
												<li><a href="#" data-filter="*" class="btn btn-default black"><b>All</b></a></li>
												<li><a href="#" data-filter=".토니모리" class="btn br-lightgrey black"><img src='productImg/토니모리/logo/토니모리_small_150.gif'style = 'height: 20px; width: 120px; ' align='middle'/></a></li>
												<li><a href="#" data-filter=".더페이스샵" class="btn br-lightgrey black"><img src='productImg/더페이스샵/logo/더페이스샵_small.gif'style = 'height: 20px; width: 120px; ' align='middle'/></a></li>
												<li><a href="#" data-filter=".미샤" class="btn br-lightgrey black"><img src='productImg/미샤/logo/미샤_small_150.gif'style = 'height: 20px; width: 120px; ' align='middle'/></a></li>
												<li><a href="#" data-filter=".스킨푸드" class="btn br-lightgrey black"><img src='productImg/스킨푸드/logo/스킨푸드_small.gif'style = 'height: 20px; width: 120px; ' align='middle'/></a></li>
												<li><a href="#" data-filter=".에뛰드하우스" class="btn br-lightgrey black" ><img src='productImg/에뛰드하우스/logo/에뛰드하우스_small_150.gif'style = 'height: 20px; width: 120px; ' align='middle'/></a></li>
												<li><a href="#" data-filter=".이니스프리" class="btn br-lightgrey black" ><img src='productImg/이니스프리/logo/이니스프리_small_150.gif'style = 'height: 20px; width: 120px; ' align='middle'/></a></li>
												
											</ul>
										</p>
<!-- 												<div id="listmore"> -->
													<div id="portfolio">
														
													</div>
<!-- 													<div id="listmore_deobogi"></div> -->

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
			
			<!-- Inner Page Content // End -->
			
			
			<div class="container">
			<table width='100%' border='0' cellpadding='0' cellspacing='0'>
			<tr>
				<td width='500' height='30'>&nbsp;</td>
				<td align='right'>
<!-- 					<a id = "a_reviewBoardList_More" class="btn btn-black">더보기</a> -->
					<a id = "a_reviewBoardList_Write" class="btn btn-black">글쓰기</a>
<!-- 					<a href='reviewBoardWrite.board' class="btn btn-default">글쓰기</a> -->
				</td>
			</tr>
			</table>
			</div>
			
			<!-- include footer -->
			<jsp:include page="/model2/footer.jsp" flush ="false"></jsp:include>
		
		</div>
		<!--Body Wrapper End -->


		<!-- Scroll to top -->
		<span class="totop"><a href="#"><i class="fa fa-angle-up"></i></a></span>
		
		
		<div id = "reviewModalList"></div> <!-- 모달 넣은거 -->
		
		
		
	</body>	
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Custom JS by YYJ -->
		<script src="js/custom_by_yyj_for_logInOut.js"></script>
		
			<!-- Header Start -->
			<div class="header">
				<!-- Header Navigation -->
				<div class="header-navigation">
					<div class="container">
					<div class="row">
						
							<div class="col-md-4">
								<!-- Logo -->
								<div class="logo">
									<!-- Heading -->
									<h1><a href="index.board"><i class="fa fa-eye-slash br-red"></i> RedLipstick</a></h1>
								</div>
							</div>		
												
							<div class="col-md-8">
								<!-- Bootstrap Navbar -->
								<nav class="navbar navbar-default" role="navigation">
									<!-- Brand and toggle get grouped for better mobile display -->
									
									<div class="navbar-header">
										<button type="button" class="navbar-toggle br-orange" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
											<span class="sr-only">Toggle navigation</span>
											<span class="icon-bar"></span>
											<span class="icon-bar"></span>
											<span class="icon-bar"></span>
										</button>
									</div>
									
									<!-- Collect the nav links, forms, and other content for toggling -->
									<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
										<ul class="nav navbar-nav navbar-right" id = "headerNavUl">
										
											<li>
												<a href="index.board" class="br-orange">
													<!-- Link Icon -->
													<i class="fa fa-home fa-3x"></i>
													<!-- Link Title -->
													<span class="link-title">홈</span>
												</a>
											</li>
											
											<li>
												<a href="event.board" class="br-lblue">
													<!-- Link Icon -->
													<i class="fa fa-exclamation fa-3x"></i>
													<!-- Link Title -->
													<span class="link-title">이벤트</span>
												</a>
											</li>
											
											<li class="dropdown">
												<a href="#" class="dropdown-toggle br-green" data-toggle="dropdown">
													<!-- Link Icon -->
													<i class="fa fa-pencil-square-o fa-3x"></i>
													<!-- Link Title -->
													<span class="link-title">게시판 <b class="fa fa-angle-down"></b></span> 
												</a>
												<ul class="dropdown-menu dropdown-md">
												
													<li>
														<div class="row">
															<div class="col-md-12">
																<div class="col-inner">
																	<ul class="list-unstyled">
																		<li><a href="qnaBoard.board"><i class="fa fa-arrow-right dd-link-icon"></i> 이벤트 Q&A</a></li>
																		<li><a href="reviewBoard.board"><i class="fa fa-arrow-right dd-link-icon"></i> 후기</a></li>
																		<li><a href="requestBoard.board"><i class="fa fa-arrow-right dd-link-icon"></i> 요청</a></li>
																	</ul>
																</div>
															</div>
															
														</div>
													</li>
													
												</ul>
											</li>

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
												
												if(b_loggedIn == false){
											%>
											
											<li id = "loginIcon">
<!-- 												<a href="#" class="br-yellow" data-toggle="modal" data-target="#loginModal"> -->
													<a href="#" class="br-yellow" id = "a_LoginModal_ShowUp">
													<!-- Link Icon -->
													<i class="fa fa-sign-in fa-3x"></i>
													<!-- Link Title -->
													<span class="link-title">로그인</span>
												</a>
											</li>
											
											<%} else {%>
											
											<li class="dropdown" id = "loggedIcon">
												<a href="#" class="dropdown-toggle br-pink" data-toggle="dropdown">
													<i class="fa fa-user fa-3x"></i>
													<span class="link-title" id = "str_loggedName"><%=str_loggedName %>님&nbsp;<b class="fa fa-angle-down"></b></span>
												</a>
												<ul class="dropdown-menu dropdown-default">
												
												<%if(str_loggedId.equals("admin")) { %>
												
													<li><a href="adminPage.board"><i class="fa fa-arrow-right dd-link-icon lblue"></i> 회원 관리 페이지</a></li>
													<li><a href="adminEventManagePage.board"><i class="fa fa-arrow-right dd-link-icon lblue"></i> 이벤트 관리 페이지</a></li>
												<%}else {%>
												
													<li><a href="#" id = "a_modifyButton"><i class="fa fa-arrow-right dd-link-icon lblue"></i> 사용자 정보 수정/탈퇴</a></li>
													
												<%} %>
												
													<li><a href="#" id = "a_logoutButton"><i class="fa fa-arrow-right dd-link-icon lblue"></i> 로그아웃</a></li>
													
												</ul>
											</li>
													<!-- 로그인한 ID, memNo 숨김. 사용자 id로 Ajax로 넘길 때 필요.-->
													<input type="hidden" value="<%=str_loggedId%>" id ="str_loggedId">
													<input type="hidden" value="<%=str_loggedMemNo%>" id ="str_loggedMemNo">
											<%} %>
											
										</ul>
									</div><!-- /.navbar-collapse -->
								</nav>
							</div>
						</div>
					</div>
				</div>
				<!-- Header Navigation End-->
			</div>
<!--                 Header End                 -->

<!-- include Modal for Login -->
 		<jsp:include page="/model2/header_loginModal.jsp" flush ="false"></jsp:include>
<!-- include Modal for Modify --> 		
 		 <jsp:include page="/model2/header_modifyModal.jsp" flush ="false"></jsp:include>
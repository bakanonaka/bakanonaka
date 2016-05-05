<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<script src="js/custom_by_yyj_for_colorpicker_register.js"></script>
<script src="../js/FBjs.js"></script>


<!--                 Modal for Login                 -->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog">

<div class="inner-page">
				<div class="container">
					<div class="page-mainbar login">
						<!-- Login/Register Content -->
						<div class="login-content">
							<!-- Nab Bar Tan Menu list -->
							<ul id="mytab" class="nav nav-tabs nav-justify">
								<li class="active">
									<a href="#login" class="br-red" data-toggle="tab">
										<!-- Icon -->
										<i class="fa fa-sign-in"></i>
										<span>로그인</span>
									</a>
								</li>
								<li>
									<a href="#register" class="br-lblue" data-toggle="tab">
										<!-- Icon -->
										<i class="fa fa-sign-out"></i>
										<span>회원등록</span>
									</a>
								</li>

							</ul>
							<!-- Content for each menu item -->
							<div class="tab-content">
								<!-- First Content for Nav bar -->
								<div class="tab-pane fade in active br-pink" id="login">
									<!-- Heading -->
									<h2>로그인</h2>
									<!-- Sign in Form Start -->
									<form class="form-horizontal" role="form" method="post" id = "loginForm">
										<div class="form-group">
											<div class="col-sm-12">
												<input type="text" class="form-control" id="id" placeholder="아이디">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-12">
												<input type="password" class="form-control" id="password" placeholder="비밀번호">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-12">
												<div class="checkbox">
													
													<span class="pull-right"><a href="#" data-toggle="modal" data-target="#findPassword">비밀번호가 생각나지 않으세요?</font></a></span>
													<div class="clearfix"></div>
												</div>
											</div>
										</div>
										<div class="form-group text-center">
										<div class="col-sm-12">
<!-- 												<button type="submit" class="btn btn-black">로그인</button> submit 때문에 홈으로 다시 자동 이동한다. 불필요함.-->
												
												<input type="hidden" id="response"> 
												
												<a href="#" id = "buttonFB" class="btn btn-black">페이스북 계정연동</a>
												<%if(session.getAttribute("id") == null) { %>
												<jsp:include page="../model2/FBLogin.jsp" flush ="false"></jsp:include>
												<%} else {}; %>
												
												<a id = "a_loginModal_login" class="btn btn-black">로그인</a>												
												
											</div>
										</div>
									</form>
																		
									
<!--  비밀번호 찾기 -->
<div class="modal fade" id="findPassword" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
	<div class="inner-page2">
		<div class="password">
			<div class="password-content">
			<div class="tab-content">
				<div class="tab-pane fade in active br-green">
					<!-- Heading -->
					<h2>비밀번호 찾기</h2>
					<!-- 비밀번호찾기 Form Start -->
					<div>
						회원님의 계정에 등록된 이메일 주소를 입력하시면, <br>임시 비밀번호를 발송해 드립니다.<br>
					</div>
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<div class="col-sm-12">
								<input type="text" class="form-control" id="email"
									placeholder="email">
							</div>
						</div>

						<div class="form-group text-center">
							<div class="col-sm-12">
								<!-- 												<button type="submit" class="btn btn-black">임시 비밀번호 발송</button> -->
								<a id="button_loginModal_sendTempPassword" class="btn btn-black">임시
									비밀번호 발송</a>
							</div>
						</div>
					</form>
					<!-- Sign in form End -->
				</div>
			</div>
			</div>
		</div>
		</div>
	</div>
</div>
									<!-- Sign in form End -->
								</div>
								
								<!-- Second Content for Nav bar -->
								<div class="tab-pane fade br-lblue" id="register">
									<!-- Heading -->
									<h2>회원등록</h2>
									
									<!-- Sign Up Form Start -->
									
									<form class="form-horizontal" role="form" method="post" id = "signUpMain">
										<div class="form-group">
											<div class="col-sm-6" align="left">
												<a href="#" id="signUpFB" class="btn btn-black" style="color:#fff; background:#6799FF;">페이스북으로 회원가입</a>
												<%-- <jsp:include page="../model2/testFB2.jsp" flush ="false"></jsp:include> --%>												
											</div>
											<div class="col-sm-6" align="right">
												
												<a id="signUpEmail" class="btn btn-black" style="color:#fff; background:#FFA7A7;">일반 회원가입</a>												
											</div>										
										</div>
									</form>
									
									<form class="form-horizontal" role="form" method="post" id = "signUpForm">
												
									
									
									</form>
									<form class="form-horizontal" role="form" method="post" id = "signUpFormA">
									<div class="form-group">		
									<div class="col-sm-12">		
									<div class="">
									<label><span class="pull-right">회원 가입시 RedLipstick측에 제공해주신 이메일 주소가 필수 정보제공 및 가입/탈퇴 절차 등을 위해 사용되는 것에 동의하십니까?</span><br>
									<div align="right">네, 동의합니다 <input type="checkbox" id="agreeornot" value="1"></div>
									</label>
									<div class="form-group text-center">
									<div class="col-sm-12">
									<a id = "a_signUpModal_signUp" class="btn btn-black">회원가입</a>
									</div></div>
									</div></div></div></form>
									
									<!-- Sign Up form End -->
								</div>
								
							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
</div>

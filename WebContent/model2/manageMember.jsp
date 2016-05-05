<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%@page import = "java.util.ArrayList" %>
<%@page import = "model1.MemberTO" %>
    

	<%
		ArrayList<MemberTO> boardList = (ArrayList<MemberTO>)request.getAttribute("memberList");
		
		StringBuffer buffer = new StringBuffer();
		String rmF = "";		
        
		String memberColor = "";                
        
        String[] springColor = {"#000000",
				"#fcdbc8", "#fed1b4", "#f8bcb4", "#fca4a3", "#fb9495", "#fb7f7f", "#f99d88",
				"#f53c1e", "#ff4121", "#fc3516", "#e82000", "#d81f01", "#f6252a", "#d91819", 
				"#ffb245", "#ffca7e", "#febc5c", "#ff9b0f", "#ff8a00", "#ff7e00", "#fc6b00",
				"#fff997","#fffcc7","#fefbb1","#fdf681","#ffeb68","#fee63f","#ffdc4e",
				"#acec57","#ccf099","#bde87f","#86dc19","#69db14","#6ebe4b","#04af45",
				"#adf1f2","#cdf8ef","#b9f2e9","#7deeda","#6ce5fa","#31c9fa","#1d4eb7",
				"#a66dd4","#7a43ab","#8942c4","#8b30d5","#9c4cdb","#a35adf","#9854cd",
				"#f5e391","#eac968","#d7ac50","#d69b3d","#b9711d","#9f5a0d","#946027",
				"#bcb6a0","#d8c9c4","#d3c2bb","#c7b4ae","#d6bdb8","#d6c0b2","#c9b4af",
				"#445281","#324377","#2a3869","#212e58","#2a4e8a","#2a4783","#264389"
		};
        

		String[] summerColor = {"#000000",
				"#d18794","#dba2ab","#fdbcdc","#ff89b9","#fa9fcb","#ffd6ea","#fad5dc",
				"#f26170","#f45363","#f54051","#f43344","#eb304f","#e42544","#ca4057",
				"#eeff7d","#efff85","#f2ffa5","#f4ffa3","#f4ffb3","#f6ffbe","#f9ffd9",
				"#86e295","#98eea7","#c8ffd5","#93e3c0","#68cfb2","#43c19c","#27b289",
				"#d1eaff","#bde0fc","#aed8fb","#a2bae8","#7996ce","#78a2ee","#4e6caa",
				"#896388","#8f5a8e","#dbbdf9","#cda8f5","#c296f5","#9a72d3","#8b64c1",
				"#b59b9a","#b8aaa9","#d4c8b0","#aa9898","#9b8383","#997b7b","#8c6a69",
				"#aaacb9","#bdbfcc","#c5c5c5","#b2b1b6","#9d9d9d","#868686","#9e9898",
				"#647096","#59658b","#586383","#535c7d","#4c567a","#47506f","#434a64"
		};
		
		String[] fallColor = {"#000000",
				"#ffbba6","#e69d8a","#df8e74","#f29e86","#fe9578","#ec8063","#d97154",
				"#5b0c08","#84130d","#9e1d08","#bd2d15","#c82208","#802826","#682d29",
				"#a44809","#d25b0b","#c66e17","#da7919","#ed7e13","#f49335","#f2a964",
				"#674a04","#7f650e","#aa8405","#d8ad13","#ffdd67","#986d40","#864e13",
				"#40560b","#4b6907","#5f8609","#80a62d","#84b14a","#b5c376","#cddb8e",
				"#94b6c2","#9cc6d4","#53a2af","#248fa3","#3799a6","#085d7a","#044257",
				"#826395","#795595","#8155a4","#652a88","#7a3aac","#4e1a6c","#441664",
				"#422807","#5b3b14","#78572c","#bc9f67","#ceb179","#dbc393","#eed8b1",
				"#605454","#6c5d5a","#776767","#8f7d7d","#a09b85","#8a7f7d","#726867",
				"#4f5e7b","#3f5972","#2d4762","#1f3c5a","#1c414a","#122d4a","#0c2742"
		};			
		
		String[] winterColor = {"#000000",
				"#ffdcf2","#fec5e8","#f344b1","#e22798","#c90d64","#6b143e","#ff3a99",
				"#471025","#611b35","#590828","#6f0930","#8c0f37","#9e103e","#a70a3d",
				"#feffe0","#fdffb6","#faff6f","#f5ff02","#e9fe02","#f0ff44","#f1ff62",
				"#09523f","#016048","#17493d","#063f1c","#02522b","#006233","#1b9d4b",
				"#dbe1ff","#6755f5","#4813f5","#2073b5","#483baf","#1b53b0","#0e23a2",
				"#450f4d","#53075f","#6b1078","#9e14b5","#911ea3","#832091","#691a87",
				"#ffffff","#fafafa","#efeeec","#ece9e4","#e5e2dd","#dedad1","#dfd6c5",
				"#424651","#3f3f3f","#565656","#676767","#e7e7e7","#f3f1f2","#7e7e7e",
				"#181077","#110d61","#1b195b","#211f4e","#24234b","#181b37","#000000"
		};	
		
		
		for(MemberTO to : boardList) {
			int memNo = to.getMemNo();
			String id = to.getId();
			String name = to.getName();
			String email = to.getEmail();
			int receiveMailFlag = to.getReceiveMailFlag();
			String signUpDate = to.getSignUpDate();
			String loginDate = to.getLoginDate();
			int likeColor = to.getLikeColor();
			String seasonOfLikeColor = to.getSeasonOfLikeColor();
			int numOfText = to.getNumOfText();
			int numOfcomment = to.getNumOfComment();									
			
			if (receiveMailFlag == 0) {
				rmF = "허용안함";
			} else {
				rmF = "허용";
			}
												
			if (seasonOfLikeColor.equals("spring")) {
				memberColor = springColor[likeColor];
			} else if (seasonOfLikeColor.equals("summer")) {
				memberColor = summerColor[likeColor];
			} else if (seasonOfLikeColor.equals("fall")) {
				memberColor = fallColor[likeColor];
			} else if (seasonOfLikeColor.equals("winter")) {
				memberColor = winterColor[likeColor];
			} else {
				memberColor = "";
			}
			
			buffer.append("				<tr>");
			buffer.append("		        	<td>" + memNo + "</td>");			
			buffer.append("					<td>" + id + "</td>");
			buffer.append("					<td>" + name + "</td>");
			buffer.append("					<td>" + email + "</td>");
			buffer.append("					<td>" + rmF + "</td>");
			buffer.append("					<td>" + signUpDate + "</td>");
			buffer.append("					<td>" + loginDate + "</td>");
			
			buffer.append("					<td>" + memberColor + "</td>");
			
			
			buffer.append("				</tr>");
			

			
			
		}



	%>    
	
	    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>관리자 페이지</title>

		<!-- 아이콘 템플릿 -->
        <link href="css/font-awesome.min.css" rel="stylesheet">

        <!-- Vendor CSS -->
        <link href="vendors/bootgrid/jquery.bootgrid.min.css" rel="stylesheet">
        <link href="vendors/animate-css/animate.min.css" rel="stylesheet">
        <link href="vendors/sweet-alert/sweet-alert.min.css" rel="stylesheet">
        <link href="vendors/material-icons/material-design-iconic-font.min.css" rel="stylesheet">
        <link href="vendors/socicon/socicon.min.css" rel="stylesheet">
		<link href="css/style.css" rel="stylesheet">
            
        <!-- CSS -->
        <link href="css/app.min.1.css" rel="stylesheet">
        <link href="css/app.min.2.css" rel="stylesheet">
        <script type = "text/javascript" src = "smartEditor20/js/HuskyEZCreator.js" charset="utf-8"></script>
        

        
    </head>
    <body>
	<div class="wrapper black"> <!-- max width 1300px; -->
		
			<!-- include header -->
			<jsp:include page="/model2/header.jsp" flush ="false"></jsp:include>
 					
 				
	<!-- 테이블 -->        
        <section id="main">           
            <section id="content">
                <div class="container">                                      
					
                    <div class="card">
                        
                            	
 							<div class="col-md-4" align="left">
								<!-- Logo -->
								<div class="logo">
								<!-- Heading -->
								<h1>  회원관리</h1>														
								</div>
                    </div>
                        
                        	<div class='table-responsive'>
			
								<table id='data-table-selection' class='table table-striped'>
									<thead>
									<tr>
										<th data-column-id='memNo' data-type='numeric' data-identifier="true">회원번호</th>
										<th data-column-id='id'>아이디</th>
										<th data-column-id='name'>닉네임</th>
										<th data-column-id='email'>이메일</th>
										<th data-column-id='receiveMailFlag'>메일수신여부</th>
										<th data-column-id='signUpDate'>가입일</th>
										<th data-column-id='loginDate'>최종접속일</th>
										<th data-column-id='likeColor'>코드</th>														
										<th data-column-id='commands' data-formatter='commands' data-sortable='false'>선호색상</th>
										
									</tr>
									</thead>
									<tbody>
									<%=buffer %>
									</tbody>
								</table>
										<div class="form-group">
											<div class="col-sm-12">
												<div class="checkbox">													
													<span class="pull-right">선택된 회원에게 : 
													<br><a href="#" data-toggle="modal" data-target="#" id="sendMail">메일 보내기</a>
													<br><a href="#" data-toggle="modal" data-target="#" id="kickMembers">강제 탈퇴</a>
													</span>
													
													<div class="clearfix"></div>
												</div>
											</div>
										</div>								
							</div>
                        
                        
                    </div>

                </div>
            </section>
        </section>
        
        
        <!-- 메일보내기 모달 -->
   <div class="modal fade" id="sendEmailToMembers">
	<div class="modal-dialog">
	<div class="inner-page2">
		<div class="password">
			<div class="password-content">
			<div class="tab-content">
				<div class="tab-pane fade in active br-pink" style="padding:10px;">
					<!-- Heading -->
					<h2>메일 보내기</h2>
					<!-- 메일보내기 Form Start -->
					<div>
						선택된 회원에게 메일보내기<br>
					</div>
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<div class="col-sm-12" align="center">
								<textarea name='contents' id='contents' style='width:550px; height:300px;' placeholder="내용을 입력하세요"></textarea>
								<!-- <textarea class="form-control" rows="5" cols="" placeholder="내용을 입력하세요" name="content" id="content" style="padding:0px;"></textarea> -->												
								<!-- <input type="text" id="content" placeholder="내용을 입력하세요"> -->
 							
							</div>
						</div>

						<div class="form-group text-center">
							<div class="col-sm-12">								
								<a id="button_sendEmailToMembers" class="btn btn-black">
									메일보내기</a>
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


<!-- 강제탈퇴 모달 -->
 <div class="modal fade" id="kickMemberModal">
	<div class="modal-dialog">
	<div class="inner-page2">
		<div class="password">
			<div class="password-content">
			<div class="tab-content">
				<div class="tab-pane fade in active br-orange" style="padding:10px;">
					<!-- Heading -->
					<h2>회원 강제 탈퇴</h2>
					<!-- 메일보내기 Form Start -->
					<div id='kickedMembersEmails'>
						다음 회원들을 강제 탈퇴 시킵니다.<br>
					</div>
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<div class="col-sm-12" align="center">
								<textarea name='kickCntents' id = 'kickContents' style='width:550px; height:300px;' placeholder="내용을 입력하세요"></textarea>
								<!-- <textarea class="form-control" rows="5" cols="" placeholder="내용을 입력하세요" name="content" id="content" style="padding:0px;"></textarea> -->												
								<!-- <input type="text" id="content" placeholder="내용을 입력하세요"> -->						
							</div>
						</div>

						<div class="form-group text-center">
							<div class="col-sm-12">								
								<a id="button_sendKickedMembers" class="btn btn-black">
									강제 탈퇴</a>
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
			
<!-- 테이블 -->       


		<!-- include footer -->
			<jsp:include page="/model2/footer.jsp" flush ="false"></jsp:include>
		
		</div>
		
		
		<!-- Scroll to top -->
		<span class="totop"><a href="#"><i class="fa fa-angle-up"></i></a></span>
	 

        <!-- Javascript Libraries -->
<!--         <script src="js/jquery-2.1.1.min.js"></script> -->
        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>

        <script src="vendors/nicescroll/jquery.nicescroll.min.js"></script>
        <script src="js/jquery.bootgrid.min.js"></script>
        <script src="vendors/waves/waves.min.js"></script>
        <script src="vendors/bootstrap-growl/bootstrap-growl.min.js"></script>
        <script src="vendors/sweet-alert/sweet-alert.min.js"></script>
        <script src="js/custom_by_yyj_for_colorpicker_modify.js"></script>
        <script src="js/custom_by_yyj_for_logInOut.js"></script>
        
        <script src="js/demo.js"></script>
        
        
		
		<!-- SmartEditor 2.0 -->

		

        <!-- Data Table -->
        <script type="text/javascript">
            $(document).ready(function(){
                //Basic Example
                $("#data-table-basic").bootgrid({
                    css: {
                        icon: 'md icon',
                        iconColumns: 'md-view-module',
                        iconDown: 'md-expand-more',
                        iconRefresh: 'md-refresh',
                        iconUp: 'md-expand-less'
                    },
                });
    			
                //Selection
                $("#data-table-selection").bootgrid({
                    css: {
                        icon: 'md icon',
                        iconColumns: 'md-view-module',
                        iconDown: 'md-expand-more',
                        iconRefresh: 'md-refresh',
                        iconUp: 'md-expand-less'
                    },                    
                    formatters: {
                        "commands": function(column, row) {
                            return "<table><tr><td style='background : " + row.likeColor +
                            "; width : 50'>" +
                            " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td></tr></table>";
                        }
                    },
                    selection: true,
                    multiSelect: true,
                    rowSelect: true,
                    keepSelection: true
                });
                
                //Command Buttons
                $("#data-table-command").bootgrid({
                    css: {
                        icon: 'md icon',
                        iconColumns: 'md-view-module',
                        iconDown: 'md-expand-more',
                        iconRefresh: 'md-refresh',
                        iconUp: 'md-expand-less'
                    },
                    formatters: {
                        "commands": function(column, row) {
                            return "<button type=\"button\" class=\"btn btn-icon command-edit\" data-row-id=\"" + row.id + "\"><span class=\"md md-edit\"></span></button> " + 
                                "<button type=\"button\" class=\"btn btn-icon command-delete\" data-row-id=\"" + row.id + "\"><span class=\"md md-delete\"></span></button>";
                        }
                    },
                    selection: true,
                    multiSelect: true,
                    rowSelect: true,
                    keepSelection: true
                });
            });
        </script>
    </body>
</html>
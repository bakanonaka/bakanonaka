<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%@page import = "java.util.ArrayList" %>
<%@page import = "model1.EventTO" %>
    

	<%
		ArrayList<EventTO> eventList = (ArrayList<EventTO>)request.getAttribute("eventAllListForManage");
		
		StringBuffer buffer = new StringBuffer();
		String rmF = "";
		
		for(EventTO to : eventList) {
			
			int no = to.getNo();
			String title = to.getTitle();
			String imgName = to.getImgName();
			String brandName = to.getBrandName();
			String dateFrom = to.getDateFrom();
			String dateTo = to.getDateTo();
			String url = to.getUrl();
			int status = to.getStatus();
			
			String status_text = "";
			switch(status) {
				case 0:
					status_text = "PLAY";	
					break;
				case 1:
					status_text = "STOP";	
					break;
				case 2:
					status_text = "FOR";	
					break;
			}
			
			
			buffer.append("				<tr>");
			buffer.append("		        	<td>" + no + "</td>");			
			buffer.append("					<td>" + title + "</td>");
			
			if(imgName == null || imgName.equals("") || imgName.length() < 3) {
				buffer.append("					<td>" + "" + "</td>");	
			} else {
				buffer.append("					<td>" + imgName + "</td>");
			}
			
			
			
			buffer.append("					<td>" + brandName + "</td>");
			buffer.append("					<td>" + dateFrom + "</td>");
			buffer.append("					<td>" + dateTo + "</td>");
			buffer.append("					<td>" + url + "</td>");
			
// 			buffer.append("					<td><i class='fa fa-clock-o fa-3x'></i></td>");	//안됨

			buffer.append("					<td>" + status_text + "</td>");
			
			buffer.append("				</tr>");
			

			
			
		}



	%>    
	
	    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>RedLipstick</title>
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
        

		<link href="css/font-awesome.min" rel="stylesheet">

        
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
									<h1>  이벤트 관리</h1>														
								</div>
               		   	  </div>
                        
                        	<div class='table-responsive'>
			
								<table id='data-table-selection' class='table table-striped'>
									<thead>
									<tr>
										<th data-column-id='no' data-type='numeric' data-identifier="true">이벤트 번호</th>
										<th data-column-id='title'>제목</th>
										<th data-column-id='imgName'>이미지</th>
										<th data-column-id='brandName' data-order='desc'>브랜드명</th>
										<th data-column-id='dateFrom'>시작일</th>
										<th data-column-id='dateTo'>종료일</th>
										<th data-column-id='url'>이벤트URL</th>
										<th data-column-id='status'>상태</th>				
				
									</tr>
									</thead>
									<tbody>
									<%=buffer %>
									</tbody>
								</table>
								
								<div style = "text-align: right">
									<a id = "addEvent" class="btn btn-black">추가</a>&nbsp;&nbsp;
									<a id = "modifyEvent" class="btn btn-black">수정</a>&nbsp;&nbsp;
									<a id = "deleteEvent" class="btn btn-black">삭제</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
		
							</div>

                    </div>

                </div>
            </section>
        </section>
        
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
        <script src="js/jquery.bootgrid2.min.js"></script>
        <script src="vendors/waves/waves.min.js"></script>
        <script src="vendors/bootstrap-growl/bootstrap-growl.min.js"></script>
        <script src="vendors/sweet-alert/sweet-alert.min.js"></script>
<!--         <script src="js/custom_by_yyj_for_colorpicker_modify.js"></script> -->
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
                    }
                });
            });
        </script>
    </body>
</html>
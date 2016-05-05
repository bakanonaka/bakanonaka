<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>RedLipstick</title>
		<meta name="description" content="website">
		<meta name="keywords" content="Lipstick">
		<meta name="author" content="KJY">
		
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		
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
		<link href="css/cupertino/jquery-ui.css" rel="stylesheet">
		
		<!-- Color Picker -->
		<link href="css/bootstrap-colorselector.css" rel="stylesheet">

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
		
		<!-- Color Picker -->
		<script src="js/bootstrap-colorselector.js"></script>
		
		
</head>

<body>

		<div class="wrapper white"> <!-- max width 1300px; -->
		
			<!-- include header -->
			<jsp:include page="/model2/header.jsp" flush ="false"></jsp:include>
			
			
			<!-- include slider -->
			<jsp:include page="/model2/index_slider.jsp" flush ="false"></jsp:include>
			
			
			<!-- include colorPickerMenu -->
			<jsp:include page="/model2/index_colorpickermenu.jsp" flush ="false"></jsp:include>
			
			
			<!-- include box wrapper -->
			<jsp:include page="/model2/index_boxwrapper.jsp" flush ="false"></jsp:include>	
			
				
			<!-- include footer -->
			<jsp:include page="/model2/footer.jsp" flush ="false"></jsp:include>
		
		</div>
		
		
		<!-- Scroll to top -->
		<span class="totop"><a href="#"><i class="fa fa-angle-up"></i></a></span>
		
</body>



</html>
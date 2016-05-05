<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import = "java.util.ArrayList" %>
<%@page import = "model1.ProductTO" %>
<%@page import = "model1.BrandTO" %>
 
<%
	StringBuffer buffer = new StringBuffer();
	ArrayList<BrandTO> brandList = (ArrayList<BrandTO>)request.getAttribute("brandList");

// 	buffer.append("<select name='brandNo'>");
	for(BrandTO to : brandList) {
		int no = to.getNo();
		String name = to.getName();
		
		buffer.append("<option value="+no+"."+name+">"+no+" . "+name+"</option>");
		
	}
// 	buffer.append("</select>");
%>

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

	<!-- SmartEditor 2.0 -->
		<script type = "text/javascript" src = "smartEditor20/js/HuskyEZCreator.js" charset="utf-8"></script>
		
    <script type = "text/javascript">
	
	function ChkForm(form) {
		
		//SmartEditor로 추가된 부분
		oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
// 		alert(document.getElementById("content").value);
		//-------------------------------
		
		if(form.subject.value.trim() == "") {
			alert("제목을 입력하셔야 합니다.");
			return false;
		}
		
		
		if(form.content.value.trim() == "") {
			alert("내용을 입력하셔야 합니다.");
			return false;
		}
		
// 		if(form.reviewImgName.value.trim() == "") {
// 			alert("사진을 추가하셔야 합니다.");
// 			return false;
// 		}
		
	}
	
	$(document).ready(function(){
		
		
		var getProductsOfBrand = function(brandNoName){
			
			$.ajax({
				url:'reviewBoardWritebyBrand.board',
				type: 'POST',
				data: {
					brandNoName : brandNoName
				},
				dataType: 'json',
				success: function(data) {

					var no = 0;
					var name = '';
					var colorName = '';
					
					
					if(data.length > 0) {
						
// 						$('select[name="productNo"]').empty();
						$('select[name="productName"]').empty();
					
						for(var i = 0; i < data.length; i++) {
							
							for(var key in data[i]) {
								
								switch(key) {
// 									case 'no':
// 										no = Number(data[i][key]);
// 										break;
									case 'name':
										name = data[i][key];
										break;
// 									case 'colorName':
// 										colorName = data[i][key];
// 										break;
									default:
										break;
								}
	
							}
							
// 							var buffer2 = "<option value="+no+"."+name+">"+no+" . "+name+"</option>";
							var buffer2 = "<option value='"+name+"'>"+name+"</option>";
							
// 							$('select[name="productNo"]').append(buffer2);
							$('select[name="productName"]').append(buffer2);
							
// 							alert(buffer2);
							
						}
					
// 						alert('성공했습니다.');

						//브랜드가 바뀌었을때 가져온 화장품명으로 칼라 가져옴.
						if($('select[name="productName"]').val() != null) {
							getColorsOfProdut($('select[name="productName"]').val());	
						}
						
// 						if($('select[name="productNo"]').val() != null) {
// 							getColorsOfProdut($('select[name="productNo"]').val());	
// 						}
						
						
					} else {
						alert('제품이 없습니다.');
					}
				},
				error: function(xhr, status, error) {
					alert('에러 : ' + status + '\n\n' + error);
				}
			});
		};
		
		
		var getColorsOfProdut = function(productName){
			
			$.ajax({
				url:'reviewBoardWritebyProduct.board',
				type: 'POST',
				data: {
					productName : productName
				},
				dataType: 'json',
				success: function(data) {

					var no = 0;
					var name = '';
					var colorName = '';
					
					
					if(data.length > 0) {
						
						$('select[name="colorNo"]').empty();
					
						for(var i = 0; i < data.length; i++) {
							
							for(var key in data[i]) {
								
								switch(key) {
									case 'no':
										no = Number(data[i][key]);
										break;
									case 'name':
										name = data[i][key];
										break;
									case 'colorName':
										colorName = data[i][key];
										break;
									default:
										break;
								}
	
							}
							
							var buffer3 = "<option value="+no+"."+colorName+">"+no+" . "+colorName+"</option>";
							
							$('select[name="colorNo"]').append(buffer3);
							
						}
					
// 						alert('성공했습니다.');
					} else {
						alert('제품이 없습니다.');
					}
				},
				error: function(xhr, status, error) {
					alert('에러 : ' + status + '\n\n' + error);
				}
			});
		};
		
		
		
		//현재 브랜드값으로 가져오기 (1.토니모리)
		getProductsOfBrand($('select[name="brandNo"]').val());
		
		
		
		$('select[name="brandNo"]').change(function(){
// 			alert('브랜드 체인지 : ' + $(this).val());
			
			var brand = $(this).val();
			
			getProductsOfBrand(brand);
		});
		
// 		$('select[name="productNo"]').change(function(){
		$('select[name="productName"]').change(function(){
// 			alert('제품 체인지 : ' + $(this).val());

			var product = $(this).val();
			
			getColorsOfProdut(product);
		});	
		
	});
	
	
	
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
								게시판 > 리뷰 게시판
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
		<form action='reviewBoardWrite_ok.board' method='post' name='wfrm' onSubmit='return ChkForm(this)' enctype = "multipart/form-data">
		<%
			String str_loggedMemNo = "";
			if(session.getAttribute("id") != null) {
				str_loggedMemNo = (String)session.getAttribute("memNo");
		%>
		<input type = "hidden" name = "memNo" value = "<%=str_loggedMemNo%>">
		<%	} %>
		
		<table width='200%' border='0' cellpadding='0' cellspacing='0'>
		<tr>
			<td width='80' style='padding:5' valign='top' align='right'>
				<font class='titdot'>&#149;&nbsp;</font>
				<font class='title'>제목</font> :
			</td>
			<td>
				<input type='text' name='subject' size='70px' class='form' style="color: black; width:28%;">
<!-- 				style="color: black" 추가 해줘야 글자가 검은색으로 나옴...ㄷㄷ -->
			</td>
		</tr>
		<tr>
			<td width='50' style='padding:5' valign='top' align='right'>
				<font class='titdot'>&#149;&nbsp;</font>
				<font class='title'>브랜드</font> :
			</td>
			
			<td style="color: black" width="50px">
			<select name='brandNo'>
				<%=buffer %>
			</select>
			</td>
		</tr>
		
		<tr>
			<td width='50' style='padding:5' valign='top' align='right'>
				<font class='titdot'>&#149;&nbsp;</font>
				<font class='title'>제품</font> :
			</td>
			
			<td style="color: black" width="50px">
			<select name='productName'>
				
			</select>
			</td>
		</tr>
		
		<tr>
			<td width='50' style='padding:5' valign='top' align='right'>
				<font class='titdot'>&#149;&nbsp;</font>
				<font class='title'>색상</font> :
			</td>
			
			<td style="color: black" width="50px">
				<select name='colorNo'>
					
				</select>
			</td>
		</tr>
		
		<tr>
			<td width='50' style='padding:5' valign='top' align='right'>
				<font class='titdot'>&#149;&nbsp;</font>
				<font class='title'>별점</font> :
			</td>
			
			<td style="color: black" width="50px">
				<select name='countOfStar'>
					<option value="0">☆☆☆☆☆</option>
					<option value="1">★☆☆☆☆</option>
					<option value="2">★★☆☆☆</option>
					<option value="3">★★★☆☆</option>
					<option value="4">★★★★☆</option>
					<option value="5">★★★★★</option>
				</select>
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
		<tr>
			<td width='80' style='padding:5' valign='top' align='right'>
				<font class='titdot'>&#149;</font>
				<font class='title'>업로드 이미지</font> :
			</td>
			<td>
				<input type='file' name='reviewImgName' size='50' maxlength='45'>
			</td>
		</tr>
		<tr>
			<td colspan='2' class='imgline'></td>
		</tr>
		<tr>
		
			<td width='80' style='padding:5' valign='top' align='right' >
			
				<font class='titdot'>&#149;&nbsp;</font>
				<font class='title'>내용</font> :
			</td>
			<td>
				<textarea name='content' id = 'content' style='width:655px; height:300px; color: black; display:none;' class='form'></textarea>
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
			<td width='770' height='30' align='center'>
				<a><input type="submit" class="btn btn-black" value = "글쓰기"></a>
				<a href='reviewBoard.board' class="btn btn-black"> 목록보기</a>
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
	
$(document).ready(function() {

	//전체 검색 버튼 클릭시
	$('#a_initSortingProducts').click(function(){
		initSortingProducts(1);
		
		scrollWindowToUnderSlider();
	});
	
	
	// 검색 버튼 클릭시	
	$('#a_ColorPickerProductSorting').click(function(){
	    
		if($('#productsorting_colorValue').val() == 0) {
			alert('검은색은 검색 기준이 되지 않습니다.');
		} else if($('#productsorting_colorColor').val() == "" || $('#productsorting_colorSeason').val() == "") {
			alert('색상 선택을 하지 않았습니다.');
		} else {
//			alert('선택한 색상과 계절은 ' + $('#productsorting_colorColor').val() + ' 와 ' + $('#productsorting_colorSeason').val() + ' 입니다.');
			
			var colorNum = $('#productsorting_colorValue').val();
			var seasonName = $('#productsorting_colorSeason').val();
			initSortingProductsWithLikeColor(1, colorNum, seasonName);
			
			scrollWindowToUnderSlider();
		}
		
	})
	
	//초기화-------------
	var cPage = 1;
	var recordPerPage = 8;
	var totalPage = 38;
	var blockPerPage = 5;
	var numberOfStartBlock = 1;
	var numberOfEndBlock = numberOfStartBlock + blockPerPage;
	//-------------
	
	var scrollWindowToUnderSlider = function(){
		
		if($(this).scrollTop() > 450) {
			$("html, body").animate({ scrollTop: 450 }, "slow");	
		}
	}
	
	//페이지 번호 클릭시
	$(document).on('click', 'a.pageNumber', function() {
		
		var pageNumber_text = $(this).attr('id');
		
		if($('#productsorting_colorColor').val() == "" || $('#productsorting_colorSeason').val() == "") {
			initSortingProducts(Number(pageNumber_text));
		} else {
			var colorNum = $('#productsorting_colorValue').val();
			var seasonName = $('#productsorting_colorSeason').val();
			initSortingProductsWithLikeColor(Number(pageNumber_text), colorNum, seasonName);
		}
		
		scrollWindowToUnderSlider();
	}); 
	

	// < 화살표 (이전 블럭)
	$(document).on('click', '#icon_pageLeft', function() {

//		alert('numberOfStartBlock' + numberOfStartBlock + '-blockPerPage' + blockPerPage);
		
		if(numberOfStartBlock != 1) {
			
			if($('#productsorting_colorColor').val() == "" || $('#productsorting_colorSeason').val() == "") {
				initSortingProducts(numberOfStartBlock-blockPerPage);
			} else {
				var colorNum = $('#productsorting_colorValue').val();
				var seasonName = $('#productsorting_colorSeason').val();
				initSortingProductsWithLikeColor(numberOfStartBlock-blockPerPage, colorNum, seasonName);
			}
			
			scrollWindowToUnderSlider();
		}
		
	});
	
	// > 화살표 이동(다음 블럭)
	$(document).on('click', '#icon_pageRight', function() {
		
//		alert('numberOfStartBlock' + numberOfStartBlock + '+blockPerPage' + blockPerPage);
		
		if(numberOfStartBlock + blockPerPage <= totalPage) {
			
			if($('#productsorting_colorColor').val() == "" || $('#productsorting_colorSeason').val() == "") {
				initSortingProducts(numberOfStartBlock + blockPerPage);
			} else {
				var colorNum = $('#productsorting_colorValue').val();
				var seasonName = $('#productsorting_colorSeason').val();
				initSortingProductsWithLikeColor(numberOfStartBlock + blockPerPage, colorNum, seasonName);
			}
			
			scrollWindowToUnderSlider();
		}
	});
	
	
	//페이징 ajax로 페이지 관련 숫자를 넘겨 받을 때, Number(data[i][key])를 꼭 해줘야 한다. 
	var changePagingNumberAndIcon = function(numberOfPage) {
		
		cPage = numberOfPage;
		
		$('#pageNumberList').empty();
		var pageNumberList = "";
		
		for(var i = numberOfStartBlock; i < numberOfEndBlock;i++) {
			
			if(numberOfPage == i) {
				pageNumberList += '&nbsp;<a id = "'+i+'" style = "color:yellow;">'+ i +'</a>&nbsp;';
			} else if(i > totalPage) {	
				//전체 페이지 수까지 보여주고, 블럭이 전체 페이지 수보다 클 때 제외
				break;
			} else {
				pageNumberList += '<a class = "pageNumber btn" id = "'+i+'">'+i+'</a>&nbsp;';
			}
		}
		
		$('#pageNumberList').prepend('<i class="fa fa-chevron-left btn btn-black" id = "icon_pageLeft"></i>&nbsp;');	//좌 블럭
		$('#pageNumberList').append(pageNumberList);	// 페이징
		$('#pageNumberList').append('&nbsp;<i class="fa fa-chevron-right btn btn-black" id = "icon_pageRight"></i>');	//우 블럭
		
		// < > 화살표 색깔 변화
		$('#icon_pageLeft').removeClass('btn-black');
		$('#icon_pageLeft').removeClass('btn-black-nonehover');
		
		if(cPage - blockPerPage < 1) {	
			$('#icon_pageLeft').addClass('btn-black-nonehover');
		} else {
			$('#icon_pageLeft').addClass('btn-black');
		}
		
		
		$('#icon_pageRight').removeClass('btn-black');
		$('#icon_pageRight').removeClass('btn-black-nonehover');
		
		if(numberOfStartBlock + blockPerPage >= totalPage) {
			$('#icon_pageRight').addClass('btn-black-nonehover');
		} else {
			$('#icon_pageRight').addClass('btn-black');	
		}
		
	};//changePagingNumberAndIcon() 끝
	
	
	// 화장품 요청 메소드(json 방식)
	var initSortingProducts = function(index) {
		
		//색상 선택 값 초기화
		$('#productsorting_colorValue').val(0);
		$('#productsorting_colorColor').val('');
		$('#productsorting_colorSeason').val('');
		$('#productsorting_colorTitle').val('');
		
		
		$.ajax({
			url:'initProductList.board',
			type: 'POST',
			data: {
				cpage:index
			},
			dataType: 'json',
			success: function(data) {

//				console.log(data);
				
				var flag = 1;
				var no = 0;						//제품 구분자
				var name = '';						//화장품 명
				var price = 0;						//가격
				var countOfstar = 0;				//별점 수
				var colorName = '';					//화장품 색상명
				var spring = 0;					//봄계열 숫자
				var summer = 0;					//여름계열 숫자
				var fall = 0;						//가을계열 숫자
				var winter = 0;					//겨울계열 숫자
				var basicProductImgName = '';		//1. 화장품 기본 이미지
				var colorProductImgName = '';		//2. 화장품 색상별 이미지 (1이 없으면, 보여줌)
				var colorImgName = '';				//3. 색상 이미지 (2가 없으면, 1과 같이 보여줌)
				var testImgName = '';				//4. 발색 이미지 (상세 보기에서 보여줌)
				var brandName = '';					//브랜드명
				var productKindName = '';			//화장품 종류 (립스틱)
				
				
				for(var i = 0; i < data.length; i++) {
					
					if(i != 0 && flag == 1) {
						//첫번째 data의 flag 값이 1이면, 실패.
						break;
					}
					
					for(var key in data[i]) {
						
						switch(key) {
							case 'flag':
								flag = Number(data[i][key]);
								break;
							case 'currentPage':
								currentPage = Number(data[i][key]);
								break;
							case 'recordPerPage':
								recordPerPage = Number(data[i][key]);
								break;
							case 'totalPage':
								totalPage = Number(data[i][key]);
								break;
							case 'blockPerPage':
								blockPerPage = Number(data[i][key]);
								break;
							case 'numberOfStartBlock':
								numberOfStartBlock = Number(data[i][key]);
								break;
							case 'numberOfEndBlock':
								numberOfEndBlock = Number(data[i][key]);
								break;
							case 'no':
								no = Number(data[i][key]);
								break;
							case 'name':
								name = data[i][key];
								break;
							case 'price':
								price = Number(data[i][key]);
								break;
							case 'countOfstar':
								countOfstar = Number(data[i][key]);
								break;
							case 'colorName':
								colorName = data[i][key];
								break;
							case 'spring':
								spring = Number(data[i][key]);
								break;
							case 'summer':
								summer = Number(data[i][key]);
								break;
							case 'fall':
								fall = Number(data[i][key]);
								break;
							case 'winter':
								winter = Number(data[i][key]);
								break;
							case 'basicProductImgName':
								basicProductImgName = data[i][key];
								break;
							case 'colorProductImgName':
								colorProductImgName = data[i][key];
								break;
							case 'colorImgName':
								colorImgName = data[i][key];
								break;
							case 'testImgName':
								testImgName = data[i][key];
								break;
							case 'brandName':
								brandName = data[i][key];
								break;
							case 'productKindName':
								productKindName = data[i][key];
								break;
								
							default:
								break;
						}
						
					}
					
					
					if(i == 0 && flag == 0) {	
						
						//화장품 박스 목록을 없앤다.
						$('#productBoxList').empty();
						
						//첫번째 data에는 flag 와 page 정보있음
						continue;
					} else if(flag == 2) {
						//화장품 없음
						break;
					}
					
//					alert(no+' '+brandName+' '+name+' '+colorName+' '+basicProductImgName+' '+colorProductImgName);
					
					var productBox = "";
					
					var brandImgPath = "./productImg/";
					
					switch(brandName) {
						case '더페이스샵':
							brandImgPath += brandName +"/logo/더페이스샵_small_150.png";
							break;
						case '미샤':
							brandImgPath += brandName +"/logo/미샤_small_150.png";
							break;
						case '스킨푸드':
							brandImgPath += brandName +"/logo/스킨푸드_small.png";
							break;
						case '에뛰드하우스':
							brandImgPath += brandName +"/logo/에뛰드하우스_small_150.png";
							break;
						case '이니스프리':
							brandImgPath += brandName +"/logo/이니스프리_small_150.png";
							break;
						case '토니모리':
							brandImgPath += brandName +"/logo/토니모리_small_150.png";
							break;
					}
					
					productBox = "<div class='col-md-3 col-sm-6'>";
					productBox += "	<div class='box box-lg animated'>";	//ajax complete될때, 클래스 삽입. 완전히 해결 못함.
					productBox += "		<div class='box-content slide-up box-gallery padd-zero br-white'>";
					
					productBox += "			<div style = 'top: 0%; padding-top: 5px; padding-bottom: 5px; vertical-align: middle; position: relative;'><img src='"+brandImgPath+"' style = 'max-height:30px; padding:5px;' align='middle' alt=''/></div>";
					productBox += "			<div style = 'height:100%; bottom: 25%; position:relative; text-align: center;'>";
					productBox += "			<div style = 'height: 22%; top:95%; position:relative; color: black; border-top:solid 1px #e63131; vertical-align: middle; padding-top:5px;'><h4>"+name+"</h4><h5> 색상명 : "+colorName+"</h5></div>";
					
					if(colorProductImgName.length < 3) {// 색상별 화장품이 없으면 기본 이미지로
						productBox += "				<img src='./productImg/"+ brandName +"/"+name+"/"+basicProductImgName+"' class='img-responsive box-gallery-img-basicProduct' alt=''/>";
						productBox += "				<img src='./productImg/"+ brandName +"/"+name+"/"+colorImgName+"' class='img-responsive box-gallery-img-colorImageName' alt=''/>";
					} else {
						productBox += "				<img src='./productImg/"+ brandName +"/"+name+"/"+colorProductImgName+"' class='img-responsive box-gallery-img' alt=''/>";
					}
					
					productBox += "			</div>";
					productBox += "			<div class='image-gallery-caption movetoup box-hover-black2' style='text-align: center'>";
//					productBox += "				<h4>추천 별점</h4>";
					
					productBox += "				<h4 style = 'color:yellow'>"; 					
					var starIcon = "";
					for(var j = 0; j < 5; j++){
						
						
						if(countOfstar >=1) {
							starIcon += "★";
						} else {
							starIcon += "☆";
						}
						countOfstar--;
					}
					productBox += starIcon;
					productBox += "</h4>";

					productBox += "				<button class='btn btn-primary btn-lg btn-ar' id = 'productModal"+no+"'>상세보기</button>";
					
					productBox += "			</div>";
					productBox += "		</div>";
					productBox += "	</div>";
					productBox += "</div>";
					
//					alert(productBox);
					$('#productBoxList').append(productBox);
					
//					console.log(productBox);
				}
				
				if(flag == 0) {
					
					//custom.js 에서 가져온다. 페이지를 열 때, js가 실행되므로, 
					//데이터를 가져오면 여기서 한번 더 실행해줘야 한다.
					
					$('.box').waypoint(function(down){
						$(this).addClass('animation');
						$(this).addClass('fadeInUp');
					}, { offset: '100%' });
					
					$(".slide-up").hover(function(){
						$(this).children(".movetoup").slideDown(500); 
						}, function(){
						$(this).children(".movetoup").slideUp(500);
					});
						
					$(".slide-down").hover(function(){
						$(this).children(".movetodown").slideDown(500); 
						}, function(){
						$(this).children(".movetodown").slideUp(500);
					});
					
					changePagingNumberAndIcon(currentPage);

					//클릭했을때, 글의 no를 가져오는 구문
					$('#productBoxList > div').find('button').each(function(){
						
//						alert($(this).attr('id'));
						
						$(this).click(function(){
							var no_text = $(this).attr('id'); 
							var no = no_text.replace('productModal','');	
							
							getProductInfo(no);
						});
						
					});
					
//					alert('화장품 불러오기를 성공했습니다.');
					
				} else if(flag == 1){
					alert('화장품 불러오기를 실패했습니다.');
				} else if(flag == 2) {
					
					changePagingNumberAndIcon(currentPage);
					
					alert('화장품 정보가 없습니다.');
				}
			},
			error: function(xhr, status, error) {
				alert('에러 : ' + status + '\n\n' + error);
			},
			complete:function(xhr, status){//error, success 상관 없이 실행 되는 부분
			}
		});
	};//initSortingProducts() 끝
	
	
	
	
	initSortingProducts(1);	//페이지를 띄울 때, 첫번째 페이지를 보여준다.
	
	
	
	
	
	
	// 화장품 요청 메소드(json 방식)
	var initSortingProductsWithLikeColor = function(index, colorNum, seasonName) {
		
		$.ajax({
			url:'initProductListWithColor.board',
			type: 'POST',
			data: {
				cpage:index,
				colorNum:colorNum,
				seasonName:seasonName
			},
			dataType: 'json',
			success: function(data) {

//				console.log(data);
				
				var flag = 1;
				var no = 0;						//제품 구분자
				var name = '';						//화장품 명
				var price = 0;						//가격
				var countOfstar = 0;				//별점 수
				var colorName = '';					//화장품 색상명
				var spring = 0;					//봄계열 숫자
				var summer = 0;					//여름계열 숫자
				var fall = 0;						//가을계열 숫자
				var winter = 0;					//겨울계열 숫자
				var basicProductImgName = '';		//1. 화장품 기본 이미지
				var colorProductImgName = '';		//2. 화장품 색상별 이미지 (1이 없으면, 보여줌)
				var colorImgName = '';				//3. 색상 이미지 (2가 없으면, 1과 같이 보여줌)
				var testImgName = '';				//4. 발색 이미지 (상세 보기에서 보여줌)
				var brandName = '';					//브랜드명
				var productKindName = '';			//화장품 종류 (립스틱)
				
				for(var i = 0; i < data.length; i++) {
					
					if(i != 0 && flag == 1) {
						//첫번째 data의 flag 값이 1이면, 실패.
						break;
					} 
					
					for(var key in data[i]) {
						
						switch(key) {
							case 'flag':
								flag = Number(data[i][key]);
								break;
							case 'currentPage':
								currentPage = Number(data[i][key]);
								break;
							case 'recordPerPage':
								recordPerPage = Number(data[i][key]);
								break;
							case 'totalPage':
								totalPage = Number(data[i][key]);
								break;
							case 'blockPerPage':
								blockPerPage = Number(data[i][key]);
								break;
							case 'numberOfStartBlock':
								numberOfStartBlock = Number(data[i][key]);
								break;
							case 'numberOfEndBlock':
								numberOfEndBlock = Number(data[i][key]);
								break;
							case 'no':
								no = Number(data[i][key]);
								break;
							case 'name':
								name = data[i][key];
								break;
							case 'price':
								price = Number(data[i][key]);
								break;
							case 'countOfstar':
								countOfstar = Number(data[i][key]);
								break;
							case 'colorName':
								colorName = data[i][key];
								break;
							case 'spring':
								spring = Number(data[i][key]);
								break;
							case 'summer':
								summer = Number(data[i][key]);
								break;
							case 'fall':
								fall = Number(data[i][key]);
								break;
							case 'winter':
								winter = Number(data[i][key]);
								break;
							case 'basicProductImgName':
								basicProductImgName = data[i][key];
								break;
							case 'colorProductImgName':
								colorProductImgName = data[i][key];
								break;
							case 'colorImgName':
								colorImgName = data[i][key];
								break;
							case 'testImgName':
								testImgName = data[i][key];
								break;
							case 'brandName':
								brandName = data[i][key];
								break;
							case 'productKindName':
								productKindName = data[i][key];
								break;
								
							default:
								break;
						}	
					}
					
					if(i == 0 && flag == 0) {	
						
						//화장품 박스 목록을 없앤다.
						$('#productBoxList').empty();
						
						//첫번째 data에는 flag 와 page 정보있음
						continue;
						
					} else if(flag == 2) {
						//화장품 없음
						break;
					}
					
//					alert(no+' '+brandName+' '+name+' '+colorName+' '+basicProductImgName+' '+colorProductImgName);
					
					var productBox = "";
					
					var brandImgPath = "./productImg/";
					
					switch(brandName) {
						case '더페이스샵':
							brandImgPath += brandName +"/logo/더페이스샵_small_150.png";
							break;
						case '미샤':
							brandImgPath += brandName +"/logo/미샤_small_150.png";
							break;
						case '스킨푸드':
							brandImgPath += brandName +"/logo/스킨푸드_small.png";
							break;
						case '에뛰드하우스':
							brandImgPath += brandName +"/logo/에뛰드하우스_small_150.png";
							break;
						case '이니스프리':
							brandImgPath += brandName +"/logo/이니스프리_small_150.png";
							break;
						case '토니모리':
							brandImgPath += brandName +"/logo/토니모리_small_150.png";
							break;
					}
					
					productBox = "<div class='col-md-3 col-sm-6'>";
					productBox += "	<div class='box box-lg animated'>";	//ajax complete될때, 클래스 삽입. 완전히 해결 못함.
					productBox += "		<div class='box-content slide-up box-gallery padd-zero br-white'>";
					
					productBox += "			<div style = 'top: 0%; padding-top: 5px; padding-bottom: 5px; vertical-align: middle; position: relative;'><img src='"+brandImgPath+"' style = 'max-height:30px; padding:5px;' align='middle' alt=''/></div>";
					productBox += "			<div style = 'height:100%; bottom: 25%; position:relative; text-align: center;'>";
					productBox += "			<div style = 'height: 22%; top:95%; position:relative; color: black; border-top:solid 1px #e63131; vertical-align: middle; padding-top:5px;'><h4>"+name+"</h4><h5> 색상명 : "+colorName+"</h5></div>";
					
					if(colorProductImgName.length < 3) {// 색상별 화장품이 없으면 기본 이미지로
						productBox += "				<img src='./productImg/"+ brandName +"/"+name+"/"+basicProductImgName+"' class='img-responsive box-gallery-img-basicProduct' alt=''/>";
						productBox += "				<img src='./productImg/"+ brandName +"/"+name+"/"+colorImgName+"' class='img-responsive box-gallery-img-colorImageName' alt=''/>";
					} else {
						productBox += "				<img src='./productImg/"+ brandName +"/"+name+"/"+colorProductImgName+"' class='img-responsive box-gallery-img' alt=''/>";
					}
					
					productBox += "			</div>";
					productBox += "			<div class='image-gallery-caption movetoup box-hover-black2' style='text-align: center'>";
//					productBox += "				<h4>추천 별점</h4>";
					
					productBox += "				<h4 style = 'color:yellow'>"; 					
					var starIcon = "";
					for(var j = 0; j < 5; j++){
						
						
						if(countOfstar >=1) {
							starIcon += "★";
						} else {
							starIcon += "☆";
						}
						countOfstar--;
					}
					productBox += starIcon;
					productBox += "</h4>";

					productBox += "				<button class='btn btn-primary btn-lg btn-ar' id = 'productModal"+no+"'>상세보기</button>";
					
					productBox += "			</div>";
					productBox += "		</div>";
					productBox += "	</div>";
					productBox += "</div>";
					
//					alert(productBox);
					$('#productBoxList').append(productBox);

				}
				
				if(flag == 0) {
					
					//custom.js 에서 가져온다. 페이지를 열 때, js가 실행되므로, 
					//데이터를 가져오면 여기서 한번 더 실행해줘야 한다.
					
					$('.box').waypoint(function(down){
						$(this).addClass('animation');
						$(this).addClass('fadeInUp');
					}, { offset: '100%' });
					
					$(".slide-up").hover(function(){
						$(this).children(".movetoup").slideDown(500); 
						}, function(){
						$(this).children(".movetoup").slideUp(500);
					});
						
					$(".slide-down").hover(function(){
						$(this).children(".movetodown").slideDown(500); 
						}, function(){
						$(this).children(".movetodown").slideUp(500);
					});
					
					changePagingNumberAndIcon(currentPage);

					
					
					//클릭했을때, 글의 no를 가져오는 구문
					$('#productBoxList > div').find('button').each(function(){
						
						$(this).click(function(){
							var no_text = $(this).attr('id'); 
							var no = no_text.replace('productModal','');	
							
							getProductInfo(no);
						});
						
					});
					
//					alert('화장품 불러오기를 성공했습니다.');
					
				} else if(flag == 1){
					alert('화장품 불러오기를 실패했습니다.');
				} else if(flag == 2) {
					
					changePagingNumberAndIcon(currentPage);
					
					alert('화장품 정보가 없습니다.');
				}
			},
			error: function(xhr, status, error) {
				alert('에러 : ' + status + '\n\n' + error);
			},
			complete:function(xhr, status){//error, success 상관 없이 실행 되는 부분
			}
		});
	};//initSortingProductsWithLikeColor() 끝
	
var getProductInfo = function(no) {
		
//		alert('product 번호 : ' + no);
		
		$.ajax({
			url:'productViewInfo.board',
			type: 'POST',
			data: {
				no:no
			},
			dataType: 'json',
			success: function(data) {

//				console.log(data);

				var flag = 1;
				var no = 0;						//제품 구분자
				var name = '';						//화장품 명
				var price = 0;						//가격
				var countOfstar = 0;				//별점 수
				var colorName = '';					//화장품 색상명
				var spring = 0;					//봄계열 숫자
				var summer = 0;					//여름계열 숫자
				var fall = 0;						//가을계열 숫자
				var winter = 0;					//겨울계열 숫자
				var basicProductImgName = '';		//1. 화장품 기본 이미지
				var colorProductImgName = '';		//2. 화장품 색상별 이미지 (1이 없으면, 보여줌)
				var colorImgName = '';				//3. 색상 이미지 (2가 없으면, 1과 같이 보여줌)
				var testImgName = '';				//4. 발색 이미지 (상세 보기에서 보여줌)
				var brandName = '';					//브랜드명
				var productKindName = '';			//화장품 종류 (립스틱)
				
				
				//배열 필요
				var otherProduct0 = {};
				var otherProduct1 = {};
				var otherProduct2 = {};

				//배열 필요
				var reviewOfProduct0 = {};
				var reviewOfProduct1 = {};
				var reviewOfProduct2 = {};
				
				for(var i = 0; i < data.length; i++) {
					
					if(i != 0 && flag == 1) {
						//첫번째 data의 flag 값이 1이면, 실패.
						break;
					}
					
					for(var key in data[i]) {
						
						switch(key) {
							case 'flag':
								flag = Number(data[i][key]);
								break;
							case 'no':
								no = Number(data[i][key]);
								break;
							case 'name':
								name = data[i][key];
								break;
							case 'price':
								price = Number(data[i][key]);
								break;
							case 'countOfstar':
								countOfstar = Number(data[i][key]);
								break;
							case 'colorName':
								colorName = data[i][key];
								break;
							case 'spring':
								spring = Number(data[i][key]);
								break;
							case 'summer':
								summer = Number(data[i][key]);
								break;
							case 'fall':
								fall = Number(data[i][key]);
								break;
							case 'winter':
								winter = Number(data[i][key]);
								break;
							case 'basicProductImgName':
								basicProductImgName = data[i][key];
								break;
							case 'colorProductImgName':
								colorProductImgName = data[i][key];
								break;
							case 'colorImgName':
								colorImgName = data[i][key];
								break;
							case 'testImgName':
								testImgName = data[i][key];
								break;
							case 'brandName':
								brandName = data[i][key];
								break;
							case 'productKindName':
								productKindName = data[i][key];
								break;
							case 'otherProduct0':
								getDataOfOtherProduct(data[i][key], otherProduct0);
								break;
							case 'otherProduct1':
								getDataOfOtherProduct(data[i][key], otherProduct1);
								break;
							case 'otherProduct2':
								getDataOfOtherProduct(data[i][key], otherProduct2);
								break;
							case 'reviewOfProduct0':
								getDataOfReview(data[i][key], reviewOfProduct0);
								break;
							case 'reviewOfProduct1':
								getDataOfReview(data[i][key], reviewOfProduct1);
								break;
							case 'reviewOfProduct2':
								getDataOfReview(data[i][key], reviewOfProduct2);
								break;	
							default:
								break;
						}	

					}
					

					if(i == 0 && flag == 0) {	
						
						//게시글 지우기
						$('#productModalList').empty();
						
						//첫번째 data에는 flag 와 page 정보있음
						continue;
					} else if(flag == 2) {
						
						//게시글 없음
						break;
					} else if(i != data.length-1) {
						
						//데이터를 끝까지 모두 읽어내지 않은 경우 continue;
						continue;
					}
					
					var productModal = "";
					productModal += '<div class="modal fade" role="dialog"';
					productModal += '	aria-labelledby="myModalLabel" aria-hidden="true">';
					productModal += '	<div class="modal-dialog">';
					productModal += '		<div class="modal-content">';
					productModal += '			<div class="modal-header">';
					productModal += '				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>';
					productModal += '				<h4 class="modal-title" id="myModalLabel" style = "color:black;">['+brandName+'] '+name+'</h4>';
					productModal += '			</div>';
					
					productModal += '			<div class="modal-body">';
					productModal += '				<table class="tg" style="color:black; text-align: center; background-color: #ffffff; margin: 10px auto; table-layout: fixed; word-break:break-all;">';
					productModal += ' 					 <tr>';
					
					if(basicProductImgName.length > 2) {
						productModal += "   						 <th class='tg-031e' colspan='2' rowspan='2'><img src='./productImg/"+ brandName +"/"+name+"/"+basicProductImgName+"' class='img-responsive box-gallery-img-basicProduct' style = 'margin:0 auto; max-width:150px; max-height:150px;'alt=''/></th>";
					}
					if(colorProductImgName.length > 2) {
						productModal += "  						  <th class='tg-031e' colspan='2' rowspan='2'><img src='./productImg/"+ brandName +"/"+name+"/"+colorProductImgName+"' class='img-responsive box-gallery-img'  style = 'margin:0 auto; max-width:150px; max-height:150px;' alt=''/></th>";
					}
					
					var starIcon = "";
					for(var j = 0; j < 5; j++){
						
						
						if(countOfstar >=1) {
							starIcon += "★";
						} else {
							starIcon += "☆";
						}
						countOfstar--;
					}
					
					productModal += '  		 					 <th class="tg-yw4l" style = "text-align:center; border-left: 3px groove #000000; border-top: 3px groove #000000; border-right: 3px groove #000000; ">추천 별점<br>' + starIcon + '</th>';
					productModal += ' 					 </tr>';
					
					productModal += ' 					 <tr >';


					
					productModal += '   <td class="tg-yw4l" style = "text-align:center; border-left: 3px groove #000000; border-right: 3px groove #000000; ">';
					productModal += '</td>';
					productModal += ' 					</tr>';

					productModal += ' 					<tr>';
					productModal += "  					 <td class='tg-9vto' colspan='2' rowspan='2'>";
					
					if(colorImgName.length > 2) {
						
						productModal += "  					<img src='./productImg/"+ brandName +"/"+name+"/"+colorImgName+"' class='img-responsive box-gallery-img-colorImageName' style = 'margin:0 auto; max-width:250px;' alt=''/>";
					}
					
					if(testImgName.length > 2) {
						productModal += "  					 <img src='./productImg/"+ brandName +"/"+name+"/"+testImgName+"' class='img-responsive box-gallery-img'  style = 'margin:0 auto; max-width:250px;' alt=''/>";
					}
					
					productModal += ' 					</td>';
					
//					productModal += ' 						  <td class="tg-yw4l">'+'<p>'+name+'</p>'+'<p>'+colorName+'</p>'+'</td>';
					productModal += ' 						  <td class="tg-yw4l" style = "text-align:center; border-left: 3px groove #000000; border-right: 3px groove #000000; ">'+'<p>'+colorName+'</p>'+'</td>';
					productModal += ' 					</tr>';
					
					productModal += ' 					<tr>';
					productModal += '  						 <td class="tg-yw4l" style = "text-align:center; border-left: 3px groove #000000; border-bottom: 3px groove #000000; border-right: 3px groove #000000; ">'+'가격 : ' + changeNumberT(price) + '원'+'</td>';
					productModal += '					 </tr>';

					if(reviewOfProduct0.subject == null && reviewOfProduct1.subject == null && reviewOfProduct2.subject == null) {
						//후기 없는 경우
					} else {
					
						if(reviewOfProduct0.subject != null) {
							//후기 1
							productModal += ' 					<tr>';
							productModal += ' 						  <td class="tg-yw4l" colspan="3"><h4>';
							
							var starIcon = "";
							for(var j = 0; j < 5; j++){
								
								
								if(reviewOfProduct0.countOfStar >=1) {
									starIcon += "★";
								} else {
									starIcon += "☆";
								}
								reviewOfProduct0.countOfStar--;
							}
							
							productModal += starIcon + ' &nbsp;';
							
							if(reviewOfProduct0.subject.length > 10) { //제목 길이가 15자 이상일경우, 15자 자르고 뒤에 ... 붙이기
								productModal += '['+reviewOfProduct0.subject.substring(0, 10)+ ']';
				    		}else {
				    			productModal += '['+reviewOfProduct0.subject+ ']';
				    		}
							
							productModal += '</h4></td>';
							productModal += '					 </tr>';
						}
						
						if(reviewOfProduct1.subject != null) {
							//후기 2
							productModal += ' 					<tr>';					
							productModal += ' 						  <td class="tg-yw4l" colspan="3"><h4>';
							var starIcon = "";
							for(var j = 0; j < 5; j++){
								
								
								if(reviewOfProduct1.countOfStar >=1) {
									starIcon += "★";
								} else {
									starIcon += "☆";
								}
								reviewOfProduct1.countOfStar--;
							}
							
							productModal += starIcon + ' &nbsp;';
							
							if(reviewOfProduct1.subject.length > 10) { //제목 길이가 15자 이상일경우, 15자 자르고 뒤에 ... 붙이기
								productModal += '['+reviewOfProduct1.subject.substring(0, 10)+ ']';
				    		}else {
				    			productModal += '['+reviewOfProduct1.subject+ ']';
				    		}
							
							productModal += '</h4></td>';
							productModal += '					 </tr>';
						}
						
						
						
						if(reviewOfProduct2.subject != null) {
							//후기 3
							productModal += ' 					<tr>';
							productModal += ' 						  <td class="tg-yw4l" colspan="3"><h4>';
							var starIcon = "";
							for(var j = 0; j < 5; j++){
								
								
								if(reviewOfProduct2.countOfStar >=1) {
									starIcon += "★";
								} else {
									starIcon += "☆";
								}
								reviewOfProduct2.countOfStar--;
							}
							
							productModal += starIcon + ' &nbsp;';
							
							if(reviewOfProduct2.subject.length > 10) { //제목 길이가 15자 이상일경우, 15자 자르고 뒤에 ... 붙이기
								productModal += '['+reviewOfProduct2.subject.substring(0, 10)+ ']';
				    		}else {
				    			productModal += '['+reviewOfProduct2.subject+ ']';
				    		}
							
							productModal += '</h4></td>';
							
							productModal += '					 </tr>';
						}
						
						
//						productModal += '				</table>';
//						productModal += '			</div>';
						
//						productModal += '			<div class="modal-footer" >';
//						productModal += '				<table class="tg" border="1" style="border: 1px; border-color: #e1e0de; text-align: center; background-color: #404040; margin: 10px; table-layout: fixed; word-break:break-all;">';
					}
					
					if( ((otherProduct0.basicProductImgName != null && otherProduct0.basicProductImgName.length > 2) || (otherProduct0.colorProductImgName != null && otherProduct0.colorProductImgName.length > 2)) 
							&& ((otherProduct1.basicProductImgName != null && otherProduct1.basicProductImgName.length > 2) || (otherProduct1.colorProductImgName != null && otherProduct1.colorProductImgName.length > 2)) 
							&& ((otherProduct2.basicProductImgName != null && otherProduct2.basicProductImgName.length > 2) || (otherProduct2.colorProductImgName != null && otherProduct2.colorProductImgName.length > 2))){
						productModal += ' 					 <tr style = "height:30px;">';
						productModal += ' 						   <td class="tg-031e" colspan="3" style = "border-bottom:solid 1px #e63131;"><br/>다른 상품</td>';
						productModal += ' 					 </tr>';	
					}
					
					productModal += ' 					<tr>';
					
					if(otherProduct0.basicProductImgName != null && otherProduct0.basicProductImgName.length > 2) {
						productModal += '   					 <td class="tg-yw4l"><img src="./productImg/'+ otherProduct0.brandName +'/'+otherProduct0.name+'/'+otherProduct0.basicProductImgName+'" style = "max-height:200px;"><br/><a class = "btn btn-default" id = "otherProduct'+otherProduct0.no+'" >보기</a></td>';
					}
					if(otherProduct0.colorProductImgName != null && otherProduct0.colorProductImgName.length > 2) {
						productModal += '   					 <td class="tg-yw4l"><img src="./productImg/'+ otherProduct0.brandName +'/'+otherProduct0.name+'/'+otherProduct0.colorProductImgName+'" style = "max-height:200px;"><br/><a class = "btn btn-default" id = "otherProduct'+otherProduct0.no+'">보기</a></td>';
					}
					
					if(otherProduct1.basicProductImgName != null && otherProduct1.basicProductImgName.length > 2) {
						productModal += '   					 <td class="tg-yw4l"><img src="./productImg/'+ otherProduct1.brandName +'/'+otherProduct1.name+'/'+otherProduct1.basicProductImgName+'" style = "max-height:200px;"><br/><a class = "btn btn-default" id = "otherProduct'+otherProduct1.no+'" >보기</a></td>';
					}
					if(otherProduct1.colorProductImgName != null && otherProduct1.colorProductImgName.length > 2) {
						productModal += '   					 <td class="tg-yw4l"><img src="./productImg/'+ otherProduct1.brandName +'/'+otherProduct1.name+'/'+otherProduct1.colorProductImgName+'" style = "max-height:200px;"><br/><a class = "btn btn-default" id = "otherProduct'+otherProduct1.no+'" >보기</a></td>';
					}
					
					if(otherProduct2.basicProductImgName != null && otherProduct2.basicProductImgName.length > 2) {
						productModal += '   					 <td class="tg-yw4l"><img src="./productImg/'+ otherProduct2.brandName +'/'+otherProduct2.name+'/'+otherProduct2.basicProductImgName+'" style = "max-height:200px;"><br/><a class = "btn btn-default" id = "otherProduct'+otherProduct2.no+'" >보기</a></td>';
					}
					if(otherProduct2.colorProductImgName != null && otherProduct2.colorProductImgName.length > 2) {
						productModal += '   					 <td class="tg-yw4l"><img src="./productImg/'+ otherProduct2.brandName +'/'+otherProduct2.name+'/'+otherProduct2.colorProductImgName+'" style = "max-height:200px;"><br/><a class = "btn btn-default" id = "otherProduct'+otherProduct2.no+'" >보기</a></td>';
					}
//					productModal += '   					 <td class="tg-yw4l"><img src="./productImg/미샤/글램 아트 루즈/cr02_1.PNG" width="33%"></td>';
//					productModal += '   					 <td class="tg-yw4l"><img src="./productImg/미샤/글램 아트 루즈/cr02_1.PNG" width="33%"></td>';
//					productModal += '  						  <td class="tg-yw4l"><img src="./productImg/미샤/글램 아트 루즈/cr02_1.PNG" width="33%"></td>';
					productModal += ' 					</tr>';
					
					productModal += '				</table>';
					productModal += '			</div>';
					productModal += '		</div>';
					productModal += '	</div>';
					productModal += '</div>';
					
					$('#productModalList').append(productModal);
					
				}

				if(flag == 0) {
					
//					alert('Review 게시글 불러오기를 성공했습니다.');
					
					$('#productModalList > div').modal('show');
					
					//다른 색 화장품 '보기' 버튼
					$('#productModalList').find('a').each(function(){
					
						$(this).click(function(){						
							var no_text = $(this).attr('id'); 	
							var no = no_text.replace('otherProduct','');
							
//							alert('no : ' + no);							
					
							$('#productModalList > div').modal('hide');
							getProductInfo(no);
						});
						
					});
					
					
				} else if(flag == 1){
					alert('Review 게시글 불러오기를 실패했습니다.');
				} else if(flag == 2) {
					alert('게시글이 없습니다.');
				}
			},
			error: function(xhr, status, error) {
				alert('에러 : ' + status + '\n\n' + error);
			},
			complete:function(xhr, status){//error, success 상관 없이 실행 되는 부분
			}
		});
		
	};
	
	
	var getDataOfOtherProduct = function(data, product) {
		
		for(var keyOfkey in data) {
			
			switch(keyOfkey) {
			case 'no':
				product.no = Number(data[keyOfkey]);
				break;
			case 'name':
				product.name = data[keyOfkey];
				break;
			case 'price':
				product.price = Number(data[keyOfkey]);
				break;
			case 'countOfstar':
				product.countOfstar = Number(data[keyOfkey]);
				break;
			case 'colorName':
				product.colorName = data[keyOfkey];
				break;
			case 'spring':
				product.spring = Number(data[keyOfkey]);
				break;
			case 'summer':
				product.summer = Number(data[keyOfkey]);
				break;
			case 'fall':
				product.fall = Number(data[keyOfkey]);
				break;
			case 'winter':
				product.winter = Number(data[keyOfkey]);
				break;
			case 'basicProductImgName':
				product.basicProductImgName = data[keyOfkey];
				break;
			case 'colorProductImgName':
				product.colorProductImgName = data[keyOfkey];
				break;
			case 'colorImgName':
				product.colorImgName = data[keyOfkey];
				break;
			case 'testImgName':
				product.testImgName = data[keyOfkey];
				break;
			case 'brandName':
				product.brandName = data[keyOfkey];
				break;
			case 'productKindName':
				product.productKindName = data[keyOfkey];
				break;
			}
			
		}
	}
	
	
	var getDataOfReview = function(data, review) {
		
		for(var keyOfkey in data) {
			
			switch(keyOfkey) {
			case 'seq':
				review.seq = Number(data[keyOfkey]);
				break;
			case 'subject':
				review.subject = data[keyOfkey];
				break;
			case 'wdate':
				review.wdate = data[keyOfkey];
				break;
			case 'content':
				review.content = data[keyOfkey];
				break;
			case 'reviewImgName':
				review.reviewImgName = data[keyOfkey];
				break;
			case 'productImgName':
				review.productImgName = data[keyOfkey];
				break;
			case 'countOfStar':
				review.countOfStar = Number(data[keyOfkey]);
				break;
			case 'writer':
				review.writer = data[keyOfkey];
				break;
			case 'productName':
				review.productName = data[keyOfkey];
				break;
			case 'brandName':
				review.brandName = data[keyOfkey];
				break;
			}
			
		}
	}
	
	// 천의 자릿수에 콤마 표시
		var changeNumberT= function(number){
	    
			if(number == 0) 
				return 0;
	 
			var reg = /(^[+-]?\d+)(\d{3})/;
			var n = (number + '');
	 
			while (reg.test(n)) n = n.replace(reg, '$1' + ',' + '$2');
	 
			return n;
		};
	
});

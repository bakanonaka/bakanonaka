	
$(document).ready(function() {

	//초기화-------------
	var cPage = 1;
	var recordPerPage = 3;
	var totalPage = 6;
	var blockPerPage = 3;
	var numberOfStartBlock = 1;
	var numberOfEndBlock = numberOfStartBlock + blockPerPage;
	//-------------
	
//	var scrollWindowToUnderSlider = function(){
//		$("html, body").animate({ scrollTop: 450 }, "slow");
//	}
	
	//페이지 번호 클릭시
	$(document).on('click', 'a.pageNumber', function() {
		
		var pageNumber_text = $(this).attr('id');
		
		initSortingEvents(Number(pageNumber_text));
		
//		scrollWindowToUnderSlider();
	}); 
	

	// < 화살표 (이전 블럭)
	$(document).on('click', '#icon_pageLeft', function() {

//		alert('numberOfStartBlock' + numberOfStartBlock + '-blockPerPage' + blockPerPage);
		
		if(numberOfStartBlock != 1) {
			initSortingEvents(numberOfStartBlock-blockPerPage);
			
//			scrollWindowToUnderSlider();
		}
		
	});
	
	// > 화살표 이동(다음 블럭)
	$(document).on('click', '#icon_pageRight', function() {
		
//		alert('numberOfStartBlock' + numberOfStartBlock + '+blockPerPage' + blockPerPage);
		
		if(numberOfStartBlock + blockPerPage <= totalPage) {
			initSortingEvents(numberOfStartBlock + blockPerPage);
			
//			scrollWindowToUnderSlider();
		}
	});
	
	
	//페이징 ajax로 페이지 관련 숫자를 넘겨 받을 때, Number(data[i][key])를 꼭 해줘야 한다. 
	var changePagingNumberAndIcon = function(numberOfPage) {
		
		cPage = numberOfPage;
		
		$('#pageNumberList_event').empty();
		var pageNumberList_event = "";
		
		for(var i = numberOfStartBlock; i < numberOfEndBlock;i++) {
			
			if(numberOfPage == i) {
				pageNumberList_event += '&nbsp;<a id = "'+i+'" style = "color:yellow;">'+ i +'</a>&nbsp;';
			} else if(i > totalPage) {	
				//전체 페이지 수까지 보여주고, 블럭이 전체 페이지 수보다 클 때 제외
				break;
			} else {
				pageNumberList_event += '<a class = "pageNumber btn" id = "'+i+'">'+i+'</a>&nbsp;';
			}
		}
		
		$('#pageNumberList_event').prepend('<i class="fa fa-chevron-left btn btn-black" id = "icon_pageLeft"></i>&nbsp;');	//좌 블럭
		$('#pageNumberList_event').append(pageNumberList_event);	// 페이징
		$('#pageNumberList_event').append('&nbsp;<i class="fa fa-chevron-right btn btn-black" id = "icon_pageRight"></i>');	//우 블럭
		
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
	
	
	// 이벤트 요청 메소드(json 방식)
	var initSortingEvents = function(index) {
		
		$.ajax({
			url:'initEventList.board',
			type: 'POST',
			data: {
				cpage:index
			},
			dataType: 'json',
			success: function(data) {

				console.log(data);
				
				var flag = 1;
				var no = 0;						
				var title = '';					
				var imgName = '';
				var brandName = '';
				var dateFrom = '';
				var dateTo = '';
				var eventBoardUrl = '';

//				private String address;		//가장 가까운 지점주소
//				private String storeName;	//가장 가까운 지점명

				
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
							case 'title':
								title = data[i][key];
								break;
							case 'imgName':
								imgName = data[i][key];
								break;
							case 'brandName':
								brandName = data[i][key];
								break;	
							case 'dateFrom':
								dateFrom = data[i][key];
								break;	
							case 'dateTo':
								dateTo = data[i][key];
								break;	
							case 'eventBoardUrl':
								eventBoardUrl = data[i][key];
								break;	
							default:
								break;
						}
						
					}
					
					
					if(i == 0 && flag == 0) {	
						
						//이벤트 박스 목록을 없앤다.
						$('#eventBoxList').empty();
						
						//첫번째 data에는 flag 와 page 정보있음
						continue;
					} else if(flag == 2) {
						//이벤트 없음
						break;
					}
					
//					alert(no+' '+title+' '+imgName+' '+brandName+' '+dateFrom+' '+dateTo + ' ' + eventBoardUrl);
					
					var eventBox = "";
				
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
					
					eventBox = "<div class='col-md-4 col-sm-6'>";
					eventBox += "	<div class='box box-lg animated'>";
					eventBox += "	<div class='box-content slide-up box-gallery padd-zero br-white'>";
					
					eventBox += "		<div style = 'top: 0%; padding-top: 5px; padding-bottom: 5px; vertical-align: middle; position: relative;'><img src='"+brandImgPath+"' style = 'max-height:30px; padding:5px;' align='middle' alt=''/></div>";				
					eventBox += "		<div style='height: 100%; bottom: 25%; position: relative; text-align: center;'>";
					eventBox += "		<div style = 'height: 22%; top:95%; position:relative; color: black; border-top:solid 1px #e63131; vertical-align: middle; padding-top:5px;'>";
					eventBox += "			<h4>제목 : "+title+"</h4>";
					eventBox += "			<h5>기간 : "+dateFrom+" ~ "+dateTo+"</h5>";
					eventBox += "		</div>";
					
					if(!imgName || imgName.length < 3) {
						
						eventBox += "			<img src='./eventImg/default.jpg' class='img-responsive box-gallery-img' alt='' />";
					} else {
						eventBox += "			<img src='./eventImg/"+ imgName +"' class='img-responsive box-gallery-img' alt='' />";
					}
					
					eventBox += "		</div>";
					eventBox += "		<div class='image-gallery-caption movetoup box-hover-black2' style='text-align: center'>";
//					eventBox += "		<br/>";
					eventBox += "			<input type = 'button' value = '이벤트 페이지로 가기' onclick='window.open(\""+eventBoardUrl+"\",\"_blank\");' class='btn btn-primary btn-lg btn-ar' style = 'margin-top:10px;'>";
					eventBox += "		</div>";
					eventBox += "	</div>";
					eventBox += "	</div>";
					eventBox += "</div>";
					
//					alert(eventBox);
					$('#eventBoxList').append(eventBox);
					
					console.log(eventBox);
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

//					alert('이벤트 불러오기를 성공했습니다.');
					
				} else if(flag == 1){
					alert('진행중인 이벤트 불러오기를 실패했습니다.');
				} else if(flag == 2) {
					alert('진행중인 이벤트 정보가 없습니다.');
				}
			},
			error: function(xhr, status, error) {
				alert('에러 : ' + status + '\n\n' + error);
			},
			complete:function(xhr, status){//error, success 상관 없이 실행 되는 부분
			}
		});
	};//initSortingEvents() 끝
	
	initSortingEvents(1);	//페이지를 띄울 때, 첫번째 페이지를 보여준다.
	
});

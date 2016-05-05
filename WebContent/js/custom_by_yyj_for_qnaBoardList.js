//페이지 열리자마자 DB 검색

$(document).ready(function() {
	
	//초기화-------------
	var cPage = 1;
	
	//임시 값
	var recordPerPage = 6;
	var totalPage = 30;
	var blockPerPage = 5;
	var numberOfStartBlock = 1;
	var numberOfEndBlock = numberOfStartBlock + blockPerPage;
	//-------------
	
//	var scrollWindowToUnderSlider = function(){
//		$("html, body").animate({ scrollTop: 460 }, "slow");
//	}
	
	//페이지 번호 클릭시
	$(document).on('click', 'a.pageNumber', function() {
		
		var pageNumber_text = $(this).attr('id');
		
		initQnaBoardList(Number(pageNumber_text), $('select[name="eventNo"]').val());
		scrollWindowToUnderSlider();
	}); 
	

	// < 화살표 (이전 블럭)
	$(document).on('click', '#icon_pageLeft', function() {

//		alert('numberOfStartBlock' + numberOfStartBlock + '-blockPerPage' + blockPerPage);
		
		if(numberOfStartBlock != 1) {
			initQnaBoardList(numberOfStartBlock-blockPerPage, $('select[name="eventNo"]').val());
			scrollWindowToUnderSlider();
		}
		
	});
	
	// > 화살표 이동(다음 블럭)
	$(document).on('click', '#icon_pageRight', function() {
		
//		alert('numberOfStartBlock' + numberOfStartBlock + '+blockPerPage' + blockPerPage);
		
		if(numberOfStartBlock + blockPerPage <= totalPage) {
			initQnaBoardList(numberOfStartBlock + blockPerPage, $('select[name="eventNo"]').val());
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
	
	
	// 이벤트 요청 메소드(json 방식)
	var initQnaBoardList = function(index, eventNoName) {
		
		$.ajax({
			url:'initQnaBoardList.board',
			type: 'POST',
			data: {
				cpage:index,
				eventNoName:eventNoName
			},
			dataType: 'json',
			success: function(data) {

//				console.log(data);

				var flag = 1;
				var seq = 0;						//글 번호
				var subject = '';					//글 제목
				var wdate = '';						//작성일
				var content = '';					//내용
				var eventName = '';					//이벤트 이름
				var writer = '';					//작성자명
				
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
							case 'seq':
								seq = Number(data[i][key]);
								break;
							case 'subject':
								subject = data[i][key];
								break;
							case 'wdate':
								wdate = data[i][key];
								break;
							case 'content':
								content = data[i][key];
								break;
							case 'eventName':
								eventName = data[i][key];
								break;
							case 'writer':
								writer = data[i][key];
								break;
							default:
								break;
						}
						
					}
					
					
					if(i == 0 && flag == 0) {	
						
						//게시글 지우기
						$('#qnaBoardList').empty();
						
						//첫번째 data에는 flag 와 page 정보있음
						continue;
					} else if(flag == 2) {
						//데이터 없더라도 여기에서는 꼭 지우기
						$('#qnaBoardList').empty();
						
						break;
					}
					
//					alert(seq+' '+subject+' '+wdate+' '+content+' '+eventName+' '+writer);
					
					var qnaBoard = "";
				
					qnaBoard = "<table align='center' width='80%' border='0' cellpadding='0' cellspacing='0'>";
					qnaBoard += "<tr valign='middle'>";
					qnaBoard += "<td width='6%' align='center' class='text'>" + seq + "</td>";
					qnaBoard += "	<td width='26%' align='center' class='text'><h5>" + eventName + "</h5></td>";
					qnaBoard += "	<td width='38%' align='center' >";
					qnaBoard += "							<a href='qnaBoardView.board?seq=" + seq +" 'style='color: white;'>" + subject + "</a>";
					qnaBoard += "	</td>";
					qnaBoard += "	<td width='10%' align='center' ><h5>" + writer + "</h5></td>";
					qnaBoard += "		<td width='14%' align='center'><h6>" + wdate + "</h6></td>";
					qnaBoard += "	</tr>";
					qnaBoard += "	<tr valign='middle' bgcolor='#d9d9e6'>";
					qnaBoard += "<td height='1' colspan='8' align='center' class='text'></td>";
					qnaBoard += "</table>";
					qnaBoard += "</tr>";
					
//					alert(qnaBoard);
					$('#qnaBoardList').append(qnaBoard);
					
//					console.log(qnaBoard);
				}

				if(flag == 0) {
					
					changePagingNumberAndIcon(currentPage);

//					alert('이벤트 Q&A 게시글 불러오기를 성공했습니다.');
					
				} else if(flag == 1){
					alert('이벤트 Q&A 게시글 불러오기를 실패했습니다.');
				} else if(flag == 2) {
					changePagingNumberAndIcon(currentPage);
					
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
	
	
//	initQnaBoardList(1);	//페이지를 띄울 때, 첫번째 페이지를 보여준다.
	
	
	
	//로그인 여부 확인
	$('#a_qnaBoardList_Write').click(function(){
		
		if($('#str_loggedMemNo').val() == null) {
			alert('로그인 이후에 글을 쓸 수 있습니다.');
		}else {
			top.location.href ='qnaBoardWrite.board';
		}
	});
	

	// 이벤트 요청 메소드(json 방식)
	var getEventInfo = function(eventNoName) {
		
		$.ajax({
			url:'qnaEventInfo.board',
			type: 'POST',
			data: {
				eventNoName:eventNoName
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
						
						//셀렉터 비움
						$('select[name="eventNo"]').empty();
						
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
					
					eventBox = "<div class='col-md-4 col-sm-4' style = 'margin:0 auto;'>";
					eventBox += "</div>";
					eventBox += "<div class='col-md-4 col-sm-4' style = 'margin:0 auto;'>";
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
//						alert('왜 여기로?');
//						alert(imgName);
					}
					
					eventBox += "		</div>";
					eventBox += "		<div class='image-gallery-caption movetoup box-hover-black2' style='text-align: center'>";
					eventBox += "			<input type = 'button' value = '이벤트 페이지로 가기' onclick='window.open(\""+eventBoardUrl+"\",\"_blank\");' class='btn btn-primary btn-lg btn-ar' style = 'margin-top:10px;'>";
					eventBox += "		</div>";
					eventBox += "	</div>";
					eventBox += "	</div>";
					eventBox += "</div>";
					
//					alert(eventBox);
					
					var eventName = eventNoName.substring(eventNoName.indexOf(".")+1, eventNoName.length);
//					alert(eventName);
//					alert(eventNoName);
					
					if(eventName == 'init' && i == 1) {	//처음 페이지를 열때, 첫번째 이벤트를 보여줌
						$('#eventBoxList').append(eventBox);
					} else if(eventName == title){	//이벤트 셀렉터의 선택한 값일 경우에만 화면에 표시.
						$('#eventBoxList').append(eventBox);
					} else {
//						alert('이벤트 한 개만 보여주므로 여긴 패스');
					}
					
					console.log(eventBox);
					
					
					var selectOption = "";
					
					if(eventName != 'init') {
						
						if(eventName == title) {
							selectOption += "<option value='"+no+"."+title+"' selected = 'selected'>"+no+" . "+title+"</option>";
						} else {
							selectOption += "<option value='"+no+"."+title+"'>"+no+" . "+title+"</option>";
						}
						
					} else {//첫 페이지 열때,
						selectOption += "<option value='"+no+"."+title+"'>"+no+" . "+title+"</option>";
					}
					
					$('select[name="eventNo"]').append(selectOption);
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
					
					
					//셀렉터에서 선택한 이벤트명으로 게시판 글 불러오기.
					initQnaBoardList(1, $('select[name="eventNo"]').val());
					
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
	};
	
	getEventInfo('init');
	
	
	$('select[name="eventNo"]').change(function(){
//		alert('이벤트 체인지 : ' + $(this).val());
		
		var eventTitle = $(this).val();
		
		getEventInfo(eventTitle);
	});
	
	
	
	
	
});

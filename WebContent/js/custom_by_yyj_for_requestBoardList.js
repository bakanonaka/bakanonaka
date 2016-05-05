	
$(document).ready(function() {
	
	//초기화-------------
	var cPage = 1;
	
	//임시 값
	var recordPerPage = 5;
	var totalPage = 25;
	var blockPerPage = 5;
	var numberOfStartBlock = 1;
	var numberOfEndBlock = numberOfStartBlock + blockPerPage;
	//-------------
	
	var scrollWindowToUnderSlider = function(){
//		$("html, body").animate({ scrollTop: 460 }, "slow");
	}
	
	//페이지 번호 클릭시
	$(document).on('click', 'a.pageNumber', function() {
		
		var pageNumber_text = $(this).attr('id');
		
		
		switch($('#filters').find('li > a.btn-default').data('filter')) {
		
			case '*':
				initRequestBoardList(Number(pageNumber_text));		
				break;
			case '.토니모리':
				requestBoardListForBrand(Number(pageNumber_text), '토니모리');
				break;
			case '.더페이스샵':
				requestBoardListForBrand(Number(pageNumber_text), '더페이스샵');
				break;
			case '.미샤':
				requestBoardListForBrand(Number(pageNumber_text), '미샤');
				break;
			case '.스킨푸드':
				requestBoardListForBrand(Number(pageNumber_text), '스킨푸드');
				break;
			case '.에뛰드하우스':
				requestBoardListForBrand(Number(pageNumber_text), '에뛰드하우스');
				break;
			case '.이니스프리':
				requestBoardListForBrand(Number(pageNumber_text), '이니스프리');
				break;
			default:
				break;
		}
			
	}); 
	

	// < 화살표 (이전 블럭)
	$(document).on('click', '#icon_pageLeft', function() {

//		alert('numberOfStartBlock' + numberOfStartBlock + '-blockPerPage' + blockPerPage);
		
		if(numberOfStartBlock != 1) {
			
//			scrollWindowToUnderSlider();
			
			switch($('#filters').find('li > a.btn-default').data('filter')) {
			
			case '*':
				initRequestBoardList(numberOfStartBlock-blockPerPage);		
				break;
			case '.토니모리':
				requestBoardListForBrand(numberOfStartBlock-blockPerPage, '토니모리');
				break;
			case '.더페이스샵':
				requestBoardListForBrand(numberOfStartBlock-blockPerPage, '더페이스샵');
				break;
			case '.미샤':
				requestBoardListForBrand(numberOfStartBlock-blockPerPage, '미샤');
				break;
			case '.스킨푸드':
				requestBoardListForBrand(numberOfStartBlock-blockPerPage, '스킨푸드');
				break;
			case '.에뛰드하우스':
				requestBoardListForBrand(numberOfStartBlock-blockPerPage, '에뛰드하우스');
				break;
			case '.이니스프리':
				requestBoardListForBrand(numberOfStartBlock-blockPerPage, '이니스프리');
				break;
			default:
				break;
			}
		}
		
	});
	
	// > 화살표 이동(다음 블럭)
	$(document).on('click', '#icon_pageRight', function() {
		
//		alert('numberOfStartBlock' + numberOfStartBlock + '+blockPerPage' + blockPerPage);
		
		if(numberOfStartBlock + blockPerPage <= totalPage) {
//			scrollWindowToUnderSlider();
			
			switch($('#filters').find('li > a.btn-default').data('filter')) {
			
			case '*':
				initRequestBoardList(numberOfStartBlock + blockPerPage);		
				break;
			case '.토니모리':
				requestBoardListForBrand(numberOfStartBlock + blockPerPage, '토니모리');
				break;
			case '.더페이스샵':
				requestBoardListForBrand(numberOfStartBlock + blockPerPage, '더페이스샵');
				break;
			case '.미샤':
				requestBoardListForBrand(numberOfStartBlock + blockPerPage, '미샤');
				break;
			case '.스킨푸드':
				requestBoardListForBrand(numberOfStartBlock + blockPerPage, '스킨푸드');
				break;
			case '.에뛰드하우스':
				requestBoardListForBrand(numberOfStartBlock + blockPerPage, '에뛰드하우스');
				break;
			case '.이니스프리':
				requestBoardListForBrand(numberOfStartBlock + blockPerPage, '이니스프리');
				break;
			default:
				break;
			}
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
	var initRequestBoardList = function(index) {
		
		$.ajax({
			url:'initRequestBoardList.board',
			type: 'POST',
			data: {
				cpage:index
			},
			dataType: 'json',
			success: function(data) {

//				console.log(data);

				var flag = 1;
				var seq = 0;						//글 번호
				var subject = '';					//글 제목
				var wdate = '';						//작성일
				var content = '';					//내용
				var requestImgName = '';			//요청 사진
				var brandName = '';					//브랜드 네임
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
							case 'requestImgName':
								requestImgName = data[i][key];
								break;
							case 'brandName':
								brandName = data[i][key];
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
						$('#requestBoardList').empty();
						
						//첫번째 data에는 flag 와 page 정보있음
						continue;
					} else if(flag == 2) {
						
						//글 없음
						break;
					}
					
//					alert(seq+' '+subject+' '+wdate+' '+content+' '+requestImgName+' '+brandName+' '+writer);
					
					var requestBoard = "";
					
//					requestBoard = "<table width='100%' border='0' cellpadding='0' cellspacing='0'>";
//					requestBoard += " <tr>";
//					requestBoard += "<td height='1'></td>";
//					requestBoard += "	</tr>";
//					requestBoard += "	<tr>";
//					requestBoard += "		<td>";
//					requestBoard += "			<table width='100%' border='0' cellpadding='0' cellspacing='0'>";
//					requestBoard += "				<tr height='25' onMouseOver=\"this.className='evencell'\" onMouseOut=\"this.className=''\">";
//					requestBoard += "					<td width='40' align='center'>" + seq + "</td>";
//					requestBoard += "					<td>";
//					requestBoard += "						<span style='width:200' class='elltxt'>";
//					requestBoard += "							<a href='requestBoardView.board?seq=" + seq +"'>" + subject + "</a>";
//					requestBoard += "						</span>";
//					requestBoard += "				</td>";
//					requestBoard += "				<td width='100' align='center'><h5>" + writer + "</h5></td>";
//					requestBoard += "				<td width='140' align='center'><h6>" + wdate + "</h6></td>";
//					requestBoard += "		</tr>";
//					requestBoard += "	</table>";
//					requestBoard += "	</td>";
//					requestBoard += "</tr>";
//					requestBoard += "<tr>";
//					requestBoard += "		<td height='1'></td>";
//					requestBoard += "</tr>";
//					requestBoard += "<tr>";
//					requestBoard += "		<td align='center' class='imgline'></td>";
//					requestBoard += "<tr valign='middle' bgcolor='#d9d9e6'>";
//					requestBoard += "	<td height='1' colspan='8' align='center' class='text'></td>";
//					requestBoard += "</tr>";
//					requestBoard += "</table>";
					
					
					requestBoard = "<table align='center' width='80%' border='0' cellpadding='0' cellspacing='0'>";
					requestBoard += "<tr valign='middle'>";
					requestBoard += "<td width='6%' align='center' class='text'>" + seq + "</td>";
					
					var imgPath = "./productImg/";
					
					switch(brandName) {
						case '더페이스샵':
							imgPath += brandName +"/logo/더페이스샵_small_150.png";
							break;
						case '미샤':
							imgPath += brandName +"/logo/미샤_small_150.png";
							break;
						case '스킨푸드':
							imgPath += brandName +"/logo/스킨푸드_small.png";
							break;
						case '에뛰드하우스':
							imgPath += brandName +"/logo/에뛰드하우스_small_150.png";
							break;
						case '이니스프리':
							imgPath += brandName +"/logo/이니스프리_small_150.png";
							break;
						case '토니모리':
							imgPath += brandName +"/logo/토니모리_small_150.png";
							break;
							
					}
					
					requestBoard += "	<td width='15%' align='center' class='text'><img src='"+imgPath+"' style = 'background:white; max-height:30px; padding:1px;' align='middle' alt=''/></td>";
					
//					requestBoard += "	<td width='15%' align='center' class='text'><h5>" + brandName + "</h5></td>";
					
					
					
					requestBoard += "	<td width='49%' align='center' >";
					requestBoard += "							<a href='requestBoardView.board?seq=" + seq +" 'style='color: white;'>" + subject + "</a>";
					requestBoard += "	</td>";
					requestBoard += "	<td width='10%' align='center' ><h5>" + writer + "</h5></td>";
					requestBoard += "		<td width='14%' align='center'><h6>" + wdate + "</h6></td>";
					requestBoard += "	</tr>";
					requestBoard += "	<tr valign='middle' bgcolor='#d9d9e6'>";
					requestBoard += "<td height='1' colspan='8' align='center' class='text'></td>";
					requestBoard += "</table>";
					requestBoard += "</tr>";
					
					
					
					
//					alert(requestBoard);
					$('#requestBoardList').append(requestBoard);
					
//					console.log(requestBoard);
				}

				if(flag == 0) {
					
					changePagingNumberAndIcon(currentPage);

//					alert('게시글 불러오기를 성공했습니다.');
					
				} else if(flag == 1){
					alert('게시글 불러오기를 실패했습니다.');
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
	
	initRequestBoardList(1);	//페이지를 띄울 때, 첫번째 페이지를 보여준다.
	
	//로그인 여부 확인
	$('#a_requestBoardList_Write').click(function(){
		
		if($('#str_loggedMemNo').val() == null) {
			alert('로그인 이후에 글을 쓸 수 있습니다.');
		}else {
			top.location.href ='requestBoardWrite.board';
		}
	});
	
	//후기 게시글 필터 선택시, 배경색 변경하기
	$('#filters > li > a').click(function(){
		
		var clickedA = $(this); 
		
		$('#filters').find('li > a').each(function(){
			
			if($(this).data('filter') == clickedA.data('filter')) {
				clickedA.removeClass('br-lightgrey');
				clickedA.addClass('btn-default');	//흰색
				
//				.토니모리" class="btn br-lightgrey black"><img src='productImg/토니모리/logo/토니모리_small_150.gif'style = 'height: 20px; width: 120px; ' align='middle'/></a></li>
//				<li><a href="#" data-filter=".더페이스샵" class="btn br-lightgrey black"><img src='productImg/더페이스샵/logo/더페이스샵_small.gif'style = 'height: 20px; width: 120px; ' align='middle'/></a></li>
//				<li><a href="#" data-filter=".미샤" class="btn br-lightgrey black"><img src='productImg/미샤/logo/미샤_small_150.gif'style = 'height: 20px; width: 120px; ' align='middle'/></a></li>
//				<li><a href="#" data-filter=".스킨푸드" class="btn br-lightgrey black"><img src='productImg/스킨푸드/logo/스킨푸드_small.gif'style = 'height: 20px; width: 120px; ' align='middle'/></a></li>
//				<li><a href="#" data-filter=".에뛰드하우스" class="btn br-lightgrey black" ><img src='productImg/에뛰드하우스/logo/에뛰드하우스_small_150.gif'style = 'height: 20px; width: 120px; ' align='middle'/></a></li>
//				<li><a href="#" data-filter=".이니스프리" class="b
				
				switch(clickedA.data('filter')) {
					case '*':
						initRequestBoardList(1);
						break;
					case '.토니모리':
						requestBoardListForBrand(1, '토니모리');
						break;
					case '.더페이스샵':
						requestBoardListForBrand(1, '더페이스샵');
						break;
					case '.미샤':
						requestBoardListForBrand(1, '미샤');
						break;
					case '.스킨푸드':
						requestBoardListForBrand(1, '스킨푸드');
						break;
					case '.에뛰드하우스':
						requestBoardListForBrand(1, '에뛰드하우스');
						break;
					case '.이니스프리':
						requestBoardListForBrand(1, '이니스프리');
						break;
					default:
						break;
				}
				
			} else {
				$(this).removeClass('btn-default');
				$(this).addClass('br-lightgrey');	//옅은 회색
			}
		});
		
	});
	
	
	// 화장품 요청 메소드(json 방식)
	var requestBoardListForBrand = function(index, brandName) {
		
		$.ajax({
			url:'requestBoardListForBrand.board',
			type: 'POST',
			data: {
				cpage:index,
				brandName:brandName
			},
			dataType: 'json',
			success: function(data) {

//				console.log(data);

				var flag = 1;
				var seq = 0;						//글 번호
				var subject = '';					//글 제목
				var wdate = '';						//작성일
				var content = '';					//내용
				var requestImgName = '';			//요청 사진
				var brandName = '';					//브랜드 네임
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
							case 'requestImgName':
								requestImgName = data[i][key];
								break;
							case 'brandName':
								brandName = data[i][key];
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
						$('#requestBoardList').empty();
						
						//첫번째 data에는 flag 와 page 정보있음
						continue;
					} else if(flag == 2) {
						
						//글 없음
						break;
					}
					
//					alert(seq+' '+subject+' '+wdate+' '+content+' '+requestImgName+' '+brandName+' '+writer);
					
					var requestBoard = "";
					
//					requestBoard = "<table width='100%' border='0' cellpadding='0' cellspacing='0'>";
//					requestBoard += " <tr>";
//					requestBoard += "<td height='1'></td>";
//					requestBoard += "	</tr>";
//					requestBoard += "	<tr>";
//					requestBoard += "		<td>";
//					requestBoard += "			<table width='100%' border='0' cellpadding='0' cellspacing='0'>";
//					requestBoard += "				<tr height='25' onMouseOver=\"this.className='evencell'\" onMouseOut=\"this.className=''\">";
//					requestBoard += "					<td width='40' align='center'>" + seq + "</td>";
//					requestBoard += "					<td>";
//					requestBoard += "						<span style='width:200' class='elltxt'>";
//					requestBoard += "							<a href='requestBoardView.board?seq=" + seq +"'>" + subject + "</a>";
//					requestBoard += "						</span>";
//					requestBoard += "				</td>";
//					requestBoard += "				<td width='100' align='center'><h5>" + writer + "</h5></td>";
//					requestBoard += "				<td width='140' align='center'><h6>" + wdate + "</h6></td>";
//					requestBoard += "		</tr>";
//					requestBoard += "	</table>";
//					requestBoard += "	</td>";
//					requestBoard += "</tr>";
//					requestBoard += "<tr>";
//					requestBoard += "		<td height='1'></td>";
//					requestBoard += "</tr>";
//					requestBoard += "<tr>";
//					requestBoard += "		<td align='center' class='imgline'></td>";
//					requestBoard += "<tr valign='middle' bgcolor='#d9d9e6'>";
//					requestBoard += "	<td height='1' colspan='8' align='center' class='text'></td>";
//					requestBoard += "</tr>";
//					requestBoard += "</table>";
					
					
					requestBoard = "<table align='center' width='80%' border='0' cellpadding='0' cellspacing='0'>";
					requestBoard += "<tr valign='middle'>";
					requestBoard += "<td width='6%' align='center' class='text'>" + seq + "</td>";
					
					var imgPath = "./productImg/";
					
					switch(brandName) {
						case '더페이스샵':
							imgPath += brandName +"/logo/더페이스샵_small_150.png";
							break;
						case '미샤':
							imgPath += brandName +"/logo/미샤_small_150.png";
							break;
						case '스킨푸드':
							imgPath += brandName +"/logo/스킨푸드_small.png";
							break;
						case '에뛰드하우스':
							imgPath += brandName +"/logo/에뛰드하우스_small_150.png";
							break;
						case '이니스프리':
							imgPath += brandName +"/logo/이니스프리_small_150.png";
							break;
						case '토니모리':
							imgPath += brandName +"/logo/토니모리_small_150.png";
							break;
							
					}
					
					requestBoard += "	<td width='15%' align='center' class='text'><img src='"+imgPath+"' style = 'max-height:30px; padding:5px;' align='middle' alt=''/></td>";
					
//					requestBoard += "	<td width='15%' align='center' class='text'><h5>" + brandName + "</h5></td>";
					
					
					
					requestBoard += "	<td width='49%' align='center' >";
					requestBoard += "							<a href='requestBoardView.board?seq=" + seq +" 'style='color: white;'>" + subject + "</a>";
					requestBoard += "	</td>";
					requestBoard += "	<td width='10%' align='center' ><h5>" + writer + "</h5></td>";
					requestBoard += "		<td width='14%' align='center'><h6>" + wdate + "</h6></td>";
					requestBoard += "	</tr>";
					requestBoard += "	<tr valign='middle' bgcolor='#d9d9e6'>";
					requestBoard += "<td height='1' colspan='8' align='center' class='text'></td>";
					requestBoard += "</table>";
					requestBoard += "</tr>";
					
					
					
					
//					alert(requestBoard);
					$('#requestBoardList').append(requestBoard);
					
//					console.log(requestBoard);
				}

				if(flag == 0) {
					
					changePagingNumberAndIcon(currentPage);

//					alert('게시글 불러오기를 성공했습니다.');
					
				} else if(flag == 1){
					alert('게시글 불러오기를 실패했습니다.');
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
});

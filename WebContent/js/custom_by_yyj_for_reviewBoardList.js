//페이지 열리자마자 DB 검색

$(document).ready(function() {
	
	var grp = '';
	
	
	//초기화-------------
	var cPage = 1;
	var recordPerPage = 8;
	var totalPage = 38;
	var blockPerPage = 5;
	var numberOfStartBlock = 1;
	var numberOfEndBlock = numberOfStartBlock + blockPerPage;
	//-------------

	// 화장품 요청 메소드(json 방식)
	var initReviewBoardList = function(index) {
		
		$.ajax({
			url:'initReviewBoardList.board',
			type: 'POST',
			data: {
				cpage:index
			},
			dataType: 'json',
			success: function(data) {

				console.log(data);

				var flag = 1;
				var seq = 0;						//
				var subject = '';					//
				var wdate = '';						//
				var content = '';					//
				var reviewImgName = '';					//
				var productImgName = '';					//
				var productName = '';
				var countOfStar = 0;					//
				var writer = '';					//
				var brandName = '';
				var colorNameOfProduct = '';
				
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
							case 'reviewImgName':
								reviewImgName = data[i][key];
								break;
							case 'productImgName':
								productImgName = data[i][key];
								break;
							case 'countOfStar':
								countOfStar = Number(data[i][key]);
								break;
							case 'writer':
								writer = data[i][key];
								break;
							case 'productName':
								productName = data[i][key];
								break;
							case 'brandName':
								brandName = data[i][key];
								break;
							case 'colorNameOfProduct':
								colorNameOfProduct = data[i][key];
								break;
							default:
								break;
						}
						
					}
					
					
					if(i == 0 && flag == 0) {	
						
						//게시글 지우기
//						$('#portfolio').empty();
						
						//첫번째 data에는 flag 와 page 정보있음
						continue;
					} else if(flag == 2) {
						
						//게시글 없음
						break;
					}
					
//					alert(seq+' '+subject+' '+wdate+' '+content+' '+reviewImgName+' '+productImgName+' '+countOfStar+' '+writer+' '+brandName+' '+productName);
					
//					var test = './productImg/'yy;
//					alert();
					
					var reviewBoard = "";
					
					reviewBoard += "<div class='element "+brandName+"'>";
					reviewBoard += "<div style='padding: 10px; border: 10px;' class='pcap br-white2' class='btn btn-info btn-lg' id = 'myModal"+seq+"'>";
					reviewBoard += "	<table class='tg' border='0' class='tg' style='border-color: red; table-layout: fixed; word-break:break-all;'>";
					
					
					var starIcon = "";
					for(var j = 0; j < 5; j++){
						if(countOfStar >=1) {
							starIcon += "★";
						} else {
							starIcon += "☆";
						}
						countOfStar--;
					}
					
//					reviewBoard += "	  <tr style = 'height:5px;'>";
//					reviewBoard += "	  <td style = 'color:black; height:1px;'>"+starIcon;
//					reviewBoard += "	  </td>";
//					reviewBoard += "	  </tr>";
					
					reviewBoard += "	  <tr>";
					if(subject.length > 10) { //제목 길이가 15자 이상일경우, 15자 자르고 뒤에 ... 붙이기
		    			reviewBoard += "	    <th class='tg-031e' colspan='2'><span style='color: black; height:auto;'><div style = 'height:25px;'>"+starIcon+"</div>"+subject.substring(0, 10)+"...</span></th>";
		    		}else {
		    			reviewBoard += "	    <th class='tg-031e' colspan='2'><span style='color: black; height:auto;'><div style = 'height:25px;'>"+starIcon+"</div>"+subject+"</span></th>";
		    		}
					reviewBoard += "	  </tr>";
					
					reviewBoard += "	  <tr>";
					if(!reviewImgName || reviewImgName.length < 3) {
						reviewBoard += "	<td class='tg-yw4l' colspan='2'>";
						reviewBoard += "	<img src='./productImg/default.jpg' alt=''/>";
						reviewBoard += "	</td>";
						
					} else {
						reviewBoard += "	<td class='tg-yw4l' colspan='2'>";
						reviewBoard += "	<a href='./uploadReviewImage/"+ reviewImgName +"' class='prettyphoto' >";
			    		reviewBoard += "		<img src='./uploadReviewImage/"+ reviewImgName +"' alt=''/>";
			    		reviewBoard += "	</a>";
			    		reviewBoard += "	</td>";
					}
					reviewBoard += "		</tr>";
					
					
					reviewBoard += " <tr>";
					reviewBoard += "    <td class='tg-yw4l' colspan='2'><h6 align='left' style='color: black;'>"+writer+"</h6></td>";
					reviewBoard += "  </tr>";
					reviewBoard += "  <tr>";
					reviewBoard += "    <td class='tg-yw4l' colspan='2'><h6 align='right' style='color: black;'>"+wdate+"</h6></td>";
					reviewBoard += "  </tr>";
					reviewBoard += "	<tr valign='middle' bgcolor='#e63131;'>";
					reviewBoard += "<td height='1' colspan='8' align='center' class='text'></td>";
					reviewBoard += "</tr>";
					reviewBoard += "  <tr>";
					reviewBoard += "    <td class='tg-yw4l'><h6 align='left' style='color: black;'>"+brandName+"<br/><br/>"+productName+"<br/><br/>"+colorNameOfProduct+"</h6></td>";
					reviewBoard += "    <td class='tg-yw4l'><h6 align='right' style='color: black;'><img src='./productImg/"+ brandName +"/"+productName+"/"+productImgName+"'style = 'height: 100%; max-height: 50px; width: 100%; max-width: 50px; ' align='middle' alt=''/></td>";
					reviewBoard += "  </tr>";
					reviewBoard += "</table>";
					reviewBoard += "  </div>";
					reviewBoard += "  </div>";
					
//					alert(reviewBoard);
					
					$('#portfolio').append(reviewBoard);
//					console.log(reviewBoard);
				}

				if(flag == 0) {
					
					//현재 페이지 저장하기
					cPage = currentPage;
					
					
					$(".prettyphoto").prettyPhoto({
						overlay_gallery: false, social_tools: false
					});

					/* Isotype */
					// cache container
					var $container = $('#portfolio');
					// initialize isotope
					$container.isotope();
					
					// 상단 필터 메뉴 클릭 이벤트 추가
					$('#filters a').click(function(){
					  var selector = $(this).attr('data-filter');
					  $container.isotope({ filter: selector });
					  return false;
					}); 
					
					//클릭했을때, 글의 seq를 가져오는 구문
					$('#portfolio > div').find('.pcap').each(function(){
						
						$(this).click(function(){
							var seq_text = $(this).attr('id'); 
							var seq = seq_text.replace('myModal','');	
							
							getReviewBoardInfo(seq);
						});
						
					});
					
					

//					alert('Review 게시글 불러오기를 성공했습니다.');
					
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
	
	initReviewBoardList(1);	//페이지를 띄울 때, 첫번째 페이지를 보여준다.
	
	
	//로그인 여부 확인
	$('#a_reviewBoardList_Write').click(function(){
		
		if($('#str_loggedMemNo').val() == null) {
			alert('로그인 이후에 글을 쓸 수 있습니다.');
		}else {
			top.location.href ='reviewBoardWrite.board';
		}
	});

	
	//후기 게시글 필터 선택시, 배경색 변경하기
	$('#filters > li > a').click(function(){
		
		var clickedA = $(this); 
		
		$('#filters').find('li > a').each(function(){
			
			if($(this).data('filter') == clickedA.data('filter')) {
				clickedA.removeClass('br-lightgrey');
				clickedA.addClass('btn-default');	//흰색
			} else {
				$(this).removeClass('btn-default');
				$(this).addClass('br-lightgrey');	//옅은 회색
			}
		});
		
	});
	
	var getReviewBoardInfo = function(seq) {
		
//		alert('글 번호 : ' + seq);
		
		$.ajax({
			url:'reviewBoardViewInfo.board',
			type: 'POST',
			data: {
				seq:seq
			},
			dataType: 'json',
			success: function(data) {

//				console.log(data);

				var flag = 1;
				var seq = 0;						//
				var subject = '';					//
				var wdate = '';						//
				var content = '';					//
				var reviewImgName = '';					//
				var productImgName = '';					//
				var productName = '';
				var countOfStar = 0;					//
				var writer = '';					//
				var brandName = '';
				var colorNameOfProduct = '';
				
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
							case 'seq':
								seq = Number(data[i][key]);
								grp = seq;
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
							case 'reviewImgName':
								reviewImgName = data[i][key];
								break;
							case 'productImgName':
								productImgName = data[i][key];
								break;
							case 'countOfStar':
								countOfStar = Number(data[i][key]);
								break;
							case 'writer':
								writer = data[i][key];
								break;
							case 'productName':
								productName = data[i][key];
								break;
							case 'brandName':
								brandName = data[i][key];
								break;
							case 'colorNameOfProduct':
								colorNameOfProduct = data[i][key];
								break;
							default:
								break;
						}
						
					}
					
					
					if(i == 0 && flag == 0) {	
						
						//게시글 지우기
						$('#reviewModalList').empty();
						
						//첫번째 data에는 flag 와 page 정보있음
						continue;
					} else if(flag == 2) {
						
						//게시글 없음
						break;
					}
					
//					alert(seq+' '+subject+' '+wdate+' '+content+' '+reviewImgName+' '+productImgName+' '+countOfStar+' '+writer+' '+brandName+' '+productName);


					var reviewBoardModal = "";
					reviewBoardModal += "<div class='modal fade' id='detailView"+seq+"' role='dialog'>";
					reviewBoardModal += "  <div class='modal-dialog'>";
					reviewBoardModal += "      <div class='modal-content' style='background-color: #F1F0EF;'>"; /*모달 창 안에 백그라운드 색*/
					reviewBoardModal += "        <div class='modal-header'>";
					reviewBoardModal += "         <button type='button' class='close' data-dismiss='modal'>&times;</button>";
//					reviewBoardModal += "          <h4 class='modal-title' style='color: black;'>글번호:"+seq+"</h4>";
					reviewBoardModal += "          <h1 class='modal-title' style='color: black; table-layout: fixed; word-break:break-all;'>"+subject+"</h1>";
					reviewBoardModal += "        </div>";
					reviewBoardModal += "        <div class='modal-body' style='color: black;'>";
					reviewBoardModal += "<table class='tg' border='0' style='border: 1px; border-color: #e1e0de; text-align: center; margin: 10px; table-layout: fixed; word-break:break-all;'>";
					
					
					reviewBoardModal += "					<tr>";
					if(!reviewImgName || reviewImgName.length < 3) {
						reviewBoardModal += "						<th class='tg-0312' colspan='2' style='padding: 5px; margin: auto;'><img src='./productImg/default.jpg' style = 'width: 100%; max-width: 550px; ' align='middle' alt=''/></th>";
					} else {
						reviewBoardModal += "						<th class='tg-0312' colspan='2' style='padding: 5px; margin: auto;'><img src='./uploadReviewImage/"+ reviewImgName +"' style = 'width: 100%; width: 550px; border: 2px solid #ddd;' align='middle' alt=''/></th>";
					}
					reviewBoardModal += "					</tr>";
					
					
					reviewBoardModal += "	<tr valign='middle' bgcolor='#d9d9e6'>";
					reviewBoardModal += "<td height='1' colspan='8' align='center' class='text'></td>";
					reviewBoardModal += "</tr>";
					
					reviewBoardModal += "					<tr>";
					reviewBoardModal += "						<td class='tg-0313' colspan='2' style='text-align: left; width: 300px; height: 50px; padding: 15px;' valign='top'><span>"+content+"</span></td>";
					reviewBoardModal += "					</tr>";
					
					reviewBoardModal += "	<tr valign='middle' bgcolor='#d9d9e6'>";
					reviewBoardModal += "<td height='1' colspan='8' align='center' class='text'></td>";
					reviewBoardModal += "</tr>";
					
					reviewBoardModal += "					<tr>";
					reviewBoardModal += "						<td class='tg-0314' rowspan='4' style='padding: 3px; text-align: center; height: 100px; width: 100px;'><img src='./productImg/"+ brandName +"/"+productName+"/"+productImgName+"'style = 'height: 100px; width: 100px; border: 2px solid #e63131;' align='middle' alt=''/></td>";
					 					
					var starIcon = "";
					for(var j = 0; j < 5; j++){
						if(countOfStar >=1) {
							starIcon += "★";
						} else {
							starIcon += "☆";
						}
						countOfStar--;
					}
					
					reviewBoardModal += "						<td class='tg-0315' style='padding: 3px; text-align: left;'><h6>"+starIcon+"</h6></td>";
					
					
					reviewBoardModal += "					</tr>";
					reviewBoardModal += "					<tr>";
					reviewBoardModal += "						<td class='tg-0316' style='padding: 3px; text-align: left;'><h4>"+brandName+"</h4></td>";
					reviewBoardModal += "					</tr>";
					reviewBoardModal += "					<tr>";
					reviewBoardModal += "						<td class='tg-0317' style='padding: 3px; text-align: left;'><h6>"+productName+"</h6></td>";
					reviewBoardModal += "					</tr>";
					reviewBoardModal += "					<tr>";
					reviewBoardModal += "						<td class='tg-0317' style='padding: 3px; text-align: left;'><h6>"+colorNameOfProduct+"</h6></td>";
					reviewBoardModal += "					</tr>";
					reviewBoardModal += "				</table>";
					reviewBoardModal += "        </div>";
					reviewBoardModal += "        <div class='modal-footer'>";
					
					reviewBoardModal += "  <table>";
					reviewBoardModal += "  <tr>";
					reviewBoardModal += "  <td><td><td></td><td></td>";
					reviewBoardModal += "  </tr>";
					
					reviewBoardModal += "  <tr style='border-top:2px dotted black;'>";
					reviewBoardModal += "<td align='left'>" + $('#str_loggedName').text() + "의 댓글</td><td></td><td></td></tr>";					
					reviewBoardModal += "<tr style='border-bottom:2px dotted black'><td><input type='text' id='comment'  style='width:350px; color:black;'>";
					reviewBoardModal += "  <input type='button' id='commentButton' value='댓글 쓰기' style='color:black;'></td>"
					reviewBoardModal += "  </tr>";

					reviewBoardModal += "  <tr style='height:10px;'></tr>";
					reviewBoardModal += "  <tr id='commentList'>";
					reviewBoardModal += "  <td style='border:2px solid white;'>";
					reviewBoardModal += "  <input type='button' id='commentListOk' value='댓글 보기' style='width:100%; color:black;'><br>";
					reviewBoardModal += "  <table id ='commentListView' style='width:100%; color:white;'>";
					reviewBoardModal += "  </table></td></tr>";
					
					reviewBoardModal += "  </table>";
					
					
					if($('#str_loggedName').html() != null) {
					
						var html = $('#str_loggedName').html();
						var userName = html.substring(0, html.indexOf('님'));
						
						if(userName == writer) {
							reviewBoardModal += "        	<a href='reviewBoardModify.board?seq="+seq+"' class='btn btn-default'> 수정</a>";
							reviewBoardModal += "			<a href='reviewBoardDelete_ok.board?seq="+seq+"' class='btn btn-default'> 삭제</a>";	//그냥 삭제는 됨 (로그인 안하고)
						} else {
							//로그인은 되어 있으나, 작성자가 아님.
						}
						
					} else {
						//로그인 하지 않음
					}
					
					
					reviewBoardModal += "		</div>";
					reviewBoardModal += "      </div>";
					reviewBoardModal += "    </div>";
					reviewBoardModal += "  </div>";
					
	

					$('#reviewModalList').append(reviewBoardModal);
				}

				if(flag == 0) {
					
//					alert('Review 게시글 불러오기를 성공했습니다.');
					
					$('#commentButton').click(function(){											
						if($('#comment').val().trim() == "") {
							alert('댓글을 입력해주세요');
						} else {
							commentOk();	
						}
						
					});
					
					$('#commentListOk').click(function(){						
						commentList();
					});
					
					$('#reviewModalList > div').modal('show');
					
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
	
	
	$('#a_reviewBoardList_More').click(function(){
		
		if(cPage+1 <= totalPage) {
			initReviewBoardList(cPage+1);
		} else {
			alert('더이상 후기글이 없습니다.');
		}
		
	});
	
	

	var memNo = '';
	var comment = '';
	
	var boardType = '';
	
	//댓글 작성
	$('#commentButton').click(function(){			
		commentOk();				

	});
	
	var commentOk = function() {		

		memNo = $('input[id="str_loggedMemNo"]').val();
		comment = $('input[id="comment"]').val();			
		boardType = "reviewboard";
		//alert(memNo + comment + grp + boardType);	
		$.ajax({
			url:'./commentOk.board',
			type: 'POST',
			data: {
				memNo: memNo,
				comment: comment,
				grp: grp,
				boardType : boardType
			},
			dataType: 'xml',
			success: function(xml) {
				
				//console.log(xml);
			
				var flag = $(xml).find('flag').text();
				
				if(flag == 0) {		
					commentList();
					alert('댓글을 작성했습니다.');									
					
				} else if(flag == 1){
					alert('댓글 작성에 실패했습니다.');
				}
			},
			error: function(xhr, status, error) {
				//alert('에러 : ' + status + '\n\n' + error);
			}
		});
	}

	$('#commentListOk').click(function(){
		
		commentList();
		//alert(memNo + comment + grp + boardType);
	});
	
	var buttonHide = function() {
		var hideBox = '';
		hideBox += '<tr><input type="button" id="commentListRemove" value="댓글 접기" style="width:100%; color:black;"></tr>';
		$('input[id="commentListView"]').append(hideBox);
	}
	
	var commentList = function() {
		
		boardType = "reviewboard";
		
		$.ajax({
			url:'./commentList.board',
			type: 'POST',
			data: {				
				grp: grp,
				boardType : boardType
			},
			dataType: 'json',
			success: function(data) {
				
				var no = '';
				var comment = '';
				var name = '';
				var wdate = '';				
				var commentBox = '';
				
				for(var i=0; i < data.length; i++) {
					$('#commentListView').empty();
					$('#commentListOk').hide();
					
					//alert(no + comment + memNo + wdate);
					for(var key in data[i]) {
						
						switch(key) {
							case "no":
								no = Number(data[i][key]);
								break;
							case "comment":
								comment = data[i][key];
								break;
							case "name":
								name = data[i][key];
								break;
							case "wdate":
								wdate = data[i][key];
								break;
							default:
								break;
						}
					}
					
					commentBox += '<tr style="color:black">';
					commentBox += '<td>' + name + '</td>' + '<td>' + comment + '</td>' + '<td align="right">' + wdate + '</td>';
					commentBox += '</tr>';					
					$('#commentListView').append(commentBox);
				}
			
				buttonHide();

	
			},
			error: function(xhr, status, error) {
				alert('에러 : ' + status + '\n\n' + error);
			},
			complete:function(xhr, status){//error, success 상관 없이 실행 되는 부분
			}
		});
	};
	
	
	
});

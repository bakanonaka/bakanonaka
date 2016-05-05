	
$(document).ready(function() {

	$(document).on('click', '#a_requestBoardView_Delete', function() {
		
		requestBoardDeleteFromDB();
		
	});

	var requestBoardDeleteFromDB = function() {
		
		var memNo = $('#str_loggedMemNo').val();
		var seq = $('input[name="seq"]').val();
		
		$.ajax({
			url:'requestBoardDelete_ok.board',
			type: 'POST',
			data: {
				seq: seq,
				memNo: memNo
			},
			dataType: 'xml',
			success: function(xml) {
				
//				console.log(xml);
			
				var flag = $(xml).find('flag').text();
				
				if(flag == 0) {
					
					alert('글 삭제에 성공했습니다.');
					
					top.location.href ='requestBoard.board';
					
				} else if(flag == 1){
					alert('글 삭제에 실패했습니다.');
				}
			},
			error: function(xhr, status, error) {
				alert('에러 : ' + status + '\n\n' + error);
			}
		});
		
	};
	
	
	//로그인 여부 확인
	$('#a_requestBoardView_Write').click(function(){
		
		if($('#str_loggedMemNo').val() == null) {
			alert('로그인 이후에 글을 쓸 수 있습니다.');
		}else {
			top.location.href ='requestBoardWrite.board';
		}
	});
	

	var memNo = '';
	var comment = '';
	var grp = '';
	var boardType = '';
	
	//댓글 작성
	$('#commentButton').click(function(){
		if($('#comment').val().trim() == "") {
			alert('댓글을 입력해주세요');
		} else {
			commentOk();	
		}	

	});
	
	var commentOk = function() {		

		memNo = $('input[id="currentUser"]').val();
		comment = $('input[id="comment"]').val();
		grp = $('input[name="seq"]').val();
		boardType = "requestboard";
				
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
		grp = $('input[name="seq"]').val();
		boardType = "requestboard";
		
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
					
					commentBox += '<tr>';
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

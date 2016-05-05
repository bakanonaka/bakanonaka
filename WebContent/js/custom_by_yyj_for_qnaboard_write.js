	
$(document).ready(function() {

	$(document).on('click', '#a_qnaBoard_Write', function() {
		
		var subject = $('input[name="subject"]').val();
//		var content = $('textarea').text();
		
		//SmartEditor로 추가된 부분
		oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
//		alert(document.getElementById("content").value);
		//-------------------------------
		
		var content = document.getElementById("content").value;

		var selectEvent = $('select[name="eventNo"]').val();
		
		
		if(subject != "") {
			
			if(content != "") {
				
				if(selectEvent != null && selectEvent != "") {
					qnaBoardWriteToDB();
				} else {
					alert('선택한 이벤트가 없습니다.');
				}
				
			} else {
				alert('내용을 입력해주세요.');
			}
			
		}else {
			alert('제목을 입력해주세요.');
		}
	});

	
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: "content",
		sSkinURI: "smartEditor20/SmartEditor2Skin.html",
		fCreator: "createSEditor2"
	});

	var qnaBoardWriteToDB = function() {
		
		var memNo = $('#str_loggedMemNo').val();
		
		var subject = '';
		var eventNoName = '';
		var content = '';
		
		subject = $('input[name="subject"]').val();
		eventNoName = $('select[name="eventNo"]').find('option:selected').val();

		//		content = $('textarea').text();
		content = document.getElementById("content").value;
		
		$.ajax({
			url:'qnaBoardWrite_ok.board',
			type: 'POST',
			data: {
				memNo: memNo,
				subject: subject,
				eventNoName: eventNoName,
				content : content
			},
			dataType: 'xml',
			success: function(xml) {
				
				console.log(xml);
			
				var flag = $(xml).find('flag').text();
				
				if(flag == 0) {
					
					alert('글쓰기에 성공했습니다.');
					
					top.location.href ='qnaBoard.board';
					
				} else if(flag == 1){
					alert('글쓰기에 실패했습니다.');
				}
			},
			error: function(xhr, status, error) {
				alert('에러 : ' + status + '\n\n' + error);
			}
		});
		
	};

});

	
$(document).ready(function() {

	$(document).on('click', '#a_qnaBoard_Modify', function() {
		
		
		var subject = $('input[name="subject"]').val();
//		var content = $('textarea').text();
		
		//SmartEditor로 추가된 부분
		oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
//		alert(document.getElementById("content").value);
		//-------------------------------
		
		var content = document.getElementById("content").value;
		
		if(subject != "") {
			
			if(content != "") {
				qnaBoardModifyToDB();
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
	
	var qnaBoardModifyToDB = function() {
		
		var seq = '';
		var subject = '';
		var eventNoName = '';
		var content = '';
		
		seq = $('input[name="seq"]').val();
		subject = $('input[name="subject"]').val();
		eventNoName = $('select[name="eventNo"]').find('option:selected').val();
		
//		content = $('textarea').text();
		content = document.getElementById("content").value;
		
		$.ajax({
			url:'qnaBoardModify_ok.board',
			type: 'POST',
			data: {
				seq: seq,
				subject: subject,
				eventNoName: eventNoName,
				content : content
			},
			dataType: 'xml',
			success: function(xml) {
				
				console.log(xml);
			
				var flag = $(xml).find('flag').text();
				
				if(flag == 0) {
					
					alert('글 수정에 성공했습니다.');
					
					top.location.href ='qnaBoard.board';
					
				} else if(flag == 1){
					alert('글 수정에 실패했습니다.');
				}
			},
			error: function(xhr, status, error) {
				alert('에러 : ' + status + '\n\n' + error);
			}
		});
		
	};
	
});

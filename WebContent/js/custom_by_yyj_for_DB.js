	
$(document).ready(function() {

	$(document).on('click', '#button_InputProductDataToDB', function() {
		
		callInputProductMethodForDB();
		
	});

	// 
	var callInputProductMethodForDB = function() {
		
		$.ajax({
			url:'inputProductDataOk.board',
			type: 'POST',
			dataType: 'xml',
			success: function(xml) {
				
//				console.log(xml);
			
				var flag = $(xml).find('flag').text();
				
				if(flag == 0) {
					alert('화장품 데이터 DB 입력에 성공했습니다.');
				} else if(flag == 1){
					alert('화장품 데이터 DB 입력에 실패했습니다.');
				}
			},
			error: function(xhr, status, error) {
				alert('에러 : ' + status + '\n\n' + error);
			}
		});
	};
	
});

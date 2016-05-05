	
$(document).ready(function() {
	
	// 체크된 값 찾기
//	if($(':radio[class="modify_likeColorflag"]:checked').val() == 1) {
//	}
	
	
	//컬러 피커 관련 내용 전부 가림
	$('#modify_seosonRidioLabel').hide();
	
//	$('#modify_SelectedColor').hide();

	$('#modify_selectorList').hide();
	$('#modify_spring').hide();
	$('#modify_summer').hide();
	$('#modify_fall').hide();
	$('#modify_winter').hide();
	
	//선호 색상 선택 라디오 초기화 및 클릭 이벤트 등록  
	$('.modify_likeColorflag').each(function(){	
		
		if($(this).val() == 0) {	//초기화. 색상 선택하지 않음
//			$(this).attr('checked', 'checked');	
			$(this).click();
		}
		
		$(this).click(function(){	//클릭 이벤트 추가

			//전부 미체크 하고 지금 클릭한 것 체크 하기.
//			$('.modify_likeColorflag').each(function(){
//				$(this).attr('checked','');
//			});
//			
//			$(this).attr('checked', 'checked');
			
			// 클릭한 것에 따라 화면에 보이기 
			if($(this).val() == 1) {
				
				$('#modify_seosonRidioLabel').show();
//				$('#modify_SelectedColor').show();
				$('#modify_selectorList').show();
				
			} else {// 비선택 클릭시
				
				$('#modify_seosonRidioLabel').hide();
//				$('#modify_SelectedColor').hide();
				$('#modify_selectorList').hide();
			}	
		});
	
	});
	
	//계절 선택 라디오 초기화 및 클릭 이벤트 등록  
	$('.modify_selectSeasonflag').each(function(){	
		
//		if($(this).val() == 'spring') {	//초기화. 봄 계절 선택
//			$(this).attr('checked', 'checked');
//		}
		
		
		$(this).click(function(){	//클릭 이벤트 추가

			//전부 미체크 하고 지금 클릭한 것 체크 하기.
//			$('.modify_selectSeasonflag').each(function(){
//				$(this).attr('checked','');
//			});
//			
//			$(this).attr('checked', 'checked');
			
			
			$('#modify_spring').hide();
			$('#modify_summer').hide();
			$('#modify_fall').hide();
			$('#modify_winter').hide();
			
			var value = 0;
			var color = '';
			var season = '';
			var title = '';
			
			// 클릭한 것에 따라 화면에 보이기
			switch($(this).val()) {
				case 'spring':
//					alert('봄');
					$('#modify_spring').show();
					$('#modify_colorselector_1').colorselector({
				        callback : function(value, color, title, season) {
				          $("#modify_colorValue").val(value).css("color", 'black');
				          $("#modify_colorColor").val(color).css("color", 'black');
				          $("#modify_colorTitle").val(title).css("color", 'black');
				          $("#modify_colorSeason").val(season).css("color", 'black');
				        }
				      });
					
					value = $('#modify_colorselector_1').val();
					color = $('#modify_colorselector_1').find("option[value='" + value + "']").data("color");
			        season = $('#modify_colorselector_1').find("option[value='" + value + "']").data("season");
			        title = $('#modify_colorselector_1').find("option[value='" + value + "']").text();
//			        $('#modify_SelectedColor').css("background-color", color);
			        
					break;
				case 'summer':
//					alert('여름');
					$('#modify_summer').show();
					$('#modify_colorselector_2').colorselector({
				        callback : function(value, color, title, season) {
				          $("#modify_colorValue").val(value).css("color", 'black');
				          $("#modify_colorColor").val(color).css("color", 'black');
				          $("#modify_colorTitle").val(title).css("color", 'black');
				          $("#modify_colorSeason").val(season).css("color", 'black');
				        }
				      });
					
					value = $('#modify_colorselector_2').val();
					color = $('#modify_colorselector_2').find("option[value='" + value + "']").data("color");
			        season = $('#modify_colorselector_2').find("option[value='" + value + "']").data("season");
			        title = $('#modify_colorselector_2').find("option[value='" + value + "']").text();
//			        $('#modify_SelectedColor').css("background-color", color);
			        
					break;
				case 'fall':
//					alert('가을');
					$('#modify_fall').show();
					$('#modify_colorselector_3').colorselector({
				        callback : function(value, color, title, season) {
				          $("#modify_colorValue").val(value).css("color", 'black');
				          $("#modify_colorColor").val(color).css("color", 'black');
				          $("#modify_colorTitle").val(title).css("color", 'black');
				          $("#modify_colorSeason").val(season).css("color", 'black');
				        }
				      });
					
					value = $('#modify_colorselector_3').val();
					color = $('#modify_colorselector_3').find("option[value='" + value + "']").data("color");
			        season = $('#modify_colorselector_3').find("option[value='" + value + "']").data("season");
			        title = $('#modify_colorselector_3').find("option[value='" + value + "']").text();
//			        $('#modify_SelectedColor').css("background-color", color);
			        
					break;
				case 'winter':
//					alert('겨울');
					$('#modify_winter').show();
					$('#modify_colorselector_4').colorselector({
				        callback : function(value, color, title, season) {
				          $("#modify_colorValue").val(value).css("color", 'black');
				          $("#modify_colorColor").val(color).css("color", 'black');
				          $("#modify_colorTitle").val(title).css("color", 'black');
				          $("#modify_colorSeason").val(season).css("color", 'black');
				        }
				      });
					
					value = $('#modify_colorselector_4').val();
					color = $('#modify_colorselector_4').find("option[value='" + value + "']").data("color");
					season = $('#modify_colorselector_4').find("option[value='" + value + "']").data("season");
					title = $('#modify_colorselector_4').find("option[value='" + value + "']").text();
//			        $('#modify_SelectedColor').css("background-color", color);
			        
					break;
			}
//			alert('클릭 : ' + value +' '+ color +' '+ season)
			
			//클릭했을 때, 값 넣어주기.
			$('#modify_colorValue').val(value);
			 $('#modify_colorColor').val(color);
			 $('#modify_colorSeason').val(season);
			 $('#modify_colorTitle').val(title);

		});
	
	});

	
});

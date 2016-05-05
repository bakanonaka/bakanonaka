	
$(document).ready(function() {
	
	$('#productsorting_colorselector_1').colorselector({
        callback : function(value, color, title, season) {
          $("#productsorting_colorValue").val(value).css("color", 'black');
          $("#productsorting_colorColor").val(color).css("color", 'black');
          $("#productsorting_colorTitle").val(title).css("color", 'black');
          $("#productsorting_colorSeason").val(season).css("color", 'black');
        }
      });
	
	$('#productsorting_colorselector_2').colorselector({
        callback : function(value, color, title, season) {
          $("#productsorting_colorValue").val(value).css("color", 'black');
          $("#productsorting_colorColor").val(color).css("color", 'black');
          $("#productsorting_colorTitle").val(title).css("color", 'black');
          $("#productsorting_colorSeason").val(season).css("color", 'black');
        }
      });
	
	$('#productsorting_colorselector_3').colorselector({
        callback : function(value, color, title, season) {
          $("#productsorting_colorValue").val(value).css("color", 'black');
          $("#productsorting_colorColor").val(color).css("color", 'black');
          $("#productsorting_colorTitle").val(title).css("color", 'black');
          $("#productsorting_colorSeason").val(season).css("color", 'black');
        }
      });
	
	$('#productsorting_colorselector_4').colorselector({
        callback : function(value, color, title, season) {
          $("#productsorting_colorValue").val(value).css("color", 'black');
          $("#productsorting_colorColor").val(color).css("color", 'black');
          $("#productsorting_colorTitle").val(title).css("color", 'black');
          $("#productsorting_colorSeason").val(season).css("color", 'black');
        }
      });

	
//	<div id="productsorting_selectorList">
//	<div id="productsorting_spring" style="float: left; width: 25%;">
//	<h4>봄 색상</h4>
//		<select id="productsorting_colorselector_1">
//		<div class = "dropdown dropdown-colorselector">
//			<a data-toggle = "dropdown" class = "dropdown-toggle" href = "#"><span class = "btn-colorselector" style = "background-color:#FFFFFF"></span></a>
//	      		<ul class = "dropdown-menu dropdown-caret">
//	      			<li>
	//클릭 이벤트 추가
	$('#productsorting_selectorList > #productsorting_spring > #productsorting_colorselector_1').next().click(function(){

//		alert('spring');
			var value = $('#productsorting_colorselector_1').val();
			var color = $('#productsorting_colorselector_1').find("option[value='" + value + "']").data("color");
	        var season = $('#productsorting_colorselector_1').find("option[value='" + value + "']").data("season");
	        var title = $('#productsorting_colorselector_1').find("option[value='" + value + "']").text();
	        
	        $('#productsorting_colorValue').val(value);
			 $('#productsorting_colorColor').val(color);
			 $('#productsorting_colorSeason').val(season);
			 $('#productsorting_colorTitle').val(title);
	});
	

	$('#productsorting_selectorList > #productsorting_summer > #productsorting_colorselector_2').next().click(function(){
		
//		alert('summer');
		var value = $('#productsorting_colorselector_2').val();
		var color = $('#productsorting_colorselector_2').find("option[value='" + value + "']").data("color");
        var season = $('#productsorting_colorselector_2').find("option[value='" + value + "']").data("season");
        var title = $('#productsorting_colorselector_2').find("option[value='" + value + "']").text();
        
        $('#productsorting_colorValue').val(value);
		 $('#productsorting_colorColor').val(color);
		 $('#productsorting_colorSeason').val(season);
		 $('#productsorting_colorTitle').val(title);
	});
	
$('#productsorting_selectorList > #productsorting_fall > #productsorting_colorselector_3').next().click(function(){
		
//		alert('fall');
		var value = $('#productsorting_colorselector_3').val();
		var color = $('#productsorting_colorselector_3').find("option[value='" + value + "']").data("color");
        var season = $('#productsorting_colorselector_3').find("option[value='" + value + "']").data("season");
        var title = $('#productsorting_colorselector_3').find("option[value='" + value + "']").text();
        
        $('#productsorting_colorValue').val(value);
		 $('#productsorting_colorColor').val(color);
		 $('#productsorting_colorSeason').val(season);
		 $('#productsorting_colorTitle').val(title);
	});

$('#productsorting_selectorList > #productsorting_winter > #productsorting_colorselector_4').next().click(function(){
	
//	alert('winter');
	var value = $('#productsorting_colorselector_4').val();
	var color = $('#productsorting_colorselector_4').find("option[value='" + value + "']").data("color");
    var season = $('#productsorting_colorselector_4').find("option[value='" + value + "']").data("season");
    var title = $('#productsorting_colorselector_4').find("option[value='" + value + "']").text();
    
    $('#productsorting_colorValue').val(value);
	 $('#productsorting_colorColor').val(color);
	 $('#productsorting_colorSeason').val(season);
	 $('#productsorting_colorTitle').val(title);
});
	
//a_ColorPickerProductSorting 이벤트는 productSorting.js 에 있음

});

/*
 * A colorselector for Twitter Bootstrap which lets you select a color from a predefined set of colors only.
 * https://github.com/flaute/bootstrap-colorselector
 *
 * Copyright (C) 2014 Flaute
 *
 * Licensed under the MIT license
 */

(function($) {
  "use strict";

  var ColorSelector = function(select, options) {
    this.options = options;
    this.$select = $(select);
    this._init();
  };

  ColorSelector.prototype = {

    constructor : ColorSelector,

    _init : function() {

      var callback = this.options.callback;

      var selectValue = this.$select.val();
      var selectColor = this.$select.find("option:selected").data("color");

      var $markupUl = $("<div>").addClass("dropdown-menu").addClass("dropdown-caret");
      var $markupDiv = $("<div>").addClass("dropdown").addClass("dropdown-colorselector").css('text-align', 'center');
      var $markupSpan = $("<span>").addClass("btn-colorselector").css("background-color", selectColor);
      var $markupA = $("<a>").attr("data-toggle", "dropdown").addClass("dropdown-toggle").attr("href", "#").append($markupSpan);

      //<ul class = "dropdown-menu dropdown-caret"></ul>
      //<div class = "dropdown dropdown-colorselector"></div>
      //<a data-toggle = "dropdown" class = "dropdown-toggle" href = "#"><span class = "btn-colorselector" style = "background-color:#FFFFFF"></span></a>
      
      // create an li-tag for every option of the select 옵션의 모든 값을 태그의 속성 값으로 넣는다.
      $("option", this.$select).each(function(index) {
    	  
        var option = $(this);//<option value="0" data-color="#FFFFFF">spring</option>
        var value = option.attr("value");//0
        var color = option.data("color");//data-color
        var season = option.data("season");//data-season
        var title = option.text();

        // create a-tag
        var $markupA = $("<a>").addClass("color-btn");//<a class = "color-btn"></a>
        if (option.prop("selected") === true || selectValue === color) {//selected="selected"
          $markupA.addClass("selected");//<a class = "color-btn selected"></a>
        }
        
        $markupA.css("background-color", color);//<a class = "color-btn selected" style = "background-color:#FFFFFF;"></a>
        $markupA.attr("href", "#").attr("data-color", color).attr("data-value", value).attr("title", title).attr("data-season", season);
        //<a class = "color-btn selected" style = "background-color:#FFFFFF;" href="#" data-color="#FFFFFF" data-value = "1" title = "spring"></a>
        
        // create li-tag
      //<ul class = "dropdown-menu dropdown-caret"><li><a class = "color-btn selected" style = "background-color:#FFFFFF;" href="#" data-color="#FFFFFF" data-value = "1" title = "spring"></a></li></ul>
        
        $markupUl.append($("<div>").addClass('colorList').append($markupA));
      });

      // append the colorselector
      $markupDiv.append('<h5>세부 색상 선택</h5>');
      $markupDiv.append($markupA);
      // append the colorselector-dropdown
      $markupDiv.append($markupUl);

      //<div class = "dropdown dropdown-colorselector">
      //<a data-toggle = "dropdown" class = "dropdown-toggle" href = "#"><span class = "btn-colorselector" style = "background-color:#FFFFFF"></span></a>
//      <ul class = "dropdown-menu dropdown-caret">
//      <li>
//      <a class = "color-btn selected" style = "background-color:#FFFFFF;" href="#" data-color="#FFFFFF" data-value = "1" title = "spring"></a>
//		...
//      </li>
//      </ul>
      //</div>
      
      // hide the select
      this.$select.hide();
      
      // insert the colorselector
      this.$selector = $($markupDiv).insertAfter(this.$select);

      // register change handler
      this.$select.on("change", function() {

        var value = $(this).val();
        var color = $(this).find("option[value='" + value + "']").data("color");
        var title = $(this).find("option[value='" + value + "']").text();
        var season = $(this).find("option[value='" + value + "']").data("season");

        // remove old and set new selected color
        $(this).next().find(".dropdown-menu").find(".colorList").find(".selected").removeClass("selected");
        $(this).next().find(".dropdown-menu").find(".colorList").find("a[data-color='" + color + "']").addClass("selected");

        $(this).next().find(".btn-colorselector").css("background-color", color);
        
        //추가 선택한 컬러 표시.
//        $('#register_SelectedColor').css("background-color", color);
//        $('#modify_SelectedColor').css("background-color", color);
        
        callback(value, color, title, season);
      });

      // register click handler
      $markupUl.on('click.colorselector', $.proxy(this._clickColor, this));
    },

    _clickColor : function(e) {

      var a = $(e.target);

      if (!a.is(".color-btn")) {
        return false;
      }

      this.$select.val(a.data("value")).change();

      e.preventDefault();
      return true;
    },

    setColor : function(color) {
      // find value for color
      var value = $(this.$selector).find(".colorList").find("a[data-color='" + color + "']").data("value");
      this.setValue(value);
    },

    setValue : function(value) {
      this.$select.val(value).change();
    },

  };

  $.fn.colorselector = function(option) {
    var args = Array.apply(null, arguments);
    args.shift();

    return this.each(function() {

      var $this = $(this), data = $this.data("colorselector"), options = $.extend({}, $.fn.colorselector.defaults, $this.data(), typeof option == "object" && option);

      if (!data) {
        $this.data("colorselector", (data = new ColorSelector(this, options)));
      }
      if (typeof option == "string") {
        data[option].apply(data, args);
      }
    });
  };

  $.fn.colorselector.defaults = {
    callback : function(value, color, title) {
    },
    colorsPerRow : 7
  };

  $.fn.colorselector.Constructor = ColorSelector;

})(jQuery, window, document);

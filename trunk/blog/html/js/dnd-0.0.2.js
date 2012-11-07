$(document).ready(function(){
	var isDnd = false;
	var dndElement = null;
	var shadow = null;
	var shadowDragAndDropLayer = null;
	
	var map = {};

	$(".dragable").bind('mousedown', function(){
		dndElement = $(this);
		
		$("html").bind('mousemove', function(e){
			if(!isDnd){
				setDnd();
			}
		});
	});
	
	$(".post-dropable").bind('hover', function(){
		if(!isDnd)			return;
		if(!isPostDrag()) 	return;
		
		shadow.appendTo($(this));
	});
	
	$(".post-dropable").mouseup(function(){
		if(!isDnd)			return;
		if(!isPostDrag()) 	return;
		
		dropTo($(this));
	});
	
	$("html").mouseup(function(){
		isDnd = false;
		dndElement = null;
		if(shadow != null){
			shadow.remove();
			shadow = null;	
		}
		
		if(shadowDragAndDropLayer != null){
			shadowDragAndDropLayer.remove();
			shadowDragAndDropLayer = null;
		}
		
		$("#dragAndDropLayer").remove();
		$("html").unbind('mousemove');
	});
	
	function setDnd(){
		isDnd = true;
		
		if(!isPostDrag()){
			if(dndElement.parent().hasClass("category")){
				dndElement = dndElement.parent();
				categoryDragAndDropActive();
			}
		}
		
		dndElement.hide();
		
		if(isPostDrag()){
			shadow = $("<div>").css('width', "200")
				.css('height', "300")
				.css('margin', dndElement.css('margin'))
				.css('padding', dndElement.css('padding'))
				.css('position', dndElement.css('position'))
				.css("float", "left")
				.css('background-color', '#888');			
		}		
	}

	function isPostDrag(){
		return dndElement.hasClass("post");
	}
	
	function isCategoryDrag(){
		return dndElement.hasClass("category");
	}
	
	function dropTo(dropable){
		dropable.append(dndElement);
		dndElement.css('position', 'relative');
	}
	
	function categoryDragAndDropActive(){
		var contents = $(".category-dropable");
			
		var offsetX = contents.offset().left - parseInt(contents.css("margin-left").replace("px", ""));
		var offsetY = contents.offset().top - parseInt(contents.css("margin-top").replace("px", ""));
		
		var layer = $("<div>")
			.attr("id", "dragAndDropLayer")
			.addClass("contents")
			.css("z-index", "10")
			.css("position", "absolute")
			.css("left", offsetX)
			.css("top", offsetY)
			.css("opacity", "0.3")
			.appendTo($("body"));
	
		$.each(contents.children(), function(index){
			appendEventLayer(index, $(this), layer);		
		});
		
		layer.bind('mouseup', function(){
			changeShadowToDNDElement();
		});
		
		shadow = $("<div>").addClass("category")
			.css('margin', dndElement.css('margin'))
			.css('padding', dndElement.css('padding'))
			.css('position', dndElement.css('position'))
			.css('background-color', '#888');
	}
	
	function appendEventLayer(index, category, layer){	
		var currentDiv = category;
		if(currentDiv.attr("id") == dndElement.attr("id"))	return;
			
		var leftWidth = (currentDiv.width()/2) + parseInt(currentDiv.css("margin-left").replace("px", ""));
		var leftHeight = (currentDiv.height())
			+ parseInt(currentDiv.css("margin-top").replace("px", ""))
			+ parseInt(currentDiv.css("margin-bottom").replace("px", ""));
		
		var leftKey = "category-dropable-left" + index;
		var left = $("<div>").attr("id", leftKey)
			.css("width", leftWidth)
			.css("height", leftHeight)
			.css("float", "left")
			.css("background-color", "#FFF")
			.appendTo(layer);
			
		map[leftKey] = currentDiv;
		
		var rightWidth = (currentDiv.width()/2) + parseInt(currentDiv.css("margin-right").replace("px", ""));
		
		var rightKey = "category-dropable-right" + index;
		var right = $("<div>").attr("id", rightKey)
			.css("width", rightWidth)
			.css("height", leftHeight)
			.css("float", "left")
			.appendTo(layer);
			
		map[rightKey] = currentDiv;
		
		shadowDragAndDropLayer = $("<div>").attr("id", "shadowDragAndDropLayer")
			.css("width", rightWidth + leftWidth)
			.css("height", leftHeight)
			.css("float", "left")
			.css("background-color", "#F00")
			.css("opacity", "0.5");
		
		left.bind('hover', function(){
			var findDiv = map[$(this).attr("id")];
			findDiv.before(shadow);
			$(this).before(shadowDragAndDropLayer);
		});
		
		right.bind('hover', function(){
			var findDiv = map[$(this).attr("id")];
			findDiv.after(shadow);
			$(this).after(shadowDragAndDropLayer);
		});
	}
	
	function changeShadowToDNDElement(){
		shadow.replaceWith(dndElement);
		dndElement.fadeIn(200);
	}
});

























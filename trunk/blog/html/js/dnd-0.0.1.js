$(document).ready(function(){
	var dragAndDrop = {
		element : null,
		referenceElement : null,
		isParent : false
	};
						
	$('.dragable').mousedown(function(e){
		setDragAndDrop($(this));				
		
		$('.dropable').mouseup(function(e){
			if(dragAndDrop == null)		return false;
			dropTo($(this), e);			
		});
		
		
		$("html").mouseup(function(){
			if(dragAndDrop == null)		return false;
			comeBackToOriginalLocation();
		});				
	});
	
	function setDragAndDrop(element){			
		if(element.parent().children().first().is(element)){
			dragAndDrop = {
				element : element,
				referenceElement : element.parent(),
				isParent : true
			}
		}else{
			dragAndDrop = {
				element : element,
				referenceElement : element.prev()
			}
		}
		
		element.detach();
	}
	
	function dropTo(dropable, e){
		var categoryOffsetTop = dropable.offset().top;
		var realMousePosition = e.pageY - categoryOffsetTop;
		
		var beforeDiv;
		dropable.children().each(function(index){
			var divHeight = ($(this).offset().top - categoryOffsetTop) + $(this).height();
			
			if(divHeight > realMousePosition){
				if(beforeDiv != null)				beforeDiv.after(dragAndDrop.element);
				else								$(this).before(dragAndDrop.element);
				
				clearDragAndDrop();
				return false;
			}
			
			beforeDiv = $(this);
		});
		
		if(dragAndDrop != null){
			if(dropable.children().length > 1){
				dropable.children().last().after(dragAndDrop.element);
			}else{
				dropable.append(dragAndDrop.element);
			}
			
			clearDragAndDrop();
		}	
	}
	
	function comeBackToOriginalLocation(){
		if(dragAndDrop != null && dragAndDrop.element != null){
			if(dragAndDrop.isParent){
				var parent = dragAndDrop.referenceElement;
				parent.append(dragAndDrop.element);
				
				if(parent.children().length > 1){
					parent.children().first().before(parent.children().last());
				}

			}else{
				dragAndDrop.referenceElement.after(dragAndDrop.element);
			}
			
			clearDragAndDrop();
		}
	}
	
	function clearDragAndDrop(){
		dragAndDrop = null;
			
		$(".dropable").unbind("mouseup");
		$("html").unbind("mouseup");
	}			
});
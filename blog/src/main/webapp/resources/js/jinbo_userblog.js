$(document).ready(function(){
	$(".nav-title").click(function(){
		var navTitle = $(this);
		var navMenu = $(this).next();
		
		$.each($(".nav-menu"), function(){
			if($(this).prev().html() === navTitle.html()){
				return true;
			}
			
			$(this).slideUp();
		});
		
		var moveToLeft = navMenu.css("width").replace("px", "") - $(this).css("width").replace("px", "");
		
		var left = $(this).offset().left - moveToLeft;
		navMenu.css("left", left + "px");
		
		navMenu.slideToggle();
	});
	
	$(".notification-nav-title").click(function(){
		$(this).next().slideToggle();
	});
	
	$(".postSimple").click(function(){
		$(this).hide();
		$(this).next().slideDown();
	});
	
	$(".postFold").bind("click", function(){
		var $post = $(this).parent().parent();
		$post.slideUp(function(){
			$post.prev().show();	
		});		
	});
	
	$("#postFoldAll").click(function(){
		$.each($(".postFold"), function(){
			$(this).trigger('click');
		});
	});
	
	$("#postOpenAll").click(function(){
		$.each($(".postSimple"), function(){
			$(this).trigger('click');
		});
	});
	
	$(".btnPostActionComment").bind("click", function(){		
		var postActionContents = $(this).parent().next();
		
		$.each(postActionContents.find(".postActionTrackback"), function(){
			$(this).hide();
		});
		
		$.each(postActionContents.find(".postActionComment"), function(){
			$(this).show();
		})
		
		$.each($(".postActionButtonSelected"), function(){
			$(this).removeClass("postActionButtonSelected");
		});
		
		$(this).addClass("postActionButtonSelected");
	});
	
	$(".btnPostActionTrackback").bind("click", function(){		
		var postActionContents = $(this).parent().next();
		$.each(postActionContents.find(".postActionComment"), function(){
			$(this).hide();
		})
		$.each(postActionContents.find(".postActionTrackback"), function(){
			$(this).show();
		});

		$.each($(".postActionButtonSelected"), function(){
			$(this).removeClass("postActionButtonSelected");
		});
		
		$(this).addClass("postActionButtonSelected");
	});

});
































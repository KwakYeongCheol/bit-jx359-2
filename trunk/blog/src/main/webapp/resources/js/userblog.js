$(document).ready(function() {
			/* 글보기 slide 이벤트 start  */
		/*	$(".postTitle").bind('click', function(){
				var contents = $(this).next();
				if(contents.html().length == 0){
				
					contents.toggle();
				}
			});*/
			/* 글보기 slide 이벤트 end  */
		
			/*글쓰기 버튼 event start  */
			$(".postWrite").hide();
			$(".writeBtn").click(function(){
				$(".postWrite").fadeIn();
			});
			$(".postWriteCloseBtn").click(function(){
				$(".postWrite").fadeOut();
			});
			/*글쓰기 버튼 event end  */
			
			/* 카테고리 모두보기 이벤트 start  */
			$(".showAllCateogory").click(function(){
				$(".showAllCategory").hide();
				$("#postList").hide(); 
				$("#categoryList").css("width", "80%");
			});
			/* 카테고리 모두보기 이벤트 end  */
			
			/* 카테고리 event start  */
			
			$("#categoryList .categoryBox").click(function(){
				var evSelect = $(this).hasClass("ev");
				$("#postList").show(); 
				$("#categoryList").css("width", "30%");
				if(evSelect == true){
						$("#postList .ev").show();
						
						var $temp = $(".extensionPost .ev").detach();
						var $reduction = $("#postList .categoryBox").detach();
						$("#sidemenu").append($reduction);
						$(".extensionPost").append($temp);
				}else { 
					if($("#postList .categoryBox").length == 1){			
						$("#postList .ev").hide();
						var $extension = $(this).detach();
						$(".extensionPost").prepend($extension);
					}else{
						var $temp = $(".extensionPost .ev").detach();
						var $reduction = $(".extensionPost .categoryBox");
						var $extension = $(this).detach();
						$(".extensionPost").prepend($extension);
						$("#sidemenu").append($reduction);
						$(".extensionPost").append($temp);
					}
				}
				if($("#postList .categoryBox").length == 1){
					$("#postList .ev").show();
				}
			});
			
			/* 카테고리 event end  */
			
			$(".nav-title").mouseover(function() {
				console.log($(this).html());
				var navMenu = $(this).next();
				navMenu.slideDown();

				$(this).parent().mouseleave(function() {
					navMenu.slideUp();
					$(this).unbind("mouseleave");

				});
			});
			
			$(".nav-notification").mouseover(function() {
				var navNotify = $(this).next();
				navNotify.slideDown();

				$(this).parent().mouseleave(function() {
					navNotify.slideUp();
					$(this).unbind("mouseleave");
				});
			});
});


$(document).ready(function() {
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
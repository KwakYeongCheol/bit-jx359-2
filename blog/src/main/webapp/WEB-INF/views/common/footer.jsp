<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	</section>
	
	<div style="clear:both;"></div>		
	<footer>
		JINBO Is Not Blog<br />
		created by 곽영철, 강현모, 민용기, 최준용
	</footer>
</div>
<script>
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
});
</script>
</body>
</html>
	</section>
	
	<div style="clear:both;"></div>		
	<footer>
		Hello~
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
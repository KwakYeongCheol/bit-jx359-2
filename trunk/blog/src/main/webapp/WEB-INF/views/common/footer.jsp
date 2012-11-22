	</section>
	
	<div style="clear:both;"></div>		
	<footer>
		Hello~
	</footer>
</div>
<script>
$(document).ready(function(){
	$(".nav-title").mouseover(function(){
		console.log($(this).html());
		var navMenu= $(this).next();
		navMenu.slideDown();
		

		$(this).parent().mouseleave(function(){
			navMenu.slideUp();
			$(this).unbind("mouseleave");
		});
	});
});
</script>
</body>
</html>
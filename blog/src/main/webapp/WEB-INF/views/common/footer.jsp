	</section>
		
	<footer>
		Hello~
	</footer>
</div>	
<script src="${pageContext.request.contextPath }/resources/js/jquery-1.8.2.js"></script>
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
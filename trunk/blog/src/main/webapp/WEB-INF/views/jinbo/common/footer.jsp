<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

		</div>
		<!-- contentsWrap end -->
	</section>
	
	<div style="clear:both;"></div>		
	<footer>
		Hello~
	</footer>
</div>

<script src="${pageContext.request.contextPath }/resources/js/jinbo_userblog.js"></script>
<script>
function openEditor(){
	popUp('${pageContext.request.contextPath }/${blog.id }/admin/post/editor');
}

function popUp(url){
	var popUpBackground = $("<div>").addClass("popUpBackground").click(clearPopUp);
	var popUpContents = $("<div>").addClass("popUpContents");
	
	$.ajax({
		url : url,
		type : 'GET',
		success : function(result){
			if(result != null){
				popUpContents.append(result);
				
				popUpBackground.appendTo($("body"));
				popUpContents.appendTo($("body"));
				
				var backgroundHeight = popUpBackground.css("height").replace("px", "");
				var contentsHeight = popUpContents.css("height").replace("px", "");
				
				if(contentsHeight > backgroundHeight){
					var height = parseInt(contentsHeight) + 200;
					popUpBackground.css("height", height + "px");
				}
			}
		},
		error : function(result){
			console.log('error');
		}
	});	
};

function clearPopUp(){
	$(".popUpBackground").remove();
	$(".popUpContents").remove();
}

$(document).ready(function(){
	$("#btnEditor").click(function(){
		openEditor();
		return false;
	});
});

</script>
</body>
</html>
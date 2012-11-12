<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<section id="categoryList">
				<input type="button" class="showAllCateogory" value="모아보기" />
				<div class="clear"></div>
				<div id="sidemenu">
					<div class="categoryBox ev">
						<h2>모든 글</h2>
						<div class="category">
							<c:forEach items="${postList }" var="post">
								<div class="post">
									${post.title } 
								</div>
							</c:forEach>
						</div>
					</div>
					<c:forEach items="${categoryList }" var="category">
					<div class="categoryBox">						
						<h2>${category.title }</h2>
							<c:forEach items="${category.postList }" var="post">
						<div class="category dropable">
								<div class="post dragable">
									<div class="postTitle">
										${post.title } 
									</div>
									<div class="postContents"> 
											${post.displayId } |
											${post.title } | 
											<spring:eval expression="post.dateCreated" /> |		
											공개여부 : ${post.isPublic } | 
											Version : ${post.currentRevision.displayId }
											<br /><br />
											
											${post.contents }
											<br />
											Tag : 
											<c:forEach items="${post.postTagList }" var="postTag">
											<span style="margin:5px;">${postTag.value }</span>
											</c:forEach>
											<c:if test="${post.canScrap }">
				<div>
					<form action="${pageContext.request.contextPath }/${loginUserProvider.blog.id }/admin/post/scrap" method="post">
						<input type="hidden" name="targetBlogId" value="${blog.id }">
						<input type="hidden" name="targetPostDisplayId" value="${post.displayId }">
						<input type="hidden" name="targetPostRevisionId" value="${post.postRevisionList.get(0).displayId }">
						<input type="submit" value="스크랩">
					</form>
				</div>
				</c:if>
				<c:if test="${loginUserProvider.loggedIn && post.canComment }">
				<div>
					<form action="${pageContext.request.contextPath }/${blog.id}/comment/writeAction" method="post">
						<input type="hidden" name="targetDisplayId" value="${post.displayId }" />
						<input type="hidden" name="type" value="post" />
						<input type="text" name="contents" />
						<input type="submit" value="댓글작성" />
					</form>
				</div>
				</c:if>
				<div>
					<c:forEach items="${post.commentList }" var="comment">
					<div>
						${comment.writer.loginId } | ${comment.dateCreated } <br />
						${comment.contents } 
						<c:if test="${loginUserProvider.loginUser.loginId == comment.writer.loginId  }">
						<a href="${pageContext.request.contextPath }/${blog.id }/comment/modify?displayId=${comment.displayId}&targetId=${comment.target.id}&type=post">수정</a>
						</c:if>
						<c:if test="${loginUserProvider.loginUser.loginId == comment.writer.loginId || loginUserProvider.loginUser.loginId == blog.owner }">
						<a href="${pageContext.request.contextPath }/${blog.id}/comment/delete?displayId=${comment.displayId}&targetId=${comment.target.id}&type=post">삭제</a>
						</c:if>
					</div>
					</c:forEach>
				</div>
				<div>
					<c:forEach items="${post.trackbackList }" var="trackback">
					<div>
						<a href="${trackback.url }">${trackback.title }</a> | ${trackback.dateCreated } <br />
					</div>
					</c:forEach>
				</div>
										</div>
								</div>
						</div>
							</c:forEach>
					</div>
					</c:forEach>
				</div>
			</section>
			
			
			<section id="postList">
				<input type="button" class="writeBtn" value="글쓰기" onclick="#" />
				<div class="search_categoryBox">
					<form action="#" method="get">
						<input type="text" name="search" placeholder="search"
							class="input_box" /> <input type="submit" value="Search"
							class="btn" />
					</form>
				</div>
				<div class="clear"></div>
				<div class="extensionPost">
				<c:set var="page" value="${page }"></c:set>
					<div class="categoryBox ev">
						<h2>모든 글</h2>
						<div class="category">
									<div class="post">
								<c:forEach items="${postList }" var="post">
									<div class="postTitle">
										${post.title } 
									</div>	
										<div class="postContents"> 
											${post.displayId } |
											${post.title } | 
											<spring:eval expression="post.dateCreated" /> |		
											공개여부 : ${post.isPublic } | 
											Version : ${post.currentRevision.displayId }
											<br /><br />
											
											${post.contents }
											<br />
											Tag : 
											<c:forEach items="${post.postTagList }" var="postTag">
											<span style="margin:5px;">${postTag.value }</span>
											</c:forEach>
											<c:if test="${post.canScrap }">
				<div>
					<form action="${pageContext.request.contextPath }/${loginUserProvider.blog.id }/admin/post/scrap" method="post">
						<input type="hidden" name="targetBlogId" value="${blog.id }">
						<input type="hidden" name="targetPostDisplayId" value="${post.displayId }">
						<input type="hidden" name="targetPostRevisionId" value="${post.postRevisionList.get(0).displayId }">
						<input type="submit" value="스크랩">
					</form>
				</div>
				</c:if>
				<c:if test="${loginUserProvider.loggedIn && post.canComment }">
				<div>
					<form action="${pageContext.request.contextPath }/${blog.id}/comment/writeAction" method="post">
						<input type="hidden" name="targetDisplayId" value="${post.displayId }" />
						<input type="hidden" name="type" value="post" />
						<input type="text" name="contents" />
						<input type="submit" value="댓글작성" />
					</form>
				</div>
				</c:if>
				<div>
					<c:forEach items="${post.commentList }" var="comment">
					<div>
						${comment.writer.loginId } | ${comment.dateCreated } <br />
						${comment.contents } 
						<c:if test="${loginUserProvider.loginUser.loginId == comment.writer.loginId  }">
						<a href="${pageContext.request.contextPath }/${blog.id }/comment/modify?displayId=${comment.displayId}&targetId=${comment.target.id}&type=post">수정</a>
						</c:if>
						<c:if test="${loginUserProvider.loginUser.loginId == comment.writer.loginId || loginUserProvider.loginUser.loginId == blog.owner }">
						<a href="${pageContext.request.contextPath }/${blog.id}/comment/delete?displayId=${comment.displayId}&targetId=${comment.target.id}&type=post">삭제</a>
						</c:if>
					</div>
					</c:forEach>
				</div>
				<div>
					<c:forEach items="${post.trackbackList }" var="trackback">
					<div>
						<a href="${trackback.url }">${trackback.title }</a> | ${trackback.dateCreated } <br />
					</div>
					</c:forEach>
				</div>
										</div>
				</c:forEach>
	
				
											</div>
											</div>
											</div>
										</div>
			</section>

	
	
</div>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
<style type="text/css">
.silber {
	color: rgb(192.192 .192);
}
</style>

</head>
<body>

	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<br>
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }/board?a=list&p=1" method="post">
				
					<input type="text" id="kwd" name="kwd" value=""> 
					<input type="submit" value="찾기">
				</form>

				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>



					<c:forEach var="bl" items="${boardList}" varStatus="status">
						<c:choose>
							<c:when test="${bl.del eq 0 }">
								<tr>
									<td>${maxBoardNum-((startPageNum)+status.index-1)}</td>

									<c:choose>
										<c:when test="${(bl.depth) eq 0}">
											<td style="text-align: left ; padding:${(bl.depth)*20}px">
												<a
												href="${pageContext.servletContext.contextPath }/board?a=view&no=${ bl.no}">
													${bl.title} </a>
											</td>
										</c:when>
										<c:otherwise>
											<td style="text-align: left ; padding:${(bl.depth)*20}px">
												<a
												href="${pageContext.servletContext.contextPath }/board?a=view&no=${ bl.no}">
													<img id="reply"
													src="${pageContext.request.contextPath }/assets/images/reply.png">${bl.title}
											</a>
											</td>
										</c:otherwise>
									</c:choose>

									<td>${bl.name}</td>
									<td>${bl.hit}</td>
									<td>${bl.regDate}</td>
									<c:choose>
										<c:when test="${bl.userNo eq authUser.no  }">
											<td><a
												href="${pageContext.servletContext.contextPath }/board?a=delete&no=${bl.no}"
												class="del">삭제</a></td>
										</c:when>
										<c:otherwise>
											<td></td>
										</c:otherwise>
									</c:choose>
								</tr>

							</c:when>
							<c:otherwise>
								<!-- del = 1이면 -->
								<tr>
									<td>${maxBoardNum-((startPageNum)+status.index-1)}</td>

									<c:choose>
										<c:when test="${(bl.depth) eq 0}">
											<td style="text-align: left ; padding:${(bl.depth)*20}px">
												<p>해당 게시글은 삭제되었습니다.</p>
											</td>
										</c:when>
										<c:otherwise>
											<td style="text-align: left ; padding:${(bl.depth)*20}px">
													<img id="reply"
													src="${pageContext.request.contextPath }/assets/images/reply.png"><p>해당 게시글은 삭제되었습니다.</p>
											</td>
										</c:otherwise>
									</c:choose>

									<td></td>
									<td></td>
									<td></td>
									<td></td>
									
								</tr>

							</c:otherwise>
						</c:choose>

					</c:forEach>

				</table>



				<!-- pager 추가 -->
				<div class="pager">
					<ul class = "row" >


						<c:choose>
							<c:when test="${startPageNum eq 1}">
								<li><h5 class="silber">◀</h5></li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.servletContext.contextPath }/board?a=list&p=${startPageNum -1}">◀</a></li>

							</c:otherwise>
						</c:choose>



						<c:forEach var="count" begin="${startPageNum }"
							end="${endPageNum }">
							<c:choose>
								<c:when test="${count eq p}">
									<li class="selected">${count}</li>
								</c:when>
								<c:otherwise>
									<li><a href="${pageContext.servletContext.contextPath }/board?a=list&p=${count}">${count}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>


						<c:choose>
							<c:when test="${endPageNum eq maxPageNum}">
								<li><h5 class="silber">▶</h5></li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.servletContext.contextPath }/board?a=list&p=${endPageNum+1}">▶</a></li>
							</c:otherwise>
						</c:choose>





					</ul>
					
				</div>
				</div>
				<!-- pager 추가 -->

				<div class="bottom">
					<c:choose>
						<c:when test="${not empty authUser}">
							<a href="${pageContext.request.contextPath }/board?a=writeform"
								id="new-book">글쓰기</a>
						</c:when>
						<c:otherwise>

							<a href="${pageContext.request.contextPath }/user?a=login&w=1"
								id="new-book">글쓰기</a>
						</c:otherwise>
					</c:choose>
				</div>
			
		</div>



		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>
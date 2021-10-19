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
		<div id="content">



			<fmt:parseNumber var="pageNo" integerOnly="true" value="${param.p}" />

			<c:set var="boardStartNo">${((pageNo-1)*10)+1}</c:set>
			<c:set var="boardmax">${fn:length(boardList)}</c:set>
			<c:set var="boardEndNo">${boardStartNo+9}</c:set>
			<c:if test="${boardEndNo > boardmax}"> ${boardEndNo = boardmax} </c:if>
			<fmt:parseNumber var="pageStart" integerOnly="true"
				value="${(pageNo/5)}" />
			<c:set var="pageStartNo">${(pageStart*5)+1}</c:set>

			<fmt:parseNumber var="pageMax" integerOnly="true"
				value="${(boardmax/10)}" />
			<c:set var="pageMaxNo">${pageMax+1}</c:set>

			<c:set var="pageEndNo">${pageStartNo+4}</c:set>
			<c:if test="${pageEndNo > pageMaxNo}"> ${pageEndNo=pageMaxNo}	</c:if>


			<!-- 			
							
					pageNo = ${pageNo} <br>
					boardStartNo = ${boardStartNo} <br>
					boardEndNo = ${boardEndNo} <br>
					boardmax = ${boardmax} <br>
					pageStartNo = ${pageStartNo} <br>
					pageEndNo = ${pageEndNo } <br>
					pageMaxNo = ${pageMaxNo} <br>
					pageEndNo = ${pageEndNo } <br>
					
			 -->

			<div id="board">
				<form id="search_form"
					action="${pageContext.request.contextPath }/board?a=search"
					method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th style="text-align: left">제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>

					<c:forEach var="count" begin="${boardStartNo}" end="${boardEndNo}">
						<tr>
							<td style="text-align: left">${count}</td>

							<c:choose>
								<c:when test="${(boardList[count-1].depth) eq 0}">
									<td
										style="text-align: left ; padding:${(boardList[count-1].depth)*20}px">
										<a
										href="${pageContext.servletContext.contextPath }/board?a=view&no=${ boardList[count-1].no}">
											${boardList[count-1].title} </a>
									</td>
								</c:when>
								<c:otherwise>
									<td
										style="text-align: left ; padding:${(boardList[count-1].depth)*20}px">
										<a
										href="${pageContext.servletContext.contextPath }/board?a=view&no=${ boardList[count-1].no}">
											<img id="reply"
											src="${pageContext.request.contextPath }/assets/images/reply.png">${boardList[count-1].title}
									</a>
									</td>
								</c:otherwise>




							</c:choose>

							<td>${boardList[count-1].userNo}</td>
							<td>${boardList[count-1].hit}</td>
							<td>${boardList[count-1].regDate}</td>
							<td><a
								href="${pageContext.servletContext.contextPath }/board?a=view&no=${ boardList[count-1].no}"
								class="del">삭제</a></td>
						</tr>
					</c:forEach>


				</table>

				<!-- pager 추가 -->
				<div class="pager">
					<ul>


						<c:choose>
							<c:when test="${pageStartNo ne 1}">
								<li><a
									href="${pageContext.servletContext.contextPath }/board?a=list&p=${pageStartNo-1}">◀</a></li>
							</c:when>
							<c:otherwise>
								<li><h5 class="silber">◀</h5></li>
							</c:otherwise>
						</c:choose>

						<c:forEach var="count" begin="${pageStartNo}" end="${pageEndNo}">
							<c:choose>
								<c:when test="${count == pageNo}">
									<li class="selected">${count}</li>
								</c:when>
								<c:otherwise>
									<li><a
										href="${pageContext.servletContext.contextPath }/board?a=list&p=${count}">${count}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:choose>
							<c:when test="${pageEndNo ne pageMaxNo}">
								<li><a
									href="${pageContext.servletContext.contextPath }/board?a=list&p=${pageEndNo+1}">▶</a></li>
							</c:when>
							<c:otherwise>
								<li><h5 class="silber">▶</h5></li>
							</c:otherwise>
						</c:choose>

					</ul>
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





		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${boardByNo.title}</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${boardByNo.contents}
							</div>
						</td>
					</tr>
				</table>
				
				
				
			<c:choose>
						<c:when test="${not empty authUser}">
							
							<div class="bottom">
								<a href="${pageContext.servletContext.contextPath }/board?a=list&p=1">글목록</a>
								<a href="${pageContext.servletContext.contextPath }/board?a=modifyform&no=${boardByNo.no }">글수정</a>
								<a href="${pageContext.servletContext.contextPath }/board?a=rewriteform&no=${boardByNo.no }">답글 작성</a>
							</div>	
								
						</c:when>
						<c:otherwise>

						<div class="bottom">
							<a href="${pageContext.servletContext.contextPath }/board?a=list&p=1">글목록</a>
							<a href="${pageContext.servletContext.contextPath }/board?a=modifyform&no=${boardByNo.no }">글수정</a>
							<a href="${pageContext.servletContext.contextPath }/user?a=login&w=1">답글 작성</a>
						</div>
						
						</c:otherwise>
			</c:choose>	
				
				
			
			
			
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>
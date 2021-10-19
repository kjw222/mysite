<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url ="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="guestbook">
				<form action="${pageContext.request.contextPath }/guestbook?a=add" method="post">
					<input type="hidden" name="a" value="insert">
					<table>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name"></td>
							<td>비밀번호</td>
							<td><input type="password" name="pass"></td>
						</tr>
						
						<tr>
							<td colspan=4><textarea name="content" id="content"></textarea></td>
						</tr>
						
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
						
					</table>
				</form>
				
				
				<c:set var="max">${fn:length(guestbookList) }</c:set>
				
				<ul>
					<c:forEach var="count" begin="1" end="${max}" >
						<li>
							<table>
								<tr>
									<td><c:out value="${count}" /></td>
									<td>${guestbookList[max-count].name}</td>
									<td>${guestbookList[max-count].regDate }</td>
									
									
									<td><a href="${pageContext.request.contextPath }/guestbook?a=deleteform&no=${guestbookList[max-count].no}" 
										onclick = "${no = max-count}">삭제</a></td>
									
								</tr>
								<tr>
									<td colspan=4>
									${guestbookList[max-count].message }
									</td>
								</tr>
							</table>
							<br>
						</li>
						
					</c:forEach>
				</ul>
				
				
				
				
			</div>
		</div>
		
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
		
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/common/default.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/book/book.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/common/common.js"></script>
		<title>책 별점 목록</title>
	</head>
	<body>
	
		<c:if test="${sessionScope.message!=null}">
			<script>
				alert('${sessionScope.message}');
			</script>
			<c:remove scope="session" var="message"/>
		</c:if>
		
		
		<table border='1'>
			<thead></thead>
			<tbody>
				<c:forEach begin="0" items="${bookGradeList}" var="bookGrade" varStatus="status">
					<tr>
						<td><img src="${bookGrade.book.bookCover}"/></td>
						<td><a href="/book/getBook.do?bookIdNum=${bookGrade.book.idNum}">${bookGrade.book.name}</a></td>
						<td>
							<c:set var="count" value="0"/>
							<c:forEach begin="1" end="${bookGrade.grade}" var="i" >
								<c:set var="count" value="${i}"/>★
							</c:forEach>
									
							<c:forEach begin="${count}" end="4">☆</c:forEach>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot></tfoot>
		</table>
		
	</body>
</html>
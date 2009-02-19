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
		<title>책 인용구 목록</title>
	</head>
	<body>
	
		<c:if test="${sessionScope.message!=null}">
			<script>
				alert('${sessionScope.message}');
			</script>
			<c:remove scope="session" var="message"/>
		</c:if>
		
		
		<c:forEach begin="0" items="${bookListInBookMark}" var="bookList" varStatus="status">
			
			<table border='1'>
				<thead></thead>
				<tbody>	
					<tr>
						<td><img src="${bookList.book.bookCover}"/></td>
						<td>제목 : ${bookList.book.name} </td>
						<td>지은이 : 
							<c:forEach begin="0" items="${bookList.book.authors}" var="authorInfo">
								[${authorInfo.name}/
								<c:if test="${authorInfo.authorDiv==0}">지음</c:if>
								<c:if test="${authorInfo.authorDiv==1}">옮김</c:if>
								]  
						</c:forEach>
						</td>
					</tr>
					<tr>
						<td>출판사 : ${bookList.book.publish_comp}</td>
						<td>출판일 : ${bookList.book.publish_date}</td>
					</tr>
					<tr>
						<td>카테고리 : ${bookList.book.category}</td>
					</tr>
					<tr>
						<td>
							<table>
								<c:forEach begin="0" items="${bookList.bookMarkList}" var="bookMarkList">
									<tr>
										<td>${bookMarkList.page}</td>
										<td>${bookMarkList.content}</td>
										<td>${bookMarkList.vibe_num}</td>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
				</tbody>
				<tfoot></tfoot>
			</table>
			<br/>
		</c:forEach>
		
	</body>
</html>
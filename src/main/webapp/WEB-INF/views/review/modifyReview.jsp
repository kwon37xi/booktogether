<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="../../styles/common/default.css" rel="stylesheet" type="text/css"/>
		<title>리뷰 수정</title>
	</head>
	<body>
		<table border='1'>
			<thead></thead>
			<tbody>
				<tr>
					<td>책표지</td>
					<td><img src="${book_info.corver}"/></td>
				</tr>
				<tr>
					<td>책이름</td>
					<td>${book_info.name}</td>
				</tr>
				<tr>
					<td>지은이</td>
					<td>
						<c:forEach begin="0" items="${book_info.authors}" var="author_info">
							[${author_info.name}/
							<c:if test="${author_info.author_div==0}">지음</c:if>
							<c:if test="${author_info.author_div==1}">옮김</c:if>
							]  
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td>ISBN</td>
					<td>${book_info.ISBN10}</td>
				</tr>
				<tr>
					<td>출판사/출판일</td>
					<td>${book_info.publish_comp}/
						${fn:substring(book_info.publish_date,0,4)}년 
						${fn:substring(book_info.publish_date,4,6)}월 
						${fn:substring(book_info.publish_date,6,8)}일
					</td>
				</tr>
				<tr>
					<td>가격</td>
					<td><fmt:formatNumber value="${book_info.price}" pattern=",###"/>원</td>
				</tr>
				<tr>
					<td>카테고리</td>
					<td>${book_info.category}</td>
				</tr>
				<tr>
					<td>설명</td>
					<td>${book_info.description}</td>
				</tr>
			</tbody>
			<tfoot></tfoot>
		</table>
		
		<div>
			<form method="post" name="modifyreview_form" action="/book/modifyBookReview.do">
				<input type="hidden" name="id" value="${bookReview_info.id}"/>
				<input type="hidden" name="book_id" value="${book_info.id}"/>
				<p>			
					<label for="name">제목</label>
					<input type="text" name="title" size="30" value="${bookReview_info.title}"/>
				</p>
				<p>
					<label for="name">내용</label>
					<textarea rows="20" cols="60" name="review"><c:out value="${bookReview_info.review}" escapeXml="true"/></textarea>
				</p>
				<input type="submit" value="수정"/>
			</form>
		</div>
		
	</body>
</html>
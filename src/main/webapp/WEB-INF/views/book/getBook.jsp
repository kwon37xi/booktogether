<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>책조회</title>
	</head>
	<body>
		<table border='1'>
			<thead>
				<tr>
					<td>이름</td>
					<td>내용</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>ID</td>
					<td>${book_info.id}</td>
				</tr>
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
							[${author_info.name}/${author_info.author_div}]  
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td>페이지수</td>
					<td>${book_info.page}쪽</td>
				</tr>
				<tr>
					<td>ISBN</td>
					<td>${book_info.ISBN10}/${book_info.ISBN13}</td>
				</tr>
				<tr>
					<td>출판사/출판일</td>
					<td>${book_info.publish_comp}/${book_info.publish_date}</td>
				</tr>
				<tr>
					<td>가격</td>
					<td>${book_info.price}</td>
				</tr>
				<tr>
					<td>카테고리</td>
					<td>${book_info.category}</td>
				</tr>
				<tr>
					<td>설명</td>
					<td>${book_info.content}</td>
				</tr>
			</tbody>
			<tfoot></tfoot>
		</table>
		
		
		<div id=''>
			<a href="javascript:history.go(-1)">뒤로</a>
			<a href="/book/modifyBookView.do?id=${book_info.id}">수정</a>
			<a href="/book/deleteBook.do?id=${book_info.id}">삭제</a>
		</div>
		
	</body>
</html>
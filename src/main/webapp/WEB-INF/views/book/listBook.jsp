<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>책목록</title>
	</head>
	<body>
		<table border='1'>
			<thead>
				<tr>
					<td>ID</td>
					<td>책표지</td>
					<td>책이름</td>
					<td>지은이</td>
					<td>페이지수</td>
					<td>ISBN</td>
					<td>출판사/출판일</td>
					<td>가격</td>
					<td>카테고리</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach begin="0" items="${booklist}" var="book_info" varStatus="status">
					<tr>
						<td>${book_info.id}</td>
						<td><img src="${book_info.corver}"/></td>
						<td><a href="/book/getBook.do?id=${book_info.id}">${book_info.name}</a></td>
						<td>
							<c:forEach begin="0" items="${book_info.authors}" var="author_info">
								[${author_info.name}/${author_info.author_div}]  
							</c:forEach>
						</td>
						<td>${book_info.page}쪽</td>
						<td>${book_info.ISBN10}/${book_info.ISBN13}</td>
						<td>${book_info.publish_comp}/${book_info.publish_date}</td>
						<td>${book_info.price}</td>
						<td>${book_info.category}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot></tfoot>
		</table>
		
		
		<div id='page_div'>
			<c:if test="${pageBean.prepage}">
				<a href="javascript:go_page('${pageBean.startPage-pageBean.limit}')">이전</a>
			</c:if>
			
			<c:forEach begin="${pageBean.startPage}" end="${pageBean.endPage}" var='i'>
				<a href="javascript:go_page('${i}')">[ ${i} ]</a>
			</c:forEach>
				
			<c:if test="${pageBean.nextpage}">
				<a href="javascript:go_page('${pageBean.startPage+pageBean.limit}')">다음</a>
			</c:if>
		</div>
		
	</body>
</html>
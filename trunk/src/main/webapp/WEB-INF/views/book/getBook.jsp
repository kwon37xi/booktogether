<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
					<td>${book_info.description}</td>
				</tr>
			</tbody>
			<tfoot></tfoot>
		</table>
		
		<br/><br/>
		<table border="1">
			<thead>
				<tr>
					<td colspan="2">별점 테이블</td>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(bookgradelist)!=0}">
						<c:forEach begin="0" items="${bookgradelist}" var="grade_info" varStatus="status">
							<tr>
								<td>
									<c:set var="count" value="0"/>
									<c:forEach begin="1" end="${grade_info.grade}" var="i" >
										<c:set var="count" value="${i}"/>★
									</c:forEach>
									
									<c:forEach begin="${count}" end="4">☆</c:forEach>
								</td>
								<td>
									${grade_info.user.user_id}(${grade_info.user.nickname})
									<c:if test="${grade_info.user.user_id==sessionScope.user_id}">
										<a href="/book/deleteBookGrade.do?id=${grade_info.id}&book_id=${book_info.id}">삭제</a>
									</c:if>
								</td>
							</tr>	
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="2">별점이 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
			<tfoot></tfoot>
		</table>
		
		<c:if test="${sessionScope.id!=null && !existGrade}">
			<form name="bookgradeform" method="post" action="/book/insertBookGrade.do">
				<input type="hidden" name="book_id" value="${book_info.id}"/>
				<table border="1">
					<thead>
						<tr>
							<td>별점 하기</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
							    <input type="radio" name="grade" value="0"/>☆☆☆☆☆<br/>
							    <input type="radio" name="grade" value="1"/>★☆☆☆☆<br/>
							    <input type="radio" name="grade" value="2"/>★★☆☆☆<br/>
							    <input type="radio" name="grade" value="3"/>★★★☆☆<br/>
							    <input type="radio" name="grade" value="4"/>★★★★☆<br/>
							    <input type="radio" name="grade" value="5"/>★★★★★<br/>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td><input type="submit" value="별점하기"/></td>
						</tr>
					</tfoot>
				</table>
			</form>
		</c:if>
		<div id=''>
			<a href="javascript:history.go(-1)">뒤로</a>
			<a href="/book/modifyBookView.do?id=${book_info.id}">수정</a>
			<a href="/book/deleteBook.do?id=${book_info.id}">삭제</a>
			<a href="/book/listBook.do">목록</a>
		</div>
		
	</body>
</html>
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
		<title>서재에 등록하기</title>
	</head>
	<body>
		
		<c:if test="${sessionScope.message!=null}">
			<script>
				alert('${sessionScope.message}');
			</script>
			<c:remove scope="session" var="message"/>
		</c:if>
		
		책정보
		<table border='1'>
			<thead></thead>
			<tbody>
				<tr>
					<td>책표지</td>
					<td><img src="${bookInfo.bookCover}"/></td>
				</tr>
				<tr>
					<td>책이름</td>
					<td>${bookInfo.name}</td>
				</tr>
				<tr>
					<td>지은이</td>
					<td>
						<c:forEach begin="0" items="${bookInfo.authors}" var="authorInfo">
							[${authorInfo.name}/
							<c:if test="${authorInfo.authorDiv==0}">지음</c:if>
							<c:if test="${authorInfo.authorDiv==1}">옮김</c:if>
							]  
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td>ISBN</td>
					<td>${bookInfo.ISBN10}</td>
				</tr>
				<tr>
					<td>출판사/출판일</td>
					<td>${bookInfo.publishComp}/
						${fn:substring(bookInfo.publishDate,0,4)}년 
						${fn:substring(bookInfo.publishDate,4,6)}월 
						${fn:substring(bookInfo.publishDate,6,8)}일
					</td>
				</tr>
				<tr>
					<td>가격</td>
					<td><fmt:formatNumber value="${bookInfo.price}" pattern=",###"/>원</td>
				</tr>
				<tr>
					<td>카테고리</td>
					<td>${bookInfo.category}</td>
				</tr>
				<tr>
					<td>설명</td>
					<td>${bookInfo.description}</td>
				</tr>
			</tbody>
			<tfoot></tfoot>
		</table>
		
		서재에 등록
		<form name="modifyBookMyLibraryform" method="post" action="/library/modifyLibraryBook.do">
		
			<input type="hidden" name="book.idNum" value="${bookInfo.idNum}"/>
			<input type="hidden" name="idNum" value="${libraryBook.idNum}"/>
			<input type="hidden" name="library.idNum" value="${libraryBook.library.idNum}"/>
			
			<table border='1'>
				<thead></thead>
				<tbody>
					<tr>
						<td>어느쪽으로 책 분류 하시겠습니까?</td>
					</tr>
					<tr>
						<td>
							<input type="radio" name="state" value="0" ${libraryBook.state==0 ? 'checked' : ''}/>읽고 싶은책<br/>
							<input type="radio" name="state" value="1" ${libraryBook.state==1 ? 'checked' : ''}/>읽고 있는책<br/>
							<input type="radio" name="state" value="2" ${libraryBook.state==2 ? 'checked' : ''}/>읽은 책<br/>
						</td>
					</tr>
					<tr>
						<td>
							<fmt:formatDate value="${libraryBook.readDate}" pattern="yyyy" var="readDateYear"/>
							<fmt:formatDate value="${libraryBook.readDate}" pattern="MM" var="readDateMonth"/>
							<fmt:formatDate value="${libraryBook.readDate}" pattern="dd" var="readDateDate"/>
							<input type="text" name="readDateYear" size="4" value="${readDateYear}"/>
							<input type="text" name="readDateMonth" size="2" value="${readDateMonth}"/>
							<input type="text" name="readDateDate" size="2" value="${readDateDate}"/>
						</td>
					</tr>
					<tr>
						<td>
							<input type="checkbox" name="isPossess" ${libraryBook.isPossess==1 ? 'checked' : ''} value="1" />소유여부
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td>
							<input type="submit" value="수정"/>
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
		<div id=''>
			<a href="javascript:history.go(-1)">뒤로</a>
		</div>
		
	</body>
</html>
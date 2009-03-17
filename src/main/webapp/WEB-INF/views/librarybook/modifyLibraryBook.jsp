<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/common/jquery-ui-1.7.custom.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/common/jquery-ui-1.7.min.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/common/datepicker.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/book/book.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/librarybook/modifylibrarybook.js"></script>
		<title>서재 책 수정하기</title>
	</head>
	<body>
		
		<table id="bookinfo_simple">
			<thead>
				<tr>
					<td colspan="2">책정보</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="b_label">책표지</td>
					<td>
						<c:if test="${bookInfo.bookCover!=null && bookInfo.bookCover!=''}">
							<img src="${bookInfo.bookCover}"/>
						</c:if>
						<c:if test="${bookInfo.bookCover==null || bookInfo.bookCover==''}">
							<img src="/images/book/bookDefault.png"/>
						</c:if>					
					</td>
				</tr>
				<tr>
					<td class="b_label">책이름</td>
					<td>${bookInfo.name}</td>
				</tr>
				<tr>
					<td class="b_label">지은이</td>
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
					<td class="b_label">출판사/출판일</td>
					<td>${bookInfo.publishComp}/
						${fn:substring(bookInfo.publishDate,0,4)}년 
						${fn:substring(bookInfo.publishDate,4,6)}월 
						${fn:substring(bookInfo.publishDate,6,8)}일
					</td>
				</tr>
			</tbody>
			<tfoot></tfoot>
		</table>
		
		
		<form name="modifyBookMyLibraryform" method="post" action="/library/modifyLibraryBook.do">
		
			<input type="hidden" name="book.idNum" value="${bookInfo.idNum}"/>
			<input type="hidden" name="idNum" value="${libraryBook.idNum}"/>
			<input type="hidden" name="library.idNum" value="${libraryBook.library.idNum}"/>
			<table id="modifypossessbook">
				<thead>
					<tr>
						<td>수정하기</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>어느쪽으로 책 분류 하시겠습니까?</td>
					</tr>
					<tr>
						<td>
							<input type="radio" name="state" value="0" ${libraryBook.state==0 ? 'checked' : ''}/>읽고 싶은책 
							<input type="radio" name="state" value="1" ${libraryBook.state==1 ? 'checked' : ''}/>읽고 있는책 
							<input type="radio" name="state" value="2" ${libraryBook.state==2 ? 'checked' : ''}/>읽은 책 
							
							<fmt:formatDate value="${libraryBook.readDate}" pattern="yyyy" var="readDateYear"/>
							<fmt:formatDate value="${libraryBook.readDate}" pattern="MM" var="readDateMonth"/>
							<fmt:formatDate value="${libraryBook.readDate}" pattern="dd" var="readDateDate"/>
							<input type="text" name="readDateYear" size="4" value="${readDateYear}"/>
							<input type="text" name="readDateMonth" size="2" value="${readDateMonth}"/>
							<input type="text" name="readDateDate" size="2" value="${readDateDate}"/>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td>
							<input type="button" value="수정" onclick="modifyLibraryBook()"/>
							<input type="button" value="뒤로" onclick="javascript:history.go(-1)"/>
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</body>
</html>
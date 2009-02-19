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
		<title>내 소유책 수정</title>
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
					<td><img src="${possessBook.book.bookCover}"/></td>
				</tr>
				<tr>
					<td>책이름</td>
					<td>${possessBook.book.name}</td>
				</tr>
				<tr>
					<td>지은이</td>
					<td>
						<c:forEach begin="0" items="${possessBook.book.authors}" var="authorInfo">
							[${authorInfo.name}/
							<c:if test="${authorInfo.authorDiv==0}">지음</c:if>
							<c:if test="${authorInfo.authorDiv==1}">옮김</c:if>
							]  
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td>ISBN</td>
					<td>${possessBook.book.ISBN10}</td>
				</tr>
				<tr>
					<td>출판사/출판일</td>
					<td>${possessBook.book.publishComp}/
						${fn:substring(possessBook.book.publishDate,0,4)}년 
						${fn:substring(possessBook.book.publishDate,4,6)}월 
						${fn:substring(possessBook.book.publishDate,6,8)}일
					</td>
				</tr>
				<tr>
					<td>가격</td>
					<td><fmt:formatNumber value="${possessBook.book.price}" pattern=",###"/>원</td>
				</tr>
				<tr>
					<td>카테고리</td>
					<td>${possessBook.book.category}</td>
				</tr>
				<tr>
					<td>설명</td>
					<td>${possessBook.book.description}</td>
				</tr>
			</tbody>
			<tfoot></tfoot>
		</table>
		
		내 소유책 수정하기
		<form name="modifyPossessBookform" method="post" action="/library/modifyPossessBook.do">
		
			<input type="hidden" name="idNum" value="${possessBook.idNum}"/>
			<input type="hidden" name="book.idNum" value="${possessBook.book.idNum}"/>
			
			<table>
				<tr>
					<td>구입날짜</td>
					<td>
						<fmt:formatDate value="${possessBook.purchaseDate}" pattern="yyyy" var="purchaseDateYear"/>
						<fmt:formatDate value="${possessBook.purchaseDate}" pattern="MM" var="purchaseDateMonth"/>
						<fmt:formatDate value="${possessBook.purchaseDate}" pattern="dd" var="purchaseDateDate"/>
						<input type="text" name="purchaseDateYear" size="4" value="${purchaseDateYear}"/>
						<input type="text" name="purchaseDateMonth" size="2" value="${purchaseDateMonth}"/>
						<input type="text" name="purchaseDateDate" size="2" value="${purchaseDateDate}"/>
					</td>
				</tr>
					<tr>
						<td>구입가격</td>
						<td>
							<input type="text" name="purchasePrice" size="20" value="${possessBook.purchasePrice}"/>
						</td>
					</tr>
				<tr>
					<td>독서시작일</td>
					<td>
						<fmt:formatDate value="${possessBook.beginRead}" pattern="yyyy" var="beginReadYear"/>
						<fmt:formatDate value="${possessBook.beginRead}" pattern="MM" var="beginReadMonth"/>
						<fmt:formatDate value="${possessBook.beginRead}" pattern="dd" var="beginReadDate"/>
						<input type="text" name="beginReadYear" size="4" value="${beginReadYear}"/>
						<input type="text" name="beginReadMonth" size="2" value="${beginReadMonth}"/>
						<input type="text" name="beginReadDate" size="2" value="${beginReadDate}"/>
					</td>
				</tr>
				<tr>
					<td>독서종료일</td>
					<td>
						<fmt:formatDate value="${possessBook.endRead}" pattern="yyyy" var="endReadYear"/>
						<fmt:formatDate value="${possessBook.endRead}" pattern="MM" var="endReadMonth"/>
						<fmt:formatDate value="${possessBook.endRead}" pattern="dd" var="endReadDate"/>
						<input type="text" name="endReadYear" size="4" value="${endReadYear}"/>
						<input type="text" name="endReadMonth" size="2" value="${endReadMonth}"/>
						<input type="text" name="endReadDate" size="2" value="${endReadDate}"/>
					</td>
				</tr>
				<tr>
					<td>책품질</td>
					<td>
						<select name="quality">
							<option value="0" ${possessBook.quality==0 ? 'selected' : ''}>상</option>
							<option value="1" ${possessBook.quality==1 ? 'selected' : ''}>중</option>
							<option value="2" ${possessBook.quality==2 ? 'selected' : ''}>하</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>책상태</td>
					<td>
						<select name="state">
							<option value="0" ${possessBook.state==0 ? 'selected' : ''}>소유</option>
							<option value="1" ${possessBook.state==1 ? 'selected' : ''}>대여중</option>
							<option value="2" ${possessBook.state==2 ? 'selected' : ''}>교환중</option>
						</select>
					</td>
				</tr>
			</table>
			<input type="submit" value="수정"/>
		</form>
		<div id=''>
			<a href="javascript:history.go(-1)">뒤로</a>
		</div>
		
	</body>
</html>
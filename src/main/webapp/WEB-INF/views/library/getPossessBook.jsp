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
		<title>내 소유책 조회</title>
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
		
		내 소유책 조회하기
		<table>
			<tr>
				<td>구입날짜</td>
				<td>
					<c:choose>
						<c:when test="${possessBook.purchaseDate!=null}">
							<fmt:formatDate value="${possessBook.purchaseDate}" pattern="yyyy" />년
							<fmt:formatDate value="${possessBook.purchaseDate}" pattern="MM"/>월
							<fmt:formatDate value="${possessBook.purchaseDate}" pattern="dd"/>일
						</c:when>
						<c:otherwise>
							입력정보 없음
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
				<tr>
					<td>구입가격</td>
					<td>
						<fmt:formatNumber value="${possessBook.purchasePrice}" pattern=",###"/>원
					</td>
				</tr>
			<tr>
				<td>독서시작일</td>
				<td>
					<c:choose>
						<c:when test="${possessBook.beginRead!=null}">
							<fmt:formatDate value="${possessBook.beginRead}" pattern="yyyy"/>년
							<fmt:formatDate value="${possessBook.beginRead}" pattern="MM"/>월
							<fmt:formatDate value="${possessBook.beginRead}" pattern="dd"/>일
						</c:when>
						<c:otherwise>
							입력정보 없음
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>독서종료일</td>
				<td>
					<c:choose>
						<c:when test="${possessBook.endRead!=null}">
							<fmt:formatDate value="${possessBook.endRead}" pattern="yyyy"/>년
							<fmt:formatDate value="${possessBook.endRead}" pattern="MM"/>월
							<fmt:formatDate value="${possessBook.endRead}" pattern="dd"/>일
						</c:when>
						<c:otherwise>
							입력정보 없음
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>책품질</td>
				<td>
					<c:choose>
						<c:when test="${possessBook.quality==0}">상</c:when>
						<c:when test="${possessBook.quality==1}">중</c:when>
						<c:otherwise>하</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>책상태</td>
				<td>
					<c:choose>
						<c:when test="${possessBook.state==0}">소유</c:when>
						<c:when test="${possessBook.state==1}">대여중</c:when>
						<c:otherwise>교환중</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
		
		<div id=''>
			<a href="javascript:history.go(-1)">뒤로</a>
		</div>
	
	</body>
</html>
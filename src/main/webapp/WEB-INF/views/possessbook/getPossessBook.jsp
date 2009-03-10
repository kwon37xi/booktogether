<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link rel="stylesheet" type="text/css" href="/styles/possess/possessbook.css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/book/book.js"></script>
		<title>내 소유책 조회</title>
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
					<td class="p_label">책표지</td>
					<td>
						<c:if test="${possessBook.book.bookCover!=null && possessBook.book.bookCover!=''}">
							<img src="${possessBook.book.bookCover}"/>
						</c:if>
						<c:if test="${possessBook.book.bookCover==null || possessBook.book.bookCover==''}">
							<img src="/images/book/bookDefault.png"/>
						</c:if>	
					</td>
				</tr>
				<tr>
					<td class="p_label">책이름</td>
					<td>${possessBook.book.name}</td>
				</tr>
				<tr>
					<td class="p_label">지은이</td>
					<td>${possessBook.book.authors[0].name} 지음	</td>
				</tr>
				<tr>
					<td class="p_label">ISBN</td>
					<td>${possessBook.book.ISBN10}</td>
				</tr>
				<tr>
					<td class="p_label">출판사/출판일</td>
					<td>${possessBook.book.publishComp}/
						${fn:substring(possessBook.book.publishDate,0,4)}년 
						${fn:substring(possessBook.book.publishDate,4,6)}월 
						${fn:substring(possessBook.book.publishDate,6,8)}일
					</td>
				</tr>
				<tr>
					<td class="p_label">가격</td>
					<td><fmt:formatNumber value="${possessBook.book.price}" pattern=",###"/>원</td>
				</tr>
				<tr>
					<td class="p_label">카테고리</td>
					<td>${possessBook.book.category}</td>
				</tr>
			</tbody>
		</table>
		
		
		<table id="possessbok_info">
			<thead>
				<tr>
					<td colspan="2">내 소유책 조회하기</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="p_label">구입날짜</td>
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
					<td class="p_label">구입가격</td>
					<td>
						<fmt:formatNumber value="${possessBook.purchasePrice}" pattern=",###"/>원
					</td>
				</tr>
				<tr>
					<td class="p_label">독서시작일</td>
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
					<td class="p_label">독서종료일</td>
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
					<td class="p_label">책품질</td>
					<td>
						<c:choose>
							<c:when test="${possessBook.quality==0}">상</c:when>
							<c:when test="${possessBook.quality==1}">중</c:when>
							<c:otherwise>하</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="p_label">책상태</td>
					<td>
						<c:choose>
							<c:when test="${possessBook.state==0}">소유</c:when>
							<c:when test="${possessBook.state==1}">대여중</c:when>
							<c:otherwise>교환중</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</tbody>
		</table>
		
	</body>
</html>
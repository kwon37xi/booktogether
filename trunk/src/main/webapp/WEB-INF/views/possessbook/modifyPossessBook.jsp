<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/common/jquery-ui-1.7.custom.css" rel="stylesheet" type="text/css"/>
		<link href="/styles/possess/possessbook.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/common/jquery-ui-1.7.min.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/common/datepicker.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/book/book.js"></script>
		<title>내 소유책 수정</title>
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
					<td>${possessBook.book.authors[0].name} 지음</td>
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
		
		
		<form name="modifyPossessBookform" method="post" action="/library/modifyPossessBook.do">
		
			<input type="hidden" name="idNum" value="${possessBook.idNum}"/>
			<input type="hidden" name="book.idNum" value="${possessBook.book.idNum}"/>
			<input type="hidden" name="purchasePrice" size="20" value="${possessBook.purchasePrice}"/>
			
			<table id="possessbok_info">
				<thead>
					<tr>
						<td colspan="2">내 소유책 수정하기</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="p_label">구입날짜</td>
						<td>
							<input type="hidden" id="datepicker_purchaseDate"/>
							<fmt:formatDate value="${possessBook.purchaseDate}" pattern="yyyy" var="purchaseDateYear"/>
							<fmt:formatDate value="${possessBook.purchaseDate}" pattern="MM" var="purchaseDateMonth"/>
							<fmt:formatDate value="${possessBook.purchaseDate}" pattern="dd" var="purchaseDateDate"/>
							<input type="text" name="purchaseDateYear" class="purchaseDateYear" size="4" value="${purchaseDateYear}" readonly="readonly"/>
							<input type="text" name="purchaseDateMonth" class="purchaseDateMonth" size="2" value="${purchaseDateMonth}" readonly="readonly"/>
							<input type="text" name="purchaseDateDate" class="purchaseDateDate" size="2" value="${purchaseDateDate}" readonly="readonly"/>
						</td>
					</tr>					
					<tr>
						<td class="p_label">독서시작일</td>
						<td>
							<input type="hidden" id="datepicker_beginRead"/>
							<fmt:formatDate value="${possessBook.beginRead}" pattern="yyyy" var="beginReadYear"/>
							<fmt:formatDate value="${possessBook.beginRead}" pattern="MM" var="beginReadMonth"/>
							<fmt:formatDate value="${possessBook.beginRead}" pattern="dd" var="beginReadDate"/>
							<input type="text" name="beginReadYear" size="4" class="beginReadYear" value="${beginReadYear}" readonly="readonly"/>
							<input type="text" name="beginReadMonth" size="2" class="beginReadMonth" value="${beginReadMonth}" readonly="readonly"/>
							<input type="text" name="beginReadDate" size="2" class="beginReadDate" value="${beginReadDate}" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="p_label">독서종료일</td>
						<td>
							<input type="hidden" id="datepicker_endRead"/>
							<fmt:formatDate value="${possessBook.endRead}" pattern="yyyy" var="endReadYear"/>
							<fmt:formatDate value="${possessBook.endRead}" pattern="MM" var="endReadMonth"/>
							<fmt:formatDate value="${possessBook.endRead}" pattern="dd" var="endReadDate"/>
							<input type="text" name="endReadYear" size="4" class="endReadYear" value="${endReadYear}" readonly="readonly"/>
							<input type="text" name="endReadMonth" size="2" class="endReadMonth" value="${endReadMonth}" readonly="readonly"/>
							<input type="text" name="endReadDate" size="2" class="endReadDate" value="${endReadDate}" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="p_label">책품질</td>
						<td>
							<select name="quality">
								<option value="0" ${possessBook.quality==0 ? 'selected' : ''}>상</option>
								<option value="1" ${possessBook.quality==1 ? 'selected' : ''}>중</option>
								<option value="2" ${possessBook.quality==2 ? 'selected' : ''}>하</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="p_label">책상태</td>
						<td>
							<select name="state">
								<option value="0" ${possessBook.state==0 ? 'selected' : ''}>소유</option>
								<option value="1" ${possessBook.state==1 ? 'selected' : ''}>대여중</option>
								<option value="2" ${possessBook.state==2 ? 'selected' : ''}>교환중</option>
							</select>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2"><input type="submit" value="수정"/></td>
					</tr>
				</tfoot>
			</table>
			
		</form>
		<div id=''>
			<a href="javascript:history.go(-1)">뒤로</a>
		</div>
		
	</body>
</html>
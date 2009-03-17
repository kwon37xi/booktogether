<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link rel="stylesheet" type="text/css" href="/styles/book/getlistpossessbook.css" />
		<script type="text/javascript" charset="utf-8" src="/scripts/book/book.js"></script>
		<title>책 소유자 목록</title>
	</head>
	<body>
	
		<table id="bookinfo">
			<thead>
				<tr>
					<td colspan="4">책정보</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="b_label" rowspan="4">책표지</td>
					<td class="b_label_c" rowspan="4">
						<c:if test="${bookInfo.bookCover!=null && bookInfo.bookCover!=''}">
							<img src="${bookInfo.bookCover}"/>
						</c:if>
						<c:if test="${bookInfo.bookCover==null || bookInfo.bookCover==''}">
							<img src="/images/book/bookDefault.png"/>
						</c:if>					
					</td>
					<td class="b_label">책이름</td>
					<td class="b_label_c">${fn:escapeXml(bookInfo.name)}</td>
				</tr>
				<tr>
					<td class="b_label">지은이</td>
					<td class="b_label_c">
						<c:forEach begin="0" items="${bookInfo.authors}" var="authorInfo">
							[${authorInfo.name}/
							<c:if test="${authorInfo.authorDiv==0}">지음</c:if>
							<c:if test="${authorInfo.authorDiv==1}">옮김</c:if>
							]  
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td class="b_label">ISBN</td>
					<td class="b_label_c">${bookInfo.ISBN10}</td>
				</tr>
				<tr>
					<td class="b_label">출판사/출판일</td>
					<td class="b_label_c">${bookInfo.publishComp}/
						${fn:substring(bookInfo.publishDate,0,4)}년 
						${fn:substring(bookInfo.publishDate,4,6)}월 
						${fn:substring(bookInfo.publishDate,6,8)}일
					</td>
				</tr>
				<tr>
					<td class="b_label">가격</td>
					<td class="b_label_c"><fmt:formatNumber value="${bookInfo.price}" pattern=",###"/>원</td>
					<td class="b_label">카테고리</td>
					<td class="b_label_c">${bookInfo.category}</td>
				</tr>
				<tr>
					<td class="b_label">설명</td>
					<td class="b_label_c"  colspan="3">${bookInfo.description}</td>
				</tr>
			</tbody>
		</table>
		
		<table id="possessBookList">
			<thead>
				<tr>
					<td colspan="5">책소유자 목록</td>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(possessBookList)!=0}">
						<c:set var="bookListLength" value="${fn:length(possessBookList)}"/>
						<c:set var="columCount" value="5"/>
						<c:set var="rowCount" value="${(bookListLength%columCount==0)? bookListLength/columCount : bookListLength/columCount+1}"/>
						<c:set var="aIndex" value="0"/>
						<c:set var="bookList" value="${possessBookList}"/>
						
						<c:forEach begin="1" end="${rowCount}" varStatus="status">
							<tr>
								<c:forEach begin="1" end="${columCount}" varStatus="istatus">
									<td>
										<c:if test="${aIndex>bookListLength}">&nbsp;</c:if>
										<c:if test="${aIndex<bookListLength}">
											<img src="/images/user/thumnail/${bookList[aIndex].user.name}" width="80" height="80"/>
											<br/>
											<a href="/library/getLibrary.do?userId=${bookList[aIndex].user.userId}">${bookList[aIndex].user.userId}</a>
										</c:if>
									</td>
									<c:set var="aIndex" value="${aIndex+1}"/>
								</c:forEach>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5" class="nocontent">검색 결과가 없습니다.</td>
						</tr>
					</c:otherwise>				
				</c:choose>
			</tbody>
		</table>

		<div id='navigator'>
			<c:if test="${pageBean.prePage}">
				<a href="javascript:go_page_possessbook('${pageBean.startPage-pageBean.limit}','${bookInfo.idNum}')">이전</a>
			</c:if>
			
			<c:forEach begin="${pageBean.startPage}" end="${pageBean.endPage}" var='i'>
				<a href="javascript:go_page_possessbook('${i}','${bookInfo.idNum}')">[ ${i} ]</a>
			</c:forEach>
				
			<c:if test="${pageBean.nextPage}">
				<a href="javascript:go_page_possessbook('${pageBean.startPage+pageBean.limit}','${bookInfo.idNum}')">다음</a>
			</c:if>
		</div>
		
		
	</body>
</html>
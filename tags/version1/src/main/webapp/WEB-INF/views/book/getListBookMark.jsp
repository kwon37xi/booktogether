<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/book/getlistbookmark.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/book/book.js"></script>
		<title>책 인용구 목록</title>
	</head>
	<body>
	
		<table id="bookinfo">
			<thead>
				<tr>
					<td colspan="2">책정보</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="b_label">책표지</td>
					<td class="b_label_c">
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
				</tr>
				<tr>
					<td class="b_label">카테고리</td>
					<td class="b_label_c">${bookInfo.category}</td>
				</tr>
				<tr>
					<td class="b_label">설명</td>
					<td class="b_label_c">${bookInfo.description}</td>
				</tr>
			</tbody>
		</table>
		
		<table class="bookmark_list">
			<thead>
				<tr>
					<td colspan="4">책 인용구 목록</td>
				</tr>
			</thead>
			<tbody>	
				<c:choose>
					<c:when test="${fn:length(bookMarkList)!=0}">
						<c:forEach begin="0" items="${bookMarkList}" var="bookMarkInfo" varStatus="status">
							<tr>
								<td class="page">p.${bookMarkInfo.page}</td>
								<td class="content">${bookMarkInfo.content} / ${bookMarkInfo.user.userId}</td>
								<td class="vibe">공감 :${bookMarkInfo.vibeNum}</td>
								<c:if test="${sessionScope.idNum!=null && sessionScope.idNum==library.user.idNum}">
									<td class="func">
										<input type="button" value="삭제" onclick="deleteBookMark('${bookMarkInfo.idNum}','${bookInfo.idNum}')"/>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td class="nocontent">검색된 결과값이 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4">
						<div id='navigator'>
							<c:if test="${pageBean.prePage}">
								<a href="/book/getListBookMark.do?pageNo=${pageBean.startPage-pageBean.limit}&bookIdNum=${bookInfo.idNum}">이전</a>
							</c:if>
							
							<c:forEach begin="${pageBean.startPage}" end="${pageBean.endPage}" var='i'>
								<a href="/book/getListBookMark.do?pageNo=${i}&bookIdNum=${bookInfo.idNum}">[ ${i} ]</a>
							</c:forEach>
								
							<c:if test="${pageBean.nextPage}">
								<a href="/book/getListBookMark.do?pageNo=${pageBean.startPage+pageBean.limit}&bookIdNum=${bookInfo.idNum}">다음</a>
							</c:if>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
		
		
	</body>
</html>
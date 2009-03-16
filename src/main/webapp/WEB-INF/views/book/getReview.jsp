<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/book/review.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/book/book.js"></script>
		<title>리뷰 조회</title>
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
		
		<form method="post" name="reviewform" action="/book/modifyBookReviewView.do">
			<input type="hidden" name="bookIdNum" value="${bookInfo.idNum}"/>
			<table id="reviewview">
				<thead>
					<tr>
						<td colspan="2">리뷰조회</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="b_label">제목</td>
						<td class="b_label_c">
							${fn:escapeXml(bookReviewInfo.title)}
						</td>
					</tr>
					<tr>
						<td class="b_label">내용</td>
						<td class="b_label_c">
							<c:out value="${bookReviewInfo.review}" escapeXml="false"/> 
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<c:if test="${bookReviewInfo.user.idNum==sessionScope.idNum}">
								<input type="submit" value="수정"/>
							</c:if>
						</td>
					</tr>
				</tfoot>
			</table>					
		</form>
		
		<div id="navigator">
		
			<a href="javascript:history.go(-1)">뒤로</a>
			
			<c:if test="${sessionScope.idNum!=null && bookReviewInfo.user.idNum!=sessionScope.idNum}">
				<input type="button" value="추천하기" onclick="recommend('${bookInfo.idNum}','${bookReviewInfo.idNum}')"/>
			</c:if>
			
		</div>
		
	</body>
</html>
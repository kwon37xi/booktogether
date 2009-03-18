<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link rel="stylesheet" type="text/css" href="/styles/library/library.css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/library/library.js"></script>
		<title>책 리뷰 목록</title>
	</head>
	<body>
	
		<table id="myreviewlist">
			<thead>
				<tr>
					<td>책 리뷰 목록</td>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(bookReviewList)!=0}">
						<c:forEach begin="0" items="${bookReviewList}" var="bookReview" varStatus="status">
							<tr>
								<td>
									<table>
										<tr>
											<td rowspan="4">
												<c:if test="${bookReview.book.bookCover!=null && bookReview.book.bookCover!=''}">
													<img src="${bookReview.book.bookCover}"/>
												</c:if>
												<c:if test="${bookReview.book.bookCover==null || bookReview.book.bookCover==''}">
													<img src="/images/book/bookDefault.png"/>
												</c:if>	
											</td>
											<td>제목 : <a href="/book/getBook.do?bookIdNum=${bookReview.book.idNum}">${fn:escapeXml(bookReview.book.name)}</a></td>
										</tr>
										<tr>
											<td>지은이 : 
												<c:forEach begin="0" items="${bookReview.book.authors}" var="authorInfo">
													[${authorInfo.name}/
													<c:if test="${authorInfo.authorDiv==0}">지음</c:if>
													<c:if test="${authorInfo.authorDiv==1}">옮김</c:if>
													]  
												</c:forEach>
											</td>
										</tr>
										<tr>
											<td>
												리뷰제목 : <a href="/book/getReview.do?bookReviewIdNum=${bookReview.idNum}">
															${fn:escapeXml(bookReview.title)}
														 </a>
											</td>
										</tr>
										<tr>
											<td>공감수 : ${bookReview.recommend} </td>
										</tr>
								
									</table>
								</td>
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
		</table>
		
		<div id='navigator'>
			<c:if test="${pageBean.prePage}">
				<a href="/library/getListMyReview.do?pageNo=${pageBean.startPage-pageBean.limit}&userIdNum=${library.user.idNum}&libraryIdNum=${library.idNum}">이전</a>
			</c:if>
			
			<c:forEach begin="${pageBean.startPage}" end="${pageBean.endPage}" var='i'>
				<a href="/library/getListMyReview.do?pageNo=${i}&userIdNum=${library.user.idNum}&libraryIdNum=${library.idNum}">[ ${i} ]</a>
			</c:forEach>
				
			<c:if test="${pageBean.nextPage}">
				<a href="/library/getListMyReview.do?pageNo=${pageBean.startPage+pageBean.limit}&userIdNum=${library.user.idNum}&libraryIdNum=${library.idNum}">다음</a>
			</c:if>
		</div>
		
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/library/library.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/library/library.js"></script>
		<title>책 인용구 목록</title>
	</head>
	<body>
		
		<table>
			<thead>
				<tr>
					<td>책 인용구 목록</td>
				</tr>
			</thead>
	
			<c:choose>
				<c:when test="${fn:length(bookListInBookMark)!=0}">
					<c:forEach begin="0" items="${bookListInBookMark}" var="bookList" varStatus="status">
						<tr><td>
						<table class="bookmark_bookinfo">
							<tbody>	
								<tr>
									<td rowspan="3"><img src="${bookList.book.bookCover}"/></td>
									<td>제목 : <a href="javascript:getBook('${bookList.book.idNum}')">${bookList.book.name}</a></td>
									<td>지은이 : 
										<c:forEach begin="0" items="${bookList.book.authors}" var="authorInfo">
											${authorInfo.name}/
											<c:if test="${authorInfo.authorDiv==0}">지음</c:if>
											<c:if test="${authorInfo.authorDiv==1}">옮김</c:if>
										</c:forEach>
									</td>
								</tr>
								<tr>
									<td>출판사 : ${bookList.book.publishComp}</td>
									<td>출판일 : ${bookList.book.publishDate}</td>
								</tr>
								<tr>
									<td colspan="3">
										<table class="bookmark_info">
											<c:forEach begin="0" items="${bookList.bookMarkList}" var="bookMarkList">
												<tr>
													<td>p.${bookMarkList.page}</td>
													<td>${bookMarkList.content}</td>
													<td>공감 : ${bookMarkList.vibeNum}</td>
													<td>
														<input type="button" value="삭제" onclick="deleteBookMark('${bookMarkList.idNum}','${bookList.book.idNum}')"/>
													</td>
												</tr>
											</c:forEach>
										</table>
									</td>
								</tr>
							</tbody>
						</table>
						</td></tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td class="nocontent">검색된 결과값이 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
		
		<div id='navigator'>
			<c:if test="${pageBean.prePage}">
				<a href="javascript:go_page_mybookmark('${pageBean.startPage-pageBean.limit}','${param.userIdNum}')">이전</a>
			</c:if>
			
			<c:forEach begin="${pageBean.startPage}" end="${pageBean.endPage}" var='i'>
				<a href="javascript:go_page_mybookmark('${i}','${param.userIdNum}')">[ ${i} ]</a>
			</c:forEach>
				
			<c:if test="${pageBean.nextPage}">
				<a href="javascript:go_page_mybookmark('${pageBean.startPage+pageBean.limit}','${param.userIdNum}')">다음</a>
			</c:if>
		</div>
	</body>
</html>
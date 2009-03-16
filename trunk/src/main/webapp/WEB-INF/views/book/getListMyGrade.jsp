<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/library/library.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/library/library.js"></script>
		<title>책 별점 목록</title>
	</head>
	<body>
	
		<table id="mygradelist">
			<thead>
				<tr>
					<td colspan="4">책 별점 목록</td>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(bookGradeList)!=0}">
						<c:forEach begin="0" items="${bookGradeList}" var="bookGrade" varStatus="status">
							<tr>
								<td>
									<c:if test="${bookGrade.book.bookCover!=null && bookGrade.book.bookCover!=''}">
										<img src="${bookGrade.book.bookCover}"/>
									</c:if>
									<c:if test="${bookGrade.book.bookCover==null || bookGrade.book.bookCover==''}">
										<img src="/images/book/bookDefault.png"/>
									</c:if>		
								</td>
								<td><a href="javascript:getBook('${bookGrade.book.idNum}')">${bookGrade.book.name}</a></td>
								<td>
									<c:set var="count" value="0"/>
									<c:forEach begin="1" end="${bookGrade.grade}" var="i" >
										<c:set var="count" value="${i}"/>
										<img src="/images/book/star.png" width="15"/>
									</c:forEach>
											
									<c:forEach begin="${count}" end="4"><img src="/images/book/star1.png" width="15"/></c:forEach>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="4" class="nocontent">검색된 결과값이 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		
		<div id='navigator'>
			<c:if test="${pageBean.prePage}">
				<a href="javascript:go_page_mygrade('${pageBean.startPage-pageBean.limit}','${param.userIdNum}')">이전</a>
			</c:if>
			
			<c:forEach begin="${pageBean.startPage}" end="${pageBean.endPage}" var='i'>
				<a href="javascript:go_page_mygrade('${i}','${param.userIdNum}')">[ ${i} ]</a>
			</c:forEach>
				
			<c:if test="${pageBean.nextPage}">
				<a href="javascript:go_page_mygrade('${pageBean.startPage+pageBean.limit}','${param.userIdNum}')">다음</a>
			</c:if>
		</div>
		
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/common/default.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/library/library.js"></script>
		<title>서재 책 목록</title>
	</head>
	<body>
	
	
		<c:choose>
			<c:when test="${state==0}">읽고 싶은책</c:when>
			<c:when test="${state==1}">읽고 있는책</c:when>
			<c:when test="${state==2}">읽은 책</c:when>
		</c:choose>
	
		<table border="1">
			<thead></thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(libraryBookList)!=0}">
						<c:set var="bookListLength" value="${fn:length(libraryBookList)}"/>
						<c:set var="columCount" value="4"/>
						<c:set var="rowCount" value="${(bookListLength%columCount==0)? bookListLength/columCount : bookListLength/columCount+1}"/>
						<c:set var="aIndex" value="0"/>
						
						<c:forEach begin="1" end="${rowCount}" varStatus="status">
							<tr>
								<c:forEach begin="1" end="${columCount}" varStatus="istatus">
									<c:set var="bookInfo" value="${libraryBookList[aIndex].book}"/>
									<c:set var="userInfo" value="${libraryBookList[aIndex].library.user}"/>
									<td>
										<c:if test="${aIndex>bookListLength}">&nbsp;</c:if>
										<c:if test="${aIndex<bookListLength}">
											<img src="${bookInfo.bookCover}" width="72" height="102" onclick="checkBook('${bookInfo.ISBN10}')"/><br/>
											<a href="javascript:checkBook('${bookInfo.ISBN10}')">
												${bookInfo.name}
											</a><br/>
											${bookInfo.authors[0].name}<br/>
											
											<c:if test="${sessionScope.idNum!=null && userInfo.idNum==sessionScope.idNum}">
												<a href="/library/modifyLibraryBookView.do?libraryBookIdNum=${libraryBookList[aIndex].idNum}">수정</a>
												<a href="/library/deleteLibraryBook.do?libraryBookIdNum=${libraryBookList[aIndex].idNum}">삭제</a>
											</c:if>
											
										</c:if>
										
										
									</td>
									<c:set var="aIndex" value="${aIndex+1}"/>
								</c:forEach>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td>검색 결과가 없습니다.</td>
						</tr>
					</c:otherwise>				
				</c:choose>
			</tbody>
			<tfoot></tfoot>
		</table>	
		
		<div id='page_div'>
			<c:if test="${pageBean.prePage}">
				<a href="javascript:go_page_librarybook('${pageBean.startPage-pageBean.limit}','${libraryIdNum}','${state}')">이전</a>
			</c:if>
			
			<c:forEach begin="${pageBean.startPage}" end="${pageBean.endPage}" var='i'>
				<a href="javascript:go_page_librarybook('${i}','${libraryIdNum}','${state}')">[ ${i} ]</a>
			</c:forEach>
				
			<c:if test="${pageBean.nextPage}">
				<a href="javascript:go_page_librarybook('${pageBean.startPage+pageBean.limit}','${libraryIdNum}','${state}')">다음</a>
			</c:if>
		</div>
		
		<div id=''>
			<a href="javascript:history.go(-1)">뒤로</a>
		</div>
		
	</body>
</html>
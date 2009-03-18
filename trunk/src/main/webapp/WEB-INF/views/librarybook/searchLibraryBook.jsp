<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/common/default.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/library/library.js"></script>
		<title>서재내 책검색</title>
	</head>
	<body>
	
		<form action="/library/searchLibraryBookInLibrary.do" name="searchLibraryBookInLibraryform" method="post">
			<input type="hidden" name="libraryIdNum" value="${libraryIdNum}"/>
			<table>
				<tr>
					<td>${library.user.name}님 서재 책검색</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="bookName" size="20" />
						<input type="submit" value="검색"/>
					</td>
				</tr>
			</table>
		</form>
		
		<table border="1">
			<thead>
				<tr>
					<td>읽고싶은책, 읽은책, 읽고 있는책</td>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(libraryBookList)!=0}">
						<c:forEach begin="0" items="${libraryBookList}" varStatus="status" var="libraryBookInfo">
							<tr>
								<td>
									<table>
										<tr>
											<td rowspan="4">
												<c:if test="${libraryBookInfo.book.bookCover!=null && libraryBookInfo.book.bookCover!=''}">
													<img src="${libraryBookInfo.book.bookCover}"/>
												</c:if>
												<c:if test="${libraryBookInfo.book.bookCover==null || libraryBookInfo.book.bookCover==''}">
													<img src="/images/book/bookDefault.png"/>
												</c:if>	
											</td>
											<td> 제목 : ${libraryBookInfo.book.name}</td>
										</tr>
										<tr>
											<td> 지은이: 
												<c:forEach begin="0" items="${libraryBookInfo.book.authors}" var="authorInfo">
													[${authorInfo.name}/
													<c:if test="${authorInfo.authorDiv==0}">지음</c:if>
													<c:if test="${authorInfo.authorDiv==1}">옮김</c:if>
													]  
												</c:forEach>
											</td>
										</tr>
										<tr>
											<td>출판사 : ${libraryBookInfo.book.publishComp}</td>
										</tr>
										<tr>
											<td>책 분류 : 
												<c:choose>
													<c:when test="${libraryBookInfo.state==0}">읽고 싶은책</c:when>
													<c:when test="${libraryBookInfo.state==1}">읽고 있는책</c:when>
													<c:when test="${libraryBookInfo.state==2}">읽은 책</c:when>
												</c:choose>
											</td>
										</tr>
									</table>
								</td>
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
				<a href="/library/getListLibraryBook.do?${pageBean.startPage-pageBean.limit}&libraryIdNum=${libraryIdNum}&state=${state}">이전</a>
			</c:if>
			
			<c:forEach begin="${pageBean.startPage}" end="${pageBean.endPage}" var='i'>
				<a href="/library/getListLibraryBook.do?pageNo=${i}&libraryIdNum=${libraryIdNum}&state=${state}')">[ ${i} ]</a>
			</c:forEach>
				
			<c:if test="${pageBean.nextPage}">
				<a href="/library/getListLibraryBook.do?pageNo=${pageBean.startPage+pageBean.limit}&libraryIdNum=${libraryIdNum}&state=${state}">다음</a>
			</c:if>
		</div>

	</body>
</html>
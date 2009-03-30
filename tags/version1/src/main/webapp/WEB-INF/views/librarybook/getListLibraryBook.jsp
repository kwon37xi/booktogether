<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/library/library.js"></script>
		<title>서재 책 목록</title>
	</head>
	<body>
	
		<table id="listlibrarybook">
			<thead>
				<tr>
					<td colspan="4">
						<c:choose>
							<c:when test="${state==0}">읽고 싶은책</c:when>
							<c:when test="${state==1}">읽고 있는책</c:when>
							<c:when test="${state==2}">읽은 책</c:when>
						</c:choose>
					</td>
				</tr>
			</thead>
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
									<td class="list_book_info">
										<c:if test="${aIndex>bookListLength}">&nbsp;</c:if>
										<c:if test="${aIndex<bookListLength}">
											<a href="/book/getBook.do?bookIdNum=${bookInfo.idNum}">
												<c:if test="${bookInfo.bookCover!=null && bookInfo.bookCover!=''}">
													<img src="${bookInfo.bookCover}" width="72" height="102"/><br/>
												</c:if>
												<c:if test="${bookInfo.bookCover==null || bookInfo.bookCover==''}">
													<img src="/images/book/bookDefault.png" width="72" height="102"/><br/>
												</c:if>											
												${bookInfo.name}
											</a>
											<p>${bookInfo.authors[0].name}</p>
											<p>
												<c:if test="${sessionScope.idNum!=null && userInfo.idNum==sessionScope.idNum}">
													<a href="/library/modifyLibraryBookView.do?libraryIdNum=${library.idNum}&libraryBookIdNum=${libraryBookList[aIndex].idNum}">수정</a>
													<a href="/library/deleteLibraryBook.do?libraryIdNum=${library.idNum}&libraryBookIdNum=${libraryBookList[aIndex].idNum}">삭제</a>
												</c:if>
											</p>
										</c:if>
										
										
									</td>
									<c:set var="aIndex" value="${aIndex+1}"/>
								</c:forEach>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td class="nocontent">검색 결과가 없습니다.</td>
						</tr>
					</c:otherwise>				
				</c:choose>
			</tbody>
		</table>
		
		<div id='navigator'>
			<c:if test="${pageBean.prePage}">
				<a href="/library/getListLibraryBook.do?pageNo=${pageBean.startPage-pageBean.limit}&libraryIdNum=${libraryIdNum}&state=${state}">이전</a>
			</c:if>
			
			<c:forEach begin="${pageBean.startPage}" end="${pageBean.endPage}" var='i'>
				<a href="/library/getListLibraryBook.do?pageNo=${i}&libraryIdNum=${libraryIdNum}&state=${state}">[ ${i} ]</a>
			</c:forEach>
				
			<c:if test="${pageBean.nextPage}">
				<a href="/library/getListLibraryBook.do?pageNo=${pageBean.startPage+pageBean.limit}&libraryIdNum=${libraryIdNum}&state=${state}">다음</a>
			</c:if>
		</div>
		
	</body>
</html>
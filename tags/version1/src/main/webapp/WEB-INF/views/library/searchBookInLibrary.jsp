<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link rel="stylesheet" type="text/css" href="/styles/library/search_result.css" />
		<script type="text/javascript" charset="utf-8" src="/scripts/library/library.js"></script>
		<title>서재내 책 검색</title>
	</head>
	<body>
	
		<table class="searchresult_librarybook">
			<thead>
				<tr>
					<td>읽고싶은책, 읽은책, 읽고 있는책</td>
				</tr>
				<c:if test="${moreLibraryBookList}">
					<tr>
						<td>
							<a href="/library/searchLibraryBookInLibrary.do?libraryIdNum=${libraryIdNum}&bookName=${requestScope.bookName}">더보기</a>
						</td>
					</tr>
				</c:if>
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
											<td> 제목 : <a href="/book/getBook.do?bookIdNum=${libraryBookInfo.book.idNum}">${libraryBookInfo.book.name}</a></td>
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
							<td class="nocontent">검색 결과가 없습니다.</td>
						</tr>
					</c:otherwise>				
				</c:choose>
			</tbody>
			<tfoot></tfoot>
		</table>
		
			
		<table class="searchresult_possessbook">
			<thead>
				<tr>
					<td>소유하고 있는 책</td>
				</tr>	
				<c:if test="${morePossessBookList}">
					<tr>
						<td>
							<a href="/library/searchPossessBookInLibrary.do?libraryIdNum=${libraryIdNum}&bookName=${requestScope.bookName}">더보기</a>
						</td>
					</tr>	
				</c:if>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(possessBookList)!=0}">
						<c:forEach begin="0" items="${possessBookList}" varStatus="status" var="possessBookInfo">
							<tr>
								<td>
									<table>
										<tr>
											<td rowspan="4"><img src="${possessBookInfo.book.bookCover}"/></td>
											<td>제목 : <a href="/library/getPossessBook.do?&possessBookIdNum=${possessBookInfo.idNum}">${possessBookInfo.book.name}</a></td>
											<td>지은이 :
												<c:forEach begin="0" items="${possessBookInfo.book.authors}" var="authorInfo">
													[${authorInfo.name}/
													<c:if test="${authorInfo.authorDiv==0}">지음</c:if>
													<c:if test="${authorInfo.authorDiv==1}">옮김</c:if>
													]  
												</c:forEach>
											</td>
										</tr>
										<tr>
											<td>구입날짜 : <fmt:formatDate value="${possessBookInfo.purchaseDate}" pattern="yyyy.MM.dd"/></td>
											<td>구입가격 : <fmt:formatNumber value="${possessBookInfo.purchasePrice}" pattern=",###"/>원</td>
										</tr>
										<tr>
											<td>독서시작 : <fmt:formatDate value="${possessBookInfo.beginRead}" pattern="yyyy.MM.dd"/></td>
											<td>독서종료 : <fmt:formatDate value="${possessBookInfo.endRead}" pattern="yyyy.MM.dd"/></td>
										</tr>
										<tr>
											<td>책품질 :
												<c:choose>
													<c:when test="${possessBookInfo.quality==0}">상</c:when>
													<c:when test="${possessBookInfo.quality==1}">중</c:when>
													<c:when test="${possessBookInfo.quality==2}">하</c:when>
												</c:choose>
											</td>
											<td>책상태 :
												<c:choose>
													<c:when test="${possessBookInfo.state==0}">소유</c:when>
													<c:when test="${possessBookInfo.state==1}">대여중</c:when>
													<c:when test="${possessBookInfo.state==2}">교환중</c:when>
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
							<td class="nocontent">검색 결과가 없습니다.</td>
						</tr>
					</c:otherwise>				
				</c:choose>
			</tbody>
			<tfoot></tfoot>
		</table>	
	</body>
</html>
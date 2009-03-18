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
	
		<form action="/library/searchPossessBookInLibrary.do" name="searchPossessBookInLibraryform" method="post">
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
					<td>소유하고 있는 책</td>
				</tr>	
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(possessBookList)!=0}">
						<c:forEach begin="0" items="${possessBookList}" varStatus="status" var="possessBookInfo">
							<tr>
								<td>
									<table>
										<tr>
											<td rowspan="4">									
												<c:if test="${possessBook.book.bookCover!=null && possessBook.book.bookCover!=''}">
													<img src="${possessBook.book.bookCover}"/>
												</c:if>
												<c:if test="${possessBook.book.bookCover==null || possessBook.book.bookCover==''}">
													<img src="/images/book/bookDefault.png"/>
												</c:if>
											</td>
											<td>제목 : <a href="/library/getPossessBook.do?possessBookIdNum=${possessBookInfo.idNum}">${possessBookInfo.book.name}</a></td>
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
							<td>검색 결과가 없습니다.</td>
						</tr>
					</c:otherwise>				
				</c:choose>
			</tbody>
			<tfoot></tfoot>
		</table>	
		
		<div id='page_div'>
			<c:if test="${pageBean.prePage}">
				<a href="/library/getListLibraryBook.do?pageNo=${pageBean.startPage-pageBean.limit}&libraryIdNum=${libraryIdNum}&state=${state}')">이전</a>
			</c:if>
			
			<c:forEach begin="${pageBean.startPage}" end="${pageBean.endPage}" var='i'>
				<a href="/library/getListLibraryBook.do?pageNo=${i}&libraryIdNum=${libraryIdNum}&state=${state}')">[ ${i} ]</a>
			</c:forEach>
				
			<c:if test="${pageBean.nextPage}">
				<a href="/library/getListLibraryBook.do?pageNo=${pageBean.startPage+pageBean.limit}&libraryIdNum=${libraryIdNum}&state=${state}')">다음</a>
			</c:if>
		</div>

		
	</body>
</html>
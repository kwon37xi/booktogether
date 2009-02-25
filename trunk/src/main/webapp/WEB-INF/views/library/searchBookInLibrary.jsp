<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/common/default.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/library/library.js"></script>
		<title>서재내 책 검색</title>
	</head>
	<body>
	
		<c:if test="${sessionScope.message!=null}">
			<script>
				alert('${sessionScope.message}');
			</script>
			<c:remove scope="session" var="message"/>
		</c:if>
	
	
		<form action="/library/searchBookInLibrary.do" name="searchBookInLibraryform" method="post">
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
				<tr>
					<td>
						<c:if test="${moreLibraryBookList}">
							<a href="/library/searchLibraryBookInLibrary.do">더보기</a>
						</c:if>
					</td>
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
												<img src="${libraryBookInfo.book.bookCover}"/>
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
		
		<br/>
		<br/>
		<br/>
			
		<table border="1">
			<thead>
				<tr>
					<td>소유하고 있는 책</td>
				</tr>	
				<tr>
					<td>
						<c:if test="${morePosssessBookList}">
							<a href="/library/searchPossessBookInLibrary.do">더보기</a>
						</c:if>
					</td>
				</tr>	
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(posssessBookList)!=0}">
						<c:forEach begin="0" items="${posssessBookList}" varStatus="status" var="posssessBookInfo">
							<tr>
								<td>
									<table>
										<tr>
											<td rowspan="4"><img src="${posssessBookInfo.book.bookCover}"/></td>
											<td>제목 : <a href="javascript:getPossessBook('${posssessBookInfo.idNum}')">${posssessBookInfo.book.name}</a></td>
											<td>지은이 :
												<c:forEach begin="0" items="${posssessBookInfo.book.authors}" var="authorInfo">
													[${authorInfo.name}/
													<c:if test="${authorInfo.authorDiv==0}">지음</c:if>
													<c:if test="${authorInfo.authorDiv==1}">옮김</c:if>
													]  
												</c:forEach>
											</td>
										</tr>
										<tr>
											<td>구입날짜 : <fmt:formatDate value="${posssessBookInfo.purchaseDate}" pattern="yyyy.MM.dd"/></td>
											<td>구입가격 : <fmt:formatNumber value="${posssessBookInfo.purchasePrice}" pattern=",###"/>원</td>
										</tr>
										<tr>
											<td>독서시작 : <fmt:formatDate value="${posssessBookInfo.beginRead}" pattern="yyyy.MM.dd"/></td>
											<td>독서종료 : <fmt:formatDate value="${posssessBookInfo.endRead}" pattern="yyyy.MM.dd"/></td>
										</tr>
										<tr>
											<td>책품질 :
												<c:choose>
													<c:when test="${posssessBookInfo.quality==0}">상</c:when>
													<c:when test="${posssessBookInfo.quality==1}">중</c:when>
													<c:when test="${posssessBookInfo.quality==2}">하</c:when>
												</c:choose>
											</td>
											<td>책상태 :
												<c:choose>
													<c:when test="${posssessBookInfo.state==0}">소유</c:when>
													<c:when test="${posssessBookInfo.state==1}">대여중</c:when>
													<c:when test="${posssessBookInfo.state==2}">교환중</c:when>
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
	</body>
</html>
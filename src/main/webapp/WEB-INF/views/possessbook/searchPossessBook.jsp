<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
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
											<td rowspan="4"><img src="${possessBookInfo.book.bookCover}"/></td>
											<td>제목 : <a href="javascript:getPossessBook('${possessBookInfo.idNum}')">${possessBookInfo.book.name}</a></td>
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
			<a href="javascript:history.go(-1)">서재로 고고싱</a>
		</div>
		
	</body>
</html>
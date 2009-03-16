<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/possess/possessbook.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/library/library.js"></script>
		<title>내 생활반경 소유책 목록</title>
	</head>
	<body>
	
		<table class="zonenamelist">
			<thead>
				<tr>
					<td>생활반경 주소</td>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(zoneName)!=0}">
						<c:forEach begin="0" items="${zoneName}" var="zoneNameInfo">
							<tr>
								<td>${zoneNameInfo}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td class="nocontent"> 등록된 생활 반경이 없습니다. </td>
						</tr>
					</c:otherwise>				
				</c:choose>
			</tbody>
		</table>
		
	
	
		<table id="zonepossesslist">
			<thead>
				<tr>
					<td>내 생활반경 소유책 목록</td>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(possessBookList)!=0}">
					
						<c:forEach begin="0" items="${possessBookList}" var="possessBook" varStatus="status">
							<tr>
								<td>
									<table id="possessbook_info">
										<tbody>
											<tr>
												<td rowspan="4">
													<c:if test="${possessBook.book.bookCover!=null && possessBook.book.bookCover!=''}">
														<img src="${possessBook.book.bookCover}"/>
													</c:if>
													<c:if test="${possessBook.book.bookCover==null || possessBook.book.bookCover==''}">
														<img src="/images/book/bookDefault.png"/>
													</c:if>		
												</td>
												<td>제목 : <a href="javascript:getPossessBook('${possessBook.idNum}','${library.idNum}','${library.user.idNum}')">${possessBook.book.name}</a></td>
											</tr>
											<tr>
												<td>소유자  : ${possessBook.user.name}<a href="javascript:getLibrary('${possessBook.user.userId}')">(${possessBook.user.userId})</a></td>
											</tr>
											<tr>
												<td>책상태 :
													<c:choose>
														<c:when test="${possessBook.state==0}">소유</c:when>
														<c:when test="${possessBook.state==1}">대여중</c:when>
														<c:when test="${possessBook.state==2}">교환중</c:when>
													</c:choose>
												</td>
											</tr>
										</tbody>
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
		</table>	
		
		<div id='navigator'>
			<c:if test="${pageBean.prePage}">
				<a href="javascript:go_page_zonebook('${pageBean.startPage-pageBean.limit}','${param.userId}')">이전</a>
			</c:if>
			
			<c:forEach begin="${pageBean.startPage}" end="${pageBean.endPage}" var='i'>
				<a href="javascript:go_page_zonebook('${i}','${param.userId}')">[ ${i} ]</a>
			</c:forEach>
				
			<c:if test="${pageBean.nextPage}">
				<a href="javascript:go_page_zonebook('${pageBean.startPage+pageBean.limit}','${param.userId}')">다음</a>
			</c:if>
		</div>
		
	</body>
</html>
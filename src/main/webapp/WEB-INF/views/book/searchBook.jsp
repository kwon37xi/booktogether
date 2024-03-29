<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link rel="stylesheet" type="text/css" href="/styles/book/searchBook.css" />
		<script type="text/javascript" charset="utf-8" src="/scripts/book/book.js"></script>
		<title>책 검색</title>
	</head>
	<body>
	
		<table id="searchRank">
			<tr>
				<td colspan="${fn:length(searchRankQuerys)}">인기 검색 순위</td>
			</tr>
			<tr>
				<c:forEach begin="0" items="${searchRankQuerys}" var="queryInfo" varStatus="status">
					<td>${status.index+1}.${queryInfo}</td>
				</c:forEach>
			</tr>
		</table>
		
		<br/>
		<br/>
		
		<c:choose>
			<c:when test="${bookList!=null}">
				<table id="searchResultTable">
					<tbody>
						<c:choose>
							<c:when test="${fn:length(bookList)!=0}">
								<c:set var="bookListLength" value="${fn:length(bookList)}"/>
								<c:set var="columCount" value="4"/>
								<c:set var="rowCount" value="${(bookListLength%columCount==0)? bookListLength/columCount : bookListLength/columCount+1}"/>
								<c:set var="aIndex" value="0"/>
								<c:set var="bookList" value="${bookList}"/>
								
								<c:forEach begin="1" end="${rowCount}" varStatus="status">
									<tr>
										<c:forEach begin="1" end="${columCount}" varStatus="istatus">
											<td>
												<c:if test="${aIndex>bookListLength}">&nbsp;</c:if>
												<c:if test="${aIndex<bookListLength}">
													<c:if test="${bookList[aIndex].bookCover!=null && bookList[aIndex].bookCover!=''}">
														<img src="${bookList[aIndex].bookCover}" width="72" height="102" onclick="checkBook('${bookList[aIndex].ISBN10}','${pageNo}','${query}','${searchType}')" class="img_button"/><br/>
													</c:if>
													<c:if test="${bookList[aIndex].bookCover==null || bookList[aIndex].bookCover==''}">
														<img src="/images/book/bookDefault.png" width="72" height="102" onclick="checkBook('${bookList[aIndex].ISBN10}','${pageNo}','${query}','${searchType}')" class="img_button"/><br/>
													</c:if>	
													<a href="/book/checkBook.do?ISBN=${bookList[aIndex].ISBN10}&pageNo=${pageNo}&query=${query}&searchType=${searchType}">
														${fn:escapeXml(bookList[aIndex].name)}
													</a><br/>
													${bookList[aIndex].authors[0].name}<br/>
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
				</table>	
				
				<div id='page_div'>
					<c:if test="${pageBean.prePage}">
						<a href="javascript:go_page_search('${pageBean.startPage-pageBean.limit}')">이전</a>
					</c:if>
					
					<c:forEach begin="${pageBean.startPage}" end="${pageBean.endPage}" var='i'>
						<a href="javascript:go_page_search('${i}')">[ ${i} ]</a>
					</c:forEach>
						
					<c:if test="${pageBean.nextPage}">
						<a href="javascript:go_page_search('${pageBean.startPage+pageBean.limit}')">다음</a>
					</c:if>
				</div>
			</c:when>
			<c:otherwise>
				여기가 메인 화면입니다용
			</c:otherwise>		
		</c:choose>
	</body>
</html>
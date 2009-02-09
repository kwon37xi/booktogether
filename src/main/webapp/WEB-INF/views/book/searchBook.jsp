<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="../../styles/common/default.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="../../scripts/book/book.js"></script>
		<title>책 검색</title>
	</head>
	<body>
	
		<form name="searchBookform" action="/book/searchBook.do" method="post">
			<input type="hidden" name="pageno" value="1"/>
			<select name="searchType">
				<option value="all">전체</option>
				<option value="title" ${param.searchType=='title' ? 'selected' : ''}>제목</option>
				<option value="writer" ${param.searchType=='writer' ? 'selected' : ''}>지은이</option>
				<option value="isbn" ${param.searchType=='isbn' ? 'selected' : ''}>ISBN</option>
			</select>
			<input type="text" name="query" size="30" value="${param.query}"/> <input type="submit" value="검색"/>
		</form>
		
		<table border="1">
			<thead>
				<tr>
					<td colspan="4">결과물</td>
				</tr>	
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(book_list)!=0}">
						<c:set var="book_list_length" value="${fn:length(book_list)}"/>
						<c:set var="colum_count" value="4"/>
						<c:set var="row_count" value="${(book_list_length%colum_count==0)? book_list_length/colum_count : book_list_length/colum_count+1}"/>
						<c:set var="a_index" value="0"/>
						<c:set var="booklist" value="${book_list}"/>
						
						<c:forEach begin="1" end="${row_count}" varStatus="status">
							<tr>
								<c:forEach begin="1" end="${colum_count}" varStatus="istatus">
									<td>
										<c:if test="${a_index>book_list_length}">&nbsp;</c:if>
										<c:if test="${a_index<book_list_length}">
											<img src="${booklist[a_index].corver}" width="72" height="102" onclick="checkBook('${booklist[a_index].ISBN10}')"/><br/>
											<a href="javascript:checkBook('${booklist[a_index].ISBN10}')">
												${booklist[a_index].name}
											</a><br/>
											${booklist[a_index].authors[0].name}<br/>
										</c:if>
									</td>
									<c:set var="a_index" value="${a_index+1}"/>
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
			<c:if test="${pageBean.prepage}">
				<a href="javascript:go_page_search('${pageBean.startPage-pageBean.limit}')">이전</a>
			</c:if>
			
			<c:forEach begin="${pageBean.startPage}" end="${pageBean.endPage}" var='i'>
				<a href="javascript:go_page_search('${i}')">[ ${i} ]</a>
			</c:forEach>
				
			<c:if test="${pageBean.nextpage}">
				<a href="javascript:go_page_search('${pageBean.startPage+pageBean.limit}')">다음</a>
			</c:if>
		</div>
	</body>
</html>
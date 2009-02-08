<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>책 검색</title>
	</head>
	<body>
	
		<form name="searchBookform" action="/book/searchBook.do" method="post">
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
											<img src="${booklist[a_index].corver}"/><br/>
											${booklist[a_index].name}<br/>
											${booklist[a_index].authors[0].name}<br/>
											${booklist[a_index].ISBN10}
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
	</body>
</html>
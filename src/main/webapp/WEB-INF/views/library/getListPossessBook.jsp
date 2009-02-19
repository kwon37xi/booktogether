<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/common/default.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/book/book.js"></script>
		<title>소유책 목록</title>
	</head>
	<body>
	
		<c:if test="${sessionScope.message!=null}">
			<script>
				alert('${sessionScope.message}');
			</script>
			<c:remove scope="session" var="message"/>
		</c:if>
	
		<table border="1">
			<thead></thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(possessBookList)!=0}">
					
						<c:forEach begin="0" items="${possessBookList}" var="possessBook" varStatus="status">
							<tr>
								<td>
									<table border="1">
										<tbody>
											<tr>
												<td rowspan="4"><img src="${possessBook.book.bookCover}"/></td>
												<td>제목 : ${possessBook.book.name}</td>
												<td>지은이 :
													<c:forEach begin="0" items="${possessBook.book.authors}" var="authorInfo">
														[${authorInfo.name}/
														<c:if test="${authorInfo.authorDiv==0}">지음</c:if>
														<c:if test="${authorInfo.authorDiv==1}">옮김</c:if>
														]  
													</c:forEach>
												</td>
											</tr>
											<tr>
												<td>구입날짜 : <fmt:formatDate value="${possessBook.purchaseDate}" pattern="yyyy.MM.dd"/></td>
												<td>구입가격 : <fmt:formatNumber value="${possessBook.purchasePrice}" pattern=",###"/>원</td>
											</tr>
											<tr>
												<td>독서시작 : <fmt:formatDate value="${possessBook.beginRead}" pattern="yyyy.MM.dd"/></td>
												<td>독서종료 : <fmt:formatDate value="${possessBook.endRead}" pattern="yyyy.MM.dd"/></td>
											</tr>
											<tr>
												<td>책품질 :
													<c:choose>
														<c:when test="${possessBook.quality==0}">상</c:when>
														<c:when test="${possessBook.quality==1}">중</c:when>
														<c:when test="${possessBook.quality==2}">하</c:when>
													</c:choose>
												</td>
												<td>책상태 :
													<c:choose>
														<c:when test="${possessBook.state==0}">소유</c:when>
														<c:when test="${possessBook.state==1}">대여중</c:when>
														<c:when test="${possessBook.state==2}">교환중</c:when>
													</c:choose>
												 </td>
											</tr>
										</tbody>
										<tfoot>
											<tr>
												<td colspan="3">
													<a href="/library/modifyPossessBookView.do?possessBookIdNum=${possessBook.idNum}">수정</a> /
													<a href="/library/deletePossessBook.do?possessBookIdNum=${possessBook.idNum}">삭제</a> 
												  
												</td>
											</tr>
										</tfoot>
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
				<a href="javascript:go_page_search('${pageBean.startPage-pageBean.limit}')">이전</a>
			</c:if>
			
			<c:forEach begin="${pageBean.startPage}" end="${pageBean.endPage}" var='i'>
				<a href="javascript:go_page_search('${i}')">[ ${i} ]</a>
			</c:forEach>
				
			<c:if test="${pageBean.nextPage}">
				<a href="javascript:go_page_search('${pageBean.startPage+pageBean.limit}')">다음</a>
			</c:if>
		</div>
		
		<div id=''>
			<a href="javascript:history.go(-1)">뒤로</a>
		</div>
		
	</body>
</html>
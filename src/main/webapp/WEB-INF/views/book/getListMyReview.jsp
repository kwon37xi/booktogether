<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/common/default.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/library/library.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/common/common.js"></script>
		<title>책 리뷰 목록</title>
	</head>
	<body>
	
		<c:if test="${sessionScope.message!=null}">
			<script>
				alert('${sessionScope.message}');
			</script>
			<c:remove scope="session" var="message"/>
		</c:if>
		
		
		<table border='1'>
			<thead></thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(bookReviewList)!=0}">
						<c:forEach begin="0" items="${bookReviewList}" var="bookReview" varStatus="status">
							<tr>
								<td>
									<table>
										<tr>
											<td rowspan="4"><img src="${bookReview.book.bookCover}"/></td>
											<td>제목 : ${fn:escapeXml(bookReview.book.name)} </td>
										</tr>
										<tr>
											<td>지은이 : 
												<c:forEach begin="0" items="${bookReview.book.authors}" var="authorInfo">
													[${authorInfo.name}/
													<c:if test="${authorInfo.authorDiv==0}">지음</c:if>
													<c:if test="${authorInfo.authorDiv==1}">옮김</c:if>
													]  
												</c:forEach>
											</td>
										</tr>
										<tr>
											<td>
												리뷰제목 : <a href="/book/getReview.do?bookReviewIdNum=${bookReview.idNum}">
															${fn:escapeXml(bookReview.title)}
														 </a>
											</td>
										</tr>
										<tr>
											<td>공감수 : ${bookReview.recommend} </td>
										</tr>
								
									</table>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td>검색된 결과값이 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
			<tfoot>
				<tr>
					<td>
						<a href="javascript:history.go(-1)">뒤로</a>
					</td>
				</tr>
			</tfoot>
		</table>
		
		<div id='page_div'>
			<c:if test="${pageBean.prePage}">
				<a href="javascript:go_page_go_page_myreview('${pageBean.startPage-pageBean.limit}','${param.userIdNum}')">이전</a>
			</c:if>
			
			<c:forEach begin="${pageBean.startPage}" end="${pageBean.endPage}" var='i'>
				<a href="javascript:go_page_go_page_myreview('${i}','${param.userIdNum}')">[ ${i} ]</a>
			</c:forEach>
				
			<c:if test="${pageBean.nextPage}">
				<a href="javascript:go_page_go_page_myreview('${pageBean.startPage+pageBean.limit}','${param.userIdNum}')">다음</a>
			</c:if>
		</div>
		
	</body>
</html>
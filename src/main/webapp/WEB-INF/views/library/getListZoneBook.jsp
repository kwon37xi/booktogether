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
		<title>내 생활반경 소유책 목록</title>
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
											</tr>
											<tr>
												<td>소유자  : ${possessBook.user.name}(${possessBook.user.userId})</td>
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
											<tr>
												<td>지역명 : ${possessBook.user.zones[0].zonename} </td>
											</tr>
										</tbody>
										<tfoot></tfoot>
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
				<a href="javascript:go_page_zonebook('${pageBean.startPage-pageBean.limit}','${param.userId}')">이전</a>
			</c:if>
			
			<c:forEach begin="${pageBean.startPage}" end="${pageBean.endPage}" var='i'>
				<a href="javascript:go_page_zonebook('${i}','${param.userId}')">[ ${i} ]</a>
			</c:forEach>
				
			<c:if test="${pageBean.nextPage}">
				<a href="javascript:go_page_zonebook('${pageBean.startPage+pageBean.limit}','${param.userId}')">다음</a>
			</c:if>
		</div>
		
		<div id=''>
			<a href="javascript:history.go(-1)">뒤로</a>
		</div>
		
	</body>
</html>
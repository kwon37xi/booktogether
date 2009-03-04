<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<body>
	
		<c:if test="${sessionScope.message!=null}">
			<script>
				alert('${sessionScope.message}');
			</script>
			<c:remove scope="session" var="message"/>
		</c:if>
		
		<!-- 좋은글 -->
		<div id="goodWriter">
			<img src="/images/main/main_goodwriter.png" height="38px"/>
			<p>${goodWriter.content}</p>
		</div>
		
		<!-- 베스트 셀러 -->
		<div id="bestsellers">
			<img src="/images/main/main_bestsellers.png" height="38px"/>
			<table>
				<c:forEach begin="0" items="${bestSellerList}" var="bestBookInfo">
					<tr>
						<td>
							<img src="${bestBookInfo.bookCover}" class="img_button" onclick="go_bookView('${bestBookInfo.idNum}')"/>
						</td>
						<td>
							<p class="book_info">${fn:escapeXml(bestBookInfo.name)}</p>
							<p class="book_info">
								<c:forEach begin="0" items="${bestBookInfo.authors}" var="authorInfo">
									[${authorInfo.name}/
									<c:if test="${authorInfo.authorDiv==0}">지음</c:if>
									<c:if test="${authorInfo.authorDiv==1}">옮김</c:if>
									]  
								</c:forEach>
							</p>
							<p class="book_info"> ${bestBookInfo.ISBN10}</p>
							<p class="book_info"> ${bestBookInfo.publishComp}/
								${fn:substring(bestBookInfo.publishDate,0,4)}년 
								${fn:substring(bestBookInfo.publishDate,4,6)}월 
								${fn:substring(bestBookInfo.publishDate,6,8)}일
							</p>
							<p class="book_info"> ${bestBookInfo.category}</p>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
		
		<!-- 추천책 -->
		<div id="recommendBook">
			<img src="/images/main/main_recobook.png" height="38px"/>
			<table>
				<c:forEach begin="0" items="${recoBookList}" var="recommendBookInfo">
					<tr>
						<td>
							<img src="${recommendBookInfo.bookCover}" class="img_button" onclick="go_bookView('${recommendBookInfo.idNum}')"/>
						</td>
						<td>
							<p class="book_info"> ${fn:escapeXml(recommendBookInfo.name)}</p>
							<p class="book_info"><c:forEach begin="0" items="${recommendBookInfo.authors}" var="authorInfo">
									[${authorInfo.name}/
									<c:if test="${authorInfo.authorDiv==0}">지음</c:if>
									<c:if test="${authorInfo.authorDiv==1}">옮김</c:if>
									]  
								</c:forEach></p>
							<p class="book_info"> ${recommendBookInfo.ISBN10}</p>
							<p class="book_info"> ${recommendBookInfo.publishComp}/
								${fn:substring(recommendBookInfo.publishDate,0,4)}년 
								${fn:substring(recommendBookInfo.publishDate,4,6)}월 
								${fn:substring(recommendBookInfo.publishDate,6,8)}일
							</p>
							<p class="book_info"> ${recommendBookInfo.category}</p>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
	</body>
</html>
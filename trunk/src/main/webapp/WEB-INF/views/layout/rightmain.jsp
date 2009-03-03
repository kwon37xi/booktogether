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
		<fieldset id="goodWriter">
			<legend>이 주의 좋은글</legend>
			<p>${goodWriter.content}</p>
		</fieldset>
		
		<!-- 베스트 셀러 -->
		<fieldset id="bestsellers">
			<legend>베스트 셀러</legend>
			<table>
				<c:forEach begin="0" items="${bestSellerList}" var="bestBookInfo">
					<tr>
						<td>
							<img src="${bestBookInfo.bookCover}" class="imgpoinger" onclick="go_bookView('${bestBookInfo.idNum}')"/>
						</td>
						<td>
							<ul>
								<li> ${fn:escapeXml(bestBookInfo.name)}</li>
								<li>    
									<c:forEach begin="0" items="${bestBookInfo.authors}" var="authorInfo">
										[${authorInfo.name}/
										<c:if test="${authorInfo.authorDiv==0}">지음</c:if>
										<c:if test="${authorInfo.authorDiv==1}">옮김</c:if>
										]  
									</c:forEach>
								</li>
								<li> ${bestBookInfo.ISBN10}</li>
								<li> ${bestBookInfo.publishComp}/
									${fn:substring(bestBookInfo.publishDate,0,4)}년 
									${fn:substring(bestBookInfo.publishDate,4,6)}월 
									${fn:substring(bestBookInfo.publishDate,6,8)}일
								</li>
								<li> ${bestBookInfo.category}</li>
							</ul>
						</td>
					</tr>
				</c:forEach>
			</table>
		</fieldset>
		
		<!-- 추천책 -->
		<fieldset id="recommendBook">
			<legend>추천책</legend>
			<table>
				<c:forEach begin="0" items="${bestSellerList}" var="recommendBookInfo">
					<tr>
						<td>
							<img src="${recommendBookInfo.bookCover}" class="imgpoinger" onclick="go_bookView('${recommendBookInfo.idNum}')"/>
						</td>
						<td>
							<ul>
								<li> ${fn:escapeXml(recommendBookInfo.name)}</li>
								<li>    
									<c:forEach begin="0" items="${recommendBookInfo.authors}" var="authorInfo">
										[${authorInfo.name}/
										<c:if test="${authorInfo.authorDiv==0}">지음</c:if>
										<c:if test="${authorInfo.authorDiv==1}">옮김</c:if>
										]  
									</c:forEach>
								</li>
								<li> ${recommendBookInfo.ISBN10}</li>
								<li> ${recommendBookInfo.publishComp}/
									${fn:substring(recommendBookInfo.publishDate,0,4)}년 
									${fn:substring(recommendBookInfo.publishDate,4,6)}월 
									${fn:substring(recommendBookInfo.publishDate,6,8)}일
								</li>
								<li> ${recommendBookInfo.category}</li>
							</ul>
						</td>
					</tr>
				</c:forEach>
			</table>
		</fieldset>
		
	</body>
</html>
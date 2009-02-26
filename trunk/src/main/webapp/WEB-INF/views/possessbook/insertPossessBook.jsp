<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/common/default.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/book/book.js"></script>
		<title>내 소유 등록하기</title>
	</head>
	<body>
		
		<c:if test="${sessionScope.message!=null}">
			<script>
				alert('${sessionScope.message}');
			</script>
			<c:remove scope="session" var="message"/>
		</c:if>
		
		책정보
		<table border='1'>
			<thead></thead>
			<tbody>
				<tr>
					<td>책표지</td>
					<td><img src="${bookInfo.bookCover}"/></td>
				</tr>
				<tr>
					<td>책이름</td>
					<td>${bookInfo.name}</td>
				</tr>
				<tr>
					<td>지은이</td>
					<td>
						<c:forEach begin="0" items="${bookInfo.authors}" var="authorInfo">
							[${authorInfo.name}/
							<c:if test="${authorInfo.authorDiv==0}">지음</c:if>
							<c:if test="${authorInfo.authorDiv==1}">옮김</c:if>
							]  
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td>ISBN</td>
					<td>${bookInfo.ISBN10}</td>
				</tr>
				<tr>
					<td>출판사/출판일</td>
					<td>${bookInfo.publishComp}/
						${fn:substring(bookInfo.publishDate,0,4)}년 
						${fn:substring(bookInfo.publishDate,4,6)}월 
						${fn:substring(bookInfo.publishDate,6,8)}일
					</td>
				</tr>
				<tr>
					<td>가격</td>
					<td><fmt:formatNumber value="${bookInfo.price}" pattern=",###"/>원</td>
				</tr>
				<tr>
					<td>카테고리</td>
					<td>${bookInfo.category}</td>
				</tr>
				<tr>
					<td>설명</td>
					<td>${bookInfo.description}</td>
				</tr>
			</tbody>
			<tfoot></tfoot>
		</table>
		
		내 소유책 등록
		<form name="insertPossessBookform" method="post" action="/library/insertPossessBook.do">
		
			<input type="hidden" name="book.idNum" value="${bookInfo.idNum}"/>
			<input type="hidden" name="library.idNum" value="${library.idNum}"/>
			
			<table border='1'>
				<thead></thead>
				<tbody>
					<tr>
						<td>
							<div>
								<table>
									<tr>
										<td>구입날짜</td>
										<td>
											<input type="text" name="purchaseDateYear" size="4"/>
											<input type="text" name="purchaseDateMonth" size="2"/>
											<input type="text" name="purchaseDateDate" size="2"/>
										</td>
									</tr>
									<tr>
										<td>구입가격</td>
										<td>
											<input type="text" name="purchasePrice" size="20"/>
										</td>
									</tr>
									<tr>
										<td>독서시작일</td>
										<td>
											<input type="text" name="beginReadYear" size="4"/>
											<input type="text" name="beginReadMonth" size="2"/>
											<input type="text" name="beginReadDate" size="2"/>
										</td>
									</tr>
									<tr>
										<td>독서종료일</td>
										<td>
											<input type="text" name="endReadYear" size="4"/>
											<input type="text" name="endReadMonth" size="2"/>
											<input type="text" name="endReadDate" size="2"/>
										</td>
									</tr>
									<tr>
										<td>책품질</td>
										<td>
											<select name="quality">
												<option value="0">상</option>
												<option value="1">중</option>
												<option value="2">하</option>
											</select>
										</td>
									</tr>
									<tr>
										<td>책상태</td>
										<td>
											<select name="bookstate">
												<option value="0">소유</option>
												<option value="1">대여중</option>
												<option value="2">교환중</option>
											</select>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td>
							<input type="submit" value="서재에 등록"/>
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
		<div id=''>
			<a href="javascript:history.go(-1)">뒤로</a>
		</div>
		
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="../../styles/common/default.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="../../scripts/book/book.js"></script>
		<title>책조회</title>
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
				<tr>
					<td>책표지</td>
					<td><img src="${book_info.corver}"/></td>
				</tr>
				<tr>
					<td>책이름</td>
					<td>${book_info.name}</td>
				</tr>
				<tr>
					<td>지은이</td>
					<td>
						<c:forEach begin="0" items="${book_info.authors}" var="author_info">
							[${author_info.name}/
							<c:if test="${author_info.author_div==0}">지음</c:if>
							<c:if test="${author_info.author_div==1}">옮김</c:if>
							]  
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td>ISBN</td>
					<td>${book_info.ISBN10}</td>
				</tr>
				<tr>
					<td>출판사/출판일</td>
					<td>${book_info.publish_comp}/
						${fn:substring(book_info.publish_date,0,4)}년 
						${fn:substring(book_info.publish_date,4,6)}월 
						${fn:substring(book_info.publish_date,6,8)}일
					</td>
				</tr>
				<tr>
					<td>가격</td>
					<td><fmt:formatNumber value="${book_info.price}" pattern=",###"/>원</td>
				</tr>
				<tr>
					<td>카테고리</td>
					<td>${book_info.category}</td>
				</tr>
				<tr>
					<td>설명</td>
					<td>${book_info.description}</td>
				</tr>
			</tbody>
			<tfoot></tfoot>
		</table>
		
		<br/><br/>
		<table border="1">
			<thead>
				<tr>
					<td colspan="2">별점 테이블</td>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(bookgradelist)!=0}">
						<c:forEach begin="0" items="${bookgradelist}" var="grade_info" varStatus="status">
							<tr>
								<td>
									<c:set var="count" value="0"/>
									<c:forEach begin="1" end="${grade_info.grade}" var="i" >
										<c:set var="count" value="${i}"/>★
									</c:forEach>
									
									<c:forEach begin="${count}" end="4">☆</c:forEach>
								</td>
								<td>
									${grade_info.user.user_id}(${grade_info.user.nickname})
									<c:if test="${grade_info.user.user_id==sessionScope.user_id}">
										<a href="/book/deleteBookGrade.do?id=${grade_info.id}&book_id=${book_info.id}">삭제</a>
									</c:if>
								</td>
							</tr>	
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="2">별점이 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
			<tfoot></tfoot>
		</table>
		
		<c:if test="${sessionScope.id!=null && !existGrade}">
			<form name="bookgradeform" method="post" action="/book/insertBookGrade.do">
				<input type="hidden" name="book_id" value="${book_info.id}"/>
				<table border="1">
					<thead>
						<tr>
							<td>별점 하기</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
							    <input type="radio" name="grade" value="0"/>☆☆☆☆☆<br/>
							    <input type="radio" name="grade" value="1"/>★☆☆☆☆<br/>
							    <input type="radio" name="grade" value="2"/>★★☆☆☆<br/>
							    <input type="radio" name="grade" value="3"/>★★★☆☆<br/>
							    <input type="radio" name="grade" value="4"/>★★★★☆<br/>
							    <input type="radio" name="grade" value="5"/>★★★★★<br/>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td><input type="submit" value="별점하기"/></td>
						</tr>
					</tfoot>
				</table>
			</form>
		</c:if>
		
		
		<br/><br/>
		<table border="1">
			<thead>
				<tr>
					<td colspan="3">리뷰 테이블</td>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(bookreviewlist)!=0}">
						<c:forEach begin="0" items="${bookreviewlist}" var="review_info" varStatus="status">
							<tr>
								<td><a href="/book/getReview.do?id=${review_info.id}">${review_info.title}</a></td>
								<td>${review_info.user.user_id}(${review_info.user.nickname})</td>
								<td>추천수 : ${review_info.recommend}</td>
							</tr>	
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="3">리뷰가 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
			<tfoot></tfoot>
		</table>
		
		
		<c:choose>
			<c:when test="${sessionScope.id!=null && existReview}">
				내가 작성한 리뷰 정보
				<form name="myreviewform" method="post">
					<input type="hidden" name="book_id" value="${book_info.id}"/> 
					
					<input type="button" value="조회" onclick="getMyReviewView()"/>
					<input type="button" value="수정" onclick="modifyReviewView()"/>
					<input type="button" value="삭제" onclick="deleteReviewView()"/>
				</form>
			</c:when>
			
			<c:when test="${sessionScope.id!=null && !existReview}">
				내가 작성한 리뷰 정보
				<form name="myreviewform" method="post">
					<input type="hidden" name="book_id" value="${book_info.id}"/> 
					<input type="button" value="등록" onclick="insertReviewView()"/>
				</form>
			</c:when>
		</c:choose>
		
		
		<div id=''>
			<a href="javascript:history.go(-1)">뒤로</a>
			<a href="/book/searchBook.do">목록</a>
		</div>
		
	</body>
</html>
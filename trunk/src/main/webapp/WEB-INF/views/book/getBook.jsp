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
					<td><img src="${bookInfo.bookCover}"/></td>
				</tr>
				<tr>
					<td>책이름</td>
					<td>${fn:escapeXml(bookInfo.name)}</td>
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
		
		<br/><br/>
		<table border="1">
			<thead>
				<tr>
					<td colspan="2">별점 테이블</td>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(bookGradeList)!=0}">
						<c:forEach begin="0" items="${bookGradeList}" var="gradeInfo" varStatus="status">
							<tr>
								<td>
									<c:set var="count" value="0"/>
									<c:forEach begin="1" end="${gradeInfo.grade}" var="i" >
										<c:set var="count" value="${i}"/>★
									</c:forEach>
									
									<c:forEach begin="${count}" end="4">☆</c:forEach>
								</td>
								<td>
									${gradeInfo.user.userId}(${gradeInfo.user.nickname})
									<c:if test="${gradeInfo.user.userId==sessionScope.userId}">
										<a href="/book/deleteBookGrade.do?bookGradeIdNum=${gradeInfo.idNum}&bookIdNum=${gradeInfo.book.idNum}">삭제</a>
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
		
		<c:if test="${sessionScope.idNum!=null && !existGrade}">
			<form name="bookgradeform" method="post" action="/book/insertBookGrade.do">
				<input type="hidden" name="bookIdNum" value="${bookInfo.idNum}"/>
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
					<c:when test="${fn:length(bookReviewList)!=0}">
						<c:forEach begin="0" items="${bookReviewList}" var="reviewInfo" varStatus="status">
							<tr>
								<td><a href="/book/getReview.do?bookReviewIdNum=${reviewInfo.idNum}">${fn:escapeXml(reviewInfo.title)}</a></td>
								<td>${reviewInfo.user.userId}(${reviewInfo.user.nickname})</td>
								<td>추천수 : ${reviewInfo.recommend}</td>
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
			<c:when test="${sessionScope.idNum!=null && existReview}">
				내가 작성한 리뷰 정보
				<form name="myreviewform" method="post">
					<input type="hidden" name="bookIdNum" value="${bookInfo.idNum}"/> 
					<input type="button" value="조회" onclick="getMyReviewView()"/>
					<input type="button" value="수정" onclick="modifyReviewView()"/>
					<input type="button" value="삭제" onclick="deleteReviewView()"/>
				</form>
			</c:when>
			
			<c:when test="${sessionScope.idNum!=null && !existReview}">
				내가 작성한 리뷰 정보
				<form name="myreviewform" method="post">
					<input type="hidden" name="bookIdNum" value="${bookInfo.idNum}"/> 
					<input type="button" value="등록" onclick="insertReviewView()"/>
				</form>
			</c:when>
		</c:choose>
		
		<br/>
		<br/>
		
		<table border="1">
			<thead>
				<tr>
					<td colspan="4">인용구 테이블</td>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(bookMarkList)!=0}">
						<c:forEach begin="0" items="${bookMarkList}" var="bookMarkInfo" varStatus="status">
							<tr>
								<td>(p.${bookMarkInfo.page})${fn:escapeXml(bookMarkInfo.content)}/
									<fmt:formatDate value="${bookMarkInfo.inputDate}" pattern="yyyy. MM. dd"/>
								</td>
								<td>
									${bookMarkInfo.user.userId}(${bookMarkInfo.user.nickname})
									<c:if test="${sessionScope.idNum!=null && bookMarkInfo.user.idNum==sessionScope.idNum}">
										<input type="button" value="삭제" onclick="deleteBookMark('${bookMarkInfo.idNum}','${bookInfo.idNum}')"/>
									</c:if>
								</td>
								
								<td>공감수 : ${bookMarkInfo.vibeNum}</td>
								
								<c:if test="${sessionScope.idNum!=null}">
									<td>
											<input type="button" value="공감하기" onclick="modifyVibe('${bookMarkInfo.idNum}','${bookInfo.idNum}')"/>
									</td>
								</c:if>
							</tr>	
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="4">인용구가 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
			<tfoot></tfoot>
		</table>
		
		<br/>
		<br/>
		
		<c:if test="${sessionScope.idNum!=null}">
			<form action="/book/insertBookMark.do" method="post" name="insertbookmarkform">
				<input type="hidden" name="bookIdNum" value="${bookInfo.idNum}"/>
				<table>
					<thead>
						<tr>
							<td colspan="2">인용구 작성</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>페이지</td>
							<td>p.<input type="text" name="page" size="4"/></td>
						</tr>
						<tr>
							<td>인용구</td>
							<td>
								<textarea rows="3" cols="50" name="content"></textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<input type="submit" value="등록"/>
							</td>
						</tr>
					</tfoot>
				</table>
			</form>
		</c:if>
		
		
		
		
		<div id=''>
			<a href="javascript:history.go(-1)">뒤로</a>
			<a href="/book/searchBook.do">목록</a>
			<a href="/library/insertLibraryBookView.do?bookIdNum=${bookInfo.idNum}">내서재에 등록</a>
		</div>
		
	</body>
</html>
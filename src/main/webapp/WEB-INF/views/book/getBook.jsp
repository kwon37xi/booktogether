<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link rel="stylesheet" type="text/css" href="/styles/book/getbook.css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/book/book.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/book/getbook.js"></script>
		<title>책조회</title>
	</head>
	<body>

		<table id="bookinfo">
			<thead>
				<tr>
					<td colspan="4">책정보</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="b_label" rowspan="4">책표지</td>
					<td class="b_label_c" rowspan="4">
						<c:if test="${bookInfo.bookCover!=null && bookInfo.bookCover!=''}">
							<img src="${bookInfo.bookCover}"/>
						</c:if>
						<c:if test="${bookInfo.bookCover==null || bookInfo.bookCover==''}">
							<img src="/images/book/bookDefault.png"/>
						</c:if>					
					</td>
					<td class="b_label">책이름</td>
					<td class="b_label_c">${fn:escapeXml(bookInfo.name)}</td>
				</tr>
				<tr>
					<td class="b_label">지은이</td>
					<td class="b_label_c">
						<c:forEach begin="0" items="${bookInfo.authors}" var="authorInfo">
							[${authorInfo.name}/
							<c:if test="${authorInfo.authorDiv==0}">지음</c:if>
							<c:if test="${authorInfo.authorDiv==1}">옮김</c:if>
							]  
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td class="b_label">ISBN</td>
					<td class="b_label_c">${bookInfo.ISBN10}</td>
				</tr>
				<tr>
					<td class="b_label">출판사/출판일</td>
					<td class="b_label_c">${bookInfo.publishComp}/
						${fn:substring(bookInfo.publishDate,0,4)}년 
						${fn:substring(bookInfo.publishDate,4,6)}월 
						${fn:substring(bookInfo.publishDate,6,8)}일
					</td>
				</tr>
				<tr>
					<td class="b_label">가격</td>
					<td class="b_label_c"><fmt:formatNumber value="${bookInfo.price}" pattern=",###"/>원</td>
					<td class="b_label">카테고리</td>
					<td class="b_label_c">${bookInfo.category}</td>
				</tr>
				<tr>
					<td class="b_label">설명</td>
					<td class="b_label_c"  colspan="3">${bookInfo.description}</td>
				</tr>
			</tbody>
		</table>
		
		<table id="possessBookList">
			<thead>
				<tr>
					<td colspan="5">책소유자 목록(<fmt:formatNumber value="${possessDbCount}" pattern=",###"/>개)</td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${morePossessBook}">
					<tr>
						<td colspan="2" class="morecontent">
							<a href="/possessBook/getListPossessBookIdNum.do?bookIdNum=${bookInfo.idNum}">더보기</a>
						</td>
					</tr>
				</c:if>
				<c:choose>
					<c:when test="${fn:length(possessBookList)!=0}">
						<c:set var="bookListLength" value="${fn:length(possessBookList)}"/>
						<c:set var="columCount" value="5"/>
						<c:set var="rowCount" value="${(bookListLength%columCount==0)? bookListLength/columCount : bookListLength/columCount+1}"/>
						<c:set var="aIndex" value="0"/>
						<c:set var="bookList" value="${possessBookList}"/>
						
						<c:forEach begin="1" end="${rowCount}" varStatus="status">
							<tr>
								<c:forEach begin="1" end="${columCount}" varStatus="istatus">
									<td class="posuser">
										<c:if test="${aIndex>bookListLength}">&nbsp;</c:if>
										<c:if test="${aIndex<bookListLength}">
											<a href="/library/getLibrary.do?userId=${bookList[aIndex].user.userId}">
												<img src="/images/user/thumnail/${bookList[aIndex].user.name}" width="80" height="80"/>
												<br/>
												${bookList[aIndex].user.userId}
											</a>
										</c:if>
									</td>
									<c:set var="aIndex" value="${aIndex+1}"/>
								</c:forEach>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5" class="nocontent">소유중인 책이 없습니다</td>
						</tr>
					</c:otherwise>				
				</c:choose>
			</tbody>
		</table>
		
		<table id="gradebook">
			<thead>
				<tr>
					<td colspan="2">별점목록(<fmt:formatNumber value="${gradeDbCount}" pattern=",###"/>개)</td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${moreGrade}">
					<tr>
						<td colspan="2" class="morecontent">
							<a href="/book/getListBookGrade.do?bookIdNum=${bookInfo.idNum}">더보기</a>
						</td>
					</tr>
				</c:if>
				<c:choose>
					<c:when test="${fn:length(bookGradeList)!=0}">
						<c:forEach begin="0" items="${bookGradeList}" var="gradeInfo" varStatus="status">
							<tr>
								<td class="b_label">
									<c:set var="count" value="0"/>
									<c:forEach begin="1" end="${gradeInfo.grade}" var="i" >
										<c:set var="count" value="${i}"/><img src="/images/book/star.png" width="10"/>
									</c:forEach>
									
									<c:forEach begin="${count}" end="4">
										<img src="/images/book/star1.png" width="10"/>
									</c:forEach>
								</td>
								<td class="b_label_c">
									<a href="/library/getLibrary.do?userId=${gradeInfo.user.userId}">${gradeInfo.user.userId}(${gradeInfo.user.nickname})</a>
									<c:if test="${gradeInfo.user.userId==sessionScope.userId}">
										<a href="javascript:deleteBookGrade('${gradeInfo.idNum}','${gradeInfo.book.idNum}','${param.pageNo}','${param.query}','${param.searchType}')">삭제</a>
									</c:if>
								</td>
							</tr>	
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="2" class="nocontent">별점이 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
			<tfoot></tfoot>
		</table>
		
		
		
		
		<table id="reviewbook">
			<thead>
				<tr>
					<td colspan="3">리뷰목록(<fmt:formatNumber value="${reviewDbCount}" pattern=",###"/>개)</td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${moreReview}">
					<tr>
						<td colspan="3" class="morecontent">
							<a href="/book/getListBookReview.do?bookIdNum=${bookInfo.idNum}">더보기</a>
						</td>
					</tr>
				</c:if>
				<c:choose>
					<c:when test="${fn:length(bookReviewList)!=0}">
						<c:forEach begin="0" items="${bookReviewList}" var="reviewInfo" varStatus="status">
							<tr>
								<td class="btitle"><a href="/book/getReview.do?bookReviewIdNum=${reviewInfo.idNum}">${fn:escapeXml(reviewInfo.title)}</a></td>
								<td class="buserid">
									<a href="/library/getLibrary.do?userId=${reviewInfo.user.userId}">${reviewInfo.user.userId}(${reviewInfo.user.nickname})</a>
								</td>
								<td class="brecommend">추천수 : ${reviewInfo.recommend}</td>
							</tr>	
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="3" class="nocontent">리뷰가 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		
		
		<table id="bookmark">
			<thead>
				<tr>
					<td colspan="4">인용구목록(<fmt:formatNumber value="${bookMarkDbCount}" pattern=",###"/>개)</td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${moreBookMark}">
					<tr>
						<td colspan="4" class="morecontent">
							<a href="/book/getListBookMark.do?bookIdNum=${bookInfo.idNum}">더보기</a>
						</td>
					</tr>
				</c:if>
				<c:choose>
					<c:when test="${fn:length(bookMarkList)!=0}">
						<c:forEach begin="0" items="${bookMarkList}" var="bookMarkInfo" varStatus="status">
							<tr>
								<td class="bmcontent">(p.${bookMarkInfo.page})${bookMarkInfo.content}/
									<fmt:formatDate value="${bookMarkInfo.inputDate}" pattern="yyyy. MM. dd"/>
								</td>
								<td class="bmnickname">
									<a href="/library/getLibrary.do?userId=${bookMarkInfo.user.userId}">${bookMarkInfo.user.userId}(${bookMarkInfo.user.nickname})</a>
									<c:if test="${sessionScope.idNum!=null && bookMarkInfo.user.idNum==sessionScope.idNum}">
										<input type="button" value="삭제" onclick="deleteBookMark('${bookMarkInfo.idNum}','${bookInfo.idNum}')"/>
									</c:if>
								</td>
								
								<td class="bmvibe">공감수 : ${bookMarkInfo.vibeNum}</td>
								
								<c:if test="${sessionScope.idNum!=null && bookMarkInfo.user.idNum!=sessionScope.idNum}">
									<td>
										<input type="button" value="공감하기" onclick="modifyVibe('${bookMarkInfo.idNum}','${bookInfo.idNum}')"/>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="4" class="nocontent">인용구가 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		
		
		<table id="input_content">
			<thead>
				<tr>
					<td>입력하기</td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${sessionScope.idNum==null}">
					<tr>
						<td class="nocontent">로그인을 하시면 별점, 리뷰, 인용구를 입력할 수 있습니다</td>
					</tr>
				</c:if>
				<c:if test="${sessionScope.idNum!=null && !existGrade}">
					<tr>
						<td>
							<form name="bookgradeform" method="post" action="/book/insertBookGrade.do">
								<input type="hidden" name="bookIdNum" value="${bookInfo.idNum}"/>
								<input type="hidden" name="pageNo" value="${param.pageNo}"/>
								<input type="hidden" name="query" value="${param.query}"/>
								<input type="hidden" name="searchType" value="${param.searchType}"/>
								<table>
									<thead>
										<tr>
											<td colspan="2">별점주기</td>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>
											    <input type="radio" name="grade" value="0"/>0개
											    <input type="radio" name="grade" value="1"/>1개
											    <input type="radio" name="grade" value="2"/>2개
											    <input type="radio" name="grade" value="3"/>3개
											    <input type="radio" name="grade" value="4"/>4개
											    <input type="radio" name="grade" value="5"/>5개
											</td>
											<td><input type="button" value="별점주기" onclick="insertBookgrade()"/></td>
										</tr>
									</tbody>
								</table>
							</form>
						</td>
					</tr>
				</c:if>
				<c:if test="${sessionScope.idNum!=null && existReview}">
					<tr>
						<td>
							<form name="myreviewform" method="post">
								<table>
									<thead>
										<tr>
											<td>내가 작성한 리뷰 정보</td>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>
												<input type="hidden" name="bookIdNum" value="${bookInfo.idNum}"/> 
												<input type="button" value="리뷰조회" onclick="getMyReviewView()"/>
												<input type="button" value="리뷰수정" onclick="modifyReviewView()"/>
												<input type="button" value="리뷰삭제" onclick="deleteReviewView()"/>
											</td>
										</tr>
									</tbody>
								</table>
							</form>
						</td>
					</tr>						
				</c:if>
				<c:if test="${sessionScope.idNum!=null && !existReview}">
					<tr>
						<td>
							<form name="myreviewform" method="post">
								<table>
									<thead>
										<tr>
											<td>리뷰를 등록하시겠습니까?</td>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>
												<input type="hidden" name="bookIdNum" value="${bookInfo.idNum}"/> 
												<input type="button" value="등록" onclick="insertReviewView()"/>
											</td>
										</tr>
									</tbody>
								</table>							
							</form>
						</td>
					</tr>
				</c:if>
				<c:if test="${sessionScope.idNum!=null}">
					<tr>
						<td>
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
												<textarea rows="2" cols="50" name="content"></textarea>
											</td>
										</tr>
									</tbody>
									<tfoot>
										<tr>
											<td colspan="2">
												<input type="button" value="인용구 등록" onclick="insertBookMark()"/>
											</td>
										</tr>
									</tfoot>
								</table>
							</form>
						</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		
		
		<div id='navigator'>
			<a href="javascript:history.go(-1)">뒤로</a>
			<c:if test="${sessionScope.idNum!=null}">
				<a href="/library/insertLibraryBookView.do?bookIdNum=${bookInfo.idNum}">내서재에 등록</a>
				<a href="/library/insertPossessBookView.do?bookIdNum=${bookInfo.idNum}">내소유책으로 등록</a>
			</c:if>
		</div>
		
	</body>
</html>
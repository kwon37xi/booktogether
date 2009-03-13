<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link rel="stylesheet" type="text/css" href="/styles/library/library.css" />
		<link href="/styles/common/jquery-ui-1.7.custom.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/common/jquery-ui-1.7.min.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/common/datepicker.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/book/book.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/librarybook/insertlibrarybook.js"></script>
		
		<title>서재에 등록하기</title>
	</head>
	<body>
		
		<table id="bookinfo_simple">
			<thead>
				<tr>
					<td colspan="2">책정보</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="b_label">책표지</td>
					<td class="b_label_c">
						<c:if test="${bookInfo.bookCover!=null && bookInfo.bookCover!=''}">
							<img src="${bookInfo.bookCover}"/>
						</c:if>
						<c:if test="${bookInfo.bookCover==null || bookInfo.bookCover==''}">
							<img src="/images/book/bookDefault.png"/>
						</c:if>
					</td>
				</tr>
				<tr>
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
				</tr>
				<tr>
					<td class="b_label">카테고리</td>
					<td class="b_label_c">${bookInfo.category}</td>
				</tr>
				<tr>
					<td class="b_label">설명</td>
					<td class="b_label_c">${bookInfo.description}</td>
				</tr>
			</tbody>
		</table>
		
		
		<form name="insertBookMyLibraryform" method="post" action="/library/insertLibraryBook.do">
		
			<input type="hidden" name="book.idNum" value="${bookInfo.idNum}"/>
			<input type="hidden" name="library.idNum" value="${library.idNum}"/>
			
			<table id="insertlibrarybook">
				<thead>
					<tr>
						<td>서재에 등록</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>어느쪽으로 책 분류 하시겠습니까?</td>
					</tr>
					<tr>
						<td> 
							<input type="radio" name="state" value="0"/>읽고 싶은책 
							<input type="radio" name="state" value="1"/>읽고 있는책 
							<input type="radio" name="state" value="2"/>읽은 책 
						</td>
					<tr>
						<td>
							읽은 날짜 : 
							<input type="text" name="readDateYear" size="4" readonly="readonly"/>
							<input type="text" name="readDateMonth" size="2" readonly="readonly"/>
							<input type="text" name="readDateDate" size="2" readonly="readonly"/>
							(읽은책일경우)
						</td>
					</tr>
					<tr>
						<td>
							<table id="insertpossessbook">
								<tr>
									<td colspan="2">
										<input type="checkbox" name="isPossess" value="1"/>소유여부
									</td>
								</tr>
								<tr>
									<td class="b_label">구입날짜</td>
									<td class="b_label_c">
										<input type="hidden" id="datepicker_purchaseDate"/>
										<input type="text" name="purchaseDateYear" size="4" class="purchaseDateYear" readonly="readonly"/>년 
										<input type="text" name="purchaseDateMonth" size="2" class="purchaseDateMonth" readonly="readonly"/>월 
										<input type="text" name="purchaseDateDate" size="2" class="purchaseDateDate" readonly="readonly"/>일
									</td>
								</tr>
								<tr>
									<td class="b_label">구입가격</td>
									<td class="b_label_c">
										<input type="text" name="purchasePrice" size="10"/>원
									</td>
								</tr>
								<tr>
									<td class="b_label">독서시작일</td>
									<td class="b_label_c">
										<input type="hidden" id="datepicker_beginRead"/>
										<input type="text" name="beginReadYear" size="4" class="beginReadYear" readonly="readonly"/>년  
										<input type="text" name="beginReadMonth" size="2" class="beginReadMonth" readonly="readonly"/>월 
										<input type="text" name="beginReadDate" size="2" class="beginReadDate" readonly="readonly"/>일
									</td>
								</tr>
								<tr>
									<td class="b_label">독서종료일</td>
									<td class="b_label_c">
										<input type="hidden" id="datepicker_endRead"/>
										<input type="text" name="endReadYear" size="4" class="endReadYear" readonly="readonly"/>년 
										<input type="text" name="endReadMonth" size="2" class="endReadMonth" readonly="readonly"/>월 
										<input type="text" name="endReadDate" size="2" class="endReadDate" readonly="readonly"/>일
									</td>
								</tr>
								<tr>
									<td class="b_label">책품질</td>
									<td class="b_label_c">
										<select name="quality">
											<option value="0"> 상 </option>
											<option value="1"> 중 </option>
											<option value="2"> 하 </option>
										</select>
									</td>
								</tr>
								<tr>
									<td class="b_label">책상태</td>
									<td class="b_label_c">
										<select name="bookstate">
											<option value="0">소유</option>
											<option value="1">대여중</option>
											<option value="2">교환중</option>
										</select>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td>
							<input type="button" value="서재에 등록" onclick="insertLibraryBook()"/>
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
		
		<div id='navigator'>
			<a href="javascript:history.go(-1)">뒤로</a>
		</div>
		
	</body>
</html>
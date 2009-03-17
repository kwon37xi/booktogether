<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/common/jquery-ui-1.7.custom.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/common/jquery-ui-1.7.min.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/common/datepicker.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/book/book.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/librarybook/modifylibrarybook.js"></script>
		<title>서재 책 수정하기</title>
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
					<td>
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
					<td>${bookInfo.name}</td>
				</tr>
				<tr>
					<td class="b_label">지은이</td>
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
					<td class="b_label">출판사/출판일</td>
					<td>${bookInfo.publishComp}/
						${fn:substring(bookInfo.publishDate,0,4)}년 
						${fn:substring(bookInfo.publishDate,4,6)}월 
						${fn:substring(bookInfo.publishDate,6,8)}일
					</td>
				</tr>
			</tbody>
			<tfoot></tfoot>
		</table>
		
		
		<form name="modifyBookMyLibraryform" method="post" action="/library/modifyLibraryBook.do">
		
			<input type="hidden" name="book.idNum" value="${bookInfo.idNum}"/>
			<input type="hidden" name="idNum" value="${libraryBook.idNum}"/>
			<input type="hidden" name="library.idNum" value="${libraryBook.library.idNum}"/>
			<input type="hidden" name="possessIdNum" value="${possessBook.idNum}"/>
			<input type="hidden" name="beforeIsPossess" value="${libraryBook.isPossess}" />
			<input type="hidden" name="purchasePrice" value="${bookInfo.price}"/>
			
			<table id="modifypossessbook">
				<thead>
					<tr>
						<td>수정하기</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>책의 리스트를 선택해주세요.</td>
					</tr>
					<tr>
						<td>
							<input type="radio" name="state" value="0" ${libraryBook.state==0 ? 'checked' : ''}/>읽고 싶은책 
							<input type="radio" name="state" value="1" ${libraryBook.state==1 ? 'checked' : ''}/>읽고 있는책 
							<input type="radio" name="state" value="2" ${libraryBook.state==2 ? 'checked' : ''}/>읽은 책 
							
							<fmt:formatDate value="${libraryBook.readDate}" pattern="yyyy" var="readDateYear"/>
							<fmt:formatDate value="${libraryBook.readDate}" pattern="MM" var="readDateMonth"/>
							<fmt:formatDate value="${libraryBook.readDate}" pattern="dd" var="readDateDate"/>
							<input type="text" name="readDateYear" size="4" value="${readDateYear}"/>
							<input type="text" name="readDateMonth" size="2" value="${readDateMonth}"/>
							<input type="text" name="readDateDate" size="2" value="${readDateDate}"/>
						</td>
					</tr>
					<c:if test="${libraryBook.isPossess!=null}">
						<tr>
							<td>
								<table>
									<tr>
										<td class="p_label">소유여부</td>
										<td><input type="checkbox" name="isPossess" ${libraryBook.isPossess==1 ? 'checked' : ''} value="1" /></td>
									</tr>
									<tr>
										<td class="p_label">구입날짜</td>
										<td>
											<input type="hidden" id="datepicker_purchaseDate"/>
											<fmt:formatDate value="${possessBook.purchaseDate}" pattern="yyyy" var="purchaseDateYear"/>
											<fmt:formatDate value="${possessBook.purchaseDate}" pattern="MM" var="purchaseDateMonth"/>
											<fmt:formatDate value="${possessBook.purchaseDate}" pattern="dd" var="purchaseDateDate"/>
											<input type="text" name="purchaseDateYear" class="purchaseDateYear" size="4" value="${purchaseDateYear}"/>
											<input type="text" name="purchaseDateMonth" class="purchaseDateMonth" size="2" value="${purchaseDateMonth}"/>
											<input type="text" name="purchaseDateDate" class="purchaseDateDate" size="2" value="${purchaseDateDate}"/>
										</td>
									</tr>
									<tr>
										<td class="p_label">독서시작일</td>
										<td>
											<input type="hidden" id="datepicker_beginRead"/>
											<fmt:formatDate value="${possessBook.beginRead}" pattern="yyyy" var="beginReadYear"/>
											<fmt:formatDate value="${possessBook.beginRead}" pattern="MM" var="beginReadMonth"/>
											<fmt:formatDate value="${possessBook.beginRead}" pattern="dd" var="beginReadDate"/>
											<input type="text" name="beginReadYear" class="beginReadYear" size="4" value="${beginReadYear}"/>
											<input type="text" name="beginReadMonth" class="beginReadMonth" size="2" value="${beginReadMonth}"/>
											<input type="text" name="beginReadDate" class="beginReadDate" size="2" value="${beginReadDate}"/>
										</td>
									</tr>
									<tr>
										<td class="p_label">독서종료일</td>
										<td>
											<input type="hidden" id="datepicker_endRead"/>
											<fmt:formatDate value="${possessBook.endRead}" pattern="yyyy" var="endReadYear"/>
											<fmt:formatDate value="${possessBook.endRead}" pattern="MM" var="endReadMonth"/>
											<fmt:formatDate value="${possessBook.endRead}" pattern="dd" var="endReadDate"/>
											<input type="text" name="endReadYear" class="endReadYear" size="4" value="${endReadYear}"/>
											<input type="text" name="endReadMonth" class="endReadMonth" size="2" value="${endReadMonth}"/>
											<input type="text" name="endReadDate" class="endReadDate" size="2" value="${endReadDate}"/>
										</td>
									</tr>
									<tr>
										<td class="p_label">책품질</td>
										<td>
											<select name="quality">
												<option value="0" ${possessBook.quality==0 ? 'selected' : ''}>상</option>
												<option value="1" ${possessBook.quality==1 ? 'selected' : ''}>중</option>
												<option value="2" ${possessBook.quality==2 ? 'selected' : ''}>하</option>
											</select>
										</td>
									</tr>
									<tr>
										<td class="p_label">책상태</td>
										<td>
											<select name="bookstate">
												<option value="0" ${possessBook.state==0 ? 'selected' : ''}>소유</option>
												<option value="1" ${possessBook.state==1 ? 'selected' : ''}>대여중</option>
												<option value="2" ${possessBook.state==2 ? 'selected' : ''}>교환중</option>
											</select>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</c:if>
				</tbody>
				<tfoot>
					<tr>
						<td>
							<input type="button" value="수정" onclick="modifyLibraryBook()"/>
							<input type="button" value="뒤로" onclick="javascript:history.go(-1)"/>
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link rel="stylesheet" type="text/css" href="/styles/possess/possessbook.css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/library/library.js"></script>
		<title>소유책 목록</title>
	</head>
	<body>
	
		<table class="possessbook_list">
			<thead>
				<tr>
					<td>보유책</td>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(possessBookList)!=0}">
						<c:forEach begin="0" items="${possessBookList}" var="possessBook" varStatus="status">
							<tr>
								<td>
									<table id="possessbook_listresult">
										<tbody>
											<tr>
												<td rowspan="4" class="possessBookImg">
													<c:if test="${possessBook.book.bookCover!=null && possessBook.book.bookCover!=''}">
														<img src="${possessBook.book.bookCover}"/>
													</c:if>
													<c:if test="${possessBook.book.bookCover==null || possessBook.book.bookCover==''}">
														<img src="/images/book/bookDefault.png"/>
													</c:if>					
												</td>
												<td class="possessBookTitle">제목 : <a href="/library/getPossessBook.do?userIdNum=${library.user.idNum}&possessBookIdNum=${possessBook.idNum}&libraryIdNum=${library.idNum}">${possessBook.book.name}</a></td>
												<td class="possessBookAuthor">지은이 : ${possessBook.book.authors[0].name} 지음	</td>
											</tr>
											<tr>
												<td>구입날짜 : <fmt:formatDate value="${possessBook.purchaseDate}" pattern="yyyy.MM.dd"/></td>
												<td>구입가격 : <fmt:formatNumber value="${possessBook.purchasePrice}" pattern=",###"/>원</td>
											</tr>
											<tr>
												<td>독서시작 : <fmt:formatDate value="${possessBook.beginRead}" pattern="yyyy.MM.dd"/></td>
												<td>독서종료 : <fmt:formatDate value="${possessBook.endRead}" pattern="yyyy.MM.dd"/></td>
											</tr>
											<tr>
												<td>책품질 :
													<c:choose>
														<c:when test="${possessBook.quality==0}">상</c:when>
														<c:when test="${possessBook.quality==1}">중</c:when>
														<c:when test="${possessBook.quality==2}">하</c:when>
													</c:choose>
												</td>
												<td>책상태 :
													<c:choose>
														<c:when test="${possessBook.state==0}">소유</c:when>
														<c:when test="${possessBook.state==1}">대여중</c:when>
														<c:when test="${possessBook.state==2}">교환중</c:when>
													</c:choose>
												 </td>
											</tr>
										</tbody>
										<tfoot>
											<tr>
												<td colspan="3">
													<a href="/library/modifyPossessBookView.do?libraryIdNum=${library.idNum}&possessBookIdNum=${possessBook.idNum}">수정</a> /
													<a href="/library/deletePossessBook.do?libraryIdNum=${library.idNum}&possessBookIdNum=${possessBook.idNum}">삭제</a> 
												  
												</td>
											</tr>
										</tfoot>
									</table>
								</td>
							</tr>
						</c:forEach>
						
					</c:when>
					<c:otherwise>
						<tr>
							<td class="nocontent">검색 결과가 없습니다.</td>
						</tr>
					</c:otherwise>				
				</c:choose>
			</tbody>
			<tfoot></tfoot>
		</table>	
		
		<div id='navigator'>
			<c:if test="${pageBean.prePage}">
				<a href="/library/getListPossessBook.do?pageNo=${pageBean.startPage-pageBean.limit}&userId=${param.userId}">이전</a>
			</c:if>
			
			<c:forEach begin="${pageBean.startPage}" end="${pageBean.endPage}" var='i'>
				<a href="/library/getListPossessBook.do?pageNo=${i}&userId='${param.userId}">[ ${i} ]</a>
			</c:forEach>
				
			<c:if test="${pageBean.nextPage}">
				<a href="/library/getListPossessBook.do?pageNo=${pageBean.startPage+pageBean.limit}&userId=${param.userId}">다음</a>
			</c:if>
		</div>
		
	</body>
</html>
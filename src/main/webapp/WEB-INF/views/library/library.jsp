<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/book/book.js"></script>
		<title>${library.user.name}님 서재</title>
	</head>
	<body>
		<table id="library_main_intro">
			<thead>
				<tr>
					<td>인사말</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${library.notice}</td>
				</tr>
			</tbody>							
		</table>
		
		<table id="library_main">
			<tbody>
				<tr>
					<td class="lastestboard_title">
						<a href="/library/getListLibraryBook.do?state=0&libraryIdNum=${library.idNum}">읽고 싶은책</a>
					</td>
					<td class="lastestboard_title">
						<a href="/library/getListLibraryBook.do?state=1&libraryIdNum=${library.idNum}">읽고 있는 책</a>
					</td>
				</tr>
				<tr>
					<td class="lastestboard_content">
						<c:choose>
							<c:when test="${fn:length(libraryBookList0)!=0}">
								<table>
									<tbody>
										<tr>
											<c:forEach begin="0" end="1" var="i">
												<td class="bookcover">
													<c:if test="${libraryBookList0[i]!=null}">
														<a href="/book/getBook.do?bookIdNum=${libraryBookList0[i].book.idNum}">
															<img src="${libraryBookList0[i].book.bookCover}" width="70px" height="100px"/><br/>
															
															<c:choose>
																<c:when test="${fn:length(libraryBookList0[i].book.name)>8}">
																	${fn:substring(libraryBookList0[i].book.name,0,8)}...
																</c:when>
																<c:otherwise>
																	${libraryBookList0[i].book.name}
																</c:otherwise>
															</c:choose>
														</a>
													</c:if>
													<c:if test="${libraryBookList0[i]==null}">&nbsp;</c:if>
												</td>
											</c:forEach>
										</tr>
									</tbody>
								</table>
							</c:when>
							<c:otherwise>
								<b>등록된 결과물이 없습니다</b>
							</c:otherwise>
						</c:choose>
					</td>
					<td class="lastestboard_content">
						<c:choose>
							<c:when test="${fn:length(libraryBookList1)!=0}">
								<table>
									<tbody>
										<tr>
											<c:forEach begin="0" end="1" var="i">
												<td class="bookcover">
													<c:if test="${libraryBookList1[i]!=null}">
														<a href="/book/getBook.do?bookIdNum=${libraryBookList1[i].book.idNum}">
															<img src="${libraryBookList1[i].book.bookCover}" width="70px" height="100px"/><br/>
															<c:choose>
																<c:when test="${fn:length(libraryBookList1[i].book.name)>8}">
																	${fn:substring(libraryBookList1[i].book.name,0,8)}...
																</c:when>
																<c:otherwise>
																	${libraryBookList1[i].book.name}
																</c:otherwise>
															</c:choose>
														</a>
													</c:if>
													<c:if test="${libraryBookList1[i]==null}">&nbsp;</c:if>
												</td>
											</c:forEach>
										</tr>
									</tbody>
								</table>
							</c:when>
							<c:otherwise>
								<b>등록된 결과물이 없습니다</b>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="lastestboard_title">
						<a href="/library/getListLibraryBook.do?state=2&libraryIdNum=${library.idNum}">읽은 책</a>
					</td>
					<td class="lastestboard_title">
						<a href="/library/getListPossessBook.do?libraryIdNum=${library.idNum}&userId=${library.user.userId}">소유책</a>
					</td>
				</tr>
				<tr>
					<td class="lastestboard_content">
						<c:choose>
							<c:when test="${fn:length(libraryBookList2)!=0}">
								<table>
									<tbody>
										<tr>
											<c:forEach begin="0" end="1" var="i">
												<td class="bookcover">
													<c:if test="${libraryBookList2[i]!=null}">
														<a href="/book/getBook.do?bookIdNum=${libraryBookList2[i].book.idNum}">
														
															<img src="${libraryBookList2[i].book.bookCover}" width="70px" height="100px"/><br/>
															
															<c:choose>
																<c:when test="${fn:length(libraryBookList2[i].book.name)>8}">
																	${fn:substring(libraryBookList2[i].book.name,0,8)}...
																</c:when>
																<c:otherwise>
																	${libraryBookList2[i].book.name}
																</c:otherwise>
															</c:choose>
														</a>
													</c:if>
													<c:if test="${libraryBookList2[i]==null}">&nbsp;</c:if>
												</td>
											</c:forEach>
										</tr>
									</tbody>
								</table>
							</c:when>
							<c:otherwise>
								<b>등록된 결과물이 없습니다</b>
							</c:otherwise>
						</c:choose>
					</td>
					<td class="lastestboard_content">
						<c:choose>
							<c:when test="${fn:length(possessBookList)!=0}">
								<table>
									<tbody>
										<tr>
											<c:forEach begin="0" end="1" var="i">
												<td class="bookcover">
													<c:if test="${possessBookList[i]!=null}">
														<a href="/library/getPossessBook.do?userIdNum=${library.user.idNum}&libraryIdNum=${library.idNum}&possessBookIdNum=${possessBookList[i].idNum}">
															<img src="${possessBookList[i].book.bookCover}" width="70px" height="100px"/><br/>
															<c:choose>
																<c:when test="${fn:length(possessBookList[i].book.name)>8}">
																	${fn:substring(possessBookList[i].book.name,0,8)}...
																</c:when>
																<c:otherwise>
																	${possessBookList[i].book.name}
																</c:otherwise>
															</c:choose>
														</a>
													</c:if>
													<c:if test="${possessBookList[i]==null}">&nbsp;</c:if>
												</td>
											</c:forEach>
										</tr>
									</tbody>
								</table>
							</c:when>
							<c:otherwise>
								<b>등록된 결과물이 없습니다</b>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="lastestboard_title">
						<a href="/library/getListMyReview.do?libraryIdNum=${library.idNum}&userIdNum=${library.user.idNum}">리뷰목록</a>
					</td>
					<td class="lastestboard_title">
						<a href="/library/getListMyBookGrade.do?libraryIdNum=${library.idNum}&userIdNum=${library.user.idNum}">별점</a>
					</td>
				</tr>
				<tr>
					<td class="lastestboard_content_list">
						<c:choose>
							<c:when test="${fn:length(bookReviewList)!=0}">
								<table>
									<tbody>
										<c:forEach begin="0" end="4" var="i">
											<tr>
												<td>
													<c:if test="${bookReviewList[i]!=null}">
														<a href="/book/getReview.do?bookReviewIdNum=${bookReviewList[i].idNum}">
															<img src="/images/library/bullet2.gif" width="16px"/>
															<c:choose>
																<c:when test="${fn:length(bookReviewList[i].title)>15}">
																	${fn:substring(bookReviewList[i].title,0,15)}...
																</c:when>
																<c:otherwise>
																	${bookReviewList[i].title}
																</c:otherwise>
															</c:choose>
														</a>
													</c:if>
													<c:if test="${possessBookList[i]==null}">&nbsp;</c:if>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:when>
							<c:otherwise>
								<b>등록된 결과물이 없습니다</b>
							</c:otherwise>
						</c:choose>
					</td>
					<td class="lastestboard_content_list">
						<c:choose>
							<c:when test="${fn:length(bookGradeList)!=0}">
								<table>
									<tbody>
										<c:forEach begin="0" end="4" var="i">
											<tr>
												<td>
													<c:if test="${bookGradeList[i]!=null}">
														<img src="/images/library/bullet2.gif" width="16px"/>
														<c:choose>
															<c:when test="${fn:length(bookGradeList[i].book.name)>15}">
																${fn:substring(bookGradeList[i].book.name,0,15)}...
															</c:when>
															<c:otherwise>
																${bookGradeList[i].book.name}
															</c:otherwise>
														</c:choose>
													</c:if>
													<c:if test="${bookGradeList[i]==null}">&nbsp;</c:if>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:when>
							<c:otherwise>
								<b>등록된 결과물이 없습니다</b>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="lastestboard_title">
						<a href="/library/getListMyBookMark.do?libraryIdNum=${library.idNum}&userIdNum=${library.user.idNum}">인용구</a>
					</td>
					<td class="lastestboard_title">
						<a href="/library/getListLibraryBoard.do?libraryIdNum=${library.idNum}">방명록</a>
					</td>
				</tr>
				<tr>
					<td class="lastestboard_content_list">
						<c:choose>
							<c:when test="${fn:length(bookMarkList)!=0}">
								<table>
									<tbody>
										<c:forEach begin="0" end="4" var="i">
											<tr>
												<td>
													<c:if test="${bookMarkList[i]!=null}">
														<img src="/images/library/bullet2.gif" width="16px"/>
														<c:choose>
															<c:when test="${fn:length(bookMarkList[i].content)>15}">
																${fn:substring(bookMarkList[i].content,0,15)}...
															</c:when>
															<c:otherwise>
																${bookMarkList[i].content}
															</c:otherwise>
														</c:choose>
													</c:if>
													<c:if test="${bookMarkList[i]==null}">&nbsp;</c:if>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:when>
							<c:otherwise>
								<b>등록된 결과물이 없습니다</b>
							</c:otherwise>
						</c:choose>
					</td>
					<td class="lastestboard_content_list">
						<c:choose>
							<c:when test="${fn:length(libraryBoardList)!=0}">
								<table>
									<tbody>
										<c:forEach begin="0" end="4" var="i">
											<tr>
												<td>
													<c:if test="${libraryBoardList[i]!=null}">
														<img src="/images/library/bullet2.gif" width="16px"/>
														<c:choose>
															<c:when test="${fn:length(libraryBoardList[i].content)>15}">
																${fn:substring(libraryBoardList[i].content,0,15)}...
															</c:when>
															<c:otherwise>
																${libraryBoardList[i].content}
															</c:otherwise>
														</c:choose>
													</c:if>
													<c:if test="${libraryBoardList[i]==null}">&nbsp;</c:if>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:when>
							<c:otherwise>
								<b>등록된 결과물이 없습니다</b>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>
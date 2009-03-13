<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		
		<link rel="stylesheet" type="text/css" href="/styles/main/main.css" />
		<link rel="stylesheet" type="text/css" href="/styles/main/leftmain.css" />
		<link rel="stylesheet" type="text/css" href="/styles/main/rightmain.css" />
		
		<script type="text/javascript" charset="utf-8" src="/scripts/book/book.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/user/user.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/main/main.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/user/login.js"></script>
		
		<title>Java Spring 2.5 기반 책 함께보기</title>
	</head>
	<body>
		<table id="main_layout">
			<tr>	
				<td valign="top" id='main_left_td'>
					<!-- 로그인 -->
					<div id="login">
						<c:choose>
							<c:when test="${sessionScope.idNum!=null}">
								<table id='user'>
									<tr>
										<td colspan="2">${sessionScope.nickname}님 환영합니다.</td>
									</tr>
									<tr>
										<td class='user_thumnail'>
											<c:if test="${fn:length(sessionScope.thumnail) > 13}">
												<img src="/images/user/thumnail/${sessionScope.thumnail}"/>
											</c:if>
											<c:if test="${fn:length(sessionScope.thumnail) == 13}">
												<img src="/images/user/userDefault.png"/>
											</c:if>	
										</td>
										<td class='user_info'>
											<p>
												<a href="/user/getUser.do">회원조회</a>
											</p>
											<p>
												<a href="/library/getLibrary.do?userId=${sessionScope.userId}">내서재가기</a>
											</p>
											<p>
												<a href="/user/logout.do">로그아웃</a>
											</p>
										</td>
									</tr>
								</table>					
							</c:when>
							<c:otherwise>
								<form name="loginform" method="post" action="/user/valiadIdPwUser.do">
									<b>로그인</b>
									<p>
										<label for="userId">ID</label><input type="text" name="userId" size="15"/>
									</p>
									<p>
										<label for="pw">PW</label><input type="password" name="pw" size="15"/>
									</p>
									<p class="but_g1">
										<img src="/images/main/login_b.png" onclick="login()" class="img_button"/>
										<img src="/images/main/join_b.png" onclick="joinform()" class="img_button"/>
									</p>
									<p>
										<a href="/user/findIdView.do">아이디 찾기</a> 
										/
										<a href="/user/findPwView.do">비밀번호 찾기</a>
									</p>
								</form>
							</c:otherwise>
						</c:choose>
					</div>
					
					<hr class="horizen"/>
					
					<!-- 인기검색순위 -->
					<div id="searchRank">
						인기 검색 순위
						<ol>
							<c:forEach begin="0" items="${searchRankQuerys}" var="queryInfo" varStatus="status">
								<li>
									<c:choose>
										<c:when test="${fn:length(queryInfo)>10}">
											<a href="javascript:go_searchBook('1','${queryInfo}','book')">${fn:substring(fn:escapeXml(queryInfo),0,10)}...</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:go_searchBook('1','${queryInfo}','book')">${fn:escapeXml(queryInfo)}</a>
										</c:otherwise>
									</c:choose>
								</li>
							</c:forEach>
						</ol>
					</div>
					
					<hr class="horizen"/>
					
					<!-- 활동 높은 서재 -->
					<div id="libraryRank">
						활동 높은 서재
						<ol>
							<c:forEach begin="0" items="${libraryRankList}" var="libraryRankInfo" varStatus="status">
								<li>
									<a href="/library/getLibrary.do?userId=${libraryRankInfo.userId}">${libraryRankInfo.name}(${libraryRankInfo.nickname})</a>
								</li>
							</c:forEach>
						</ol>
					</div>
					
					
					<hr class="horizen"/>
					
					
					<!-- 인기책 -->
					<div id="topBookHits">
						인기책
						<ol>
							<c:forEach begin="0" items="${topBookHitsList}" var="bookInfo" varStatus="status">
								<li>
									<a href="/book/getBook.do?bookIdNum=${bookInfo.idNum}">${bookInfo.name}</a>
								</li>
							</c:forEach>
						</ol>
					</div>
				</td>
				<td valign="top" id='main_right_td'>
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
										<p class="book_info">
											<c:choose>
												<c:when test="${fn:length(bestBookInfo.name)>30}">
													<a href="javascript:getBook('${bestBookInfo.idNum}')">${fn:substring(fn:escapeXml(bestBookInfo.name),0,30)}...</a>
												</c:when>
												<c:otherwise>
													<a href="javascript:getBook('${bestBookInfo.idNum}')">${fn:escapeXml(bestBookInfo.name)}</a>
												</c:otherwise>
											</c:choose>
										</p>
										<p class="book_info">
											<c:forEach begin="0" items="${bestBookInfo.authors}" var="authorInfo">
												[${authorInfo.name}/
												<c:if test="${authorInfo.authorDiv==0}">지음</c:if>
												<c:if test="${authorInfo.authorDiv==1}">옮김</c:if>
												]  
											</c:forEach>
										</p>
										<p class="book_info">ISBN : ${bestBookInfo.ISBN10}</p>
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
										<p class="book_info">
											<c:choose>
												<c:when test="${fn:length(recommendBookInfo.name)>30}">
													<a href="javascript:getBook('${recommendBookInfo.idNum}')">${fn:substring(fn:escapeXml(recommendBookInfo.name),0,30)}...</a>
												</c:when>
												<c:otherwise>
													<a href="javascript:getBook('${recommendBookInfo.idNum}')">${fn:escapeXml(recommendBookInfo.name)}</a>
												</c:otherwise>
											</c:choose>
										</p>
										<p class="book_info"><c:forEach begin="0" items="${recommendBookInfo.authors}" var="authorInfo">
												[${authorInfo.name}/
												<c:if test="${authorInfo.authorDiv==0}">지음</c:if>
												<c:if test="${authorInfo.authorDiv==1}">옮김</c:if>
												]  
											</c:forEach></p>
										<p class="book_info">ISBN : ${recommendBookInfo.ISBN10}</p>
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
				</td>
			</tr>
		</table>
	</body>
</html>
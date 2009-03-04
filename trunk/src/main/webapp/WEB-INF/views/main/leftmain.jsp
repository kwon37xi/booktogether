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
		
		<!-- 로그인 -->
		<div id="login">
			<c:choose>
				<c:when test="${sessionScope.idNum!=null}">
					<table id='user'>
						<tr>
							<td class='user_thumnail'>
								<img src="/images/user/thumnail/${sessionScope.thumnail}"/>
							</td>
							<td class='user_info'>
								<p>
									&lt;${sessionScope.nickname}&gt;
								</p>
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
						<p>
							<input type="submit" value="로그인"/>
							<input type="button" value="회원가입" onclick="join()"/>
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
					<li>${queryInfo}</li>
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
		
	</body>
</html>
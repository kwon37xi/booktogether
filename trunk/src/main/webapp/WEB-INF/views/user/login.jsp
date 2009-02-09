<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="../../styles/common/default.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="../../scripts/user/user.js"></script>
		<title>로그인</title>
	</head>
	<body>
	
		<c:if test="${message!=null}">
			<script>
				alert('${message}');
			</script>
		</c:if>
	
		<c:choose>
			<c:when test="${sessionScope.id!=null}">
				<table border="1">
					<thead>
						<tr>
							<td>${sessionScope.nickname}님 환영합니다.</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<a href="/user/getUser.do">회원조회</a>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td>
								<a href="/user/logout.do">로그아웃</a>
							</td>
						</tr>
					</tfoot>
				</table>
			</c:when>
			<c:otherwise>
				<form name="loginform" method="post" action="/user/valiadIdPwUser.do">
					<table border"1">
						<thead>
							<tr>
								<td colspan="2" align="center">로그인</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><label for="user_id">아이디</label></td>
								<td><input type="text" name="user_id" size="20"/></td>
							</tr>
							<tr>
								<td><label for="pw">비밀번호</label></td>
								<td><input type="password" name="pw" size="20"/></td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2" align="center">
									<input type="submit" value="로그인"/>
									<input type="button" value="회원가입" onclick="join()"/>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center"><a href="/user/findIDView.do">아이디 찾기</a> / <a href="/user/findPWView.do">비밀번호 찾기</a></td>
							</tr>
						</tfoot>
					</table>
				</form>
			</c:otherwise>
		</c:choose>
	</body>
</html>
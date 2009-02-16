<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/common/default.css" rel="stylesheet" type="text/css"/>
		<title>사용자 조회</title>
	</head>
	<body>
	
		<c:if test="${sessionScope.message!=null}">
			<script>
				alert('${sessionScope.message}');
			</script>
			<c:remove scope="session" var="message"/>
		</c:if>
		
		기본정보
		<table border='1'>
			<thead></thead>
			<tbody>
				<tr>
					<td>아이디</td>
					<td>${userInfo.userId}</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>${userInfo.name}</td>
				</tr>
				<tr>
					<td>닉네임</td>
					<td>${userInfo.nickname}</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>${userInfo.email}</td>
				</tr>
				<tr>
					<td>가입일</td>
					<td>${userInfo.inputDate}</td>
				</tr>
			</tbody>
			<tfoot></tfoot>
		</table>
		
		
		추가정보
		<table border='1'>
			<thead></thead>
			<tbody>
				<tr>
					<td>블로그</td>
					<td>${userInfo.userAddInfo.blog}</td>
				</tr>
				<tr>
					<td>썸네일</td>
					<td><img src="/images/user/thumnail/${userInfo.userAddInfo.thumnail}"/></td>
				</tr>
				
				<c:forEach begin="0" items="${userInfo.zones}" var="zoneInfo" varStatus="status">
					<tr>
						<td>생활 반경</td>
						<td>${zoneInfo.zoneName}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot></tfoot>
		</table>
		
		
		<div id=''>
			<a href="javascript:history.go(-1)">뒤로</a>
			<a href="/user/modifyUserPWView.do">비밀번호수정</a>
			<a href="/user/modifyUserView.do">수정</a>
			<a href="/user/deleteUser.do">탈퇴</a>
		</div>
		
	</body>
</html>
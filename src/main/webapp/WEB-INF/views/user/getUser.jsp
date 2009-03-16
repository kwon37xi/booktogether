<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link rel="stylesheet" type="text/css" href="/styles/user/getuser.css" />
		<script type="text/javascript" charset="utf-8" src="/scripts/user/getuser.js"></script>
		<title>사용자 조회</title>
	</head>
	<body>
	
		<table id="user_info">
			<thead>
				<tr>
					<td colspan="2">기본정보</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="u_label">아이디</td>
					<td class="u_label_c">${userInfo.userId}</td>
				</tr>
				<tr>
					<td class="u_label">이름</td>
					<td class="u_label_c">${userInfo.name}</td>
				</tr>
				<tr>
					<td class="u_label">닉네임</td>
					<td class="u_label_c">${userInfo.nickname}</td>
				</tr>
				<tr>
					<td class="u_label">이메일</td>
					<td class="u_label_c">${userInfo.email}</td>
				</tr>
				<tr>
					<td class="u_label">가입일</td>
					<td class="u_label_c">${userInfo.inputDate}</td>
				</tr>
			</tbody>
		</table>
		
		<hr/>
		
		<table id="user_add_info">
			<thead>
				<tr>
					<td colspan="2">추가정보</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="u_label">블로그</td>
					<td class="u_label_c">${userInfo.userAddInfo.blog}</td>
				</tr>
				<tr>
					<td class="u_label">썸네일</td>
					<td class="u_label_c">
						<c:if test="${fn:length(userInfo.userAddInfo.thumnail) > 13}">
							<img src="/images/user/thumnail/${userInfo.userAddInfo.thumnail}"/>
						</c:if>
						<c:if test="${fn:length(userInfo.userAddInfo.thumnail) == 13}">
							<img src="/images/user/userDefault.png"/>
						</c:if>	
					</td>
				</tr>
				
				<c:forEach begin="0" items="${userInfo.zones}" var="zoneInfo" varStatus="status">
					<tr>
						<td class="u_label">생활 반경</td>
						<td class="u_label_c">${zoneInfo.zoneName}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<div id='navigator'>
			<a href="javascript:history.go(-1)">뒤로</a>
			<a href="/user/modifyUserPWView.do">비밀번호수정</a>
			<a href="/user/modifyUserView.do">수정</a>
			<a href="/user/deleteUser.do">탈퇴</a>
		</div>
		
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>사용자 조회</title>
	</head>
	<body>
		<table border='1'>
			<thead>
				<tr>
					<td>분류</td>
					<td>내용</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>일련번호</td>
					<td>${user_info.id}</td>
				</tr>
				<tr>
					<td>아이디</td>
					<td>${user_info.user_id}</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>${user_info.name}</td>
				</tr>
				<tr>
					<td>닉네임</td>
					<td>${user_info.nickname}</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>${user_info.email}</td>
				</tr>
				<tr>
					<td>등록날짜</td>
					<td>${user_info.input_date}</td>
				</tr>
				<tr>
					<td>탈퇴유무</td>
					<td>${user_info.delete_y_n}</td>
				</tr>
			</tbody>
			<tfoot></tfoot>
		</table>
		
		
		<div id=''>
			<a href="javascript:history.go(-1)">뒤로</a>
			<a href="/user/modifyUserPWView.do">비밀번호수정</a>
			<a href="/user/modifyUserView.do">수정</a>
			<a href="/user/deleteUser.do">탈퇴</a>
			<a href="/user/listUser.do">목록</a>
		</div>
		
	</body>
</html>
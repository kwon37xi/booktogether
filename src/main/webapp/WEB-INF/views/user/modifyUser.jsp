<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>회원수정</title>
	</head>
	<body>
		<div>
			<form method="post" name="modifyuser_form" action="/user/modifyUser.do">
				<p>			
					${user_info.user_id}
				</p>
				<p>
					<label for="pw">PW</label>
					<input type="text" name="pw" size="20" value="${user_info.pw}"/>
				</p>
				<p>
					<label for="pw_again">PW 다시 확인</label>
					<input type="text" name="pw_again" size="20" value="${user_info.pw}"/>
				</p>
				<p>
					<label for="email">이메일</label>
					<input type="text" name="email" size="20" value="${user_info.email}"/>
				</p>
				<p>
					<label for="nickname">별명</label>
					<input type="text" name="nickname" size="20" value="${user_info.nickname}"/>
				</p>
				<p>
					<label for="name">이름</label>
					<input type="text" name="name" size="20" value="${user_info.name}"/>
				</p>
				<input type="submit" value="등록"/>
				
			</form>
		</div>
	</body>
</html>
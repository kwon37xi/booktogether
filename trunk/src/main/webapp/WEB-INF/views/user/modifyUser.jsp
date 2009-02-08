<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>회원수정</title>
	</head>
	<body>
		<div>
			<form method="post" name="modifyuser_form" action="/user/modifyUser.do">
			
				<p>			
					${user_info.user_id}
				</p>
				<p>
					<input type="checkbox" name="pw"/>비밀번호 수정함 
				</p>
				<p>
					<label for="pw">비밀번호 수정시 입력 :</label> <input type="text" name="pw_c" size="20" disabled="disabled"/>
				</p>
				<p>
					<label for="pw_again">비밀번호 수정시 다시입력 :</label> <input type="text" name="pw_again" size="20" disabled="disabled"/>
				</p>
				<p>
					<label for="nickname">별명</label>
					<input type="text" name="nickname" size="20" value="${user_info.nickname}"/>
				</p>
				<p>
					<label for="name">이름</label>
					<input type="text" name="name" size="20" value="${user_info.name}"/>
				</p>
				<p>
					<label for="name">이메일</label>
					<input type="text" name="email" size="20" value="${user_info.email}"/>
				</p>
				<input type="submit" value="수정"/>
			</form>
		</div>
	</body>
</html>
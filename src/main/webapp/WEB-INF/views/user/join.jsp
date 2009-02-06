<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>회원가입</title>
	</head>
	<body>
		<div>
			<form method="post" name="insertuser_form" action="/user/insertUser.do">
				<p>			
					<label for="user_id">ID</label>
					<input type="text" name="user_id" size="20"/>
				</p>
				<p>
					<label for="pw">PW</label>
					<input type="text" name="pw" size="20"/>
				</p>
				<p>
					<label for="pw_again">PW 다시 확인</label>
					<input type="text" name="pw_again" size="20"/>
				</p>
				<p>
					<label for="email">이메일</label>
					<input type="text" name="email" size="20"/>
				</p>
				<p>
					<label for="nickname">별명</label>
					<input type="text" name="nickname" size="20"/>
				</p>
				<p>
					<label for="name">이름</label>
					<input type="text" name="name" size="20"/>
				</p>
				<input type="submit" value="등록"/>
				
			</form>
		</div>
	</body>
</html>
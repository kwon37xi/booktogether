<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="../../styles/common/default.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="../../scripts/common/prototype.js"></script>
		<script type="text/javascript" charset="utf-8" src="../../scripts/common/common.js"></script>
		<script type="text/javascript" charset="utf-8" src="../../scripts/user/user.js"></script>
		<title>회원가입</title>
	</head>
	<body>
		<div>
			<form enctype="multipart/form-data" method="post" name="insertuser_form" action="/user/insertUser.do">
				<input type="hidden" name="dupli_id" value="0"/>
				<p>			
					<label for="user_id">ID</label>
					<input type="text" name="user_id" size="20" readonly="readonly"/>
					<a href="/user/duplicateUserIdView.do" target="_blank">중복 확인</a>
				</p>
				<p>
					<label for="pw">PW</label>
					<input type="password" name="pw" size="20"/>
				</p>
				<p>
					<label for="pw_again">PW 다시 확인</label>
					<input type="password" name="pw_again" size="20"/>
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
				
				<hr/>
				추가정보
				<p>
					<label for="blog">Blog 주소</label>
					<input type="text" name="blog" size="20"/>
				</p>
				<p>
					<label for="thumnail">이미지</label>
					<input type="file" name="thumnail" size="20"/>
				</p>
				<p>
					<label for="zone">생활반경</label>
					<input type="button" value="추가" onclick="addZone()"/>
				</p>
				<div id="insertzonesdiv"></div>
				
				<input type="submit" value="등록"/>
				<input type="button" value="뒤로" onclick="go_back()"/>
				
			</form>
		</div>
	</body>
</html>
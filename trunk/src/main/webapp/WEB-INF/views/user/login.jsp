<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>로그인</title>
	</head>
	<body>
		<form name="loginform" method="post" action="/user/login.do">
			<table>
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
						<td><input type="text" name="pw" size="20"/></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2" align="center"><input type="submit" value="로그인"/></td>
					</tr>
				</tfoot>
			</table>
		</form>
	</body>
</html>
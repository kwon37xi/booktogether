<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/common/default.css" rel="stylesheet" type="text/css"/>
		<title>비밀번호 찾기</title>
	</head>
	<body>
		<form name="findPwform" method="post" action="/user/modifyPw.do">
			<table>
				<thead>
					<tr>
						<td colspan="2">비밀번호 변경</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><label for="pw">기존 비밀번호</label></td>
						<td><input type="password" name="pw" size="20"/></td>
					</tr>
					<tr>
						<td><label for="pw_c">비밀번호 변경</label></td>
						<td><input type="password" name="newPw" size="20"/></td>
					</tr>
					<tr>
						<td><label for="pw_c_again">비밀번호 변경확인</label></td>
						<td><input type="password" name="newPw_again" size="20"/></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<input type="submit" value="변경"/>
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/user/pwmodify.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/user/modifypw.js"></script>
		<title>비밀번호 변경</title>
	</head>
	<body>
		<form name="modifyPwform" method="post" action="/user/modifyPw.do">
			<table id="pw_modify">
				<thead>
					<tr>
						<td colspan="2">비밀번호 변경</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="u_label"><label for="pw">기존 비밀번호</label></td>
						<td class="u_label_c"><input type="password" name="pw" size="30"/></td>
					</tr>
					<tr>
						<td class="u_label"><label for="pw_c">비밀번호 변경</label></td>
						<td class="u_label_c"><input type="password" name="newPw" size="30"/></td>
					</tr>
					<tr>
						<td class="u_label"><label for="pw_c_again">비밀번호 변경확인</label></td>
						<td class="u_label_c"><input type="password" name="newPwAgain" size="30"/></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<input type="button" value="변경" onclick="modifypw()"/>
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</body>
</html>
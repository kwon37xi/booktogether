<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>아이디 찾기</title>
	</head>
	<body>
		<form name="findIDform" method="post" action="/user/findID.do">
			<table>
				<thead>
					<tr>
						<td colspan="2">아이디 찾기</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><label for="name">이름</label></td>
						<td><input type="text" name="name" size="20"/></td>
					</tr>
					<tr>
						<td><label for="email">이메일</label></td>
						<td><input type="text" name="email" size="20"/></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<input type="submit" value="찾기"/>
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</body>
</html>
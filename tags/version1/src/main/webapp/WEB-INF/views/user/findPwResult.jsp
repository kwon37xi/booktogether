<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link rel="stylesheet" type="text/css" href="/styles/user/user.css" />
		<title>비밀번호 찾기</title>
	</head>
	<body>
		
		<table class="findidresult">
			<thead>
				<tr>
					<td>비밀번호 찾기</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${userInfo.name}(${userInfo.userId})님이 요청에 대한 결과</td>
				</tr>
				<tr>
					<td><b>${message}</b></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td><a href="javascript:self.close();">닫기</a></td>
				</tr>
			</tfoot>
		</table>
		
		
			
	</body>
</html>
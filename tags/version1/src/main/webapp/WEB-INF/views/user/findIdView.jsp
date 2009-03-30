<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link rel="stylesheet" type="text/css" href="/styles/user/user.css" />
		<script type="text/javascript" charset="utf-8" src="/scripts/common/jquery.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/user/findid.js"></script>
		<title>아이디 찾기</title>
	</head>
	<body>
	
		<form name="findIdform" method="post">
			
			<table class="findIdView">
				<thead>
					<tr>
						<td colspan="2">아이디 찾기</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="u_label"><label for="name">이름</label></td>
						<td class="u_label_c"><input type="text" name="name" size="20" id="findId_name"/></td>
					</tr>
					<tr>
						<td class="u_label"><label for="email">이메일</label></td>
						<td class="u_label_c"><input type="text" name="email" size="20" id="findId_email"/></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<input type="button" value="찾기" id="findidbutton"/>
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</body>
</html>
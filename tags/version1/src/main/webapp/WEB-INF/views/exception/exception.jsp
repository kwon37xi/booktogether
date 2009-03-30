<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link rel="stylesheet" type="text/css" href="/styles/common/message.css" />
		<title>에러페이지</title>
	</head>
	<body>
	
		<table id="message">
			<thead>
				<tr>
					<td>오류보고</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="message_c">
						요청을 처리하는 과정에서 문제가 발생 했습니다.<br/>
						불편을 드려서 대단히 죄송합니다.<br/>
						<c:catch >
							 <%
								Throwable exception=(Throwable)request.getAttribute("exception");
								exception.printStackTrace();
							 %>
						</c:catch>
					</td>
				</tr>
			</tbody>
		</table>
		
		<div id="navigator">
			<a href="/index.do">홈으로</a>
		</div>
	
		
</body>
</html>
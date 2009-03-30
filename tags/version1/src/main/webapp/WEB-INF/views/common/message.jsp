<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link rel="stylesheet" type="text/css" href="/styles/common/message.css" />
		<title>알림</title>
	</head>
	<body>
		<table id="message">
			<thead>
				<tr>
					<td>메세지</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="message_c">
						<c:if test="${sessionScope.message!=null}">
							${sessionScope.message}
						</c:if>
						<c:remove scope="session" var="message"/>
					</td>
				</tr>
			</tbody>
		</table>
		
		<div id="navigator">
			<a href="/index.do">홈으로</a>
		</div>
	</body>
</html>
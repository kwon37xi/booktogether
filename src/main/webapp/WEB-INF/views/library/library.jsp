<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/book/book.js"></script>
		<title>${library.user.name}님 서재</title>
	</head>
	<body>
		<table id="library_main_intro">
			<thead>
				<tr>
					<td>인사말</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${library.notice}</td>
				</tr>
			</tbody>							
		</table>
		
		<table id="library_main">
			<tbody>
				<tr>
					<td>
						content
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>
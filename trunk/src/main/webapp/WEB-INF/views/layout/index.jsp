<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/common/default.css" rel="stylesheet" type="text/css"/>
		<link href="/styles/main/main.css" rel="stylesheet" type="text/css"/>
		<link href="/styles/main/leftmain.css" rel="stylesheet" type="text/css"/>
		<link href="/styles/main/rightmain.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/book/book.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/user/user.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/common/common.js"></script>
		
		<title>Java Spring 2.5 기반 책 함께보기</title>
	</head>
	<body>
		<table id="layout">
			<tr>	
				<td valign="top">
					<jsp:include page="./leftmain.jsp" flush="false"></jsp:include>
				</td>
				<td valign="top">
					<jsp:include page="./rightmain.jsp" flush="false"></jsp:include>
				</td>
			</tr>
		</table>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<title><decorator:title default="Java Spring 2.5 기반 책 함께보기" /></title>
		<link rel="stylesheet" type="text/css" href="/styles/common/default.css" />
		
		<script type="text/javascript" charset="utf-8" src="/scripts/common/jquery.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/common/common.js"></script>
		
		<decorator:head />
	</head>
	<body>
		<table id="layout">
			<tr>
				<td>Top 부분</td>	
			</tr>
			<tr>
				<td>
					<decorator:body/>
				</td>
			</tr>
			<tr>
				<td>Footer 부분</td>
			</tr>
		</table>
	</body>
</html>
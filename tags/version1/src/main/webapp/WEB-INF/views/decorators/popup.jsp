<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<title><decorator:title default="Java Spring 2.5 기반 책 함께보기" /></title>
		<link href="/styles/common/popupdefault.css" rel="stylesheet" type="text/css"/>
		
		<script type="text/javascript" charset="utf-8" src="/scripts/common/jquery.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/common/common.js"></script>
		
		<decorator:head />
		
	</head>
	<body>
		<decorator:body/>
	</body>
</html>
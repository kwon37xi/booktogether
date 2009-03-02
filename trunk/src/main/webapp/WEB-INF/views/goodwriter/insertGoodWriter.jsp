<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/common/default.css" rel="stylesheet" type="text/css"/>
		<title>이 주의 명언</title>
	</head>
	<body>
		<div>
			<form method="post" name="insertGoodWriterform" action="/goodwriter/insertGoodWriter.do">
				<p>
					<label for="name">내용</label>
					<textarea rows="20" cols="60" name="content"></textarea>
				</p>
				<input type="submit" value="등록"/>
			</form>
		</div>
	</body>
</html>
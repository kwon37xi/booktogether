<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>책 함께 보기 프로젝트 시작</title>
</head>
<body>

	<c:if test="${sessionScope.message!=null}">
		<script>
			alert('${sessionScope.message}');
		</script>
		<c:remove scope="session" var="message"/>
	</c:if>

	<p>
	이제 시작합니다.화이팅*ㅁ*
	</p>
</body>
</html>
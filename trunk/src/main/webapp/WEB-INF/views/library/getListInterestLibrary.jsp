<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/common/default.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/library/library.js"></script>
		<title>관심 서재 목록</title>
	</head>
	<body>
	
		<c:if test="${sessionScope.message!=null}">
			<script>
				alert('${sessionScope.message}');
			</script>
			<c:remove scope="session" var="message"/>
		</c:if>
	
		<table>
			<thead>
				<tr>
					<td>관심 서재 목록</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach begin="0" items="${interestLibraryList}" var="interestLibraryInfo" varStatus="status">
					<tr>
						<td>${status.index + 1}</td>
						<td><a href="/library/getLibrary.do?userId=${interestLibraryInfo.userId}">${interestLibraryInfo.name}</a></td>
						<td>${interestLibraryInfo.nickname}</td>
						<td>${interestLibraryInfo.email}</td>
						<td><a href="/library/deleteInterestLibrary.do?target=${interestLibraryInfo.idNum}">삭제</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>
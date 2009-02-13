<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="../../styles/common/default.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="../../scripts/user/user.js"></script>
		<title>아이디 중복 확인</title>
	</head>
	<body>
		<form name="duplicateuseridform" action="/user/duplicateUserId.do" method="post">
			<table>
				<thead>
					<tr>
						<td>아이디 중복확인</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<input type="text" name="user_id" id="user_id" size="20" value="${requestScope.user_id}"/>
							<input type="submit" value="중복조회"/>
						</td>
					</tr>
				</tbody>
				<tfoot></tfoot>
			</table>
		</form>
		
		검색 결과
		<c:if test="${requestScope.message!=null && requestScope.result_div!=null}">
			<table>
				<thead>
				</thead>
				<tbody>
					<tr>
						<td>
							<c:choose>
								<c:when test="${requestScope.result_div==1}">
									<b>${requestScope.message}</b>
									<br/>
									<input type="button" value="사용하기" onclick="useId('${requestScope.user_id}')"/>
								</c:when>
								<c:otherwise>
									<b>${requestScope.message}</b>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</tbody>
				<tfoot></tfoot>
			</table>
		</c:if>
	</body>
</html>
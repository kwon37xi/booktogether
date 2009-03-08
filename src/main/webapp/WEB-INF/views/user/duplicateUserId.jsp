<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/user/duplicateuserid.js"></script>
		<title>아이디 중복 확인</title>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<form name="duplicateuseridform" method="post">
			<table>
				<thead>
					<tr>
						<td>아이디 중복확인</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<input type="text" name="userId" id="userId" size="20" value="${requestScope.userId}"/>
							<!-- 
							<input type="submit" value="중복조회"/>
						 	-->
						 	<span style="cursor: pointer;" id="checkDuplicate">중복조회</span>
						</td>
					</tr>
				</tbody>
				<tfoot></tfoot>
			</table>
		</form>
		
		검색 결과
		<div id="searchResult" style="display: none;">
			<table>
				<thead>
				</thead>
				<tbody>
					<tr>
						<td>
						<b id="message"></b>
						<input type="button" name="useId" id="useId" value="사용하기"/>
						<!--
							<c:choose>
								<c:when test="${requestScope.resultDiv==1}">
									<b>${requestScope.message}</b>
									<br/>
									<input type="button" value="사용하기" onclick="useId('${requestScope.userId}')"/>
								</c:when>
								<c:otherwise>
									<b>${requestScope.message}</b>
								</c:otherwise>
							</c:choose>
						-->
						</td>
					</tr>
				</tbody>
				<tfoot></tfoot>
			</table>
		</div>
	</body>
</html>
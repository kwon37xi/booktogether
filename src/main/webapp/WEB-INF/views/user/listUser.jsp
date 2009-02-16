<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/common/default.css" rel="stylesheet" type="text/css"/>
		<title>책목록</title>
	</head>
	<body>
		<table border='1'>
			<thead>
				<tr>
					<td>번호</td>
					<td>ID</td>
					<td>이름</td>
					<td>닉네임</td>
					<td>이메일</td>
					<td>비밀번호</td>
					<td>탈퇴여부</td>
					<td>등록일</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach begin="0" items="${userList}" var="userInfo" varStatus="status">
					<tr>
						<td>${userInfo.idNum}</td>
						<td><a href="/user/getUser.do?id=${userInfo.idNum}">${userInfo.userId}</a></td>
						<td>${userInfo.name}</td>
						<td>${userInfo.nickname}</td>
						<td>${userInfo.email}</td>
						<td>${userInfo.pw}</td>
						<td>${userInfo.isdelete}</td>
						<td>${userInfo.inputDate}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot></tfoot>
		</table>
		
		
		<div id='page_div'>
			<c:if test="${pageBean.prePage}">
				<a href="javascript:go_page('${pageBean.startPage-pageBean.limit}')">이전</a>
			</c:if>
			
			<c:forEach begin="${pageBean.startPage}" end="${pageBean.endPage}" var='i'>
				<a href="javascript:go_page('${i}')">[ ${i} ]</a>
			</c:forEach>
				
			<c:if test="${pageBean.nextPage}">
				<a href="javascript:go_page('${pageBean.startPage+pageBean.limit}')">다음</a>
			</c:if>
		</div>
		
	</body>
</html>
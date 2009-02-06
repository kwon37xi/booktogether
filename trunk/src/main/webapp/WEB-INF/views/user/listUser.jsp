<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
				<c:forEach begin="0" items="${userlist}" var="user_info" varStatus="status">
					<tr>
						<td>${user_info.id}</td>
						<td><a href="/user/getUser.do?id=${user_info.id}">${user_info.user_id}</a></td>
						<td>${user_info.name}</td>
						<td>${user_info.nickname}</td>
						<td>${user_info.email}</td>
						<td>${user_info.pw}</td>
						<td>${user_info.delete_y_n}</td>
						<td>${user_info.input_date}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot></tfoot>
		</table>
		
		
		<div id='page_div'>
			<c:if test="${pageBean.prepage}">
				<a href="javascript:go_page('${pageBean.startPage-pageBean.limit}')">이전</a>
			</c:if>
			
			<c:forEach begin="${pageBean.startPage}" end="${pageBean.endPage}" var='i'>
				<a href="javascript:go_page('${i}')">[ ${i} ]</a>
			</c:forEach>
				
			<c:if test="${pageBean.nextpage}">
				<a href="javascript:go_page('${pageBean.startPage+pageBean.limit}')">다음</a>
			</c:if>
		</div>
		
	</body>
</html>
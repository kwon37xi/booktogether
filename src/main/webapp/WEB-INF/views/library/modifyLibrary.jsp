<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/common/default.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/book/book.js"></script>
		<title>서재 수정하기</title>
	</head>
	<body>
		
		<c:if test="${sessionScope.message!=null}">
			<script>
				alert('${sessionScope.message}');
			</script>
			<c:remove scope="session" var="message"/>
		</c:if>
	
		<form name="modifyLibraryform" method="post" action="/library/modifyLibrary.do">
			<input type="hidden" name="idNum" value="${library.idNum}"/>
			<table border='1'>
				<thead></thead>
				<tbody>
					<tr>
						<td>메뉴</td>
						<td>내용</td>
					</tr>
					<tr>
						<td>알림말</td>
						<td>
							<textarea name="notice" rows="3" cols="50">${library.notice}</textarea>
						</td>
					</tr>
					<tr>
						<td>공개/비공개</td>
						<td>
							<input type="radio" name="isOpen" value="0" ${library.isOpen==0 ? 'checked' : ''}/>공개<br/>
							<input type="radio" name="isOpen" value="1" ${library.isOpen==1 ? 'checked' : ''}/>비공개
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<input type="submit" value="수정"/>
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
		<div id=''>
			<a href="javascript:history.go(-1)">뒤로</a>
		</div>
		
	</body>
</html>
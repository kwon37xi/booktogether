<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link rel="stylesheet" type="text/css" href="/styles/user/user.css" />
		<title>사용자 ID찾기</title>
	</head>
	<body>
		<table class="findidresult">
			<thead>
				<tr>
					<td>사용자 ID찾기</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${name}(${email})님이 요청에 대한 결과</td>
				</tr>
				<tr>
					<td>
						<c:choose>
							<c:when test="${userId==''}"><b>해당 아이디가 존재하지 않습니다.</b></c:when>
							<c:otherwise>요청하신 아이디는 <b>${userId}</b>입니다.</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td><a href="javascript:self.close();">닫기</a></td>
				</tr>
			</tfoot>
		</table>
		
	</body>
</html>
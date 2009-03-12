<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link rel="stylesheet" type="text/css" href="/styles/user/usermodify.css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/user/user.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/user/modify.js"></script>
		<title>회원수정</title>
	</head>
	<body>
	
		<form method="post" name="modifyuser_form" action="/user/modifyUser.do"  enctype="multipart/form-data">

			<input type="hidden" name="userAddInfoIdNum" value="${userInfo.userAddInfo.idNum}"/>
		
			<table id="modifyuser">
				<thead>
					<tr>
						<td colspan="2">${userInfo.userId}님 정보 수정</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="u_label"><label for="nickname">별명</label></td>
						<td class="u_label_c"><input type="text" name="nickname" size="20" value="${userInfo.nickname}"/></td>
					</tr>	
					<tr>
						<td class="u_label"><label for="name">이름</label></td>
						<td class="u_label_c"><input type="text" name="name" size="20" value="${userInfo.name}"/></td>
					</tr>	
					<tr>
						<td class="u_label"><label for="name">이메일</label></td>
						<td class="u_label_c"><input type="text" name="email" size="20" value="${userInfo.email}"/></td>
					</tr>
					<tr>
						<td class="u_label"><label for="blog">블로그</label></td>
						<td class="u_label_c"><input type="text" name="blog" size="20" value="${userInfo.userAddInfo.blog}"/></td>
					</tr>
					<tr>
						<td class="u_label"><label for="thumnail">이미지</label></td>
						<td class="u_label_c">
							<input type="hidden" name="currThumnail" value="${userInfo.userAddInfo.thumnail}"/>
							<input type="hidden" name="isdefaultThumnail" value="no" id="isdefaultThumnail"/>
							<c:if test="${fn:length(userInfo.userAddInfo.thumnail) > 13}">
								<img src="/images/user/thumnail/${userInfo.userAddInfo.thumnail}"/>
							</c:if>
							<c:if test="${fn:length(userInfo.userAddInfo.thumnail) == 13}">
								<img src="/images/user/userDefault.png"/>
							</c:if>	
							<input type="button" value="기본사진으로변경" onclick="defaultThumnail()"/>
							<input type="button" value="변경" onclick="addThumnail()"/>
							<div id="modifythumnaildiv"></div>
						</td>
					</tr>
					<tr>
						<td class="u_label"><label>생활반경</label></td>
						<td class="u_label_c">
							<c:set var="index" value="0"/>
							
							<c:forEach begin="0" items="${userInfo.zones}" var="zoneInfo" varStatus="status">
								<c:set var="index" value="${index+1}"/>
								<p>
									${zoneInfo.zoneName} <input type="button" value="삭제" onclick="deleteZone('${zoneInfo.idNum}')"/>
								</p>
							</c:forEach>
							
							<c:forEach begin="${index}" end="2" var="i">
								<p>
									<input type="hidden" name="zone" id="h_zone${i}"/>
									<input type="text" name="t_zone${i}" id="t_zone${i}" size="30" readonly="readonly"/>
									<input type="button" value="찾기" id="f_zone${i}" onclick="findAddr('${i}')"/>
								</p>
							</c:forEach>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<input type="button" value="수정" onclick="modifyuser()"/>
							<input type="button" value="뒤로" onclick="go_back()"/>
						</td>
					</tr>
				</tfoot>
			</table>
				
		</form>
	</body>
</html>
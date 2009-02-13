<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="../../styles/common/default.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="../../scripts/common/prototype.js"></script>
		<script type="text/javascript" charset="utf-8" src="../../scripts/common/common.js"></script>
		<script type="text/javascript" charset="utf-8" src="../../scripts/user/user.js"></script>
		<title>회원수정</title>
	</head>
	<body>
	
		<c:if test="${sessionScope.message!=null}">
			<script>
				alert('${sessionScope.message}');
			</script>
			<c:remove scope="session" var="message"/>
		</c:if>
		
		<div>
			<form method="post" name="modifyuser_form" action="/user/modifyUser.do"  enctype="multipart/form-data">
				<input type="hidden" name="userAddInfo_id" value="${user_info.userAddInfo.id}"/>
			
				<p>			
					${user_info.user_id}
				</p>
				<p>
					<label for="nickname">별명</label>
					<input type="text" name="nickname" size="20" value="${user_info.nickname}"/>
				</p>
				<p>
					<label for="name">이름</label>
					<input type="text" name="name" size="20" value="${user_info.name}"/>
				</p>
				<p>
					<label for="name">이메일</label>
					<input type="text" name="email" size="20" value="${user_info.email}"/>
				</p>
				<p>
					<label for="blog">블로그</label>
					<input type="text" name="blog" size="20" value="${user_info.userAddInfo.blog}"/>
				</p>
				<p>
					<label for="thumnail">이미지</label>
					<input type="hidden" name="curr_thumnail" value="${user_info.userAddInfo.thumnail}"/>
					${user_info.userAddInfo.thumnail}
					<input type="button" value="변경" onclick="addThumnail()"/>
				</p>

				<div id="modifythumnaildiv"></div>
				
				<p>
					<label>생활반경</label>
					<c:forEach begin="0" items="${user_info.zones}" var="zone_info" varStatus="status">
						${zone_info.zone} <input type="button" value="삭제" onclick="deleteZone('${zone_info.id}')"/>
					</c:forEach>
					<br/>
					<input type="button" value="추가" onclick="addZone()"/>
				</p>
				<div id="insertzonesdiv"></div>
				
				<input type="submit" value="수정"/>
				
				<input type="button" value="뒤로" onclick="go_back()"/>
			</form>
		</div>
	</body>
</html>
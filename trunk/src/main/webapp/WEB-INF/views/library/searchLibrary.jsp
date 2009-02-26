<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/common/default.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/library/library.js"></script>
		<title>서재 검색</title>
	</head>
	<body>
	
		<c:if test="${sessionScope.message!=null}">
			<script>
				alert('${sessionScope.message}');
			</script>
			<c:remove scope="session" var="message"/>
		</c:if>
	
	
		<form name="searchLibraryform" action="/library/searchLibrary.do" method="post">
			<select name="searchType">
				<option value="userId" ${param.searchType=='id' ? 'selected' : ''}>아이디</option>
				<option value="name" ${param.searchType=='name' ? 'selected' : ''}>이름</option>
				<option value="nickname" ${param.searchType=='nickname' ? 'selected' : ''}>별명</option>
			</select>
			<input type="text" name="query" size="30" value="${param.query}"/><input type="submit" value="검색"/>
		</form>
		
		<c:choose>
			<c:when test="${userList==null}">
				처음 화면이라요~~!!!
			</c:when>
			<c:otherwise>
				<table border="1">
					<thead>
						<tr>
							<td colspan="4">결과물</td>
						</tr>	
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${fn:length(userList)!=0}">
								<c:forEach begin="0" items="${userList}" varStatus="status" var="userInfo">
									<tr>
										<td>
											<table>
												<tr>
													<td rowspan="3">
														<img src="/images/user/thumnail/${userInfo.userAddInfo.thumnail}"/>
													</td>
													<td> 이름 : ${userInfo.name}</td>
												</tr>
												<tr>
													<td> 별명 : ${userInfo.nickname}</td>
												</tr>
												<tr>
													<td> 개인서재 : <a href="/library/getLibrary.do?userId=${userInfo.userId}">바로가기</a></td>
												</tr>
											</table>
										</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td>검색 결과가 없습니다.</td>
								</tr>
							</c:otherwise>				
						</c:choose>
					</tbody>
					<tfoot></tfoot>
				</table>	
			</c:otherwise>
		</c:choose>
		
		<br/>
		<br/>
		<br/>
		
		<table>
			<thead>
				<tr>
					<td>활동 높은 사용자</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach begin="0" items="${libraryRankList}" var="libraryRankInfo" varStatus="status">
					<tr>
						<td>${status.index + 1}</td>
						<td><a href="/library/getLibrary.do?userId=${libraryRankInfo.userId}">${libraryRankInfo.name}(${libraryRankInfo.nickname})</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>
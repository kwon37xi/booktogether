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
		<title>${library.user.name}님 서재</title>
	</head>
	<body>
		
		<c:if test="${sessionScope.message!=null}">
			<script>
				alert('${sessionScope.message}');
			</script>
			<c:remove scope="session" var="message"/>
		</c:if>
	
	
		<table border='1'>
			<thead></thead>
			<tbody>
				<tr>
					<td>메뉴</td>
					<td>내용</td>
				</tr>
				<tr>
					<td>
						<table>
							<tr>
								<td rowspan="3"><img src="/images/user/thumanil/${library.user.userAddInfo.thumnail}"/></td>
								<td>이름 : ${library.user.name}</td>
							</tr>
							<tr>
								<td>닉네임 : ${library.user.nickname}</td>
							</tr>
							<tr>
								<td>아이디 : ${library.user.userId }</td>
							</tr>
							<tr>
								<td colspan="2"><a href="/library/modifyLibraryView.do">서재정보 수정</a> / 관심 서재 등록</td>
							</tr>
							<tr>
								<td colspan="2">${library.user.name}님 서재에 오신걸 환영합니다.</td>
							</tr>
						</table>
						
						
						<table>
							<tr>
								<td><a href="/library/getListLibraryBook.do?state=0&libraryIdNum=${library.idNum}">읽고 싶은책</a></td>
							</tr>
							<tr>
								<td><a href="/library/getListLibraryBook.do?state=1&libraryIdNum=${library.idNum}">읽고 있는 책</a></td>
							</tr>
							<tr>
								<td><a href="/library/getListLibraryBook.do?state=2&libraryIdNum=${library.idNum}">읽은 책</a></td>
							</tr>
							<tr>
								<td><a href="/library/getListPossessBook.do?userId=${library.user.userId}">내가 보유한 책</a></td>
							</tr>
							<tr>
								<td>주위에 있는 책</td>
							</tr>
							<tr>
								<td>관심 서재</td>
							</tr>
							<tr>
								<td>책 검색</td>
							</tr>
							<tr>
								<td>추천책</td>
							</tr>
							<tr>
								<td>리뷰목록</td>
							</tr>
							<tr>
								<td>별점 목록</td>
							</tr>
						</table>
					</td>
					<td>${library.notice}</td>
				</tr>
			</tbody>
			<tfoot></tfoot>
		</table>
		
		<div id=''>
			<a href="javascript:history.go(-1)">뒤로</a>
			<a href="/book/searchBook.do">목록</a>
		</div>
		
	</body>
</html>
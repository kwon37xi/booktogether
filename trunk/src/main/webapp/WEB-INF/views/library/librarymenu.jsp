<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
</head>

<body>
	<table id="user_info">
		<thead>
			<tr>
				<td colspan="2">서재주인</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td rowspan="3"><img src="/images/user/thumnail/${library.user.userAddInfo.thumnail}"/></td>
				<td>이름 : ${library.user.name}</td>
			</tr>
			<tr>
				<td>닉네임 : ${library.user.nickname}</td>
			</tr>
			<tr>
				<td>아이디 : ${library.user.userId}</td>
			</tr>
			<tr>
				<td colspan="2">
					<a href="/library/modifyLibraryView.do">서재정보 수정</a> /
				 	<a href="/library/insertInterestLibrary.do?target=${library.user.idNum}&userId=${library.user.userId}">관심 서재 등록</a>
				 </td>
			</tr>
		</tbody>
	</table>
	
	
	<table id="library_menu">
		<thead>
			<tr>
				<td>메뉴</td>
			</tr>
		</thead>
		<tbody>
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
				<td><a href="/library/getListZoneBook.do?userId=${library.user.userId}">주위에 있는 책</a></td>
			</tr>
			<tr>
				<td><a href="/library/getListInterestLibrary.do?userIdNum=${library.user.idNum}">관심 서재</a></td>
			</tr>
			<tr>
				<td><a href="/book/searchBook.do">책 검색</a></td>
			</tr>
			<tr>
				<td>추천책</td>
			</tr>
			<tr>
				<td><a href="/book/getListMyReview.do?userIdNum=${library.user.idNum}">리뷰목록</a></td>
			</tr>
			<tr>
				<td><a href="/book/getListMyBookGrade.do?userIdNum=${library.user.idNum}">별점 목록</a></td>
			</tr>
			<tr>
				<td><a href="/book/getListMyBookMark.do?userIdNum=${library.user.idNum}">인용구 목록</a></td>
			</tr>
			<tr>
				<td><a href="/libraryboard/getListLibraryBoard.do?libraryIdNum=${library.idNum}">방명록</a></td>
			</tr>
		</tbody>
	</table>
</body>
</html>
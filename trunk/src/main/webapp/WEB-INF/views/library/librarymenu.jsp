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
				<td colspan="2">��������</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td rowspan="3"><img src="/images/user/thumnail/${library.user.userAddInfo.thumnail}"/></td>
				<td>�̸� : ${library.user.name}</td>
			</tr>
			<tr>
				<td>�г��� : ${library.user.nickname}</td>
			</tr>
			<tr>
				<td>���̵� : ${library.user.userId}</td>
			</tr>
			<tr>
				<td colspan="2">
					<a href="/library/modifyLibraryView.do">�������� ����</a> /
				 	<a href="/library/insertInterestLibrary.do?target=${library.user.idNum}&userId=${library.user.userId}">���� ���� ���</a>
				 </td>
			</tr>
		</tbody>
	</table>
	
	
	<table id="library_menu">
		<thead>
			<tr>
				<td>�޴�</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><a href="/library/getListLibraryBook.do?state=0&libraryIdNum=${library.idNum}">�а� ����å</a></td>
			</tr>
			<tr>
				<td><a href="/library/getListLibraryBook.do?state=1&libraryIdNum=${library.idNum}">�а� �ִ� å</a></td>
			</tr>
			<tr>
				<td><a href="/library/getListLibraryBook.do?state=2&libraryIdNum=${library.idNum}">���� å</a></td>
			</tr>
			<tr>
				<td><a href="/library/getListPossessBook.do?userId=${library.user.userId}">���� ������ å</a></td>
			</tr>
			<tr>
				<td><a href="/library/getListZoneBook.do?userId=${library.user.userId}">������ �ִ� å</a></td>
			</tr>
			<tr>
				<td><a href="/library/getListInterestLibrary.do?userIdNum=${library.user.idNum}">���� ����</a></td>
			</tr>
			<tr>
				<td><a href="/book/searchBook.do">å �˻�</a></td>
			</tr>
			<tr>
				<td>��õå</td>
			</tr>
			<tr>
				<td><a href="/book/getListMyReview.do?userIdNum=${library.user.idNum}">������</a></td>
			</tr>
			<tr>
				<td><a href="/book/getListMyBookGrade.do?userIdNum=${library.user.idNum}">���� ���</a></td>
			</tr>
			<tr>
				<td><a href="/book/getListMyBookMark.do?userIdNum=${library.user.idNum}">�ο뱸 ���</a></td>
			</tr>
			<tr>
				<td><a href="/libraryboard/getListLibraryBoard.do?libraryIdNum=${library.idNum}">����</a></td>
			</tr>
		</tbody>
	</table>
</body>
</html>
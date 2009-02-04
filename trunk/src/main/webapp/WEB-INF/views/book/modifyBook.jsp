<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>책조회</title>
	</head>
	<body>
		<form method="POST" name="modifyBook_form" action="/book/modifyBook.do">
			<table border='1'>
				<thead>
					<tr>
						<td>이름</td>
						<td>내용</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>ID</td>
						<td><input type="text" name='id' value='${book_info.id}'/></td>
					</tr>
					<tr>
						<td>책표지</td>
						<td><input type="text" name='corver' value='${book_info.corver}'/></td>
					</tr>
					<tr>
						<td>책이름</td>
						<td><input type="text" name='name' value='${book_info.name}'/></td>
					</tr>
					<tr>
						<td>지은이</td>
						<td>
							<c:forEach begin="0" items="${book_info.authors}" var="author_info">
								id <input type="text" name='author_id' value='${author_info.id}'/><br/>
								이름 <input type="text" name='author_name' value='${author_info.name}'/><br/>
								구분 <input type="text" name='author_div' value='${author_info.author_div}'/><br/>  
								<br/>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td>ISBN</td>
						<td>
							ISBN10<input type="text" name='isbn10' value='${book_info.ISBN10}'/><br/>
							ISBN13<input type="text" name='isbn13' value='${book_info.ISBN13}'/>
						</td>
					</tr>
					<tr>
						<td>출판사/출판일</td>
						<td>
							<input type="text" name='publish_comp' value='${book_info.publish_comp}'/><br/>
							<input type="text" name='publish_date' value='${book_info.publish_date}'/>
						</td>
					</tr>
					<tr>
						<td>가격</td>
						<td><input type="text" name='price' value='${book_info.price}'/></td>
					</tr>
					<tr>
						<td>카테고리</td>
						<td><input type="text" name='category' value='${book_info.category}'/></td>
					</tr>
					<tr>
						<td>설명</td>
						<td><input type="text" name='description' value='${book_info.description}'/></td>
					</tr>
				</tbody>
				<tfoot></tfoot>
			</table>
			<input type="submit" value="수정"/>			
		</form>
		
		<div id=''>
			<a href="javascript:history.go(-1)">뒤로</a>
		</div>
		
	</body>
</html>
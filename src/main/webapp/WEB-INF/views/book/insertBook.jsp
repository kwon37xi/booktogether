<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>책 등록</title>
	</head>
	<body>
		<div>
			<form method="post" name="insertbook_form" action="/book/insertBook.do">
				<p>			
					<label for="name">책 제목</label>
					<input type="text" name="name" size="20"/>
				</p>
				<p>
					<label for="name">지은이 이름1</label>
					<input type="text" name="author_name" size="20"/>
				</p>
				<p>
					<label for="name">지은이 구분1 </label>
					<input type="text" name="author_div" size="20"/>
				</p>
				<p>
					<label for="name">지은이 이름2</label>
					<input type="text" name="author_name" size="20"/>
				</p>
				<p>
					<label for="name">지은이 구분 2</label>
					<input type="text" name="author_div" size="20"/>
				</p>
				<p>
					<label for="isbn10">ISBN(10자리)</label>
					<input type="text" name="isbn10" size="20"/>
				</p>
				<p>
					<label for="isbn13">ISBN(13자리)</label>
					<input type="text" name="isbn13" size="20"/>
				</p>
				<p>
					<label for="publish_comp">출판사</label>
					<input type="text" name="publish_comp" size="20"/>
				</p>
				<p>
					<label for="publish_date">출판일</label>
					<input type="text" name="publish_date" size="20"/>
				</p>
				<p>				
					<label for="price">가격</label>
					<input type="text" name="price" size="20"/>
				</p>
				<p>
					<label for="corver">이미지</label>
					<input type="text" name="corver" size="20"/>
				</p>
				<p>
					<label for="category">카테고리</label>
					<input type="text" name="category" size="20"/>
				</p>
				<p>
					<label for="content">책 설명</label>
					<input type="text" name="description" size="20"/>
				</p>
				<p>
				<input type="submit" value="등록"/>
				
			</form>
		</div>
	</body>
</html>
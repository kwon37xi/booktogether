<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/library/board.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/library/library.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/libraryboard/insertlibraryboard.js"></script>
		
		<title>방명록</title>
	</head>
	<body>
	
		<table>
			<thead>
				<tr>
					<td class="title">방명록</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach begin="0" items="${libraryBoardList}" var="libraryBoardInfo" varStatus="status">
					<tr>
						<td>
							<img src="/images/library/board_bullet.gif" width="12" height="12"/>
							${libraryBoardInfo.content} / ${libraryBoardInfo.writerUserId} / ${libraryBoardInfo.inputDate}
							<c:if test="${libraryBoardInfo.writer==sessionScope.idNum}">
								<a href="/library/deleteLibraryBoard.do?boardIdNum=${libraryBoardInfo.idNum}&libraryIdNum=${libraryBoardInfo.libraryIdNum}">
									<img src="/images/common/delete.png" style="cursor: pointer;"/>
								</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			<tr>
				<td></td>
			</tr>
		</table>
		
		<c:choose>
			<c:when test="${sessionScope.idNum!=null}">
				<form action="/library/insertLibraryBoard.do" method="post" name="insertLibraryBoardform">
				
					<input type="hidden" name="libraryIdNum" value="${libraryIdNum}"/>
					
					
					<input type="text" name="content" size="40"/>
					<input type="button" value="입력" onclick="insertLibraryBoard()"/>
					
				</form>
			</c:when>
			<c:otherwise>
				로그인후 작성 할수 있습니다
			</c:otherwise>
		</c:choose>
	</body>
</html>
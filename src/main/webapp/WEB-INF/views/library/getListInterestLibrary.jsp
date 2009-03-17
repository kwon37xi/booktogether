<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/possess/possessbook.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/library/library.js"></script>
		<title>관심 서재 목록</title>
	</head>
	<body>
	
		<table id="interestlibrary">
			<thead>
				<tr>
					<td colspan="5">관심 서재 목록</td>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(interestLibraryList)!=0 && interestLibraryList!=null}">
						<c:forEach begin="0" items="${interestLibraryList}" var="interestLibraryInfo" varStatus="status">
							<tr>
								<td>${status.index + 1}</td>
								<td><a href="/library/getLibrary.do?userId=${interestLibraryInfo.userId}">${interestLibraryInfo.name}</a></td>
								<td>${interestLibraryInfo.nickname}</td>
								<td>${interestLibraryInfo.email}</td>
								<td>
									<a href="/library/deleteInterestLibrary.do?target=${interestLibraryInfo.idNum}&libraryIdNum=${library.idNum}">
										<img src="/images/common/delete.png"/>
									</a>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td class="nocontent">등록된 관심서재가 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<title><decorator:title default="Java Spring 2.5 기반 책 함께보기" /></title>
		<link rel="stylesheet" type="text/css" href="/styles/common/default.css" />
		<link rel="stylesheet" type="text/css" href="/styles/common/layout.css" />
		<link rel="stylesheet" type="text/css" href="/styles/library/library.css" />
		
		<script type="text/javascript" charset="utf-8" src="/scripts/common/jquery.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/common/cornerz.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/common/common.js"></script>
		
		<decorator:head />
	</head>
	<body>
	
		<c:if test="${sessionScope.message!=null}">
			<script>
				alert('${sessionScope.message}');
			</script>
			<c:remove scope="session" var="message"/>
		</c:if>
	
		<table id="layout">
			<tr>
				<td>
					<table id="top_navigator">
						<tr>
						    <td class="logo" onclick="go_home()"><img src="/images/main/logo_LBT.gif" width="43" height="44"/></td>
    						<td class="logo_title" onclick="go_home()"> Love,<br/> Book Together</td>
    						<td class="menu1"><img src="/images/main/btn_about.png" width="67" height="29"/></td>
   	 						<td class="menu2"><img src="/images/main/btn_sitemap.png" width="75" height="29"/></td>
    						<td class="menu3"><img src="/images/main/btn_personalInfo.png" width="110" height="29"/></td>
    						<td class="menu4"><img src="/images/main/btn_help.png" width="51" height="29"/></td>
    						<td class="menu5"><img src="/images/main/btn_login.png" width="60" height="29"/></td>
						</tr>
					</table>
				</td>
			<tr>
				<td>
					<table id="searchbook_div">
						<tr>
						    <td class="count_info"><span class="style5">&nbsp;&nbsp; count : </span></td>
						    <td class="search_div">
								<form name="searchBookform" action="/book/searchBook.do" method="post">
									<input type="hidden" name="pageNo" value="1"/>
									<input type="hidden" name="beforeQuery" value="${requestScope.query}"/>
									
									<input type="radio" name="searchType" value="book" checked/>책&nbsp;&nbsp;
									<input type="radio" name="searchType" value="library"/>서재 &nbsp;&nbsp;
									
									<input type="text" name="query" size="30" value="${requestScope.query}"/>
									<input type="submit" value="검색"/>
								</form>
							</td>
						</tr>
					</table>
				</td>	
			</tr>
			<tr>
				<td>
					<table id="library">
						<thead>
							<tr>
								<td colspan="2">${library.user.name}님 서재에 오신걸 환영합니다</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td valign="top">
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
													<c:if test="${sessionScope.idNum!=null ||sessionScope.idNum==library.user.userId}">
														<a href="/library/modifyLibraryView.do">서재정보 수정</a>
													</c:if>
													
													<c:if test="${sessionScope.idNum!=null}">
														/
												 		<a href="/library/insertInterestLibrary.do?target=${library.user.idNum}&userId=${library.user.userId}">관심 서재 등록</a>
												 	</c:if>
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
												<td><a href="/library/getListPossessBook.do?libraryIdNum=${library.idNum}&userId=${library.user.userId}">내가 보유한 책</a></td>
											</tr>
											<tr>
												<td><a href="/library/getListZoneBook.do?userId=${library.user.userId}">주위에 있는 책</a></td>
											</tr>
											<tr>
												<td><a href="/library/getListInterestLibrary.do?libraryIdNum=${library.idNum}&userIdNum=${library.user.idNum}">관심 서재</a></td>
											</tr>
											<tr>
												<td><a href="/index.do">책 검색</a></td>
											</tr>
											<tr>
												<td>추천책</td>
											</tr>
											<tr>
												<td><a href="/library/getListMyReview.do?libraryIdNum=${library.idNum}&userIdNum=${library.user.idNum}">리뷰목록</a></td>
											</tr>
											<tr>
												<td><a href="/library/getListMyBookGrade.do?libraryIdNum=${library.idNum}&userIdNum=${library.user.idNum}">별점 목록</a></td>
											</tr>
											<tr>
												<td><a href="/library/getListMyBookMark.do?libraryIdNum=${library.idNum}&userIdNum=${library.user.idNum}">인용구 목록</a></td>
											</tr>
											<tr>
												<td><a href="/library/getListLibraryBoard.do?libraryIdNum=${library.idNum}">방명록</a></td>
											</tr>
										</tbody>
									</table>
								</td>
								<td valign="top" id="library_body">
									<decorator:body/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<form action="/library/searchBookInLibrary.do" name="searchBookInLibraryform" method="post">
										<input type="hidden" name="libraryIdNum" value="${library.idNum}"/>
										<table id="searchbook">
											<thead>
												<tr>
													<td>${library.user.name}님 서재 책검색</td>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>
														책이름 : <input type="text" name="bookName" size="20" />
														<input type="submit" value="검색"/>
													</td>
												</tr>
											</tbody>
										</table>
									</form>
								</td>
							</tr>
						</tfoot>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table id="footer">
  						<tr>
							<td>
								<b> Copyright @ LoveBookTogether :D </b>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
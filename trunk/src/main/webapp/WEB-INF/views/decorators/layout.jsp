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
						    <td class="count_info"><span class="style5">&nbsp;&nbsp; Love, Book Together에 오신것을 환영합니다 </span></td>
						    <td class="search_div">
								<form name="searchBookform" action="/book/searchBook.do" method="post">
									<input type="hidden" name="pageNo" value="1"/>
									<input type="hidden" name="beforeQuery" value="${requestScope.query}"/>
									
									<input type="radio" name="searchType" value="book" ${searchType=='book'? 'checked' :''}/>책&nbsp;&nbsp;
									<input type="radio" name="searchType" value="library" ${searchType=='library'? 'checked' :''}/>서재 &nbsp;&nbsp;
									
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
					<decorator:body/>
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
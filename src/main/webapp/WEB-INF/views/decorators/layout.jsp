<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<title><decorator:title default="Java Spring 2.5 기반 책 함께보기" /></title>
		<link rel="stylesheet" type="text/css" href="/styles/common/default.css" />
		<link rel="stylesheet" type="text/css" href="/styles/common/layout.css" />
		<link rel="stylesheet" type="text/css" href="/styles/main/menubar.css" />
		
		<script type="text/javascript" charset="utf-8" src="/scripts/common/formvalidate.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/common/jquery.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/common/cornerz.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/common/common.js"></script>
				
		<decorator:head />
	</head>
	<body>
	
		<c:if test="${empty searchType}">
			<c:set value="book" var="searchType"/>		
		</c:if>
	
		<table id="layout">
			<tr>
				<td>
					<table id="top_navigator">
						<tr>
						    <td class="logo" onclick="go_home()"><img src="/images/main/logo_LBT.gif" width="43" height="44" style="cursor: pointer;"/></td>
    						<td class="logo_title" onclick="go_home()"  style="cursor: pointer;"> Love,<br/> Book Together</td>
							<td>
								<div id="menu">
									<ul>
										<c:choose>
											<c:when test="${sessionScope.idNum==null}">
												<li><a href="javascript:go_home()"> Log-in</a></li>
											</c:when>
											<c:when test="${sessionScope.idNum!=null}">
												<li><a href="javascript:logout()"> Log-Out</a></li>
											</c:when>
										</c:choose>
										<li><a href="/sitemap.do"> Sitemap</a></li>
										<li><a href="/faq.do"> FAQ</a></li>
										<li><a href="/help.do"> Help</a></li>
										<li><a href="/aboutus.do"> About us</a></li>			
									</ul>
								</div>		
							</td>
						</tr>
					</table>
				</td>
			<tr>
				<td>
					<table id="searchbook_div">
						<tr>
						    <td class="count_info"><span class="style5">&nbsp;&nbsp; Love, Book Together에 오신 것을 환영합니다. </span></td>
						    <td class="search_div">
								<form name="searchBookform" action="/book/searchBook.do" method="get">
									<input type="hidden" name="pageNo" value="1"/>
									<input type="hidden" name="beforeQuery" value="${requestScope.query}"/>
									
									<input type="radio" name="searchType" value="book" ${searchType=='book'? "checked='checked'" :""}/>책&nbsp;&nbsp;
									<input type="radio" name="searchType" value="library" ${searchType=='library'? "checked='checked'" :""}/>서재 &nbsp;&nbsp;
									
									<input type="text" name="query" size="30" value="${requestScope.query}" id="query"/>
									<input type="button" value="검색" onclick="searchBookQuery()"/>
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
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
				<td align="center">
					<table width="760" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td height="10"> </td>
						</tr>
						<tr>
						    <td width="55" align="center" valign="middle"><img src="/images/main/logo_LBT.gif" width="43" height="44"></td>
    						<td width="326" align="left" valign="middle"> Love,<br> Book Together</td>
    						<td width="67" align="center" valign="middle"><img src="/images/main/btn_about.png" width="67" height="29"></td>
   	 						<td width="75" align="center" valign="middle"><img src="/images/main/btn_sitemap.png" width="75" height="29"></td>
    						<td width="110" align="center" valign="middle"><img src="/images/main/btn_personalInfo.png" width="110" height="29"></td>
    						<td width="51" align="center" valign="middle"><img src="/images/main/btn_help.png" width="51" height="29"></td>
    						<td width="60" align="center" valign="middle"><img src="/images/main/btn_login.png" width="60" height="29"></td>
						</tr>
					</table>
					
					<table  width="760" border="0">
						<tr>
							<td height="10"> </td>
						</tr>
					</table>
				
					<table width="760" cellspacing=0>
						<tr bgcolor="#6eaec9">
						    <td width="391" height="37" valign="middle" bgcolor="#6eaec9"><span class="style5">&nbsp;&nbsp; count : </span></td>
						    <td width="346" valign="middle">
								<form name="form1" method="post" action="">
							        <input type="radio" name="searchSelect" value="book" checked>책&nbsp;&nbsp;
									<input type="radio" name="searchSelect" value="library">서재 &nbsp;&nbsp;
									<input type="input" name="keyword" value="search" size="25"> 돋보기
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
					<table width="760" border="0" cellspacing="0">
  						<tr bgcolor="#6eaec9">
							<td width="760" height="50" valign="middle" bgcolor="#6eaec9" align="center">
								<font color="white"><b> Copyright @ LoveBookTogether :D </b></font>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<title><decorator:title default="차세대 교통정보 시스템 프로젝트" /></title>
		<decorator:head />
	</head>
	<body>
		<table id="layout">
			<tr>
				<td>
					<table>
						<tr>
							<td>로고사진</td>
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
								footer
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
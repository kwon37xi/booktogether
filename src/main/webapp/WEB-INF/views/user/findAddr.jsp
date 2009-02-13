<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="../../styles/common/default.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="../../scripts/common/prototype.js"></script>
		<script type="text/javascript" charset="utf-8" src="../../scripts/common/common.js"></script>
		<script type="text/javascript" charset="utf-8" src="../../scripts/user/user.js"></script>
		
		<title>주소찾기</title>
		
	</head>
	<body>
		<form action="/user/findAddr.do" onsubmit="" name="findAddrform">
			<input type="hidden" name="ele_seq" value="${requestScope.ele_seq}"/>
			<table>
				<thead>
					<tr>
						<td>주소찾기</td>
					</tr>	
				</thead>
				<tbody>
					<tr>
						<td>
							<input type="text" name="addr" size="20" value="${requestScope.addr}"/>
							<input type="submit" value="찾기"/>
						</td>
					</tr>	
				</tbody>
				<tfoot></tfoot>
			</table>
		</form>
		
		<br/>
		
		<c:if test="${requestScope.zipcodelist!=null}">
			결과물
			<table>
				<thead>	
					<tr>
						<td>주소</td>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${fn:length(zipcodelist)!=0}">
							<c:forEach begin="0" items="${zipcodelist}" var="addr_info" varStatus="status">
								<tr>
									<td>
										<a href="javascript:choice_addr('${addr_info.seq}','${addr_info.sido} ${addr_info.gugun} ${addr_info.dong}')">${addr_info.sido} ${addr_info.gugun} ${addr_info.dong}</a>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td>검색 결과가 없습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
				<tfoot></tfoot>
			</table>
		</c:if>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/user/user.js"></script>
		
		<title>주소찾기</title>
		
	</head>
	<body>
		<form action="/user/findAddr.do" onsubmit="" name="findAddrform" method="post">
			<input type="hidden" name="eleSeq" value="${requestScope.eleSeq}"/>
			<input type="hidden" name="decorator" value="popup"/>
			<input type="hidden" name="confirm" value="true"/>
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
		
		<c:if test="${requestScope.zipcodeList!=null}">
			결과물
			<table>
				<thead>	
					<tr>
						<td>주소</td>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${fn:length(zipcodeList)!=0}">
							<c:forEach begin="0" items="${zipcodeList}" var="addrInfo" varStatus="status">
								<tr>
									<td>
										<a href="javascript:choice_addr('${addrInfo.idNum}','${addrInfo.sido} ${addrInfo.gugun} ${addrInfo.dong}')">${addrInfo.sido} ${addrInfo.gugun} ${addrInfo.dong}</a>
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
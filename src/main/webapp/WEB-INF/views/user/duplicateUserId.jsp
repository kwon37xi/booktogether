<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/user/user.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/common/jquery.js"></script>
		<title>아이디 중복 확인</title>
		<script type="text/javascript">
		jQuery(function($) {

			$("#useId").click(function(){
				window.returnValue = $(this).attr("userId");
				self.close();
				return false;
			});
			
			$("#userId").click(function(){
				$("#searchResult").fadeOut("fast");
				return false;
			}).keydown(function(e){
				
				if( e.keyCode == 13) {
					$("#checkDuplicate").trigger("click");
					return false;
				}	
				
			});
			
			$("#checkDuplicate").click(function(){

				var userId = $("#userId").val();

				if(jQuery.trim(userId).length == 0){
					alert("id를 입력해 주세요.");
					return false;					
				}
				// Use jQuery Ajax..
				$.ajax({
					type : "post",
					dataType : "json",
					data : {userId: userId},
					url : "/user/duplicateUserIdAjax.do",
					success : function(json){
						// json type으로 처리결과를 받아옴.
						if(json.status == "success") {
							// <b></b> 태그 안에 message를 넣어준다.
							$("b#message").text(json.message);
							// "사용하기" button에  onclick event 넣어준다.
							$("#useId").attr("userId", userId);
							// 숨겨져 있는 div를 보여준다.
							$("#searchResult").fadeIn("fast");
						} else {
							// alert message를 띄우고 div를 숨긴다.
							alert(json.message);
							$("#searchResult").fadeOut("fast");
						}
						return false;
					}
				});
				return false;
			});
		});
		</script>
	</head>
	<body>
		<form name="duplicateuseridform" method="post">
			<table>
				<thead>
					<tr>
						<td>아이디 중복확인</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<input type="text" name="userId" id="userId" size="20" value="${requestScope.userId}"/>
							<!-- 
							<input type="submit" value="중복조회"/>
						 	-->
						 	<span style="cursor: pointer;" id="checkDuplicate">중복조회</span>
						</td>
					</tr>
				</tbody>
				<tfoot></tfoot>
			</table>
		</form>
		
		검색 결과
		<div id="searchResult" style="display: none;">
			<table>
				<thead>
				</thead>
				<tbody>
					<tr>
						<td>
						<b id="message"></b>
						<input type="button" name="useId" id="useId" value="사용하기"/>
						<!--
							<c:choose>
								<c:when test="${requestScope.resultDiv==1}">
									<b>${requestScope.message}</b>
									<br/>
									<input type="button" value="사용하기" onclick="useId('${requestScope.userId}')"/>
								</c:when>
								<c:otherwise>
									<b>${requestScope.message}</b>
								</c:otherwise>
							</c:choose>
						-->
						</td>
					</tr>
				</tbody>
				<tfoot></tfoot>
			</table>
		</div>
	</body>
</html>
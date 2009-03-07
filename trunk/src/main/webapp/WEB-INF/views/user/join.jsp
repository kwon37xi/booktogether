<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"   "http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="/styles/common/default.css" rel="stylesheet" type="text/css"/>
		<link href="/styles/user/join.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/common/common.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/user/user.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/common/jquery.js"></script>
		<title>회원가입</title>
		<script type="text/javascript">
		jQuery(function($){

			$("#userId").click(function(){
				$("#duplicateId").trigger("click");
				return false;
			});
			
			$("#duplicateId").click(function(){
				var url = "/user/duplicateUserIdView.do?decorator=popup&confirm=true";
				var ret = window.showModalDialog(url,window,"dialogWidth:300px; dialogHeight:200px; help:no; scroll:no; resizable;no; status:no");
				$("#userId").val(ret);
				return false;
			});
			
		});
		</script>
	</head>
	<body>
	
		<form enctype="multipart/form-data" method="post" name="insertuser_form" action="/user/insertUser.do">
			<input type="hidden" name="dupliId" value="0"/>
			
			<table class="join1" align="center">
				<thead>
					<tr>
						<td colspan="2">사용자 정보 입력</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="join_label">
							<label for="userId">ID</label>
						</td>
						<td>
							<input type="text" name="userId" id="userId" size="20" readonly="readonly"/><span id="duplicateId" style="cursor: pointer;"> 중복 확인</span>
						</td>
					<tr>
						<td class="join_label">
							<label for="pw">PW</label>
						</td>
						<td>
							<input type="password" name="pw" size="20"/>
						</td>
					</tr>
					<tr>
						<td class="join_label">
							<label for="pwAgain">PW 다시 확인</label>
						</td>
						<td>
							<input type="password" name="pwAgain" size="20"/>
						</td>
					</tr>
					<tr>
						<td class="join_label">
							<label for="email">이메일</label>
						</td>
						<td>
							<input type="text" name="email" size="20"/>
						</td>
					</tr>
					<tr>
						<td class="join_label">
							<label for="nickname">별명</label>
						</td>
						<td>
							<input type="text" name="nickname" size="20"/>
						</td>
					</tr>
					<tr>
						<td class="join_label">
							<label for="name">이름</label>
						</td>
						<td>
							<input type="text" name="name" size="20"/>
						</td>
					</tr>
				</tbody>
			</table>

			<hr/>
			
			<table class="join1" align="center">
				<thead>
					<tr>
						<td colspan="2">추가정보</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="join_label">
							<label for="blog">Blog 주소</label>
						</td>
						<td>
							<input type="text" name="blog" size="20"/>
						</td>
					</tr>
					<tr>
						<td class="join_label">
							<label for="thumnail">이미지</label>
						</td>
						<td>
							<input type="file" name="thumnail" size="30"/>
						</td>
					</tr>
					<tr>
						<td rowspan="3" class="join_label">
							<label for="zone">생활반경</label>
						</td>
						<td>
							<input type="hidden" name="zone" id="h_zone1"/>
							<input type="text" name="t_zone1" id="t_zone1" size="30" readonly="readonly"/>
							<input type="button" value="찾기" id="f_zone1" onclick="findAddr('1')"/>
						</td>
					</tr>
					<tr>
						<td>
							<input type="hidden" name="zone" id="h_zone2"/>
							<input type="text" name="t_zone2" id="t_zone2" size="30" readonly="readonly"/>
							<input type="button" value="찾기" id="f_zone2" onclick="findAddr('2')"/>
						</td>
					</tr>
					<tr>
						<td>
							<input type="hidden" name="zone" id="h_zone3"/>
							<input type="text" name="t_zone3" id="t_zone3" size="30" readonly="readonly"/>
							<input type="button" value="찾기" id="f_zone3" onclick="findAddr('3')"/>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<input type="submit" value="등록"/>
							<input type="button" value="뒤로" onclick="go_back()"/>
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</body>
</html>
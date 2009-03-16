<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link rel="stylesheet" type="text/css" href="/styles/common/default.css" />
		<link rel="stylesheet" type="text/css" href="/styles/user/join.css"/>
		<link rel="stylesheet" type="text/css" href="/styles/user/user.css"/>
		<script type="text/javascript" charset="utf-8" src="/scripts/common/common.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/user/user.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/common/jquery.js"></script>
		<script type="text/javascript" charset="utf-8" src="/scripts/user/join.js"></script>
		<title>회원가입</title>
	</head>
	<body>
	
		<form enctype="multipart/form-data" method="post" name="insertuser_form" action="/user/insertUser.do">
			<input type="hidden" name="dupliId" value="0" id="dupliId"/>
			
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
							<input type="text" name="blog" id="blog" size="35"/>
						</td>
					</tr>
					<tr>
						<td class="join_label">
							<label for="blog">포스팅 유무</label>
						</td>
						<td>
							<input type="checkbox" name="isPostBlog" size="20" id="isPostBlog" value="1"/>이글루스
						</td>
					</tr>
					<tr id="addServerInfo">
						<td class="join_label">블로그 Post<br/>정보 입력 사항</td>
						<td>
							<input type="hidden" name="validBlog" id="validBlog" value="no"/>
							<table class="bloginfo">
								<tbody>
									<tr>
										<td>서버주소</td>
										<td><input type="text" name="webServer" id="webServer" size="30"/></td>
									</tr>
									<tr>
										<td>BLOG ID</td>
										<td>
											<input type="text" name="etcInfo" id="etcInfo"  size="30"/>(tistory 경우 입력)
										</td>
									</tr>
									<tr>
										<td>ID</td>
										<td><input type="text" name="blogId" id="blogId"  size="30"/></td>
									</tr>
									<tr>
										<td>PW</td>
										<td>
											<input type="password" name="blogPw" id="blogPw"  size="30"/>
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td colspan="2">
											<input type="button" value="연결테스트" id="validBlogButton"/>
										</td>
									</tr>
								</tfoot>
							</table>
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
							<input type="button" value="등록" onclick="join()"/>
							<input type="button" value="뒤로" onclick="go_back()"/>
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</body>
</html>
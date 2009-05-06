var join_checker;

jQuery( function($) {

	$("#userId").click( function() {
		$("#duplicateId").trigger("click");
		return false;
	});

	$("#duplicateId")
			.click(
					function() {
						var url = "/user/duplicateUserIdView.do?decorator=popup&confirm=true";
						var ret = window
								.showModalDialog(
										url,
										window,
										"dialogWidth:300px; dialogHeight:200px; help:no; scroll:no; resizable;no; status:no");
						$("#userId").val(ret);

						if (ret != '' || ret != null) {
							$("#dupliId").val("true");
						}

						return false;
					});

	$("#isPostBlog").click( function() {
		$("#addServerInfo").toggle();
	});

	$("#validBlogButton").click(
			function() {

				var webServer = $("#webServer").val();
				var blog = $("#blog").val();
				var etcInfo = $("#etcInfo").val();
				var blogId = $("#blogId").val();
				var blogPw = $("#blogPw").val();

				if (jQuery.trim(webServer).length == 0
						|| jQuery.trim(blog).length == 0
						|| jQuery.trim(blogId).length == 0
						|| jQuery.trim(blogPw).length == 0) {
					alert("올바른 값을 입력하십시요");
					return false;
				}

				$("#validBlogButton").hide();

				// Use jQuery Ajax..
				$.ajax( {
					type :"post",
					dataType :"json",
					data : {
						webServer :webServer,
						blog :blog,
						etcInfo :etcInfo,
						blogId :blogId,
						blogPw :blogPw
					},
					url :"/blog/validBlogAjax.do",
					success : function(json) {

						$("#validBlogButton").show();
						// json type으로 처리결과를 받아옴.
					if (json.status == "true") {
						alert(json.message);
						$("#validBlog").val("true");
					} else {
						alert(json.message);
					}
					return false;
				}
				});
				return false;
			});

	join_checker = new FormChecker(document.insertuser_form);
	join_checker.checkRequired('userId', '아이디를 입력하세요.', true);
	join_checker.checkMinLength('userId', 6, '아이디는 최소 6자리입니다.', true);
	join_checker.checkMaxLength('userId', 15, '아이디는 최대 15자리입니다.', true);

	join_checker.checkRequired('pw', '비밀번호를 입력하세요.', true);
	join_checker.checkMinLength('pw', 4, '비밀번호는 최소 4자리입니다.', true);
	join_checker.checkRequired('pwAgain', '비밀번호 확인란에 입력하세요.', true);
	join_checker.checkTwoFiledValue('pw', 'pwAgain', '값이 일치하지 않습니다.', true);

	join_checker.checkRequired('email', '이메일을 입력하세요.', true);
	join_checker.checkEmail('email', '이메일 형식이 아닙니다.', true);
	join_checker.checkRequired('nickname', '별명을 입력하세요.', true);
	join_checker.checkMaxLength('nickname', 10, '별명은 최대 10자(영문),한글(5)자입니다.',
			true);
	join_checker.checkRequired('name', '성명을 입력하세요.', true);

	$("#addServerInfo").hide();

});

function join() {
	if (join_checker.validate()) {

		if (document.insertuser_form.dupliId.value == '0') {
			alert("중복 검사를 하세요.");
			return;
		}

		document.insertuser_form.submit();
	}
}

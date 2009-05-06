var modify_checker;

$(document).ready( function() {
	modify_checker = new FormChecker(document.modifyuser_form);
	modify_checker.checkRequired('nickname', '별명을  입력해 주세요', true);
	modify_checker.checkRequired('name', '성명을 입력해 주세요', true);
	modify_checker.checkEmail('email','이메일 형식이 아닙니다', true);
});

function modifyuser() {

	if (modify_checker.validate()) {
		document.modifyuser_form.submit();
	}

}

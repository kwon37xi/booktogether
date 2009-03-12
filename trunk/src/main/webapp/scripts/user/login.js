var login_checker;

$(document).ready( function() {
	login_checker = new FormChecker(document.loginform);
	login_checker.checkRequired('userId', '아이디를  입력해 주세요', true);
	login_checker.checkRequired('pw', '비밀번호를 입력해 주세요', true);
	login_checker.checkMinLength('userId',6,'최소 6자리입니다.',true);
	login_checker.checkMaxLength('userId',15,'최대 15자리입니다.',true);
});


function login(){

	if (login_checker.validate()) {
		document.loginform.submit();
	}

}


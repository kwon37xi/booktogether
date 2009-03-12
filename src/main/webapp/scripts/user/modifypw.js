var modifypw_checker;

$(document).ready( function() {
	modifypw_checker = new FormChecker(document.modifyPwform);
	modifypw_checker.checkRequired('pw', '비밀번호를  입력해 주세요', true);
	modifypw_checker.checkRequired('newPw', '새로운 비밀번호를 입력해 주세요', true);
	modifypw_checker.checkMinLength('newPw',4,'최소 4자리 이상입니다', true);
	modifypw_checker.checkRequired('newPwAgain', '새로운 비밀번호 확인란에 입력해 주세요', true);
	modifypw_checker.checkTwoFiledValue('newPw', 'newPwAgain', '값이 일치 하지 않습니다.', true);
	
});

function modifypw() {

	if (modifypw_checker.validate()) {
		document.modifyPwform.submit();
	}

}

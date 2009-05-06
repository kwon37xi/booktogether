var modify_library_checker;

$(document).ready(function(){

	modify_library_checker = new FormChecker(document.modifyLibraryform);
	modify_library_checker.checkRequired('notice', '알림말을 입력하세요', true);
	modify_library_checker.checkMinLengthByte('notice', 10, '최대 10자(영문),한글(5)자입니다', true);

});

function modifyLibrary() {
	if (modify_library_checker.validate()) {

		document.modifyLibraryform.submit();
	}
}

var bookgrade_checker;
var bookmark_checker;

jQuery( function($) {

	if (document.bookgradeform != 'undefined' && document.bookgradeform != null) {
		bookgrade_checker = new FormChecker(document.bookgradeform);
		bookgrade_checker.checkAtLeastOneChecked('grade', '별점을 선택하십시요', true);
	}

	if (document.insertbookmarkform != 'undefined'
			&& document.insertbookmarkform != null) {
		bookmark_checker = new FormChecker(document.insertbookmarkform);
		bookmark_checker.checkRequired('page', '페이지를 입력하세요', true);
		bookmark_checker.checkOnlyNumber('page', '숫자만 가능합니다.', true);
		bookmark_checker.checkRequired('content', '인용구를 입력하세요', true);
	}

});

function insertBookgrade() {

	if (bookgrade_checker.validate()) {

		document.bookgradeform.submit();

	}

}

function insertBookMark() {

	if (bookmark_checker.validate()) {

		document.insertbookmarkform.submit();

	}

}

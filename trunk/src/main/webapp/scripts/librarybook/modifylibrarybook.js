var modifyLibrary_book_checker;

$(document).ready(function(){

	modifyLibrary_book_checker = new FormChecker(document.modifyBookMyLibraryform);
	modifyLibrary_book_checker.checkOnlyNumber('purchasePrice', '숫자만 가능합니다.', true);

});

function modifyLibraryBook() {

	if (modifyLibrary_book_checker.validate()) {

		document.modifyBookMyLibraryform.submit();

	}

}


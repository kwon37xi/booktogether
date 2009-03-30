var insertLibrary_book_checker;

$(document).ready(function(){

	insertLibrary_book_checker = new FormChecker(document.insertBookMyLibraryform);
	insertLibrary_book_checker.checkAtLeastOneChecked('state', '한개이상 선택해야 합니다', true);

});

function insertLibraryBook() {

	if (insertLibrary_book_checker.validate()) {

		document.insertBookMyLibraryform.submit();

	}

}


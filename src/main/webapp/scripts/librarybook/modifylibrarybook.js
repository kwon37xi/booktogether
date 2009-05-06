var modifyLibrary_book_checker;

$(document).ready(function(){

	modifyLibrary_book_checker = new FormChecker(document.modifyBookMyLibraryform);

});

function modifyLibraryBook() {

	if (modifyLibrary_book_checker.validate()) {

		document.modifyBookMyLibraryform.submit();

	}

}


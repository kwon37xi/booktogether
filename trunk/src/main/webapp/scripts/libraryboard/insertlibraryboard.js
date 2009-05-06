var insertLibrary_board_checker;

$(document).ready(function(){

	insertLibrary_board_checker = new FormChecker(document.insertLibraryBoardform);
	insertLibrary_board_checker.checkRequired('content', '내용을 입력하십시요', true);
	insertLibrary_board_checker.checkMinLengthByte('content',10,'최소 10자 이상입니다.', true);
	insertLibrary_board_checker.checkMaxLengthByte('content',100,'최대 100자 입니다.', true);

});

function insertLibraryBoard() {

	if (insertLibrary_board_checker.validate()) {

		document.insertLibraryBoardform.submit();

	}

}


var searchbook_main_checker;

$(document).ready(function(){
	$("#goodWriter").cornerz();
	$("#bestsellers").cornerz();
	$("#recommendBook").cornerz();
	
	searchbook_main_checker = new FormChecker(document.searchBookform);
	searchbook_main_checker.checkRequired('query', '검색어를 입력 해주세요', true);
});


function searchBookQuery() {

	var query = $("#query").val();

	if (searchbook_main_checker.validate()) {
		document.searchBookform.submit();
	}

}
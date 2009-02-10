
function go_page_search(pageno){
	
	document.searchBookform.pageno.value=pageno;
	document.searchBookform.submit();
	
}

function checkBook(isbn){
	location.href='/book/checkBook.do?ISBN='+isbn;
}

function modifyReviewView(){

	document.myreviewform.action="/book/modifyBookReviewView.do";
	document.myreviewform.submit();
	
}

function getReviewView(){
	
	document.myreviewform.action="/book/getReview.do";
	document.myreviewform.submit();
	
}


function insertReviewView(){
	
	document.myreviewform.action="/book/insertReviewView.do";
	document.myreviewform.submit();
	
}
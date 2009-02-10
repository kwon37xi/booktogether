
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

function getReviewView(id){
	document.myreviewform.action="/book/getMyReview.do?id"+id;
	document.myreviewform.submit();
}

function getMyReviewView(){
	
	document.myreviewform.action="/book/getMyReview.do";
	document.myreviewform.submit();
	
}


function insertReviewView(){
	
	document.myreviewform.action="/book/insertBookReviewView.do";
	document.myreviewform.submit();
	
}


function deleteReviewView(){
	
	if(confirm("삭제하시겠습니까?")){
		document.myreviewform.action="/book/deleteBookReview.do";
		document.myreviewform.submit();
	}
}
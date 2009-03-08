
//검색
function go_page_librarybook(pageNo,libraryIdNum, state){
	
	var pararms="pageNo="+pageNo+"&libraryIdNum="+libraryIdNum+"&state="+state;
	
	location.replace("/library/getListLibraryBook.do?"+pararms);
	
}



//검색
function go_page_possessbook(pageNo,userId){
	
	var pararms="pageNo="+pageNo+"&userId="+userId;
	
	location.replace("/library/getListPossessBook.do?"+pararms);
	
}


//검색
function go_page_zonebook(pageNo,userId){
	
	var pararms="pageNo="+pageNo+"&userId="+userId;
	
	location.replace("/library/getListZoneBook.do?"+pararms);
	
}


//검색
function go_page_mybookmark(pageNo,userIdNum){
	
	var pararms="pageNo="+pageNo+"&userIdNum="+userIdNum;
	
	location.replace("/book/getListMyBookMark.do?"+pararms);
	
}


//검색
function go_page_mygrade(pageNo,userIdNum){
	
	var pararms="pageNo="+pageNo+"&userIdNum="+userIdNum;
	
	location.replace("/book/getListMyBookGrade.do?"+pararms);
	
}


//검색
function go_page_go_page_myreview(pageNo,userIdNum){
	
	var pararms="pageNo="+pageNo+"&userIdNum="+userIdNum;
	
	location.replace("/book/getListMyReview.do?"+pararms);
	
}


function getBook(bookIdNum){
	
	location.href="/book/getBook.do?bookIdNum="+bookIdNum;
	
}


function getPossessBook(possessBookIdNum,libraryIdNum,userIdNum){
	
	location.href="/library/getPossessBook.do?userIdNum="+userIdNum+"&libraryIdNum="+libraryIdNum+"&possessBookIdNum="+possessBookIdNum;
	
}

function getLibrary(userId){
	
	location.href="/library/getLibrary.do?userId="+userId;
	
}

function getLibraryBook(userId){
	
	location.href="/library/getLibrary.do?userId="+userId;
	
}



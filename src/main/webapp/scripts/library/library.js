
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





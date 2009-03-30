//검색
function go_page_search(pageno) {

	document.searchBookform.pageNo.value = pageno;
	document.searchBookform.submit();

}

function go_searchBook(pageno, query, searchType) {

	var params = "pageNo=" + pageno + "&query=" + query + "&searchType="
			+ searchType;

	location.href = "/book/searchBook.do?" + params;

}

function checkBook(isbn, pageNo, query, searchType) {
	
	var params = "ISBN=" + isbn + "&pageNo=" + pageNo + "&query=" + query + "&searchType=" + searchType;
	
	location.href = "/book/checkBook.do?" + params;
}


// 책 조회
function go_bookView(bookIdNum) {
	location.href = "/book/getBook.do?bookIdNum=" + bookIdNum;
}

// 내가 작성한 리뷰 수정화면
function modifyReviewView() {

	document.myreviewform.action = "/book/modifyBookReviewView.do";
	document.myreviewform.submit();

}

// 리뷰 조회
function getReviewView(id) {
	document.myreviewform.action = "/book/getMyReview.do?bookReviewIdNum" + id;
	document.myreviewform.submit();
}

// 내가 작성한 리뷰 조회
function getMyReviewView() {

	document.myreviewform.action = "/book/getMyReview.do";
	document.myreviewform.submit();

}

// 리뷰 등록
function insertReviewView() {

	document.myreviewform.action = "/book/insertBookReviewView.do";
	document.myreviewform.submit();

}

// 리뷰 삭제
function deleteReviewView() {

	if (confirm("삭제하시겠습니까?")) {
		document.myreviewform.action = "/book/deleteBookReview.do";
		document.myreviewform.submit();
	}
}

// 추천수 올리기
function recommend(bookIdNum, reviewIdNum) {

	location.href = "/book/modifyRecommend.do?bookIdNum=" + bookIdNum
			+ "&bookReviewIdNum=" + reviewIdNum;

}

// 인용구 삭제
function deleteBookMark(bookMarkIdNum, bookIdNum) {

	if (confirm("삭제하시겠습니까?")) {
		location.href = "/book/deleteBookMark.do?bookMarkIdNum="
				+ bookMarkIdNum + "&bookIdNum=" + bookIdNum;
	}

}

// 인용구 공감 수정
function modifyVibe(bookMarkIdNum, bookIdNum) {

	if (confirm("공감하시겠습니까?")) {
		location.href = "/book/modifyVibe.do?bookMarkIdNum=" + bookMarkIdNum
				+ "&bookIdNum=" + bookIdNum;
	}

}

// 별점 삭제
function deleteBookGrade(bookGradeIdNum, bookIdNum, pageno, query, searchType) {

	if (confirm("삭제하시겠습니까?")) {

		var params = "bookGradeIdNum=" + bookGradeIdNum + "&bookIdNum="
				+ bookIdNum + "&pageno=" + pageno + "&query=" + query
				+ "&searchType=" + searchType;

		location.href = "/book/deleteBookGrade.do?" + params;

	}

}



// 검색
function go_page_bookgrade(pageNo, bookIdNum) {
	
	var pararms = "pageNo=" + pageNo + "&bookIdNum=" + bookIdNum;
	
	location.replace("/book/getListBookGrade.do?" + pararms);
	
}

// 검색
function go_page_bookreview(pageNo, bookIdNum) {
	
	var pararms = "pageNo=" + pageNo + "&bookIdNum=" + bookIdNum;
	
	location.replace("/book/getListBookReview.do?" + pararms);
	
}


// 검색
function go_page_possessbook(pageNo, bookIdNum) {
	
	var pararms = "pageNo=" + pageNo + "&bookIdNum=" + bookIdNum;
	
	location.replace("/possessBook/getListPossessBookIdNum.do?" + pararms);
	
}

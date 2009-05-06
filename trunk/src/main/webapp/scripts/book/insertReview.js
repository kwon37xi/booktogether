var insert_review;

jQuery( function($) {

	insert_review = new FormChecker(document.insertreviewform);
	insert_review.checkRequired('title', '리뷰제목을 입력하십시요', true);
	insert_review.checkRequired('review', '리뷰내용을 입력하십시요', true);
	
	insert_review.checkMinLength('title',5,'최소 5자 이상입니다', true);
	insert_review.checkMinLength('review',10,'최소 10자 이상입니다', true);
	
	insert_review.checkMaxLengthByte('title',50, '최대 50자 입니다', true);

});

function insertBookReview() {

	if (insert_review.validate()) {
		document.insertreviewform.submit();
	}

}

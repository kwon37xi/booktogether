var modify_review;

jQuery( function($) {

	modify_review = new FormChecker(document.modifyreviewform);
	modify_review.checkRequired('title', '리뷰제목을 입력하십시요', true);
	modify_review.checkRequired('review', '리뷰내용을 입력하십시요', true);
	
	modify_review.checkMinLength('title',5,'최소 5자 이상입니다', true);
	modify_review.checkMinLength('review',10,'최소 10자 이상입니다', true);
	
	modify_review.checkMaxLengthByte('title',50, '최대 50자 입니다', true);

});

function modifyBookReview() {

	if (modify_review.validate()) {
		document.modifyreviewform.submit();
	}

}

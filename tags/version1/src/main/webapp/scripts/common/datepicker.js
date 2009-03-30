$(document).ready( function() {
	$('#datepicker_purchaseDate').datepicker( {
		showOn: 'button', buttonImage: '/images/common/calendar.gif', buttonImageOnly: true,
		dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		onSelect : function(dateText) {
			$(".purchaseDateYear").val(dateText.substring(6));
			$(".purchaseDateMonth").val(dateText.substring(0,2));
			$(".purchaseDateDate").val(dateText.substring(3,5));
		}
	});
	
	$('#datepicker_beginRead').datepicker( {
		showOn: 'button', buttonImage: '/images/common/calendar.gif', buttonImageOnly: true,
		dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		onSelect : function(dateText) {
		$(".beginReadYear").val(dateText.substring(6));
		$(".beginReadMonth").val(dateText.substring(0,2));
		$(".beginReadDate").val(dateText.substring(3,5));
	}
	});
	
	
	$('#datepicker_endRead').datepicker( {
		showOn: 'button', buttonImage: '/images/common/calendar.gif', buttonImageOnly: true,
		dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		onSelect : function(dateText) {
		$(".endReadYear").val(dateText.substring(6));
		$(".endReadMonth").val(dateText.substring(0,2));
		$(".endReadDate").val(dateText.substring(3,5));
	}
	});
});
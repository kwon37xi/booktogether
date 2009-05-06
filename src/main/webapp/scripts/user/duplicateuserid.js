jQuery( function($) {

	$("#useId").click( function() {
		window.returnValue = $(this).attr("userId");
		self.close();
		return false;
	});

	$("#userId").click( function() {
		$("#searchResult").fadeOut("fast");
		return false;
	}).keydown( function(e) {

		if (e.keyCode == 13) {
			$("#checkDuplicate").trigger("click");
			return false;
		}

	});

	$("#checkDuplicate").click( function() {

		var userId = $("#userId").val();

		if (jQuery.trim(userId).length == 0) {
			alert("id를 입력해 주세요.");
			return false;
		}
		// Use jQuery Ajax..
			$.ajax( {
				type :"post",
				dataType :"json",
				data : {
					userId :userId
				},
				url :"/user/duplicateUserIdAjax.do",
				success : function(json) {
					// json type으로 처리결과를 받아옴.
				if (json.status == "success") {
					// <b></b> 태그 안에 message를 넣어준다.
				$("b#message").text(json.message);
				// "사용하기" button에 onclick event 넣어준다.
				$("#useId").attr("userId", userId);
				// 숨겨져 있는 div를 보여준다.
				$("#searchResult").fadeIn("fast");
			} else {
				// alert message를 띄우고 div를 숨긴다.
				alert(json.message);
				$("#searchResult").fadeOut("fast");
			}
			return false;
		}
			});
			return false;
		});
});
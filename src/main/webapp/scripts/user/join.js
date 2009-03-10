jQuery( function($) {

	$("#userId").click( function() {
		$("#duplicateId").trigger("click");
		return false;
	});

	$("#duplicateId")
			.click(
					function() {
						var url = "/user/duplicateUserIdView.do?decorator=popup&confirm=true";
						var ret = window
								.showModalDialog(
										url,
										window,
										"dialogWidth:300px; dialogHeight:200px; help:no; scroll:no; resizable;no; status:no");
						$("#userId").val(ret);
						return false;
					});

});

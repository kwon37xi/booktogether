jQuery( function($) {

	$("#findpwbutton")
			.click(
					function() {

						var userId = $("#userId").val();
						var name = $("#name").val();
						var email = $("#email").val();

						var url = "/user/findPw.do?decorator=popup&confirm=true&name="
								+ name
								+ "&email="
								+ email
								+ "&userId="
								+ userId;
						var ret = window
								.showModalDialog(
										url,
										window,
										"dialogWidth:400px; dialogHeight:200px; help:no; scroll:no; resizable;no; status:no");
						$("#userId").val(ret);
						return false;
					});

});
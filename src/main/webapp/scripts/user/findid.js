jQuery( function($) {

	$("#findidbutton")
			.click(
					function() {
						
						var name=$("#findId_name").val();
						var email=$("#findId_email").val();
						
						var url = "/user/findId.do?decorator=popup&confirm=true&name="+name+"&email="+email;
						var ret = window
								.showModalDialog(
										url,
										window,
										"dialogWidth:400px; dialogHeight:200px; help:no; scroll:no; resizable;no; status:no");
						$("#userId").val(ret);
						return false;
					});

});
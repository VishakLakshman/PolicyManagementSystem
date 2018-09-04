$('#employer_type').change(function() {
	var value = document.getElementById("employer_type").value;
	if (value != "self") {
		$('#employer').attr('readonly', false);
		$('#employer_text').show(500);
		$('#employer_label').show(500);
		$('#employer').value = "";
		$('#employer').focus();
	} else {
		$('#employer').value = $('#user_firstname').value;
		$('#employer_text').hide(500);
		$('#employer_label').hide(500);
		$('#hint_question').focus();
		$('#employer').attr('readonly', true);
	}
});
/*function sessionvalidate() {
	var xhttp = new XMLHttpRequest();
	var path = '<%=request.getContextPath()%>';
	console.log(path);
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			var status = parseInt(xhttp.responseText);
			var url = window.location.pathname;
			var filename = url.substring(url.lastIndexOf('/') + 1);
			if (status == 0) {
				if (filename != "index.jsp") {
					window.location.href = "index.jsp";
				}
			}
		}
	};
	xhttp.open("GET","/PolicyManagementSystem/SessionValidate", true);
	xhttp.send();
}*/




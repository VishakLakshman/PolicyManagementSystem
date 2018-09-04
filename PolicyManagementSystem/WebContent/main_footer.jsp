<!-- Bootstrap Core JavaScript -->
<%-- <script src="<%=request.getContextPath()%>/js/bootstrap 3.3.6.js"></script> --%>
<!-- Custom Theme JavaScript -->
<script src="<%=request.getContextPath()%>/js/creative.js"></script>

<!-- Plugin JavaScript -->
<script src="<%=request.getContextPath()%>/js/jquery easing.js"></script>
<script src="<%=request.getContextPath()%>/js/fitText.js"></script>

<script type="text/javascript">
function checkuser() {
	var xhttp = new XMLHttpRequest();
	var url = window.location.pathname;
	var ind=url.slice(1).indexOf('/')+1;
	var path=url.substring(1,ind);
	var filename = url.substring(url.lastIndexOf('/') + 1);
	if (filename != "register.jsp") {
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				var status = parseInt(xhttp.responseText);
				if (status == 1) {
					if (filename != "adminpage.jsp") {
						window.location.href = "<%=request.getContextPath()%>/view/admin/adminpage.jsp";
					}
				} else if (status == 2) {
					if (filename != "userpage.jsp") {
						window.location.href = "<%=request.getContextPath()%>/view/user/userpage.jsp";
					}
				} else if (status == 0)
					if (filename != "index.jsp") {
						window.location.href = "<%=request.getContextPath()%>/index.jsp";
					}
			}
		};
		xhttp.open("POST", "<%=request.getContextPath()%>/SessionValidate", true);
		xhttp.send();
	}
}
</script>
</body>
</html>
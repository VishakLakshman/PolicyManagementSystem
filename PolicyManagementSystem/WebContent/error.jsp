<%@page import="org.apache.log4j.Logger"%>
<jsp:include page="header.jsp"></jsp:include>
<title>Error | Policy Management System</title>
</head>
<body>
	<%
		final Logger LOGGER = Logger.getLogger("error_jsp.class");

		LOGGER.info("Page : " + request.getAttribute("page"));
	%>
	<%
		if (request.getAttribute("page") != null) {
	%>

	<%
		if (request.getAttribute("page").toString()
					.equalsIgnoreCase("user_register")) {
				request.logout();
	%>
	<%
		LOGGER.error("Registration Failed from : "
						+ request.getRemoteAddr());
	%>\
	<div class="display-message">Registration failed. Please Check
		the informations and try again later.</div>
	<%
		} else if (request.getAttribute("page").toString()
					.equalsIgnoreCase("edit_policy")) {
	%>
	<div class="display-message">
		<br> <br>
		<h2>Invalid Credentials provided. Please check again.</h2>
		<br> <br> <a href="adminpage.jsp"><h3>Go Back</h3> </a>
	</div>
	<%
		} else if (request.getAttribute("page").toString()
					.equalsIgnoreCase("user_register_policy")) {
	%>
	<div class="display-message">
		<br> <br>
		<h2>policy registration failed.</h2>
		<br> <br> <a href="userpage.jsp"><h3>Go Back</h3> </a>
	</div>

	<%
		} else if (request.getAttribute("page").toString()
					.equalsIgnoreCase("search_policy")) {
	%>
	<div class="display-message">
		<br> <br>
		<h2>Could not locate the policy.</h2>
		<br> <br> <a href="view/user/userpage.jsp"><h3>Go
				Back</h3> </a>
	</div>
	<%
		} else if (request.getAttribute("page").toString()
					.equalsIgnoreCase("number_change")) {
	%>
	<div class="display-message">
		<br> <br>

		<h2>Unable to change mobile number Please check the credentials
			and try again later.</h2>
		<br> <br> <a href="../user/userpage.jsp">back</a>
	</div>
	<%
		} else if (request.getAttribute("page").toString()
					.equalsIgnoreCase("email_change")) {
	%>
	<div class="display-message">
		<br> <br>

		<h2>Unable to change the email please check the credentials and
			try again later.</h2>
		<br> <br> <a href="../user/userpage.jsp">back</a>
	</div>
	<%
		} else if (request.getAttribute("page").toString()
					.equalsIgnoreCase("password_change")) {
	%>
	<div class="display-message">
		<br> <br>

		<h2>Unable to change the password, please check the credentials
			and try again later.</h2>
		<br> <br> <a href="../user/userpage.jsp">back</a>
	</div>
	<%
		} else if (request.getAttribute("page").toString()
					.equalsIgnoreCase("policy_register")) {
	%>
	<div class="display-message">
		<br> <br>

		<h2>Unable to register the policy, please check the credentials
			and try again later.</h2>
		<br> <br> <a href="adminpage.jsp">back</a>
	</div>
	<%
		}
	%>
	<%
		} else {
	%>
	<div class="display-message">This is sample Error Page</div>
	<%
		}
	%>
</body>
</html>
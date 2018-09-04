<%@page import="org.apache.log4j.Logger"%>
<%@ include file="../../header.jsp"%>
<title>Admin | Policy Management System</title>
</head>
<script type="text/javascript">
	function loadAll() {
		checkuser();
		getThings();
	}
</script>
<%final Logger LOGGER = Logger.getLogger("adminpage_jsp.class"); %>
<body onload="loadAll()">
	<!-- addthis later -->
	<%LOGGER.info("Admin "+session.getAttribute("username")+" Page"); %>
	<nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand page-scroll" href="#page-top"
				onclick="showSection('admin_details','policy_name');"><span
				draggable="true"><img src="../../images/company_icon.png"
					height="30" weidth="40"> </span><%=session.getAttribute("username")%></a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropbtn"><span
						draggable="true"><img src="../../images/settings.png"
							height="15" width="15"> </span> </a>
					<div class="dropdown-content">
						<a class="dropbtn" href="#change_password" id="change_password"
							onclick="showSection('change_password','current_password');">Change
							password</a><a class="dropbtn" href="../../InvalidateSession">Logout</a>
					</div></li>
				<li><a class="page-scroll" href="#register_policy" id="register_policy"
					onclick="showSection('register_policy','policy_name');">Policy
						Register</a></li>
				<li><a class="page-scroll" href="#edit_policy"
					onclick="showSection('edit_policy','policy_name');">Edit Policy</a>
				</li>
			</ul>
			<!-- 	.navbar-collapse -->
		</div>
	</div>
	<!-- /.container-fluid --> </nav>
	<section id="admin_details" style="display: block;">
	<div class="display-message">
		Hi,
		<%=session.getAttribute("username")%>. Welcome to our Super Website
	</div>
	</section>
	<section id="register_policy" style="display: none;"> <jsp:include
		page="policyregister.jsp"></jsp:include> </section>
		<section id="change_password" style="display: none;"> <jsp:include
		page="../settings/changepassword.jsp"></jsp:include> </section>
	<section id="edit_policy" style="display: none;"> <jsp:include
		page="policyedit.jsp"></jsp:include> </section>

	<jsp:include page="../../main_footer.jsp"></jsp:include>
</body>
</html>

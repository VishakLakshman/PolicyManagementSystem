<%@ include file="../../header.jsp"%>
<title>User Page | Policy Management System</title>
</head>
<script type="text/javascript">
	function loadAll() {
		checkuser();
		getPolicyId();
	}
</script>
<body onload="loadAll()">
	<!--  onload="checkuser()"> -->
	<!--  background="images/ss.jpg" onload="checkuser()"> -->
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
				onclick="showSection('user_details','');"><%=session.getAttribute("username")%></a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">

				<li class="dropdown"><a href="#" class="dropbtn"><span
						draggable="true"><img src="../../images/settings.png"
							height="15" width="15"> </span> </a>
					<div class="dropdown-content">
						<a class="dropbtn" href="#change_number"
							onclick="showSection('change_number','current_password');">Change
							Number</a> <a class="dropbtn" href="#change_password"
							onclick="showSection('change_password','current_password');">Change
							password</a> <a class="dropbtn" href="#change_email"
							onclick="showSection('change_email','current_password');">Change
							Email</a> <a class="dropbtn" href="../../InvalidateSession">Logout</a>
					</div>
				</li>
				<li><a class="page-scroll" href="#policy_search"
					onclick="showSection('policy_search','policy_name');">Search
						Policy</a>
				</li>
				<li><a class="page-scroll" href="#policy_payment"
					onclick="showSection('policy_payment','policy_name');">Policy
						Payment</a>
				</li>

				<!-- 					<li>
					</li> -->
			</ul>
			<!-- 	/.navbar-collapse -->
		</div>
	</div>
	<!-- /.container-fluid --> </nav>
	<!-- 	<section name="dummy" id="">&nbsp;</section> -->
	<section id="change_number" style="display: none;"> <jsp:include
		page="../settings/changenumber.jsp"></jsp:include> </section>
	<section id="change_password" style="display: none;"> <jsp:include
		page="../settings/changepassword.jsp"></jsp:include> </section>
	<section id="change_email" style="display: none;"> <jsp:include
		page="../settings/changeemail.jsp"></jsp:include> </section>
	<section id="user_details" style="display: block;">
	<div class="display-message" id="page-top">
		Hi,
		<%=session.getAttribute("username")%>. Welcome to our Super Website
	</div>
	</section>
	<section id="policy_search" style="display: none;"> <jsp:include
		page="policysearch.jsp"></jsp:include> </section>
	<section id="policy_payment" style="display: none;"> <jsp:include
		page="payment.jsp"></jsp:include> </section>
	<jsp:include page="../../main_footer.jsp"></jsp:include>
</body>
</html>
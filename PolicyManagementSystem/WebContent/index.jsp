<%@page import="org.apache.log4j.Logger"%>
<%@ include file="header.jsp"%>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<%
	final Logger LOGGER = Logger.getLogger("index_jsp.class");
%>
<script type="text/javascript">
	function loadAll() {
		checkuser();
		setTitle('Home Page');
	}
</script>
<%
	LOGGER.info("New Session : " + request.getSession().getId()
			+ " from device : " + request.getRemoteAddr());
%>
<body id="page-top" onload="loadAll()">

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

			<a class="navbar-brand page-scroll" href="#page-top"><span
				draggable="true"><img src="images/company_icon.png"
					height="30" weidth="40"> </span> Team4 Insurance</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li><a class="page-scroll" href="#about">About us</a>
				</li>
				<li><a class="page-scroll" href="#userus">Contact us</a>
				</li>
				<!-- <li><a class="page-scroll" href="#admin">Admin</a></li> -->
				<li><a class="page-scroll" href="#login" onclick="getfocus()">Login</a>
				</li>
			</ul>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</div>
	</nav>

	<section id="about">
	<div class="container">
		<div class="padding-2 row form-set text-center col-width-6">
			<!-- 				<div class="col-lg-8 col-lg-offset-2 text-center"> -->
			<h2 class="section-heading">
				<font color="white">Policy Management System</font>
			</h2>
			<!-- <hr class="light"> -->
			<br>
			<p class="text-faded">
				<font color="white">Policy registration is intended to be a
					vehicle for the exploration and discussion of policy issues and is
					aimed in particular at enhancing communication between health
					policy researchers, legislators, decision-makers, and professionals
					concerned with developing, implementing, and analyzing health
					policy. Policy Management System (PMS) is basically a system that
					helps to maintain the Policies and Payments. It also allows to
					search a Policy. Policy renewals and policy cancellation can be
					managed by the insurance policy management system.</font>
			</p>
		</div>
	</div>
	</section>

	<section id="userus">
	<div class="container">
		<div class="row">
			<div class="col-lg-6 text-center">
				<br>
				<div align="left">
					<br> <br> <font color="white">
						<h2>Contact Us</h2> <br>
						<h4>
							<a href="mailto:reachus@policyinsurance.in">reachus@policyinsurance.in</a>
						</h4>
						<h4>
							CONTACT NUMBER : <a href="tel:+91 1800 268 9876">1800 268
								9876</a>
						</h4>
						<h4>
							SMS:POLICY to <a href="sms:596789">56789</a>
						</h4>
						<p>Meet our Advisor (Mon-Sat 9 a.m to 6 p.m)</p> </font>
				</div>
			</div>
			<div class="col-lg-6 text-center">
				<div align="right">
					<br> <br> <br>
					<iframe text align="right"
						src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d15661.726661166109!2d76.9888473!3d11.081171!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xe4c632007ced9e42!2sKCT+Tech+Park!5e0!3m2!1sen!2sin!4v1457611928524"
						width="400" height="250" frameborder="2" style="border: 0"
						allowfullscreen></iframe>
				</div>
			</div>
		</div>
	</div>

	<div align="right"></div>


	</section>
	<section id="login" onfocus="getfocus()">
	<div class="container" style="float: center;">
		<!-- <div class="row">
				<div class="col-lg-8 col-lg-offset-2 text-center">
					<div class="col-lg-12 text-center"> -->
		<!-- <hr class="light"> -->
		<br>
		<jsp:include page="login.jsp"></jsp:include>

		<!-- <a href="#userus"class="page-scroll btn btn-default btn-xl">Contact us</a> -->
		<!-- </div>
				</div>
			</div> -->
	</div>
	</section>
	<jsp:include page="footer.jsp"></jsp:include>
	<jsp:include page="main_footer.jsp"></jsp:include>
	<script type="text/javascript">
		function getfocus() {
			$('#UserName').focus();
		}
	</script>
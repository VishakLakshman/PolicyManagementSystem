<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.List"%>
<%@page import="com.cognizant.to.PaymentTO"%>
<%@page import="com.cognizant.to.PolicyTO"%>
<%@page import="com.cognizant.to.UserTO"%>
<jsp:include page="header.jsp"></jsp:include>
<title>Success | Policy Management System</title>
</head>
<%
	final Logger LOGGER = Logger.getLogger("success_jsp.class");
	LOGGER.info("Page : " + request.getAttribute("page"));
%>
<body>
	<%
		if (request.getAttribute("page") != null) {
	%>
	<%
		if (request.getAttribute("page").toString()
		.equalsIgnoreCase("user_register")) {
	%>
	<div class="display-message">
		<%
			LOGGER.info("User has been Registered Successfully");
		%>
		<div align="center" style="display: block; height: 20%; width: 80%; line-height:90px;">
		<table align="center">
			<th>Dear User,</th>
			<tr>
				<td>User Id</td>
				<td>: <%=((UserTO) request.getAttribute("bean"))
							.getUserName()%></td>
			</tr>
			<tr>
				<td>Password</td>
				<td>: <%=((UserTO) request.getAttribute("bean"))
							.getPassword()%></td>
			</tr>
		</table>
		<h3>
			Click <a href="view/user/userpage.jsp"> here </a> to login. Or you
			can go to <a href="index.jsp"> home </a>
		</h3>
	</div>
	<%
		} else if (request.getAttribute("page").toString()
			.equalsIgnoreCase("policy_register")) {
		PolicyTO pb = (PolicyTO) request.getAttribute("pbean");
	%>
	<div class="display-message">
		The policy is successfully register<br> The policy
		<%=pb.getPolicyNo()%>
		is available to the users from
		<%=pb.getStartDate()%>to
		<%=pb.getEndDate()%>.This is of policy type
		<%=pb.getPolicyType()%>. .</font>
		<h3>
			<font color="#8A240F"> To add more Click </font><a
				href="adminpage.jsp"><font color="white">Home.</font> </a>
		</h3>
		<%
			LOGGER.info("Policy : " + pb.getPolicyName()
							+ " is registered Successfully by "
							+ session.getAttribute("username"));
		%>
	</div>
	<%
		} else if (request.getAttribute("page").toString()
			.equalsIgnoreCase("edit_policy")) {
	%>
	<div class="display-message">
		<br> <br>
		<h2>You have successfully altered the policy details..</h2>
		<br> <br> <a href="adminpage.jsp"> Back </a>.
		<%
 	LOGGER.info("Policy : " + request.getAttribute("msg")
   					+ " is altered by "
   					+ session.getAttribute("username"));
 %>
	</div>
	<%
		} else if (request.getAttribute("page").toString()
			.equalsIgnoreCase("password_change")) {
	%>
	<div class="display-message">
		<br> <br>

		<h2>You have successfully changed the password.</h2>
		<br> <br>
		<%
			if (session.getAttribute("type").toString()
							.equalsIgnoreCase("admin")) {
		%>
		<a href="../admin/adminpage.jsp">back</a>
		<%
			} else if (session.getAttribute("type").toString()
							.equalsIgnoreCase("user")) {
		%>
		<a href="../user/userpage.jsp">back</a>
		<%
			}
		%>
	</div>

	<%
		} else if (request.getAttribute("page").toString()
			.equalsIgnoreCase("user_register_policy")) {
	%>
	<div class="display-message">
		<br> <br>
		<h2>You have been successfully registered for the policy</h2>
		<br> <br> <a href="userpage.jsp"> Back </a>.
		<%
 	LOGGER.info("Policy : " + request.getAttribute("msg")
   					+ " is altered by "
   					+ session.getAttribute("username"));
 %>
	</div>
	<%
		} else if (request.getAttribute("page").toString()
			.equalsIgnoreCase("policy_payment")) {
	%>
	<!-- 	<div class="display-message"> -->
	<%
		PaymentTO pb = ((PaymentTO) request
				.getAttribute("paymentbean"));
	%>
	<div class="invoiceform">
		<!-- <input type="button" value="Print" name="print" onClick='window.print()'>  -->
		<button value="something" onclick="window.print();">Print</button>
		<header>
		<h1>Invoice</h1>
		<link rel="stylesheet" href="../../css/style.css">
		<script src="../../js/script.js"></script>
		<address>
			<p>
				<img alt="" src="../../images/cognizant-logo.png">
			</p>
			<p>Cognizant Academy</p>
			<p>
				KCT Tech Park,2nd Floor<br>Saravanampatti,Coimbatore
			</p>
			<p>1800-200-2345</p>
		</address>

		<article> <!-- <h1>Recipient</h1> --> <!-- <address >
			<p>
				Some Company<br>c/o Some Guy
			</p>
		</address> -->
		<table class="meta">
			<tr>
				<th><span>Invoice #</span>
				</th>
				<td><span><%=pb.getBillid()%></span>
				</td>
			</tr>
			<tr>
				<th><span>Date</span>
				</th>
				<td><span><%=pb.getBillDate()%></span>
				</td>
			</tr>
			<tr>
				<th><span>Amount Due</span>
				</th>
				<td><span id="prefix"></span><span><%=pb.getPaymentAmount()%></span>
				</td>
			</tr>
		</table>
		<table class="inventory">
			<thead>
				<tr>
					<th><span>Policy No#</span>
					</th>
					<th><span>Invoice#</span>
					</th>
					<th><span>Next Due Date</span>
					</th>
					<th><span>Remaining Balance Amount</span>
					</th>
					<!-- <th><span>Bill Id</span>
					</th> -->
					<th><span>Total Amount</span>
					</th>


				</tr>
			</thead>
			<tbody>
				<tr>
					<td><a class="cut"></a><span><%=pb.getPolicyId()%></span></td>
					<td><span><%=pb.getBillid()%></span>
					</td>
					<td><span><%=pb.getDueDate()%></span>
					</td>
					<td><span data-prefix></span><span><%=pb.getBalanceamount()%></span>
					</td>
					<%-- <td><span><%=pb.getBillid()%></span>
					</td> --%>
					<td><span data-prefix></span><span><%=pb.getPaymentAmount()%></span>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- <a class="add"></a> -->
		<table class="balance">
			<tr>
				<th><span>Total</span>
				</th>
				<td><span data-prefix></span><span><%=pb.getPaymentAmount()%></span>
				</td>
			</tr>
			<tr>
				<th><span>Amount Paid</span>
				</th>
				<td><span data-prefix></span><span><%=pb.getPaymentAmount()%></span>
				</td>
			</tr>
			<tr>
				<th><span>Balance Due</span>
				</th>
				<td><span data-prefix></span><span><%=pb.getBalanceamount()%></span>
				</td>
			</tr>
		</table>
		</article> <aside>
		<h1>
			<span>Thank You</span>
		</h1>
		<div>
			<h3>
				To make payment to other policies, click the link <a
					href="userpage.jsp">Policy Payment</a>.<br>
			</h3>
		</div>
		</aside> <footer> Invoice was created on a computer and is valid
		without the signature and seal. </footer>
	</div>
	<%
		LOGGER.info("User : " + session.getAttribute("username")
					+ " paid " + pb.getPaymentAmount() + " for policy "
					+ pb.getPolicyId());
	%>
	<%
		} else if (request.getAttribute("page").toString()
				.equalsIgnoreCase("search_policy")) {
	%>

	<div class="col-width-8 left-margin-1 right-margin-1 display-table">
		The Policy that you have searching for.... <br> <br> </font> <font
			color="#fcf0e7">
			<table border="" id="" class="" cellspacing="0" width="100%"
				align="center">
				<%
					List<PolicyTO> lp = (List<PolicyTO>) request
											.getAttribute("list");
									/* out.print("<table border=1>"); */
									for (PolicyTO p : lp) {
										String maturity = new Double(p.getMaturityAmount())
												.toString();
										out.println("<tr><td>Policy id : "
												+ p.getPolicyNo()
												+ "</td><td rowspan='2'>Description : <br>This policy duration is "
												+ p.getDurationInYears()
												+ " and for each year "
												+ p.getTermPerYear()
												+ " terms you have to pay the amount Rs. "
												+ p.getTermAmount()
												+ " with interest of "
												+ p.getInterest()
												+ "%. The maturity amount will be provided after period of policy will be Rs. "
												+ maturity.substring(0,
														maturity.indexOf('.') + 2)
												+ "<a href='RegisterToUser?policynum="
												+ p.getPolicyNo()
												+ "'> register</a></td></tr><tr><td> PolicyName : "
												+ p.getPolicyName() + "</td></tr> ");
									}
									/* out.print("</table>"); */
				%>
			</table> <!-- <font color="white">Click</font> <a href="userpage.jsp"><font
			color="#76d9d6"> here</font> </a> <font color="white">to go back.
			</font> -->
	</div>
	<%
		} else if (request.getAttribute("page").toString()
					.equalsIgnoreCase("number_change")) {
	%>
	<div class="display-message">
		<br> <br>

		<h2>You have successfully changed the mobile number.</h2>
		<br> <br> <a href="../user/userpage.jsp">back</a>
	</div>
	<%
		} else if (request.getAttribute("page").toString()
					.equalsIgnoreCase("email_change")) {
	%>
	<div class="display-message">
		<br> <br>

		<h2>You have successfully changed the email.</h2>
		<br> <br> <a href="../user/userpage.jsp">back</a>
	</div>
	<%
		}
	%>
	<%
		}
	%>
	<%-- <%}else{ %> --%>
	<jsp:include page="main_footer.jsp"></jsp:include>
</body>
</html>
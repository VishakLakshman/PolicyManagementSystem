<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@ include file="../../header.jsp"%>

<head>
<title>Policy Payment</title>
<script type="text/javascript">
	function getPolicyId() {
		var xhttp = new XMLHttpRequest();
		var obj;
		var type = "id";
		var i = 0;
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				obj = JSON.parse(xhttp.responseText);
				document.getElementById("bill_date").value = "";
				document.getElementById("due_date").value = "";
				document.getElementById("fine").value = "";
				document.getElementById("paymentAmount").value = "";
				for (var i = 0; i < obj.col.length; i++) {
					var ins = obj.col[i].pno;
					console.log(obj.col[i].pno);
					document.getElementById("policy_id").innerHTML += "<option value='" + ins + "'>"
							+ ins + "</option>";
				}
			}
		};

		xhttp.open("POST", "GetPayment?type=" + type, true);
		xhttp.send();
	}
</script>
<script type="text/javascript">
	function getOthers() {
		var xhttp = new XMLHttpRequest();
		var obj;
		var type="others"
		var policyno = document.getElementById("policy_id").value.toString();
		var i = 0;
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				obj = JSON.parse(xhttp.responseText);
				document.getElementById("bill_date").value = obj.col[0].sdate;
				document.getElementById("due_date").value = obj.col[1].duedate;
				document.getElementById("fine").value = obj.col[2].fine;
				document.getElementById("paymentAmount").value = obj.col[3].pay;
			}
		};
		xhttp.open("POST", "GetPayment?policyno=" + policyno+"&type="+type, true);
		xhttp.send();
	}
</script>

</head>
<body><!--  onload="getPolicyId()"> -->

	<div class="container">
		<!-- style="background-image: url('images/ss.jpg');"> -->
		<div class="row">
			<div class="col-lg-9 left-margin-1 right-margin-1 text-center"
				align="center">
				<div class="left-margin-1 text-center form-set right-margin-1">
					<div>
						<h2>Policy Payment</h2>
						<br>
					</div>
					<div style="display: block; height: 100%; width: 80%; float: center;">
					<form role="form" id="defaultForm" class="voffset5 form-horizontal"
						style="width: 100%;" action="PaymentServlet" method="post">
							<div class="col-md-4 voffset1" align="left">
								<label>Policy Id</label>
							</div>
							<div class="form-group col-md-8">
								<select name="PolicyNo" id="policy_id" class="form-control"
									onchange="getOthers()">
									<option label="--select--"></option>
								</select>
							</div>
							<div class="col-md-4 voffset1" align="left">
								<label>Bill Date</label>
							</div>
							<%
									Calendar cal = Calendar.getInstance();
									SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
									String max = sdf.format(cal.getTime());
									String min = sdf.format(cal.getTime());
									/* cal.add(Calendar.MONTH,-5);
									String min=sdf.format(cal.getTime()); */
								%>

							<div class="form-group col-md-8">
								<input type="text" id="bill_date" name="bill_date"
									class="form-control" placeholder="dd-mm-yyyy" min="<%=min%>"
									max="<%=max%>" autocomplete="off" readonly>
							</div>
							<div class="col-md-4 voffset1" align="left">
								<label>Due Date</label>
							</div>
							<%
									Calendar cal1 = Calendar.getInstance();
									SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
									String max1 = sdf1.format(cal.getTime());
									/* String min1=sdf1.format(cal.getTime()); */
								%>
							<div class="form-group col-md-8">
								<input type="text" id="due_date" name="due_date"
									class="form-control" placeholder="dd-mm-yyyy" min="01-01-1990"
									max="<%=max%>" autocomplete="off" readonly>

							</div>


							<div class="col-md-4 voffset1" align="left">
								<label>Fine</label>
							</div>
							<div class="form-group col-md-8">
								<label></label> <input type="text" id="fine" name="fine"
									class="form-control" placeholder="Fine amount"
									autocomplete="off" readonly>
							</div>
							<div class="col-md-4 voffset1" align="left">
								<label>Payment Amount</label>
							</div>
							<div class="form-group col-md-8">
								<input type="text" id="paymentAmount" name="paymentAmount"
									min="100" max="100000" class="form-control"
									placeholder="Payment Amount" autocomplete="off" readonly>

							</div>


							<div class="container-fluid voffset4">
								<div class="col-md-6 top-margin-1 bottom-margin-1">
									<input type="submit" value="Payment" id="register_policy"
										class="btn btn-warning btn-block">
								</div>

								<div class="col-md-6 top-margin-1 bottom-margin-1">
									<input type="reset" value="Reset"
										class="btn btn-success btn-block">
								</div>
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 	<script type="text/javascript">
		$(document)
				.ready(
						function() {

							$('#defaultForm')
									.formValidation(
											{

												message : 'This value is not valid',
												fields : {

													policy_id : {
														validators : {
															notEmpty : {
																message : 'The policy id is required'
															}
														}
													},
													bill_date : {
														validators : {
															notEmpty : {
																message : 'The bill date is required'
															}
														}
													},
													paymentAmount : {

														validators : {
															notEmpty : {
																message : 'Amount is required'
															}
														}
													},

													fine : {
														validators : {
															notEmpty : {
																message : 'Fine Amount required'
															}
														}

													},

													due_date : {
														validators : {
															notEmpty : {
																message : 'The Due Date  is required'
															}
														}
													}
												}
											});
					

		var dateControler = {
			currentDate : null
		}
 -->
	<!--  		$(document).on("change", "#txtDate", function(event, ui) {
			var now = new Date();
			var selectedDate = new Date($(this).val());

			if (selectedDate > now) {
				$(this).val(dateControler.currentDate)
			} else {
				dateControler.currentDate = $(this).val();
			}
		});
						}); -->
	</script>
</body>

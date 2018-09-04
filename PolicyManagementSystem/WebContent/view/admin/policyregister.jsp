<%@page import="java.text.SimpleDateFormat"%>
<%@ include file="../../header.jsp"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<!-- <title>Policy Register | Policy Management System</title> -->
<!-- <body onload="FocusOnInput(0)"> -->
<!-- <body> -->
<!-- background="images/ss.jpg" onload="sessionvalidate()"> -->
<div class="container">
	<!-- style="background-image: url('images/ss.jpg');"> -->
	<!-- <font style="color: white"> -->
	<div class="row">
		<div class="col-lg-9 left-margin-1 right-margin-1 text-center"
			align="center">
			<div class="left-margin-1 text-center form-set right-margin-1">
				<div>
					<h2>Policy Register</h2>
					<br>
				</div>
				<div
					style="display: block; height: 100%; width: 80%; float: center;">
					<form role="form" id="defaultForm" class="voffset5 form-horizontal"
						style="width: 100%;" action="PolicyRegister" method="post">
						<div class="col-md-4 voffset1" align="left">
							<label>Policy Name<font color="red">*</font> </label>
						</div>
						<div class="form-group col-md-8">
							<input type="text" onchange="loadXMLDoc()" name="policy_name"
								id="policy_name" class="form-control" rows="5"
								placeholder="money back" autofocus autocomplete="off">
						</div>
						<font color="red"><p id="policy_exists"></p> </font>

						<div class="col-md-4 voffset1" align="left">
							<label>Start Date<font color="red">*</font> </label>
						</div>
						<%
							Calendar cal = Calendar.getInstance();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							String min = sdf.format(cal.getTime());
						%>

						<div class="form-group col-md-8">
							<input type="date" id="policy_start_date"
								name="policy_start_date" class="form-control"
								placeholder="yyyy-MM-dd" min="<%=min%>" autocomplete="off">
						</div>

						<div class="col-md-4 voffset1" align="left">
							<label>Duration in years<font color="red">*</font> </label>
						</div>
						<div class="form-group col-md-8">
							<input type="number" id="policy_duration" name="policy_duration"
								min="1" max="25" class="form-control" placeholder="10"
								autocomplete="off">

						</div>

						<div class="col-md-4 voffset4" align="left">
							<label>Company<font color="red">*</font> </label>
						</div>
						<div class="form-group col-md-8">
							<label></label> <input type="text" id="company" name="company"
								class="form-control" placeholder="abcxyz" autocomplete="off">
						</div>

						<div class="col-md-4 voffset1" align="left">
							<label>Initial deposit<font color="red">*</font> </label>
						</div>
						<div class="form-group col-md-8">
							<input type="text" id="policy_initial_deposit"
								name="policy_initial_deposit" min="100" max="100000"
								class="form-control" placeholder="1000" autocomplete="off">
						</div>
						<div class="col-md-4" align="left">
							<label>Policy Type<font color="red">*</font> </label>
						</div>
						<div class="form-group col-md-8">
							<select name="policy_type" id="policy_type" class="form-control ">
								<option label="--Select--"></option>
								<option value="VI">Vehicle Insurance</option>
								<option value="TI">Travel Insurance</option>
								<option value="LI">Life Insurance</option>
								<option value="HI">Health Insurance</option>
								<option value="CP">Child Plans</option>
								<option value="RP">Retirement Plans</option>
							</select>
						</div>
						<div class="col-md-4" align="left">
							<label>User Type<font color="red">*</font> </label>
						</div>
						<div class="form-group col-md-8">
							<select name="user_type" id="user_type" class="form-control ">
								<option label="--Select--"></option>
								<option value="A">A</option>
								<option value="B">B</option>
								<option value="C">C</option>
								<option value="D">D</option>
								<option value="E">E</option>

							</select>
						</div>

						<div class="col-md-4 voffset4" align="left">
							<label>Terms per year<font color="red">*</font> </label>
						</div>
						<div class="form-group col-md-8">
							<label></label> <input type="text" id="policy_terms"
								name="policy_terms" min="1" max="12" class="form-control"
								placeholder="6" autocomplete="off">
						</div>

						<div class="col-md-4 voffset4" align="left">
							<label>Term amount<font color="red">*</font> </label>
						</div>
						<div class="form-group col-md-8">
							<label></label> <input type="text" id="policy_term_amount"

								name="policy_term_amount" class="form-control" min="1000" max="50000"
								placeholder="2000" autocomplete="off">

						</div>

						<div class="col-md-4 voffset4" align="left">
							<label>Interest<font color="red">*</font> </label>
						</div>
						<div class="form-group col-md-8">
							<label></label> <input type="text" id="policy_interest"
								name="policy_interest" min="0.001" max="100"
								class="form-control" placeholder="0.8" autocomplete="off">
						</div>
						<!-- 		</font> -->
						<div class="container-fluid voffset4">
							<div class="col-md-6 top-margin-1 bottom-margin-1">
								<input type="submit" value="Register" id="register_policy"
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
<script type="text/javascript">
		$(document)
				.ready(
						function() {

							$('#defaultForm')
									.formValidation(
											{

												message : 'This value is not valid',
												fields : {

													policy_name : {
														validators : {
															notEmpty : {
																message : 'The policy name is required'
															},
															regexp: {
										                        regexp: /^([a-zA-Z ]){12,20}$/,
										                        message: 'Policy Name should be between 12 - 20 alphabets'
										                    }
														}
													},
													policy_start_date : {
														validators : {
															notEmpty : {
																message : 'The start date is required'
															},
															date : {
																format : 'yyyy-MM-dd',
																min : '<%=min%>',
															message : 'The date is not a valid'
														}
													}
												},
												policy_duration : {

													validators : {
														notEmpty : {
															message : 'The duration is required'
														}
													}
												},

												company : {
													validators : {
														notEmpty : {
															message : 'Company name required'
														},
														stringLength : {
															min : 2,
															max : 70,
															message : 'Invalid Company Name'
														}
													}

												},

												policy_initial_deposit : {
													validators : {
														notEmpty : {
															message : 'The initial deposit is required'
														}
													}
												},

												policy_type : {
													validators : {
														notEmpty : {
															message : 'The policy type is required'
														},
														user_policy_type : {
															message : 'The input is not a valid policy type'
														}
													}
												},

												user_type : {
													validators : {
														notEmpty : {
															message : 'user type is required'
														}
													}
												},

												policy_terms : {
													validators : {
														notEmpty : {
															message : 'Terms required'
														}
													}
												},

												policy_term_amount : {
													validators : {
														notEmpty : {
															message : 'Term amount is required'
														}
													}
												},

												policy_interest : {
													validators : {
														notEmpty : {
															message : 'Interest is required'
														}
													}

												}
											}
										});
					});

	var dateControler = {
		currentDate : null
	}

	$(document).on("change", "#txtDate", function(event, ui) {
		var now = new Date();
		var selectedDate = new Date($(this).val());

		if (selectedDate > now) {
			$(this).val(dateControler.currentDate)
		} else {
			dateControler.currentDate = $(this).val();
		}
	});
	function loadXMLDoc() {
		var policy_name = document.getElementById("policy_name").value;
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4) {
				var msg = xhttp.responseText;
				if (msg != "no") {
					document.getElementsByTagName("small")[0].style = "display:block; color:#d9534f;";
					document.getElementsByTagName("small")[0].innerHTML = msg;
					document.getElementById("policy_name").value = "";
					document.getElementById("policy_name").focus = true;
				} else {
					document.getElementsByTagName("small")[0].style = "display:none;";
				}
			}

		};
		xhttp.open("GET", "CheckPolicy?policy_name=" + policy_name + "", true);
		xhttp.send();
	}
</script>
<!-- </body> -->

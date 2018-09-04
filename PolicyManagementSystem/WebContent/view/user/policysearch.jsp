<%@ include file="../../header.jsp"%>
<script type="text/javascript">
	function getYear() {
		var xhttp;
		var ele = document.getElementById("policytype").value.toString();
		var obj;
		var type = "year";
		xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				obj = JSON.parse(xhttp.responseText);
				/* 				document.getElementById("policyno").innerHTML = "";
				 document.getElementById("policyname").innerHTML = "";
				 document.getElementById("company").innerHTML = "";*/
				document.getElementById("duration").innerHTML = "";
				for ( var i = 0; i < obj.col.length; i++) {
					var ins = obj.col[i].duration;
					document.getElementById("duration").innerHTML += "<option value='" + ins + "'>"
							+ ins + "</option>";
				}
			}
		};
		xhttp.open("POST", "GetPolicy?policytype=" + ele
				+ "&getype=" + type, true);
		;
		xhttp.send();
	}

	function getCompany() {
		var xhttp;
		var ele = document.getElementById("policytype").value.toString();
		var ele1 = document.getElementById("duration").value.toString();
		var obj;
		var type = "company";
		xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				obj = JSON.parse(xhttp.responseText);
				document.getElementById("company").innerHTML = "";
				for ( var i = 0; i < obj.col.length; i++) {
					var ins = obj.col[i].company;
					document.getElementById("company").innerHTML += "<option value='" + ins + "'>"
							+ ins + "</option>";
				}
			}
		};
		xhttp.open("POST", "GetPolicy?policytype=" + ele
				+ "&duration=" + ele1 + "&getype=" + type, true);
		;
		xhttp.send();
	}

	function getPolicy() {
		var xhttp;
		var ele = document.getElementById("policytype").value.toString();
		var ele1 = document.getElementById("duration").value.toString();
		var ele2 = document.getElementById("company").value.toString();
		var obj;
		var type = "policy";
		console.log(ele1);
		xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				obj = JSON.parse(xhttp.responseText);
				document.getElementById("policyno").value = obj.col[0].policyno;
				document.getElementById("policyname").value = obj.col[1].policyname;
			}
		};
		xhttp.open("POST", "GetPolicy?policytype=" + ele
				+ "&duration=" + ele1 + "&company=" + ele2 + "&getype=" + type,
				true);
		;
		xhttp.send();
	}
</script>
</head>
<!-- <body background="images/ss.jpg"> -->
<div class="container">
	<!-- -fluid bottom-margin"
		style="background-image: url('images/ss.jpg');">
		<font style="color: white"> -->
	<!-- <div class="row col-md-8 col-md-offset-2 voffset2"> -->
	<div class="row">
		<div class="col-lg-9 left-margin-1 right-margin-1 text-center"
			align="center">
			<div class="left-margin-1 text-center form-set right-margin-1">
				<div>
					<h2>Policy Search</h2>
					<br>
				</div>
				<div style="display: block; height: 100%; width: 80%; float: center;">
					<form role="form" id="defaultForm" class="voffset5 form-horizontal"
						style="width: 100%;" action="PolicySearch"
						method="post">
						<div class="col-md-4" align="left">
							<label>Policy Type<font color="red">*</font> </label>
						</div>
						<div class="form-group col-md-8">
							<select name="policytype" id="policytype" class="form-control "
								onchange="getYear()">
								<option label="--Select--"></option>
								<option value="VI">Vehicle Insurance</option>
								<option value="TI">Travel Insurance</option>
								<option value="LI">Life Insurance</option>
								<option value="HI">Health Insurance</option>
								<option value="CP">Child Plans</option>
								<option value="RP">Retirement Plans</option>
							</select>
						</div>
						<div class="col-md-4 voffset1" align="left">
							<label>Number of Years<font color="red">*</font> </label>
						</div>
						<div class="form-group col-md-8">
							<select name="Duration" id="duration" class="form-control"
								onblur="getCompany()">
								<option label="--select--"></option>

							</select>
						</div>
						<div class="col-md-4 voffset1" align="left">
							<label>Company<font color="red">*</font> </label>
						</div>
						<div class="form-group col-md-8">
							<select name="Company" id="company" class="form-control "
								onclick="getPolicy()">
								<option label="--select--"></option>

							</select>
						</div>
<!-- 						<div class="col-md-4 voffset1" align="left">
							<label>PolicyName<font color="red">*</font> </label>
						</div>
						<div class="form-group col-md-8">
							<input type="text" name="PolicyName" id="policyname"
								class="form-control" rows="5" placeholder="abcxxxx" autofocus
								autocomplete="off" readonly>
						</div>

						<div class="col-md-4 voffset1" align="left">
							<label>PolicyId<font color="red">*</font> </label>
						</div>
						<div class="form-group col-md-8">
							<input type="text" name="PolicyNo" id="policyno"
								class="form-control" rows="5" placeholder="LI-xxxx" autofocus
								autocomplete="off" readonly>
						</div> -->
						<div class="container-fluid voffset4">
							<div class="col-md-6 top-margin-1 bottom-margin-1">
								<input type="submit" value="search" id="search"
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
											button : {
												selector : '[type="submit"]',
												disabled : ''
											},

											message : 'This value is not valid',
											fields : {
												policy_type : {
													validators : {
														notEmpty : {
															message : 'The first name field must not be empty'
														}
													}
												},
												duration : {
													validators : {
														notEmpty : {
															message : 'The last name field must not be empty'
														}
													}
												},
												company : {

													validators : {
														notEmpty : {
															message : 'The date field must not be empty'
														}
													}
												},

												policyname : {
													validators : {
														notEmpty : {
															message : 'Fill your address'
														}
													}
												},

												policyno : {
													validators : {
														notEmpty : {
															message : 'The mobile number field must not be empty'
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
	function checkLogin() {
		var xhttp;
		var un = document.getElementById("uname").value;
		var up = document.getElementById("upass").value;
		var url = window.location.pathname;
		var ind = url.slice(1).indexOf('/') + 1;
		var path = url.substring(1, ind);
		xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				document.getElementById("myform").style.display = "none";
				document.getElementById("resp").innerHTML = xhttp.responseText;
			}
		};
		xhttp.open("POST", "../../LoginServlet?user=" + un + "&userpass=" + up,
				true);
		xhttp.send();
		return false;
	}
</script>
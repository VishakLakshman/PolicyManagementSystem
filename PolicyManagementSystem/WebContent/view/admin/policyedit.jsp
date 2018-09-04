<head>
<script type="text/javascript">
	/* 	function setThings() {
	 sessionvalidate();
	 getThings();
	 } */
	function getThings() {
		var xhttp = new XMLHttpRequest();
		var obj;

		var i = 0;
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4) {
				obj = JSON.parse(xhttp.responseText);
				for ( var i = 0; i < obj.col.length; i++) {
					var ins = obj.col[i].pname;
					document.getElementById("edit_policy_name").innerHTML += "<option value='" + ins + "'>"
							+ ins + "</option>";
				}
			}
		};
		xhttp.open("GET", "GetPolicyName", true);
		xhttp.send();
	}
</script>
<script type="text/javascript">
	function getData() {
		var xhttp;
		var ele = document.getElementById("edit_policy_name").value.toString();
		var obj;
		var val = ele;
		xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				obj = JSON.parse(xhttp.responseText);
				console.log(obj);
				document.getElementById("edit_duration").value = obj.data[0].policyduration;
				document.getElementById("edit_term_amount").value = obj.data[1].policytermamount;
				document.getElementById("edit_policy_type").value = obj.data[2].policytype;

			}
		};
		xhttp.open("POST", "GetPolicyName?policy=" + ele, true);
		xhttp.send();
	}
</script>

</head>
<%
	String path = application.getContextPath() + "/index.jsp";
%>
<body>
	<!-- </body> background="images\ss.jpg" onload="setThings()"> -->

	<!-- 	<div class="container-fluid bottom-margin"
		style="background-image: url('images/ss.jpg');">
		<font style="color: white"> -->
	<div class="container">
		<div class="row">
			<div class="col-lg-9 left-margin-1 right-margin-1 text-center"
				align="center">
				<div class="left-margin-1 right-margin-1 text-center form-set">
					<div>
						<h2>Policy Edit</h2>
						<br> <br>
					</div>
					<div
						style="display: block; height: 100%; width: 80%; float: center;">
						<form role="form" id="defaultForm"
							class="voffset5 form-horizontal" style="width: 100%;"
							action="PolicyEditServlet" method="post">
							<div class="col-md-4" align="left">
								<label>Policy Name<font color="red">*</font> </label>
							</div>
							<div class="form-group col-md-8">
								<select name="edit_policy_name" id="edit_policy_name"
									class="form-control" autofocus onclick="getData()">
									<option label="--select--"></option>
								</select>
							</div>
							<div class="col-md-4" align="left">
								<label>Policy Type<font color="red">*</font> </label>
							</div>
							<div class="form-group col-md-8">
								<select name="edit_policy_type" id="edit_policy_type"
									class="form-control ">
									<option label="--select--"></option>
									<option value="VI">Vehicle insurance</option>
									<option value="TI">Travel insurance</option>
									<option value="LI">Life insurance</option>
									<option value="HI">Health insurance</option>
									<option value="CP">Child plans</option>
									<option value="RP">Retirement plans</option>
								</select>
							</div>
							<div class="col-md-4 voffset1" align="left">
								<label>Duration in Years<font color="red">*</font> </label>
							</div>
							<div class="form-group col-md-8">
								<input type="text" name="edit_duration" id="edit_duration"
									class="form-control" min="1" rows="5" placeholder="10"
									autofocus autocomplete="off">
							</div>
							<div class="col-md-4 voffset1" align="left">
								<label>TermAmount<font color="red">*</font> </label>
							</div>
							<div class="form-group col-md-8">
								<input type="text" name="edit_term_amount" id="edit_term_amount"
									class="form-control" min="100" rows="5" placeholder="xxxxxxxx"
									autofocus autocomplete="off">
							</div>
							</font>
							<div class="container-fluid voffset4">
								<div class="col-md-6 top-margin-1">
									<input type="submit" value="Edit" id="search"
										class="btn btn-warning btn-block">
								</div>

								<div class="col-md-6 top-margin-1">
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

													edit_policy_name : {
														validators : {
															notEmpty : {
																message : 'The policy name is required'
															},
															regexp : {
																regexp : /^([a-zA-Z ]){12,20}$/,
																message : 'Policy Name should be between 12 - 20 alphabets'
															}
														}
													},
													edit_policy_type : {
														validators : {
															notEmpty : {
																message : 'The policy type is required'
															}
														}
													}
												},
												edit_duration : {

													validators : {
														notEmpty : {
															message : 'The duration is required'
														},
														stringLength : {
															min : 1,
															max : 2,
															message : 'Invalid duration'
														}
													}
												},

												edit_term_amount : {
													validators : {
														notEmpty : {
															message : 'Term amount required'
														},
														stringLength : {
															min : 4,
															max : 6,
															message : 'Invalid Term amount'
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
	</script>
</body>
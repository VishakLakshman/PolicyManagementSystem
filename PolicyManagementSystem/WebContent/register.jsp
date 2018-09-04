<%@page import="com.cognizant.to.RegisterTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ include file="header.jsp"%>

<title>Registration | Policy Management System</title>
<body>
	<%
		if (request.getAttribute("status") != null) {
			if (request.getAttribute("status").toString()
					.equalsIgnoreCase("fails")) {

			}
	%>
	<div class="left-margin-1 text-center form-set right-margin-1">Technical Difficulty, Please try again later</div>
	<%
		}
	%>
	<div class="container">
		<!-- style="background-image: url('images/ss.jpg');">
		<font style="color: white"> -->
		<%RegisterTO registerTO=(RegisterTO)request.getAttribute("bean");%>
		<div class="row">
			<div class="col-lg-9 left-margin-1 text-center right-margin-1"
				align="center">
				<div class="left-margin-1 text-center form-set right-margin-1">
					<div>
						<h2>User Registration</h2>
						<br>
					</div>
					<div
						style="display: block; height: 100%; width: 80%; float: center;">
						<form role="form" id="defaultForm"
							class="voffset5 form-horizontal" style="width: 100%;"
							action="RegistrationServlet" method="post">
							<div class="col-md-4 voffset1" align="left">
								<label>First Name<font color="red">*</font> </label>
							</div>
							<div class="form-group col-md-8">
								<input type="text" name="user_firstname" id="user_firstname"
									class="form-control" rows="5" placeholder="eg: Aswin" autofocus
									autocomplete="off" onchange="capitalize(this)">
							</div>
							<div class="col-md-4 voffset1" align="left">
								<label>Last Name<font color="red">*</font> </label>
							</div>
							<div class="form-group col-md-8">
								<input class="form-control" type="text" name="user_lastname"
									id="user_lastname" autocomplete="off" placeholder="eg: Yohesh"
									onchange="capitalize(this)">
							</div>

							<div class="col-md-4 voffset1" align="left">
								<label>Date of Birth<font color="red">*</font> </label>
							</div>
							<div class="form-group col-md-8">
								<input type="date" id="user_date_of_birth"
									name="user_date_of_birth" class="form-control" rows="1"
									placeholder="yyyy-MM-dd" autocomplete="off">
							</div>
							<div class="col-md-4 voffset1" align="left">
								<label><br>Address<font color="red">*</font> </label>
							</div>
							<div class="form-group col-md-8">
								<textarea name="user_address" id="user_address"
									autocomplete="off" class="form-control" rows="3"
									placeholder="No.1 Sathy road,Saravanampatti"></textarea>
							</div>
							<div class="col-md-4 voffset1" align="left">
								<label>Mobile Number<font color="red">*</font> </label>
							</div>
							<div class="form-group col-md-8">
								<input type="text" id="user_contact_no" name="user_contact_no"
									class="form-control" min="7000000000" max="9999999999"
									onchange="capitalize(this)" placeholder="9876543210">
							</div>
							<div class="col-md-4 voffset1" align="left">
								<label>Email Address<font color="red">*</font> </label>
							</div>
							<div class="form-group col-md-8">
								<input type="email" id="user_email_address" autocomplete="off"
									name="user_email_address" class="form-control"
									placeholder="abcd@gmail.com">
							</div>
							<div class="col-md-4 voffset1" align="left">
								<label>Qualification<font color="red">*</font> </label>
							</div>
							<div class="form-group col-md-8">
								<select name="qualification" id="qualification"
									class="form-control">
									<option label="--select-- "></option>
									<option value="B.E">B.E</option>
									<option value="M.S">M.S</option>
									<option value="B.SC">B.SC</option>
								</select>
							</div>
							<div class="col-md-4 voffset1" align="left">
								<label>Gender<font color="red">*</font> </label>
							</div>
							<div class="form-group col-md-8">
								<select name="user_gender" id="user_gender" class="form-control">
									<option label="--select-- "></option>
									<option value="male">Male</option>
									<option value="female">Female</option>
									<option value="transgender">Transgender</option>
								</select>
							</div>
							<div class="col-md-4 voffset1" align="left">
								<label>Salary<font color="red">*</font> </label>
							</div>
							<div class="form-group col-md-8">
								<input type="text" id="salary" name="salary" min="1"
									autocomplete="off" min="10000" max="10000000"
									class="form-control" placeholder="100000000">
							</div>
							<div class="col-md-4 voffset1" align="left">
								<label>Pan Number<font color="red">*</font> </label>
							</div>
							<div class="form-group col-md-8">
								<input type="text" id="pannumber" name="pannumber"
									autocomplete="off" class="form-control"
									placeholder="ABCDE1234S" onchange="capitalize(this)">
							</div>
							<div class="col-md-4 voffset1" align="left">
								<label>Employer Type<font color="red">*</font> </label>
							</div>
							<div class="form-group col-md-8">
								<select name="employer_type" id="employer_type"
									class="form-control">
									<option label="--Select-- "></option>
									<option value="self">Self</option>
									<option value="employed">Employed</option>

								</select>
							</div>
							<div class="col-md-4 voffset1" align="left" id="employer_label">
								<label>Employer<font color="red">*</font> </label>
							</div>
							<div class="form-group col-md-8" id="employer_text">
								<input type="text" id="employer" name="employer"
									autocomplete="off" class="form-control" placeholder="Cognizant">
							</div>
							<div class="col-md-4 voffset1" align="left">
								<label>Hint Question<font color="red">*</font> </label>
							</div>
							<div class="form-group col-md-8">
								<select name="hint_question" id="hint_question"
									class="form-control ">
									<option label="--select-- "></option>
									<option value="Favorite pet">Favorite pet</option>
									<option value="Dream Job">Dream Job</option>
									<option value="Favorite place">Favorite place</option>
								</select>
							</div>
							<div class="col-md-4 voffset1" align="left">
								<label>Hint Answer<font color="red">*</font> </label>
							</div>
							<div class="form-group col-md-8">
								<input type="text" id="answer" name="answer" autocomplete="off"
									class="form-control" placeholder="xxxxxxxx">
							</div>

							<div class="container-fluid voffset4">
								<div class="col-md-6">
									<br>
									<button type="submit" id="submit_button" name="submit_button"
										class="btn btn-warning btn-block">Sign up</button>
									<!-- <input type="submit" value="Submit"
										class="btn btn-warning btn-block"> -->
								</div>
								<div class="col-md-6">
									<br> <input type="reset" value="Reset" id="reset_button"
										name="reset_button" class="btn btn-success btn-block"
										width="50%">
								</div>
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="main_footer.jsp"></jsp:include>
	<script src="js/validate.js" type="text/javascript"></script>
	<%
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.YEAR, -18);
		String max = sdf.format(cal.getTime());
		cal.add(Calendar.YEAR, -42);
		String min = sdf.format(cal.getTime());
	%>
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

													user_firstname : {
														validators : {
															notEmpty : {
																message : 'The first name field must not be empty'
															},
															regexp: {
										                        regexp: /^([a-zA-Z ]){3,20}$/,
										                        message: 'The firstname should be only alphabets and not above 20 characters.'
										                    },
															stringLength : {
																min : 3,
																max : 30,
																message : 'The first name should be more than 3 characters'
															}
														}
													},
													user_lastname : {
														validators : {
															notEmpty : {
																message : 'The last name field must not be empty'
															},
															regexp: {
										                        regexp: /^([a-zA-Z ]){3,20}$/,
										                        message: 'The lastname should be only alphabets and not above 20 characters.'
										                    }
														}
													},
													user_date_of_birth : {

														validators : {
															notEmpty : {
																message : 'The date field must not be empty'
															},
															date : {
																format : 'yyyy-MM-dd',
																min : '<%=min%>',
																max : '<%=max%>',
																message : 'The date is not a valid'
															}
														}
													},

													user_address : {
														validators : {
															notEmpty : {
																message : 'Fill your address'
															},
															regexp : {
																regexp : /^([a-zA-Z0-9 -\/,.'\n']){20,50}$/,
																message : 'The Address contains Invalid Characters'
															},
															stringLength : {
																min : 20,
																max : 50,
																message : ' Length must be 20-50 characters'
															}
														}
													},

													user_contact_no : {
														validators : {
															notEmpty : {
																message : 'The mobile number field must not be empty'
															},
															stringLength : {
																min : 10,
																max : 10,
																message : 'Mobile Number must be greater than 7000000000 and less than 9999999999'
															}
														}
													},

													user_email_address : {
														validators : {
															notEmpty : {
																message : 'The email address field must not be empty'
															},
															regexp : {
																regexp :/^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+)*\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$/,
																message : 'The Address contains Invalid Characters'
															},
															emailAddress : {
																message : 'The input is not a valid email address'
															}
														}
													},

													qualification : {
														validators : {
															notEmpty : {
																message : 'Qualification field must not be empty'
															}
														}
													},

													user_gender : {
														validators : {
															notEmpty : {
																message : 'Gender field must not be empty'
															}
														}
													},

													salary : {
														validators : {
															notEmpty : {
																message : 'Salary field must not be empty'
															},
															stringLength : {
																min : 5,
																max : 8,
																message : 'Salary should be of minimum 10000 and maximun 100000000'
															}
														}
													/* stringLength: {
													                		var n=$("#salary").val();
													                		if(n<=0)
													     
													                			message:'Salary should be positive'
													                		} */
													},
													pannumber : {
														validators : {
															notEmpty : {
																message : 'Pan Number field must not be empty'
															},
															regexp : {
																regexp : /^([a-z||A-Z||0-9]){10}$/,
																message : 'The pannumber is not valid'
															}
														/* ,
																													stringLength : {
																														min : 10,
																														max : 10,
																														message : ' must be 10 character length'
																													} */
														}
													},
													employer_type : {
														validators : {
															notEmpty : {
																message : 'Employee type field must not be empty'
															}
														}
													},
													employer : {
														validators : {
															notEmpty : {
																message : 'Employer field must not be empty'
															}
														}
													},
													hint_question : {
														validators : {
															notEmpty : {
																message : 'Hint Question field must not be empty'
															}
														}
													},
													answer : {
														validators : {
															notEmpty : {
																message : 'Hint Answer field must not be empty'
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
		function capitalize(str) {
			if (str.id == "pannumber" || str.id == "user_contact_no") {
				var val = str.value;
				if (str.value.length != 10) {
					str.value = val.slice(0, 10);
					str.style = "border-color:#55595C;";
					document.getElementsByTagName("small")[20].style = "display:none; color:#d9534f;";
					document.getElementsByTagName("small")[20].innerHTML = "Pan number must be 10 character length";
					document.getElementsByTagName("small")[21].style = "display:none; color:#d9534f;";
					//document.getElementsByTagName("small")[21].innerHTML="testing ";
				} else {
					/* 					document.getElementsByTagName("small")[20].innerHTML="";
					 document.getElementsByTagName("small")[21].innerHTML=""; */
				}
				str.value = str.value.toUpperCase();

			} else if (str.id == "user_firstname") {
				var val = str.value;
				str.value = val.charAt(0).toUpperCase()
						+ val.slice(1).toLowerCase();
				console.log(val.toUpperCase());
			} else if (str.id == "user_lastname") {
				var val = str.value;
				str.value = val.charAt(0).toUpperCase()
						+ val.slice(1).toLowerCase();
				console.log(val.toUpperCase());
			}

		}
		function validatefield(id, val) {
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (xhttp.readyState == 4 && xhttp.status == 200) {
					var status = xhttp.responseText;
				}
			};
			xhttp.open("GET", "RegistrationServlet?id=" + id + "val=" + val,
					true);
			xhttp.send();
		}
	</script>
</body>

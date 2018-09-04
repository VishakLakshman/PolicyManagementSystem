
<section id="login">
	<div class="container" style="float: center">
		<div class="row">

			<div class="col-lg-6 col-lg-offset-3 form-set text-center">
				<h2 class="section-heading">
<!-- 						<font color="white"> -->Login <!-- </font> -->
				</h2>
				<!-- <hr class="light"> -->
				<br>
				<p class="text-faded"></p>

				<form role="form" id="defaultForm" class="voffset3 form-horizontal"
					action="LoginServlet" method="post">
					<div class="col-md-4 voffset1" align="left">
						<label>Username <font color="red">*</font>
						</label>
					</div>
					<div class="form-group col-md-8">
						<input type="text" name="UserName" id="UserName"
							class="form-control" rows="5" placeholder="xxxxxxxx"
							autocomplete="off">
					</div>
					<div class="col-md-4 voffset1" align="left">
						<label>Password <font color="red">*</font>
						</label>
					</div>
					<br>
					<div class="form-group col-md-8">
						<input type="password" name="Password" id="Password"
							class="form-control" rows="5" placeholder="*******"
							autocomplete="off" onpaste="return false;">
					</div>
					<div class="container-fluid voffset4">
						<div class="col-md-6">
							<br>
							<button type="submit" class="btn btn-warning btn-block"
								style="margin-right: 30%">Login</button>
							<!-- <input type="submit" value="Submit"
										class="btn btn-warning btn-block"> -->
						</div>
						<div class="col-md-6">
							<br> <input type="reset" value="Reset"
								class="btn btn-success btn-block" style="margin-left: 30%">
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 col-sm-offset-3">
							<br>
							<h3>Not Registered ?</h3>
						</div>
						<div class="col-sm-6 col-sm-offset-3">
							<a class="block-tag" href="register.jsp"><font color="white">Create an
									account</font></a>
						</div>
					</div>
				</form>
				<!-- <a href="login.jsp" class="page-scroll btn btn-default btn-xl">Get
							Started</a> -->
			</div>
		</div>
	</div>
</section>
<script type="text/javascript">
	$(document).ready(function() {
		$('#defaultForm').formValidation({
			button : {
				selector : '[type="submit"]',
				disabled : ''
			},
			message : 'This value is not valid',
			fields : {

				UserName : {
					validators : {
						notEmpty : {
							message : 'The user name field must not be empty'
						}
					}
				},
				Password : {
					validators : {
						notEmpty : {
							message : 'The password field must not be empty'
						}
					}
				}
			}
		});
	});

	var dateControler = {
		currentDate : null
	}
	/* }); */
</script>
</section>

u<div class="container">
	<div class="row">
		<div class="col-lg-9 left-margin-1 right-margin-1 text-center"
			align="center">
			<div class="left-margin-1 text-center form-set right-margin-1">
				<div>
					<h2>Change Contact Number</h2>
					<br>
				</div>
				<div
					style="display: block; height: 100%; width: 80%; float: center;">
					<!-- <form role="form" class="change_num" id="defaultForm" class="voffset5 form-horizontal"></form> -->
					<form role="form" class="change_contact" id="defaultForm" class="form-horizontal"
						style="width: 100%;" action="../settings/Settings" method="post">
						<div class="col-md-4" align="left">
							<label>Enter Password<font color="red">*</font>
							</label>
						</div>
						<div class="form-group col-md-8">
							<input type="password" name="current_password"
								id="current_password" class="form-control" rows="5"
								placeholder="******" autocomplete="off" onpaste="return false;">
						</div>
						<div class="col-md-4 voffset1" align="left">
							<label>New Mobile Number<font color="red">*</font>
							</label>
						</div>
						<div class="form-group col-md-8">
							<input type="text" name="new_number" id="new_number"
								class="form-control" min="7000000000" max="9999999999" rows="5" placeholder="eg: 9009008989"
								autocomplete="off">
						</div>
						<div class="col-md-4 voffset1" align="left">
							<label>Re-Enter Mobile Number<font color="red">*</font>
							</label>
						</div>
						<div class="form-group col-md-8">
							<input type="text" name="re_number" id="re_number"
								class="form-control" rows="5" min="7000000000" max="9999999999" placeholder="eg: 9009008989"
								autocomplete="off">
						</div>
						<div class="container-fluid voffset4">
							<div class="col-md-6 top-margin-1 bottom-margin-1">
								<input type="submit" value="change" id="search"
									class="btn btn-warning btn-block">
							</div>

							<div class="col-md-6 top-margin-1 bottom-margin-1">
								<input type="reset" value="Reset"
									class="btn btn-success btn-block">
									<input type="password" name="type" id="type" value="mobile" hidden>
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

						$('.change_num')
								.formValidation(
										{
											button : {
												selector : '[type="submit"]',
												disabled : ''
											},
											message : 'This value is not valid',
											fields : {
												current_password: {
													validators : {
														notEmpty : {
															message : 'The password field must not be empty'
														}
													}
												},
												new_number : {
													validators : {
														notEmpty : {
															message : 'The new number field must not be empty'
														}
														}
													},
												re_number : {
													validators : {
														notEmpty : {
															message : 'The re number field must not be empty'
														},
														identical : {
															field : 'new_number',
															message: 'This field must be same'
														}
														}
													}										}
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

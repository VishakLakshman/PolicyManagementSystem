<div class="container">
	<div class="row">
		<div class="col-lg-9 left-margin-1 right-margin-1 text-center"
			align="center">
			<div class="left-margin-1 text-center form-set right-margin-1">
				<div>
					<h2>Change Password</h2>
					<br>
				</div>
				<div
					style="display: block; height: 100%; width: 80%; float: center;">
					<form role="form" class="def_form" id="defaultForm" class="voffset5 form-horizontal"
						style="width: 100%;" action="../settings/Settings" method="post">
						<div class="col-md-4" align="left">
							<label>Current Password<font color="red">*</font> </label>
						</div>
						<div class="form-group col-md-8">
							<input type="password" name="current_password"
								id="current_password" class="form-control" rows="5"
								placeholder="******" autocomplete="off"
								onpaste="return false;">
						</div>
						<div class="col-md-4 voffset1" align="left">
							<label>New Password<font color="red">*</font> </label>
						</div>
						<div class="form-group col-md-8">
							<input type="password" name="new_password" id="new_password"
								class="form-control" rows="5" placeholder="******"
								autocomplete="off" onpaste="return false;">
						</div>
						<div class="col-md-4 voffset1" align="left">
							<label>Re-Enter Password<font color="red">*</font> </label>
						</div>
						<div class="form-group col-md-8">
							<input type="password" name="re_password" id="re_password"
								class="form-control" rows="5" placeholder="******"
								autocomplete="off" onpaste="return false;">
						</div>
						<div class="container-fluid voffset4">
							<div class="col-md-6 top-margin-1 bottom-margin-1">
								<input type="submit" value="change" id="search"
									class="btn btn-warning btn-block">
							</div>

							<div class="col-md-6 top-margin-1 bottom-margin-1">
								<input type="reset" value="Reset"
									class="btn btn-success btn-block"> <input
									type="password" name="type" id="type" value="pass" hidden>
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
						$('.def_form').formValidation(
										{
											button : {
												selector : '[type="submit"]',
												disabled : ''
											},
											message : 'This value is not valid',
											fields : {
												new_password : {
													validators : {
														notEmpty : {
															message : 'The new password field must not be empty'
														}
													}
												},
												re_password : {
													validators : {
														notEmpty : {
															message : 'This field must not be empty'
														},
														identical : {
															field : 'new_password',
															message : 'New password doesn\'t match'
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
</script>
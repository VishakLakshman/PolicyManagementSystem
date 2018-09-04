<div class="container">
	<div class="row">
		<div class="col-lg-9 left-margin-1 right-margin-1 text-center"
			align="center">
			<div class="left-margin-1 text-center form-set right-margin-1">
				<div>
					<h2>Change E-mail</h2>
					<br>
				</div>
				<div
					style="display: block; height: 100%; width: 80%; float: center;">
					<form role="form" class="change_email" id="defaultForm"
						class="voffset5 form-horizontal" style="width: 100%;"
						action="../settings/Settings" method="post">
						<div class="col-md-4" align="left">
							<label>Enter Password<font color="red">*</font> </label>
						</div>
						<div class="form-group col-md-8">
							<input type="password" name="current_password"
								id="current_password" class="form-control" rows="5"
								placeholder="******" autocomplete="off" onpaste="return false;">
						</div>
						<div class="col-md-4 voffset1" align="left">
							<label>New E-Mail<font color="red">*</font> </label>
						</div>
						<div class="form-group col-md-8">
							<input type="text" name="new_email" id="new_email"
								class="form-control" rows="5" placeholder="eg: xyz@abc.com"
								autocomplete="off">
						</div>
						<div class="col-md-4 voffset1" align="left">
							<label>Re-Enter E-Mail<font color="red">*</font> </label>
						</div>
						<div class="form-group col-md-8">
							<input type="text" name="re_email" id="re_email"
								class="form-control" rows="5" placeholder="eg: xyz@abc.com"
								autocomplete="off">
						</div>
						<div class="container-fluid voffset4">
							<div class="col-md-6 top-margin-1 bottom-margin-1">
								<input type="submit" value="change" id="search"
									class="btn btn-warning btn-block">
							</div>

							<div class="col-md-6 top-margin-1 bottom-margin-1">
								<input type="reset" value="Reset"
									class="btn btn-success btn-block"> <input
									type="password" name="type" id="type" value="email" hidden>
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
						$('.change_email')
								.formValidation(
										{
											button : {
												selector : '[type="submit"]',
												disabled : ''
											},
											message : 'This value is not valid',
											fields : {
												new_email : {
													validators : {
														notEmpty : {
															message : 'The new email field must not be empty'
														},
														regexp : {
															regexp : /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/,
															message : 'The email should be of format abc@xyz.com'
														}

													}
												},
												re_email : {
													validators : {
														notEmpty : {
															message : 'The Re-email field must not be empty'
														},
														identical : {
															field : 'new_email',
															message : ' The emails should be same'
														},
														regexp : {
															regexp : /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/,
															message : 'The email should be of format abc@xyz.com'
														}
													}
												}
											}
										});
					});
</script>

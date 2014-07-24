<!DOCTYPE html>
<html lang="en">
<%@ include file="../commonHeader.jsp"%>

<!--main content start-->
<section class="panel">
	<header class="panel-heading"> User Modify Password </header>
	<div class="panel-body">
		<s:actionerror />
		<s:actionmessage />
		<form id="changePwdForm" class="form-horizontal tasi-form" action="backend/user/changePassword.action" method="post">
			<div class="form-group has-success">
				<label class="col-lg-2 control-label">Original Password</label>
				<div class="col-lg-10">
					<input type="password" name="user.password" class="form-control">
				</div>
			</div>
			<div class="form-group has-success">
				<label class="col-lg-2 control-label">New Password</label>
				<div class="col-lg-10">
					<input type="password" id="newPassword" name="newPassword" class="form-control">
				</div>
			</div>
			<div class="form-group has-success">
				<label class="col-lg-2 control-label">new Password Again</label>
				<div class="col-lg-10">
					<input type="password" name="confirm_password" class="form-control">
				</div>
			</div>


			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<button class="btn btn-info" type="submit">Save</button>
					<button class="btn btn-info" type="button" onclick="history.go(-1);">Cancel</button>
				</div>
			</div>
		</form>
	</div>
</section>
<script type="text/javascript">
	//please refer to form-validation-script.js
	$(document).ready(function() {
		$("#changePwdForm").validate({
			rules : {
				confirm_password : {
					equalTo : "#newPassword"
				}
			},
			messages : {
				'user.password' : {
					required : "Input original password"
				},
				'newPassword' : {
					required : "Input new password"
				},
				confirm_password : {
					required : "Input new password again",
					equalTo : "Twice new password doesn't match"
				}
			}
		});
	});
</script>
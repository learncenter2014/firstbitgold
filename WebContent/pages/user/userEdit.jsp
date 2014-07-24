<%@ include file="../commonHeader.jsp"%>

<!--main content start-->
<section class="panel">
	<header class="panel-heading"> User Edit </header>
	<form role="form" class="form-horizontal tasi-form" action="${rootPath}/user/save.action">
		<div class="form-group has-success">
			<label class="col-lg-2 control-label">Owner</label>
			<div class="col-lg-10">
				<input name="user.id" type="hidden" value="${user.id}" /> <input type="text" placeholder="" name="user.name" class="form-control"
					required="required" value="${user.name}">
			</div>
		</div>
		<div class="form-group has-success">
			<label class="col-lg-2 control-label">User Group</label>
			<div class="col-lg-10">
				<s:select list="userGroup" name="user.ugroup" value="user.ugroup" listKey="id" listValue="name"/>
			</div>
		</div>
		<div class="form-group has-success">
			<label class="col-lg-2 control-label">Email</label>
			<div class="col-lg-10">
				<input type="text" placeholder="" name="user.email" class="form-control" value="${user.email}">
			</div>
		</div>
		<div class="form-group has-success">
			<label class="col-lg-2 control-label">Location</label>
			<div class="col-lg-10">
				<input type="text" placeholder="" name="user.location" class="form-control" value="${user.location}">
			</div>
		</div>
		<div class="form-group has-success">
			<label class="col-lg-2 control-label">Comments</label>
			<div class="col-lg-10">
				<input type="text" placeholder="" name="user.comments" class="form-control" value="${user.comments}">
			</div>
		</div>
		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">
				<button class="btn btn-info" type="submit">Save</button>
				<button class="btn btn-info" type="button" onclick="window.location.href='${rootPath}/user/index.action'">Cancel</button>
			</div>
		</div>
	</form>
</section>

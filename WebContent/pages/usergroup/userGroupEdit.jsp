<%@ include file="../commonHeader.jsp"%>

<!--main content start-->
<section class="panel">
	<header class="panel-heading"> User Edit </header>
	<form role="form" class="form-horizontal tasi-form" action="${rootPath}/usergroup/save.action">
		<div class="form-group has-success">
			<label class="col-lg-2 control-label">Group Name</label>
			<div class="col-lg-10">
				<input name="userGroup.id" type="hidden" value="${userGroup.id}" /> <input type="text" placeholder="" id="f-name" name="userGroup.name"
					class="form-control" required="required" value="${userGroup.name}">
			</div>
		</div>

		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">
				<button class="btn btn-info" type="submit">Save</button>
				<button class="btn btn-info" type="button" onclick="window.location.href='${rootPath}/usergroup/index.action'">Cancel</button>
			</div>
		</div>
	</form>
</section>

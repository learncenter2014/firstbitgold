<%@ include file="../commonHeader.jsp"%>

<!--main content start-->
<section class="panel">
	<header class="panel-heading"> Edit </header>
	<form role="form" class="form-horizontal tasi-form" action="${rootPath}/devicetype/save.action">
		<div class="form-group has-success">
			<label class="col-lg-2 control-label">Type Name</label>
			<div class="col-lg-10">
				<input name="deviceType.id" type="hidden" value="${deviceType.id}" /> <input type="text" placeholder="" id="f-name" name="deviceType.name"
					class="form-control" required="required" value="${deviceType.name}">
			</div>
		</div>


		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">
				<button class="btn btn-info" type="submit">Save</button>
				<button class="btn btn-info" type="button" onclick="window.location.href='${rootPath}/devicetype/index.action'">Cancel</button>
			</div>
		</div>
	</form>
</section>

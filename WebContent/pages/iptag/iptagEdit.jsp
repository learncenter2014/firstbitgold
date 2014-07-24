<%@ include file="../commonHeader.jsp"%>

<!--main content start-->
<section class="panel">
	<header class="panel-heading"> Edit </header>
	<form role="form" class="form-horizontal tasi-form" action="${rootPath}/iptag/save.action">
		<div class="form-group has-success">
			<label class="col-lg-2 control-label">Tag Name</label>
			<div class="col-lg-10">
				<input name="ipTag.id" type="hidden" value="${ipTag.id}" /> <input type="text" placeholder="" id="f-name" name="ipTag.name" class="form-control"
					required="required" value="${ipTag.name}">
			</div>
		</div>


		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">
				<button class="btn btn-info" type="submit">Save</button>
				<button class="btn btn-info" type="button" onclick="window.location.href='${rootPath}/iptag/index.action'">Cancel</button>
			</div>
		</div>
	</form>
</section>

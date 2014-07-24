
<!--main content start-->
<section class="panel">
	<header class="panel-heading"> Edit </header>
	<form role="form" class="form-horizontal tasi-form" action="${rootPath}/project/save.action">
		<div class="form-group has-success">
			<label class="col-lg-2 control-label">Project Name</label>
			<div class="col-lg-10">
				<input name="project.id" type="hidden" value="${project.id}" /> <input type="text" placeholder="" id="f-name" name="project.name"
					class="form-control" required="required" value="${project.name}">
			</div>
		</div>


		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">
				<button class="btn btn-info" type="submit">Save</button>
				<button class="btn btn-info" type="button" onclick="window.location.href='${rootPath}/project/index.action'">Cancel</button>
			</div>
		</div>
	</form>
</section>

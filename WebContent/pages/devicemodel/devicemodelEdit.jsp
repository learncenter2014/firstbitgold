<%@ include file="../commonHeader.jsp"%>
<!--main content start-->
<section class="panel">
	<header class="panel-heading"> Edit </header>
	<form role="form" class="form-horizontal tasi-form" action="${rootPath}/devicemodel/save.action">
		<div class="form-group has-success">
			<label class="col-lg-2 control-label">Model Name</label>
			<div class="col-lg-10">
				<input name="deviceModel.id" type="hidden" value="${deviceModel.id}" /> <input type="text" placeholder="" id="f-name" name="deviceModel.name"
					class="form-control" required="required" value="${deviceModel.name}">
			</div>
		</div>
		<div class="form-group has-success">
			<label class="col-lg-2 control-label">Device Type</label>
			<div class="col-lg-10">
				<s:select list="deviceTypes" name="deviceModel.devtype" value="deviceModel.devtype" listKey="id" listValue="name"/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">
				<button class="btn btn-info" type="submit">Save</button>
				<button class="btn btn-info" type="button" onclick="window.location.href='${rootPath}/devicemodel/index.action'">Cancel</button>
			</div>
		</div>
	</form>
</section>

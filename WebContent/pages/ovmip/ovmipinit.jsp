
<div id="errorarea"><%@include file="../strutsMessage.jsp"%></div>

<!--main content start-->
<section class="panel">
	<form role="form" class="form-horizontal tasi-form" action="${rootPath}/ovmip/requestip.action">
		<div class="col-sm-6">
			<section class="panel">
				<header class="panel-heading"> OVM IP Allocation </header>
				<table class="table">
					<thead>
						<tr>
							<th>Parameter</th>
							<th>Value</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>User Name</td>
							<td><s:property value="#session['sessionUser'].name" /></td>
						</tr>
						<tr>
							<td>IP Scope</td>
							<td><s:if test="ipscopes!=null"><s:select list="ipscopes" listKey="ip_scope_name" listValue="ip_scope_name" /></s:if></td>
						</tr>
						<tr>
							<td>OVM Management IP</td>
							<td></td>
						</tr>
						<tr>
							<td>Network</td>
							<td></td>
						</tr>
						<tr>
							<td>VM List</td>
							<td></td>
						</tr>
						<tr>
							<td>Tag</td>
							<td><s:if test="ipTagBeans!=null"><s:select list="ipTagBeans" listKey="name" listValue="name" /></s:if></td>
						</tr>
						<tr>
							<td>Request new IP</td>
							<td><input class="btn btn-info" type="button" value="Allocation OVM IP"/></td>
						</tr>
						<tr>
							<td>Query Result</td>
							<td><input class="btn btn-info" type="button" value="Query OVM IP"/></td>
						</tr>
					</tbody>
				</table>
			</section>
		</div>
	</form>
</section>
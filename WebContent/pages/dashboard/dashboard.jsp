<!DOCTYPE html>
<%@ include file="/pages/commonHeader.jsp"%>
<html lang="en">
<head>
<title></title>
<script type="text/javascript">
	function selectMenu(menu, parameter) {
		if (null != parameter) {
			window.location = $("#" + menu)[0].href + parameter;
		}else{
			window.location = $("#" + menu)[0].href;
		}
	}
</script>
</head>
<body>
	<!--state overview start-->
	<div class="row state-overview">
		<div class="col-lg-3 col-sm-6">
			<a href="javascript:selectMenu('userlink')">
				<section class="panel">
					<div class="symbol terques">
						<i class="fa fa-user"></i>
					</div>
					<div class="value">
						<h1 class="count">
							<s:property value="dashBoardBean.userCounter" default="0" />
						</h1>
						<p>User</p>
					</div>
				</section>
			</a>
		</div>
		<div class="col-lg-3 col-sm-6">
			<a href="javascript:selectMenu('projectlink')">
				<section class="panel">
					<div class="symbol red">
						<i class="fa fa-eye"></i>
					</div>
					<div class="value">
						<h1 class=" count2">
							<s:property value="dashBoardBean.projectCounter" default="0" />
						</h1>
						<p>Project</p>
					</div>
				</section>
			</a>
		</div>
		<div class="col-lg-3 col-sm-6">
			<a href="javascript:selectMenu('devicelink')">
				<section class="panel">
					<div class="symbol yellow">
						<i class="fa fa-cog"></i>
					</div>
					<div class="value">
						<h1 class=" count3">
							<s:property value="dashBoardBean.deviceCounter" default="0" />
						</h1>
						<p>Device Info</p>
					</div>
				</section>
			</a>
		</div>
	</div>
	<!--state overview end-->
	<section class="wrapper">
		<s:iterator value="mapRackDeviceInfo">
			<s:property value="#entry" />
			<div class="col-sm-3">
				<section class="panel">
					<header class="panel-heading">
						<b>Rack:</b><i style="color: blue"><s:property value="key" /></i>
					</header>
					<table class="table">
						<thead>
							<tr>
								<th>Slot</th>
								<th>Device Name</th>
								<th>Device Type</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="value">
								<tr>
									<td><s:property value="slot" /></td>
									<td><a href="javascript:selectMenu('devicelink','?search=${name}')"><s:property value="name" /></a></td>
									<td><s:property value="devtypeinfo" /></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</section>
			</div>

		</s:iterator>
	</section>
</body>
</html>

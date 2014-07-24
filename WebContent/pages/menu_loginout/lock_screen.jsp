<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/pages/commonHeader.jsp"%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lock Screen</title>


<!-- Bootstrap core CSS -->
<link href="${rootPath}/jslib/flatlab/css/bootstrap.min.css" rel="stylesheet">
<link href="${rootPath}/jslib/flatlab/css/bootstrap-reset.css" rel="stylesheet">
<!--external css-->
<link href="${rootPath}/jslib/flatlab/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
<!-- Custom styles for this template -->
<link href="${rootPath}/jslib/flatlab/css/style.css" rel="stylesheet">
<link href="${rootPath}/jslib/flatlab/css/style-responsive.css" rel="stylesheet" />


<!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
<!--[if lt IE 9]>
    <script src="${rootPath}/jslib/flatlab/js/html5shiv.js"></script>
    <script src="${rootPath}/jslib/flatlab/js/respond.min.js"></script>
    <![endif]-->
</head>


<body class="lock-screen" onload="startTime()">
	<div class="lock-wrapper">
		<div id="time"></div>
		<div class="lock-box text-center">
			<img src="jslib/flatlab/img/follower-avatar.jpg" alt="lock avatar" />
			<h1>User</h1>
			<span class="locked">Locked</span>
			<form role="form" class="form-inline" action="${rootPath}/index.jsp">
				<div class="form-group col-lg-12">
					<input type="password" placeholder="Password" id="exampleInputPassword2" class="form-control lock-input">
					<button class="btn btn-lock" type="submit">
						<i class="fa fa-arrow-right"></i>
					</button>
				</div>
			</form>
		</div>
	</div>
	<script>
		function startTime() {
			var today = new Date();
			var h = today.getHours();
			var m = today.getMinutes();
			var s = today.getSeconds();
			// add a zero in front of numbers<10
			m = checkTime(m);
			s = checkTime(s);
			document.getElementById('time').innerHTML = h + ":" + m + ":" + s;
			t = setTimeout(function() {
				startTime();
			}, 500);
		}

		function checkTime(i) {
			if (i < 10) {
				i = "0" + i;
			}
			return i;
		}
	</script>
</body>
</html>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="IP Address Management">
<meta name="author" content="peterli">
<meta name="keyword" content="IP Address Management">

<title>Login</title>


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


<body class="login-body">
	<div class="container">

		<form class="form-signin" action="${rootPath}/login.action" method="post">
			<h2 class="form-signin-heading">IP Management System</h2>
			<div class="login-wrap">
				<input type="text" name="user.name" class="form-control" placeholder="User ID" autofocus> <input type="password" name="user.password"
					class="form-control" placeholder="Password"> <label class="checkbox"> <input type="checkbox" value="remember-me">
					Remember me <span class="pull-right"> <a data-toggle="modal" href="#myModal"> Forgot Password?</a>
				</span>
				</label>
				<button class="btn btn-lg btn-login btn-block" type="submit">Sign in</button>
				<%--<div class="registration">
					Don't have an account yet? <a class="" href=""> Create an account </a>
				</div> --%>
			</div>


			<!-- Modal -->
			<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title">Forgot Password ?</h4>
						</div>
						<div class="modal-body">
							<p>Enter your e-mail address below to reset your password.</p>
							<input type="text" name="email" placeholder="Email" autocomplete="off" class="form-control placeholder-no-fix">


						</div>
						<div class="modal-footer">
							<button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
							<button class="btn btn-success" type="button">Submit</button>
						</div>
					</div>
				</div>
			</div>
			<!-- modal -->
		</form>
	</div>


	<!-- js placed at the end of the document so the pages load faster -->
	<script src="${rootPath}/jslib/flatlab/js/jquery.js"></script>
	<script src="${rootPath}/jslib/flatlab/js/bootstrap.min.js"></script>

</body>
</html>

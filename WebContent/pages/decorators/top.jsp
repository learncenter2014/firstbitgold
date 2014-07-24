<%@ include file="/pages/commonHeader.jsp"%>
<!--header start-->
<header class="header white-bg">
	<div class="sidebar-toggle-box">
		<div class="fa fa-bars tooltips" data-placement="right" data-original-title="menu navigation"></div>
	</div>
	<!--logo start-->
	<a href="${rootPath}/index.jsp" class="logo">IP<span>Address Management System</span></a>
	<!--logo end-->
	<div class="top-nav ">
		<ul class="nav pull-right top-menu">
			<!-- user login dropdown start-->
			<li class="dropdown"><a class="dropdown-toggle" href="#" data-toggle="dropdown"> <img alt=""
					src="${rootPath}/jslib/flatlab/img/avatar1_small.jpg"> <span class="username"><s:property value="#session['sessionUser'].name" /></span> <b
					class="caret"></b>
			</a>
				<ul class="dropdown-menu extended logout">
					<div class="log-arrow-up"></div>
					<li><a href="${rootPath}/user/changePassword.action"><i class=" fa fa-suitcase"></i>Modify Password</a></li>
					<li><a href="${rootPath}/logout.action"><i class="fa fa-key"></i>Log Out</a></li>
				</ul></li>
		</ul>
	</div>
</header>
<!--header end-->
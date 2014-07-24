<%@ include file="/pages/commonHeader.jsp"%>
<section id="container">

	<!--sidebar start-->
	<aside>
		<div id="sidebar" class="nav-collapse ">
			<!-- sidebar menu start-->
			<ul class="sidebar-menu" id="nav-accordion">
				<li><a href="${rootPath}/usergroup/index.action"> <i class="fa fa-tasks"></i> <span>User Group</span> <span
						class="label label-danger pull-right mail-info"></span>
				</a></li>
				<li><a id="userlink" href="${rootPath}/user/index.action"> <i class="fa fa-user"></i> <span>User</span> <span
						class="label label-danger pull-right mail-info"></span>
				</a></li>
				<li><a href="${rootPath}/devicemodel/index.action"> <i class="fa fa-flag"></i><span>Device Model</span> <span
						class="label label-danger pull-right mail-info"></span>
				</a></li>
				<li><a href="${rootPath}/devicetype/index.action"> <i class="fa fa-anchor"></i> <span>Device Type</span> <span
						class="label label-danger pull-right mail-info"></span>
				</a></li>
				<li><a href="${rootPath}/iptag/index.action"> <i class="fa fa-bars"></i> <span>IP Tag</span> <span
						class="label label-danger pull-right mail-info"></span>
				</a></li>
				<li><a id="projectlink" href="${rootPath}/project/index.action"> <i class="fa fa-eye"></i> <span>Project</span> <span
						class="label label-danger pull-right mail-info"></span>
				</a></li>
				<li><a href="${rootPath}/subnet/index.action"> <i class="fa fa-bolt"></i> <span>SubNet</span> <span
						class="label label-danger pull-right mail-info"></span>
				</a></li>
				<li><a id="devicelink" href="${rootPath}/deviceinfo/index.action"> <i class="fa fa-cog"></i> <span>Device Info</span> <span
						class="label label-danger pull-right mail-info"></span>
				</a></li>
				<li><a id="ovmiplink" href="${rootPath}/ovmip/init.action"> <i class="fa fa-cog"></i> <span>OVM IP Allocation</span> <span
						class="label label-danger pull-right mail-info"></span>
				</a></li>
				<li><a href="${rootPath}/logout.action"> <i class="fa fa-sign-out"></i> <span>Log Out</span> <span
						class="label label-danger pull-right mail-info"></span>
				</a></li>
				<!--multi level menu end-->

			</ul>
			<!-- sidebar menu end-->
		</div>
	</aside>
	<!--sidebar end-->
</section>
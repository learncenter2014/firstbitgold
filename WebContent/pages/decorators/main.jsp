<!DOCTYPE html>
<html lang="en">
  <head>
    <%@ include file="/pages/commonHeader.jsp" %>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="peter">
    <meta name="keyword" content="dynamicform">
    <title><decorator:title default="动态表单管理系统" /></title>
    
    <link rel="shortcut icon" href="jslib/flatlab/img/favicon.png">

    <!-- Bootstrap core CSS -->
    <link href="jslib/flatlab/css/bootstrap.min.css" rel="stylesheet">
    <link href="jslib/flatlab/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="jslib/flatlab/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    
    <!-- Custom styles for this template -->
    <link href="jslib/flatlab/css/style.css" rel="stylesheet">
    <link href="jslib/flatlab/css/style-responsive.css" rel="stylesheet" />
    <link href="jslib/jquery-ui-1.10.4.custom/css/start/jquery-ui-1.10.4.custom.min.css" rel="stylesheet" />
    <style type="text/css">
		.ui-dialog {
			z-index: 2000;
		}
		body{
		  background:none repeat scroll 0 0 rgb(255, 255, 255);
		}
    </style>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
    <!--[if lt IE 9]>
      <script src="jslib/flatlab/js/html5shiv.js"></script>
      <script src="jslib/flatlab/js/respond.min.js"></script>
    <![endif]-->
    
    <!-- js placed at the end of the document so the pages load faster -->
    <script src="jslib/flatlab/js/jquery.js"></script>
    <script src="jslib/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>
    <script src="jslib/flatlab/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="jslib/flatlab/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="jslib/flatlab/js/jquery.scrollTo.min.js"></script>
    <script src="jslib/flatlab/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="jslib/flatlab/js/respond.min.js" ></script>

    <decorator:head/>
  </head>

  <body>

  <section id="container" >
      <!--header start-->
      <jsp:include page="/pages/decorators/top.jsp"></jsp:include>
      <!--header end-->
      <!--sidebar start-->
      <jsp:include page="/pages/decorators/menu.jsp"></jsp:include>
      <!--sidebar end-->
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
            <decorator:body />  
          </section>
      </section>
      <!--main content end-->
      <!--footer start-->
      <jsp:include page="/pages/decorators/foot.jsp"></jsp:include>
      <!--footer end-->
  </section>
      <!--common script for all pages-->
      <script src="jslib/flatlab/js/common-scripts.js"></script>
      <div id="dialog_message" title="系统消息区"></div>
  </body>
</html>

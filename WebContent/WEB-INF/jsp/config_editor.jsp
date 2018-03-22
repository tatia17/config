
<%@include file="common.jsp" %>

<c:set var="isNew" value="${empty configs.configs.id}" />
<c:set var="newsId" value="${configs.configs.id}" />


<html>
<head>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta name="google" content="notranslate" />

<link href="<c:url value="/styles/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/styles/font-awesome/css/font-awesome.css" />"
	rel="stylesheet">
<link href="<c:url value="/styles/css/animate.css" />" rel="stylesheet">
<link href="<c:url value="/styles/css/style.css" />" rel="stylesheet">
<title>Config</title>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
</head>

<body>



	<div id="wrapper">



		<div>
			<div class="row border-bottom"></div>
			<div class="row wrapper border-bottom white-bg page-heading">
				<div class="col-lg-10">
					<h2>Config Editor</h2>
					<ol class="breadcrumb">

						<li><a href="${ctx}/configs">configs</a></li>
						<li class="active"><strong>Config Editor</strong></li>
					</ol>
				</div>
				<div class="col-lg-2"></div>
			</div>


			<div class="wrapper wrapper-content animated fadeIn">
				<div class="row" style="padding-left: 24%;">


					<div class="col-lg-9">
						<div class="ibox float-e-margins">
							<div class="ibox-title">

								<div class="ibox-tools">

									<a class="close-link" href="${ctx}"> <i class="fa fa-times"></i>
									</a>
								</div>
							</div>
							<div class="ibox-content">



								<form:form class="form-horizontal" method="POST" commandName="user" action="${ctx}/configs/save_confs">


									<input type="hidden" class="form-control" name="id"
										value="${configs.configs.id}">

									<div class="col-lg-12">
										<div class="form-group">

											<label class="col-sm-2 control-label">Config Name</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="confTitle"
													value="${configs.configs.confName}" required="required">
											</div>


										</div>

									</div>











									<div class="col-lg-12">
										<div class="form-group">
											<div class="ibox float-e-margins">
												<div>
													<label class="col-sm-2 control-label">active</label>
													<div class="col-sm-4">
														<c:if test="${configs.configs.active == true}">
															<input type="checkbox" name="active" class="i-checks"
																checked="checked">
														</c:if>
														<c:if
															test="${configs.configs.active == false || configs.configs.active == NULL}">
															<input type="checkbox" name="active" class="i-checks">
														</c:if>

													</div>
												</div>
											</div>
										</div>
									</div>








									<div class="form-group">
										<div class="col-sm-4 col-sm-offset-2">
											<a class="btn btn-primary" href="${ctx}/configs">Cancel</a>
											<button class="btn btn-primary " id="saveNews" type="submit">Save</button>
										</div>
									</div>

								</form:form>
							</div>










						</div>
					</div>
				</div>
			</div>
		</div>


	</div>

</body>
</html>
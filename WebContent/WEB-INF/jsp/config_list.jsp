
<%@include file="common.jsp" %>
<html>
<head>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta name="google" content="notranslate" />
    <title>Config</title>

</head>
<body>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<div>


		<div>
			<div class="row border-bottom"></div>
			<div class="row wrapper border-bottom white-bg page-heading">
				<div class="col-lg-5">
					<h2 class="col-xs-6">Config List</h2>
			
				</div>
				<div class="col-lg-2"></div>
			</div>
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
				
							<div class="ibox-content">
						
								<a href="${ctx}/configs/new" style="width: 15%;" class="btn btn-md btn-info">Add Config</a>
								
								
								<div class="table-responsive">
							
									<a href="${ctx}/configs"><i class="fa fa-refresh fa-3x" aria-hidden="true">refresh</i></a>
									<table id="confsTable"
										class="table table-striped table-bordered table-hover dataTables-example">
										<thead>
											<tr>
												<th style="width: 3%;">N</th>
												<th>Config Name</th>
												<th>Create Date</th>
												<th>Active</th>	
	                                            <th>Edit/Remove</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="rec" items="${configs.configs}">
												<tr>
														<td><c:out value="${rec.id}" /></td>
														<td><c:out value="${rec.confName}" /></td>
														<td><fmt:formatDate value="${rec.createDate}" pattern="yyyy-MM-dd HH:mm" /></td>
                                                    <c:if test="${rec.active == true}">    
													     <td><img class="my_table_image" src="${ctx}/styles/img/done.gif"></img></td>
                                                    </c:if>
													<c:if test="${rec.active == false}">    
													     <td><img class="my_table_image" src="${ctx}/styles/img/un.jpg"></img></td>
												     </c:if>
													
														<td>
														<a href="${ctx}/configs/edit/<c:out value="${rec.id}"/>" class="btn btn-sm btn-info">Edit</a>
														<button class="btn btn-sm btn-info removeConf" data-reckey="${rec.id}">Remove</button>
														</td>
												</tr>
											</c:forEach>
										</tbody>
										<tfoot>
										</tfoot>
									</table>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="footer"></div>

		</div>
	</div>


	<script type="text/javascript" src="js/jquery-2.1.1.js"></script>


	<!-- Page-Level Scripts -->
	<script>
		$(document)
				.ready(
						function() {
						
							$(".removeConf")
									.click(
											function(event) {

												var id = $(this).data("reckey");

												if (confirm('Are you sure you want to delete this item?')) {
													var url = $(this).attr(
															'href');

													$('#confsTable').load(url);
												
															$.ajax({
																type : "GET",
																url : "/Config/configs/remove?id="
																		+ id

															});

												
															window.location.reload()
													$('#confsTable').load(url);
												} else {
													console.log("NO");
												}
											});

					

						});
	</script>


</body>
</html>
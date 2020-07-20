<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/home.css" />
<script src="js/home.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-md bg-dark navbar-dark mynavbar">
		<a class="navbar-brand" href="#"> <img
			src="https://i.ibb.co/j8yDHJK/Shoppers-Stop.png" class="companylogo" />
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
	</nav>
	<!-- <img src="https://i.ibb.co/HNBVySz/dropdown.png"/> -->
	<h2>${serverStatus}</h2>
	<div class="clustersection">
		<div class="container">
			<ul id="tabs" class="nav nav-tabs clustertabs" role="tablist">
				<c:choose>
					<c:when test="${!empty cluster_configs}">
						<c:forEach items="${cluster_configs}" var="clusterConfig">
							<li class="nav-item clusteritem"><a
								id="${clusterConfig.id}"
								href="nodes.htm?id=${clusterConfig.id}"
								class="nav-link cluster-link" data-toggle="tab" role="tab">${clusterConfig.name}</a>
								<div class="showingArrow">
									<img src="https://i.ibb.co/HNBVySz/dropdown.png"
										alt="downArrow" />
								</div></li>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<h1 style="color: red; text-align: center;">No Cluster Found</h1>
					</c:otherwise>
				</c:choose>
			</ul>
			<div id="content" class="tab-content clustermain-content"
				role="tablist">
				<div id="clustermainpane-A"
					class="card clustercard tab-pane fade show active" role="tabpanel"
					aria-labelledby="clustertab-A">
					<div class="card-header cluster-header" role="tab"
						id="clusterheading-A">
						<h5 class="mb-0">
							<a data-toggle="collapse" href="#clustercollapse-A"
								aria-expanded="true" aria-controls="clustercollapse-A">
								Cluster1 </a>
						</h5>
					</div>
					<div class="editmainsection">
						<ul class="editsubsection">
							<li><a href="#" class="editingproperties"
								data-toggle="modal" data-target="#myModal1">Edit Properties</a></li>
						</ul>
					</div>
					<div id="clustercollapse-A" class="collapse show"
						data-parent="#content" role="tabpanel"
						aria-labelledby="clusterheading-A">
						<div class="card-body cluster-body">
							<ul id="tabs" class="nav nav-tabs innertabs" role="tablist">
								<li class="nav-item inneritem"><a id="tinnertab-A"
									href="#innerpane-A" class="nav-link innerlink active"
									data-toggle="tab" role="tab">Nodes</a></li>
							</ul>
							<div id="innercontent" class="tab-content innertabcontent"
								role="tablist">
								<div id="innerpane-A"
									class="card innercard tab-pane fade show active"
									role="tabpanel" aria-labelledby="innertab-A">
									<div class="card-header innercardheader" role="tab"
										id="innerheading-A">
										<h5 class="mb-0">

											<a data-toggle="collapse" href="#innercollapse-A"
												aria-expanded="true" aria-controls="innercollapse-A">
												Nodes </a>
										</h5>
									</div>
									
									
									<div id="innercollapse-A" class="collapse show"
										data-parent="#innercontent" role="tabpanel"
										aria-labelledby="innerheading-A">
										<div class="card-body innercardbody">
											<c:choose>
												<c:when test="${!empty node_configs}">
													<table class="table table-dark table-hover">
														<thead>
															<tr class="tableheadingssection">
																<th>Nodes</th>
																<th>IP</th>
																<th>Configuration</th>
																<th></th>
																<th></th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${node_configs}" var="nodeConfig">

																<tr class="tableheadings">
																	<td>${nodeConfig.id}</td>
																	<td>${nodeConfig.ip}</td>
																	<td>${nodeConfig.config}</td>
																	<td>
																		<button id="${nodeConfig.id}"
																			class="btn btn-danger editbutton1"
																			data-toggle="modal" data-target="#myModal1">Edit</button>
																	</td>
																	<td><span class="e3dittable "><i id="${nodeConfig.id}" class="fa fa-spinner fa-spin propertyloader" style="display:none;"></i></span></td>
																	<td><span class="edittable"><i
																			id="${nodeConfig.id}" class="fa edittick edittick1">&#xf00c;</i></span></td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</c:when>
												<c:otherwise>
													<h3 style="color: red; text-align: center;">Select a
														cluster to get the nodes or No Nodes Found</h3>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
							
							</div>
						</div>
					</div>
				</div>
			
				
			
			</div>
		</div>
	</div>
	<div class="modal" id="myModal1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Properties</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<div class="container">
						<ul id="tabs" class="nav nav-tabs modaltabs" role="tablist">
							<li class="nav-item modal-item"><a id="" href="#modalpane-A"
								class="nav-link modal-link active modeltabval" data-toggle="tab"
								role="tab">ADD</a></li>
							<li class="nav-item modal-item"><a id="" href="#modalpane-B"
								class="nav-link modal-link modeltabval" data-toggle="tab"
								role="tab">UPDATE</a></li>
						</ul>

						<div id="content modalcontent" class="tab-content modal-content"
							role="tablist">
							<div id="modalpane-A"
								class="card modalcard tab-pane fade show active" role="tabpanel"
								aria-labelledby="modal-A">
								<div class="card-header modalcardheader" role="tab"
									id="modalheading-A">
									<h5 class="mb-0">

										<a data-toggle="collapse" href="#modalcollapse-A"
											aria-expanded="true" aria-controls="modalcollapse-A"> 
											<p class="addedproperty"></p>                        
										</a>
									</h5>
								</div>

								<div id="modalcollapse-A" class="collapse show"
									data-parent="#modalcontent" role="tabpanel"
									aria-labelledby="modalheading-A">
									<div class="card-body modalcardbody" id="add_property">
										<!-- ADD PROPERTY HERE -->
										<p class="addedproperty"></p>
										
									</div>
								</div>
							</div>
							<div id="modalpane-B" class="card modalcard tab-pane fade"
								role="tabpanel" aria-labelledby="modaltab-B">
								<div class="card-header modalcardheader" role="tab"
									id="modalheading-B">
									<h5 class="mb-0">
										<a class="collapsed" data-toggle="collapse"
											href="#modalcollapse-B" aria-expanded="false"
											aria-controls="modalcollapse-B">
											<p class="addedproperty"></p> 
										</a>
									</h5>
								</div>
								<div id="modalcollapse-B" class="collapse"
									data-parent="#modalcontent" role="tabpanel"
									aria-labelledby="modelheading-B">
									<div id="formcontainerdiv" class="card-body modelcardbody">
										<form class="form-horizontal" id="searchForm">
											<div
												class="col-lg-12 col-md-12 col-sm-12 col-xs-12 spaceremove">
												<div class="row">
													<div
														class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-6 spaceinnerremove">
														<div
															class="col-lg-12 col-md-12 col-sm-12 col-xs-12 spaceremove">
															<div class="input-group">
																<input type="text" id="searchinput" class="form-control"
																	placeholder="Add Value"> <span
																	class="input-group-btn"> <input type="submit"
																	name="search" id="searchsubmit" class="btn btn-primary"
																	value="Search"><span
																	class="glyphicon glyphicon-search"></span>
																</span>
															</div>
														</div>
													</div>
												</div>
											</div>
										</form>
										<p class="addedproperty" id="addedproperty"></p>
										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					
				</div>
			</div>
		</div>
	</div>
	<span id="ctx" style="display:none;"><%=request.getContextPath()%></span>
	
	<span id="nodeidstore" style="display: none;"></span>
	<script>
		var arr = [];
		$(".cluster-link").click(function() {
			setTimeout(tdprint, 100)
			//tdprint();
		});
		function tdprint() {

			var tds = document.getElementsByClassName("tableheadings");
			for (var i = 0; i < tds.length; i++) {
				arr.push(tds[i].getElementsByTagName("td")[0].textContent);
				console.log(tds[i].getElementsByTagName("td")[0].textContent);
			}
		}
	</script>
</body>
</html>
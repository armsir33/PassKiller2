<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layouts/taglib.jsp"%>

<br />
<div class="container">

	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<li id="credential-tab" role="presentation"><a
			href="#credentials" aria-controls="credentials" role="tab"
			data-toggle="tab">Credentials</a></li>
		<li id="user-tab" role="presentation"><a href="#users"
			aria-controls="users" role="tab" data-toggle="tab">users</a></li>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content">
		<div role="tabpanel" class="tab-pane active" id="users">
			<jsp:directive.include file="user-detail.jsp" />
		</div>

		<div role="tabpanel" class="tab-pane" id="credentials">
			<form:form method="POST" modelAttribute="searchTermBackingBean" >
				<div class="input-group col-xs-3 pull-right">
					<form:input id="searchTerm" class="form-control" path="searchTerm" />
					<div class="input-group-btn">
						<div class="dropdown">
							<button class="btn btn-primary dropdown-toggle" type="button"
								id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="true">
								SearchBy <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
								<li><a id="searchByCompany" href="/account/credential/searchByCompany">Search by company</a></li>
								<li><a id="searchByEnv" href="/account/credential/searchByEnv">Search by environment</a></li>
								<li><a id="searchByDesc" href="/account/credential/searchByDesc">Search by description</a></li>
								<li><a id="searchByModifier" href="/account/credential/searchByModifier">Search by modifier</a></li>
							</ul>
						</div>
					</div>
				</div>
			</form:form>

			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>Company</th>
						<th>Environment</th>
						<th>Username</th>
						<th>Password</th>
						<th>Description</th>
						<th>Modifier</th>
						<th>Date</th>
						<th>Range</th>
						<th>Operations</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${credentials}" var="credential">
						<tr>
							<td>${credential.company}</td>
							<td>${credential.environment}</td>
							<td>${credential.name}</td>
							<td>${credential.password}</td>
							<td>${credential.description}</td>
							<td>${credential.modifier}</td>
							<td>${credential.date}</td>
							<td>${credential.range}</td>
							<td><a href="/account/credential-edit/${credential.id}.html"
								class="btn btn-info">Edit</a> <a
								href="/account/credential-remove/${credential.id}.html"
								class="btn btn-danger triggerRemove">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<nav>
				<ul class="pager">
					<c:if test="${pageNo > 1}">
						<li><a href="/account/credential/searchByDesc/${desc}/${pageNo - 1}">Previous</a></li>
					</c:if>
					<c:if test="${pageNo < pageMax }">
						<li><a href="/account/credential/searchByDesc/${desc}/${pageNo + 1}">Next</a></li>
					</c:if>
				</ul>
			</nav>

			<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Remove article</h4>
						</div>
						<div class="modal-body">Really remove?</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Cancel</button>
							<a href="" class="btn btn-danger removeBtn">Remove</a>
						</div>
					</div>
				</div>
			</div>

			<a class="btn btn-primary btn-lg" href="/account">Return</a>
		</div>

	</div>


	<script type="text/javascript">
		$(document).ready(
				function() {
					$('.nav-tabs a:first').tab('show') // Select first tab

					$(".triggerRemove").click(
							function(e) {
								e.preventDefault();
								$("#modalRemove .removeBtn").attr("href",
										$(this).attr("href"));
								$("#modalRemove").modal();
							});
					
					$("#searchByCompany").click(
							function() {
								var _href = $(this).attr("href"); 
								var searchTerm = $("#searchTerm").val();
								if(searchTerm != "undefined" && searchTerm != null && searchTerm != "") {
									$(this).attr("href", _href + "/" + searchTerm);
								} else {
									$(this).attr("href", "/account/credential/searchAll");
								}
							});
					
					$("#searchByEnv").click(
							function() {
								var _href = $(this).attr("href"); 
								var searchTerm = $("#searchTerm").val();
								if(searchTerm != "undefined" && searchTerm != null && searchTerm != "") {
									$(this).attr("href", _href + "/" + searchTerm);
								} else {
									$(this).attr("href", "/account/credential/searchAll");
								}
							});
					
					$("#searchByDesc").click(
							function() {
								var _href = $(this).attr("href"); 
								var searchTerm = $("#searchTerm").val();
								if(searchTerm != "undefined" && searchTerm != null && searchTerm != "") {
									$(this).attr("href", _href + "/" + searchTerm);
								} else {
									$(this).attr("href", "/account/credential/searchAll");
								}
							});
					
					$("#searchByModifier").click(
							function() {
								var _href = $(this).attr("href"); 
								var searchTerm = $("#searchTerm").val();
								if(searchTerm != "undefined" && searchTerm != null && searchTerm != "") {
									$(this).attr("href", _href + "/" + searchTerm);
								} else {
									$(this).attr("href", "/account/credential/searchAll");
								}
							});

				});
	</script>
</div>
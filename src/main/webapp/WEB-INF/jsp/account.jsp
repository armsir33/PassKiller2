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
			<form:form commandName="credential" cssClass="form-horizontal">
				<!-- Modal -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">New Credential</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label for="name" class="col-sm-2 control-label">Company:</label>
									<div class="col-sm-10">
										<form:input path="company" cssClass="form-control" />
									</div>
								</div>

								<div class="form-group">
									<label for="name" class="col-sm-2 control-label">Environment:</label>
									<div class="col-sm-10">
										<form:input path="environment" cssClass="form-control" />
									</div>
								</div>

								<div class="form-group">
									<label for="name" class="col-sm-2 control-label">Username:</label>
									<div class="col-sm-10">
										<form:input path="name" cssClass="form-control" />
									</div>
								</div>

								<div class="form-group">
									<label for="password" class="col-sm-2 control-label">Password:</label>
									<div class="col-sm-10">
										<form:input path="password" cssClass="form-control" />
									</div>
								</div>

								<div class="form-group">
									<label for="description" class="col-sm-2 control-label">Description:</label>
									<div class="col-sm-10">
										<form:input path="description" cssClass="form-control" />
									</div>
								</div>

								<div class="form-group">
									<label for="name" class="col-sm-2 control-label">Range:</label>
									<div class="col-sm-10">
										<form:select path="range" cssClass="form-control">
											<form:option value="Public" />
											<form:option value="Private" />
										</form:select>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
								<input type="submit" class="btn btn-primary" value="Add">
							</div>
						</div>
					</div>
				</div>
			</form:form>

			<form:form method="POST" modelAttribute="searchTermBackingBean"
				action="/account/credential/search">
				<div class="input-group col-xs-3 pull-right">
					<form:input class="form-control" path="searchTerm" />
					<div class="input-group-btn">
						<input id="trigger-credential-tab" type="submit"
							class="btn btn-primary" value="Search" />
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
						<li><a href="/account/${pageNo - 1}.html">Previous</a></li>
					</c:if>
					<c:if test="${pageNo < pageMax }">
						<li><a href="/account/${pageNo + 1}.html">Next</a></li>
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

			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary btn-lg"
				data-toggle="modal" data-target="#myModal">Add Credential</button>
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

				});
	</script>
</div>
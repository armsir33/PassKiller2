<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layouts/taglib.jsp"%>

<style>
/* Space out content a bit */
body {
	padding-top: 50px;
	padding-bottom: 20px;
}

/* Everything but the jumbotron gets side spacing for mobile first views */
.header, .marketing, .footer {
	padding-right: 15px;
	padding-left: 15px;
}

/* Custom page header */
.header {
	border-bottom: 1px solid #e5e5e5;
}
/* Make the masthead heading the same height as the navigation */
.header h3 {
	padding-bottom: 19px;
	margin-top: 0;
	margin-bottom: 0;
	line-height: 40px;
}
</style>

<div class="container">

	<form:form commandName="credential" cssClass="form-horizontal"
		method="POST">
		<div class="form-group">
			<label for="company" class="col-sm-2 control-label">Company:</label>
			<div class="col-sm-10">
				<form:input path="company" cssClass="form-control" />
			</div>
		</div>

		<div class="form-group">
			<label for="environment" class="col-sm-2 control-label">Environment:</label>
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

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="button" class="btn btn-default" onclick="goBack()">Go
					Back</button>
				<input type="submit" class="btn btn-primary" value="Save">
			</div>
		</div>
	</form:form>
</div>

<script>
	function goBack() {
		window.history.back();
	}
</script>
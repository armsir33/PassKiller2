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

	<form:form commandName="contact" cssClass="form-horizontal"
		method="POST">
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Name:</label>
			<div class="col-sm-10">
				<form:input path="name" cssClass="form-control" readonly="true" />
			</div>
		</div>

		<div class="form-group">
			<label for="email" class="col-sm-2 control-label">Email:</label>
			<div class="col-sm-10">
				<form:input path="email" cssClass="form-control" readonly="true" />
			</div>
		</div>

		<div class="form-group">
			<label for="message" class="col-sm-2 control-label">Message:</label>
			<div class="col-sm-10">
				<form:textarea path="message" rows="5" cssClass="form-control" readonly="true" />
			</div>
		</div>

		<div class="form-group">
			<label for="status" class="col-sm-2 control-label">Status:</label>
			<div class="col-sm-10">
				<form:select path="status" cssClass="form-control">
					<form:option value="Unresolved" />
					<form:option value="Resolved" />
					<form:option value="Pending" />
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
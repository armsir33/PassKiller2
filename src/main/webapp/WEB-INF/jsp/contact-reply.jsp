<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- <%@taglib prefix="botDetect" uri="botDetect"%> --%>
<%@page import="botdetect.web.Captcha"%>

<%@ include file="../layouts/taglib.jsp"%>

<style>
i.red:before {
	color: red;
}
</style>

<div class="container">
	<div class="row">
		<div class="col-md-12">
			<c:if test="${param.success eq true}">
				<div class="alert alert-success">
					<strong><span class="glyphicon glyphicon-send"></span>
						Success! Message sent.</strong>
				</div>
			</c:if>
			<c:if test="${param.failed eq true}">
				<div class="alert alert-danger">
					<span class="glyphicon glyphicon-alert"></span><strong>
						Error! Please check the inputs.</strong>
				</div>
			</c:if>
		</div>
		<form:form id="contactReplyForm" commandName="contact"
			cssClass="form-horizontal">
			<div class="col-lg-6">
				<div class="form-group">
					<label for="name">Your Name</label>
					<div class="input-group">
						<form:input path="name" cssClass="form-control"
							placeholder="Enter Name" required="required" readonly="true" />
						<span class="input-group-addon"></span>

					</div>
					<form:errors path="name" cssClass="alert alert-danger error" />
				</div>
				<div class="form-group">
					<label for="email">Your Email</label>
					<div class="input-group">
						<form:input path="email" cssClass="form-control"
							placeholder="Enter Email" required="required" readonly="true" />
						<span class="input-group-addon"></span>
					</div>
					<form:errors path="email" cssClass="alert alert-danger error" />
				</div>
				<div class="form-group">
					<label for="message">Message</label>
					<div class="input-group">
						<form:textarea path="message" cssClass="form-control" rows="5"
							required="required" />
						<span class="input-group-addon"><i
							class="glyphicon glyphicon-asterisk form-control-feedback red"></i></span>
					</div>
				</div>

				<div class="form-group">
					<a href="/admin/browse-contacts" class="btn btn-default">Go Back</a>
					<input type="submit" name="submit" id="submit" value="Reply"
						class="btn btn-primary">
				</div>
			</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#contactReplyForm")
								.validate(
										{
											rules : {
												message : {
													required : true,
													minlength : 5
												}
											},
											messages : {
												message : {
													required : "<span class='alert alert-danger error'>Please provide messages</span>",
													minlength : "<span class='alert alert-danger error'>Your message must be at least 5 characters long</span>"
												}
											}
										}

								);

					});
</script>
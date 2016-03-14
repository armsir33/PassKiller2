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
		<form:form id="contactForm" commandName="contact"
			cssClass="form-horizontal">
			<div class="col-lg-6">
				<div class="form-group">
					<label for="name">Your Name</label>
					<div class="input-group">
						<form:input path="name" cssClass="form-control"
							placeholder="Enter Name" required="required" />
						<span class="input-group-addon"><i
							class="glyphicon glyphicon-asterisk form-control-feedback red"></i></span>

					</div>
					<form:errors path="name" cssClass="alert alert-danger error" />
				</div>
				<div class="form-group">
					<label for="email">Your Email</label>
					<div class="input-group">
						<form:input path="email" cssClass="form-control"
							placeholder="Enter Email" required="required" />
						<span class="input-group-addon"><i
							class="glyphicon glyphicon-asterisk form-control-feedback red"></i></span>
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
					<div class="input-group">
						<%-- <botDetect:captcha id="captcha" /> --%>
						<%
							// Adding BotDetect Captcha to the page
								Captcha captcha = Captcha.load(request, "captchaCodeTextBox");
								captcha.renderCaptchaMarkup(pageContext.getServletContext(), pageContext.getOut());
						%>
						<form:input id="captchaCodeTextBox" path="status" />
						<c:if test="${param.failed eq true}">
							<div class="alert alert-danger">
								<span class="glyphicon glyphicon-alert"></span>Captcha Code
								Wrong
							</div>
						</c:if>
					</div>
				</div>
				<input type="submit" name="submit" id="submit" value="Submit"
					class="btn btn-primary">
			</div>
		</form:form>
		<hr class="featurette-divider hidden-lg">
		<div class="col-lg-5 col-md-push-1">
			<address>
				<h3>Office Location</h3>
				<p class="lead">
					<a
						href="https://www.google.se/maps/place/Karlav%C3%A4gen+112,+115+26+Stockholm/@59.3352769,18.0980599,17z/data=!3m1!4b1!4m2!3m1!1s0x465f9d4d0d34f269:0x97a329e7d7dcc6a9">Midport
						Scandinavia AB<br> Karlavägen 112, Stockholm Sweden
					</a><br /> <br /> Switchboard: <br /> +46 (0)8 56 29 99 10
				</p>
			</address>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#contactForm")
								.validate(
										{
											rules : {
												name : {
													required : true,
													minlength : 3
												},
												email : {
													required : true,
													email : true
												},
												message : {
													required : true,
													minlength : 5
												}
											},
											messages : {
												name : {
													required : "<span class='alert alert-danger error'>Please provide a name</span>",
													minlength : "<span class='alert alert-danger error'>Your name must be at least 3 characters long</span>"
												},
												email : "<span class='alert alert-danger error'>Please enter a valid email address</span>",
												message : {
													required : "<span class='alert alert-danger error'>Please provide messages</span>",
													minlength : "<span class='alert alert-danger error'>Your message must be at least 5 characters long</span>"
												}
											}
										}

								);

					});
</script>
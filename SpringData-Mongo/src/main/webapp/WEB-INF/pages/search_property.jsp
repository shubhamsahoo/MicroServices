<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
	$(".searchupdate").click(function() {
		$("#myForm11").submit(function(e) {
			e.preventDefault();
			var formEl = $(this);
			var submitButton = $('input[type=submit]', formEl);
			var random = null;
			$.ajax({
				type : "POST",
				url :"/search_property.htm",
				accept : {
					javascript : 'application/javascript'
				},
				data : formEl.serialize(), // serializes the form's elements.
				beforeSend : function() {
					submitButton.prop('disabled', 'disabled');
				},
				success : function(data) {
					$("#myModal1").modal('hide');
					$(".modal-backdrop").remove();
					("#myForm11")[0].reset();
					alert("property updated successfully on all nodes");
				}
			});
		});
	});
	function urlpathfunc() {
		var base = document.getElementsByTagName('base')[0];
		if (base && base.href && (base.href.length > 0)) {
			base = base.href;
		} else {
			base = document.URL;
		}
		return base.substr(0, base.indexOf("/", base.indexOf("/", base
				.indexOf("//") + 2) + 1));
	};
</script>
<c:choose>
	<c:when test="${!empty property}">
		<c:forEach var="entry" items="${property}">
			<form:form class="form-horizontal" id="myForm11"
				modelAttribute="nodeCommand">
				<div
					class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 removepaddding">

					<div class="row"
						style="border-bottom: 1px solid grey; background-color: #f1f1f1;">
						<div
							class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12 removepaddding">
							<div class="updatedsitetext">
								<p class="updatedtext" style="color: black;">
									<form:input path="key" value="${entry.key}" />
								</p>
							</div>
						</div>

						<div
							class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12 removepaddding">
							<div class="updateInput">
								<form:input path="value" class="form-control"
									value="${entry.value}" />
							</div>
						</div>
					</div>
					<span class="input-group-btn"> <input type="submit"
						name="search" id="searchupdate"
						class="btn btn-primary searchupdate" value="Update"><span
						class="glyphicon glyphicon-search"></span>
					</span>


				</div>
			</form:form>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<h3>Property not found</h3>
	</c:otherwise>
</c:choose>
<h1>${result}</h1>
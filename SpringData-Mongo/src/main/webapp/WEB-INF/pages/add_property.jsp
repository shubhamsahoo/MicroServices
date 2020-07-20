<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
	$("#editpropertyform").submit(function(e) {
		var idnode = document.getElementById("nodeidstore").innerText;
		e.preventDefault();
		var formEl = $(this);
		var submitButton = $('input[type=submit]', formEl);
		var random = null;
		$('.propertyloader').each(function() {
			if (idnode == $(this).attr("id")) {
				$(this).css("display", "block");
			}
		});
		
		
		$.ajax({
			type : "POST",
			url : urlpathfunc() + "/add_property.htm",
			accept : {
				javascript : 'application/javascript'
			},
			data : formEl.serialize(), // serializes the form's elements.
			beforeSend : function() {
				submitButton.prop('disabled', 'disabled');
			},
			success : function(data) {
				//var idnode = document.getElementById("nodeidstore").innerText;
				$("#myModal1").modal('hide');
				$(".modal-backdrop").remove();
				$("#editpropertyform").remove();
                 
				$('.propertyloader').each(function() {
					if (idnode == $(this).attr("id")) {
						$(this).css("display", "none");
					}
				});
				
				$('.edittick').each(function() {
					if (idnode == $(this).attr("id")) {
						$(this).css("color", "green");
					}
				});
				
				console.log("balle balle");
               console.log(arr);
				for (var i = 0; i < arr.length; i++) {
					console.log(idnode);
					console.log(arr[i]);
					if (arr[i] !== idnode) {
						console.log(arr[i] + " value");
						recursiveCall(arr[i]);
					}
				}
			}
		});
	});

	function recursiveCall(nodeval) {
		console.log(nodeval);
		
		$('.propertyloader').each(function() {
			if (nodeval == $(this).attr("id")) {
				$(this).css("display", "block");
			}
		});
		$.ajax({
			url :urlpathfunc() + "/ajaxrecursive?nodeval=" + nodeval,
			type : 'POST',
			success : function(data) {
				console.log(nodeval + "updated");
				
				$('.propertyloader').each(function() {
					if (nodeval == $(this).attr("id")) {
						$(this).css("display", "none");
					}
				});
				

				$('.edittick').each(function() {
					if (nodeval == $(this).attr("id")) {
						$(this).css("color", "green");

					}
				});
			},
			error : function() {
				alert("error");
			}
		});
	}

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
</head>
<body>
	<form:form id="editpropertyform" class="form-horizontal"
		modelAttribute="nodeCommand">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 spaceremove">
			<div class="row">
				<div
					class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-6 spaceinnerremove">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 spaceremove">
						<div class="input-group">
							<form:input path="key" class="form-control" placeholder="Add Key" />
						</div>
					</div>
				</div>
				<div
					class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-6 spaceinnerremove">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 spaceremove">
						<div class="input-group">
							<form:input path="value" class="form-control"
								placeholder="Add Value" />
							<span class="input-group-btn"> <input type="submit"
								class="btn btn-primary" value="ADD"><span
								class="glyphicon glyphicon-search"></span>
							</span>
						</div>
					</div>
				</div>

			</div>
		</div>
	</form:form>
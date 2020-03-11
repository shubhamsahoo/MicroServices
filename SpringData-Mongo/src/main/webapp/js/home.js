var nodeid =null;
$(document).ready(function() {
	
	var add = document.getElementsByClassName("modeltabval")[0];
	var update = document.getElementsByClassName("modeltabval")[1];
	
	$(".cluster-link").click(function() {
		   
		  var clusterid = $(this).attr("id");
		  $.ajax({
	          url:"nodes.htm?id="+clusterid,
	          success: function(data){
	        	  	$('#clustercollapse-A').empty();
	        	 $('#clustercollapse-A').append($(data).find("#clustercollapse-A").html());
	        	
	              }
		   });
		  
		//$(location).attr("href", $(".active").attr("href"));
	});
	
	
	 $("body").on("click",".editbutton1", function(){
		
		 document.getElementById("nodeidstore").innerHTML=$(this).attr("id");
		nodeid = $(this).attr("id");
		console.log(nodeid);
		$(".modeltabval").attr("id",$(this).attr("id"));
	  });

	/*$(".editbutton1").click(function(){
		alert("clicked");
		 document.getElementById("nodeidstore").innerHTML=$(this).attr("id");
		nodeid = $(this).attr("id");
		console.log(nodeid);
		$(".modeltabval").attr("id",$(this).attr("id"));
	});*/
	
	$(add).click(function(){
		var base = urlpathfunc();
		console.log(base);
		$.ajax({
	          url: base+"/add_property.htm?id="+nodeid,
	          success: function(data){
	        	  	$('#add_property').empty();
	                $('#add_property').append(data);
	              }
		   });
	});
	$("#searchsubmit").click(function(){
		$("#searchForm").submit(function(e){
			var updateidval = $(update).attr("id");
			var inputVal = document.getElementById("searchinput").value;
			e.preventDefault();
			 var formEl = $(this);
			    var submitButton = $('input[type=submit]', formEl);
			 $.ajax({
		         type: "GET",
		         url: "/search_property.htm?id="+updateidval+"&searchstring="+inputVal,
		         accept: {
		             javascript: 'application/javascript'
		           },
		         data: formEl.serialize(), // serializes the form's elements.
		         beforeSend: function() {
		             submitButton.prop('disabled', 'disabled');
		           },
		         success: function(data)
		         {
		        	  $('#addedproperty').append(data);
		         } 
				 });
		       });
	});
	$(".close").click(function() {
		location.reload(true);
	});
	$(".btn-success").click(function() {
		location.reload(true);
	});
});

function urlpathfunc() {
    var base = document.getElementsByTagName('base')[0];
    if (base && base.href && (base.href.length > 0)) {
        base = base.href;
    } else {
        base = document.URL;
    }
    return base.substr(0,
        base.indexOf("/", base.indexOf("/", base.indexOf("//") + 2) + 1));
};
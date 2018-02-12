$(document).ready(function() {
  
	$.ajax({
		url: window.location.protocol + "//" + window.location.host +"/apis/allpersons"
	}).then(function(data){
		
		for(let value of data){
			console.log(value);
			$("#allpersons").append("<tr><td><a href='/persons?person="+value.username+"'>"+value.username+"</a></td><td>"+value.mobile+"</td></tr>");
			
		}
	});
	
});
$(document).ready(function() {
  
	$.ajax({
		url: "http://localhost:8080/apis/allpersons"
	}).then(function(data){
		
		for(let value of data){
			console.log(value);
			$("#allpersons").append("<tr><td><a href='/persons?person="+value.personName+"'>"+value.personName+"</a></td><td>"+value.mobile+"</td></tr>");
			
		}
	});
	
});
$(document).ready(function() {
  
	$.ajax({
		url: "https://thepubliclibrary.herokuapp.com/apis/allpersons"
	}).then(function(data){
		
		for(let value of data){
			console.log(value);
			$("#allpersons").append("<br>");
			$("#allpersons").append("<a href='/persons?person="+value.personName+"'>"+value.personName+"</a>: "+value.mobile);
			
		}
	});
	
});
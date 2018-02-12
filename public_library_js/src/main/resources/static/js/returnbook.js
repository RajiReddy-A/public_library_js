$(document).ready(function() {
  
	var username = $("#username").text();
	
	$.ajax({
		url: window.location.protocol + "//" + window.location.host +"/apis/persons/"+username
	}).then(function(data){
		
		for(let value of data.listOfBooks){
			console.log(value);
			$("#bookName").append("<option value='"+value.bookName+"'>"+value.bookName+"(Author: "+value.author+")</option>");
			
		}
	});
	
});
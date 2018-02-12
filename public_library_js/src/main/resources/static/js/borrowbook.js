$(document).ready(function() {
  
	$.ajax({
		url: window.location.protocol + "//" + window.location.host +"/apis/allbooks"
	}).then(function(data){
		
		for(let value of data){
			console.log(value);
			$("#bookName").append("<option value='"+value.bookName+"'>"+value.bookName+"(Author: "+value.author+")</option>");
			
		}
	});
	
});
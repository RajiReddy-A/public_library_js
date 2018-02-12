$(document).ready(function() {
  
	$.ajax({
		url: "http://localhost:8080/apis/allbooks"
	}).then(function(data){
		
		for(let value of data){
			console.log(value);
			$("#bookName").append("<option value='"+value.bookName+"'>"+value.bookName+"(Author: "+value.author+")</option>");
			
		}
	});
	
});
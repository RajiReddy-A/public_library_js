$(document).ready(function() {
  
	$.ajax({
		url: window.location.protocol + "//" + window.location.host +"/apis/allbooks"
	}).then(function(data){
		
		for(let value of data){
			console.log(value);
			$("#allbooks").append("<tr><td><a href='/books?book="+value.bookName+"'>"+value.bookName+"</a></td><td>"+value.author+"</td><td>"+value.copiesTotal+"</td><td>"+value.copiesAvailable+"</td></tr>");
			
		}
	});
	
});
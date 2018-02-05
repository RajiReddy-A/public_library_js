$(document).ready(function() {
  
	$.ajax({
		url: "https://thepubliclibrary.herokuapp.com/apis/allbooks"
	}).then(function(data){
		
		for(let value of data){
			console.log(value);
			$("#allbooks").append("<br>");
			$("#allbooks").append("<a href='/books?book="+value.bookName+"'>"+value.bookName+"</a>"+" is written by "+value.author+". Total copies: "+value.copiesTotal+", Available copies: "+value.copiesAvailable);
			
		}
	});
	
});
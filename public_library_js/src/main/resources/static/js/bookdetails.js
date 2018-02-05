$(document).ready(function() {
  
	$.urlParam = function(name){
		var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
		return results[1] || 0;
	}
	
	var theBook = $.urlParam('book');
	theBook = decodeURIComponent(theBook);
	console.log(theBook);
	
	var theUrl = "https://thepubliclibrary.herokuapp.com/apis/books/"+theBook;
	
	$.ajax({
		url: theUrl
	}).then(function(data){
		
		$("#bookdetails").append("Book: "+data.bookName+"<br>");
		$("#bookdetails").append("Author: "+data.author+"<br>");
		$("#bookdetails").append("Total copies: "+data.copiesTotal+"<br>");
		$("#bookdetails").append("Available copies: "+data.copiesAvailable+"<br><br>");
		$("#bookdetails").append("<b>BORROWED BY</b><br>");
		
		for(let value of data.listOfPersons){
			
			$("#bookdetails").append("<a href='/persons?person="+value.personName+"'>"+value.personName+"</a><br>");
			
		}
		
	});
	
});
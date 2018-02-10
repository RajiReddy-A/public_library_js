$(document).ready(function() {
  
	$.urlParam = function(name){
		var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
		return results[1] || 0;
	}
	
	var theBook = $.urlParam('book');
	theBook = decodeURIComponent(theBook);
	console.log(theBook);
	
	var theUrl = "http://localhost:8080/apis/books/"+theBook;
	
	$.ajax({
		url: theUrl
	}).then(function(data){
		
		$("#bookName").append(data.bookName);
		$("#author").append("Author: "+data.author);
		$("#totalCopies").append("Total copies: "+data.copiesTotal);
		$("#availableCopies").append("Available copies: "+data.copiesAvailable);
		
		var i=0;
		for(let value of data.listOfPersons){
			
			$("#personList").append("<th scope='row'>"+i+"</th><tr><td><a href='/persons?person="+value.username+"'>"+value.username+"</a></td><td>"+value.mobile+"</td></tr>");
			i=i+1;
			
		}
		
	});
	
});
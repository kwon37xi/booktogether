
function go_page_search(pageno){
	
	document.searchBookform.pageno.value=pageno;
	document.searchBookform.submit();
	
}

function checkBook(isbn){
	location.href='/book/checkBook.do?ISBN='+isbn;
}
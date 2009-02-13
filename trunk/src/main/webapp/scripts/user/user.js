

function join(){
	location.href='/user/join.do';
}


function deleteZone(zone_id){
	
	location.href="/user/deleteZone.do?zone_id="+zone_id;
	
}

zone_seq=0;

function addZone(){
	
	zone_seq++;
	
	var text_tag=document.createElement("input");
	
	text_tag.setAttribute("type", "text");
	text_tag.setAttribute("name", "zone");
	text_tag.setAttribute("size", "20");
	text_tag.setAttribute("id", "zone"+zone_seq);
	
	
	var button_tag=document.createElement("input");
	button_tag.setAttribute("type", "button");
	button_tag.setAttribute("value", "삭제");
	button_tag.setAttribute("id", "zone"+zone_seq);
	button_tag.setAttribute("onclick", "removeZone('zone"+zone_seq+"')");
	
	$('insertzonesdiv').appendChild(text_tag);
	$('insertzonesdiv').appendChild(button_tag);
	
}


function addThumnail(){
	
	var file_tag=document.createElement("input");
	
	file_tag.setAttribute("type", "file");
	file_tag.setAttribute("name", "thumnail");
	file_tag.setAttribute("size", "20");
	
	var button_tag=document.createElement("input");
	button_tag.setAttribute("type", "button");
	button_tag.setAttribute("value", "삭제");
	button_tag.setAttribute("onclick", "removeThumnail()");
	
	$('modifythumnaildiv').innerHTML="";
	$('modifythumnaildiv').appendChild(file_tag);
	$('modifythumnaildiv').appendChild(button_tag);
	
	
}

function removeZone(ele_id){
	
    Element.remove(ele_id);
    Element.remove(ele_id);
	
}

function removeThumnail(){
	
	$('modifythumnaildiv').innerHTML="";
	
}


function join(){
	location.href='/user/join.do';
}


function deleteZone(zoneIdNum){
	
	location.href="/user/deleteZone.do?zoneIdNum="+zoneIdNum;
	
}

zone_seq=0;

function addZone(){
	
	zone_seq++;
	
	var hidden_tag=document.createElement("input");
	hidden_tag.setAttribute("type", "hidden");
	hidden_tag.setAttribute("name", "zone");
	hidden_tag.setAttribute("id", "h_zone"+zone_seq);
	
	var text_tag=document.createElement("input");
	text_tag.setAttribute("type", "text");
	text_tag.setAttribute("size", "20");
	text_tag.setAttribute("readOnly", "readOnly");
	text_tag.setAttribute("id", "t_zone"+zone_seq);
	text_tag.setAttribute("name", "t_zone"+zone_seq);
	
	
	var find_tag=document.createElement("input");
	find_tag.setAttribute("type", "button");
	find_tag.setAttribute("value", "찾기");
	find_tag.setAttribute("id", "f_zone"+zone_seq);
	find_tag.setAttribute("onclick", "findAddr('"+zone_seq+"')");
	
	
	var button_tag=document.createElement("input");
	button_tag.setAttribute("type", "button");
	button_tag.setAttribute("value", "삭제");
	button_tag.setAttribute("id", "d_zone"+zone_seq);
	button_tag.setAttribute("onclick", "removeZone('"+zone_seq+"')");
	
	
	$('insertzonesdiv').appendChild(hidden_tag);
	$('insertzonesdiv').appendChild(text_tag);
	$('insertzonesdiv').appendChild(find_tag);
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

function removeZone(ele_seq){
	
    Element.remove("t_zone"+ele_seq);
    Element.remove("f_zone"+ele_seq);
    Element.remove("d_zone"+ele_seq);
	
}

function findAddr(ele_seq){
	
	window.open('/user/findAddrView.do?eleSeq='+ele_seq,'주소찾기','');
	
}


function removeThumnail(){
	
	$('modifythumnaildiv').innerHTML="";
	
}


function useId(userId){
	
	if(userId==''){
		alert('아이디가 없습니다.');
	}else{
		opener.document.insertuser_form.dupliId.value="true";
		opener.document.insertuser_form.userId.value=userId;
		self.close();
	}
	
}

function choice_addr(seq, addr){
	
	var ele_seq=document.findAddrform.eleSeq.value;
	
	//eval("opener.document.insertuser_form.t_zone"+ele_seq).value=addr;
	
	opener.$("t_zone"+ele_seq).value=addr;
	opener.$("h_zone"+ele_seq).value=seq;

	self.close();
	
}












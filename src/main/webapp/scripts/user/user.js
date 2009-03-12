
function joinform(){
	location.href='/user/join.do';
}


function deleteZone(zoneIdNum){
	
	location.href="/user/deleteZone.do?zoneIdNum="+zoneIdNum;
	
}

function addThumnail(){
	
	var file_tag=document.createElement("input");
	file_tag.setAttribute("type", "file");
	file_tag.setAttribute("name", "thumnail");
	file_tag.setAttribute("size", "25");
	
	var button_tag=document.createElement("input");
	button_tag.setAttribute("type", "button");
	button_tag.setAttribute("value", "삭제");
	button_tag.setAttribute("onclick", "removeThumnail()");
	
	$('#modifythumnaildiv').empty();
	$('#modifythumnaildiv').append(file_tag);
	$('#modifythumnaildiv').append("&nbsp;");
	$('#modifythumnaildiv').append(button_tag);
	
}

function findAddr(ele_seq){
	
	window.open('/user/findAddrView.do?eleSeq='+ele_seq+"&decorator=popup&confirm=true",'주소찾기','width=300px,height=300px,scrollbars=1');
	
}


function removeThumnail(){
	
	$('#modifythumnaildiv').empty();
	
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
	
	opener.document.getElementById("t_zone"+ele_seq).value=addr;
	opener.document.getElementById("h_zone"+ele_seq).value=seq;

	self.close();
	
}












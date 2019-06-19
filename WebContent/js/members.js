/**
 * 
 */

function infoConfirm(){
	
	if(document.reg_frm.userID.value == 0){
		alert("아이디를 입력해주세요.");
		reg_frm.id.focus();
		return;
	}
	
	if(document.reg_frm.userID.value.length < 4){
		alert("아이디는 4글자 이상이어야 해요.")
		reg_frm.id.focus();
		return;
	}
	
	if(document.reg_frm.userPassword1.value.length == 0){
		alert("비밀번호는 필 수 사항입니다.");
		reg_frm.pw.focus();
		return;
	}
	
	if(document.reg_frm.userPassword1.value != document.reg_frm.userPassword2.value){
		alert("비밀번호가 일치하지 않습니다.");
		reg_frm.pw.focus();
		return;
	}
	
	if(document.reg_frm.userName.value.length == 0) {
		alert("이름은 필수 사항입니다.");
		reg_frm.name.focus();
		return;
	}
	
	if(document.reg_frm.userAge.value.length == 0) {
		alert("이름은 필수 사항입니다.");
		reg_frm.name.focus();
		return;
	}
	
	
	if(document.reg_frm.userEmail.value.length == 0) {
		alert("메일은 필 수 사항입니다.");
		reg_frm.eMail.focus();
		return;
	}
	
	document.reg_frm.submit();
	
}

function updateconfirm(){
	
	if(document.reg_frm.userPassword1.value.length == 0){
		alert("비밀번호는 필 수 사항입니다.");
		reg_frm.pw.focus();
		return;
	}
	
	if(document.reg_frm.userPassword1.value != document.reg_frm.userPassword2.value){
		alert("비밀번호가 일치하지 않습니다.");
		reg_frm.pw.focus();
		return;
	}
	
	if(document.reg_frm.userName.value.length == 0) {
		alert("이름은 필수 사항입니다.");
		reg_frm.name.focus();
		return;
	}
	
	if(document.reg_frm.userAge.value.length == 0) {
		alert("이름은 필수 사항입니다.");
		reg_frm.name.focus();
		return;
	}
	
	
	if(document.reg_frm.userEmail.value.length == 0) {
		alert("메일은 필 수 사항입니다.");
		reg_frm.eMail.focus();
		return;
	}
	
	document.reg_frm.submit();
}
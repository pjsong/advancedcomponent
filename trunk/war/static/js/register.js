﻿function clearRegisterForm(){
	document.getElementById('loginName').value="";
	document.getElementById('password').value="";
	document.getElementById('passwordV').value="";
	document.getElementById('question').value="-1";
	document.getElementById('answer').value="";
	document.getElementById('sex').value="";
	document.getElementById('email').value="";
	document.getElementById('companywebsite').value="";
	document.getElementById('companyname').value="";
	document.getElementById('companyaddress').value="";
	document.getElementById('name').value="";
	document.getElementById('qqNumber').value="";
	document.getElementById('msnNumber').value="";
	document.getElementById('mobile').value="";
	document.getElementById('phoneFax').value="";
}
function updateRegisterForm(){
	if(document.getElementById('update').value=='更新'){
		form1.submit();
	}
	else{
	document.getElementById('loginName').disabled=false;
	document.getElementById('password').disabled=false;
	document.getElementById('passwordV').disabled=false;
	document.getElementById('question').disabled=false;
	document.getElementById('answer').disabled=false;
	document.getElementById('sex').disabled=false;
	document.getElementById('email').disabled=false;
	document.getElementById('companywebsite').disabled=false;
	document.getElementById('companyname').disabled=false;
	document.getElementById('companyaddress').disabled=false;
	document.getElementById('name').disabled=false;
	document.getElementById('qqNumber').disabled=false;
	document.getElementById('msnNumber').disabled=false;
	document.getElementById('mobile').disabled=false;
	document.getElementById('phoneFax').disabled=false;
	document.getElementById('newsletter').disabled=false;
	document.getElementById('update').value='更新';
	}

}
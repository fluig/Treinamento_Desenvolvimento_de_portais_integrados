function validateForm(form){
	var atv = getValue("WKNumState");
	var msg = "";
	
	if(atv==4){
		if(form.getValue("txt_nome").trim()=="") {
			msg += "<br />- Nome"
		}
		if(form.getValue("txt_email").trim()=="") {
			msg += "<br />- E-mail"
		}
		if(form.getValue("txt_feedback").trim()=="") {
			msg += "<br />- Feedback"
		}
	}
	
	if(msg!=""){
		throw "<br />Os seguintes campos são de preenchimento obrigatório:" + msg;
	}
}
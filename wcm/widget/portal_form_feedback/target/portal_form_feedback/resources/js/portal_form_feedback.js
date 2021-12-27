var portalFormFeedback = SuperWidget.extend({
    //variáveis da widget
    variavelNumerica: null,
    variavelCaracter: null,

    //método iniciado quando a widget é carregada
    init: function() {
    },
  
    //BIND de eventos
    bindings: {
        local: {
            'enviarFeedback': ['click_initProcessFeedback']
        },
        global: {}
    },
 
    initProcessFeedback: function() {
    	var nome = $("#txt_nome_"+this.instanceId).val();
    	var email = $("#txt_email_"+this.instanceId).val();
    	var feedback = $("#txt_feedback_"+this.instanceId).val();
    	var url = WCMAPI.getServerURL() + '/portal_form_feedback/api/rest/feedback/enviar';
    	
    	var data = {
			"nome": nome,
			"email": email,
			"feedback": feedback,
			"empresa": 1
		}
    	
    	WCMAPI.Read({
    	    type: "POST",
    	    url: url,
    	    async: true,
    	    data: JSON.stringify(data),
    	    success: function funcao(data) {
    	        console.log(data);
    	        FLUIGC.toast({
    	        	title:"",
    	        	message:"Feedback enviado!",
    	        	type:"success"
    	        });
    	    },
    	    error: function (jqXHR) {
    	    	var erro = jqXHR.responseText.replace("{","").replace("}","");

    	        FLUIGC.toast({
    	        	title:"",
    	        	message: erro,
    	        	type:"danger"
    	        });
    	    }
    	});    	
    }
});


function enviarEmail() {
    /* Documentação de email personalizado
    * https://tdn.totvs.com/pages/releaseview.action?pageId=183730587
    */
   
   try{
       var empresa = fluigAPI.getSecurityService().getCurrentTenantId();
       var server = fluigAPI.getPageService().getServerURL();
       var usuario = hAPI.getCardValue("txt_nome");
       var num_solic = getValue("WKNumProces");
       var template = "TPL_WF_FEEDBACK_PORTAL";    
       var remetente = "luis.rossi";
       var assunto  = "Obrigado pelo seu feedback!";
            
       var parametros = new java.util.HashMap();        
       parametros.put("SERVER_URL", server);
       parametros.put("NOME_SOLICITANTE", usuario);
       parametros.put("NUMERO_SOLIC", num_solic);
       parametros.put("subject", assunto);
       parametros.put("CODIGO_EMPRESA", empresa);

       var destinatarios = new java.util.ArrayList();
       destinatarios.add(hAPI.getCardValue("txt_email"));	    
       
       notifier.notify(remetente, template, parametros, destinatarios, "text/html");
    } catch(e){
       log.error("Erro ao enviar email: " + e);
   }
}

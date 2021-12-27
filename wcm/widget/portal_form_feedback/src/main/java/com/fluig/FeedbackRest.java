package com.fluig.kitintranet.news.proxy.rest;
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import java.io.InputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;

import com.fluig.customappkey.Keyring;
import com.fluig.sdk.api.customappkey.KeyVO;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fluig.sdk.api.social.PostVO;
import com.fluig.sdk.api.FluigAPI;
import com.fluig.sdk.api.common.SDKException;
import com.fluig.sdk.service.PostService;
import com.fluig.sdk.service.UserService;
import com.fluig.sdk.user.UserVO;
import com.fluig.sdk.web.FluigRest;
import com.fluig.RestConstant;
import com.fluig.ErrorStatus;

@Path("/feedback")
public class FeedbackRest extends FluigRest {
 
    @GET
    @Path("/helloWorld")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHelloWorld() {
        try {
        	UserService userservice = new FluigAPI().getUserService();
        	UserVO currentUser = userservice.getCurrent();
        	return super.buildSuccessResponse(currentUser.getFullName());
        } catch(SDKException e){
        	return super.buildSuccessResponse(e);
        }
    }
    
    @POST
    @Path("/enviar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response iniciarProcessoFeedback (String params) throws Exception {
    	try{
    		// Transformar o corpo da requisiÃ§Ã£o em um Objeto JSON
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(params);
            
            // Capturar os parametros enviados a partir do Objeto JSON
            String nome = (String) obj.get("nome"); 
            String email = (String) obj.get("email"); 
            String feedback = (String) obj.get("feedback");
            Long empresa = (Long) obj.get("empresa");
            
            // Definir atividade destino
            String atividadeDestino = "5";

            // Definir usuÃ¡rio destino
            // Atribuição por Pool: https://tdn.totvs.com/pages/releaseview.action?pageId=142804157#Mecanismodeatribui%C3%A7%C3%A3opersonalizado-UtilizandoDatasets
            String usuarioDestino = "Pool:Group:AtendimentoFeedback";

            // Comentario que serÃ¡ inserido no processo ao iniciar a solicitaÃ§Ã£o
            String comentarios = "Iniciado via portal";
                 
            // CÃ³digo do processo que sera iniciado
            String cod_processo = "portal_avaliar_feedback";
        	
            KeyVO key = Keyring.getKeys(empresa, RestConstant.APP_KEY);
        	OAuthConsumer config = config(key);	
        	
            URL url = new URL( new FluigAPI().getPageService().getServerURL() +"/process-management/api/v2/processes/"+cod_processo+"/start");
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            config.sign(conn);            

            String json = 
            "{"
       		+ "\"targetState\": "+atividadeDestino+","
       		+ "\"targetAssignee\": \""+usuarioDestino+"\","
       		+ "\"comment\": \""+comentarios+"\","
       		+ "\"formFields\": "
        		+ "{  "
        			+ "\"txt_nome\": \""+nome+"\",  "
        			+ "\"txt_email\": \""+email+"\",  "
        			+ "\"txt_feedback\": \""+feedback+"\"  "
        		+ "}"
           + "}";
            
            
            System.out.println("JSON ENVIADO: " + json);
            
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(json);
            wr.flush();
            wr.close();
            conn.connect();
            
            InputStream _is;    
                    
            if (conn.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
                _is = conn.getInputStream();
            } else {
                _is = conn.getErrorStream();
            }
            
            Reader inputCreateUser = new BufferedReader(new InputStreamReader(_is, RestConstant.UTF_8_ENCODE));

            String result = "";
            for (int c = inputCreateUser.read(); c != -1; c = inputCreateUser.read()) {
            	result += (char) c;
            }
            
            int code = conn.getResponseCode();
            
            conn.disconnect();
            
    		return Response.status(code).entity(result).build();
    	} catch(Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorStatus(e)).build();
    	}
    }
    
    private OAuthConsumer config(KeyVO key) {
        OAuthConsumer consumer = new DefaultOAuthConsumer(key.getConsumerKey(), key.getConsumerSecret());
        consumer.setTokenWithSecret(key.getToken(), key.getTokenSecret());
        return consumer;
    }
    
    
    
}
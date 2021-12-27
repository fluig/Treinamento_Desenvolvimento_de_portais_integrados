package com.fluig.kitintranet.news.proxy.rest;
 
import com.totvs.technology.wcm.sdk.rest.WCMRest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.fluig.sdk.web.FluigRest;
import com.fluig.sdk.service.PostService;
import com.fluig.sdk.service.UserService;
import com.fluig.sdk.user.UserVO;
import com.fluig.sdk.web.FluigRest;
import com.fluig.sdk.api.common.SDKException;
import com.fluig.sdk.api.FluigAPI;

@Path("/treinamento")
public class TreinamentoRest extends FluigRest {
 
    @GET
    @Path("/helloWorld")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHelloWorld() {
    	return super.buildSuccessResponse("Oi :)");
    }
}
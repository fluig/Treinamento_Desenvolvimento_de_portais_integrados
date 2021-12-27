package com.fluig;
 
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
 
import com.fluig.sdk.api.component.activation.ActivationEvent;
import com.fluig.sdk.api.component.activation.ActivationListener;

import com.fluig.RestConstant;
import com.fluig.customappkey.Keyring;

@Singleton(mappedName = "activator/portal_form_feedback", name = "activator/portal_form_feedback")
public class ActivationClass implements ActivationListener {
 
    /**
     * @return nome do arquivo que será gerado e feito o deploy na central de componentes
     * @throws Exception
     */
    @Override
    public String getArtifactFileName() throws Exception {
        return "portal_form_feedback.war";
    }
 
    /**
     * Evento chamado no momento da instalação (deploy) do artefato dentro da Central de Componentes.
     * @param event
     * @throws Exception
     */
    @Override
    public void install(ActivationEvent event) throws Exception {
 
    }
 
    /**
     * Evento chamado no momento da ativação do artefato dentro da Central de Componentes.
     * @param event
     * @throws Exception
     */
    @Override
    public void enable(ActivationEvent event) throws Exception {
    	Keyring.provision(RestConstant.APP_KEY);
    }
 
    /**
     * Evento chamado no momento da desativação do artefato dentro da Central de Componentes.
     * @param event
     * @throws Exception
     */
    @Override
    public void disable(ActivationEvent event) throws Exception {
 
    }
 
}
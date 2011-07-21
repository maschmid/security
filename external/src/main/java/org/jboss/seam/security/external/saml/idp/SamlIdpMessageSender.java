package org.jboss.seam.security.external.saml.idp;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.seam.security.external.saml.SamlEntityBean;
import org.jboss.seam.security.external.saml.SamlMessageSender;

@ApplicationScoped
public class SamlIdpMessageSender extends SamlMessageSender {

    @Inject SamlIdpBean samlIdpBean;
    
    @Override
    protected SamlEntityBean getSamlEntityBean() {
        return samlIdpBean; 
    }
}

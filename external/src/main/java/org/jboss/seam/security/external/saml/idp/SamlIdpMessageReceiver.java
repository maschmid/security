package org.jboss.seam.security.external.saml.idp;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.seam.security.external.saml.SamlEntityBean;
import org.jboss.seam.security.external.saml.SamlMessageReceiver;

@ApplicationScoped
public class SamlIdpMessageReceiver extends SamlMessageReceiver {

    @Inject SamlIdpBean samlIdpBean;
    
    @Override
    protected SamlEntityBean getSamlEntityBean() {
        return samlIdpBean; 
    }
}

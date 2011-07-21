package org.jboss.seam.security.external.saml.idp;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.seam.security.external.saml.SamlEntityBean;
import org.jboss.seam.security.external.saml.SamlMessageFactory;

@ApplicationScoped
public class SamlIdpMessageFactory extends SamlMessageFactory {

    @Inject SamlIdpBean samlIdpBean;
    
    @Override
    protected SamlEntityBean getSamlEntityBean() {
        return samlIdpBean; 
    }
}

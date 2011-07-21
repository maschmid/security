package org.jboss.seam.security.external.saml.sp;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.seam.security.external.saml.SamlEntityBean;
import org.jboss.seam.security.external.saml.SamlMessageSender;

@ApplicationScoped
public class SamlSpMessageSender extends SamlMessageSender {

    @Inject SamlSpBean samlSpBean;
    
    @Override
    protected SamlEntityBean getSamlEntityBean() {
        return samlSpBean; 
    }
}

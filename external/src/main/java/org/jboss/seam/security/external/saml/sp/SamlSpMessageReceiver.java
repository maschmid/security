package org.jboss.seam.security.external.saml.sp;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.seam.security.external.saml.SamlEntityBean;
import org.jboss.seam.security.external.saml.SamlMessageReceiver;

@ApplicationScoped
public class SamlSpMessageReceiver extends SamlMessageReceiver {

    @Inject SamlSpBean samlSpBean;
    
    @Override
    protected SamlEntityBean getSamlEntityBean() {
        return samlSpBean; 
    }
}

package org.jboss.seam.security.external.saml.sp;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.seam.security.external.saml.SamlEntityBean;
import org.jboss.seam.security.external.saml.SamlMessageFactory;

@ApplicationScoped
public class SamlSpMessageFactory extends SamlMessageFactory {

    @Inject SamlSpBean samlSpBean;
    
    @Override
    protected SamlEntityBean getSamlEntityBean() {
        return samlSpBean; 
    }
}

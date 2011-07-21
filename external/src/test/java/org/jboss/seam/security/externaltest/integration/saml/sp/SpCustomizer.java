package org.jboss.seam.security.externaltest.integration.saml.sp;

import javax.enterprise.event.Observes;
import javax.servlet.ServletContext;

import org.jboss.seam.security.external.saml.api.SamlBinding;
import org.jboss.seam.security.external.saml.api.SamlServiceProviderConfigurationApi;
import org.jboss.seam.security.external.virtualapplications.api.AfterVirtualApplicationCreation;
import org.jboss.seam.security.external.virtualapplications.api.VirtualApplication;
import org.jboss.seam.servlet.event.Initialized;

public class SpCustomizer {
   /* public void customize(@Observes AfterVirtualApplicationCreation event, SamlServiceProviderConfigurationApi sp, VirtualApplication virtualApplication) {
        if (virtualApplication.getHostName().equals("www.sp2.com")) {
            sp.setPreferredBinding(SamlBinding.HTTP_Redirect);
        }
        sp.setSingleLogoutMessagesSigned(false);
        sp.setProtocol("http");
        sp.setPort(8080);
    }*/

    
    public void customize(@Observes @Initialized final ServletContext context, SamlServiceProviderConfigurationApi sp) {
        sp.setEntityId("https://www.sp1.com");
        sp.setHostName("www.sp1.com");
        sp.setSingleLogoutMessagesSigned(false);
        sp.setProtocol("http");
        sp.setPort(8080);
    }
    
}

package org.jboss.seam.security.examples.id_consumer;

import javax.enterprise.event.Observes;
import javax.servlet.ServletContext;

import org.jboss.seam.security.external.openid.api.OpenIdRelyingPartyConfigurationApi;
import org.jboss.seam.servlet.event.Initialized;

public class Customizer {
    public void servletInitialized(@Observes @Initialized final ServletContext context, OpenIdRelyingPartyConfigurationApi rp) {
        rp.setHostName("www.openid-rp.com");
        rp.setProtocol("http");
        rp.setPort(8080);
    }
}


package org.jboss.seam.security.external.saml.idp;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;

import org.jboss.seam.security.external.virtualapplications.api.VirtualApplication;
import org.jboss.seam.security.external.virtualapplications.api.VirtualApplicationScoped;

/**
 * @author Marcel Kolsteren
 */
@Alternative
public class SamlIdpInVirtualApplicationScopeProducer {
    @Produces
    @VirtualApplicationScoped
    public SamlIdpBean produce(@New SamlIdpBean idp, VirtualApplication virtualApplication) {
        String hostName = virtualApplication.getHostName();
        idp.setHostName(hostName);
        idp.setEntityId("https://" + hostName);

        return idp;
    }
}

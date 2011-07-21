package org.jboss.seam.security.externaltest.integration.saml.sp;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.jboss.seam.security.external.saml.sp.SamlSpBean;

@ApplicationScoped
public class SpMetaDataLoader {
    @Inject
    private Instance<SamlSpBean> samlSpBean;

    public void loadMetaDataOfOtherSamlEntity(String hostName, String idpOrSp) {
        URLConnection uc;
        try {
            URL url = new URL("http://localhost:8080/" + idpOrSp + "/saml/" + idpOrSp.toUpperCase() + "/MetaDataService");
            uc = url.openConnection();
            uc.setRequestProperty("host", hostName);
            Reader reader = new InputStreamReader(uc.getInputStream());
            
            samlSpBean.get().addExternalSamlEntity(reader);
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

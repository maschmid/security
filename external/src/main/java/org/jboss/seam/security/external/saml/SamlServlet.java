package org.jboss.seam.security.external.saml;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.jboss.seam.security.external.InvalidRequestException;
import org.jboss.seam.security.external.ResponseHandler;
import org.jboss.seam.security.external.saml.idp.SamlIdpBean;
import org.jboss.seam.security.external.saml.idp.SamlIdpMessageReceiver;
import org.jboss.seam.security.external.saml.sp.SamlSpBean;
import org.jboss.seam.security.external.saml.sp.SamlSpMessageReceiver;

/**
 * @author Marcel Kolsteren
 */
public class SamlServlet extends HttpServlet {
    private static final long serialVersionUID = -6125510783395424719L;

    // TODO: use injection as soon as Jira issue SOLDER-63 has been solved
    // @Inject
    private Logger log = Logger.getLogger(SamlServlet.class);

    @Inject
    private SamlIdpMessageReceiver samlIdpMessageReceiver;
    
    @Inject
    private SamlSpMessageReceiver samlSpMessageReceiver;

    @Inject
    private ResponseHandler responseHandler;

    @Inject
    private Instance<SamlIdpBean> samlIdpBean;
    
    @Inject
    private Instance<SamlSpBean> samlSpBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGetOrPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGetOrPost(request, response);
    }

    private void doGetOrPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            handleMessage(request, response);
        } catch (InvalidRequestException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getDescription());
            log.infof("Bad request received from %s: %s", request.getRemoteHost(), e.getDescription());
        }
    }

    private void handleMessage(HttpServletRequest httpRequest, HttpServletResponse response) throws InvalidRequestException {
        Matcher matcher = Pattern.compile("/(IDP|SP)/(.*?)$").matcher(httpRequest.getRequestURI());
        boolean found = matcher.find();
        if (!found) {
            responseHandler.sendError(HttpServletResponse.SC_NOT_FOUND, "No service endpoint exists for this URL.", response);
        }
        SamlIdpOrSp idpOrSp = SamlIdpOrSp.valueOf(matcher.group(1));
        SamlServiceType service = SamlServiceType.getByName(matcher.group(2));

        switch (service) {
            case SAML_SINGLE_LOGOUT_SERVICE:
            case SAML_SINGLE_SIGN_ON_SERVICE:
            case SAML_ASSERTION_CONSUMER_SERVICE:
                if (SamlIdpOrSp.IDP.equals(idpOrSp)) {
                    samlIdpMessageReceiver.handleIncomingSamlMessage(service, httpRequest, response, idpOrSp);
                }
                else {
                    samlSpMessageReceiver.handleIncomingSamlMessage(service, httpRequest, response, idpOrSp);
                }
                break;
            case SAML_META_DATA_SERVICE:
                if (SamlIdpOrSp.IDP.equals(idpOrSp)) {
                    samlIdpBean.get().writeMetaData(responseHandler.getWriter("application/xml", response));
                }
                else {
                    samlSpBean.get().writeMetaData(responseHandler.getWriter("application/xml", response));
                }
                break;
            default:
                throw new RuntimeException("Unsupported service " + service);
        }
    }
}

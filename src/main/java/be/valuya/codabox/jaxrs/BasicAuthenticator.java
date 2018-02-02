package be.valuya.codabox.jaxrs;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BasicAuthenticator implements ClientRequestFilter {

    private final String user;
    private final char[] password;
    private Base64.Encoder base64Encoder;

    public BasicAuthenticator(String user, char[] password) {
        this.user = user;
        this.password = password;
        base64Encoder = Base64.getEncoder();
    }

    public void filter(ClientRequestContext requestContext) {
        MultivaluedMap<String, Object> headers = requestContext.getHeaders();
        final String basicAuthentication = getBasicAuthentication();
        headers.add("Authorization", basicAuthentication);

    }

    private String getBasicAuthentication() {
        String passwordStr = new String(password);
        String token = user + ":" + passwordStr;
        byte[] tokenBytes = token.getBytes(StandardCharsets.UTF_8);
        base64Encoder.encodeToString(tokenBytes);
        return "Basic " + DatatypeConverter.printBase64Binary(tokenBytes);
    }
}
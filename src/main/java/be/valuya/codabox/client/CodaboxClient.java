package be.valuya.codabox.client;

import be.valuya.codabox.domain.Feed;
import be.valuya.codabox.domain.FeedClient;
import be.valuya.codabox.domain.PodClient;
import be.valuya.codabox.jaxrs.BasicAuthenticator;
import be.valuya.codabox.jaxrs.JsonbJaxrsMessageBodyReader;
import be.valuya.codabox.jaxrs.JsonbJaxrsMessageBodyWriter;

import javax.json.bind.JsonbConfig;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.net.URI;

public class CodaboxClient {

    public static final String SOFTWARE_COMPANY_HEADER_NAME = "X-Software-Company";
    private final CodaboxClientConfig codaboxClientConfig;
    private Client client;

    public CodaboxClient(CodaboxClientConfig codaboxClientConfig) {
        this.codaboxClientConfig = codaboxClientConfig;
        initClient();
    }

    public PodClient getPodClient() {
        String softwareCompany = codaboxClientConfig.getSoftwareCompany();
        return getWebTarget()
                .path("delivery")
                .path("pod-client")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header(SOFTWARE_COMPANY_HEADER_NAME, softwareCompany)
                .get(PodClient.class);
    }

    public Feed getFeed(FeedClient feedClient) {
        String softwareCompany = codaboxClientConfig.getSoftwareCompany();
        int feedId = feedClient.getId();
        return getWebTarget()
                .path("delivery")
                .path("feed")
                .path("{feedId}")
                .resolveTemplate("feedId", feedId)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header(SOFTWARE_COMPANY_HEADER_NAME, softwareCompany)
                .get(Feed.class);
    }

    private WebTarget getWebTarget() {
        URI baseUri = codaboxClientConfig.getBaseUri();
        return client.target(baseUri)
                .path("v2");
    }

    private void initClient() {
        client = ClientBuilder.newBuilder()
                .build();
        JsonbConfig jsonbConfig = new JsonbConfig();

        JsonbJaxrsMessageBodyReader<Object> jsonbJaxrsMessageBodyReader = new JsonbJaxrsMessageBodyReader<>(jsonbConfig);
        client.register(jsonbJaxrsMessageBodyReader);

        JsonbJaxrsMessageBodyWriter<Object> jsonbJaxrsMessageBodyWriter = new JsonbJaxrsMessageBodyWriter<>(jsonbConfig);
        client.register(jsonbJaxrsMessageBodyWriter);

        String username = codaboxClientConfig.getUsername();
        char[] password = codaboxClientConfig.getPassword();
        BasicAuthenticator basicAuthenticator = new BasicAuthenticator(username, password);
        client.register(basicAuthenticator);
    }

}

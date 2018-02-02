package be.valuya.codabox.client;

import be.valuya.codabox.domain.Feed;
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

    private final CodaboxClientConfig codaboxClientConfig;
    private Client client;

    public CodaboxClient(CodaboxClientConfig codaboxClientConfig) {
        this.codaboxClientConfig = codaboxClientConfig;
        initClient();
    }

    public Feed getFeed(long feedId) {
        return getWebTarget()
                .path("delivery")
                .path("feed")
                .resolveTemplate("{0}", feedId)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(Feed.class);
    }

    public PodClient getPodClient() {
        return getWebTarget()
                .path("pod-client")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(PodClient.class);
    }

    private WebTarget getWebTarget() {
        URI baseUri = codaboxClientConfig.getBaseUri();
        return client.target(baseUri);
    }

    private void initClient() {
        client = ClientBuilder.newClient();
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

package be.valuya.codabox.client;

import be.valuya.codabox.domain.Feed;
import be.valuya.codabox.domain.FeedClient;
import be.valuya.codabox.domain.FeedStatus;
import be.valuya.codabox.domain.Fiduciary;
import be.valuya.codabox.domain.PodClient;
import be.valuya.codabox.jaxrs.BasicAuthenticator;
import be.valuya.codabox.jaxrs.JsonbJaxrsMessageBodyReader;
import be.valuya.codabox.jaxrs.JsonbJaxrsMessageBodyWriter;

import javax.json.bind.JsonbConfig;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

public class CodaboxClient {

    public static final String SOFTWARE_COMPANY_HEADER_NAME = "X-Software-Company";
    public static final GenericType<List<Fiduciary>> FIDUCIARY_LIST_TYPE = new GenericType<List<Fiduciary>>() {
    };
    public static final String DOWNLOAD_FEED_TYPE_NAME = "download";
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

    public List<Fiduciary> getFiduciaries() {
        String softwareCompany = codaboxClientConfig.getSoftwareCompany();
        return getWebTarget()
                .path("fiduciaries")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header(SOFTWARE_COMPANY_HEADER_NAME, softwareCompany)
                .get(FIDUCIARY_LIST_TYPE);
    }

    public Fiduciary getFiduciary(String fiduciaryId) {
        String softwareCompany = codaboxClientConfig.getSoftwareCompany();
        return getWebTarget()
                .path("fiduciaries")
                .path(fiduciaryId)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header(SOFTWARE_COMPANY_HEADER_NAME, softwareCompany)
                .get(Fiduciary.class);
    }

    public InputStream download(String feedIndex, String format) {
        return download(DOWNLOAD_FEED_TYPE_NAME, feedIndex, format);
    }

    public InputStream download(String feedTypeName, String feedIndex, String format) {
        String softwareCompany = codaboxClientConfig.getSoftwareCompany();
        return getWebTarget()
                .path("delivery")
                .path(feedTypeName)
                .path(feedIndex)
                .path(format)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header(SOFTWARE_COMPANY_HEADER_NAME, softwareCompany)
                .get(InputStream.class);
    }

    public FeedStatus markAsDownloaded(Integer feedId, String feedOffset) {
        FeedStatus feedStatus = new FeedStatus();
        feedStatus.setFeedId(feedId);
        feedStatus.setFeedOffset(feedOffset);
        return markAsDownloaded(feedStatus);
    }

    public FeedStatus markAsDownloaded(FeedStatus feedStatus) {
        String softwareCompany = codaboxClientConfig.getSoftwareCompany();
        Integer feedId = feedStatus.getFeedId();
        Entity<FeedStatus> feedStatusEntity = Entity.entity(feedStatus, MediaType.APPLICATION_JSON_TYPE);
        return getWebTarget()
                .path("delivery")
                .path("feed")
                .path("{feedId}")
                .resolveTemplate("feedId", feedId)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header(SOFTWARE_COMPANY_HEADER_NAME, softwareCompany)
                .put(feedStatusEntity, FeedStatus.class);
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

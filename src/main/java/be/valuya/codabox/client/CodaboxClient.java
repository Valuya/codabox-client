package be.valuya.codabox.client;

import be.valuya.codabox.domain.CodaboxCustomer;
import be.valuya.codabox.domain.CodaboxFormat;
import be.valuya.codabox.domain.CodaboxFormatJsonbAdapter;
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
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

public class CodaboxClient {

    private static final String SOFTWARE_COMPANY_HEADER_NAME = "X-Software-Company";
    private static final GenericType<List<Fiduciary>> FIDUCIARY_LIST_TYPE = new GenericType<List<Fiduciary>>() {
    };
    private static final GenericType<List<CodaboxCustomer>> CUSTOMER_LIST_TYPE = new GenericType<List<CodaboxCustomer>>() {
    };
    private static final String DOWNLOAD_CHANNEL_NAME = "download";
    private static final String REDOWNLOAD_CHANNEL_NAME = "redownload";
    private static final String DOWNLOAD_FEED_NAME = "feed";
    private static final String REDOWNLOAD_FEED_NAME = "redownload-feed";
    private final CodaboxClientConfig codaboxClientConfig;
    private Client client;

    public CodaboxClient(CodaboxClientConfig codaboxClientConfig, Configuration jaxRsConfiguration) {
        this.codaboxClientConfig = codaboxClientConfig;
        createClient(jaxRsConfiguration);
    }

    public CodaboxClient(CodaboxClientConfig codaboxClientConfig) {
        this.codaboxClientConfig = codaboxClientConfig;
        createClient();
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
        return getFeed(feedClient, false);
    }

    public Feed getFeed(FeedClient feedClient, boolean redownload) {
        String softwareCompany = codaboxClientConfig.getSoftwareCompany();
        int feedId = feedClient.getId();

        String downloadFeedName = getDownloadFeedName(redownload);

        return getWebTarget()
                .path("delivery")
                .path(downloadFeedName)
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

    public List<CodaboxCustomer> getCustomers() {
        String softwareCompany = codaboxClientConfig.getSoftwareCompany();
        return getWebTarget()
                .path("clients")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header(SOFTWARE_COMPANY_HEADER_NAME, softwareCompany)
                .get(CUSTOMER_LIST_TYPE);
    }

    public CodaboxCustomer getCodaboxCustomer(String customerId) {
        String softwareCompany = codaboxClientConfig.getSoftwareCompany();
        return getWebTarget()
                .path("clients")
                .path(customerId)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header(SOFTWARE_COMPANY_HEADER_NAME, softwareCompany)
                .get(CodaboxCustomer.class);
    }

    public InputStream download(boolean redownload, String feedIndex, CodaboxFormat codaboxFormat) {
        String softwareCompany = codaboxClientConfig.getSoftwareCompany();
        String formatName = codaboxFormat.getFormatName();

        String downloadChannelName = getDownloadChannelName(redownload);

        return getWebTarget()
                .path("delivery")
                .path(downloadChannelName)
                .path(feedIndex)
                .path(formatName)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header(SOFTWARE_COMPANY_HEADER_NAME, softwareCompany)
                .get(InputStream.class);
    }

    public FeedStatus markAsDownloaded(Integer feedId, String feedOffset, boolean redelivery) {
        FeedStatus feedStatus = new FeedStatus();
        feedStatus.setFeedId(feedId);
        feedStatus.setFeedOffset(feedOffset);
        return markAsDownloaded(feedStatus, redelivery);
    }

    public FeedStatus markAsDownloaded(FeedStatus feedStatus, boolean redownload) {
        String softwareCompany = codaboxClientConfig.getSoftwareCompany();
        Integer feedId = feedStatus.getFeedId();
        Entity<FeedStatus> feedStatusEntity = Entity.entity(feedStatus, MediaType.APPLICATION_JSON_TYPE);

        String downloadFeedName = getDownloadFeedName(redownload);

        return getWebTarget()
                .path("delivery")
                .path(downloadFeedName)
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

    private void createClient(Configuration jaxRsConfiguration) {
        client = ClientBuilder.newBuilder()
                .withConfig(jaxRsConfiguration)
                .build();

        init();
    }

    private void createClient() {
        client = ClientBuilder.newBuilder()
                .build();

        init();
    }

    private void init() {
        JsonbConfig jsonbConfig = new JsonbConfig();
        CodaboxFormatJsonbAdapter codaboxFormatJsonbAdapter = new CodaboxFormatJsonbAdapter();
        jsonbConfig.withAdapters(codaboxFormatJsonbAdapter);

        JsonbJaxrsMessageBodyReader<Object> jsonbJaxrsMessageBodyReader = new JsonbJaxrsMessageBodyReader<>(jsonbConfig);
        client.register(jsonbJaxrsMessageBodyReader, 0);

        JsonbJaxrsMessageBodyWriter<Object> jsonbJaxrsMessageBodyWriter = new JsonbJaxrsMessageBodyWriter<>(jsonbConfig);
        client.register(jsonbJaxrsMessageBodyWriter, 0);

        String username = codaboxClientConfig.getUsername();
        char[] password = codaboxClientConfig.getPassword();
        BasicAuthenticator basicAuthenticator = new BasicAuthenticator(username, password);
        client.register(basicAuthenticator);
    }

    private String getDownloadFeedName(boolean redownload) {
        if (redownload) {
            return REDOWNLOAD_FEED_NAME;
        } else {
            return DOWNLOAD_FEED_NAME;
        }
    }
    private String getDownloadChannelName(boolean redownload) {
        if (redownload) {
            return REDOWNLOAD_CHANNEL_NAME;
        } else {
            return DOWNLOAD_CHANNEL_NAME;
        }
    }
}

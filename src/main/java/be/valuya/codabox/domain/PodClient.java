package be.valuya.codabox.domain;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import java.util.List;
import java.util.Map;

@JsonbPropertyOrder({
        "id",
        "fetch_delay",
        "allowed_formats",
        "feed_clients"
})
public class PodClient {

    @JsonbProperty("id")
    private Integer id;
    @JsonbProperty("fetch_delay")
    private Integer fetchDelay;
    @JsonbProperty("allowed_formats")
    private Map<String, List<CodaboxFormat>> documentTypeFormatListMap;
    @JsonbProperty("feed_clients")
    private List<FeedClient> feedClients;

    @JsonbProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonbProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonbProperty("fetch_delay")
    public Integer getFetchDelay() {
        return fetchDelay;
    }

    @JsonbProperty("fetch_delay")
    public void setFetchDelay(Integer fetchDelay) {
        this.fetchDelay = fetchDelay;
    }

    public Map<String, List<CodaboxFormat>> getDocumentTypeFormatListMap() {
        return documentTypeFormatListMap;
    }

    public void setDocumentTypeFormatListMap(Map<String, List<CodaboxFormat>> documentTypeFormatListMap) {
        this.documentTypeFormatListMap = documentTypeFormatListMap;
    }

    @JsonbProperty("feed_clients")
    public List<FeedClient> getFeedClients() {
        return feedClients;
    }

    @JsonbProperty("feed_clients")
    public void setFeedClients(List<FeedClient> feedClients) {
        this.feedClients = feedClients;
    }

}

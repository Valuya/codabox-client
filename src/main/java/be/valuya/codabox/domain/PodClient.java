package be.valuya.codabox.domain;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import java.util.List;

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
    private AllowedFormats allowedFormats;
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

    @JsonbProperty("allowed_formats")
    public AllowedFormats getAllowedFormats() {
        return allowedFormats;
    }

    @JsonbProperty("allowed_formats")
    public void setAllowedFormats(AllowedFormats allowedFormats) {
        this.allowedFormats = allowedFormats;
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

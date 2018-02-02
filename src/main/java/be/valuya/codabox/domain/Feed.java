package be.valuya.codabox.domain;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import java.util.List;

@JsonbPropertyOrder({
        "id",
        "type",
        "feed_entries"
})
public class Feed {

    @JsonbProperty("id")
    private Integer id;
    @JsonbProperty("type")
    private String type;
    @JsonbProperty("feed_entries")
    private List<FeedEntry> feedEntries;

    @JsonbProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonbProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonbProperty("type")
    public String getType() {
        return type;
    }

    @JsonbProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonbProperty("feed_entries")
    public List<FeedEntry> getFeedEntries() {
        return feedEntries;
    }

    @JsonbProperty("feed_entries")
    public void setFeedEntries(List<FeedEntry> feedEntries) {
        this.feedEntries = feedEntries;
    }

}

package be.valuya.codabox.domain;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({
        "feed_offset"
})
public class FeedStatus {

    @JsonbProperty("id")
    private String feedId;
    @JsonbProperty("feed_offset")
    private String feedOffset;

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getFeedOffset() {
        return feedOffset;
    }

    public void setFeedOffset(String feedOffset) {
        this.feedOffset = feedOffset;
    }

}

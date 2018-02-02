package be.valuya.codabox;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({
        "feed_offset"
})
public class FeedStatus {

    @JsonbProperty("feed_offset")
    private String feedOffset;

    @JsonbProperty("feed_offset")
    public String getFeedOffset() {
        return feedOffset;
    }

    @JsonbProperty("feed_offset")
    public void setFeedOffset(String feedOffset) {
        this.feedOffset = feedOffset;
    }

    public FeedStatus withFeedOffset(String feedOffset) {
        this.feedOffset = feedOffset;
        return this;
    }

}

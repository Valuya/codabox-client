package be.valuya.codabox.domain;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({
        "feed_offset"
})
public class FeedStatus {

    @JsonbProperty("id")
    private Integer feedId;
    @JsonbProperty("feed_offset")
    private String feedOffset;

    public Integer getFeedId() {
        return feedId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    public String getFeedOffset() {
        return feedOffset;
    }

    public void setFeedOffset(String feedOffset) {
        this.feedOffset = feedOffset;
    }

}

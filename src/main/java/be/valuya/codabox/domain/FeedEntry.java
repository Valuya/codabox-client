package be.valuya.codabox.domain;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({
        "feed_index",
        "document_model",
        "metadata"
})
public class FeedEntry {

    @JsonbProperty("feed_index")
    private String feedIndex;
    @JsonbProperty("document_model")
    private String documentModel;
    @JsonbProperty("metadata")
    private CodaMetadata metadata;

    @JsonbProperty("feed_index")
    public String getFeedIndex() {
        return feedIndex;
    }

    @JsonbProperty("feed_index")
    public void setFeedIndex(String feedIndex) {
        this.feedIndex = feedIndex;
    }

    @JsonbProperty("document_model")
    public String getDocumentModel() {
        return documentModel;
    }

    @JsonbProperty("document_model")
    public void setDocumentModel(String documentModel) {
        this.documentModel = documentModel;
    }

    @JsonbProperty("metadata")
    public CodaMetadata getMetadata() {
        return metadata;
    }

    @JsonbProperty("metadata")
    public void setMetadata(CodaMetadata metadata) {
        this.metadata = metadata;
    }

}

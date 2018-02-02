package be.valuya.codabox;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({
        "id",
        "type",
        "fiduciary_id",
        "state"
})
public class Service {

    @JsonbProperty("id")
    private String id;
    @JsonbProperty("type")
    private String type;
    @JsonbProperty("fiduciary_id")
    private String fiduciaryId;
    @JsonbProperty("state")
    private String state;

    @JsonbProperty("id")
    public String getId() {
        return id;
    }

    @JsonbProperty("id")
    public void setId(String id) {
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

    @JsonbProperty("fiduciary_id")
    public String getFiduciaryId() {
        return fiduciaryId;
    }

    @JsonbProperty("fiduciary_id")
    public void setFiduciaryId(String fiduciaryId) {
        this.fiduciaryId = fiduciaryId;
    }

    @JsonbProperty("state")
    public String getState() {
        return state;
    }

    @JsonbProperty("state")
    public void setState(String state) {
        this.state = state;
    }

}

package be.valuya.codabox.domain;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({
        "id",
        "client_id",
        "fiduciary_id",
        "social_welfare_id",
        "validity_start",
        "state",
        "state_modified_at"
})
public class SocialMandate {

    @JsonbProperty("id")
    private String id;
    @JsonbProperty("client_id")
    private String clientId;
    @JsonbProperty("fiduciary_id")
    private String fiduciaryId;
    @JsonbProperty("social_welfare_id")
    private String socialWelfareId;
    @JsonbProperty("validity_start")
    private String validityStart;
    @JsonbProperty("state")
    private String state;
    @JsonbProperty("state_modified_at")
    private String stateModifiedAt;

    @JsonbProperty("id")
    public String getId() {
        return id;
    }

    @JsonbProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonbProperty("client_id")
    public String getClientId() {
        return clientId;
    }

    @JsonbProperty("client_id")
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @JsonbProperty("fiduciary_id")
    public String getFiduciaryId() {
        return fiduciaryId;
    }

    @JsonbProperty("fiduciary_id")
    public void setFiduciaryId(String fiduciaryId) {
        this.fiduciaryId = fiduciaryId;
    }

    @JsonbProperty("social_welfare_id")
    public String getSocialWelfareId() {
        return socialWelfareId;
    }

    @JsonbProperty("social_welfare_id")
    public void setSocialWelfareId(String socialWelfareId) {
        this.socialWelfareId = socialWelfareId;
    }

    @JsonbProperty("validity_start")
    public String getValidityStart() {
        return validityStart;
    }

    @JsonbProperty("validity_start")
    public void setValidityStart(String validityStart) {
        this.validityStart = validityStart;
    }

    @JsonbProperty("state")
    public String getState() {
        return state;
    }

    @JsonbProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonbProperty("state_modified_at")
    public String getStateModifiedAt() {
        return stateModifiedAt;
    }

    @JsonbProperty("state_modified_at")
    public void setStateModifiedAt(String stateModifiedAt) {
        this.stateModifiedAt = stateModifiedAt;
    }

}

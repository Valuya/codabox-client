package be.valuya.codabox.domain;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({
        "state",
        "client_id",
        "state_modified_at",
        "representative_name",
        "representative_function",
        "representative_email"
})
public class PurchaseInvoiceMandate {

    @JsonbProperty("state")
    private String state;
    @JsonbProperty("client_id")
    private String clientId;
    @JsonbProperty("state_modified_at")
    private String stateModifiedAt;
    @JsonbProperty("representative_name")
    private Object representativeName;
    @JsonbProperty("representative_function")
    private Object representativeFunction;
    @JsonbProperty("representative_email")
    private Object representativeEmail;

    @JsonbProperty("state")
    public String getState() {
        return state;
    }

    @JsonbProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonbProperty("client_id")
    public String getClientId() {
        return clientId;
    }

    @JsonbProperty("client_id")
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @JsonbProperty("state_modified_at")
    public String getStateModifiedAt() {
        return stateModifiedAt;
    }

    @JsonbProperty("state_modified_at")
    public void setStateModifiedAt(String stateModifiedAt) {
        this.stateModifiedAt = stateModifiedAt;
    }

    @JsonbProperty("representative_name")
    public Object getRepresentativeName() {
        return representativeName;
    }

    @JsonbProperty("representative_name")
    public void setRepresentativeName(Object representativeName) {
        this.representativeName = representativeName;
    }

    @JsonbProperty("representative_function")
    public Object getRepresentativeFunction() {
        return representativeFunction;
    }

    @JsonbProperty("representative_function")
    public void setRepresentativeFunction(Object representativeFunction) {
        this.representativeFunction = representativeFunction;
    }

    @JsonbProperty("representative_email")
    public Object getRepresentativeEmail() {
        return representativeEmail;
    }

    @JsonbProperty("representative_email")
    public void setRepresentativeEmail(Object representativeEmail) {
        this.representativeEmail = representativeEmail;
    }

}

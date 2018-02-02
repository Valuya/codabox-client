package be.valuya.codabox;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({
        "state",
        "client_id",
        "fiduciary_id"
})
public class PurchaseInvoiceTransmissionMandate {

    @JsonbProperty("state")
    private String state;
    @JsonbProperty("client_id")
    private String clientId;
    @JsonbProperty("fiduciary_id")
    private String fiduciaryId;

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

    @JsonbProperty("fiduciary_id")
    public String getFiduciaryId() {
        return fiduciaryId;
    }

    @JsonbProperty("fiduciary_id")
    public void setFiduciaryId(String fiduciaryId) {
        this.fiduciaryId = fiduciaryId;
    }

}

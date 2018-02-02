package be.valuya.codabox;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import java.util.List;

@JsonbPropertyOrder({
        "id",
        "client_id",
        "fiduciary_id",
        "bank_id",
        "ibans",
        "validity_start",
        "state",
        "reason",
        "reason_code",
        "twikey_url",
        "state_modified_at"
})
public class CodaMandate {

    @JsonbProperty("id")
    private String id;
    @JsonbProperty("client_id")
    private String clientId;
    @JsonbProperty("fiduciary_id")
    private String fiduciaryId;
    @JsonbProperty("bank_id")
    private String bankId;
    @JsonbProperty("ibans")
    private List<String> ibans;
    @JsonbProperty("validity_start")
    private String validityStart;
    @JsonbProperty("state")
    private String state;
    @JsonbProperty("reason")
    private Object reason;
    @JsonbProperty("reason_code")
    private String reasonCode;
    @JsonbProperty("twikey_url")
    private Object twikeyUrl;
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

    @JsonbProperty("bank_id")
    public String getBankId() {
        return bankId;
    }

    @JsonbProperty("bank_id")
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    @JsonbProperty("ibans")
    public List<String> getIbans() {
        return ibans;
    }

    @JsonbProperty("ibans")
    public void setIbans(List<String> ibans) {
        this.ibans = ibans;
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

    @JsonbProperty("reason")
    public Object getReason() {
        return reason;
    }

    @JsonbProperty("reason")
    public void setReason(Object reason) {
        this.reason = reason;
    }

    @JsonbProperty("reason_code")
    public String getReasonCode() {
        return reasonCode;
    }

    @JsonbProperty("reason_code")
    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    @JsonbProperty("twikey_url")
    public Object getTwikeyUrl() {
        return twikeyUrl;
    }

    @JsonbProperty("twikey_url")
    public void setTwikeyUrl(Object twikeyUrl) {
        this.twikeyUrl = twikeyUrl;
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

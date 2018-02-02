package be.valuya.codabox;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import java.util.List;

@JsonbPropertyOrder({
        "iban",
        "subaccounts",
        "type",
        "coda_mandate_ids",
        "bank_id",
        "client_id",
        "state",
        "state_modified_at"
})
public class BankAccount {

    @JsonbProperty("iban")
    private String iban;
    @JsonbProperty("subaccounts")
    private List<Subaccount> subaccounts;
    @JsonbProperty("type")
    private String type;
    @JsonbProperty("coda_mandate_ids")
    private List<String> codaMandateIds;
    @JsonbProperty("bank_id")
    private String bankId;
    @JsonbProperty("client_id")
    private String clientId;
    @JsonbProperty("state")
    private String state;
    @JsonbProperty("state_modified_at")
    private String stateModifiedAt;

    @JsonbProperty("iban")
    public String getIban() {
        return iban;
    }

    @JsonbProperty("iban")
    public void setIban(String iban) {
        this.iban = iban;
    }

    @JsonbProperty("subaccounts")
    public List<Subaccount> getSubaccounts() {
        return subaccounts;
    }

    @JsonbProperty("subaccounts")
    public void setSubaccounts(List<Subaccount> subaccounts) {
        this.subaccounts = subaccounts;
    }

    @JsonbProperty("type")
    public String getType() {
        return type;
    }

    @JsonbProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonbProperty("coda_mandate_ids")
    public List<String> getCodaMandateIds() {
        return codaMandateIds;
    }

    @JsonbProperty("coda_mandate_ids")
    public void setCodaMandateIds(List<String> codaMandateIds) {
        this.codaMandateIds = codaMandateIds;
    }

    @JsonbProperty("bank_id")
    public String getBankId() {
        return bankId;
    }

    @JsonbProperty("bank_id")
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    @JsonbProperty("client_id")
    public String getClientId() {
        return clientId;
    }

    @JsonbProperty("client_id")
    public void setClientId(String clientId) {
        this.clientId = clientId;
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

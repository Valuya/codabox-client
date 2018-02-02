package be.valuya.codabox;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({
        "id",
        "client_id",
        "client_name",
        "client_code",
        "fiduciary_id",
        "target_root_state",
        "delivery_config"
})
public class FeedClient {

    @JsonbProperty("id")
    private Integer id;
    @JsonbProperty("client_id")
    private String clientId;
    @JsonbProperty("client_name")
    private String clientName;
    @JsonbProperty("client_code")
    private String clientCode;
    @JsonbProperty("fiduciary_id")
    private String fiduciaryId;
    @JsonbProperty("target_root_state")
    private String targetRootState;
    @JsonbProperty("delivery_config")
    private Object deliveryConfig;

    @JsonbProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonbProperty("id")
    public void setId(Integer id) {
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

    @JsonbProperty("client_name")
    public String getClientName() {
        return clientName;
    }

    @JsonbProperty("client_name")
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @JsonbProperty("client_code")
    public String getClientCode() {
        return clientCode;
    }

    @JsonbProperty("client_code")
    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    @JsonbProperty("fiduciary_id")
    public String getFiduciaryId() {
        return fiduciaryId;
    }

    @JsonbProperty("fiduciary_id")
    public void setFiduciaryId(String fiduciaryId) {
        this.fiduciaryId = fiduciaryId;
    }

    @JsonbProperty("target_root_state")
    public String getTargetRootState() {
        return targetRootState;
    }

    @JsonbProperty("target_root_state")
    public void setTargetRootState(String targetRootState) {
        this.targetRootState = targetRootState;
    }

    @JsonbProperty("delivery_config")
    public Object getDeliveryConfig() {
        return deliveryConfig;
    }

    @JsonbProperty("delivery_config")
    public void setDeliveryConfig(Object deliveryConfig) {
        this.deliveryConfig = deliveryConfig;
    }

}

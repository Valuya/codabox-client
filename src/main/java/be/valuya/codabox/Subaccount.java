package be.valuya.codabox;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({
        "currency",
        "extension"
})
public class Subaccount {

    @JsonbProperty("currency")
    private String currency;
    @JsonbProperty("extension")
    private Object extension;

    @JsonbProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonbProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonbProperty("extension")
    public Object getExtension() {
        return extension;
    }

    @JsonbProperty("extension")
    public void setExtension(Object extension) {
        this.extension = extension;
    }

}

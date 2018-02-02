package be.valuya.codabox.domain;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({
        "name",
        "enterprise_num",
        "is_enterprise_num_validated",
        "representative_name",
        "representative_function",
        "address",
        "address2",
        "zip",
        "city"
})
public class LegalEntity {

    @JsonbProperty("name")
    private String name;
    @JsonbProperty("enterprise_num")
    private String enterpriseNum;
    @JsonbProperty("is_enterprise_num_validated")
    private Boolean isEnterpriseNumValidated;
    @JsonbProperty("representative_name")
    private String representativeName;
    @JsonbProperty("representative_function")
    private String representativeFunction;
    @JsonbProperty("address")
    private String address;
    @JsonbProperty("address2")
    private Object address2;
    @JsonbProperty("zip")
    private String zip;
    @JsonbProperty("city")
    private String city;

    @JsonbProperty("name")
    public String getName() {
        return name;
    }

    @JsonbProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonbProperty("enterprise_num")
    public String getEnterpriseNum() {
        return enterpriseNum;
    }

    @JsonbProperty("enterprise_num")
    public void setEnterpriseNum(String enterpriseNum) {
        this.enterpriseNum = enterpriseNum;
    }

    @JsonbProperty("is_enterprise_num_validated")
    public Boolean getIsEnterpriseNumValidated() {
        return isEnterpriseNumValidated;
    }

    @JsonbProperty("is_enterprise_num_validated")
    public void setIsEnterpriseNumValidated(Boolean isEnterpriseNumValidated) {
        this.isEnterpriseNumValidated = isEnterpriseNumValidated;
    }

    @JsonbProperty("representative_name")
    public String getRepresentativeName() {
        return representativeName;
    }

    @JsonbProperty("representative_name")
    public void setRepresentativeName(String representativeName) {
        this.representativeName = representativeName;
    }

    @JsonbProperty("representative_function")
    public String getRepresentativeFunction() {
        return representativeFunction;
    }

    @JsonbProperty("representative_function")
    public void setRepresentativeFunction(String representativeFunction) {
        this.representativeFunction = representativeFunction;
    }

    @JsonbProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonbProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonbProperty("address2")
    public Object getAddress2() {
        return address2;
    }

    @JsonbProperty("address2")
    public void setAddress2(Object address2) {
        this.address2 = address2;
    }

    @JsonbProperty("zip")
    public String getZip() {
        return zip;
    }

    @JsonbProperty("zip")
    public void setZip(String zip) {
        this.zip = zip;
    }

    @JsonbProperty("city")
    public String getCity() {
        return city;
    }

    @JsonbProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

}

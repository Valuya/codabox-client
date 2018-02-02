package be.valuya.codabox.domain;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import java.util.List;

@JsonbPropertyOrder({
        "id",
        "name",
        "legal_entity",
        "contact_name",
        "contact_email",
        "language",
        "website_url",
        "allows_social_mandates",
        "allows_doccle_delivery",
        "allows_client_delivery",
        "services",
        "delegates_mandate_delivery",
        "software"
})
public class Fiduciary {

    @JsonbProperty("id")
    private String id;
    @JsonbProperty("name")
    private String name;
    @JsonbProperty("legal_entity")
    private LegalEntity legalEntity;
    @JsonbProperty("contact_name")
    private String contactName;
    @JsonbProperty("contact_email")
    private String contactEmail;
    @JsonbProperty("language")
    private String language;
    @JsonbProperty("website_url")
    private String websiteUrl;
    @JsonbProperty("allows_social_mandates")
    private Boolean allowsSocialMandates;
    @JsonbProperty("allows_doccle_delivery")
    private Boolean allowsDoccleDelivery;
    @JsonbProperty("allows_client_delivery")
    private Boolean allowsClientDelivery;
    @JsonbProperty("services")
    private List<Service> services;
    @JsonbProperty("delegates_mandate_delivery")
    private Boolean delegatesMandateDelivery;
    @JsonbProperty("software")
    private Software software;

    @JsonbProperty("id")
    public String getId() {
        return id;
    }

    @JsonbProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonbProperty("name")
    public String getName() {
        return name;
    }

    @JsonbProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonbProperty("legal_entity")
    public LegalEntity getLegalEntity() {
        return legalEntity;
    }

    @JsonbProperty("legal_entity")
    public void setLegalEntity(LegalEntity legalEntity) {
        this.legalEntity = legalEntity;
    }

    @JsonbProperty("contact_name")
    public String getContactName() {
        return contactName;
    }

    @JsonbProperty("contact_name")
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @JsonbProperty("contact_email")
    public String getContactEmail() {
        return contactEmail;
    }

    @JsonbProperty("contact_email")
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @JsonbProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonbProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonbProperty("website_url")
    public String getWebsiteUrl() {
        return websiteUrl;
    }

    @JsonbProperty("website_url")
    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    @JsonbProperty("allows_social_mandates")
    public Boolean getAllowsSocialMandates() {
        return allowsSocialMandates;
    }

    @JsonbProperty("allows_social_mandates")
    public void setAllowsSocialMandates(Boolean allowsSocialMandates) {
        this.allowsSocialMandates = allowsSocialMandates;
    }

    @JsonbProperty("allows_doccle_delivery")
    public Boolean getAllowsDoccleDelivery() {
        return allowsDoccleDelivery;
    }

    @JsonbProperty("allows_doccle_delivery")
    public void setAllowsDoccleDelivery(Boolean allowsDoccleDelivery) {
        this.allowsDoccleDelivery = allowsDoccleDelivery;
    }

    @JsonbProperty("allows_client_delivery")
    public Boolean getAllowsClientDelivery() {
        return allowsClientDelivery;
    }

    @JsonbProperty("allows_client_delivery")
    public void setAllowsClientDelivery(Boolean allowsClientDelivery) {
        this.allowsClientDelivery = allowsClientDelivery;
    }

    @JsonbProperty("services")
    public List<Service> getServices() {
        return services;
    }

    @JsonbProperty("services")
    public void setServices(List<Service> services) {
        this.services = services;
    }

    @JsonbProperty("delegates_mandate_delivery")
    public Boolean getDelegatesMandateDelivery() {
        return delegatesMandateDelivery;
    }

    @JsonbProperty("delegates_mandate_delivery")
    public void setDelegatesMandateDelivery(Boolean delegatesMandateDelivery) {
        this.delegatesMandateDelivery = delegatesMandateDelivery;
    }

    @JsonbProperty("software")
    public Software getSoftware() {
        return software;
    }

    @JsonbProperty("software")
    public void setSoftware(Software software) {
        this.software = software;
    }

}

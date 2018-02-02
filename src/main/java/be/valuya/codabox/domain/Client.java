package be.valuya.codabox.domain;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import java.util.List;

@JsonbPropertyOrder({
        "id",
        "fiduciary_id",
        "legal_entity",
        "client_code",
        "contact_name",
        "contact_email",
        "language",
        "exact_email",
        "coda_mandates",
        "bank_accounts",
        "social_mandates",
        "purchase_invoice_mandates",
        "purchase_invoice_transmission_mandates",
        "current_purchase_invoice_mandate_state",
        "state",
        "delivery_mode",
        "doccle_link_connect",
        "mycodabox_url",
        "state_modified_at",
        "services",
        "zoomit_ibans",
        "mycodabox_access_state"
})
public class Client {

    @JsonbProperty("id")
    private String id;
    @JsonbProperty("fiduciary_id")
    private String fiduciaryId;
    @JsonbProperty("legal_entity")
    private LegalEntity legalEntity;
    @JsonbProperty("client_code")
    private String clientCode;
    @JsonbProperty("contact_name")
    private String contactName;
    @JsonbProperty("contact_email")
    private String contactEmail;
    @JsonbProperty("language")
    private String language;
    @JsonbProperty("exact_email")
    private String exactEmail;
    @JsonbProperty("coda_mandates")
    private List<CodaMandate> codaMandates;
    @JsonbProperty("bank_accounts")
    private List<BankAccount> bankAccounts;
    @JsonbProperty("social_mandates")
    private List<SocialMandate> socialMandates;
    @JsonbProperty("purchase_invoice_mandates")
    private List<PurchaseInvoiceMandate> purchaseInvoiceMandates;
    @JsonbProperty("purchase_invoice_transmission_mandates")
    private List<PurchaseInvoiceTransmissionMandate> purchaseInvoiceTransmissionMandates;
    @JsonbProperty("current_purchase_invoice_mandate_state")
    private String currentPurchaseInvoiceMandateState;
    @JsonbProperty("state")
    private String state;
    @JsonbProperty("delivery_mode")
    private String deliveryMode;
    @JsonbProperty("doccle_link_connect")
    private Object doccleLinkConnect;
    @JsonbProperty("mycodabox_url")
    private Object mycodaboxUrl;
    @JsonbProperty("state_modified_at")
    private String stateModifiedAt;
    @JsonbProperty("services")
    private List<Service> services;
    @JsonbProperty("zoomit_ibans")
    private List<Object> zoomitIbans;
    @JsonbProperty("mycodabox_access_state")
    private String mycodaboxAccessState;

    @JsonbProperty("id")
    public String getId() {
        return id;
    }

    @JsonbProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonbProperty("fiduciary_id")
    public String getFiduciaryId() {
        return fiduciaryId;
    }

    @JsonbProperty("fiduciary_id")
    public void setFiduciaryId(String fiduciaryId) {
        this.fiduciaryId = fiduciaryId;
    }

    @JsonbProperty("legal_entity")
    public LegalEntity getLegalEntity() {
        return legalEntity;
    }

    @JsonbProperty("legal_entity")
    public void setLegalEntity(LegalEntity legalEntity) {
        this.legalEntity = legalEntity;
    }

    @JsonbProperty("client_code")
    public String getClientCode() {
        return clientCode;
    }

    @JsonbProperty("client_code")
    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
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

    @JsonbProperty("exact_email")
    public String getExactEmail() {
        return exactEmail;
    }

    @JsonbProperty("exact_email")
    public void setExactEmail(String exactEmail) {
        this.exactEmail = exactEmail;
    }

    @JsonbProperty("coda_mandates")
    public List<CodaMandate> getCodaMandates() {
        return codaMandates;
    }

    @JsonbProperty("coda_mandates")
    public void setCodaMandates(List<CodaMandate> codaMandates) {
        this.codaMandates = codaMandates;
    }

    @JsonbProperty("bank_accounts")
    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    @JsonbProperty("bank_accounts")
    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    @JsonbProperty("social_mandates")
    public List<SocialMandate> getSocialMandates() {
        return socialMandates;
    }

    @JsonbProperty("social_mandates")
    public void setSocialMandates(List<SocialMandate> socialMandates) {
        this.socialMandates = socialMandates;
    }

    @JsonbProperty("purchase_invoice_mandates")
    public List<PurchaseInvoiceMandate> getPurchaseInvoiceMandates() {
        return purchaseInvoiceMandates;
    }

    @JsonbProperty("purchase_invoice_mandates")
    public void setPurchaseInvoiceMandates(List<PurchaseInvoiceMandate> purchaseInvoiceMandates) {
        this.purchaseInvoiceMandates = purchaseInvoiceMandates;
    }

    @JsonbProperty("purchase_invoice_transmission_mandates")
    public List<PurchaseInvoiceTransmissionMandate> getPurchaseInvoiceTransmissionMandates() {
        return purchaseInvoiceTransmissionMandates;
    }

    @JsonbProperty("purchase_invoice_transmission_mandates")
    public void setPurchaseInvoiceTransmissionMandates(List<PurchaseInvoiceTransmissionMandate> purchaseInvoiceTransmissionMandates) {
        this.purchaseInvoiceTransmissionMandates = purchaseInvoiceTransmissionMandates;
    }

    @JsonbProperty("current_purchase_invoice_mandate_state")
    public String getCurrentPurchaseInvoiceMandateState() {
        return currentPurchaseInvoiceMandateState;
    }

    @JsonbProperty("current_purchase_invoice_mandate_state")
    public void setCurrentPurchaseInvoiceMandateState(String currentPurchaseInvoiceMandateState) {
        this.currentPurchaseInvoiceMandateState = currentPurchaseInvoiceMandateState;
    }

    @JsonbProperty("state")
    public String getState() {
        return state;
    }

    @JsonbProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonbProperty("delivery_mode")
    public String getDeliveryMode() {
        return deliveryMode;
    }

    @JsonbProperty("delivery_mode")
    public void setDeliveryMode(String deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    @JsonbProperty("doccle_link_connect")
    public Object getDoccleLinkConnect() {
        return doccleLinkConnect;
    }

    @JsonbProperty("doccle_link_connect")
    public void setDoccleLinkConnect(Object doccleLinkConnect) {
        this.doccleLinkConnect = doccleLinkConnect;
    }

    @JsonbProperty("mycodabox_url")
    public Object getMycodaboxUrl() {
        return mycodaboxUrl;
    }

    @JsonbProperty("mycodabox_url")
    public void setMycodaboxUrl(Object mycodaboxUrl) {
        this.mycodaboxUrl = mycodaboxUrl;
    }

    @JsonbProperty("state_modified_at")
    public String getStateModifiedAt() {
        return stateModifiedAt;
    }

    @JsonbProperty("state_modified_at")
    public void setStateModifiedAt(String stateModifiedAt) {
        this.stateModifiedAt = stateModifiedAt;
    }

    @JsonbProperty("services")
    public List<Service> getServices() {
        return services;
    }

    @JsonbProperty("services")
    public void setServices(List<Service> services) {
        this.services = services;
    }

    @JsonbProperty("zoomit_ibans")
    public List<Object> getZoomitIbans() {
        return zoomitIbans;
    }

    @JsonbProperty("zoomit_ibans")
    public void setZoomitIbans(List<Object> zoomitIbans) {
        this.zoomitIbans = zoomitIbans;
    }

    @JsonbProperty("mycodabox_access_state")
    public String getMycodaboxAccessState() {
        return mycodaboxAccessState;
    }

    @JsonbProperty("mycodabox_access_state")
    public void setMycodaboxAccessState(String mycodaboxAccessState) {
        this.mycodaboxAccessState = mycodaboxAccessState;
    }

}

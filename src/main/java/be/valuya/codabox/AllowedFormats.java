package be.valuya.codabox;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

public class AllowedFormats {

    @JsonbProperty("expense")
    private List<String> expense;
    @JsonbProperty("soda")
    private List<String> soda;
    @JsonbProperty("purchase_invoice")
    private List<String> purchaseInvoice;
    @JsonbProperty("sales_invoice")
    private List<String> salesInvoice;
    @JsonbProperty("coda")
    private List<String> coda;

    @JsonbProperty("expense")
    public List<String> getExpense() {
        return expense;
    }

    @JsonbProperty("expense")
    public void setExpense(List<String> expense) {
        this.expense = expense;
    }

    @JsonbProperty("soda")
    public List<String> getSoda() {
        return soda;
    }

    @JsonbProperty("soda")
    public void setSoda(List<String> soda) {
        this.soda = soda;
    }

    @JsonbProperty("purchase_invoice")
    public List<String> getPurchaseInvoice() {
        return purchaseInvoice;
    }

    @JsonbProperty("purchase_invoice")
    public void setPurchaseInvoice(List<String> purchaseInvoice) {
        this.purchaseInvoice = purchaseInvoice;
    }

    @JsonbProperty("sales_invoice")
    public List<String> getSalesInvoice() {
        return salesInvoice;
    }

    @JsonbProperty("sales_invoice")
    public void setSalesInvoice(List<String> salesInvoice) {
        this.salesInvoice = salesInvoice;
    }

    @JsonbProperty("coda")
    public List<String> getCoda() {
        return coda;
    }

    @JsonbProperty("coda")
    public void setCoda(List<String> coda) {
        this.coda = coda;
    }

}

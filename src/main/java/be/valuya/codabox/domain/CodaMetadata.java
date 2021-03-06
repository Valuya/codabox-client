package be.valuya.codabox.domain;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import java.time.LocalDate;

@JsonbPropertyOrder({
        "movement_count",
        "last_statement_number",
        "extension_zone",
        "new_balance_date",
        "bank_id",
        "currency",
        "iban",
        "first_statement_number"
})
public class CodaMetadata implements Metadata {

    @JsonbProperty("first_statement_number")
    private Integer firstStatementNumber;
    @JsonbProperty("last_statement_number")
    private Integer lastStatementNumber;
    @JsonbProperty("new_balance_date")
    private LocalDate newBalanceDate;
    @JsonbProperty("iban")
    private String iban;
    @JsonbProperty("bank_id")
    private String bankId;
    @JsonbProperty("currency")
    private String currency;
    @JsonbProperty("movement_count")
    private Integer movementCount;
    @JsonbProperty("extension_zone")
    private String extensionZone;

    @JsonbProperty("movement_count")
    public Integer getMovementCount() {
        return movementCount;
    }

    @JsonbProperty("movement_count")
    public void setMovementCount(Integer movementCount) {
        this.movementCount = movementCount;
    }

    @JsonbProperty("last_statement_number")
    public Integer getLastStatementNumber() {
        return lastStatementNumber;
    }

    @JsonbProperty("last_statement_number")
    public void setLastStatementNumber(Integer lastStatementNumber) {
        this.lastStatementNumber = lastStatementNumber;
    }

    @JsonbProperty("extension_zone")
    public String getExtensionZone() {
        return extensionZone;
    }

    @JsonbProperty("extension_zone")
    public void setExtensionZone(String extensionZone) {
        this.extensionZone = extensionZone;
    }

    @JsonbProperty("new_balance_date")
    public LocalDate getNewBalanceDate() {
        return newBalanceDate;
    }

    @JsonbProperty("new_balance_date")
    public void setNewBalanceDate(LocalDate newBalanceDate) {
        this.newBalanceDate = newBalanceDate;
    }

    @JsonbProperty("bank_id")
    public String getBankId() {
        return bankId;
    }

    @JsonbProperty("bank_id")
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    @JsonbProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonbProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonbProperty("iban")
    public String getIban() {
        return iban;
    }

    @JsonbProperty("iban")
    public void setIban(String iban) {
        this.iban = iban;
    }

    @JsonbProperty("first_statement_number")
    public Integer getFirstStatementNumber() {
        return firstStatementNumber;
    }

    @JsonbProperty("first_statement_number")
    public void setFirstStatementNumber(Integer firstStatementNumber) {
        this.firstStatementNumber = firstStatementNumber;
    }

}

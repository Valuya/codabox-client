package be.valuya.codabox.domain;

public class UnknownCodaboxFormat implements CodaboxFormat {

    private String documentTypeName;
    private String mimeType;

    public UnknownCodaboxFormat() {
    }

    public UnknownCodaboxFormat(String documentTypeName, String mimeType) {
        this.documentTypeName = documentTypeName;
        this.mimeType = mimeType;
    }

    @Override
    public String getFormatName() {
        return documentTypeName;
    }

    public void setDocumentTypeName(String documentTypeName) {
        this.documentTypeName = documentTypeName;
    }

    @Override
    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}

package be.valuya.codabox.domain;

import java.util.Optional;
import java.util.stream.Stream;

public enum KnownCodaboxFormat implements CodaboxFormat {

    PDF("pdf", "application/pdf"),
    XML("xml", "application/xml"),
    COD("cod", "application/vnd.coda");

    private final String formatName;
    private final String mimeType;

    KnownCodaboxFormat(String formatName, String mimeType) {
        this.formatName = formatName;
        this.mimeType = mimeType;
    }

    public static Optional<KnownCodaboxFormat> findByNameOptional(String formatName) {
        KnownCodaboxFormat[] knownCodaboxFormats = values();
        return Stream.of(knownCodaboxFormats)
                .filter(knownCodaboxFormat -> knownCodaboxFormat.hasFormatName(formatName))
                .findFirst();
    }

    @Override
    public String getFormatName() {
        return formatName;
    }

    @Override
    public String getMimeType() {
        return mimeType;
    }

    private boolean hasFormatName(String formatName) {
        return this.formatName.equals(formatName);
    }
}

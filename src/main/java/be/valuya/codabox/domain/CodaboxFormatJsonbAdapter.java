package be.valuya.codabox.domain;

import javax.json.bind.adapter.JsonbAdapter;

public class CodaboxFormatJsonbAdapter implements JsonbAdapter<CodaboxFormat, String> {
    @Override
    public String adaptToJson(CodaboxFormat codaboxFormat) {
        return codaboxFormat.getFormatName();
    }

    @Override
    public CodaboxFormat adaptFromJson(String formatName) {
        return KnownCodaboxFormat.findByNameOptional(formatName)
                .map(CodaboxFormat.class::cast)
                .orElseGet(() -> new UnknownCodaboxFormat(formatName, "application/vnd.codabox." + formatName));
    }
}

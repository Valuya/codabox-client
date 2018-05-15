package be.valuya.codabox.domain;

import javax.json.bind.annotation.JsonbTypeAdapter;

@JsonbTypeAdapter(CodaboxFormatJsonbAdapter.class)
public interface CodaboxFormat {

    String getFormatName();

    String getMimeType();
}

package be.valuya.codabox.jaxrs;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JsonbJaxrsMessageBodyReader<T> implements MessageBodyReader<T> {

    private Jsonb jsonb;
    private JsonbConfig config;

    public JsonbJaxrsMessageBodyReader(JsonbConfig config) {
        this.config = config;
        jsonb = createJsonb();
    }

    protected Jsonb createJsonb() {
        return JsonbBuilder.create(config);
    }

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return mediaType.equals(MediaType.APPLICATION_JSON_TYPE);
    }

    @Override
    public T readFrom(Class<T> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) {
        return jsonb.fromJson(entityStream, genericType);
    }

}
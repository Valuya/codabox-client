package be.valuya.codabox.domain;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import java.util.List;

@JsonbPropertyOrder({
        "name",
        "slug",
        "supported_services"
})
public class Software {

    @JsonbProperty("name")
    private String name;
    @JsonbProperty("slug")
    private String slug;
    @JsonbProperty("supported_services")
    private List<Object> supportedServices;

    @JsonbProperty("name")
    public String getName() {
        return name;
    }

    @JsonbProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonbProperty("slug")
    public String getSlug() {
        return slug;
    }

    @JsonbProperty("slug")
    public void setSlug(String slug) {
        this.slug = slug;
    }

    @JsonbProperty("supported_services")
    public List<Object> getSupportedServices() {
        return supportedServices;
    }

    @JsonbProperty("supported_services")
    public void setSupportedServices(List<Object> supportedServices) {
        this.supportedServices = supportedServices;
    }

}

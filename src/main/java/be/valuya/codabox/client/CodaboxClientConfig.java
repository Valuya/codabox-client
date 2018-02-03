package be.valuya.codabox.client;

import java.net.URI;

public class CodaboxClientConfig {

    private URI baseUri = URI.create("https://sandbox-api.codabox.com");
    private String softwareCompany;
    private String username;
    private char[] password;

    public CodaboxClientConfig() {
    }

    public CodaboxClientConfig(URI baseUri, String softwareCompany, String username, char[] password) {
        this.baseUri = baseUri;
        this.softwareCompany = softwareCompany;
        this.username = username;
        this.password = password;
    }

    public URI getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(URI baseUri) {
        this.baseUri = baseUri;
    }

    public String getSoftwareCompany() {
        return softwareCompany;
    }

    public void setSoftwareCompany(String softwareCompany) {
        this.softwareCompany = softwareCompany;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
}

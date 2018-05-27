package be.valuya.codabox.client;

import be.valuya.codabox.domain.CodaMetadata;
import be.valuya.codabox.domain.CodaboxCustomer;
import be.valuya.codabox.domain.CodaboxFormat;
import be.valuya.codabox.domain.CodaboxFormatJsonbAdapter;
import be.valuya.codabox.domain.Feed;
import be.valuya.codabox.domain.FeedClient;
import be.valuya.codabox.domain.FeedEntry;
import be.valuya.codabox.domain.Fiduciary;
import be.valuya.codabox.domain.KnownCodaboxFormat;
import be.valuya.codabox.domain.LegalEntity;
import be.valuya.codabox.domain.PodClient;
import be.valuya.codabox.domain.Service;
import be.valuya.codabox.domain.Software;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CodaboxClientTest {

    private static final Logger LOGGER = Logger.getLogger(CodaboxClientTest.class.getName());

    private CodaboxClient codaboxClient;

    @BeforeEach
    public void setup() {
        String username = System.getProperty("codabox.client.username");
        String password = System.getProperty("codabox.client.password");
        String softwareCompany = System.getProperty("codabox.client.software.company");
        String baseUriStr = System.getProperty("codabox.client.url");
        URI baseUri = URI.create(baseUriStr);

        char[] passwordChars = password.toCharArray();

        CodaboxClientConfig codaboxClientConfig = new CodaboxClientConfig();
        codaboxClientConfig.setUsername(username);
        codaboxClientConfig.setPassword(passwordChars);
        codaboxClientConfig.setSoftwareCompany(softwareCompany);
        codaboxClientConfig.setBaseUri(baseUri);

        codaboxClient = new CodaboxClient(codaboxClientConfig);
    }

    @Test
    public void testDeserializeGetPodClient() throws IOException, URISyntaxException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL resourceUrl = classLoader.getResource("example/pod_client_example.json");
        URI resourceUri = resourceUrl.toURI();
        Path resourcePath = Paths.get(resourceUri);
        List<String> jsonLines = Files.readAllLines(resourcePath, StandardCharsets.UTF_8);
        String podClientJson = jsonLines.stream()
                .collect(Collectors.joining(" "));
        JsonbConfig jsonbConfig = new JsonbConfig();
        CodaboxFormatJsonbAdapter codaboxFormatJsonbAdapter = new CodaboxFormatJsonbAdapter();
        jsonbConfig.withAdapters(codaboxFormatJsonbAdapter);
        Jsonb jsonb = JsonbBuilder.create(jsonbConfig);
        PodClient podClient = jsonb.fromJson(podClientJson, PodClient.class);
        Assertions.assertNotNull(podClient);
        Map<String, List<CodaboxFormat>> documentTypeFormatListMap = podClient.getDocumentTypeFormatListMap();
        Set<String> documentTypes = documentTypeFormatListMap.keySet();
        Assertions.assertFalse(documentTypes.isEmpty());
    }

    @Test
    public void testGetPodClient() {
        PodClient podClient = codaboxClient.getPodClient();
        Integer fetchDelay = podClient.getFetchDelay();
        Map<String, List<CodaboxFormat>> documentTypeFormatListMap = podClient.getDocumentTypeFormatListMap();
        List<FeedClient> feedClients = podClient.getFeedClients();

        List<CodaboxFormat> allowedFormatsCoda = documentTypeFormatListMap.get("coda");
        Assertions.assertFalse(allowedFormatsCoda.isEmpty(), "should have at least one allowed format for coda");

        LOGGER.info("fetchDelay : " + fetchDelay);

        printAllowedFormats(documentTypeFormatListMap);

        feedClients.forEach(this::printFeedClient);
    }

    @Test
    public void testGetFiduciaries() {
        List<Fiduciary> fiduciaries = codaboxClient.getFiduciaries();

        Assertions.assertFalse(fiduciaries.isEmpty(), "should find at least one fiduciary");

        fiduciaries.forEach(this::printFiduciary);
    }

    @Test
    public void testGetCustomers() {
        List<CodaboxCustomer> customers = codaboxClient.getCustomers();

        Assertions.assertFalse(customers.isEmpty(), "should find at least one customer");

        customers.forEach(this::printCustomer);
    }

    @Test
    public void testGetFeed() {
        testGetFeed(false);
        testGetFeed(true);
    }

    private void testGetFeed(boolean redelivery) {
        PodClient podClient = codaboxClient.getPodClient();

        List<FeedClient> feedClients = podClient.getFeedClients();
        Assertions.assertFalse(feedClients.isEmpty(), "should have at least one feed client");

        feedClients.stream()
                .findFirst()
                .ifPresent(feedClient -> testGetFeed(feedClient, redelivery));
    }

    @Test
    public void testDownload() {
        testDownload(false, true);
        testDownload(true, false);
    }

    private void testDownload(boolean redelivery, boolean entriesExpected) {
        PodClient podClient = codaboxClient.getPodClient();
        List<FeedClient> feedClients = podClient.getFeedClients();

        feedClients.stream()
                .findFirst()
                .ifPresent(feedClient -> testDownloadFromFeed(feedClient, redelivery, entriesExpected));
    }

    private void testDownloadFromFeed(FeedClient feedClient, boolean redelivery, boolean entriesExpected) {
        Feed feed = codaboxClient.getFeed(feedClient, redelivery);
        List<FeedEntry> feedEntries = feed.getFeedEntries();

        if (!entriesExpected) {
            return;
        }
        Assertions.assertFalse(feedEntries.isEmpty(), "should have at least one feed entry");

        feedEntries
                .stream()
                .findFirst()
                .ifPresent(feedEntry -> processFeedEntry(feedClient, feedEntry, redelivery));
    }

    private void processFeedEntry(FeedClient feedClient, FeedEntry feedEntry, boolean redelivery) {
        printFeedEntry(feedEntry);
        downloadFeedEntry(feedEntry, redelivery);
        markAsDownloaded(feedClient, feedEntry, redelivery);
    }

    private void markAsDownloaded(FeedClient feedClient, FeedEntry feedEntry, boolean redelivery) {
        String feedIndex = feedEntry.getFeedIndex();
        Integer feedId = feedClient.getId();
        codaboxClient.markAsDownloaded(feedId, feedIndex, redelivery);
    }

    private void downloadFeedEntry(FeedEntry feedEntry, boolean redownload) {
        String feedIndex = feedEntry.getFeedIndex();
        try (InputStream inputStream = codaboxClient.download(feedIndex, KnownCodaboxFormat.PDF, redownload)) {
            int availableByteCount = inputStream.available();
            byte[] targetArray = new byte[availableByteCount];
            inputStream.read(targetArray);
            Path tempFile = Files.createTempFile("codabox_", ".pdf");
            Files.write(tempFile, targetArray);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void testGetFeed(FeedClient feedClient, boolean redelivery) {
        Feed feed = codaboxClient.getFeed(feedClient, redelivery);
        Assertions.assertNotNull(feed, "should get some feed");

        Integer feedId = feed.getId();
        Assertions.assertNotNull(feedId, "feed should have an id");

        printFeed(feed);
    }

    private void printFiduciary(Fiduciary fiduciary) {
        String id = fiduciary.getId();
        String name = fiduciary.getName();
        String contactEmail = fiduciary.getContactEmail();
        String contactName = fiduciary.getContactName();
        String language = fiduciary.getLanguage();
        Boolean allowsClientDelivery = fiduciary.getAllowsClientDelivery();
        Boolean allowsDoccleDelivery = fiduciary.getAllowsDoccleDelivery();
        Boolean allowsSocialMandates = fiduciary.getAllowsSocialMandates();
        LegalEntity legalEntity = fiduciary.getLegalEntity();
        List<Service> services = fiduciary.getServices();
        Software software = fiduciary.getSoftware();
        Boolean delegatesMandateDelivery = fiduciary.getDelegatesMandateDelivery();
        String websiteUrl = fiduciary.getWebsiteUrl();

        LOGGER.info("id: " + id);
        LOGGER.info("name: " + name);
        LOGGER.info("contactEmail: " + contactEmail);
        LOGGER.info("contactName: " + contactName);
        LOGGER.info("language: " + language);
        LOGGER.info("allowsClientDelivery: " + allowsClientDelivery);
        LOGGER.info("allowsDoccleDelivery: " + allowsDoccleDelivery);
        LOGGER.info("allowsSocialMandates: " + allowsSocialMandates);
        LOGGER.info("legalEntity: " + legalEntity);
        LOGGER.info("software: " + software);
        LOGGER.info("delegatesMandateDelivery: " + delegatesMandateDelivery);
        LOGGER.info("websiteUrl: " + websiteUrl);

        LOGGER.info("services: ");
        services.forEach(this::printService);
    }

    private void printCustomer(CodaboxCustomer codaboxCustomer) {
        String id = codaboxCustomer.getId();
        String contactEmail = codaboxCustomer.getContactEmail();
        String contactName = codaboxCustomer.getContactName();
        String language = codaboxCustomer.getLanguage();
        LegalEntity legalEntity = codaboxCustomer.getLegalEntity();
        List<Service> services = codaboxCustomer.getServices();

        LOGGER.info("id: " + id);
        LOGGER.info("contactEmail: " + contactEmail);
        LOGGER.info("contactName: " + contactName);
        LOGGER.info("language: " + language);
        LOGGER.info("legalEntity: " + legalEntity);

        LOGGER.info("services: ");
        services.forEach(this::printService);
    }

    private void printFeed(Feed feed) {
        Integer id = feed.getId();
        String type = feed.getType();
        List<FeedEntry> feedEntries = feed.getFeedEntries();

        LOGGER.info("id: " + id);
        LOGGER.info("type: " + type);
        feedEntries.forEach(this::printFeedEntry);
    }

    private void printService(Service service) {
        String id = service.getId();
        String state = service.getState();
        String type = service.getType();

        LOGGER.info("  id: " + id);
        LOGGER.info("  state: " + state);
        LOGGER.info("  type: " + type);
    }

    private void printFeedEntry(FeedEntry feedEntry) {
        String documentModel = feedEntry.getDocumentModel();
        String feedIndex = feedEntry.getFeedIndex();
        CodaMetadata metadata = feedEntry.getMetadata();

        LOGGER.info("  documentModel: " + documentModel);
        LOGGER.info("  feedIndex: " + feedIndex);
        printMetadata(metadata);
    }

    private void printMetadata(CodaMetadata metadata) {
        String bankId = metadata.getBankId();
        String currency = metadata.getCurrency();
        String extensionZone = metadata.getExtensionZone();
        Integer firstStatementNumber = metadata.getFirstStatementNumber();
        String iban = metadata.getIban();
        Integer lastStatementNumber = metadata.getLastStatementNumber();
        Integer movementCount = metadata.getMovementCount();
        LocalDate newBalanceDate = metadata.getNewBalanceDate();

        LOGGER.info("    bankId: " + bankId);
        LOGGER.info("    currency: " + currency);
        LOGGER.info("    extensionZone: " + extensionZone);
        LOGGER.info("    firstStatementNumber: " + firstStatementNumber);
        LOGGER.info("    iban: " + iban);
        LOGGER.info("    lastStatementNumber: " + lastStatementNumber);
        LOGGER.info("    movementCount: " + movementCount);
        LOGGER.info("    newBalanceDate: " + newBalanceDate);
    }

    private void printAllowedFormats(Map<String, List<CodaboxFormat>> documentTypeFormatListMap) {
        LOGGER.info("allowedFormats:");
        documentTypeFormatListMap.forEach((documentType, formats) -> printAllowedFormats(documentType, formats));
    }

    private void printAllowedFormats(String documentType, List<CodaboxFormat> formats) {
        Collector<CharSequence, ?, String> hyphenJoiningCollector = Collectors.joining(" - ");
        String allowedFormatListString = formats.stream()
                .map(CodaboxFormat::getFormatName)
                .collect(hyphenJoiningCollector);

        LOGGER.info("  " + documentType + ": " + allowedFormatListString);
    }

    private void printFeedClient(FeedClient feedClient) {
        String clientId = feedClient.getClientId();
        String clientCode = feedClient.getClientCode();
        String clientName = feedClient.getClientName();
        String fiduciaryId = feedClient.getFiduciaryId();
        Object deliveryConfig = feedClient.getDeliveryConfig();
        String targetRootState = feedClient.getTargetRootState();

        LOGGER.info("  clientId : " + clientId);
        LOGGER.info("  clientCode : " + clientCode);
        LOGGER.info("  clientName : " + clientName);
        LOGGER.info("  fiduciaryId : " + fiduciaryId);
        LOGGER.info("  deliveryConfig : " + deliveryConfig);
        LOGGER.info("  targetRootState : " + targetRootState);
    }
}
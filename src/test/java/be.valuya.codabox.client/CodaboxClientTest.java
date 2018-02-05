package be.valuya.codabox.client;

import be.valuya.codabox.domain.AllowedFormats;
import be.valuya.codabox.domain.Feed;
import be.valuya.codabox.domain.FeedClient;
import be.valuya.codabox.domain.FeedEntry;
import be.valuya.codabox.domain.Fiduciary;
import be.valuya.codabox.domain.LegalEntity;
import be.valuya.codabox.domain.Metadata;
import be.valuya.codabox.domain.PodClient;
import be.valuya.codabox.domain.Service;
import be.valuya.codabox.domain.Software;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
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
    public void testGetPodClient() {
        PodClient podClient = codaboxClient.getPodClient();
        Integer fetchDelay = podClient.getFetchDelay();
        AllowedFormats allowedFormats = podClient.getAllowedFormats();
        List<FeedClient> feedClients = podClient.getFeedClients();

        List<String> allowedFormatsCoda = allowedFormats.getCoda();
        Assertions.assertFalse(allowedFormatsCoda.isEmpty(), "should have at least one allowed format for coda");

        LOGGER.info("fetchDelay : " + fetchDelay);

        printAllowedFormats(allowedFormats);

        feedClients.forEach(this::printFeedClient);
    }

    @Test
    public void testGetFiduciaries() {
        List<Fiduciary> fiduciaries = codaboxClient.getFiduciaries();

        Assertions.assertFalse(fiduciaries.isEmpty(), "should find at list one fiduciary");

        fiduciaries.forEach(this::printFiduciary);
    }

    @Test
    public void testGetFeed() {
        PodClient podClient = codaboxClient.getPodClient();

        List<FeedClient> feedClients = podClient.getFeedClients();
        Assertions.assertFalse(feedClients.isEmpty(), "should have at least one feed client");

        feedClients.stream()
                .findFirst()
                .ifPresent(this::testGetFeed);
    }

    @Test
    public void testDownload() {
        PodClient podClient = codaboxClient.getPodClient();
        List<FeedClient> feedClients = podClient.getFeedClients();

        feedClients.stream()
                .findFirst()
                .ifPresent(this::testDownloadFromFeed);
    }

    private void testDownloadFromFeed(FeedClient feedClient) {
        Feed feed = codaboxClient.getFeed(feedClient);
        List<FeedEntry> feedEntries = feed.getFeedEntries();

        Assertions.assertFalse(feedEntries.isEmpty(), "should have at least one feed entry");

        feedEntries
                .stream()
                .findFirst()
                .ifPresent(feedEntry -> processFeedEntry(feedClient, feedEntry));
    }

    private void processFeedEntry(FeedClient feedClient, FeedEntry feedEntry) {
        downloadFeedEntry(feedEntry);
        markAsDownloaded(feedClient, feedEntry);
    }

    private void markAsDownloaded(FeedClient feedClient, FeedEntry feedEntry) {
        String feedIndex = feedEntry.getFeedIndex();
        Integer feedId = feedClient.getId();
        codaboxClient.markAsDownloaded(feedId, feedIndex);
    }

    private void downloadFeedEntry(FeedEntry feedEntry) {
        String feedIndex = feedEntry.getFeedIndex();
        try (InputStream inputStream = codaboxClient.download(feedIndex, "pdf")) {
            int availableByteCount = inputStream.available();
            byte[] targetArray = new byte[availableByteCount];
            inputStream.read(targetArray);
            Path tempFile = Files.createTempFile("codabox_", ".pdf");
            Files.write(tempFile, targetArray);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void testGetFeed(FeedClient feedClient) {
        Feed feed = codaboxClient.getFeed(feedClient);
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
        Metadata metadata = feedEntry.getMetadata();

        LOGGER.info("  documentModel: " + documentModel);
        LOGGER.info("  feedIndex: " + feedIndex);
        printMetadata(metadata);
    }

    private void printMetadata(Metadata metadata) {
        String bankId = metadata.getBankId();
        String currency = metadata.getCurrency();
        String extensionZone = metadata.getExtensionZone();
        Integer firstStatementNumber = metadata.getFirstStatementNumber();
        String iban = metadata.getIban();
        Integer lastStatementNumber = metadata.getLastStatementNumber();
        Integer movementCount = metadata.getMovementCount();
        String newBalanceDate = metadata.getNewBalanceDate();

        LOGGER.info("    bankId: " + bankId);
        LOGGER.info("    currency: " + currency);
        LOGGER.info("    extensionZone: " + extensionZone);
        LOGGER.info("    firstStatementNumber: " + firstStatementNumber);
        LOGGER.info("    iban: " + iban);
        LOGGER.info("    lastStatementNumber: " + lastStatementNumber);
        LOGGER.info("    movementCount: " + movementCount);
        LOGGER.info("    newBalanceDate: " + newBalanceDate);
    }

    private void printAllowedFormats(AllowedFormats allowedFormats) {
        List<String> expenseFormats = allowedFormats.getExpense();
        List<String> purchaseInvoiceFormats = allowedFormats.getPurchaseInvoice();
        List<String> salesInvoiceFormats = allowedFormats.getSalesInvoice();
        List<String> codaFormats = allowedFormats.getCoda();
        List<String> sodaFormats = allowedFormats.getSoda();

        Collector<CharSequence, ?, String> hyphenJoiningCollector = Collectors.joining(" - ");

        LOGGER.info("allowedFormats:");
        LOGGER.info("  expenseFormats : " + expenseFormats.stream().collect(hyphenJoiningCollector));
        LOGGER.info("  purchaseInvoiceFormats : " + purchaseInvoiceFormats.stream().collect(hyphenJoiningCollector));
        LOGGER.info("  salesInvoiceFormats : " + salesInvoiceFormats.stream().collect(hyphenJoiningCollector));
        LOGGER.info("  codaFormats : " + codaFormats.stream().collect(hyphenJoiningCollector));
        LOGGER.info("  sodaFormats : " + sodaFormats.stream().collect(hyphenJoiningCollector));
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
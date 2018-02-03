package be.valuya.codabox.client;

import be.valuya.codabox.domain.AllowedFormats;
import be.valuya.codabox.domain.Feed;
import be.valuya.codabox.domain.FeedClient;
import be.valuya.codabox.domain.FeedEntry;
import be.valuya.codabox.domain.Metadata;
import be.valuya.codabox.domain.PodClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URI;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CodaboxClientTest {

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

        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};

// Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            ;
        }
    }

    @Test
    public void testGetPodClient() {
        PodClient podClient = codaboxClient.getPodClient();
        Integer fetchDelay = podClient.getFetchDelay();

        System.out.println("fetchDelay : " + fetchDelay);

        AllowedFormats allowedFormats = podClient.getAllowedFormats();
        printAllowedFormats(allowedFormats);
        System.out.println("allowedFormats : " + allowedFormats);

        List<FeedClient> feedClients = podClient.getFeedClients();
        feedClients.forEach(this::printFeedClient);
    }

    @Test
    public void testGetFeed() {
        PodClient podClient = codaboxClient.getPodClient();
        List<FeedClient> feedClients = podClient.getFeedClients();
        feedClients.stream()
                .findFirst()
                .ifPresent(this::testGetFeed);
    }

    private void testGetFeed(FeedClient feedClient) {
        Feed feed = codaboxClient.getFeed(feedClient);
        printFeed(feed);
    }

    private void printFeed(Feed feed) {
        Integer id = feed.getId();
        String type = feed.getType();
        List<FeedEntry> feedEntries = feed.getFeedEntries();

        System.out.println("id: " + id);
        System.out.println("type: " + type);
        feedEntries.forEach(this::printFeedEntry);
    }

    private void printFeedEntry(FeedEntry feedEntry) {
        String documentModel = feedEntry.getDocumentModel();
        String feedIndex = feedEntry.getFeedIndex();
        Metadata metadata = feedEntry.getMetadata();
        System.out.println("  documentModel: " + documentModel);
        System.out.println("  feedIndex: " + feedIndex);
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
        System.out.println("    bankId: " + bankId);
        System.out.println("    currency: " + currency);
        System.out.println("    extensionZone: " + extensionZone);
        System.out.println("    firstStatementNumber: " + firstStatementNumber);
        System.out.println("    iban: " + iban);
        System.out.println("    lastStatementNumber: " + lastStatementNumber);
        System.out.println("    movementCount: " + movementCount);
        System.out.println("    newBalanceDate: " + newBalanceDate);
    }

    private void printAllowedFormats(AllowedFormats allowedFormats) {
        List<String> expenseFormats = allowedFormats.getExpense();
        List<String> purchaseInvoiceFormats = allowedFormats.getPurchaseInvoice();
        List<String> salesInvoiceFormats = allowedFormats.getSalesInvoice();
        List<String> codaFormats = allowedFormats.getCoda();
        List<String> sodaFormats = allowedFormats.getSoda();

        Collector<CharSequence, ?, String> hyphenJoiningCollector = Collectors.joining(" - ");
        System.out.println("allowedFormats:");
        System.out.println("  expenseFormats : " + expenseFormats.stream().collect(hyphenJoiningCollector));
        System.out.println("  purchaseInvoiceFormats : " + purchaseInvoiceFormats.stream().collect(hyphenJoiningCollector));
        System.out.println("  salesInvoiceFormats : " + salesInvoiceFormats.stream().collect(hyphenJoiningCollector));
        System.out.println("  codaFormats : " + codaFormats.stream().collect(hyphenJoiningCollector));
        System.out.println("  sodaFormats : " + sodaFormats.stream().collect(hyphenJoiningCollector));
    }

    private void printFeedClient(FeedClient feedClient) {
        String clientId = feedClient.getClientId();
        String clientCode = feedClient.getClientCode();
        String clientName = feedClient.getClientName();
        String fiduciaryId = feedClient.getFiduciaryId();
        Object deliveryConfig = feedClient.getDeliveryConfig();
        String targetRootState = feedClient.getTargetRootState();

        System.out.println("  clientId : " + clientId);
        System.out.println("  clientCode : " + clientCode);
        System.out.println("  clientName : " + clientName);
        System.out.println("  fiduciaryId : " + fiduciaryId);
        System.out.println("  deliveryConfig : " + deliveryConfig);
        System.out.println("  targetRootState : " + targetRootState);
    }
}
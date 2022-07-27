import java.net.URL;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;

import com.microsoft.graph.authentication.TokenCredentialAuthProvider;

import com.microsoft.graph.logger.DefaultLogger;
import com.microsoft.graph.logger.LoggerLevel;
import com.microsoft.graph.models.ApplicationAddPasswordParameterSet;
import com.microsoft.graph.models.PasswordCredential;
import com.microsoft.graph.requests.GraphServiceClient;

public class Graph {
        private static GraphServiceClient<Request> graphClient = null;
        private static TokenCredentialAuthProvider authProvider = null;

        public static void initializeGraphAuth(String CLIENT_ID, String CLIENT_SECRET, String TENANT_GUID,
                        List<String> scopes) {

                // Create the auth provider
                final ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder()
                                .clientId(CLIENT_ID)
                                .clientSecret(CLIENT_SECRET)
                                .tenantId(TENANT_GUID)
                                .build();

                authProvider = new TokenCredentialAuthProvider(scopes, clientSecretCredential);

                // Create default logger to only log errors
                DefaultLogger logger = new DefaultLogger();
                logger.setLoggingLevel(LoggerLevel.DEBUG);

                // Build a Graph client
                graphClient = GraphServiceClient.builder()
                                .authenticationProvider(authProvider)
                                .logger(logger)
                                .buildClient();
        }

        public static String getUserAccessToken() {
                try {
                        URL meUrl = new URL("https://graph.microsoft.com/v1.0/users");
                        return authProvider.getAuthorizationTokenAsync(meUrl).get();
                } catch (Exception ex) {
                        return null;
                }
        }

        public static String addSecrectandFetchToken(String CLIENT_ID, String OBJECT_ID, String TENANT_GUID)
                        throws Exception {
                if (graphClient == null)
                        throw new NullPointerException(
                                        "Graph client has not been initialized. Call initializeGraphAuth before calling this method");

                // GET /me to get authenticated user
                PasswordCredential passwordCredential = new PasswordCredential();
                passwordCredential.displayName = "appsec1";
                passwordCredential.startDateTime = OffsetDateTime.parse("2022-07-27T07:55:47.6125978Z");
                passwordCredential.endDateTime = OffsetDateTime.parse("2022-09-09T13:45:00Z");

                PasswordCredential password = graphClient.applications(OBJECT_ID)
                                .addPassword(ApplicationAddPasswordParameterSet
                                                .newBuilder()
                                                .withPasswordCredential(passwordCredential)
                                                .build())
                                .buildRequest()
                                .post();

                System.out.println("secret: " + password.secretText);
                System.out.println("secret ID: " + password.keyId);

                TimeUnit.SECONDS.sleep(5);
                // Get token
                OkHttpClient client = new OkHttpClient().newBuilder()
                                .build();
                MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                String url = "grant_type=client_credentials&client_id=" + CLIENT_ID
                                + "&scope=https://graph.microsoft.com/.default&client_secret="
                                + password.secretText;
                System.out.println("URL: " + url);

                RequestBody body = RequestBody.create(mediaType, url);
                Request request = new Request.Builder()
                                .url("https://login.microsoftonline.com/" + TENANT_GUID + "/oauth2/v2.0/token")
                                .method("POST", body)
                                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                                .addHeader("Cookie",
                                                "fpc=Aleu0coyCTFDvWAPdBcTfEMl4JdnAgAAAP2McNoOAAAA; stsservicecookie=estsfd; x-ms-gateway-slice=estsfd")
                                .build();
                Response response = client.newCall(request).execute();

                return response.body().string();
        }
}

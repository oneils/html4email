package info.idgst.integration;

import info.idgst.digest.Digest;
import info.idgst.digest.DigestRepository;
import info.idgst.digest.reader.JsonDigestReader;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Integration test for {@link info.idgst.digest.DigestResource}.
 *
 * @author Aliaksei Bahdanau.
 */
public class DigestResourceIntegrationTest extends AbstractIntegrationTest {

    private static final String DIGEST_API_URL = "/api/v1/digests/";

    @Autowired
    private DigestRepository digestRepository;
    @Autowired
    private JsonDigestReader digestReader;


    @Override
    public void before() throws Exception {
        super.before();

        String path = "src/test/resources/digest.json";
        Digest digest = digestReader.readDigest(Files.readAllBytes(Paths.get(path)));

        digestRepository.save(digest);
    }

    @Override
    public void after() throws Exception {
        super.after();

        digestRepository.deleteAll();
    }

    @Test
    public void digestResource_notFound() throws IOException {
        // Setup
        final String name = RandomStringUtils.randomAlphabetic(8);
        final HttpGet request = new HttpGet(protocol + "://" + serverHost + ":" + serverPort + DIGEST_API_URL + name);

        // Run
        final CloseableHttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Verify
        assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void digestReturnedAsJson() throws IOException {
        String jsonMimeType = "application/json";
        final HttpGet request = new HttpGet(protocol + "://" + serverHost + ":" + serverPort + DIGEST_API_URL);

        // Run
        final CloseableHttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Verify
        final String mimeType = ContentType.getOrDefault(httpResponse.getEntity()).getMimeType();
        assertThat(mimeType, is(jsonMimeType));
    }
}

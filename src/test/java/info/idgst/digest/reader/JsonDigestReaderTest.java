package info.idgst.digest.reader;

import com.google.gson.JsonSyntaxException;
import info.idgst.AbstractTest;
import info.idgst.digest.Digest;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Aliaksei Bahdanau
 */
public class JsonDigestReaderTest extends AbstractTest {

    private DigestReader digestReader;

    @Override
    public void before() throws Exception {
        super.before();

        digestReader = new JsonDigestReader();
    }

    @Test
    public void readDigest_correctExecution() throws IOException {
        // Setup
        String path = "src/test/resources/digest.json";

        // Run
        Digest digest = digestReader.readDigest(Files.readAllBytes(Paths.get(path)));

        // Verify
        assertThat(digest.getTopics(), hasSize(2));
        assertThat(digest.getTitle(), is("Digest #4"));
        assertThat(digest.getContributeTo(), is("your_company@mail.com"));
    }

    @Test(expected = JsonSyntaxException.class)
    public void readDigest_invalidJson() throws IOException {
        String path = "src/test/resources/invalid-json.json";
        digestReader.readDigest(Files.readAllBytes(Paths.get(path)));
    }
}
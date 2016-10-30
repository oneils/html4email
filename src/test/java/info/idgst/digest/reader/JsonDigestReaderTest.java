package info.idgst.digest.reader;

import com.google.gson.JsonSyntaxException;
import info.idgst.AbstractTest;
import info.idgst.digest.Digest;
import info.idgst.exception.IdgstException;
import org.junit.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Aliaksei Bahdanau
 */
public class JsonDigestReaderTest extends AbstractTest {

    private DigestReader digestReader;

    @Override
    public void before() {
        super.before();

        digestReader = new JsonDigestReader();
    }

    @Test
    public void readDigest_correctExecution() {
        // Setup
        String path = "src/test/resources/digest.json";

        // Run
        Digest digest = digestReader.readDigest(path);

        // Verify
        assertThat(digest.getTopics(), hasSize(2));
        assertThat(digest.getTitle(), is("Digest #4"));
        assertThat(digest.getContributeTo(), is("your_company@mail.com"));
    }

    @Test(expected = JsonSyntaxException.class)
    public void readDigest_invalidJson() {
        digestReader.readDigest("src/test/resources/invalid-json.json");
    }

    @Test(expected = IdgstException.class)
    public void readDigest_fileNotFound() {
        digestReader.readDigest("unknown_file_path.json");
    }
}
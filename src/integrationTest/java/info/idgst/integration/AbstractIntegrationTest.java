package info.idgst.integration;

import info.idgst.BaseWebTest;
import info.idgst.IdgstServer;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Abstract test for Integration tests.
 *
 * @author Aliaksei Bahdanau.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IdgstServer.class)
@WebIntegrationTest(randomPort = true)
public abstract class AbstractIntegrationTest implements BaseWebTest {
    @Value("${local.server.port}")
    protected int serverPort;

    protected String serverHost = "localhost";

    protected String protocol = "http";

    @Override
    public void before() throws Exception {

    }

    @Override
    public void after() throws Exception {

    }
}

package info.idgst.selenium;

import info.idgst.IdgstServer;
import org.fluentlenium.adapter.FluentTest;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Abstract test for Selenium tests.
 *
 * @author Aliaksei Bahdanau.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IdgstServer.class)
@WebIntegrationTest(randomPort = true)
public abstract class AbstractFluentIntegrationTest extends FluentTest {

    @Value("${local.server.port}")
    protected int serverPort;

    @Override
    public WebDriver getDefaultDriver() {
        return new PhantomJSDriver();
    }

    @Override
    public String getDefaultBaseUrl() {
        return "http://localhost:" + serverPort;
    }
}

package info.devbug;

import info.devbug.HomeController;
import org.fluentlenium.adapter.FluentTest;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Aliaksei Bahdanau.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {HomeController.class})
@WebIntegrationTest(randomPort = true)
public abstract class BaseFluentIntegrationTest extends FluentTest {

    @Value("${local.server.port}")
    private int serverPort;

    @Override
    public WebDriver getDefaultDriver() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);

        // TODO path to the phantomjs should be extractes to the system path
        caps.setCapability("phantomjs.binary.path", "D:\\tools\\phantomjs\\bin\\phantomjs.exe");
        return new PhantomJSDriver(caps);
    }

    public String getDefaultBaseUrl() {
        return "http://localhost:" + serverPort;
    }

}

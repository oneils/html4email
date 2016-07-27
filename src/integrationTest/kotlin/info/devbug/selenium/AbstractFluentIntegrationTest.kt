package info.devbug.selenium

import info.devbug.HomeController
import org.fluentlenium.adapter.FluentTest
import org.junit.Ignore
import org.junit.runner.RunWith
import org.openqa.selenium.WebDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * @author Aliaksei Bahdanau.
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(HomeController::class))
@WebIntegrationTest(randomPort = true)
abstract class AbstractFluentIntegrationTest : FluentTest() {

    @Value("\${local.server.port}")
    protected  var serverPort: Int = 0

    override fun getDefaultDriver(): WebDriver {
        return PhantomJSDriver()
    }

    override fun getDefaultBaseUrl(): String {
        return "http://localhost:" + serverPort
    }
}
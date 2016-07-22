package info.devbug.integration

import info.devbug.HomeController
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * Base integration test.
 *
 * @author Aliaksei Bahdanau.
 */
@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(HomeController::class))
@WebIntegrationTest(randomPort = true)
open class BaseIntegrationTest {

    @Value("\${local.server.port}")
    protected var serverPort: Int = 0

    protected val serverHost = "localhost"
}
package info.devbug.selenium

import org.junit.Test

/**
 * Selenium test for main page.
 *
 * @author Aliaksei Bahdanau.
 */
class MainPageIntegrationTest : AbstractFluentIntegrationTest() {

    @Test
    fun hasPageTitle() {
        goTo("/")
        assert(findFirst("h4").text == "Digests archive")
    }
}
package info.devbug.selenium

import org.junit.Test

/**
 * @author Aliaksei Bahdanau.
 */
class MainPageIntegrationTest : BaseFluentIntegrationTest(){

    @Test
    fun hasPageTitle() {
        goTo("/")
        assert(findFirst("h4").text == "Digests archive")
    }
}
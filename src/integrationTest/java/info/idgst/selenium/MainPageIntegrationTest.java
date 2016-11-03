package info.idgst.selenium;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * It's an example of Selenium Tests for Main page.
 * <p>
 * // TODO should be updated.
 *
 * @author Aliaksei Bahdanau.
 */
public class MainPageIntegrationTest extends AbstractFluentIntegrationTest {

    @Test
    public void hasPageTitle() {
        goTo("/");
        assertThat(findFirst("h5").getText(), is("Digests archive"));
    }
}

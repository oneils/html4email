package info.devbug;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Aliaksei Bahdanau.
 */
public class MainPageIntegrationTest extends BaseFluentIntegrationTest {

    @Test
    public void hasPageTitle() {
        goTo("/");
        assertThat(findFirst("h4").getText()).isEqualTo("Digests archive");
    }
}

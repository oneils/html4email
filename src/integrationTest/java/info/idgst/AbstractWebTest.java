package info.idgst;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Base Test for web testing
 * @author Aliaksei Bahdanau
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IdgstServer.class)
@WebAppConfiguration
@ContextConfiguration(classes = MockServletContext.class)
public abstract class AbstractWebTest implements BaseWebTest{

    protected MockMvc mockMvc;

    @Before
    public void before() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    @Override
    public void after() throws Exception {

    }
}

package info.idgst;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Aliaksei Bahdanau
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractTest implements BaseTest{

    @Before
    @Override
    public void before() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    @Override
    public void after() throws Exception {

    }
}

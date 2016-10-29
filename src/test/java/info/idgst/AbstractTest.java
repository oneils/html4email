package info.idgst;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Aliaksei Bahdanau
 */
@RunWith(MockitoJUnitRunner.class)
public class AbstractTest {

    @Before
    public void before()
    {
        MockitoAnnotations.initMocks(this);
    }
}

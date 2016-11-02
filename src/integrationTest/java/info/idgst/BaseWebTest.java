package info.idgst;

/**
 * Minimal interface for test classes that keeps consistency for common methods.
 *
 * @author Aliaksei Bahdanau.
 */
public interface BaseWebTest {

    /**
     * Method to be called before each test.
     */
    void before() throws Exception;

    /**
     * Method to be called after each test.
     */
    void after() throws Exception;
}

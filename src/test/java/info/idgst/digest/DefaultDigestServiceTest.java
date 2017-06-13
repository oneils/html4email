package info.idgst.digest;

import info.idgst.AbstractTest;
import info.idgst.exception.DigestAlreadyExistsException;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test for {@link DefaultDigestService}.
 *
 * @author Aliaksei Bahdanau
 */
public class DefaultDigestServiceTest extends AbstractTest {

    private static final String DIGEST_TITLE = "Digest title";
    private DefaultDigestService defaultDigestService;
    @Mock
    private DigestCache digestCache;

    @Override
    public void before() throws Exception {
        super.before();

        defaultDigestService = new DefaultDigestService(digestCache);
    }

    @Test
    public void findByTitle_correctExecution() {
        // Setup
        Digest digest = new Digest(DIGEST_TITLE);
        when(digestCache.fetchByTitle(DIGEST_TITLE)).thenReturn(digest);

        // Run
        Digest result = defaultDigestService.findByTitle(DIGEST_TITLE);

        // Verify
        assertThat(result, is(digest));
        verify(digestCache).fetchByTitle(DIGEST_TITLE);
    }

    @Test
    public void findById_correctExecution() {
        // Setup
        String digestID = "57e0e552090c9b862910e5c6";
        Digest digest = new Digest();
        digest.setId(digestID);
        when(digestCache.fetch(digestID)).thenReturn(digest);

        // Run
        Digest result = defaultDigestService.findById(digestID);

        // Verify
        assertThat(result, is(digest));
        verify(digestCache).fetch(digestID);
    }

    @Test
    public void save_correctExecution() {
        // Setup
        Digest digest = new Digest(DIGEST_TITLE);
        when(digestCache.fetchByTitle(DIGEST_TITLE)).thenReturn(null);
        when(digestCache.put(digest)).thenReturn(digest);

        // Run
        Digest result = defaultDigestService.save(digest);

        // Verify
        assertThat(result, is(digest));
        verify(digestCache).put(digest);
    }


    @Test(expected = DigestAlreadyExistsException.class)
    public void save_alreadyExists() {
        // Setup
        Digest digest = new Digest(DIGEST_TITLE);
        when(digestCache.fetchByTitle(DIGEST_TITLE)).thenReturn(digest);

        // Run
        try {
            defaultDigestService.save(digest);
        } finally {
            verify(digestCache).fetchByTitle(DIGEST_TITLE);
        }
    }

    @Test
    public void findAll_correctExecution() {
        // Setup
        int page = 1;
        int size = 5;
        Sort.Direction sortDirection = Sort.Direction.DESC;
        String sortBy = "publishedDate";

        Page<Digest> digestsPage = new PageImpl<>(singletonList(new Digest("Digest title")));
        when(digestCache.fetch(page, size, sortDirection, sortBy)).thenReturn(digestsPage);

        // Run
        Page<Digest> result = defaultDigestService.findAll(page, size, sortDirection, sortBy);

        // Verify
        assertThat(result, is(digestsPage));
        verify(digestCache).fetch(page, size, sortDirection, sortBy);
    }
}
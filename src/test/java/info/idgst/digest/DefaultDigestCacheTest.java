package info.idgst.digest;

import info.idgst.AbstractTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test for {@link DefaultDigestCache}.
 *
 * @author Aliaksei Bahdanau
 */
public class DefaultDigestCacheTest extends AbstractTest {

    private DefaultDigestCache defaultDigestCache;
    @Mock
    private DigestRepository digestRepository;

    @Override
    public void before() throws Exception {
        super.before();

        defaultDigestCache = new DefaultDigestCache(digestRepository);
    }

    @Test
    public void fetchByTitle_correctExecution() {
        // Setup
        String title = "Digest title";
        Digest digest = new Digest(title);
        when(digestRepository.findByTitle(title)).thenReturn(digest);

        // Run
        Digest result = defaultDigestCache.fetchByTitle(title);

        // Verify
        assertThat(result, is(digest));
        verify(digestRepository).findByTitle(title);
    }

    @Test
    public void fetchById_correctExecution() {
        // Setup
        String digestID = "57e0e552090c9b862910e5c6";
        Digest digest = new Digest();
        digest.setId(digestID);
        when(digestRepository.findOne(digestID)).thenReturn(digest);

        // Run
        Digest result = defaultDigestCache.fetch(digestID);

        // Verify
        assertEquals(digest, result);
        verify(digestRepository).findOne(digestID);
    }

    @Test
    public void fetchByPageAndSize_correctExecution() {
        // Setup
        int page = 1;
        int size = 5;

        Sort.Direction sortDirection = Sort.Direction.DESC;
        String sortBy = "publishedDate";
        PageRequest pageRequest = new PageRequest(page, size, sortDirection, sortBy);
        Page<Digest> digestsPage = new PageImpl<>(singletonList(new Digest("Digest title")));
        when(digestRepository.findAll(pageRequest)).thenReturn(digestsPage);

        // Run
        Page<Digest> result = defaultDigestCache.fetch(page, size, sortDirection, sortBy);

        // Verify
        assertEquals(digestsPage, result);
        verify(digestRepository).findAll(pageRequest);
    }
}
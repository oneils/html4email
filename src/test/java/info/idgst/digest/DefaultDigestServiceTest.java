package info.idgst.digest;

import info.idgst.AbstractTest;
import info.idgst.exception.DigestAlreadyExistsException;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;

import java.util.HashMap;

import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Test for {@link DefaultDigestService}.
 *
 * @author Aliaksei Bahdanau
 */
public class DefaultDigestServiceTest extends AbstractTest {

    private DefaultDigestService defaultDigestService;
    @Mock
    private DigestCache digestCache;
    @Mock
    private DigestRepository digestRepository;
    @Mock
    private DigestTemplateProcessor digestTemplateProcessor;
    @Mock
    private DigestMailService digestMailService;

    private static final String DIGEST_TITLE = "Digest title";
    private Digest digest;

    @Override
    public void before() throws Exception {
        super.before();

        digest = new Digest(DIGEST_TITLE);

        defaultDigestService = new DefaultDigestService(digestCache, digestRepository, digestTemplateProcessor, digestMailService);
    }

    @Test
    public void findByTitle_correctExecution() {
        // Setup
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
        when(digestRepository.save(digest)).thenReturn(digest);

        // Run
        Digest result = defaultDigestService.save(digest);

        // Verify
        assertThat(result, is(digest));
        verify(digestRepository).save(digest);
    }


    @Test(expected = DigestAlreadyExistsException.class)
    public void save_alreadyExists() {
        // Setup
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

    @Test
    public void sendViaEmail_correctExecution() {
        // Setup
        int digestNumber = 20;
        HashMap<String, Object> model = new HashMap<>();
        model.put("digestNumber", digestNumber);
        String digestTemplate = "digestTemplate";

        when(digestTemplateProcessor.generateDigest(model)).thenReturn(digestTemplate);

        // Run
        defaultDigestService.sendViaEmail(digest, model);

        // Verify
        verify(digestTemplateProcessor).generateDigest(model);
        verify(digestMailService).sendDigest(digestTemplate, digest.getTitle(), digestNumber);
    }

    @Test
    public void sendViaEmail_digestTemplateIsEmpty() {
        // Setup
        when(digestTemplateProcessor.generateDigest(emptyMap())).thenReturn("");

        // Run
        defaultDigestService.sendViaEmail(digest, anyMap());

        // Verify
        verify(digestMailService, never()).sendDigest(anyString(), anyString(), anyInt());
    }
}
package info.idgst.digest

import info.idgst.AbstractTest
import info.idgst.exception.DigestAlreadyExistsException
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Sort

/**
 * Test for [DefaultDigestService].

 * @author Aliaksei Bahdanau
 */
class DefaultDigestServiceTest : AbstractTest() {
    private val DIGEST_TITLE = "Digest title"

    private lateinit var defaultDigestService: DefaultDigestService
    @Mock
    private lateinit var digestCache: DigestCache
    @Mock
    private lateinit var digestRepository: DigestRepository
    @Mock
    private lateinit var digestTemplateProcessor: DigestTemplateProcessor
    @Mock
    private lateinit var mailService: MailService

    private lateinit var digest: Digest

    override fun before() {
        super.before()

        digest = Digest(DIGEST_TITLE)

        defaultDigestService = DefaultDigestService(digestCache, digestRepository)
    }

    @Test
    fun `findByTitle correct execution`() {
        // Setup
        `when`(digestCache.fetchByTitle(DIGEST_TITLE)).thenReturn(digest)

        // Run
        val result = defaultDigestService.findByTitle(DIGEST_TITLE)

        // Verify
        assertThat(result, `is`<Digest>(digest))
        verify(digestCache).fetchByTitle(DIGEST_TITLE)
    }

    @Test
    fun `findById correct execution`() {
        // Setup
        val digestID = "57e0e552090c9b862910e5c6"
        digest.id = digestID
        `when`(digestCache.fetch(digestID)).thenReturn(digest)

        // Run
        val result = defaultDigestService.findById(digestID)

        // Verify
        assertThat(result, `is`<Digest>(digest))
        verify(digestCache).fetch(digestID)
    }

    @Test
    fun `save correct execution`() {
        // Setup
        `when`(digestRepository.save<Digest>(digest)).thenReturn(digest)

        // Run
        val result = defaultDigestService.save(digest)

        // Verify
        assertThat(result, `is`<Digest>(digest))
        verify(digestRepository).save<Digest>(digest)
    }


    @Test(expected = DigestAlreadyExistsException::class)
    fun `save should throw exception when saving duplicated digest`() {
        // Setup
        `when`(digestCache.fetchByTitle(DIGEST_TITLE)).thenReturn(digest)

        // Run
        try {
            defaultDigestService.save(digest)
        } finally {
            verify(digestCache).fetchByTitle(DIGEST_TITLE)
        }
    }

    @Test
    fun `findAll correct execution`() {
        // Setup
        val page = 1
        val size = 5
        val sortDirection = Sort.Direction.DESC
        val sortBy = "publishedDate"

        val digestsPage = PageImpl(listOf(Digest("Digest title")))
        `when`(digestCache.fetch(page, size, sortDirection, sortBy)).thenReturn(digestsPage)

        // Run
        val result = defaultDigestService.findAll(page, size, sortDirection, sortBy)

        // Verify
        assertThat(result, `is`<Page<Digest>>(digestsPage))
        verify(digestCache).fetch(page, size, sortDirection, sortBy)
    }
}
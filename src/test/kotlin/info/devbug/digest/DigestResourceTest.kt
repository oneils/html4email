package info.devbug.digest

import info.devbug.HomeController
import info.devbug.digest.repository.DigestDto
import info.devbug.digest.service.DigestServiceImpl
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.data.domain.PageImpl
import org.springframework.mock.web.MockServletContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

/**
 * @author Aliaksei Bahdanau
 */
@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(HomeController::class))
@WebAppConfiguration
@ContextConfiguration(classes = arrayOf(MockServletContext::class))
class DigestResourceTest {

    @Mock
    private var digestServiceMock: DigestServiceImpl = Mockito.mock(DigestServiceImpl::class.java, "digestService")

    private var digestResource: DigestResource = DigestResource(digestServiceMock)

    lateinit private var mockMvc: MockMvc


    @Before
    fun setUp() {
        mockMvc = standaloneSetup(digestResource).build();
    }

    @Test
    fun `getDigest should return 200 status for correct endpoint`() {
        given(digestServiceMock.findAll(1, 10)).willReturn(PageImpl<DigestDto>(listOf(DigestDto(), DigestDto())))

        mockMvc.perform(get("/api/v1/digests"))?.andExpect(status().isOk())
    }

    @Test
    fun `getDigest should return 404 status for incorrect endpoint`() {
        mockMvc.perform(get("/api/v1/digests/nonexists"))?.andExpect(status().isNotFound())
    }
}
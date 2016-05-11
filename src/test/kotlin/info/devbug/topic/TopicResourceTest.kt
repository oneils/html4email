package info.devbug.topic

import info.devbug.HomeController
import info.devbug.topic.service.TopicService
import info.devbug.topic.service.TopicServiceImpl
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.mock.web.MockServletContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

/**
 * @author Aliaksei Bahdanau
 */
@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(HomeController::class))
@WebAppConfiguration
@ContextConfiguration(classes = arrayOf(MockServletContext::class))
class TopicResourceTest {
    @Mock
    private var topicServiceMock: TopicService = Mockito.mock(TopicServiceImpl::class.java, "topicService")

    private var topicResource: TopicResource = TopicResource(topicServiceMock)

    private var mockMvc: MockMvc? = null


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mockMvc = MockMvcBuilders.standaloneSetup(topicResource).build();
    }

    @Test
    fun `topics should return a list of topics`() {
        given(topicServiceMock.findAll()).willReturn(listOf(TopicDto("NEWS", 0), TopicDto("Videos", 1)))

        mockMvc?.perform(MockMvcRequestBuilders.get("/v1/topics"))?.andExpect(MockMvcResultMatchers.status().isOk())
    }

    @Test
    fun `findTopicById should return 1 topic`() {
        given(topicServiceMock.findById(1)).willReturn(TopicDto("NEWS", 0))

        mockMvc?.perform(MockMvcRequestBuilders.get("/v1/topics/1"))?.andExpect(MockMvcResultMatchers.status().isOk())
    }
}
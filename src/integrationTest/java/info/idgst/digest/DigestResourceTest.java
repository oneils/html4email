package info.idgst.digest;

import info.idgst.AbstractWebTest;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Test for {@link DigestResource}.
 *
 * @author Aliaksei Bahdanau
 */
public class DigestResourceTest extends AbstractWebTest {

    @Mock
    private DigestService digestService;

    private DigestResource digestResource;

    @Override
    public void before() throws Exception {
        super.before();

        digestResource = new DigestResource(digestService);
        mockMvc = standaloneSetup(digestResource).build();
    }

    @Test
    public void digests_correctExecution() throws Exception {
        given(digestService.findAll(0, 10, Sort.Direction.DESC, "publishedDate")).willReturn(
                new PageImpl<>(Arrays.asList(new Digest(), new Digest())));

        mockMvc.perform(get("/api/v1/digests"))
               .andExpect(status().isOk());
    }

    @Test
    public void digest_resourceNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/digests/nonexists"))
               .andExpect(status().isNotFound());
    }

    @Test
    public void findById_statusOk() throws Exception {
        String digestID = "57d99e57090c9bfba32f0665";
        given(digestService.findById(digestID)).willReturn(new Digest());

        mockMvc.perform(get("/api/v1/digests/" + digestID))
               .andExpect(status().isOk());
    }

    @Test
    public void findById_statusNotFound() throws Exception {
        String digestID = "57d99e57090c9bfba32f0665";

        mockMvc.perform(get("/api/v1/digests/" + digestID))
               .andExpect(status().isNotFound());
    }
}
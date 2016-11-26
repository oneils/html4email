package info.idgst.user;

import info.idgst.AbstractTest;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

/**
 * Test for {@link IdgstUserDetailService}
 *
 * @author Aliaksei Bahdanau.
 */
public class IdgstUserDetailServiceTest extends AbstractTest {

    private UserDetailsService idgstUserDetailService;

    @Mock
    private UserRepository userRepository;

    private static final String USERNAME = "Test UserName";

    @Override
    public void before() throws Exception {
        super.before();

        idgstUserDetailService = new IdgstUserDetailService(userRepository);
    }

    @Test (expected = UsernameNotFoundException.class)
    public void loadUserByUsername_userNotFound() {

        idgstUserDetailService.loadUserByUsername("user");
    }

    @Test
    public void loadUserByUsername_userIsAdmin() {
        // Setup
        AppUser appUser = new AppUser(USERNAME, "secret", true);
        when(userRepository.findByUserName(USERNAME)).thenReturn(appUser);

        // Run
        final UserDetails user = idgstUserDetailService.loadUserByUsername(USERNAME);

        // Verify
        assertThat(user.getUsername(), is(USERNAME));
        assertThat(user.getAuthorities(), containsInAnyOrder(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

    @Test
    public void loadUserByUsername_regularUser() {
        // Setup
        AppUser appUser = new AppUser(USERNAME, "secret", false);
        when(userRepository.findByUserName(USERNAME)).thenReturn(appUser);

        // Run
        final UserDetails user = idgstUserDetailService.loadUserByUsername(USERNAME);

        // Verify
        assertThat(user.getUsername(), is(USERNAME));
        assertThat(user.getAuthorities(), containsInAnyOrder(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
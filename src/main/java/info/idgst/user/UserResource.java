package info.idgst.user;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;

/**
 * Endpoint for retrieving information about user.
 *
 * @author Aliaksei Bahdanau
 */
@RestController
public class UserResource {

    @RequestMapping("/api/v1/user")
    public Principal user(Principal principal) {
        if (principal == null) {
            return new UsernamePasswordAuthenticationToken(
                    new User("guest", "", Collections.singletonList(new SimpleGrantedAuthority("ROLE_GUEST"))), null);
        }
        return principal;
    }
}

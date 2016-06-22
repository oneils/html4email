package info.devbug.user

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

/**
 * @author Aliaksei Bahdanau
 */
@RestController
class UserResource {

    @RequestMapping("/v1/user")
    fun user(principal: Principal?) : Principal {
        if (null == principal) {
            return UsernamePasswordAuthenticationToken(User("guest", "", listOf(SimpleGrantedAuthority("ROLE_GUEST"))
            ), null)
        }
        return principal;
    }
}
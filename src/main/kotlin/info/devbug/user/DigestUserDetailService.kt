package info.devbug.user
package info.devbug.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

/**
 * @author Aliaksei Bahdanau
 */
@Service
class DigestUserDetailService : UserDetailsService {

    private val userRepository: UserRepository

    @Autowired constructor(userRepository: UserRepository) {
        this.userRepository = userRepository
    }

    override fun loadUserByUsername(userName: String): UserDetails {
        val user: UserDto? = userRepository.findByUserName(userName) ?: throw UsernameNotFoundException(String.format("User with the username ${userName} doesn't 	" +
                "exist"))

        if (user!!.admin) {
            val adminAuthorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN")
            return User(user.userName, user.password, adminAuthorities)
        }

        val authorities: List<GrantedAuthority> = emptyList()
        return User(user.userName, user.password, authorities)
    }
}
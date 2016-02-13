package info.devbug.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * @author Aliaksei Bahdanau
 */
@Configuration
@EnableWebSecurity
open class SecurityConfig() : WebSecurityConfigurerAdapter() {

    @Autowired private var userDetailsService: UserDetailsService? = null

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(BCryptPasswordEncoder())
    }
}
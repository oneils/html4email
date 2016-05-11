package info.devbug.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 *
 * Secures the RESTful endpoints with basic authentication.
 *
 * @author Aliaksei Bahdanau
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@Order(1)
open class ApiSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired private var userDetailsService: UserDetailsService? = null

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(BCryptPasswordEncoder())
    }

    override fun configure(http: HttpSecurity) {
        http.httpBasic()
        .and()
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/login", "/logout").permitAll()
                .antMatchers(HttpMethod.POST, "/v1/digests").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/v1/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/v1/**").hasRole("ADMIN")
                .anyRequest().permitAll()
    }
}
package info.devbug.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

/**
 * Configures login form for the rest of our application.
 *
 * @author Aliaksei Bahdanau
 */
@Configuration
open class WebSecurityConfig : WebSecurityConfigurerAdapter(){

    override fun configure(http: HttpSecurity) {
        http
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/profile")
                .and()
                .logout().logoutSuccessUrl("/login")
                .and()
                .authorizeRequests()
                .antMatchers("/public/**", "/login").permitAll()
                .anyRequest().authenticated().and()
                .logout().logoutRequestMatcher(AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
    }
}
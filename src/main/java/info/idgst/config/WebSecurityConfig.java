package info.idgst.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Configures login form for the rest of our application.
 *
 * @author Aliaksei Bahdanau
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/profile")
            .and()
            .logout()
            .logoutSuccessUrl("/login")
            .and()
            .authorizeRequests()
            .antMatchers("/public/**", "/login")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login");
    }
}

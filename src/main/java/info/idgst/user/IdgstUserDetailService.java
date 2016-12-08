package info.idgst.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link UserDetailsService}.
 *
 * @author Aliaksei Bahdanau
 */
@Service
public class IdgstUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public IdgstUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException(
                    String.format("User with the username %s doesn't exist", username));
        }
        if (user.isAdmin()) {
            List<GrantedAuthority> adminAuthorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
            return new User(user.getUserName(), user.getPassword(), adminAuthorities);
        }
        return new User(user.getUserName(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
    }
}
